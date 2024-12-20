package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import constructores.ConstructorLearningPath;
import exceptions.ProfesorNoCreadorException;
import learningPaths.LearningPath;

public class VentanaEditarLP extends JFrame {
	private JComboBox<String> cmbAtributo;
	private JTextField txtValorNuevo;
	private JButton btnGuardar;
	private JButton btnSalir;
	

	public VentanaEditarLP(LearningPath lp, String idProfesor, MenuProfesor menu) {
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        setTitle("Editar Learning Path");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Atributo a cambiar:"));
        cmbAtributo = new JComboBox<>(new String[]{
            "Titulo",
            "DescripcionGeneral",
            "NivelDificultad",
            "FechaDuracion",
            "FechaModificacion",
            "Objetivos"
        });
        add(cmbAtributo);

        add(new JLabel("Nuevo valor:"));
        txtValorNuevo = new JTextField();
        add(txtValorNuevo);

        btnGuardar = new JButton("Guardar Cambios");
        add(btnGuardar);

        btnSalir = new JButton("Salir");
        add(btnSalir);

        btnGuardar.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                String atributo = (String) cmbAtributo.getSelectedItem();
                String valorNuevoStr = txtValorNuevo.getText();
                Object valorNuevo = null;

                try {
                	ConstructorLearningPath constructor = new ConstructorLearningPath();
                    if(atributo.equals("Titulo")|| atributo.equals("DescripcionGeneral") || atributo.equals("Objetivos") )
                    {
                            valorNuevo = valorNuevoStr;
                            constructor.editarLP(lp, atributo, valorNuevo, idProfesor);
                            JOptionPane.showMessageDialog(VentanaEditarLP.this, "Atributo actualizado con éxito.");
                            dispose();
                            menu.setVisible(true);
                    }
                    else if (atributo.equals("NivelDificultad"))
                    {
                            Integer temporal = Integer.parseInt(valorNuevoStr);
                            constructor.editarLP(lp, atributo, temporal, idProfesor);
                            JOptionPane.showMessageDialog(VentanaEditarLP.this, "Atributo actualizado con éxito.");
                            dispose();
                            menu.setVisible(true);
                            
                    }
                    else if (atributo.equals("FechaDuracion") || atributo.equals("FechaModificacion") )
                    {
                    	Date temporal = formato.parse(valorNuevoStr); 
                    	constructor.editarLP(lp, atributo, temporal, idProfesor);
                    	 JOptionPane.showMessageDialog(VentanaEditarLP.this, "Atributo actualizado con éxito.");
                         dispose();
                         menu.setVisible(true);
                    }


                 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaEditarLP.this, "El valor ingresado no es válido para el atributo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ProfesorNoCreadorException ex) {
                    JOptionPane.showMessageDialog(VentanaEditarLP.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaEditarLP.this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

	btnSalir.addActionListener(e->dispose());menu.setVisible(true);}
}
