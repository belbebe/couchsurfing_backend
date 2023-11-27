package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.LeaderboardRole;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.CanNotRateUserWithoutBookingHistoryException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.CouchsurfingRuntimeException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.LeaderboardMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Leaderboard;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.data_projection_interface.LeaderboardScoreProjection;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.BookingRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.LeaderboardRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LeaderboardRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.LeaderboardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardMapper lbMapper;

    private final LeaderboardRepository lbRepository;

    private final BookingRepository bookingRepository;

    private final UserRepository userRepository;

    @Override
    public List<LeaderboardScoreProjection> getScores(LeaderboardRole role) {
        if (role == LeaderboardRole.TENANT) {
            return lbRepository.getTenantScore();
        }

        return lbRepository.getOwnerScore();
    }

    @Override
    public Long createTenantScore(LeaderboardRequest lbReq) {
        if (lbReq.getTenantScore() == null) {
            throw new CouchsurfingRuntimeException("Tenant score field can't be empty when creating a tenant score.");
        }

        User tenant = userRepository.findById(lbReq.getUserId()).orElseThrow(() -> new EntityNotFoundException("Tenant user entity not found."));
        User scoringUser = userRepository.findById(lbReq.getOwnUserId()).orElseThrow(() -> new EntityNotFoundException("Scoring user entity not found."));

        List<Long> bookings = bookingRepository.findBookingIdByTenantIdAndOwnerId(tenant.getId(), scoringUser.getId());

        if (bookings.isEmpty()) {
            throw new CanNotRateUserWithoutBookingHistoryException("Can not rate tenant user if they haven't booked the rating user's room.");
        }

        Leaderboard lb = lbMapper.toEntity(lbReq);
        lb.assignUser(tenant);

        return lbRepository.save(lb).getId();
    }

    @Override
    public Long createOwnerScore(LeaderboardRequest lbReq) {
        if (lbReq.getHostScore() == null) {
            throw new CouchsurfingRuntimeException("Host score field can't be empty when creating an owner score.");
        }

        User owner = userRepository.findById(lbReq.getUserId()).orElseThrow(() -> new EntityNotFoundException("Owner user entity not found."));
        User scoringUser = userRepository.findById(lbReq.getOwnUserId()).orElseThrow(() -> new EntityNotFoundException("Scoring user entity not found."));

        List<Long> bookings = bookingRepository.findBookingIdByTenantIdAndOwnerId(scoringUser.getId(), owner.getId());

        if (bookings.isEmpty()) {
            throw new CanNotRateUserWithoutBookingHistoryException("Can not rate owner user if they haven't booked the rated user's room.");
        }

        Leaderboard lb = lbMapper.toEntity(lbReq);
        lb.assignUser(owner);

        return lbRepository.save(lb).getId();
    }
}
