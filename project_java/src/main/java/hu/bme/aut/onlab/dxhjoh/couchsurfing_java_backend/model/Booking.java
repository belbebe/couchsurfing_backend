package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private int id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @Column(name = "num_of_guests", nullable = false)
    private int numOfGuests;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @Column(name = "pay_with_chores", nullable = false)
    private boolean payWithChores;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
