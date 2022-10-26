package br.com.vgmsltda.api.services.impl;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.domain.dto.UserDTO;
import br.com.vgmsltda.api.repository.UserRepository;
import br.com.vgmsltda.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.util.Mapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Victor";
    public static final String EMAIL = "victor_1234@yahoo.com.br";
    public static final String PASSWORD = "1234";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private Users user;
    private UserDTO userDTO;
    private Optional<Users> optionalUsers;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUsers);
//        Mockito.when();

        Users response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Users.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());

    }
    @Test
    void whenFindByIdReturnThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).
                thenThrow(new ObjectNotFoundException("Objeto nao encontrado de Id : "+ID));

        try {
            service.findById(ID);

        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals("Objeto nao encontrado de Id : "+ID,ex.getMessage());
        }
    }
    @Test
    void findAll() {
    }

    @Test
    void create() {
//        when(repository.save((new Users(ID,NAME,PASSWORD,EMAIL))));

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new Users(ID, NAME,
                EMAIL, PASSWORD);

        userDTO = new UserDTO(ID, NAME,
                EMAIL, PASSWORD);

        optionalUsers = Optional.of(new Users(ID, NAME,
                EMAIL, PASSWORD));
    }
}