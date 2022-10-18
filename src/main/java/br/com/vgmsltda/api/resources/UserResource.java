package br.com.vgmsltda.api.resources;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping(value ="/{id}")
    public ResponseEntity<Users> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
