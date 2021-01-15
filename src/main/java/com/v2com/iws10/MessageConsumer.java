package com.v2com.iws10;

import javax.enterprise.context.ApplicationScoped;

import com.v2com.iws10.xwitch.canonical.protobuf.Protocol.Response;
import com.v2com.iws10.xwitch.canonical.protobuf.Reading.Readings;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MessageConsumer {
  Logger logger = LoggerFactory.getLogger(getClass());

  @Incoming("switch-device-response")
  public void consumeSwitchDeviceResponse(Response response) throws Exception {
    logger.info("Response [{}]", response);

    if (!"type.googleapis.com/com.v2com.iws10.xwitch.canonical.protobuf.Readings"
        .equals(response.getPayload().getTypeUrl()))
      return;
    Readings readings = response.getPayload().unpack(Readings.class);
    logger.info("Readings [{}]", readings);
  }
}
