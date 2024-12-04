package usuarioTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.IdUsuarioYaExisteException;
import exceptions.UsuarioContraseñaIncorrectoException;
import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;
public class ControladorUsuariosTest {
    private ControladorUsuarios controlador;

    @BeforeEach
    public void setUp() {
        controlador = ControladorUsuarios.getInstancia();
        controlador.getEstudiantes().clear();
        controlador.getProfesores().clear();
    }

    @Test
    public void testRegistrarEstudiante() throws IdUsuarioYaExisteException {
        assertTrue(controlador.registrarEstudiante("E1", "Juan", "juan@example.com", "pass123", "Estudiante"));
        assertNotNull(controlador.encontrarEstudiante("E1"));
        try {
        	 controlador.registrarEstudiante("E1", "Juan", "juan@example.com", "pass123", "Estudiante");
        	 fail("Deberia haber retornado un id ya existente exception");
        }
        catch (IdUsuarioYaExisteException e) {
        	
        } 
      
    }

    @Test
    public void testRegistrarProfesor() throws IdUsuarioYaExisteException {
        assertTrue(controlador.registrarProfesor("P1", "Ana", "ana@example.com", "prof456", "Profesor"));
        assertNotNull(controlador.encontrarProfesor("P1"));
        try {
        	controlador.registrarProfesor("P1", "Ana", "ana@example.com", "prof456", "Profesor");
       	 fail("Deberia haber retornado un id ya existente exception");
       }
       catch (IdUsuarioYaExisteException e) {
       	
       }
    }

    @Test
    public void testAgregarYEncontrarEstudiante() {
        Estudiante estudiante = new Estudiante("E2", "Carlos", "carlos@example.com", "pass789", "Estudiante");
        controlador.agregarEstudiante(estudiante);
        assertEquals(estudiante, controlador.encontrarEstudiante("E2"));
    }

    @Test
    public void testAgregarYEncontrarProfesor() {
        Profesor profesor = new Profesor("P2", "Laura", "laura@example.com", "prof987", "Profesor");
        controlador.agregarProfesor(profesor);
        assertEquals(profesor, controlador.encontrarProfesor("P2"));
    }

    @Test
    public void testEliminarEstudiante() throws IdUsuarioYaExisteException {
        controlador.registrarEstudiante("E3", "Luis", "luis@example.com", "pass456", "Estudiante");
        controlador.eliminarEstudiante("E3");
        assertNull(controlador.encontrarEstudiante("E3"));
    }

    @Test
    public void testEliminarProfesor() throws IdUsuarioYaExisteException {
        controlador.registrarProfesor("P3", "Maria", "maria@example.com", "prof321", "Profesor");
        controlador.eliminarProfesor("P3");
        assertNull(controlador.encontrarProfesor("P3"));
    }

    @Test
    public void testEditarPerfilEstudiante() throws IdUsuarioYaExisteException {
        controlador.registrarEstudiante("E4", "Pedro", "pedro@example.com", "pass123", "Estudiante");
        controlador.editarPerfil("E4", "Pedro Editado", "pedro_editado@example.com");
        Estudiante estudiante = controlador.encontrarEstudiante("E4");
        assertEquals("Pedro Editado", estudiante.getNombre());
        assertEquals("pedro_editado@example.com", estudiante.getEmail());
    }

    @Test
    public void testEditarPerfilProfesor() throws IdUsuarioYaExisteException {
        controlador.registrarProfesor("P4", "Sofia", "sofia@example.com", "prof789", "Profesor");
        controlador.editarPerfil("P4", "Sofia Editada", "sofia_editada@example.com");
        Profesor profesor = controlador.encontrarProfesor("P4");
        assertEquals("Sofia Editada", profesor.getNombre());
        assertEquals("sofia_editada@example.com", profesor.getEmail());
    }

    @Test
    public void testIniciarSesionEstudiante() throws IdUsuarioYaExisteException, UsuarioContraseñaIncorrectoException {
        controlador.registrarEstudiante("E5", "Jorge", "jorge@example.com", "password", "Estudiante");
        assertEquals("E5",controlador.iniciarSesion("E5", "password").getId());
        try {
            controlador.iniciarSesion("E5", "wrongpassword");
            fail("Se esperaba una UsuarioContraseñaIncorrectoException pero no se lanzó.");
        } catch (UsuarioContraseñaIncorrectoException e) {

        }
    }

    @Test
    public void testIniciarSesionProfesor() throws IdUsuarioYaExisteException, UsuarioContraseñaIncorrectoException {
        controlador.registrarProfesor("P5", "Carla", "carla@example.com", "secret", "Profesor");
        assertEquals("P5",controlador.iniciarSesion("P5", "secret").getId());
        try {
            controlador.iniciarSesion("P5", "wrongpassword");
            fail("Se esperaba una UsuarioContraseñaIncorrectoException pero no se lanzó.");
        } catch (UsuarioContraseñaIncorrectoException e) {

        }
    }
}