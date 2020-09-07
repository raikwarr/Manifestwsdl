package integration.manifest.consume.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ws.SimpleWebServiceInboundGateway;
import org.springframework.ws.config.annotation.DelegatingWsConfiguration;
import org.springframework.ws.server.EndpointMapping;
import org.springframework.ws.server.endpoint.mapping.UriEndpointMapping;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

import javax.servlet.Servlet;
import static integration.manifest.consume.services.WebServiceGateway.WEB_SERVICE_INBOUND_GATEWAY_BEAN_NAME;


@EnableIntegration
@Configuration
public class WebServiceConfig extends DelegatingWsConfiguration {

    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        final MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/" + "*");
    }

    @Bean(name="DMSFortoldningsangivelseHent_unsecure")
    public SimpleWsdl11Definition defaultWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/ws/wsdl/DMSFortoldningsangivelseHent_unsecure.wsdl"));

        return wsdl11Definition;
    }

    @Bean
    public EndpointMapping uriEndpointMapping(
            @Qualifier(WEB_SERVICE_INBOUND_GATEWAY_BEAN_NAME) SimpleWebServiceInboundGateway webServiceInboundGateway) {
        UriEndpointMapping endpointMapping = new UriEndpointMapping();
        endpointMapping.setUsePath(true);
        endpointMapping.setDefaultEndpoint(webServiceInboundGateway);

        return endpointMapping;
    }
}

