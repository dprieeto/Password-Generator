package Controlador;

import Vista.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Prieto
 */
public class ControladorPrincipal implements ActionListener{
    
    private VistaPrincipal vPrincipal;
    
    private VPanelPrincipal vpPrincipal;

    public ControladorPrincipal() {
        vPrincipal = new VistaPrincipal();
        vpPrincipal = new VPanelPrincipal();
        
        addListeners();
        vPrincipal.setLocationRelativeTo(null);//situa la ventana en el centro de la pantalla
        vPrincipal.setVisible(true);//muestra la ventana
        //vprincipal.setPreferredSize(new Dimension(970,400));
        vPrincipal.getContentPane().setLayout(new CardLayout());//Layout CardLayout para poder tener 
                                                                //mas de un panel en la misma posicion
        
        vPrincipal.add(vpPrincipal);
        vPrincipal.setVisible(true);
    }
    
    private void addListeners() {
        vPrincipal.jMenuFileExit.addActionListener(this);
        vPrincipal.jMenuFileNew.addActionListener(this);
        vPrincipal.jMenuFileOpen.addActionListener(this);
        vPrincipal.jMenuFileSave.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Exit" -> {
                
                // mensaje de salir sin guardar
                vPrincipal.dispose();
                System.exit(0);
            }
            case "New" -> {
                
            }
            case "Open" -> {
                
            }
            case "Save" -> {
                
            }
        }
    }
    
    
    
}
