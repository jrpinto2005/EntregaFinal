package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorReseña;
import envios.PreguntaOpcionMultiple;
import envios.Reseña;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Actividad;
import usuario.Sistema;

class ConstructorReseñaTest {

    private ConstructorReseña constructorReseña;
    private Sistema sistema;
    private Date fechaInicio;
	private Date fechaFin;
	private Quiz quiz;
	private PreguntaOpcionMultiple pregunta1;
	private PreguntaOpcionMultiple pregunta2;

	
	@BeforeEach
	void setUp() {
	    sistema = Sistema.getInstancia();
	    
	    LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
	            1, "profesor01", "Entender lo básico de Java", 0.0);
	    sistema.addLP(lp1);

	    quiz = new Quiz("Examen de Java", "Evaluar entendimiento de los estudiantes en Java", "Examen1",
	            new Date(), new Date(), 30, 2, 4.0, "Examen", true, "Java Básico", 100);

	    pregunta1 = new PreguntaOpcionMultiple("¿Qué es una variable en Java?", 1);
	    pregunta2 = new PreguntaOpcionMultiple("¿Cómo se implementa una clase en Java?", 2);
	    
	    quiz.addPregunta(pregunta1);
	    quiz.addPregunta(pregunta2);
	     
	    RecursoEducativo actividad1 = new RecursoEducativo("video de variables", "Introducir variables", "Tarea1", 
	            fechaInicio, fechaFin, 120, 3, 4.5, "Recurso Educativo", true, "Java Básico");
	    
	    lp1.agregarActividad(actividad1); 
	    sistema.addActividad(actividad1); 

	    constructorReseña = new ConstructorReseña();
	}

	    
	    @Test
	    void testHacerReseña() throws ActivdadNoEcontradaException {
	        String idReseña = "Reseña1";
	        String comentario = "Excelente contenido para aprender Java.";
	        int rating = 5;
	        Date fecha = new Date();
	        String idActividad = "Java Básico.Tarea1";   

	        try {
	            constructorReseña.hacerReseña(idReseña, comentario, rating, fecha, idActividad);
	        } catch (ActivdadNoEcontradaException e) {
	            fail("La actividad no fue encontrada: " + e.getMessage());
	        }

	        Actividad actividad = sistema.encontrarActividad(idActividad);
	        assertNotNull(actividad, "La actividad debería existir.");

	        Reseña reseñaEncontrada = null;
	        for (Reseña reseña : actividad.getReseñas()) {
	            if (reseña.getIdReseña().equals(idReseña)) {
	                reseñaEncontrada = reseña;
	            }
	        }

	        assertNotNull(reseñaEncontrada, "La reseña debería haberse agregado a la actividad.");
	        assertEquals(idReseña, reseñaEncontrada.getIdReseña(), "El ID de la reseña debería coincidir.");
	        assertEquals(comentario, reseñaEncontrada.getComentario(), "El comentario debería coincidir.");
	        assertEquals(rating, reseñaEncontrada.getRating(), "El rating debería coincidir.");
	        assertEquals(fecha, reseñaEncontrada.getFecha(), "La fecha de la reseña debería coincidir.");
	        assertEquals(idActividad, sistema.encontrarActividad(idActividad).getId(), "El ID de la actividad debería coincidir.");
	    }

	    
	}



