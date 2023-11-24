package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.Currency;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;

@Entity
@Table(name = "booking")
@Getter
@Setter
@FieldNameConstants
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "payment_method", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @ColumnTransformer(write = "?::payment_method_type")
    private PaymentMethod paymentMethod;

    @Column(name = "num_of_guests", nullable = false)
    private int numOfGuests;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @Column(name = "pay_with_chores", nullable = false)
    private boolean payWithChores;

    @Column(name = "total_price", nullable = false)
    private float totalPrice;

    @Column(name = "currency", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @ColumnTransformer(write = "?::currency_enum")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private User user;

    @Column(name = "tenant_id", insertable = false, updatable = false)
    private Long tenantId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "room_id", insertable = false, updatable = false)
    private Long roomId;

    public void assignUser(User user) {
        this.user = user;
    }

    public void assignRoom(Room room) {
        this.room = room;
    }
}
