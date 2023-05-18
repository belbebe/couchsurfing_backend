package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.enums.PaymentMethod;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Room;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private int id;

    private LocalDate startDate;

    private LocalDate endDate;

    private PaymentMethod paymentMethod;

    private boolean approved;

    private int numOfGuests;

    private String additionalNotes;

    private boolean payWithChores;

    private float totalPrice;
}
