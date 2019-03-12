package com.weedti.janehempshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.weedti.janehempshop.model.Cliente;
import com.weedti.janehempshop.repository.ClienteRepository;

@SpringBootApplication
public class JaneHempShopApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(JaneHempShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		clienteRepository.save(new Cliente("Gabriel", "gabriellgomesbh@gmail.com", "(31)9 8454-8992"));
		clienteRepository.save(new Cliente("Rafael", "souza2701@gmail.com", "(31)9 8454-9508"));

	}

}
