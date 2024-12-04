package learningPathsTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import envios.PreguntaOpcionMultiple;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import usuario.Sistema;

class QuizTest {

    private Quiz quiz;
    private PreguntaOpcionMultiple pregunta1;
    private PreguntaOpcionMultiple pregunta2;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);

        quiz = new Quiz("Examen de Java", "Evaluar entendimiento de los estudiantes en Java", "Examen1",
                new Date(), new Date(), 30, 2, 4.0, "Examen", true, "Java Básico", 100);

        pregunta1 = new PreguntaOpcionMultiple("¿Que es una variable en Java?", 1);
        pregunta2 = new PreguntaOpcionMultiple("¿Como se implementa una clase en Java?", 2);
        
        quiz.addPregunta(pregunta1);
        quiz.addPregunta(pregunta2);
    }

    @Test
    void testGetPreguntas() {
         
        assertEquals(2, quiz.getPreguntas().size(), "Debería haber 2 preguntas en la encuesta.");
        assertTrue(quiz.getPreguntas().contains(pregunta1), "El quiz debería contener pregunta1.");
        assertTrue(quiz.getPreguntas().contains(pregunta2), "El quiz debería contener pregunta2.");
    }

    @Test
    void testRemovePregunta() {
    
        quiz.removePregunta(pregunta1);
        assertEquals(1, quiz.getPreguntas().size(), "Debería quedar solo 1 pregunta después de eliminar una.");
        assertFalse(quiz.getPreguntas().contains(pregunta1), "El quiz no debería contener pregunta1.");
        assertTrue(quiz.getPreguntas().contains(pregunta2), "El quiz debería seguir conteniendo pregunta2.");
    }
}
