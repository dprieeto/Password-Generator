package Modelo;

import java.security.AuthProvider;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author Prieto
 */
public class Password {

    /**
     * Crea una contraseña dada una lista de caracteres a utilizar y una
     * longitud.
     *
     * @param caracteres tipos de caracteres que utiliza
     * @param longitud longitud de la contraseña
     * @return la contraseña generada
     * @throws NoSuchAlgorithmException
     * @see Constantes
     */
    public static String generatePassword(List<String> caracteres, int longitud) throws NoSuchAlgorithmException {
        StringBuilder password = new StringBuilder();

        //SecureRandom random = SecureRandom.getInstance("NativePRNG"); // utiliza ese algoritmo para la creacion de numeros aleatorios
        SecureRandom random = SecureRandom.getInstance("Windows-PRNG");
        byte[] seed = random.generateSeed(32); // crea la semilla con 256 bits
        random.setSeed(seed);

        for (int i = 0; i < longitud; i++) {

            int tipoCaracter = random.nextInt(caracteres.size());
            int index = random.nextInt(caracteres.get(tipoCaracter).length());

            password.append(caracteres.get(tipoCaracter).charAt(index));

        }

        return password.toString();
    }
}
