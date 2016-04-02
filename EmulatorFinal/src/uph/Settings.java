package uph;
import java.awt.Font;
import java.io.File;
import javax.swing.ImageIcon;

public class Settings {
	private static String appName="IRestaurant";
	private static String appVersion="0.0.1";
	private static int appScreenheight=500;
	private static int appScreenwidth=800;
	private static ImageIcon appBackgroundimage=new ImageIcon(Main.class.getResource("background.jpg")); 
	private static String appCurrentlang="English";
	private static String notificationDatabase=new File("notification.database").getAbsolutePath().toString();
	private static String menuDatabase=new File("menu.database").getAbsolutePath().toString();
	private static String orderDatabase=new File("order.database").getAbsolutePath().toString();
	
	public static String getMenuDatabase(){
		return menuDatabase;
	}
	public static ImageIcon getAppbackground(){
		return appBackgroundimage;
	}
	public static void setLanguage(String lang){
		appCurrentlang=lang;
	}
	public static String getdatabaseNotification(){
		return notificationDatabase;
	}
	public static String getOrderDatabase(){
		return orderDatabase;
	}
	public static void setAppname(String changeappname){
		appName=changeappname;
	}
	public static String getAppversion(){
		return appVersion;
	}
	public static int getAppscreenwidth(){
		return appScreenwidth;
	}
	public static int getAppscreenheight(){
		return appScreenheight;
	}
	public static String getAppname(){
		return appName;
	}
	public static void setLang(String lang){
		Settings.appCurrentlang=lang;
	}
	public static String getLang(){
		return appCurrentlang;
	}
	public static Font getFont(int size){
		if (getLang()=="Japanese" || getLang()=="Chinese"){
			return new Font(null).decode("UTF-"+String.valueOf(size));
		}
		else {
			return new Font("Segoe Script", Font.ITALIC, size);
		}
	}
}
