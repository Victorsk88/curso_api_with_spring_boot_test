package br.com.vgmsltda.api.resources;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.domain.dto.UserDTO;
import br.com.vgmsltda.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping(value ="/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
//        List<UserDTO> listDTO =
//                service.findAll().stream().map(x -> mapper.map(x,UserDTO.class)).collect(Collectors.toList());
//        return ResponseEntity.ok().body(listDTO);

        return ResponseEntity.ok().body(service.findAll()
                .stream().map(x -> mapper.map(x,UserDTO.class)).collect(Collectors.toList()));
    }
}
