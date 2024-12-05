package interfaz;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import exceptions.IdUsuarioYaExisteException;
import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;
import usuario.Usuario;

@SuppressWarnings("serial")
public class VentanaCrearUsuario extends JFrame {
    public VentanaCrearUsuario() {
        setTitle("Menu Crear Usuario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));

        JCheckBox chkProfesor = new JCheckBox("Profesor");
        JLabel lblID = new JLabel("ID:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblCorreo = new JLabel("Correo:");
        JLabel lblContrasena = new JLabel("Contraseña:");
        JTextField txtID = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtCorreo = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();
        JButton btnCrear = new JButton("Crear");
        JButton btnVolver = new JButton("Volver");

        // Acción del botón volver
        btnVolver.addActionListener(e -> {
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            this.dispose();
        });
        btnCrear.addActionListener(e -> {
        	String id = txtID.getText();
        	String nombre = txtNombre.getText();
        	String correo = txtCorreo.getText();
        	String contrasena = String.valueOf(txtContrasena.getPassword());
        	if (chkProfesor.isSelected())
        		{
        		Profesor profesor = new Profesor (id,nombre,correo,contrasena,"profesor");
        		try {
    				ControladorUsuarios.getInstancia().registrarProfesor(id, nombre, correo, contrasena, "profesor");
    				JOptionPane.showMessageDialog(this, "Profesor guardado con exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
    			} catch (IdUsuarioYaExisteException e1) {
    				// TODO Auto-generated catch block
    				JOptionPane.showMessageDialog(this, "No se guardo correctamente el profesor", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
    			}
        		
        		}
        	else 
        	{
        		Estudiante estudiante = new Estudiante (id,nombre,correo,contrasena,"estudiante");
        		try {
    				ControladorUsuarios.getInstancia().registrarEstudiante(id, nombre, correo, contrasena, "estudiante");
    				JOptionPane.showMessageDialog(this, "Estudiante guardado con exito", "Exito!", JOptionPane.INFORMATION_MESSAGE);
    			} catch (IdUsuarioYaExisteException e1) {
    				// TODO Auto-generated catch block
    				JOptionPane.showMessageDialog(this, "No se guardo correctamente el estudiante ", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
    			}
        	}
        		
        	
			
        });

        // Añadir componentes
        
        add(lblID);
        add(txtID);
        add(lblNombre);
        add(txtNombre);
        add(lblCorreo);
        add(txtCorreo);
        add(lblContrasena);
        add(txtContrasena);
        add(chkProfesor);
        add(new JLabel(""));
        add(btnVolver);
        add(btnCrear);
        
        
    }
}
