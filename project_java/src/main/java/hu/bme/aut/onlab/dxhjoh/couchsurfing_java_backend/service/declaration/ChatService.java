package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.ChatMessageRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.ChatMessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService {
    ChatMessageResponse createChatMessage(ChatMessageRequest req);

    List<ChatMessageResponse> getMessagesForConversation(Long currentUserId, Long partnerUserId);
}
