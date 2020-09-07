package integration.manifest.consume.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.stereotype.Service;

import static org.springframework.integration.context.IntegrationContextUtils.ERROR_CHANNEL_BEAN_NAME;

@Service
public class GenericErrorHandlingService {

    public static final String ERROR_MESSAGE = "Error occurred when handling the message.";
    private static final String LOG_MESSAGE_PREFIX = "General error channel handler: ";

    /**
     * Handler for the default errorChannel. It wraps any incoming exception message to a MessageHandlingException. If
     * the MessageHandlingException is propagated to a WS Gateway, the Gateway transforms the exception into SoapFault.
     *
     * @param message Message with an exception
     */
    @ServiceActivator(inputChannel = ERROR_CHANNEL_BEAN_NAME)
    public void handleGenericException(final Message message) {
        Throwable originalException = null;
        if (message.getPayload() instanceof Throwable) {
            originalException = ExceptionUtils.getRootCause((Throwable) message.getPayload());
        }
        if (originalException != null) {
            logException(message, originalException.getMessage());
            throw new MessageHandlingException(message, originalException.getMessage());
        } else {
            logException(message, ERROR_MESSAGE);
            throw new MessageHandlingException(message, ERROR_MESSAGE);
        }
    }

    private void logException(final Message message, final String errorMessage) {
        final Throwable exception = message.getPayload() instanceof Throwable ? (Throwable) message.getPayload() : null;
        System.out.println(LOG_MESSAGE_PREFIX + errorMessage + exception);
    }
}
