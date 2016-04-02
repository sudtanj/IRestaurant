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
			return "あなたのご注文";
		}
		if (Settings.getLang()=="Chinese"){
			return "你的秩序";
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
		if (Settings.getLang()=="Japanese"){
			return "この食べ物欲しいです";
		}
		if (Settings.getLang()=="Chinese"){
			return "我要这个食物";
		}
		return "";
	}
	public static String callHelp(){
		if (Settings.getLang()=="English"){
			return "Need Help!";
		}
		if (Settings.getLang()=="Japanese")
			return "助けて！";
		if (Settings.getLang()=="Chinese"){
			return "幫我！";
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
		if (Settings.getLang()=="Japanese")
			return "メニュー";
		if (Settings.getLang()=="Chinese")
			return "菜單";
		return "";
	}
	public static String payWith(){
		if (Settings.getLang()=="English"){
			return "Pay With";
		}
		if (Settings.getLang()=="Japanese")
			return "払う";
		if (Settings.getLang()=="Chinese")
			return "支付";
		return "";
	}
	public static String creditCard(){
		if (Settings.getLang()=="English"){
			return "Credit Card";
		}
		if (Settings.getLang()=="Japanese"){
			return "クレジットカード";
		}
		if (Settings.getLang()=="Chinese"){
			return "信用卡";
		}
		return "";
	}
	public static String payCash(){
		if (Settings.getLang()=="English"){
			return "Cash";
		}
		if (Settings.getLang()=="Japanese"){
			return "現金";
		}
		if (Settings.getLang()=="Chinese"){
			return "現金";
		}
		return "";
	}
	public static String payCashDescription(){
		if (Settings.getLang()=="English"){
			return "A waiter will be here shortly to handle cash payment. Please wait a moment. Thank you!";
		}
		if (Settings.getLang()=="Japanese"){
			return "wu we ta ga ge n ki n shi ha ra i wo sho ri su ru no ta me ni, su gu koko ni nari mas";
		}
		return "";
	}
	public static String payCreditCardDescription(){
		if (Settings.getLang()=="English"){
			return "Please enter your credit card number on the following provided box! Thank you!";
		}
		if (Settings.getLang()=="Japanese"){
			return "ボックスでクレジットカード番号を入れてください";
		}
		if (Settings.getLang()=="Chinese")
			return "请在框中输入您的信用卡号码";
		return "";
	}
	public static String pendingOrderDescription(){
		if (Settings.getLang()=="English"){
			return "Thank you for paying! your meal will be done shortly! please wait while enjoy our entertaiment services";
		}
		if (Settings.getLang()=="Japanese"){
			return "Shi ha ra i o a ri ga to u go za i ma su, a na ta no sho ku hin ga su gu ni o ko na wa re ma su.";
		}
		return "";
	}
}
