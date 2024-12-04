
package learningPathsTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import envios.PreguntaEncuesta;
import learningPaths.Encuesta;
import learningPaths.LearningPath;
import usuario.Sistema;

class EncuestaTest {

    private Encuesta encuesta;
    private PreguntaEncuesta pregunta1;
    private PreguntaEncuesta pregunta2;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);

        encuesta = new Encuesta("Encuesta de Java", "Evaluar entendimiento de los estudiantes en Java", "Encuesta1",
                new Date(), new Date(), 30, 2, 4.0, "Encuesta", true, "Java Básico", 100);

        pregunta1 = new PreguntaEncuesta("¿De 1-5: Qué tanto entiendes que es una variable en Java?", 1);
        pregunta2 = new PreguntaEncuesta("¿De 1-5: Qué tanto entiendes que es una clase en Java?", 2);
        
        encuesta.addPregunta(pregunta1);
        encuesta.addPregunta(pregunta2);
    }

    @Test
    void testGetPreguntas() {
         
        assertEquals(2, encuesta.getPreguntas().size(), "Debería haber 2 preguntas en la encuesta.");
        assertTrue(encuesta.getPreguntas().contains(pregunta1), "La encuesta debería contener pregunta1.");
        assertTrue(encuesta.getPreguntas().contains(pregunta2), "La encuesta debería contener pregunta2.");
    }

    @Test
    void testRemovePregunta() {
    
        encuesta.removePregunta(pregunta1);
        assertEquals(1, encuesta.getPreguntas().size(), "Debería quedar solo 1 pregunta después de eliminar una.");
        assertFalse(encuesta.getPreguntas().contains(pregunta1), "La encuesta no debería contener pregunta1.");
        assertTrue(encuesta.getPreguntas().contains(pregunta2), "La encuesta debería seguir conteniendo pregunta2.");
    }
}
