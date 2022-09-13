package br.ufpr.dac.bantads.conta.configurations;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import br.ufpr.dac.bantads.conta.read.model.ContaRead;

@Configuration
@EnableJpaRepositories(
  basePackages = "br.ufpr.dac.bantads.conta.read.repository",
  entityManagerFactoryRef = "contaReadEntityManagerFactory",
  transactionManagerRef = "contaReadTransactionManager"
)
public class ConfigurarContaReadDB {
	@Bean(name="contaReadProperties")
    @ConfigurationProperties("spring.datasource.conta-read")
    public DataSourceProperties contaReaddataSourceProperties() {
        return new DataSourceProperties();
    }

	@Bean(name="contaReadDataSource")
	@ConfigurationProperties("spring.datasource.conta-read.configuration")
    public DataSource contaReadDataSource() {
        return contaReaddataSourceProperties().initializeDataSourceBuilder()
        		.type(HikariDataSource.class).build();
    }

	@Bean(name="contaReadEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean contaReadEntityManagerFactory(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(contaReadDataSource())
				.packages(ContaRead.class).build();
	}

	@Bean(name="contaReadTransactionManager")
	public PlatformTransactionManager contaReadTransactionManager(
			final @Qualifier("contaReadEntityManagerFactory") LocalContainerEntityManagerFactoryBean
			contaReadEntityManagerFactory) {
		return new JpaTransactionManager(contaReadEntityManagerFactory.getObject());
	}
}
