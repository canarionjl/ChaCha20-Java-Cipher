package cipher;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JButton;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ChaCha20Generator {

	ChaCha20 cipher;
	SecretKey key;
	byte[] nonce;
	int counter;
	

	public ChaCha20Generator() {
		cipher = new ChaCha20();
		try {
			 key = getKey();
			
		} catch (NoSuchAlgorithmException e) {
			
		}
		nonce = getNonce();
	    counter = 1;

	}

	public byte[] encrypt(byte[] input) {
		byte[] cText;
		try {
			cText = cipher.encrypt(input, key, nonce, counter);
		} catch (Exception e) {
			return null;
		}
		return cText;
	}
	
	public byte[] decrypt(byte[] input) {
		byte[] pText;
		
		try {
			 pText = cipher.decrypt(input, key, nonce, counter);
		} catch (Exception e) {
			return null;
		}
		nonce = getNonce();
		return pText;
	}

	private static SecretKey getKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
		keyGen.init(256, SecureRandom.getInstanceStrong());
		return keyGen.generateKey();
	}
	
	public String getHexKey() {
		return convertBytesToHex(key.getEncoded());
	}
	
	public String getHexNonce() {
		return convertBytesToHex(nonce);
	}

	private static byte[] getNonce() {
		byte[] newNonce = new byte[12];
		new SecureRandom().nextBytes(newNonce);
		return newNonce;
	}

	public static String convertBytesToHex(byte[] bytes) {
		StringBuilder result = new StringBuilder();
		for (byte temp : bytes) {
			result.append(String.format("%02x", temp));
		}
		return result.toString();
	}
	
}