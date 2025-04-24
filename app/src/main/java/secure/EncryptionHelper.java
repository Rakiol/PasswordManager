package secure;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import data.*;

public class EncryptionHelper {

    //mit gpt entwickelt, da ich es noch nicht so kann

    private static SecretKeySpec createKey(String myKey) throws Exception {
        byte[] key = myKey.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16); // AES key = 16 bytes
        return new SecretKeySpec(key, "AES");
    }

    public static String encrypt(String strToEncrypt, String secret) throws Exception {
        SecretKeySpec secretKey = createKey(secret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String decrypt(String strToDecrypt, String secret) throws Exception {
        SecretKeySpec secretKey = createKey(secret);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decoded = Base64.getDecoder().decode(strToDecrypt);
        return new String(cipher.doFinal(decoded));
    }
}

