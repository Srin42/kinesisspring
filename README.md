# Kinesis Spring Demo

This Spring Boot project demonstrates integration with Amazon Kinesis for data streaming.

## Key Components

### Configuration File (`KinesisConfig.java`)

Defines the Kinesis client bean and configures its region based on the `aws.kinesis.region` property.

### Controller File (`Controller.java`)

A REST controller with an endpoint `/api/hello` that sends a message to the configured Kinesis stream.

### Dependencies

- Spring Boot: Provides a framework for building Java-based enterprise applications.
- AWS SDK for Java: Enables interaction with AWS services, including Kinesis.

### Application Properties (`application.properties`)

Configure AWS credentials and Kinesis stream details:

properties
aws.accessKey=your-access-key
aws.secretKey=your-secret-key
aws.kinesis.streamName=your-kinesis-stream
aws.kinesis.region=your-region

How It Works

Controller Endpoint (/api/hello): Triggers a message to be sent to the configured Kinesis stream.

Kinesis Configuration (KinesisConfig.java): Configures the Kinesis client with the specified AWS region.

Kinesis Integration (Controller.java): Utilizes the Kinesis client to send a message to the specified stream.
