package br.com.vgmsltda.api.services;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.domain.dto.UserDTO;

import java.util.List;


public interface UserService {
    Users findById(Integer id);
    List<Users> findAll();
    Users create(UserDTO userDTO);
    Users update(UserDTO userDTO);
    void delete(Integer id);
}
