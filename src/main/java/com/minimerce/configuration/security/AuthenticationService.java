package com.minimerce.configuration.security;

import com.minimerce.domain.client.Client;
import com.minimerce.domain.client.ClientRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by gemini on 11/05/2017.
 */
@Service
public class AuthenticationService {
    private final ClientRepository clientRepository;

    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public AuthenticationService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void authentication(HttpServletRequest request) {
        Long clientId = NumberUtils.toLong(request.getHeader("mini-client-id"), -1L);
        String apiKey = StringUtils.defaultString(request.getHeader("mini-api-key"), "");
        if(clientId == -1L) throw new BadCredentialsException("Bad Request");
        if(StringUtils.isEmpty(apiKey)) throw new BadCredentialsException("Bad Request");

        Client client = clientRepository.findOne(clientId);
        if(null == client) throw new BadCredentialsException("Invalid Client");
        if(false == bCryptPasswordEncoder.matches(apiKey, client.getApiKey())) throw new BadCredentialsException("Invalid Client");

        Authentication authentication = new UsernamePasswordAuthenticationToken(client, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
