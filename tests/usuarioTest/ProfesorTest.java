package usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usuario.Profesor;

class ProfesorTest {

    private Profesor profesor;

    @BeforeEach
    void setUp() {
        
        profesor = new Profesor("123", "Carlos Perez", "carlos.perez@example.com", "password123", "Profesor");
    }

    @Test
    void testVerPerfil() {
        
        String resultado = profesor.verPerfil();

         
        String esperado = "ID: 123\nNombre: Carlos Perez\nEmail: carlos.perez@example.com\nTipo: Profesor";

        
        assertEquals(esperado, resultado);
    }
}
