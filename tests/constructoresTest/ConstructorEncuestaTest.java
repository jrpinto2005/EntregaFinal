package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorEncuesta;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Encuesta;
import learningPaths.LearningPath;
import usuario.Sistema;

class ConstructorEncuestaTest {

    private ConstructorEncuesta constructorEncuesta;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
 				1, "profesor01", "Entender lo básico de Java", 0.0);
    	sistema.addLP(lp1);
    	
        constructorEncuesta = new ConstructorEncuesta();

    }

    @Test
    void testCrearEncuesta() throws ActivdadNoEcontradaException {
        
 
        String descripcion = "Encuesta de Java Básico";
        String objetivo = "Evaluar conocimientos básicos de Java";
        String id = "Encuesta1";
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        int duracion = 30;
        int dificultad = 2;
        double rating = 4.5;
        String tipoActividad = "Encuesta";
        boolean obligatoria = true;
        String learningPath = "Java Básico";
        int puntajeMaximo = 100;

         
        Encuesta encuesta = constructorEncuesta.crearEncuesta(descripcion, objetivo, id, fechaInicio, fechaFin,
                duracion, dificultad, rating, tipoActividad, obligatoria, learningPath, puntajeMaximo);
        
  
        assertNotNull(encuesta, "La encuesta debería haberse creado y no ser nula.");
        assertEquals(descripcion, encuesta.getDescripcion());
        assertEquals(objetivo, encuesta.getObjetivo());
        assertEquals(learningPath + "." + id, encuesta.getId());
        assertEquals(duracion, encuesta.getDuracion());
        assertEquals(dificultad, encuesta.getDificultad());
        assertEquals(rating, encuesta.getRating(), 0.01);
        assertEquals(tipoActividad, encuesta.getTipoActividad());
        assertEquals(obligatoria, encuesta.isObligatoria());
        assertEquals(learningPath, encuesta.getLearningPath().getTitulo());
        assertEquals(puntajeMaximo, encuesta.getPuntajeMaximo());
        assertEquals(encuesta, sistema.encontrarActividad(learningPath + "." + id), "La encuesta debería estar registrada en el sistema.");
    }

    @Test
    void testEditarEncuesta() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
         
        String id = "Encuesta2";
        String learningPath = "Java Básico";
        Encuesta encuesta = constructorEncuesta.crearEncuesta("Encuesta de Prueba", "Prueba de edición", id, 
                new Date(), new Date(), 20, 1, 3.0, "Encuesta", true, "Java Básico", 50);
        
   
        String nuevoDescripcion = "Nueva Descripción";
        constructorEncuesta.editarEncuesta(learningPath + "." + id, "descripcion", nuevoDescripcion);
        assertEquals(nuevoDescripcion, encuesta.getDescripcion(), "La descripción debería haber sido actualizada.");
        
         
        Integer nuevaDificultad = 3;
        constructorEncuesta.editarEncuesta(learningPath + "." + id, "dificultad", nuevaDificultad);
        assertEquals(nuevaDificultad, encuesta.getDificultad(), "La dificultad debería haber sido actualizada.");
        
       
        Integer nuevoPuntajeMaximo = 75;
        constructorEncuesta.editarEncuesta(learningPath + "." + id, "puntajeMaximo", nuevoPuntajeMaximo);
        assertEquals(nuevoPuntajeMaximo, encuesta.getPuntajeMaximo(), "El puntaje máximo debería haber sido actualizado.");
        
    }

}
