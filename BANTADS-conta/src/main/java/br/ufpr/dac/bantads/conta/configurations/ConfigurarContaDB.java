package br.ufpr.dac.bantads.conta.configurations;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import br.ufpr.dac.bantads.conta.model.Conta;

@Configuration
@EnableJpaRepositories(
  basePackages = "br.ufpr.dac.bantads.conta.repository",
  entityManagerFactoryRef = "contaEntityManagerFactory",
  transactionManagerRef= "contaTransactionManager"
)
public class ConfigurarContaDB {
	@Primary
	@Bean
    @ConfigurationProperties("spring.datasource.conta")
    public DataSourceProperties contaDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource.conta.configuration")
    public DataSource contaDataSource() {
        return contaDataSourceProperties().initializeDataSourceBuilder()
        		.type(HikariDataSource.class).build();
    }
	
	@Primary
	@Bean(name="contaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean contaEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(contaDataSource())
				.packages(Conta.class).build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager contaTransactionManager(
			final @Qualifier("contaEntityManagerFactory") LocalContainerEntityManagerFactoryBean
			contaEntityManagerFactory) {
		return new JpaTransactionManager(contaEntityManagerFactory.getObject());
	}
}
