package com.example.wroom.services.implementations;

import com.example.wroom.exceptions.AuthorityNotFoundException;
import com.example.wroom.models.Authority;

import com.example.wroom.repository.AuthorityRepository;
import com.example.wroom.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of Authority Service
 *
 * @author Jonathan Rigottier
 */

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void createAuthority(Authority authority) {
        authority.setActive(true);
        authorityRepository.save(authority);
    }

    @Override
    public Authority findAuthorityByName(String name) throws AuthorityNotFoundException {
        Optional<Authority> optionalAuthority = authorityRepository.findByName(name);

        if(optionalAuthority.isEmpty()) {
            throw new AuthorityNotFoundException(name);
        }

        return optionalAuthority.get();
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findAuthorityById(UUID id) throws AuthorityNotFoundException {
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);

        if(optionalAuthority.isEmpty()) {
            throw new AuthorityNotFoundException(id);
        }

        return optionalAuthority.get();
    }
}
