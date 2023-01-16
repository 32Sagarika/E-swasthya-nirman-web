package nirmalya.aathithya.webmodule.util;

import java.util.Random;

public class GenerateRandomValue {

	static Random r = new Random();

	static char[] choices = ("abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "01234567890" + "$#_")
			.toCharArray();

	public static String generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}

	public static String generateAlphanumericWithSpecialCharacter(int len) {
		StringBuilder salt = new StringBuilder(len);
		for (int i = 0; i < len; ++i)
			salt.append(choices[r.nextInt(choices.length)]);
		return salt.toString();
	}

}
