package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query(value = "SELECT * FROM chat WHERE " +
            "(chat.user_id_from = ?1 AND chat.user_id_to = ?2) OR (chat.user_id_to = ?1 AND chat.user_id_from = ?2)", nativeQuery = true)
    List<Chat> findMessagesForConversation(Long currentUserId, Long partnerUserId, Pageable pageable);
}
