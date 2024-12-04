package enviosTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import envios.EnvioEncuesta;
import envios.RespuestaEncuesta;
import envios.PreguntaEncuesta;
import learningPaths.Actividad;
import learningPaths.LearningPath;
import usuario.Sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class EnvioEncuestaTest {
	Sistema sistema = Sistema.getInstancia();

    private Actividad actividad;
    private EnvioEncuesta envioEncuesta;
    private List<RespuestaEncuesta> respuestas;

    @BeforeEach
    void setUp() {
    	
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);
        actividad = new Actividad("Encuesta1", "Descripción de la encuesta", "Encuesta", 
                new Date(), new Date(), 20, 1, 3.5, "Encuesta", true, "Java Básico");
        
        respuestas = new ArrayList<>();
        
        PreguntaEncuesta pregunta1 = new PreguntaEncuesta("Pregunta 1",1);
        PreguntaEncuesta pregunta2 = new PreguntaEncuesta("Pregunta 2",2);

        respuestas.add(new RespuestaEncuesta(4, pregunta1));
        respuestas.add(new RespuestaEncuesta(5, pregunta2));

        envioEncuesta = new EnvioEncuesta(actividad, "estudiante01", "Java Básico", true, 
                80.0, 100, 4.5, respuestas);
    }

    @Test
    void testGetIdEstudiante() {
        assertEquals("estudiante01", envioEncuesta.getIdEstudiante(), "El ID del estudiante debería ser 'estudiante01'.");
    }

    @Test
    void testGetTituloLp() {
        assertEquals("Java Básico", envioEncuesta.getTituloLp(), "El título del Learning Path debería ser 'Java Básico'.");
    }

    @Test
    void testIsCompletado() {
        assertTrue(envioEncuesta.isCompletado(), "El estado de completado debería ser verdadero.");
    }

    @Test
    void testGetActividad() {
        assertEquals(actividad, envioEncuesta.getActividad(), "La actividad debería coincidir con la inicializada en setUp.");
    }

    @Test
    void testGetPuntaje() {
        assertEquals(80.0, envioEncuesta.getPuntaje(), "El puntaje debería ser 80.0.");
    }

    @Test
    void testSetPuntaje() {
        envioEncuesta.setPuntaje(90.0);
        assertEquals(90.0, envioEncuesta.getPuntaje(), "El puntaje debería actualizarse a 90.0.");
    }

    @Test
    void testGetPuntajeMaximo() {
        assertEquals(100, envioEncuesta.getPuntajeMaximo(), "El puntaje máximo debería ser 100.");
    }

    @Test
    void testSetPuntajeMaximo() {
        envioEncuesta.setPuntajeMaximo(110);
        assertEquals(110, envioEncuesta.getPuntajeMaximo(), "El puntaje máximo debería actualizarse a 110.");
    }

    @Test
    void testGetRating() {
        assertEquals(4.5, envioEncuesta.getRating(), "El rating debería ser 4.5.");
    }

    @Test
    void testSetRating() {
        envioEncuesta.setRating(4.8);
        assertEquals(4.8, envioEncuesta.getRating(), "El rating debería actualizarse a 4.8.");
    }

    @Test
    void testGetRespuestas() {
        assertEquals(respuestas, envioEncuesta.getRespuestas(), "La lista de respuestas debería coincidir con la inicializada en setUp.");
    }
}
