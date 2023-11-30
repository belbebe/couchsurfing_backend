package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatMessageResponse {
    private int fromUserId;

    private int toUserId;

    private LocalDateTime messageTime;
    
    private String message;
}
