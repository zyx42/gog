package com.eugenarium.statistics.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@EnableResourceServer
@Configuration
public class ResouceServerConfig extends ResourceServerConfigurerAdapter {

    private final ResourceServerProperties rsp;

    public ResouceServerConfig(ResourceServerProperties rsp) {
        this.rsp = rsp;
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        return new CustomUserInfoTokenServices(rsp.getUserInfoUri(), rsp.getClientId());
    }
}
