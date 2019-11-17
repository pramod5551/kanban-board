package com.server.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration extends GlobalAuthenticationConfigurerAdapter {

	PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {

		// @formatter:off
		auth.inMemoryAuthentication()
		.withUser("admin").password(passwordEncoder.encode("admin"))
				.roles("USER", "ADMIN").authorities("CAN_READ", "CAN_WRITE", "CAN_DELETE")
				.and()
				.withUser("readuser").password(passwordEncoder.encode("readpass"))
				.roles("USER").authorities("CAN_READ")
				.and()
				.withUser("writeuser").password(passwordEncoder.encode("writepass"))
				.roles("USER").authorities("CAN_READ", "CAN_WRITE");;
	}
}