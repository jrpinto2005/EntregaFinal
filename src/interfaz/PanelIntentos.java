package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelIntentos extends JFrame {
    public PanelIntentos() {
        setTitle("Intento Fallido");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar layout y componentes
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel lblMensaje = new JLabel(
                "El usuario o contraseña no es correcto, intente de nuevo",
                SwingConstants.CENTER
        );
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 14));
        JButton btnAceptar = new JButton("Aceptar");

        // Acción del botón "Aceptar"
        btnAceptar.addActionListener(e -> dispose());

        // Añadir componentes al panel
        panel.add(lblMensaje, BorderLayout.CENTER);
        panel.add(btnAceptar, BorderLayout.SOUTH);

        // Añadir panel al frame
        add(panel);
    }
    }
