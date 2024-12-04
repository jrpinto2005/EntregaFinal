package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorExamen;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Examen;
import learningPaths.LearningPath;
import usuario.Sistema;

class ConstructorExamenTest {

    private ConstructorExamen constructorExamen;
    Sistema sistema = Sistema.getInstancia();

    @BeforeEach
    void setUp() {
        LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
                1, "profesor01", "Entender lo básico de Java", 0.0);
        sistema.addLP(lp1);
        
        constructorExamen = new ConstructorExamen();
    }

    @Test
    void testCrearExamen() throws ActivdadNoEcontradaException {

        String descripcion = "Examen de Java Básico";
        String objetivo = "Evaluar conocimientos básicos de Java";
        String id = "Examen1";
        Date fechaInicio = new Date();
        Date fechaFin = new Date();
        int duracion = 60;
        int dificultad = 3;
        double rating = 4.8;
        String tipoActividad = "Examen";
        boolean obligatoria = true;
        String learningPath = "Java Básico";
        int puntajeMaximo = 100;

        Examen examen = constructorExamen.crearExamen(descripcion, objetivo, id, fechaInicio, fechaFin, duracion, dificultad, rating, tipoActividad, obligatoria, learningPath, puntajeMaximo);

        assertNotNull(examen, "El examen debería haberse creado y no ser nulo.");
        assertEquals(descripcion, examen.getDescripcion());
        assertEquals(objetivo, examen.getObjetivo());
        assertEquals(learningPath + "." + id, examen.getId());
        assertEquals(duracion, examen.getDuracion());
        assertEquals(dificultad, examen.getDificultad());
        assertEquals(rating, examen.getRating(), 0.01);
        assertEquals(tipoActividad, examen.getTipoActividad());
        assertEquals(obligatoria, examen.isObligatoria());
        assertEquals(learningPath, examen.getLearningPath().getTitulo());
        assertEquals(puntajeMaximo, examen.getPuntajeMaximo());
        assertEquals(examen, sistema.encontrarActividad(learningPath + "." + id), "El examen debería estar registrado en el sistema.");
    }

    @Test
    void testEditarExamen() throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, ActivdadNoEcontradaException {
        
        String id = "Examen2";
        String learningPath = "Java Básico";
        Examen examen = constructorExamen.crearExamen("Examen de Prueba", "Prueba de edición", id, new Date(), new Date(), 45, 2, 3.5, "Examen", true, learningPath, 80);

    
        String nuevoDescripcion = "Nueva Descripción de Examen";
        constructorExamen.editarExamen(learningPath + "." + id, "descripcion", nuevoDescripcion);
        assertEquals(nuevoDescripcion, examen.getDescripcion(), "La descripción debería haber sido actualizada.");
        
 
        Integer nuevaDificultad = 4;
        constructorExamen.editarExamen(learningPath + "." + id, "dificultad", nuevaDificultad);
        assertEquals(nuevaDificultad, examen.getDificultad(), "La dificultad debería haber sido actualizada.");

  
        Integer nuevoPuntajeMaximo = 90;
        constructorExamen.editarExamen(learningPath + "." + id, "puntajeMaximo", nuevoPuntajeMaximo);
        assertEquals(nuevoPuntajeMaximo, examen.getPuntajeMaximo(), "El puntaje máximo debería haber sido actualizado.");
        
        
    }
    
    
    
}
