package com.davimc.cursomc.services;

import com.davimc.cursomc.domain.Cliente;
import com.davimc.cursomc.repositories.ClienteRepository;
import com.davimc.cursomc.security.UserSS;
import com.davimc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cli = repo.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email: " + email + ", Tipo: " + Cliente.class.getName()));

        return new UserSS(cli.getId(),cli.getEmail(),cli.getSenha(),cli.getPerfis());
    }
}
