package com.competitions.init;

import com.competitions.config.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInit extends AbstractSecurityWebApplicationInitializer {

    protected SecurityInit() {
        super(SecurityConfig.class);
    }
}
