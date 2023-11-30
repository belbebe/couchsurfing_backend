package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.ChatMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Chat;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.ChatRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.ChatMessageRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.ChatMessageResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.ChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    @Override
    public ChatMessageResponse createChatMessage(ChatMessageRequest req) {
        User fromUser = userRepository.findById(req.getFromUserId()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));
        User toUser = userRepository.findById(req.getToUserId()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));

        Chat message = chatMapper.toEntity(req);
        message.setMessageTime(LocalDateTime.now());
        message.assignUserFrom(fromUser);
        message.assignUserTo(toUser);
        return chatMapper.toResponse(chatRepository.save(message));
    }

    @Override
    public List<ChatMessageResponse> getMessagesForConversation(Long currentUserId, Long partnerUserId) {
        User currentUser = userRepository.findById(currentUserId.intValue()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));
        User partnerUser = userRepository.findById(partnerUserId.intValue()).orElseThrow(() -> new EntityNotFoundException("User entity not found."));

        Pageable fiftyMostRecentMessages = PageRequest.of(0, 50, Sort.by("message_time").descending());
        List<Chat> messages = chatRepository.findMessagesForConversation(currentUserId, partnerUserId, fiftyMostRecentMessages);
        return chatMapper.toResponseList(messages);
    }
}
