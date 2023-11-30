package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.ChatMessageRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.ChatMessageResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.ChatService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private final ChatService chatService;

    @GetMapping("/api/chat")
    public @ResponseBody ResponseEntity<List<ChatMessageResponse>> getMessagesForConversation(
            @RequestParam @NotNull Integer currentUserId,
            @RequestParam @NotNull Integer partnerUserId
    ) {
        return ResponseEntity.ok(chatService.getMessagesForConversation(currentUserId.longValue(), partnerUserId.longValue()));
    }

    @PostMapping("/api/chat")
    public @ResponseBody ResponseEntity<ChatMessageResponse> createMessage(@RequestBody @NotNull ChatMessageRequest messageRequest) {
        ChatMessageResponse res = chatService.createChatMessage(messageRequest);

        // send the new message to the receiving user via WebSocket topic,
        // where the receiving user's id is the 2nd parameter and the sending user's id is the 1st
        simpMessagingTemplate.convertAndSend(String.format("/chat/%d/%d", messageRequest.getFromUserId(), messageRequest.getToUserId()), res);

        return ResponseEntity.ok(res);
    }

    /*
    @MessageMapping("/chat")
    public void sendMessage(
            @Payload ChatMessageRequest message,
            Principal user,
            @Header("simpSessionId") String sessionId
    ) throws Exception {
        simpMessagingTemplate.convertAndSend(
                "/secured/user/queue/specific-user",
                chatService.createChatMessage(message));
        simpMessagingTemplate.convertAndSendToUser(
                String.valueOf(message.getToUserId()),
                "/secured/user/queue/specific-user",
                chatService.createChatMessage(message));
    }
     */
}
