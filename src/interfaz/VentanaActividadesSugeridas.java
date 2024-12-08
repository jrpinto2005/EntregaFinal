package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import envios.Envio;
import learningPaths.Actividad;
import learningPaths.Encuesta;
import learningPaths.Examen;
import learningPaths.LearningPath;
import learningPaths.Quiz;
import learningPaths.RecursoEducativo;
import learningPaths.Tarea;
import usuario.Estudiante;
import usuario.Sistema;

public class VentanaActividadesSugeridas extends JFrame
{
	private JList<String> listaActividades;
    private JButton btnSeleccionar;
    private JButton btnVolver;
    private JLabel lblResultado;

    public VentanaActividadesSugeridas(Estudiante estudiante,MenuEstudiante menu) {
        // Usar un conjunto para evitar duplicados
        List<String> actividades = new ArrayList<>();
        
        for (LearningPath lp : estudiante.getLearningPaths()) {
            for (Actividad actividad : lp.getActividadesOrdenadas()) {
                boolean actividadCompletada = false;

                // Revisar los envíos del estudiante
                for (Envio envio : estudiante.getEnvios()) {
                    if (envio.getActividad().equals(actividad)) {
                        actividadCompletada = envio.isCompletado();
                        break; // Salir del bucle si encontramos el envío relacionado
                    }
                }

                // Solo agregar actividades no completadas
                if (!actividadCompletada) {
                    actividades.add(actividad.getId());
                }
            }
        }

        // Configuración de la ventana
        setTitle("Seleccionar Actividad");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar el layout
        setLayout(new BorderLayout(10, 10));

        // Etiqueta superior
        JLabel lblTitulo = new JLabel("Seleccione una actividad para realizar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        // Crear lista de actividades
        listaActividades = new JList<>(actividades.toArray(new String[0]));
        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaActividades);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botón y resultado
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnSeleccionar = new JButton("Seleccionar");
        btnVolver = new JButton("Volver");
        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Arial", Font.ITALIC, 14));
        lblResultado.setForeground(Color.BLUE);

        // Acción del botón
        btnSeleccionar.addActionListener(e-> {
                String actividadSeleccionada = listaActividades.getSelectedValue();
                if (actividadSeleccionada != null) 
                {
                
                    Actividad actividad = Sistema.getInstancia().getActividades().get(actividadSeleccionada);
                    List<Actividad> sugeridas =  actividad.getActividadesRecomendadas();
                    VentanaListaActividades ventanaActividades = new VentanaListaActividades(sugeridas,this);
                    ventanaActividades.setVisible(true);
                    this.setVisible(false);
            
                }});
        btnVolver.addActionListener(e-> {
        	setVisible(false);
            menu.setVisible(true);
            dispose();
        });

        // Añadir componentes al panel inferior
        panelBotones.add(btnSeleccionar, BorderLayout.NORTH);
        panelBotones.add(btnVolver, BorderLayout.NORTH);
        panelBotones.add(lblResultado, BorderLayout.CENTER);

        add(panelBotones, BorderLayout.SOUTH);
    }

}
