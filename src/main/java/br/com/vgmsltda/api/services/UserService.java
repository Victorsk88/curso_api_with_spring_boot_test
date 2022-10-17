package br.com.vgmsltda.api.services;

import br.com.vgmsltda.api.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {
    User findById(Integer id);
}
