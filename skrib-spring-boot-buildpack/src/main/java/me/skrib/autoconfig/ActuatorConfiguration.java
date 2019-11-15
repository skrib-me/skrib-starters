package me.skrib.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnBean(WebSecurityConfigurerAdapter.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ActuatorConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${management.endpoints.web.base-path:/actuators}")
    private String actuatorsBasePath;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(actuatorsBasePath + "/**").permitAll();
    }

}
