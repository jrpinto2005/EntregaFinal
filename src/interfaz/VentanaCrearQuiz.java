package interfaz;

import javax.swing.*;
import envios.Opcion;
import envios.PreguntaOpcionMultiple;
import learningPaths.Quiz;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearQuiz extends JFrame {
    private JTextField txtDescripcionPregunta;
    private JComboBox<String> cmbCantidadOpciones;
    private JTextField[] txtOpciones;
    private JCheckBox[] chkEsCorrecto;
    private JButton btnSiguiente;
    private JButton btnFinalizar;

    private int cantidadPreguntas;
    private int preguntaActual;

    private Quiz quiz;

    public VentanaCrearQuiz(Quiz quiz, int cantidadPreguntas) {
        this.cantidadPreguntas = cantidadPreguntas;
        this.preguntaActual = 0;
        this.quiz = quiz;

        setTitle("Crear Quiz");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarFormulario();
    }

    private void inicializarFormulario() {
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel para los datos de la pregunta
        JPanel panelPregunta = new JPanel(new GridLayout(0, 1, 5, 5));
        txtDescripcionPregunta = new JTextField();
        cmbCantidadOpciones = new JComboBox<>(new String[]{"2", "4"});

        panelPregunta.add(new JLabel("Descripción de la pregunta:"));
        panelPregunta.add(txtDescripcionPregunta);
        panelPregunta.add(new JLabel("Cantidad de opciones:"));
        panelPregunta.add(cmbCantidadOpciones);

        cmbCantidadOpciones.addActionListener(e -> actualizarOpciones(Integer.parseInt((String) cmbCantidadOpciones.getSelectedItem())));

        panelCentral.add(panelPregunta, BorderLayout.NORTH);

        // Panel para las opciones
        JPanel panelOpciones = new JPanel(new GridLayout(4, 2, 10, 10)); 
        panelOpciones.setBorder(BorderFactory.createTitledBorder("Opciones"));
        panelCentral.add(panelOpciones, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        // Botones inferiores
        btnSiguiente = new JButton("Siguiente");
        btnFinalizar = new JButton("Finalizar");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnFinalizar);
        add(panelBotones, BorderLayout.SOUTH);

        btnSiguiente.addActionListener(e -> capturarPregunta(panelOpciones));
        btnFinalizar.addActionListener(e -> finalizarQuiz());

        actualizarOpciones(2); // Inicializar con 2 opciones por defecto
    }


    private void actualizarOpciones(int cantidad) {
        JPanel panelOpciones = (JPanel) ((JPanel) getContentPane().getComponent(0)).getComponent(1);

        panelOpciones.removeAll();

        // Inicializa los arreglos con el tamaño adecuado según la cantidad de opciones.
        txtOpciones = new JTextField[cantidad]; 
        chkEsCorrecto = new JCheckBox[cantidad];

        for (int i = 0; i < cantidad; i++) {
            txtOpciones[i] = new JTextField();
            chkEsCorrecto[i] = new JCheckBox("Correcta");

            panelOpciones.add(new JLabel("Opción " + (i + 1) + ":"));
            panelOpciones.add(txtOpciones[i]);
            panelOpciones.add(new JLabel(""));
            panelOpciones.add(chkEsCorrecto[i]);
        }

        panelOpciones.revalidate();
        panelOpciones.repaint();
    }



    private void capturarPregunta(JPanel panelOpciones) {
        String descripcion = txtDescripcionPregunta.getText();
        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(descripcion, preguntaActual + 1);

        for (int i = 0; i < txtOpciones.length; i++) {
            String texto = txtOpciones[i].getText();
            boolean esCorrecta = chkEsCorrecto[i].isSelected();
            Opcion opcion = new Opcion(texto, esCorrecta, i);
            pregunta.agregarOpcion(opcion);
        }

        quiz.addPregunta(pregunta);
        preguntaActual++;

        if (preguntaActual < cantidadPreguntas) {
            txtDescripcionPregunta.setText("");
            cmbCantidadOpciones.setSelectedIndex(0); // Reinicia a 2 opciones
            actualizarOpciones(2);
        } else {
            JOptionPane.showMessageDialog(this, "Todas las preguntas han sido creadas.");
            btnSiguiente.setEnabled(false);
        }
    }

    private void finalizarQuiz() {
        JOptionPane.showMessageDialog(this, "Quiz creado con éxito.");
        dispose();
    }
}
