import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class hashPassword {
    
    final String PASS;
    final int hashIterations = 10000;

    public hashPassword(String password) {
        this.PASS = password;
    }

    public String getHashed() {

        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        String saltEncoded = Base64.getEncoder().encodeToString(saltBytes);

        String hashEncoded = runPdkdf2(saltBytes, PASS);

        return saltEncoded + ":" + hashEncoded + ':' + hashIterations;
    }

    public boolean verify(String stored) {
        try {
            String[] parts = stored.split(":");
            String saltEncoded = parts[0];
            String hashEncoded = parts[1];

            byte[] saltBytes = Base64.getDecoder().decode(saltEncoded);

            String newHash = runPdkdf2(saltBytes, PASS);

            return MessageDigest.isEqual(Base64.getDecoder().decode(newHash), Base64.getDecoder().decode(hashEncoded));

        } catch (Exception e) {

            return false;

        }
    }

    private String runPdkdf2(byte[] saltBytes, String password) {

        int iterations = hashIterations;
        int keyLength = 256;

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, keyLength);

        try {

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hashBytes = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashBytes);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Failed to hash password  -> \n" + e);
        } finally {
            spec.clearPassword();
        }


    }

    

}
