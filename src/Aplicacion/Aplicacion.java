package Aplicacion;

import Controlador.ControladorPrincipal;
import Vista.VistaMensaje;
import java.util.Calendar;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

/**
 *
 * @author Prieto
 */
public class Aplicacion {
    public static void main(String[] args) {
        LightMode();
        init();
        
    }
    
    /** Inicia la aplicacion al acceso de inicio de usuario.
     * 
     */
    @SuppressWarnings("unchecked")
    private static void init() {
        new ControladorPrincipal();
    }
    
    /** Modo nocturno/diurno
     * Cambia el diseño de la interfaz de la aplicacion dependiendo de la hora 
     * actual.
     * Modo diurno: 08:00-20:00
     * Modo nocturno: 20:01-07:59 
     */
    private static void LightMode() {
        VistaMensaje vmensaje = new VistaMensaje();
        Calendar calendario = Calendar.getInstance();
        int hora = calendario.get(Calendar.HOUR_OF_DAY);//obtiene la hora en formtao 24h
        //System.out.println(hora);
        try {//modifica el aspecto de la interfaz; bordes, fondo, colores...
            if(hora>8 || hora<20)
                UIManager.setLookAndFeel( new NimbusLookAndFeel());
            if(hora<8 || hora>20)
                UIManager.setLookAndFeel( new MetalLookAndFeel());
        } catch(UnsupportedLookAndFeelException ex) {
            //System.err.println( "Mensaje de error main UIMAnager:\n" + ex.getMessage());
            vmensaje.Mensaje("error", "Se ha producido un error con UIManager.setLookAndFell:\n"
                    + ex.getMessage(), 0);
        }        
    }    
}
