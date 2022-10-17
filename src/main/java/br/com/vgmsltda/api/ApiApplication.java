package br.com.vgmsltda.api;

import br.com.vgmsltda.api.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {	DataSourceAutoConfiguration.class })
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		User user = new User(1,"Victor Gabriel","victor_gabrielms@yahoo.com.br","123456");



	}

}
