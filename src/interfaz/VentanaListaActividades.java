package interfaz;

	import javax.swing.*;
	import java.awt.*;
	import java.util.List;

	import learningPaths.Actividad;

	@SuppressWarnings("serial")
	public class VentanaListaActividades extends JFrame {

	    public VentanaListaActividades(List<Actividad> actividades, VentanaActividadesSugeridas ventana) {
	        setTitle("Lista de Actividades Sugeridas");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setLocationRelativeTo(null);

	        // Configuración del layout
	        setLayout(new BorderLayout(10, 10));

	        // Etiqueta superior
	        JLabel lblTitulo = new JLabel("Actividades disponibles", SwingConstants.CENTER);
	        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
	        add(lblTitulo, BorderLayout.NORTH);

	        // Crear una lista de nombres de actividades
	        String[] nombresActividades = actividades.stream()
	                                                 .map(Actividad::getNombre) // Obtener el nombre de cada actividad
	                                                 .toArray(String[]::new);

	        // Crear el componente JList con los nombres
	        JList<String> listaActividades = new JList<>(nombresActividades);
	        listaActividades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane scrollPane = new JScrollPane(listaActividades);
	        add(scrollPane, BorderLayout.CENTER);

	        // Botón para cerrar la ventana
	        JButton btnCerrar = new JButton("Cerrar");
	        btnCerrar.addActionListener(e -> 
	        {
	        ventana.setVisible(true);
	        this.setVisible(false);
	        dispose();
	        });
	        JPanel panelInferior = new JPanel();
	        panelInferior.add(btnCerrar);
	        add(panelInferior, BorderLayout.SOUTH);
	    }
	}


