package luisrrleal.com.foodapp_v1.helpers;
import android.os.Build;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHashingWithSalt {
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    // Método para generar una sal aleatoria
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    // Método para encriptar una contraseña con salting y hashing
    public static String hashPassword(String password) {
        try {
            byte[] salt = generateSalt();
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Concatenar la sal y el hash para almacenarlos juntos
            byte[] saltedHash = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, saltedHash, 0, salt.length);
            System.arraycopy(hash, 0, saltedHash, salt.length, hash.length);

            // Devolver el resultado en Base64
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(saltedHash);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // Método para verificar si una contraseña coincide con la encriptación almacenada
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            byte[] saltedHash = new byte[0];
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                saltedHash = Base64.getDecoder().decode(storedHash);
            }

            // Extraer la sal y el hash almacenados
            byte[] salt = new byte[16];
            byte[] hash = new byte[saltedHash.length - salt.length];
            System.arraycopy(saltedHash, 0, salt, 0, salt.length);
            System.arraycopy(saltedHash, salt.length, hash, 0, hash.length);

            // Generar el hash de la contraseña proporcionada con la misma sal y verificar si coincide con el almacenado
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            // Comparar los hashes
            return hash.equals(testHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

