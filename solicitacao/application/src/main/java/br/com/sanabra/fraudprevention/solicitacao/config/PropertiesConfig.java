package br.com.sanabra.fraudprevention.solicitacao.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@EnableAutoConfiguration
@PropertySource("classpath:/ddd.properties")
public class PropertiesConfig {
}
