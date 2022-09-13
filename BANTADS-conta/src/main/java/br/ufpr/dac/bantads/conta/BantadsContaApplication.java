package br.ufpr.dac.bantads.conta;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufpr.dac.bantads.conta.BantadsContaApplication;

@SpringBootApplication
public class BantadsContaApplication {
	public static void main(String[] args) {
		SpringApplication.run(BantadsContaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
