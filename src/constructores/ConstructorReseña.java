package constructores;

import java.util.Date;
import exceptions.ActivdadNoEcontradaException;
import learningPaths.Actividad;
import usuario.Sistema;
import envios.Reseña;

public class ConstructorReseña {
    private Sistema sistema;

    public ConstructorReseña() {
        this.sistema = Sistema.getInstancia();
    }

    public void hacerReseña(String idReseña, String comentario, int rating, Date fecha, String idActividad) 
            throws ActivdadNoEcontradaException {
        
        Actividad actividad = sistema.encontrarActividad(idActividad);
        
       
        if (actividad == null) {
            throw new ActivdadNoEcontradaException(idActividad);
        }
        
       
        Reseña reseña = new Reseña(idReseña, comentario, rating, fecha, idActividad);
        actividad.agregarReseña(reseña);
    }
}
