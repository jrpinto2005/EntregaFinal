package learningPathsTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constructores.ConstructorLearningPath;
import constructores.ConstructorQuiz;
import constructores.ConstructorTarea;
import envios.Reseña;
import learningPaths.Actividad;
import learningPaths.LearningPath;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Sistema;

class ActividadTest {
	
	Sistema sistema = Sistema.getInstancia();

    private RecursoEducativo actividad1;
    private RecursoEducativo actividad2;
    private Tarea tarea3;
    private Date fechaInicio;
    private Date fechaFin;
    private Reseña reseña1;

    @BeforeEach
    void setUp() {
    	LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);

		LearningPath lp2 = new LearningPath("Java Avanzado", "Conceptos avanzados de Java", 3, 80, 4, new Date(), new Date(),
				1, "profesor02", "Dominar conceptos avanzados de Java", 0.0);
		sistema.addLP(lp1);
		sistema.addLP(lp2);
	
        fechaInicio = new Date();
        fechaFin = new Date();
        actividad1 = new RecursoEducativo("video de variables", "Introducir variables", "Tarea1", 
                fechaInicio, fechaFin, 120, 3, 4.5, "Recurso Educativo", true, "Java Básico");
        actividad2 = new RecursoEducativo("Video variables 2", "Introducir variables", "Tarea2", new Date(), new Date(),
				120, 3, 4.5, "Recurso Educativo", true, "Java Básico");
        tarea3 = new Tarea("Tarea Exceptions", "Exceptions", "Tarea3", new Date(), new Date(),
				120, 3, 4.5, "Tarea", true, "Java Básico");
         
        actividad1.agregarActividadRecomendada(actividad2);
        
        String idReseña = "Reseña1";
        String comentario = "Muy útil y bien estructurada.";
        int rating = 5;
        Date fecha = new Date();
        String idActividad = "Java101";

       
        reseña1 = new Reseña(idReseña, comentario, rating, fecha, idActividad);
        
        actividad1.agregarReseña(reseña1);
        
    }

    @Test
    void testGetters() {
        assertEquals("video de variables", actividad1.getDescripcion());
        assertEquals("Introducir variables", actividad1.getObjetivo());
        assertEquals("Java Básico.Tarea1", actividad1.getId());
        assertEquals(fechaInicio, actividad1.getFechaInicio());
        assertEquals(fechaFin, actividad1.getFechaFin());
        assertEquals(120, actividad1.getDuracion());
        assertEquals(3, actividad1.getDificultad());
        assertEquals(4.5, actividad1.getRating());
        assertEquals("Recurso Educativo", actividad1.getTipoActividad());
        assertTrue(actividad1.isObligatoria());
        assertEquals(1,actividad1.getActividadesRecomendadas().size());
        assertEquals(3, actividad1.getActividadesRecomendadas().get(0).getDificultad());
        assertEquals("Reseña1", actividad1.getReseñas().getFirst().getIdReseña());
        assertEquals(1, actividad1.getReseñas().size());

    }

    @Test
    void testSetters() {
        actividad1.setDescripcion("Video variables 2");
        assertEquals("Video variables 2", actividad1.getDescripcion());

        actividad1.setObjetivo("Refuerzo de variables");
        assertEquals("Refuerzo de variables", actividad1.getObjetivo());

        actividad1.setId("NuevaTarea1");
        assertEquals("NuevaTarea1", actividad1.getId());

        Date nuevaFechaInicio = new Date(fechaInicio.getTime() + 1000);
        actividad1.setFechaInicio(nuevaFechaInicio);
        assertEquals(nuevaFechaInicio, actividad1.getFechaInicio());

        Date nuevaFechaFin = new Date(fechaFin.getTime() + 1000);
        actividad1.setFechaFin(nuevaFechaFin);
        assertEquals(nuevaFechaFin, actividad1.getFechaFin());

        actividad1.setDuracion(150);
        assertEquals(150, actividad1.getDuracion());

        actividad1.setDificultad(4);
        assertEquals(4, actividad1.getDificultad());

        actividad1.setRating(3.8);
        assertEquals(3.8, actividad1.getRating());

        actividad1.setTipoActividad("Lectura");
        assertEquals("Lectura", actividad1.getTipoActividad());

        actividad1.setObligatoria(false);
        assertFalse(actividad1.isObligatoria());
    }
}
