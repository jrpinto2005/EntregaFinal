package learningPathsTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import envios.PreguntaAbierta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import usuario.Sistema;

class ExamenTest {

    private Examen examen;
    private PreguntaAbierta pregunta1;
    private PreguntaAbierta pregunta2;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);

        examen = new Examen("Examen de Java", "Evaluar entendimiento de los estudiantes en Java", "Examen1",
                new Date(), new Date(), 30, 2, 4.0, "Examen", true, "Java Básico", 100);

        pregunta1 = new PreguntaAbierta("¿Que es una variable en Java?", 1, 0.5);
        pregunta2 = new PreguntaAbierta("¿Como se implementa una clase en Java?", 2, 0.5);
        
        examen.addPregunta(pregunta1);
        examen.addPregunta(pregunta2);
    }

    @Test
    void testGetPreguntas() {
         
        assertEquals(2, examen.getPreguntas().size(), "Debería haber 2 preguntas en la encuesta.");
        assertTrue(examen.getPreguntas().contains(pregunta1), "El examen debería contener pregunta1.");
        assertTrue(examen.getPreguntas().contains(pregunta2), "El examen debería contener pregunta2.");
    }

    @Test
    void testRemovePregunta() {
    
        examen.removePregunta(pregunta1);
        assertEquals(1, examen.getPreguntas().size(), "Debería quedar solo 1 pregunta después de eliminar una.");
        assertFalse(examen.getPreguntas().contains(pregunta1), "El examen no debería contener pregunta1.");
        assertTrue(examen.getPreguntas().contains(pregunta2), "El examen debería seguir conteniendo pregunta2.");
    }
}
