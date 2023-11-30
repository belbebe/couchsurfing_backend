package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.Chat;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.ChatMessageRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.ChatMessageResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChatMapper {

    @Mapping(source = "userFrom", target = "fromUserId", qualifiedByName = "UserToUserId")
    @Mapping(source = "userTo", target = "toUserId", qualifiedByName = "UserToUserId")
    ChatMessageResponse toResponse(Chat entity);

    List<ChatMessageResponse> toResponseList(List<Chat> entity);

    Chat toEntity(ChatMessageRequest request);

    Chat update(@MappingTarget Chat entity, ChatMessageRequest request);

    @Named("UserToUserId")
    public static int userToUserId(User user) {
        return user.getId().intValue();
    }
}
