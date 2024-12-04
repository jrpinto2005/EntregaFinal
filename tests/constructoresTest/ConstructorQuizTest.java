package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorQuiz;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Quiz;
import learningPaths.LearningPath;
import usuario.Sistema;

class ConstructorQuizTest {

    private ConstructorQuiz constructorQuiz;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
         
        LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
                1, "profesor01", "Entender lo básico de Java", 0.0);
        sistema.addLP(lp1);

        constructorQuiz = new ConstructorQuiz();
    }

    @Test
    void testCrearQuiz() throws ActivdadNoEcontradaException {
        
        String descripcion = "Quiz de Java Básico";
        String objetivo = "Evaluar conocimientos básicos de Java";
        String id = "Quiz1";
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        int duracion = 30;
        int dificultad = 2;
        double rating = 4.5;
        String tipoActividad = "Quiz";
        boolean obligatoria = true;
        String learningPath = "Java Básico";
        int puntajeMaximo = 100;

         
        Quiz quiz = constructorQuiz.crearQuiz(descripcion, objetivo, id, fechaInicio, fechaFin,
                duracion, dificultad, rating, tipoActividad, obligatoria, learningPath, puntajeMaximo);

         
        assertNotNull(quiz, "El quiz debería haberse creado y no ser nulo.");
        assertEquals(descripcion, quiz.getDescripcion());
        assertEquals(objetivo, quiz.getObjetivo());
        assertEquals(learningPath + "." + id, quiz.getId());
        assertEquals(duracion, quiz.getDuracion());
        assertEquals(dificultad, quiz.getDificultad());
        assertEquals(rating, quiz.getRating(), 0.01);
        assertEquals(tipoActividad, quiz.getTipoActividad());
        assertEquals(obligatoria, quiz.isObligatoria());
        assertEquals(learningPath, quiz.getLearningPath().getTitulo());
        assertEquals(puntajeMaximo, quiz.getPuntajeMaximo());
        assertEquals(quiz, sistema.encontrarActividad(learningPath + "." + id), "El quiz debería estar registrado en el sistema.");
    }

    @Test
    void testEditarQuiz() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
        
        String id = "Quiz2";
        String learningPath = "Java Básico";
        Quiz quiz = constructorQuiz.crearQuiz("Quiz de Prueba", "Prueba de edición", id,
                new Date(), new Date(), 20, 1, 3.0, "Quiz", true, "Java Básico", 50);

         
        String nuevaDescripcion = "Nueva Descripción";
        constructorQuiz.editarQuiz(learningPath + "." + id, "descripcion", nuevaDescripcion);
        assertEquals(nuevaDescripcion, quiz.getDescripcion(), "La descripción debería haber sido actualizada.");

        
        Integer nuevaDificultad = 3;
        constructorQuiz.editarQuiz(learningPath + "." + id, "dificultad", nuevaDificultad);
        assertEquals(nuevaDificultad, quiz.getDificultad(), "La dificultad debería haber sido actualizada.");

         
        Integer nuevoPuntajeMaximo = 75;
        constructorQuiz.editarQuiz(learningPath + "." + id, "puntajeMaximo", nuevoPuntajeMaximo);
        assertEquals(nuevoPuntajeMaximo, quiz.getPuntajeMaximo(), "El puntaje máximo debería haber sido actualizado.");
    }
}
