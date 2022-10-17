package br.com.vgmsltda.api.resources;

import br.com.vgmsltda.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @GetMapping(value ="/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(
                new User(1,"Victor Gabriel ","victor_gabrielms@yahoo.com.br","123456"));
    }
}
