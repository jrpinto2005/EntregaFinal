package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaLogin extends JFrame {
    public VentanaLogin() {
        setTitle("Menu Ingresar");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtUsuario = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnVolver = new JButton("Volver");

        // Acción del botón volver
        btnVolver.addActionListener(e -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            this.dispose();
        });

        // Añadir componentes
        add(lblUsuario);
        add(txtUsuario);
        add(lblContrasena);
        add(txtContrasena);
        add(btnVolver);
        add(btnIngresar);
        
    }
}
