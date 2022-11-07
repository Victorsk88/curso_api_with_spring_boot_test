package br.com.vgmsltda.api.resources.exceptions;
import br.com.vgmsltda.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.jupiter.api.Assertions.*;

class ResourceExceptionHandlerTest {


    public static final String OBJECTNOTFOUND = "Objeto nao encontrado de Id : ";

    @InjectMocks
    private ResourceExceptionHandler handler;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = handler.objectNotFound(
                new ObjectNotFoundException(OBJECTNOTFOUND),
                new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND ,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECTNOTFOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegrityViolationException() {
    }
}