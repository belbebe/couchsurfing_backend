package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Booking;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.AccommodationRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Room> findRoomsBasedOnSearchConditions(AccommodationRequest accReq) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Room> query = cb.createQuery(Room.class);

        Root<Room> room = query.from(Room.class);
        List<Predicate> andPredicates = new ArrayList<>();

        andPredicates.add(cb.like(room.get(Room.Fields.address), "%" + accReq.getCity() + "%"));

        for(PaymentMethod pm: accReq.getPaymentMethod()) {
            switch(pm) {
                case CASH -> andPredicates.add(cb.isTrue(room.get(Room.Fields.payingWithCashPossible)));

                case CARD -> andPredicates.add(cb.isTrue(room.get(Room.Fields.payingWithCardPossible)));
            }
        }

        if(accReq.getPriceFrom() != null) {
            andPredicates.add(cb.greaterThanOrEqualTo(room.get(Room.Fields.price), accReq.getPriceFrom()));
        }

        if(accReq.getPriceTo() != null) {
            andPredicates.add(cb.lessThanOrEqualTo(room.get(Room.Fields.price), accReq.getPriceTo()));
        }

        if(accReq.getCurrency() != null) {
            andPredicates.add(cb.equal(room.get(Room.Fields.currency), accReq.getCurrency()));
        }

        // leaderboard owner rating alapján lekérni a szállást
        if(accReq.getRatingFrom() != null) {
            // TODO
        }

        if(accReq.getRatingTo() != null) {
            // TODO
        }

        if (accReq.getNonSmoking()) {
            andPredicates.add(cb.isTrue(room.get(Room.Fields.nonSmoking)));
        }

        if (accReq.getPetFriendly()) {
            andPredicates.add(cb.isTrue(room.get(Room.Fields.petFriendly)));
        }

        if (accReq.getAirConditioner()) {
            andPredicates.add(cb.isTrue(room.get(Room.Fields.airConditioner)));
        }

        if (accReq.getParking()) {
            andPredicates.add(cb.isTrue(room.get(Room.Fields.parking)));
        }

        if (accReq.getBicycleStorage()) {
            andPredicates.add(cb.isTrue(room.get(Room.Fields.bicycleStorage)));
        }


        andPredicates.add(cb.notEqual(room.get(Room.Fields.ownerId), accReq.getTenantId()));

        andPredicates.add(occupiedRoomsSubquery(cb, query, room, accReq.getStartDate(), accReq.getEndDate()));

        query.select(room).where(cb.and(andPredicates.toArray(new Predicate[andPredicates.size()])));

        return em.createQuery(query).getResultList();
    }

    public Predicate occupiedRoomsSubquery(CriteriaBuilder cb, CriteriaQuery<?> query, Root<Room> root, LocalDate startDate, LocalDate endDate) {
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<Booking> subroot = subquery.from(Booking.class);

        subquery.select(subroot.get(Booking.Fields.roomId));
        subquery.distinct(true);

        subquery.where(cb.or(cb.greaterThanOrEqualTo(
                subroot.get(Booking.Fields.endDate), startDate)),
                cb.lessThanOrEqualTo(subroot.get(Booking.Fields.startDate), endDate));

        return cb.not(cb.in(root.get(Room.Fields.id)).value(subquery));
    }
}
