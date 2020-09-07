package integration.manifest.consume.common;

import org.springframework.integration.ws.SimpleWebServiceInboundGateway;
import org.springframework.messaging.MessageChannel;

public class GatewayUtils {

    public static SimpleWebServiceInboundGateway createSimpleWebServiceInboundGateway(final String channelName, final MessageChannel errorChannel) {

        final SimpleWebServiceInboundGateway inboundGateway = new SimpleWebServiceInboundGateway();
        inboundGateway.setErrorChannel(errorChannel);
        inboundGateway.setRequestChannelName(channelName);
        inboundGateway.setExtractPayload(false);

        return inboundGateway;
    }
}
