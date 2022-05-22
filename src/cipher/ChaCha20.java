
package cipher;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;


public class ChaCha20 {

    private static final String CYPHER_ALGORITHM = "ChaCha20"; 
    
    public byte[] encrypt(byte[] plaintext, SecretKey key, byte[] nonce, int counter) throws Exception {

        Cipher cipher = Cipher.getInstance(CYPHER_ALGORITHM); 

        ChaCha20ParameterSpec ChaChaParameters = new ChaCha20ParameterSpec(nonce,counter); 
        cipher.init(Cipher.ENCRYPT_MODE, key, ChaChaParameters);

        byte[] encryptedText = cipher.doFinal(plaintext);

        return encryptedText;
    }

    public byte[] decrypt(byte[] cyphertext, SecretKey key, byte[] nonce, int counter) throws Exception {

        Cipher cipher = Cipher.getInstance(CYPHER_ALGORITHM);

        ChaCha20ParameterSpec ChaChaParameters = new ChaCha20ParameterSpec(nonce, counter);

        cipher.init(Cipher.DECRYPT_MODE, key, ChaChaParameters);

        byte[] decryptedText = cipher.doFinal(cyphertext);

        return decryptedText;

    }

}