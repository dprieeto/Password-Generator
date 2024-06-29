package Modelo;

/**
 * Esta clase incluye todos los tipos de caracteres que pueden ser usados para
 * generar una contraseña. No tiene metodos.
 * 
 * @see Constantes#MAYUSCULAS
 * @see Constantes#MINUSCULAS
 * @see Constantes#NUMEROS
 * @see Constantes#SIMBOLOS
 * @see Constantes#ESP_MAYUS
 * @see Constantes#ESP_MIN
 */
public class Constantes {

    /**
     * Alfabeto ingles en mayusculas.
     */
    public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    /**
     * Alfabeto ingles en minusculas.
     */
    public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    
    /**
     * Todos los numeros decimales.
     */
    public static final String NUMEROS = "0123456789";
    
    /**
     * Simbolos que se pueden utilizar.
     */
    public static final String SIMBOLOS = "!@#$€%^&*()_+-={}[]|:;<>,.?/";
    
    /**
     * Letras especiales en mayusculas, 'Ñ' y 'Ç'.
     */
    public static final String ESP_MAYUS = "ÑÇ";
    
    /**
     * Letras especiales en minusculas, 'ñ' y 'ç'.
     */
    public static final String ESP_MIN = "ñç";
}
