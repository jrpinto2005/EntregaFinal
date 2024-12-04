package enviosTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import envios.Reseña;

class ReseñaTest {

    private Reseña reseña;

    @BeforeEach
    void setUp() {
         
        reseña = new Reseña("1", "Excelente actividad", 5, new Date(), "Actividad01");
    }

    @Test
    void testDisplay() {
         
        String resultado = reseña.display();

        
        String esperado = "Reseña: Excelente actividad, Rating: 5, Fecha: " + reseña.getFecha().toString();

        assertEquals(esperado, resultado);
    }
}
