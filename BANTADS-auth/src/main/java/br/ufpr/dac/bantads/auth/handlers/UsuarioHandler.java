package br.ufpr.dac.bantads.auth.handlers;

import br.ufpr.dac.bantads.auth.model.Usuario;
import br.ufpr.dac.bantads.auth.repository.UsuarioRepository;
import br.ufpr.dac.bantads.auth.services.EnviarEmailService;
import br.ufpr.dac.bantads.auth.services.RabbitMQService;
import br.ufpr.dac.bantads.common.constants.RabbitMQConstants;
import br.ufpr.dac.bantads.common.dto.AlterarUsuarioGerenteDTO;
import br.ufpr.dac.bantads.common.dto.ContaReadDTO;
import br.ufpr.dac.bantads.common.dto.GerenteDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioHandler {
    @Autowired
    private UsuarioRepository repo;
    @Autowired
    private RabbitMQService rabbitmqService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnviarEmailService enviarEmailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RabbitListener(queues = RabbitMQConstants.FILA_CRIAR_USUARIO_GERENTE)
    private void criarUsuarioGerente(GerenteDTO gerente) {
        String senha = gerarNovaSenha();

        Usuario usuario = new Usuario(UUID.randomUUID(), gerente.getNome(), gerente.getEmail(), passwordEncoder.encode(senha), "GERENTE");
        repo.saveAndFlush(usuario);

        enviarEmailService.sendEmail(gerente.getEmail(), "Você foi adicionado como gerente do BANTADS! Aqui está sua senha", senha);
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_ATUALIZAR_USUARIO_GERENTE)
    private void editarUsuarioGerente(AlterarUsuarioGerenteDTO alterarUsuarioGerenteDTO) {
        Usuario usuario = repo.findByLogin(alterarUsuarioGerenteDTO.getLoginAntigo());
        if (usuario != null) {
            usuario.setLogin(alterarUsuarioGerenteDTO.getLoginNovo());
            usuario.setNome(alterarUsuarioGerenteDTO.getNomeNovo());
            repo.saveAndFlush(usuario);
        }
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_REMOVER_USUARIO_GERENTE)
    private void removerUsuarioGerente(String loginUsuario) {
        Usuario usuario = repo.findByLogin(loginUsuario);
        if (usuario != null) {
            repo.delete(usuario);
        }
    }

    @RabbitListener(queues = RabbitMQConstants.FILA_CRIAR_USUARIO_CLIENTE)
    private void criarUsuarioCliente(ContaReadDTO contaReadDTO) {
        String senha = gerarNovaSenha();
        Usuario usuario = new Usuario(UUID.randomUUID(), contaReadDTO.getNomeCliente(), contaReadDTO.getEmailCliente(),
                passwordEncoder.encode(senha), "CLIENTE");
        repo.saveAndFlush(usuario);

        enviarEmailService.sendEmail(contaReadDTO.getEmailCliente(), "Sua conta no BANTADS foi aprovada! Aqui está sua senha ", senha);
    }

    private String gerarNovaSenha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
        return RandomStringUtils.random(15, characters);
    }
}
