package uph;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Tombol {
	public JButton customButton (String name,int xposition,int yposition){
		JButton loginbutton = new JButton(name);
		loginbutton.setFont(Settings.getFont(12));
		loginbutton.setBackground(new Color(182, 155, 76));
	    loginbutton.setForeground(Color.WHITE);
	    loginbutton.setBounds(xposition, yposition, 150, 30);
	    return loginbutton;
	}
}
