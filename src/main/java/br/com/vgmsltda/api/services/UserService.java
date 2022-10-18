package br.com.vgmsltda.api.services;

import br.com.vgmsltda.api.domain.Users;


public interface UserService {
    Users findById(Integer id);
}
