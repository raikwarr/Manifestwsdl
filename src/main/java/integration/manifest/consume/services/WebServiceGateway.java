package integration.manifest.consume.services;
import integration.manifest.consume.common.GatewayUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.ws.SimpleWebServiceInboundGateway;
import org.springframework.messaging.MessageChannel;

import static integration.manifest.consume.services.WebServiceChannels.WEB_SERVICE_FLOW_INPUT_CHANNEL_NAME;


@Configuration
public class WebServiceGateway {
    public static final String WEB_SERVICE_INBOUND_GATEWAY_BEAN_NAME = "webServiceInboundGateway";

    @Bean(name = WEB_SERVICE_INBOUND_GATEWAY_BEAN_NAME)
    public SimpleWebServiceInboundGateway webServiceInboundGateway(
                                                                        @Qualifier("errorChannel") MessageChannel errorChannel) {

        return GatewayUtils.createSimpleWebServiceInboundGateway(WEB_SERVICE_FLOW_INPUT_CHANNEL_NAME, errorChannel);
    }
}
