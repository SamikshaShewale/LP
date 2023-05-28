import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {
    // Method to encrypt plaintext using AES encryption with a given key
    public static String encryptAES(String plaintext, String key) throws Exception {
        // Create a SecretKeySpec object using the provided key and AES algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Create a Cipher object with AES/ECB/PKCS5Padding transformation
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Initialize the Cipher object in encrypt mode with the secret key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Perform the encryption on the plaintext and store the encrypted bytes
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        // Encode the encrypted bytes using Base64 encoding and return the ciphertext
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt ciphertext using AES decryption with a given key
    public static String decryptAES(String ciphertext, String key) throws Exception {
        // Create a SecretKeySpec object using the provided key and AES algorithm
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");

        // Create a Cipher object with AES/ECB/PKCS5Padding transformation
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Initialize the Cipher object in decrypt mode with the secret key
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the Base64 encoded ciphertext into bytes
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);

        // Perform the decryption on the ciphertext bytes and store the decrypted bytes
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        // Convert the decrypted bytes to a string and return the plaintext
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the plaintext: ");
            String plaintext = scanner.nextLine();

            System.out.print("Enter the encryption key (16 characters): ");
            String key = scanner.nextLine();

            // Encrypt the plaintext using AES encryption
            String ciphertext = encryptAES(plaintext, key);

            // Decrypt the ciphertext using AES decryption
            String decrypted = decryptAES(ciphertext, key);

            // Print the original plaintext, ciphertext, and decrypted text
            System.out.println("Plaintext: " + plaintext);
            System.out.println("Ciphertext: " + ciphertext);
            System.out.println("Decrypted: " + decrypted);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
