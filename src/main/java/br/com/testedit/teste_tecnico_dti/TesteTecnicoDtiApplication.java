package br.com.testedit.teste_tecnico_dti;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class TesteTecnicoDtiApplication {

	public static void main(String[] args) {
		log.info("Iniciando Spring Boot");
		SpringApplication.run(TesteTecnicoDtiApplication.class, args);
	}

}
