package interfaz;

import javax.swing.*;

import usuario.ControladorUsuarios;
import usuario.Estudiante;
import usuario.Profesor;

import java.awt.*;

@SuppressWarnings("serial")
public class VentanaEditarPerfilProfesor extends JFrame {

    public VentanaEditarPerfilProfesor(Profesor profesor, MenuProfesor menu) {
        setTitle("Editar Perfil");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); 


        JLabel lblOpcion = new JLabel("Seleccione qué desea cambiar:");
        JRadioButton rbCambiarCorreo = new JRadioButton("Cambiar Correo");
        JRadioButton rbCambiarContrasena = new JRadioButton("Cambiar Contraseña");
        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(rbCambiarCorreo);
        grupoOpciones.add(rbCambiarContrasena);

        JLabel lblNuevoDato = new JLabel("Ingrese el nuevo dato:");
        JTextField txtNuevoDato = new JTextField();

        JButton btnGuardarCambios = new JButton("Guardar Cambios");
        JButton btnVolver = new JButton("Volver");


        btnGuardarCambios.addActionListener(e -> {
            if (rbCambiarCorreo.isSelected()) {
                String nuevoCorreo = txtNuevoDato.getText().trim();
                if (!nuevoCorreo.isEmpty()) {
                    ControladorUsuarios.getInstancia().editarPerfil(profesor.getId(), profesor.getNombre(), nuevoCorreo);
                    JOptionPane.showMessageDialog(this, "Correo actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (rbCambiarContrasena.isSelected()) {
                String nuevaContrasena = txtNuevoDato.getText().trim();
                if (!nuevaContrasena.isEmpty()) {
                	ControladorUsuarios.getInstancia().editarPerfil(profesor.getId(), profesor.getNombre(), nuevaContrasena);
                    JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese una contraseña válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una opción antes de guardar.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        
        });

        btnVolver.addActionListener(e -> {
            this.dispose(); 
            menu.setVisible(true);
        });

        
        add(lblOpcion);
        add(new JLabel()); 
        add(rbCambiarCorreo);
        add(rbCambiarContrasena);
        add(lblNuevoDato);
        add(txtNuevoDato);
        add(btnGuardarCambios);
        add(btnVolver);
    }
}