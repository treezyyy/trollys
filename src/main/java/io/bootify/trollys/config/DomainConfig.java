package io.bootify.trollys.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = "io.bootify.trollys.entity")
@EnableJpaRepositories("io.bootify.trollys.repos")
@EnableTransactionManagement
public class DomainConfig {
}
