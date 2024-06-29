package Controlador;

import Modelo.Constantes;
import Modelo.Password;
import Vista.*;
import java.awt.CardLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Prieto
 */
public class ControladorPrincipal implements ActionListener, ChangeListener {

    private VistaPrincipal vPrincipal;

    private VPanelPrincipal vpPrincipal;

    private List<JCheckBox> checkboxes; // necesario?? crro q no

    private List<String> caracteres;

    private int longitud;

    public ControladorPrincipal() {
        vPrincipal = new VistaPrincipal();
        vpPrincipal = new VPanelPrincipal();

        caracteres = new ArrayList<>();

        addActionListeners();
        addChangeListeners();

        vPrincipal.setLocationRelativeTo(null);//situa la ventana en el centro de la pantalla
        vPrincipal.setVisible(true);//muestra la ventana
        //vprincipal.setPreferredSize(new Dimension(970,400));
        vPrincipal.getContentPane().setLayout(new CardLayout());//Layout CardLayout para poder tener 
        //mas de un panel en la misma posicion

        vPrincipal.add(vpPrincipal);
        vPrincipal.setVisible(true);
    }

    /**
     * Añade los listeners del action performed
     */
    private void addActionListeners() {
        vPrincipal.jMenuFileExit.addActionListener(this);

        vpPrincipal.jButtonCrearPass.addActionListener(this);
    }

    /**
     * Añade los listeners del change listener
     */
    private void addChangeListeners() {
        vpPrincipal.jCheckBoxCaracterEspecialMayusculas.addChangeListener(this);
        vpPrincipal.jCheckBoxCaracterEspecialMinusculas.addChangeListener(this);
        vpPrincipal.jCheckBoxMayusculas.addChangeListener(this);
        vpPrincipal.jCheckBoxMinusculas.addChangeListener(this);
        vpPrincipal.jCheckBoxNumeros.addChangeListener(this);
        vpPrincipal.jCheckBoxSimbolos.addChangeListener(this);

        vpPrincipal.jSliderLongitudPass.addChangeListener(this);
        longitud = vpPrincipal.jSliderLongitudPass.getValue();
        vpPrincipal.jLabelLongitudPass.setText("Password lenght: " + longitud);
        vpPrincipal.jLabelPassword.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit" -> {

                // mensaje de salir sin guardar
                vPrincipal.dispose();
                System.exit(0);
            }
            case "Generate password" -> {
                try {
                    if (!caracteres.isEmpty()) {
                        String pass = Password.generatePassword(caracteres, longitud);
                        vpPrincipal.jLabelPassword.setText(pass);
                    } else {
                        VistaMensaje.StaticMensaje(vPrincipal, "error", "Error", "None checkboxes were selected.");
                    }

                } catch (NoSuchAlgorithmException ex) {
                    VistaMensaje.StaticMensaje(vPrincipal, "error", "Internal error", "No algorithm found: "
                            + ex.getMessage());
                }

            }

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        if (e.getSource() instanceof JCheckBox) {
            //caracteres.removeAll(caracteres);
            caracteres = new ArrayList<>();
            if (vpPrincipal.jCheckBoxCaracterEspecialMayusculas.isSelected()) {
                caracteres.add(Constantes.ESP_MAYUS);
            }
            if (vpPrincipal.jCheckBoxCaracterEspecialMinusculas.isSelected()) {
                caracteres.add(Constantes.ESP_MIN);
            }
            if (vpPrincipal.jCheckBoxMayusculas.isSelected()) {
                caracteres.add(Constantes.MAYUSCULAS);
            }
            if (vpPrincipal.jCheckBoxMinusculas.isSelected()) {
                caracteres.add(Constantes.MINUSCULAS);
            }
            if (vpPrincipal.jCheckBoxNumeros.isSelected()) {
                caracteres.add(Constantes.NUMEROS);
            }
            if (vpPrincipal.jCheckBoxSimbolos.isSelected()) {
                caracteres.add(Constantes.SIMBOLOS);
            }

        } else {
            longitud = vpPrincipal.jSliderLongitudPass.getValue();
            vpPrincipal.jLabelLongitudPass.setText("Password lenght: " + longitud);
        }
    }
}
