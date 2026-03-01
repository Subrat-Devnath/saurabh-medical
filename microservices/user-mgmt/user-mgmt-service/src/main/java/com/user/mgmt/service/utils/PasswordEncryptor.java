package com.user.mgmt.service.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptor {

	private static final int SALT_ROUND_LIMIT = 10;
	private static PasswordEncryptor instance;

	@Value("${app.master-salt}")
	private String masterSalt;

	private PasswordEncryptor() {
	}

	public String encrypt(String inputPass, String salt) throws NoSuchAlgorithmException {
		String password = encryptWithSHA256AndSalt(inputPass, salt);
		return encryptWithSHA256AndSalt(password, masterSalt);
	}

	public String encryptWithSHA256AndSalt(String inputText, String salt) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		StringBuilder passwordHash = new StringBuilder();
		StringBuilder saltpass = new StringBuilder(inputText);
		int i = 1;
		do {
			saltpass.append(salt);
			i++;
		} while (i <= SALT_ROUND_LIMIT);

		mDigest.update(salt.getBytes());
		byte[] result = mDigest.digest(inputText.getBytes());

		for (byte eachByte : result) {
			passwordHash.append(String.valueOf(eachByte));
		}

		return String.valueOf(passwordHash);
	}

	public String encryptText(String plaintext) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
		try {
			md.update(plaintext.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
		byte raw[] = md.digest();
		String hash = Base64.getEncoder().encodeToString(raw);
		return hash;

	}

	public String encryptWithSha256(String plaintext) throws Exception {
		// code for encripting otp with SHA-1
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			MessageDigest mDigest = md;
			byte[] result = mDigest.digest(plaintext.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}

			return sb.toString();
		} catch (Exception e) {
			throw e;
		}

	}

	public static PasswordEncryptor getInstance() {
		if (instance == null) {

			synchronized (PasswordEncryptor.class) {
				if (instance == null)
					return new PasswordEncryptor();
				return instance;
			}

		} else {
			return instance;
		}
	}

}
