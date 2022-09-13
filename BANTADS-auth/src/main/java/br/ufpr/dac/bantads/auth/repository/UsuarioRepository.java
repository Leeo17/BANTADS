package br.ufpr.dac.bantads.auth.repository;

import br.ufpr.dac.bantads.auth.model.Usuario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	public Usuario findByLogin(String login);

	@Query("from Usuario where login = :login and senha = :senha")
	public Usuario findByLoginAndSenha(@Param("login") String login,
									   @Param("senha") String senha);
}