package scenario;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import constants.CommonConstants;
import encoder.EncryptDecryptStringWithDES;
import encoder.Hashing;
import javazoom.jl.decoder.JavaLayerException;
import model.MsgAndComments;
import model.PwdFractionModel;
import model.Question;
import model.ScoreBoard;
import utils.FileUtils;
import utils.ReproductorMp3;
import utils.Utils;

public class ScreenPlay {

	private static final String[] SI = { "si", "sí", "ye", "vale", "ok", "clarisimo", "clarísimo", "claro" };
	private static final String[] NO = { "no", "negativo", "nada" };
	private static final String[] BIEN = { "bien", "genial", "perfect", "felices" };
	private static final String[] MAL = { "mal", "regular", "no muy bien", "nada bien" };

	public static ReproductorMp3 player2 = new ReproductorMp3();
	public static ReproductorMp3 player1 = new ReproductorMp3();
	public static Map<Integer, PwdFractionModel> passwordMap;

	public static void init() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		passwordMap = new HashMap<>();

		String pwd = FileUtils.readLineByLineJava8("questions/pencrypted").replace("\n", "");
		pwd = EncryptDecryptStringWithDES.decrypt(pwd);
		String[] lines = pwd.split("\n");
		String password = lines[0];
		String blocks = lines[1];

