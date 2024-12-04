package constructoresTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import envios.EnvioExamen;
import envios.EnvioQuiz;
import envios.EnvioEncuesta;
import envios.EnvioTarea;
import envios.Reseña;
import exceptions.ActivdadNoEcontradaException;
import envios.EnvioRecurso;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.Encuesta;
import learningPaths.Tarea;
import learningPaths.RecursoEducativo;
import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Sistema;
import constructores.ControladorEnvios;

class ControladorEnviosTest {

    private ControladorEnvios controlador;
    private Estudiante estudiante;
    private Examen examen;
    private Quiz quiz;
    private Encuesta encuesta;
    private Tarea tarea;
    private RecursoEducativo recurso;
    private Sistema sistema;
    private Date fechaInicio;
    private Date fechaFin;
    private RecursoEducativo actividad1;
    private RecursoEducativo actividad2;
    private Tarea tarea3;
    private Reseña reseña1;
    private Profesor profesor01;
    
    @BeforeEach
    void setUp() {
        sistema = Sistema.getInstancia();
        controlador = new ControladorEnvios();
        ControladorUsuarios cu= ControladorUsuarios.getInstancia();
        
        LearningPath lp1 = new LearningPath("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, fechaInicio, fechaFin,
				1, "profesor01", "Entender lo básico de Java", 0.0);

		LearningPath lp2 = new LearningPath("Java Avanzado", "Conceptos avanzados de Java", 3, 80, 4, fechaInicio, fechaFin,
				1, "profesor02", "Dominar conceptos avanzados de Java", 0.0);
		sistema.addLP(lp1);
		sistema.addLP(lp2);
		

      
        estudiante = new Estudiante("001", "Juan Perez", "juan@example.com", "password123", "estudiante");
        profesor01 = new Profesor("profesor01", "profesor01", "juan@example.com", "password123", "profesor");
        cu.agregarProfesor(profesor01);

         
        examen = new Examen("ex01", "Examen de Java","Examen1", fechaInicio, fechaFin,1, 100, 5, "Examen", true,"Java Básico", 1);
        quiz = new Quiz("quiz01", "Quiz de Java","Quiz1", fechaInicio, fechaFin,1, 100, 5, "Quiz", true,"Java Básico", 1);
        encuesta = new Encuesta("enc01", "Encuesta de Java","Encuesta1", fechaInicio, fechaFin,1, 100, 5, "Encuesta", true,"Java Básico", 1);
        tarea = new Tarea("tarea01", "Tarea de Java","Tarea1", fechaInicio, fechaFin,1, 100, 5, "Tarea", true,"Java Básico");
        recurso = new RecursoEducativo("recurso01", "Recurso de Java ","Recurso1", fechaInicio, fechaFin,1, 100, 5, "RecursoEducativo", true,"Java Básico");
        
        // Agregar actividades al sistema
        sistema.addActividad(examen);
        sistema.addActividad(quiz);
        sistema.addActividad(encuesta);
        sistema.addActividad(tarea);
        sistema.addActividad(recurso);
        
        lp1.agregarActividad(examen);
        lp1.agregarActividad(quiz);
        lp1.agregarActividad(encuesta);
        lp1.agregarActividad(tarea);
        lp1.agregarActividad(recurso);
         
        estudiante.inscribirseEnLearningPath(lp1);
        
    }

    @Test
    void testHacerExamen() throws ActivdadNoEcontradaException {
        List<String> respuestas = new ArrayList<>();
        respuestas.add("respuesta 1");
        respuestas.add("respuesta 2");
        respuestas.add("respuesta 3");
        
         
        EnvioExamen envioExamen = controlador.hacerExamen(estudiante, "Java Básico.Examen1", respuestas);

        // Verificar que el envío de examen esté completo
        assertNotNull(envioExamen);
        assertEquals(1, estudiante.getEnvios().size());
        assertEquals("Java Básico",envioExamen.getTituloLp());
    }

    @Test
    void testHacerQuiz() throws ActivdadNoEcontradaException {
        List<Integer> respuestas = new ArrayList<>();
        respuestas.add(1);
        respuestas.add(0);
        respuestas.add(2);
        
        // Realizar el quiz
        EnvioQuiz envioQuiz = controlador.hacerQuiz(estudiante, "Java Básico.Quiz1", respuestas);

        // Verificar que el envío de quiz esté completo
        assertNotNull(envioQuiz);
        assertEquals(1, estudiante.getEnvios().size());
        assertEquals("Java Básico",envioQuiz.getTituloLp());
    }

    @Test
    void testHacerEncuesta() throws ActivdadNoEcontradaException {
        List<Integer> respuestas = new ArrayList<>();
        respuestas.add(1);
        respuestas.add(0);
        respuestas.add(2);
        
        // Realizar la encuesta
        EnvioEncuesta envioEncuesta = controlador.hacerEncuesta(estudiante, "Java Básico.Encuesta1", respuestas);

        // Verificar que el envío de encuesta esté completo
        assertNotNull(envioEncuesta);
        assertEquals(1, estudiante.getEnvios().size());
        assertEquals("Java Básico",envioEncuesta.getTituloLp());
    }

    @Test
    void testHacerTarea() throws ActivdadNoEcontradaException {
        // Realizar la tarea
        EnvioTarea envioTarea = controlador.hacerTarea(estudiante, "Java Básico.Tarea1");

        // Verificar que el envío de tarea esté completo
        assertNotNull(envioTarea);
        assertEquals(1, estudiante.getEnvios().size());
        assertEquals("Java Básico",envioTarea.getTituloLp());
    }

    @Test
    void testHacerRecurso() throws ActivdadNoEcontradaException {
        // Realizar el recurso
        EnvioRecurso envioRecurso = controlador.hacerRecurso(estudiante, "Java Básico.Recurso1");

        // Verificar que el envío de recurso esté completo
        assertNotNull(envioRecurso);
        assertEquals(1, estudiante.getEnvios().size());
        assertEquals("Java Básico",envioRecurso.getTituloLp());
    }
}