package com.sejong.creativesemester.chat.controller;

import com.sejong.creativesemester.chat.controller.res.MessageListInfoResponse;
import com.sejong.creativesemester.chat.controller.res.MessageResponse;
import com.sejong.creativesemester.chat.domain.ChatMessage;
import com.sejong.creativesemester.chat.domain.PublishMessage;
import com.sejong.creativesemester.chat.service.ChatService;
import com.sejong.creativesemester.chat.service.MessageDto;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final ChatService chatService;

    private final ChannelTopic topic;
    private final SimpMessageSendingOperations messageSendingOperations;

    @Resource(name = "chatRedisTemplate")
    private final RedisTemplate redisTemplate;

    @MessageMapping("/chats/messages/{room-id}")
    public void message(@DestinationVariable("room-id") Long roomId, MessageDto messageDto) {

        PublishMessage publishMessage =
                new PublishMessage(messageDto.getRoomId(), messageDto.getSenderStudentNum(), messageDto.getContent(), LocalDateTime.now());
        log.info("/sub/chats/messages/"+roomId);
        String topic1 = topic.getTopic();
        log.info(topic1);
        messageSendingOperations.convertAndSend("/sub/chats/messages/"+roomId, publishMessage);
        // 채팅방에 메세지 전송
//        redisTemplate.convertAndSend("/sub/chats/messages/"+roomId, publishMessage);
        log.info("레디스 서버에 메세지 전송 완료");

        chatService.saveMessage(messageDto, roomId);
    }

    // 채팅메세지 가져오기
    @GetMapping("/chats/messages/{room-id}")
    public SuccessResponse getMessages(@Positive @PathVariable("room-id") long roomId,
                                       @Positive @RequestParam(defaultValue = "1") int page,
                                       @Positive @RequestParam(defaultValue = "10") int size,
                                       Authentication authentication) {

        // 해당 채팅방의 메세지를 가져와야 함
        Page<ChatMessage> messages = chatService.findMessages(roomId, page, size);
        PageInfo pageInfo = new PageInfo(page, size, (int)messages.getTotalElements(), messages.getTotalPages());
        List<ChatMessage> messageList = messages.getContent();
        List<MessageResponse> messageResponseList = messageList.stream().map(chatMessage -> MessageResponse.builder()
                .messageId(chatMessage.getMessageId())
                .senderStudentNum(chatMessage.getSender().getStudentNum())
                .content(chatMessage.getContent())
                .sendTime(chatMessage.getSendTime())
                .build()).collect(Collectors.toList());

        return new SuccessResponse<>(MessageListInfoResponse.builder()
                .messageResponseList(messageResponseList)
                .pageInfo(pageInfo)
                .build());
    }
}