		String passwordSplit[] = password.split(";");
		String blocksSplit[] = blocks.split(";");
		for (int i = 0; i < passwordSplit.length; i++) {
			passwordMap.put(Integer.parseInt(blocksSplit[i]),
					new PwdFractionModel(Integer.parseInt(passwordSplit[i]), i + 1));
		}

	}

	public static void advertisment() throws InterruptedException {

		System.out.println("================================== ADVERTENCIA ==================================");
		System.out.println("Este juego puede ser adictivo.");
		System.out.println("Se recomienda un uso responsable.");
		System.out.println("No mezclar con bebidas alcohólicas.");
		System.out.println("ES ABSOLUTAMENTE IMPRESCINDIBLE ACIVAR EL SONIDO DEL ORDENADOR.");
		System.out.println("================================ FIN ADVERTENCIA =================================");
		Thread.sleep(5000);
	}

	public static void rules() throws InterruptedException {
		System.out.println();
		Utils.writeConsole("A continuación se detallan las normas del juego. "
				+ "Prestad atención porque sólo las repetiremos una vez: ", true, 3);

		Utils.writeConsole("1. Debéis responder con mucha precisión lo que se os pregunte.", true, 1);
		Utils.writeConsole("2. Cada fallo será penalizado.", true, 1);
		Utils.writeConsole("3. Disponéis de una vida por bloque. Si os quedáis sin vidas, volvéis a empezar.", true, 1);
		Utils.writeConsole("4. El juego es interactivo. Si se os hace una pregunta, responded.", true, 1);
		Utils.writeConsole("5. En el momento que sepáis la contraseña escribid: QUEREMOS RESOLVER.", true, 10);
	}

	public static void previousQuestions() throws InterruptedException, FileNotFoundException, JavaLayerException {
		System.out.println("");
		Utils.writeConsole("Bien. ¿Todo claro?", true, 1);
		String res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res) && (!res.toLowerCase().startsWith("no"))
				&& (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("Chicos listos.", true, 1);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("Pues bien empezamos igualmente...", true, 1);
		} else {
			Utils.dontUnderstand();
		}

		System.out.println("");

		Utils.writeConsole("¿Habéis activado el sonido del ordenador?", true, 1);
		res = Utils.read().toLowerCase();
		if (Utils.arrayContainsWord(SI, res) && (!res.toLowerCase().startsWith("no"))
				&& (!res.toLowerCase().startsWith("nada"))) {
			Utils.writeConsole("¡Genial! Pues...", false, 3);
		} else if (Utils.arrayContainsWord(NO, res)) {
			Utils.writeConsole("¿Y a qué estáis esperando? Os doy 5 segundos para hacerlo:", true, 1);
			Utils.counterDown(5);
			Utils.writeConsole("¡Estupendo! Pues...", false, 3);
		} else {
			Utils.dontUnderstand();
		}
	}

	public static int presentation() throws InterruptedException, FileNotFoundException, JavaLayerException {

//		Utils.writeConsole(" ¡¡¡Que comience el espectáculo!!!", true, 1);
//
//		player1 = new ReproductorMp3();
//		player1.play("dll1");
//
//		Thread.sleep(3000);
//		Utils.insertNewLine(3);
//
//		Utils.writeConsole("¡Hoy tenemos aquí a Sergio y Bea!", true, 3);
//		Utils.writeConsole(
//				"Esta pareja se ha casado recientemente y están aquí " + "para conseguir el espigo de sus amigos.",
//				true, 3);
//		Utils.writeConsole("¿Qué tal estáis, Sergio y Bea?", true, 0);
//
//		String res = Utils.read();
//		String msg = "";
//		if (Utils.arrayContainsWord(BIEN, res) && (!res.toLowerCase().startsWith("no"))
//				&& (!res.toLowerCase().startsWith("nada"))) {
//			msg = "¿" + Utils.firstToUpper(res) + "? Me alegro. Yo también, gracias.";
//		} else if (Utils.arrayContainsWord(MAL, res)) {
//			msg = "Vaya, siento oír eso, pero... vamos a seguir el juego igual.";
//		} else if (res.contains("nervios")) {
//			msg = "¡No estéis nerviosos! Estamos aquí para divertirnos.";
//		} else {
//			Utils.dontUnderstand();
//		}
//		Utils.writeConsole(msg, true, 1.5);
//
//		Utils.writeConsole(
//				"Como ellos ya saben, su espigo ha sido depositado en una caja fuerte con un candado de combinación. \n"
//						+ "El objetivo de este concurso es que consigan esa combinación. "
//						+ "Para ello deberán contestar correctamente una serie de preguntas.",
//				true, 6);
//
//		Utils.writeConsole(
//				"Estas preguntas están agrupadas en distintos bloques. Cada bloque les dará un número. "
//						+ "Cuando hayan superado todos los bloques, tendrán que ordernarlos y formar una contraseña.",
//				true, 7);
//
//		Utils.writeConsole(
//				"Deberán introducir dicha contraseña para obtener la combinación de la caja fuerte y ¡CONSEGUIR SU PREMIO!",
//				true, 4);
//
//		Utils.writeConsole("Pero ¡ojo!, sólo podrán fallar una respuesta por bloque."
//				+ " Si fallan dos... tendrán que volver a empezar.", true, 5);
//
//		Utils.writeConsole(
//				"Podéis interrumpirme en cualquier momento para decirme la constraseña. Sólo tenéis que decirme: QUEREMOS RESOLVER.",
//				true, 5);
//
//		Utils.writeConsole("¿Estáis preparados, Sergio y Bea?", true, 0);
//
//		res = Utils.read();
//		if (Utils.arrayContainsWord(SI, res) || res.toLowerCase().contains("preparad")) {
//			Utils.writeConsole("¡Eso es lo que quería oír. Allá vamos!", true, 5);
//		} else if (Utils.arrayContainsWord(NO, res)) {
//			Utils.writeConsole("Mala suerte porque...", true, 5);
//		} else {
//			Utils.dontUnderstand();
//		}
//
//		Utils.insertNewLine(2);
		int option = showBlockQuestions(true);
		player1.stop();
		player2 = new ReproductorMp3();
		return option;

	}

	public static void quiz(int block) throws InterruptedException, FileNotFoundException, JavaLayerException,
			InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {

		player2.play("dll2");
		Set<Question> questions = Questions.getQuestions(block);
		for (Question q : questions) {
			q.makeQuestion();
			String resp = Utils.read();
			if (q.checkAnswer(resp)) {
				System.out.println(MsgAndComments.getRandomMsg(1));
			} else {
				System.out.println(MsgAndComments.getRandomMsg(2));
				ScoreBoard.lives--;
			}
			Thread.sleep(1000);
			ScoreBoard.getScoreBoard();
			Thread.sleep(3000);

		}
		blockResolved(block);

	}

	public static void combination() throws InterruptedException, FileNotFoundException, JavaLayerException {
		player2.stop();
		ReproductorMp3 player3 = new ReproductorMp3();
		player3.play("ws");
		Utils.writeConsole(MsgAndComments.ENHORABUENA, true, 3);

		Utils.writeConsole("¡Habéis conseguido acertar todas las preguntas!", true, 1);
		Utils.writeConsole("¡Aquí tenéis vuestra combinación!", true, 1);
		System.out.println(MsgAndComments.RIGHT_COMBINATION);

		Utils.writeConsole("¡Enhorabuena por vuestro premio! ¡Vivan los novios!", true, 10);
		System.exit(-1);
	}

	private static int showBlockQuestions(boolean first)
			throws InterruptedException, FileNotFoundException, JavaLayerException {

		if (first) {
			Utils.writeConsole("¡Debéis elegir un bloque de preguntas. ¿Cuál va a ser?", true, 1);
			for (Map.Entry<Integer, String> entry : CommonConstants.QUESTIONS_BLOCKS.entrySet()) {
				Utils.writeConsole(entry.getKey() + ": " + entry.getValue(), true, 0.5);
			}
		}
		System.out.print("Bloque: ");
		String option = Utils.read();
		int oNum = 0;
		try {
			oNum = Integer.parseInt(option);
			if (oNum < 0 && oNum >= CommonConstants.QUESTIONS_BLOCKS.size()) {
				System.out.println("El bloque que habéis elegido no existe... Intentádlo de nuevo...");
				showBlockQuestions(false);
			} else {
				Utils.writeConsole("¡Estupendo!", true, 1);
				Utils.writeConsole(
						"Habéis elegido el bloque de preguntas de " + CommonConstants.QUESTIONS_BLOCKS.get(oNum) + ".",
						true, 2);
				Utils.writeConsole("¡Comenzamos!", true, 1);
				System.out.println();

				return oNum;
			}

		} catch (Exception e) {
			System.out.println("A ver, que es dar a un número. Intentádlo de nuevo...");
			return showBlockQuestions(false);
		}
		return oNum;

	}

	public static void resolveGame() throws FileNotFoundException, JavaLayerException, InterruptedException {
		player1.stop();
		player2.play("dll4");
		Utils.writeConsole("¡Atención señoras y señores! Sergio y Bea quieren resolver...", true, 1);
		Utils.writeConsole("Bien, ¿Cuál pensáis que es la contraseña?", true, 1);
		String password = Hashing.hashString(Utils.read());
		if (password.equals(CommonConstants.PASSWORD)) {
			combination();
		} else {
			player2.stop();
			ReproductorMp3 playerWrong = new ReproductorMp3();
			playerWrong.play("dll5");
			Utils.writeConsole("¡Oh, oh! ¡La respuesta no es correcta!", true, 1);
			Utils.writeConsole("Tenéis que volver a intentarlo...", true, 5);
		}
		System.exit(-1);
	}

	private static void blockResolved(int block)
			throws FileNotFoundException, JavaLayerException, InterruptedException {
		player2.stop();
		ReproductorMp3 playerWrong = new ReproductorMp3();
		playerWrong.play("dll6");
		Utils.wordByWord("Bloque... ¡¡¡COMPLETADO!!");
		PwdFractionModel pwdFraction = passwordMap.get(block);
		if (ScoreBoard.lives == 1) {
			Utils.writeConsole(
					"¡Oh! Pero habéis fallado una pregunta... eso significa que os vamos a dar el número que se esconde tras\n"
							+ "este bloque de preguntas, pero no vais a saber el orden que le corresponde en la contraseña.",
					true, 5);
			Utils.writeConsole("El número de este bloque es: " + pwdFraction.getNum() + ".", true, 1);
		} else {
			Utils.writeConsole("El número de este bloque es " + pwdFraction.getNum() + " y está en la posición "
					+ pwdFraction.getOrder() + ".", true, 1);
		}
		Utils.writeConsole(
				"Anotad el número en un papel, lo necesitaréis para resolver. Iniciad de nuevo el juego para continuar.",
				true, 3);
		System.exit(-1);
	}

}
