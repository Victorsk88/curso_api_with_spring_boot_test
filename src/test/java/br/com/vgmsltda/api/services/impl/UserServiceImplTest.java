package br.com.vgmsltda.api.services.impl;

import br.com.vgmsltda.api.domain.Users;
import br.com.vgmsltda.api.domain.dto.UserDTO;
import br.com.vgmsltda.api.repository.UserRepository;
import br.com.vgmsltda.api.services.exceptions.DataIntegrityViolationException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String OBJECTNOTFOUND = "Objeto nao encontrado de Id : " + ID;
    public static final String NAME = "Victor";
    public static final String EMAIL = "victor_1234@yahoo.com.br";
    public static final String PASSWORD = "1234";
    public static final int INDEX = 0;
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
                thenThrow(new ObjectNotFoundException(OBJECTNOTFOUND));

        try {
            service.findById(ID);

        }catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(OBJECTNOTFOUND,ex.getMessage());
        }
    }
    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<Users> response = service.findAll();
        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Users.class,response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
        assertEquals(NAME, response.get(INDEX).getName());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        Users response =service.create(userDTO);

        assertNotNull(response);
        assertEquals(Users.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(NAME,response.getName());
        assertEquals(PASSWORD, response.getPassword());

    }
    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUsers);

        try {
            optionalUsers.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado",ex.getMessage());
        }

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