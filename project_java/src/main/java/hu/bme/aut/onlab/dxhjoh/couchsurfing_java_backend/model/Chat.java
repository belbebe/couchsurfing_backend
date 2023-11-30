package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat")
@Getter
@Setter
@FieldNameConstants
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Long id;

    @Column(name = "message_time", nullable = false)
    private LocalDateTime messageTime;

    @Column(name = "message", nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id_from")
    private User userFrom;

    @Column(name = "user_id_from", insertable = false, updatable = false)
    private Long userIdFrom;

    @ManyToOne
    @JoinColumn(name = "user_id_to")
    private User userTo;

    @Column(name = "user_id_to", insertable = false, updatable = false)
    private Long userIdTo;

    public void assignUserFrom(User user) {
        this.userFrom = user;
    }

    public void assignUserTo(User user) { this.userTo = user; }
}
