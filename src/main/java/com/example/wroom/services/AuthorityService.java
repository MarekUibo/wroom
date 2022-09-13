package com.example.wroom.services;

import com.example.wroom.exceptions.AuthorityNotFoundException;
import com.example.wroom.models.Authority;

import java.util.List;

/**
 * Service to handle authority related operations
 *
 * @author Vinod John
 */
public interface AuthorityService {

    /**
     * To create a new authority
     *
     * @param authority Authority
     */
    void createAuthority(Authority authority);

    /**
     * To find authority by name
     *
     * @param name Authority name
     * @return optional of Authority
     */
   Authority findAuthorityByName(String name) throws AuthorityNotFoundException;


    /**
     * To find all authorities
     *
     * @return list of authorities
     */
    List<Authority> findAllAuthorities();
}
