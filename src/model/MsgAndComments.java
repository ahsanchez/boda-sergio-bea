package model;

import java.util.concurrent.ThreadLocalRandom;

public class MsgAndComments {
	private static final String[] RIGHT_ANSWERS = { "¡Respuesta correcta!", "¡Correcto!", "¡Maravilloso!",
			"¡Genial! ¡Sois unos cracks!", "¡Bien! ¡Qué memoria!", "¡Habéis acertado!",
			"¡Muy bien! ¡Se os da bien esto!" };
	private static final String[] WRONG_ANSWERS = { "¡Respuesta incorrecta!", "¡Oh, oh.. habéis fallado!",
			"¡No! ¿Cómo habéis podido fallar eso?", "¡Fallo! ¡Si era super fácil!", "¡Errooooorrrrr!",
			"¡Ouch! ¡Una vida menos!" };

	public static final String ENHORABUENA = "\r\n"
			+ " _  _______         _                           _                                  _ \r\n"
			+ "| |(_______)       | |                         | |                                | |\r\n"
			+ "| | _____    ____  | |__    ___    ____  _____ | |__   _   _  _____  ____   _____ | |\r\n"
			+ "|_||  ___)  |  _ \\ |  _ \\  / _ \\  / ___)(____ ||  _ \\ | | | || ___ ||  _ \\ (____ ||_|\r\n"
			+ " _ | |_____ | | | || | | || |_| || |    / ___ || |_) )| |_| || ____|| | | |/ ___ | _ \r\n"
			+ "|_||_______)|_| |_||_| |_| \\___/ |_|    \\_____||____/ |____/ |_____)|_| |_|\\_____||_|\r\n"
			+ "                                                                                     \r\n";

	public static final String RIGHT_COMBINATION = "\r\n" + "  _____   ____   ______ \r\n"
			+ " / ___ \\ / __ \\ / __   |\r\n" + "( (   ) | (__) ) | //| |\r\n" + " > > < < \\__  /| |// | |\r\n"
			+ "( (___) )  / / |  /__| |\r\n" + " \\_____/  /_/   \\_____/ \r\n" + "                        \r\n" + "";

	public static String getRandomMsg(int type) {
		if (type == 1) {
			return RIGHT_ANSWERS[ThreadLocalRandom.current().nextInt(1, RIGHT_ANSWERS.length)];
		} else if (type == 2) {
			return WRONG_ANSWERS[ThreadLocalRandom.current().nextInt(1, WRONG_ANSWERS.length)];
		}
		return "";
	}
}
