package usuario;

import java.util.ArrayList;
import java.util.Date;

import constructores.ConstructorLearningPath;
import constructores.ConstructorQuiz;
import constructores.ConstructorTarea;
import learningPaths.Actividad;
import learningPaths.LearningPath;
import learningPaths.Quiz;

public class MainUsuarios {

	// deberia: crearUsuarios
	// guardarlos en el sistema
	// metodo para editar perfil

	public static void main(String[] args) {
		// Crear listas de usuarios
		ArrayList<Estudiante> estudiantes = new ArrayList<>();
		ArrayList<Profesor> profesores = new ArrayList<>();

		Sistema sistema = new Sistema();

		ConstructorLearningPath c = new ConstructorLearningPath();
		ConstructorTarea t = new ConstructorTarea();
		ConstructorQuiz q = new ConstructorQuiz();

		// Crear estudiantes
		Estudiante estudiante1 = new Estudiante("estudiante1", "Carlos", "carlos@example.com", "pass123", "estudiante");
		Estudiante estudiante2 = new Estudiante("estudiante2", "Laura", "laura@example.com", "pass456", "estudiante");

		// Añadir estudiantes a la lista
		estudiantes.add(estudiante1);
		estudiantes.add(estudiante2);

		// Imprimir información de los estudiantes
		System.out.println("Lista de estudiantes:");
		for (Estudiante estudiante : estudiantes) {
			System.out.println(estudiante.getNombre());
		}

		Profesor profesor1 = new Profesor("profesor01", "Noe Leslie", "mariosanchez@example.com", "profpass",
				"profesor");
		Profesor profesor2 = new Profesor("profesor02", "Julian Restrepo", "julianrestrepo@example.com", "profpass2",
				"profesor");

		profesores.add(profesor1);
		profesores.add(profesor2);

		System.out.println("\nLista de profesores:");
		for (Profesor profesor : profesores) {
			System.out.println(profesor.getNombre());
		}

		// Crea LearningPaths
		LearningPath lp1 = c.crearLP("Java Básico", "Aprende los fundamentos de Java", 1, 40, 5, new Date(), new Date(),
				1, "profesor01", "Entender lo básico de Java", 0.0);

		LearningPath lp2 = c.crearLP("Java Avanzado", "Conceptos avanzados de Java", 3, 80, 4, new Date(), new Date(),
				1, "profesor02", "Dominar conceptos avanzados de Java", 0.0);
		// Añadir actividades al Learning Path
		Actividad act1 = t.crearTarea("Video variables 1", "Introducir variables", "Proyecto 1", new Date(), new Date(),
				120, 3, 4.5, "Recurso Educativo", true, "Java Básico");
		Quiz act2 = q.crearQuiz("Quiz de transformaciones lineales", "aprender a ....", "Quiz 2", new Date(),
				new Date(), 120, 3, 4.5, "Recurso Educativo", true, "Java Básico", 10);

		lp1.agregarActividad(act1);
		lp1.agregarActividad(act2);

		// Mostrar Learning Path
		System.out.println("\nLearning Path:");
		System.out.println(lp1.getTitulo());

		// Mostrar actividades del Learning Path
		System.out.println("Actividades del Learning Path:");
		for (Actividad actividad : lp1.getActividadesOrdenadas()) {
			System.out.println(actividad.getId());
		}
	}
}
