package uph;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main implements ActionListener{
	public static void main(String[] args) throws InterruptedException, IOException{
		String[] hasil=Translation.welcomeText().split("");
		
		JLabel welcomeSign = new JLabel();
		
		welcomeSign.setBounds(0,0, 300, 100);
		new Font("Segoe Script", Font.ITALIC, 50);
		welcomeSign.setFont(Settings.getFont(50));
		welcomeSign.setLocation(Settings.getAppscreenwidth()-(Settings.getAppscreenwidth()/2)+50,Settings.getAppscreenheight()/3-10);
		Panel.backgroundPage().add(welcomeSign);
		for (int i=0;i<Translation.welcomeText().length();i++){
			welcomeSign.setText(welcomeSign.getText()+hasil[i]);
			Panel.backgroundPage().add(welcomeSign);
			Panel.windows().add(Panel.backgroundPage(), BorderLayout.WEST);
			//Thread.sleep(500);
		}
		Speech.speak(Translation.welcomeSpeak());
		Panel.backgroundPage().remove(welcomeSign);
		Panel.backgroundPage().revalidate();
		Panel.backgroundPage().repaint();
		
		Panel.backgroundPage().add(Panel.languagePanel());
		Panel.backgroundPage().revalidate();
		Panel.backgroundPage().repaint();
		/* Notification
		int y=350;
		for (int i=1;i<=Database.countDatabase(Settings.getdatabaseNotification());i++){
			if (i==1){
				Panel.backgroundPage().add(Panel.waiterNotification(i,y));
				y-=120;
				Panel.backgroundPage().revalidate();
				Panel.backgroundPage().repaint();
			}
			else{ 
				Panel.backgroundPage().add(Panel.waiterNotification(i,y));
				y-=120;
				Panel.backgroundPage().revalidate();
				Panel.backgroundPage().repaint();
			}
		}
		*/
		Panel.windows().add(Panel.backgroundPage(), BorderLayout.WEST);
		Panel.windows().revalidate();
		Panel.windows().repaint();
		Thread.sleep(500);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
