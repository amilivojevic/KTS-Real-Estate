package kts.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class represents the main class and runs
 * the Application.
 */
@SpringBootApplication
public class KtsprojectApplication {

	/**
	 * This is the main method that runs the
	 * Spring application.
	 * @param	args Array of arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(KtsprojectApplication.class, args);
	}
}
