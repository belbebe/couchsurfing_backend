package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChatMessageRequest {
    @NotNull
    private int fromUserId;

    @NotNull
    private int toUserId;

    @NotNull
    private String message;
}
