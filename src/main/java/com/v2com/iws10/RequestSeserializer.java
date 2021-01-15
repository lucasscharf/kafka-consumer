package com.v2com.iws10;

import com.google.protobuf.InvalidProtocolBufferException;
import com.v2com.iws10.xwitch.canonical.protobuf.Protocol.Response;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSeserializer implements Deserializer<CommunicationLog> {

  private static final Logger logger = LoggerFactory.getLogger(CommunicationLog.class);

  public Response deserialize(String topic, byte[] data) {
    try {
      Response response = Response.parseFrom(data);
      logger.trace("Parsed request [{}] to topic [{}]", response, topic);
      return response;
    } catch (InvalidProtocolBufferException e) {
      logger.error("Error deserializing message from topic [{}]", topic, e);
    }
    return null;
  }
}
