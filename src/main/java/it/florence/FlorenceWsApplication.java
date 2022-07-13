package it.florence;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import it.florence.config.DefaultProfileUtil;
import it.florence.config.properties.ApplicationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class FlorenceWsApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(FlorenceWsApplication.class);

	private final Environment env;

	public FlorenceWsApplication(Environment env) {
		this.env = env;
	}

	/**
	 * Main method, used to run the application.
	 *
	 * @param args the command line arguments
	 * @throws UnknownHostException if the local host name could not be resolved
	 *                              into an address
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(FlorenceWsApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		app.run(args).getEnvironment();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application '{}' is running! \n\t", env.getProperty("spring.application.name"));
		log.info("Port '{}'  \n\t", env.getProperty("server.port"));
	}
}