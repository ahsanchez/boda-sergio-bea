package constants;

import java.util.HashMap;

public class CommonConstants {

	public static final int RESOLVER_OPTION = 16;

	@SuppressWarnings("serial")
	public static final HashMap<Integer, String> QUESTIONS_BLOCKS = new HashMap<Integer, String>() {
		{
			put(1, "Pitu y Sandra");
			put(2, "Alber y Fuello");
			put(3, "Álex y Patri");
			put(4, "Andrew y Patri");
			put(5, "Ana y Pilar");
			put(6, "San");
			put(7, "Martita");
			put(8, "Irene y Pablo");
			put(9, "Cris");
			put(10, "Juankar");
			put(11, "Ivi");
			put(12, "Charly");
			put(13, "Manu");
			put(14, "Sebas");
			put(15, "Alvarito");
			put(RESOLVER_OPTION, "RESOLVER");
		}
	};

	public static final String PASSWORD = "e643b33b3019892367371b27bc0e63c2";
}
