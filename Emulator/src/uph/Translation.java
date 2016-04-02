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
	public static String buy(){
		if (Settings.getLang()=="English"){
			return "I Want This";
		}
		return "";
	}
	public static String callHelp(){
		if (Settings.getLang()=="English"){
			return "Need Help!";
		}
		return "";
	}
	public static String callHelpDesc(){
		if (Settings.getLang()=="English"){
			return "This button will notify the waiter to come to your table for assistance.";
		}
		return "";
	}
	public static String menuText(){
		if (Settings.getLang()=="English"){
			return "Menu";
		}
		return "";
	}
	public static String payWith(){
		if (Settings.getLang()=="English"){
			return "Pay With";
		}
		return "";
	}
	public static String creditCard(){
		if (Settings.getLang()=="English"){
			return "Credit Card";
		}
		return "";
	}
	public static String payCash(){
		if (Settings.getLang()=="English"){
			return "Cash";
		}
		return "";
	}
	public static String payCashDescription(){
		if (Settings.getLang()=="English"){
			return "A waiter will be here shortly to handle cash payment. Please wait a moment. Thank you!";
		}
		return "";
	}
	public static String payCreditCardDescription(){
		if (Settings.getLang()=="English"){
			return "Please enter your credit card number on the following provided box! Thank you!";
		}
		return "";
	}
	public static String pendingOrderDescription(){
		if (Settings.getLang()=="English"){
			return "Thank you for paying! your meal will be done shortly! please wait while enjoy our entertaiment services";
		}
		return "";
	}
}
