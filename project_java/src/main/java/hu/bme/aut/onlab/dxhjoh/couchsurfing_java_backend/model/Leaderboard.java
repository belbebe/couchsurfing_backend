package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leader_board")
@Getter
@Setter
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lb_id", nullable = false)
    private Long id;

    @Column(name = "tenant_score", nullable = false)
    private int tenantScore;

    @Column(name = "host_score", nullable = false)
    private int host_score;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
