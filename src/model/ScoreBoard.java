package model;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javazoom.jl.decoder.JavaLayerException;
import scenario.ScreenPlay;
import utils.ReproductorMp3;

public class ScoreBoard {

	public static int lives = 2;

	private static String GAME_OVER = "\r\n"
			+ "               ('-.     _   .-')       ('-.                           (`-.      ('-.  _  .-')   \r\n"
			+ "              ( OO ).-.( '.( OO )_   _(  OO)                        _(OO  )_  _(  OO)( \\( -O )  \r\n"
			+ "  ,----.      / . --. / ,--.   ,--.)(,------.       .-'),-----. ,--(_/   ,. \\(,------.,------.  \r\n"
			+ " '  .-./-')   | \\-.  \\  |   `.'   |  |  .---'      ( OO'  .-.  '\\   \\   /(__/ |  .---'|   /`. ' \r\n"
			+ " |  |_( O- ).-'-'  |  | |         |  |  |          /   |  | |  | \\   \\ /   /  |  |    |  /  | | \r\n"
			+ " |  | .--, \\ \\| |_.'  | |  |'.'|  | (|  '--.       \\_) |  |\\|  |  \\   '   /, (|  '--. |  |_.' | \r\n"
			+ "(|  | '. (_/  |  .-.  | |  |   |  |  |  .--'         \\ |  | |  |   \\     /__) |  .--' |  .  '.' \r\n"
			+ " |  '--'  |   |  | |  | |  |   |  |  |  `---.         `'  '-'  '    \\   /     |  `---.|  |\\  \\  \r\n"
			+ "  `------'    `--' `--' `--'   `--'  `------'           `-----'      `-'      `------'`--' '--' \r\n";

	public static final String _0 = " \r\n" + "  ______ \r\n" + " / __   |  /` \\/ `\\\r\n" + "| | //| |  \\      /\r\n"
			+ "| |// | |   '.  .'\r\n" + " \\_____/      \\/\r\n" + "         ";
	public static final String _1 = "\r\n" + "  __ \r\n" + " /  |  /` \\/ `\\\r\n" + "/_/ |  \\      /\r\n"
			+ "  | |   '.  .'\r\n" + "  |_|     \\/\r\n" + "";

	public static final String HEART = "  ,d88b.d88b,\r\n" + "  88888888888\r\n" + "  `Y8888888Y'\r\n"
			+ "    `Y888Y'\r\n" + "      `Y'";

	private static final Map<Integer, String> asciiArt;
	static {
		Map<Integer, String> aMap = new HashMap<>();
		aMap.put(2, _1);
		aMap.put(1, "");
		asciiArt = Collections.unmodifiableMap(aMap);
	}

	public static void getScoreBoard() throws FileNotFoundException, JavaLayerException, InterruptedException {
		if (lives == 2)
			System.out.println(asciiArt.get(lives) + "Tenéis 1 vida.");
		else if (lives == 1)
			System.out.println(asciiArt.get(lives) + "Ya no tenéis vidas.");
		else if (lives == 0) {
			System.out.println(GAME_OVER);
			ScreenPlay.player2.stop();
			ReproductorMp3 player3 = new ReproductorMp3();
			player3.play("go");
			Thread.sleep(5000);
			System.exit(-1);

		}
		System.out.println();
	}
}
