package org.hawk.cryption;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.hawk.os.HawkException;

/**
 * DES加密算法封装
 * 
 * @author hawk
 */
public class HawkDESCrypt {
	/**
	 * 加密对象
	 */
	Cipher cipher;
	/**
	 * 密钥
	 */
	KeyGenerator keyGenerator;
	/**
	 * 密钥长度
	 */
	static final int KEY_LENGTH = 56;

	/**
	 * 初始化, 秘钥长度56
	 * 
	 * @param secretKey
	 * @param encrypt
	 * @return
	 */
	public boolean init(byte[] secretKey, boolean encrypt) {
		try {
			if (secretKey != null) {
				if (secretKey.length > KEY_LENGTH) {
					return false;
				}
				keyGenerator = KeyGenerator.getInstance("DES");
				keyGenerator.init(KEY_LENGTH, new SecureRandom(secretKey));
				SecretKey key = keyGenerator.generateKey();
				cipher = Cipher.getInstance("DES");
				cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, key);
				return true;
			}
		} catch (Exception e) {
			HawkException.catchException(e);
		}
		return false;
	}

	/**
	 * 加解密
	 * 
	 * @param input
	 * @return
	 */
	public byte[] digit(byte[] input) {
		try {
			byte[] output = cipher.doFinal(input);
			return output;
		} catch (Exception e) {
			HawkException.catchException(e);
		}
		return null;
	}
}
