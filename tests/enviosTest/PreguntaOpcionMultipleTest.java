package enviosTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.List;
import envios.Opcion;
import envios.PreguntaOpcionMultiple;
import learningPaths.LearningPath;
import usuario.Sistema;

class PreguntaOpcionMultipleTest {

 
    private Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {

       
        LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
                1, "profesor01", "Entender lo básico de Java", 0.0);
        sistema.addLP(lp1);

         
    }

    @Test
    void testDisplayOpciones() {

         
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple("¿Cuál es la respuesta correcta?", 1);
        
        
        pregunta.agregarOpcion(new Opcion("Opción A", false, 1));
        pregunta.agregarOpcion(new Opcion("Opción B", true, 2));   
        pregunta.agregarOpcion(new Opcion("Opción C", false, 3));
        pregunta.agregarOpcion(new Opcion("Opción D", false, 4));

        
        List<Opcion> opciones = pregunta.getOpciones();

        
        assertEquals(4, opciones.size()); 
         
        assertEquals("Opción A", opciones.get(0).getDescripcion());
        assertEquals(false, opciones.get(0).esCorrecto());
        assertEquals(1, opciones.get(0).getIndice());

        assertEquals("Opción B", opciones.get(1).getDescripcion());
        assertEquals(true, opciones.get(1).esCorrecto());
        assertEquals(2, opciones.get(1).getIndice());

        assertEquals("Opción C", opciones.get(2).getDescripcion());
        assertEquals(false, opciones.get(2).esCorrecto());
        assertEquals(3, opciones.get(2).getIndice());

        assertEquals("Opción D", opciones.get(3).getDescripcion());
        assertEquals(false, opciones.get(3).esCorrecto());
        assertEquals(4, opciones.get(3).getIndice());
    }
}
