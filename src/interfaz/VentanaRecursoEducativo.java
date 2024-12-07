package interfaz;

import java.awt.GridLayout;
import javax.swing.*;
import learningPaths.Lectura;
import learningPaths.RecursoEducativo;
import learningPaths.SitioWeb;
import learningPaths.Video;
import usuario.Sistema;

public class VentanaRecursoEducativo extends JFrame {
    
    private JTextField txtContenido;
    private JButton btnTerminado;
    private JComboBox<String> cmbTipoRecurso;

    public VentanaRecursoEducativo(MenuProfesor menu, RecursoEducativo recurso) {
        setTitle("Crear Recurso Educativo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        // Panel para contenido
        JLabel lblContenido = new JLabel("Contenido del Recurso:");
        txtContenido = new JTextField();
        add(lblContenido);
        add(txtContenido);

        // ComboBox para seleccionar el tipo de recurso
        JLabel lblTipoRecurso = new JLabel("Tipo de Recurso:");
        cmbTipoRecurso = new JComboBox<>(new String[]{"Sitio web", "Video", "Lectura"});
        add(lblTipoRecurso);
        add(cmbTipoRecurso);

        // Botón Terminado
        btnTerminado = new JButton("Terminado");
        add(btnTerminado);

        // Acción del botón
        btnTerminado.addActionListener(e -> {
            String tipoRecurso = (String) cmbTipoRecurso.getSelectedItem();
            String contenido = txtContenido.getText();

            if (contenido.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El contenido no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (tipoRecurso) {
                case "Sitio web":
                    String url = JOptionPane.showInputDialog(this, "Ingresa la URL:", "Entrada de URL", JOptionPane.QUESTION_MESSAGE);
                    if (url == null || url.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "URL inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    SitioWeb web = new SitioWeb(
                            recurso.getDescripcion(), recurso.getObjetivo(), recurso.getNombre(), recurso.getFechaInicio(),
                            recurso.getFechaFin(), recurso.getDuracion(), recurso.getDificultad(), recurso.getRating(),
                            "web", recurso.isObligatoria(), recurso.getLearningPath().getTitulo(), url
                    );
                    web.setContenido(contenido);
                    Sistema.getInstancia().addActividad(web);
                    break;

                case "Video":
                    String duracionStr = JOptionPane.showInputDialog(this, "Ingresa la duración del video:", "Duración del video", JOptionPane.QUESTION_MESSAGE);
                    if (duracionStr == null || duracionStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Duración inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        int duracion = Integer.parseInt(duracionStr);
                        Video video = new Video(
                                recurso.getDescripcion(), recurso.getObjetivo(), recurso.getNombre(), recurso.getFechaInicio(),
                                recurso.getFechaFin(), recurso.getDuracion(), recurso.getDificultad(), recurso.getRating(),
                                "video", recurso.isObligatoria(), recurso.getLearningPath().getTitulo(), duracion
                        );
                        video.setContenido(contenido);
                        Sistema.getInstancia().addActividad(video);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Por favor ingresa un número válido para la duración.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;

                case "Lectura":
                    Lectura lectura = new Lectura(
                            recurso.getDescripcion(), recurso.getObjetivo(), recurso.getNombre(), recurso.getFechaInicio(),
                            recurso.getFechaFin(), recurso.getDuracion(), recurso.getDificultad(), recurso.getRating(),
                            "lectura", recurso.isObligatoria(), recurso.getLearningPath().getTitulo()
                    );
                    lectura.setContenido(contenido);
                    Sistema.getInstancia().addActividad(lectura);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Tipo de recurso no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            JOptionPane.showMessageDialog(this, "Recurso Educativo creado con éxito.");
            this.dispose();
            menu.setVisible(true);
        });
    }
}
