package br.com.vgmsltda.api.services;

import br.com.vgmsltda.api.domain.Users;

import java.util.List;


public interface UserService {
    Users findById(Integer id);

    List<Users> findAll();
}
