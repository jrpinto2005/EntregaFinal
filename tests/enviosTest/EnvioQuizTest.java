package enviosTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import envios.CalificadorRespuestaMultiple;
import envios.EnvioQuiz;
import envios.Opcion;
import envios.PreguntaOpcionMultiple;
import envios.RespuestaMultiple;
import learningPaths.Actividad;
import learningPaths.LearningPath;
import usuario.Sistema;

class EnvioQuizTest {

    private Actividad actividad;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {

     
        LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
                1, "profesor01", "Entender lo básico de Java", 0.0);
        sistema.addLP(lp1);

       
        actividad = new Actividad("Quiz de prueba", "Evaluación de conocimientos básicos", 
                "Quiz1", new Date(), new Date(), 60, 2, 4.0, "Quiz", true, "Java Básico");

        CalificadorRespuestaMultiple calificador = new CalificadorRespuestaMultiple();
    }

    @Test
    void testCalificarQuizConCuatroOpciones() {

       
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple("¿Cuál es la respuesta correcta?", 1);
        pregunta.agregarOpcion(new Opcion("Opción A", false, 1));
        pregunta.agregarOpcion(new Opcion("Opción B", true, 2));   
        pregunta.agregarOpcion(new Opcion("Opción C", false, 3));
        pregunta.agregarOpcion(new Opcion("Opción D", false, 4));

    
        RespuestaMultiple respuesta = new RespuestaMultiple(1, 2, pregunta);
        List<RespuestaMultiple> respuestas = new ArrayList<>();
        respuestas.add(respuesta);

     
        EnvioQuiz envioQuiz = new EnvioQuiz(actividad, "estudiante01", "Java Básico", false, 0.0, 1, 0.0, respuestas);
        envioQuiz.calificarQuiz();

         
        assertEquals(1, envioQuiz.getPuntaje());
        assertEquals(1.0, envioQuiz.getNotaPorcentaje());
    }

    @Test
    void testCalificarQuizVerdaderoFalso() {

         
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple("¿Es Java un lenguaje de programación?", 2);
        pregunta.agregarOpcion(new Opcion("Verdadero", true, 1));   
        pregunta.agregarOpcion(new Opcion("Falso", false, 2));

 
        RespuestaMultiple respuesta = new RespuestaMultiple(2, 1, pregunta);
        List<RespuestaMultiple> respuestas = new ArrayList<>();
        respuestas.add(respuesta);

         
        EnvioQuiz envioQuiz = new EnvioQuiz(actividad, "estudiante02", "Java Básico", false, 0.0, 1, 0.0, respuestas);
        envioQuiz.calificarQuiz();

         
        assertEquals(1, envioQuiz.getPuntaje());
        assertEquals(1.0, envioQuiz.getNotaPorcentaje());
    }
}
