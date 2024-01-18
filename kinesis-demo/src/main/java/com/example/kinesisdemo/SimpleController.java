package com.example.kinesisdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.services.kinesis.model.PutRecordResponse;

@RestController
@RequestMapping("/api")
public class SimpleController {

    private final KinesisClient kinesisClient;
    private final String kinesisStreamName;

    public SimpleController(KinesisClient kinesisClient, @Value("${aws.kinesis.streamName}") String kinesisStreamName) {
        this.kinesisClient = kinesisClient;
        this.kinesisStreamName = kinesisStreamName;
    }

    @GetMapping("/sendToKinesis/{message}")
    public String sendToKinesis(@PathVariable String message) {
        sendDataToKinesis(message);
        return "Message sent to Kinesis: " + message;
    }

    private void sendDataToKinesis(String message) {
        SdkBytes sdkBytesData = SdkBytes.fromUtf8String(message);

        PutRecordRequest putRecordRequest = PutRecordRequest.builder()
                .streamName(kinesisStreamName)
                .data(sdkBytesData)
                .partitionKey("somePartitionKey")
                .build();

        PutRecordResponse response = kinesisClient.putRecord(putRecordRequest);

        System.out.println("Record sent to Kinesis. SequenceNumber: " + response.sequenceNumber());
    }
}

