import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5 {
    // Method to generate MD5 hash of the input string
    public static String getMd5(String input) {
        try {
            // Get instance of MD5 hashing algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Calculate the message digest of the input string
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert the byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert the message digest into hex value
            String hashtext = no.toString(16);

            // Pad the hash value with leading zeros if necessary
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            // Throw a RuntimeException if MD5 algorithm is not available
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text to generate MD5 hash: ");
        String inputText = scanner.nextLine();

        // Close the scanner to release resources
        scanner.close();

        // Generate the MD5 hash for the input text
        String md5Hash = getMd5(inputText);

        // Print the generated MD5 hash
        System.out.println("MD5 Hash: " + md5Hash);
    }
}
