package com.gromov.csvReader.service.grpc;

import com.gromov.notification_grpc.EventRequest;
import com.gromov.notification_grpc.EventResponse;
import com.gromov.notification_grpc.NotificationServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
@Slf4j
public class NotificationServiceImpl extends NotificationServiceGrpc.NotificationServiceImplBase {

    private static final String responseMessage = "Event processed successfully. EventId:";

    @Override
    public void notifyEvent(EventRequest request, StreamObserver<EventResponse> responseObserver) {
        log.warn("Received event: eventId={}, message={}",request.getEventId(),request.getMessage());
        EventResponse response = EventResponse.newBuilder()
                .setSuccess(true)
                .setDetails(responseMessage + request.getEventId())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
