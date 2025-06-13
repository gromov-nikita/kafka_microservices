package com.gromov.dbsaver.service.grpc;

import com.gromov.notification_grpc.EventRequest;
import com.gromov.notification_grpc.EventResponse;
import com.gromov.notification_grpc.NotificationServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GrpcNotificationService {

    private final GrpcNotificationClient grpcNotificationClient;

    public void notValidNotify() {
        System.out.println("Notification sent, success: " +
                grpcNotificationClient.notifyEvent(EventRequest.newBuilder()
                        .setEventId(UUID.randomUUID().toString())
                        .setMessage("Invalid assignment DTO received")
                        .setTimestamp(System.currentTimeMillis())
                        .build()).getSuccess()
        );
    }
}
