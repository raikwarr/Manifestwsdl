package integration.manifest.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@SpringBootApplication
public class IntegrationManifestConsumeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationManifestConsumeApplication.class, args);
	}

}
