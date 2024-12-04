package usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constructores.ConstructorLearningPath;
import envios.Envio;
import envios.EnvioExamen;
import learningPaths.LearningPath;

public class Profesor extends Usuario {

	private List<LearningPath> learningPaths;
	private List<EnvioExamen> enviosPendientes;
	

	public Profesor(String id, String nombre, String email, String contraseña, String tipo) {
		super(id, nombre, email, contraseña, tipo);
		this.learningPaths = new ArrayList<LearningPath>();
		this.enviosPendientes= new ArrayList<EnvioExamen>();
	}

	public String getContrasena() {
		return this.contraseña;

	}

	public void agregarEnvio(EnvioExamen envio) {
		enviosPendientes.add(envio);
		
	}

	public List<EnvioExamen> getEnviosPorCalificar() {
		
		return enviosPendientes;
	}

}