package uph;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class main {
	public static void main(String[] args) throws InterruptedException{
		JFrame window= new JFrame();
		window.setBounds(250,120,800,500);
		window.setTitle(" V");
		window.setResizable(false);
		window.setLayout(new GridLayout());
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		JLabel backgroundpicture= new JLabel();
		ImageIcon imageIcon = new ImageIcon('background.jpg'); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(window.getWidth(), window.getHeight(),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		backgroundpicture.setIcon(imageIcon);
		String welcometext="Welcome";
		String[] hasil=welcometext.split("");
		JLabel welcomesign = new JLabel();
		welcomesign.setBounds(0,0, 300, 100);
		welcomesign.setFont(new Font("Segoe Script", Font.ITALIC, 50));
		welcomesign.setLocation(window.getWidth()-(window.getWidth()/2)+50,window.getHeight()/3-10);
		backgroundpicture.add(welcomesign);
		for (int i=0;i<welcometext.length();i++){
			welcomesign.setText(welcomesign.getText()+hasil[i]);
			backgroundpicture.add(welcomesign);
			window.add(backgroundpicture, BorderLayout.WEST);
			Thread.sleep(800);
		}
		
		uph.Speech.speak("Welcome");
	
		backgroundpicture.remove(welcomesign);
		
		JLabel menuquestion = new JLabel("To whom do i speak to?");
		menuquestion.setBounds(0,0, 300, 100);
		menuquestion.setFont(new Font("Segoe Script", Font.ITALIC, 20));
		menuquestion.setLocation(window.getWidth()-400,50);
		
		backgroundpicture.add(uph.tombol.loginbutton("Customer", window.getWidth()-350 , 150));
		backgroundpicture.add(uph.tombol.loginbutton("Waiter", window.getWidth()-350 , 200));
		backgroundpicture.add(uph.tombol.loginbutton("Chef", window.getWidth()-350 , 250));
		
		backgroundpicture.add(menuquestion);
		backgroundpicture.revalidate();
		backgroundpicture.repaint();
		window.add(backgroundpicture, BorderLayout.WEST);
		Thread.sleep(500);
		
		uph.Speech.speak("To whom do i speak to");
	}
}
