package co.edu.udea.SalasInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SalasInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalasInfoApplication.class, args);
	}

}
