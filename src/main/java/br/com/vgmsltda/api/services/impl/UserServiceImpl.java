package br.com.vgmsltda.api.services.impl;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.domain.dto.UserDTO;
import br.com.vgmsltda.api.services.exceptions.DataIntegrityViolationException;
import br.com.vgmsltda.api.services.exceptions.ObjectNotFoundException;
import br.com.vgmsltda.api.repository.UserRepository;
import br.com.vgmsltda.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado de Id : " + id));
    }

    public List<Users> findAll(){
        return repository.findAll();
    }

    @Override
    public Users create(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, Users.class));
    }
    @Override
    public Users update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO,Users.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO obj){
        Optional<Users> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }

}