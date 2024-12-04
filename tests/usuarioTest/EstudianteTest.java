package usuarioTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import envios.Envio;
import envios.EnvioExamen;
import envios.EnvioRecurso;
import envios.EnvioTarea;
import learningPaths.LearningPath;
import learningPaths.Actividad;
import learningPaths.Tarea;
import learningPaths.RecursoEducativo;
import usuario.Estudiante;
import usuario.Sistema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
class EstudianteTest {
    private Estudiante estudiante;
    private LearningPath lp1;
    private Sistema sistema;

    @BeforeEach
    void setUp() {
        sistema = Sistema.getInstancia();

         
        lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
                1, "profesor01", "Entender lo básico de Java", 0.0);
        sistema.addLP(lp1);
        

         
        estudiante = new Estudiante("001", "Juan Perez", "juan@example.com", "password123", "estudiante");

         
        Actividad actividad1 = new RecursoEducativo("video de variables", "Introducir variables", "Tarea1", 
                new Date(), new Date(), 120, 3, 4.5, "Recurso Educativo", true, "Java Básico");
        Actividad actividad2 = new Tarea("Tarea Exceptions", "Exceptions", "Tarea2", new Date(), new Date(),
                120, 3, 4.5, "Tarea", true, "Java Básico");

         
        Envio envio1 = new EnvioRecurso(actividad1, estudiante.getId(), lp1.getTitulo(), true);  
        Envio envio2 = new EnvioTarea(actividad2, estudiante.getId(), lp1.getTitulo(), false);  

        
        estudiante.getEnvios().add(envio1);
        estudiante.getEnvios().add(envio2);
        
    }

    @Test
    void testInscribirseEnLearningPath() {
        
        assertTrue(estudiante.inscribirseEnLearningPath(lp1), "El estudiante debería poder inscribirse en un nuevo Learning Path.");

         
        assertFalse(estudiante.inscribirseEnLearningPath(lp1), "El estudiante no debería poder inscribirse dos veces en el mismo Learning Path.");
    }

    @Test
    void testVerProgreso() {
        
        double progresoEsperado = 1.0 / 2.0;
        assertEquals(progresoEsperado, estudiante.verProgreso(), 0.01, "El progreso calculado no es correcto.");
    }
}
