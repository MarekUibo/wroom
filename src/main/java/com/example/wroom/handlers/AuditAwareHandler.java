package com.example.wroom.handlers;


import com.example.wroom.utils.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


/**
 * @author:Marek Uibo
 */
public class AuditAwareHandler implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Constants.Audit.DEFAULT_AUDITOR);
    }

}
