package Controlador;

import Modelo.Constantes;
import Modelo.Password;
import Vista.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Prieto
 */
public class ControladorPrincipal implements ActionListener, ChangeListener {

    private final VistaPrincipal vPrincipal;

    private final VPanelPrincipal vpPrincipal;

    private List<String> caracteres;

    private int longitud;

    public ControladorPrincipal() {
        vPrincipal = new VistaPrincipal();
        vpPrincipal = new VPanelPrincipal();

        caracteres = new ArrayList<>();

        addCopyToClipboardSettings();
        vpPrincipal.jLabelPasswordCopied.setVisible(false);
        addActionListeners();
        addChangeListeners();

        setComponentsVisibility(false);

        // ajustes del jframe:
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
        vpPrincipal.jButtonCopy.addActionListener(this);
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

    }

    /**
     *
     * @param aFlag true = muestra la contraseña generada, false en caso
     * contrario
     */
    private void setComponentsVisibility(boolean aFlag) {
        vpPrincipal.jLabelNoPass.setVisible(aFlag);
        vpPrincipal.jTextFieldPassword.setVisible(aFlag);
        vpPrincipal.jButtonCopy.setVisible(aFlag);
        vpPrincipal.revalidate();
    }

    private void addCopyToClipboardSettings() {
        try {
            vpPrincipal.jButtonCopy.setText("C");
            vpPrincipal.jButtonCopy.setMinimumSize(new Dimension(30, 30));
            BufferedImage imagen = ImageIO.read(new File("src/Vista/Imagenes/copy_icon.jpg"));
            ImageIcon icon = new ImageIcon(imagen.getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            vpPrincipal.jButtonCopy.setIcon(icon);

        } catch (IOException ex) {
            VistaMensaje.StaticMensaje(vPrincipal, "error", "Internal error", ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Exit" -> {
                vPrincipal.dispose();
                System.exit(0);
            }
            case "Generate password" -> {
                vpPrincipal.jLabelPasswordCopied.setVisible(false);
                try {
                    if (!caracteres.isEmpty()) {
                        String pass = Password.generatePassword(caracteres, longitud);
                        setComponentsVisibility(true);
                        vpPrincipal.jTextFieldPassword.setText(pass);

                    } else {
                        VistaMensaje.StaticMensaje(vPrincipal, "error", "Error", "None checkboxes were selected.");
                    }

                } catch (NoSuchAlgorithmException ex) {
                    VistaMensaje.StaticMensaje(vPrincipal, "error", "Internal error", "No algorithm found: "
                            + ex.getMessage());
                }

            }
            case "C" -> {
                String pass = vpPrincipal.jTextFieldPassword.getText();
                if (!pass.isBlank()) {
                    StringSelection selection = new StringSelection(pass);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(selection, selection);
                    vpPrincipal.jLabelPasswordCopied.setText("The generated password has been cut to the clipboard.");
                    vpPrincipal.jLabelPasswordCopied.setVisible(true);
                    vpPrincipal.repaint();

                } else {
                    VistaMensaje.StaticMensaje(vPrincipal, "error", "Error", "No password is generated.");
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
