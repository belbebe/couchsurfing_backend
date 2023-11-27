package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Entity
@Table(name = "leader_board")
@Getter
@Setter
@FieldNameConstants
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lb_id", nullable = false)
    private Long id;

    @Column(name = "tenant_score")
    private int tenantScore;

    @Column(name = "host_score")
    private int hostScore;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public void assignUser(User user) {
        this.user = user;
    }
}
