package br.ufpr.dac.bantads.auth.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpr.dac.bantads.auth.model.Login;
import br.ufpr.dac.bantads.auth.model.Usuario;
import br.ufpr.dac.bantads.auth.model.UsuarioDTO;
import br.ufpr.dac.bantads.auth.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class AuthREST {
	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	ResponseEntity<UsuarioDTO> login(@RequestBody Login login) {
		Usuario usu;
		if (login.getLogin().equals("admin")) {
			usu = repo.findByLoginAndSenha(login.getLogin(), login.getSenha());
			return ResponseEntity.ok().body(mapper.map(usu, UsuarioDTO.class));
		}

		usu = repo.findByLogin(login.getLogin());

		if (usu != null) {
			if (passwordEncoder.matches(login.getSenha(), usu.getSenha())) {
				UsuarioDTO usuDTO = mapper.map(usu, UsuarioDTO.class);
				return ResponseEntity.ok().body(usuDTO);
			}
		}

		return ResponseEntity.status(401).build();
	}
}