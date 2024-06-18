package Controlador;

import Vista.*;
import java.awt.CardLayout;

/**
 *
 * @author Prieto
 */
public class ControladorPrincipal {
    
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
        
    }
    
    
    
}
