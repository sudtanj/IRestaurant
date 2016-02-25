package uph;

public class Translation {
	public static String welcomeText(){
		if (Settings.getLang()=="English"){
			return "Welcome";
		}
		if (Settings.getLang()=="Japanese"){
			return "ようこそ";
		}
		if (Settings.getLang()=="Chinese"){
			return "欢饮光临";
		}
		return "";
	}
	public static String yourOrderText(){
		if (Settings.getLang()=="English"){
			return "Your Order";
		}
		if (Settings.getLang()=="Japanese"){
			return "誰かとは話しますか？";
		}
		if (Settings.getLang()=="Chinese"){
			return "你是谁？";
		}
		return "";
	}
	public static String loginQuestionText(){
		if (Settings.getLang()=="English"){
			return "To whom do I speak to?";
		}
		if (Settings.getLang()=="Japanese"){
			return "誰かとは話しますか？";
		}
		if (Settings.getLang()=="Chinese"){
			return "你是谁？";
		}
		return "";
	}
	public static String loginQuestionSpeak(){
		if (Settings.getLang()=="English"){
			return "To whom do I speak to";
		}
		if (Settings.getLang()=="Japanese"){
			return "da re ka to ha na shi mas ka?";
		}
		return "";
	}
	public static String welcomeSpeak(){
		if (Settings.getLang()=="English"){
			return "Welcome";
		}
		if (Settings.getLang()=="Japanese"){
			return "yo ko so";
		}
		return "";
	}
	public static String customerLoginText(){
		if (Settings.getLang()=="English"){
			return "Customer";
		}
		if (Settings.getLang()=="Japanese"){
			return "お客様";
		}
		if (Settings.getLang()=="Chinese"){
			return "客人";
		}
		return "";
	}
	public static String chefLoginText(){
		if (Settings.getLang()=="English"){
			return "Chef";
		}
		if (Settings.getLang()=="Japanese"){
			return "シェフ";
		}
		if (Settings.getLang()=="Chinese"){
			return "厨师";
		}
		return "";
	}
	public static String waiterLoginText(){
		if (Settings.getLang()=="English"){
			return "Waiter";
		}
		if (Settings.getLang()=="Japanese"){
			return "ウェイター";
		}
		if (Settings.getLang()=="Chinese"){
			return "服务员";
		}
		return "";
	}
}
