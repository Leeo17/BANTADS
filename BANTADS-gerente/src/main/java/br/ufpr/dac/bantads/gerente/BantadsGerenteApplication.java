package br.ufpr.dac.bantads.gerente;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.dac.bantads.gerente.BantadsGerenteApplication;

@SpringBootApplication
public class BantadsGerenteApplication {
	public static void main(String[] args) {
		SpringApplication.run(BantadsGerenteApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
