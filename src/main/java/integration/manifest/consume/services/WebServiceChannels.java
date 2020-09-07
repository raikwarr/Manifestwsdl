package integration.manifest.consume.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
public class WebServiceChannels {
    public static final String WEB_SERVICE_FLOW_INPUT_CHANNEL_NAME = "webServiceFlowInputChannel";
    public static final String WEB_SERVICE_FLOW_OUTPUT_CHANNEL_NAME = "webServiceFlowOutputChannel";

    @Bean(WEB_SERVICE_FLOW_INPUT_CHANNEL_NAME)
    public MessageChannel webServiceFlowInputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean(WEB_SERVICE_FLOW_OUTPUT_CHANNEL_NAME)
    public MessageChannel webServiceFlowOutputChannel() {
        return MessageChannels.direct().get();
    }
}
