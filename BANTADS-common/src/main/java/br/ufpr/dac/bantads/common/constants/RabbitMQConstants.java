package br.ufpr.dac.bantads.common.constants;

public class RabbitMQConstants {
    public static final String FILA_CLIENTE_CRIADO = "CLIENTE_CRIADO";
    public static final String FILA_CRIAR_CONTA = "CRIAR_CONTA";
    public static final String FILA_CONTA_CRIADA = "CONTA_CRIADA";
    public static final String FILA_CONTA_READ_CRIADA = "CONTA_READ_CRIADA";
    public static final String FILA_DEFINIR_GERENTE = "DEFINIR_GERENTE";
    public static final String FILA_GERENTE_DEFINIDO = "GERENTE_DEFINIDO";

    public static final String FILA_GERENTE_CRIADO = "GERENTE_CRIADO";
    public static final String FILA_GERENTE_EDITADO = "GERENTE_EDITADO";
    public static final String FILA_GERENTE_REMOVIDO = "GERENTE_REMOVIDO";

    public static final String FILA_ATRIBUIR_GERENTE_CONTA = "ATRIBUIR_GERENTE_CONTA";
    public static final String FILA_ATRIBUIR_GERENTE_CONTA_READ = "ATRIBUIR_GERENTE_CONTA_READ";

    public static final String FILA_CRIAR_USUARIO_GERENTE = "CRIAR_USUARIO_GERENTE";
    public static final String FILA_ATUALIZAR_USUARIO_GERENTE = "ATUALIZAR_USUARIO_GERENTE";
    public static final String FILA_REMOVER_USUARIO_GERENTE = "REMOVER_USUARIO_GERENTE";

    public static final String FILA_CONTA_CLIENTE_APROVADA = "CONTA_CLIENTE_APROVADA";
    public static final String FILA_CONTA_READ_CLIENTE_APROVADA = "CONTA_READ_CLIENTE_APROVADA";
    public static final String FILA_CONTA_CLIENTE_REPROVADA = "CONTA_CLIENTE_REPROVADA";

    public static final String FILA_CRIAR_USUARIO_CLIENTE = "CRIAR_USUARIO_CLIENTE";
    public static final String FILA_MOVIMENTACAO_CONTA_REALIZADA = "MOVIMENTACAO_CONTA_REALIZADA";

    public static final String FILA_SALDO_CONTA_ATUALIZADO = "SALDO_CONTA_ATUALIZADO";
    public static final String FILA_ATUALIZAR_SALDO_GERENTE_CONTA = "ATUALIZAR_SALDO_GERENTE_CONTA";

    public static final String[] TODAS_FILAS = {FILA_CLIENTE_CRIADO, FILA_CRIAR_CONTA,
            FILA_CONTA_CRIADA, FILA_CONTA_READ_CRIADA, FILA_DEFINIR_GERENTE, FILA_GERENTE_DEFINIDO,
            FILA_ATRIBUIR_GERENTE_CONTA, FILA_ATRIBUIR_GERENTE_CONTA_READ, FILA_GERENTE_CRIADO,
            FILA_GERENTE_EDITADO, FILA_GERENTE_REMOVIDO, FILA_CRIAR_USUARIO_GERENTE,
            FILA_ATUALIZAR_USUARIO_GERENTE, FILA_REMOVER_USUARIO_GERENTE, FILA_CONTA_CLIENTE_APROVADA,
            FILA_CONTA_CLIENTE_REPROVADA, FILA_CONTA_READ_CLIENTE_APROVADA, FILA_CRIAR_USUARIO_CLIENTE,
            FILA_MOVIMENTACAO_CONTA_REALIZADA, FILA_SALDO_CONTA_ATUALIZADO, FILA_ATUALIZAR_SALDO_GERENTE_CONTA};
}