package uph;
import javafx.scene.media.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class Panel {
	private static JFrame windows=new JFrame();
	private static JLabel backgroundPicture=new JLabel();
	private static JPanel waiterNote=new JPanel();
	private static JPanel menuList=new JPanel();
	private static JScrollPane menuList1=new JScrollPane(menuList);
	private static JPanel orderPending=new JPanel();
	private static JPanel game = new JPanel();
	private static JPanel WaiterPage = new JPanel();
	
	public static JFrame windows(){
		windows.setBounds(250,120,Settings.getAppscreenwidth(),Settings.getAppscreenheight());
		windows.setTitle(Settings.getAppname());
		windows.setResizable(false);
		windows.setLayout(new GridLayout());
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windows.setVisible(true);
		return windows;
	}
	public static JLabel backgroundPage(){
		backgroundPicture.setIcon(new ImageIcon(Settings.getAppbackground().getImage().getScaledInstance(Settings.getAppscreenwidth(), Settings.getAppscreenheight(),  java.awt.Image.SCALE_SMOOTH)));
		return backgroundPicture;
	}
	public static JPanel loginPage(){
		JPanel loginPage = new JPanel();
		loginPage.setVisible(true);
		loginPage.setOpaque(false);
		loginPage.setLayout(null);
		loginPage.setBounds(Settings.getAppscreenwidth()/2-100,0,500,400);
		JLabel menuquestion = new JLabel(Translation.loginQuestionText());
		menuquestion.setBounds(0,0, 300, 100);
		menuquestion.setFont(Settings.getFont(20));
		menuquestion.setLocation(loginPage.getWidth()-400,50);
		loginPage.add(menuquestion);
		
		//Customer login button
		JButton customer= new Tombol().customButton(Translation.customerLoginText(), loginPage.getWidth()-350 , 150);
		customer.setFont(Settings.getFont(15));
		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					backgroundPicture.remove(loginPage);
					try {
						backgroundPicture.add(customerPage());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						backgroundPicture.add(waiterNote());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					backgroundPicture.revalidate();
					backgroundPicture.repaint();
			}
		});
		customer.revalidate();
		customer.repaint();
		loginPage.add(customer);
		
		//Waiter login button
		JButton waiter= new Tombol().customButton(Translation.waiterLoginText(), loginPage.getWidth()-350 , 200);
		waiter.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent e){
				backgroundPage().removeAll();
				backgroundPage().add(WaiterPage());
				backgroundPage().repaint();
			}
		});
		waiter.setFont(Settings.getFont(15));
		loginPage.add(waiter);
		
		loginPage.revalidate();
		loginPage.repaint();
		return loginPage;
	}
	public static JPanel waiterNotification(int index,int y) throws IOException{
		JPanel waiterNotification = new JPanel();
		waiterNotification.setVisible(true);
		waiterNotification.setBounds(0,y,250,100);
		String[] hasil = Database.checkDatabase(index,Settings.getdatabaseNotification()).split(",");
		if (hasil[0].equals("call")){
			JLabel notif=new JLabel();
			notif.setText("<html><font size='5'><strong>Customer Calling</strong></font><br><u>Customer name</u><br> <font color='red'>"+hasil[1]+"</font><br><u>Table</u> <br><font color='green'>"+hasil[2]+"</font></html>");
			notif.setBounds(0, 0, 100, 100);
			waiterNotification.add(notif);
			JButton terima = new Tombol().customButton("Accept",5,5);
			terima.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						Database.databaseRemove(Database.checkDatabase(index, Settings.getdatabaseNotification()),Settings.getdatabaseNotification());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					backgroundPage().removeAll();
					WaiterPage().removeAll();
					backgroundPage().add(WaiterPage());
					WaiterPage().repaint();
					backgroundPage().repaint();
				}
			}	
					);
			
			waiterNotification.add(terima);
		}
		waiterNotification.revalidate();
		waiterNotification.repaint();
		return waiterNotification;
	}
	public static JPanel customerPage() throws IOException{
		JPanel customerPage = new JPanel();
		customerPage.setLayout(null);
		customerPage.setVisible(true);
		customerPage.setOpaque(false);
		customerPage.setBounds(300, 0, 700, 900);
		customerPage.add(menuList());
		JLabel menuText=new JLabel("<html><font color='black'><u>"+Translation.menuText()+"</u></font></html>");
		menuText.setFont(Settings.getFont(50));
		menuText.setBounds(160,60,200,200);
		customerPage.add(menuText);
		JButton callHelp = new Tombol().customButton(Translation.callHelp(), 0 , 20);
		callHelp.setToolTipText(Translation.callHelpDesc());
		callHelp.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					Database.databaseWriter("call,Tom,"+Spesial.advancedrandom(1, 10)+";", Settings.getdatabaseNotification());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
		customerPage.add(callHelp);
		JLabel paymentText=new JLabel(Translation.payWith());
		paymentText.setFont(Settings.getFont(20));
		paymentText.setBounds(0,350,200,100);
		customerPage.add(paymentText);
		JButton creditCard= new Tombol().customButton(Translation.creditCard(), 130 , 380);
		creditCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Speech.speak(Translation.payCreditCardDescription());
				JOptionPane.showInputDialog(null, "Please enter your Credit Card Number", "Credit Card Payment Gateway", 0);
				windows().removeAll();
				windows().add(orderPending());
				windows().add(Game());
				windows().repaint();
			}
		});
		JButton payCash=new Tombol().customButton(Translation.payCash(), 300, 380);
		payCash.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					Database.databaseWriter("call,Tom,"+Spesial.advancedrandom((int)System.currentTimeMillis(), 10)+";", Settings.getdatabaseNotification());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				backgroundPage().removeAll();
				backgroundPage().add(orderPending());
				backgroundPage().add(Game());
				backgroundPage().repaint();
			}
		});
		JLabel loginInfo=new JLabel("<html><u>Mr.Tom</u></html>");
		loginInfo.setFont(Settings.getFont(20));
		loginInfo.setBounds(400,0,100,80);
		customerPage.add(loginInfo);
		customerPage.add(payCash);
		customerPage.add(creditCard);
		customerPage.revalidate();
		customerPage.repaint();
		return customerPage;
	}
	public static JPanel orderPending() {
		orderPending.setBounds(0,0,Settings.getAppscreenwidth(),150);
		orderPending.setOpaque(false);
		orderPending.setLayout(null);
		JButton callHelp = new Tombol().customButton(Translation.callHelp(), Settings.getAppscreenwidth()-300 , 80);
		callHelp.setToolTipText(Translation.callHelpDesc());
		callHelp.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					Database.databaseWriter("call,Tom,"+Spesial.advancedrandom((int)System.currentTimeMillis(), 10)+";", Settings.getdatabaseNotification());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
		orderPending.add(callHelp);
		JLabel timer=new JLabel();
		timer.setFont(Settings.getFont(20));
		timer.setBounds(Settings.getAppscreenwidth()-430, 0, 500,100);
		orderPending.add(timer);
		Thread clock=new Thread(){
			public void run(){
				for (int i=1800;i>0;i--){
				timer.setText("<html><strong>"+String.valueOf(i)+" Second before your meal ready</strong></html>");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		};
		clock.start();
		return orderPending;
	}
	public static JPanel waiterNote() throws IOException{
		waiterNote.setBounds(20,20,250,420);
		waiterNote.setLayout(null);
		waiterNote.setVisible(true);
		JLabel welcomeText = new JLabel("<html><u>"+Translation.yourOrderText()+"</u></html>");
		welcomeText.setFont(Settings.getFont(20));
		welcomeText.setBounds(waiterNote.getWidth()/2-60,10,120,50);
		waiterNote.add(welcomeText);
		double jumlah=0;
		JLabel output=new JLabel("<html><table border=0>");
		output.setVerticalAlignment(JLabel.TOP);
		for (int j=0;j<Database.countDatabase(Settings.getOrderDatabase());j++){
			String[] hasil=(Database.checkDatabase(j+1, Settings.getOrderDatabase())).split(",");
			output.setText(output.getText()+"<tr><td width=130>"+hasil[0]+"</td><td>Rp."+hasil[1]+"</td></tr>");
			jumlah+=Double.parseDouble(hasil[1]);
		}
		output.setText(output.getText()+"<tr><td colspan=2><hr></td></tr><tr><td>Total Harga</td><td>Rp."+jumlah+"00</td></tr></table></html>");
		output.setBounds(20,50,250,340);
		waiterNote.add(output);
		return waiterNote;
	}
	public static JScrollPane menuList() throws IOException{
		menuList.setOpaque(false);
		menuList.setLayout(null);
		menuList.setPreferredSize(new Dimension(Database.countDatabase(Settings.getMenuDatabase())*150, 130));
		int y=0;
			for (int j=0;j<Database.countDatabase(Settings.getMenuDatabase());j++){
				String[] hasil=(Database.checkDatabase(j+1, Settings.getMenuDatabase())).split(",");
				menuList.add(productDetail(hasil[1],hasil[0],hasil[2],y));
				menuList.revalidate();
				menuList.repaint();
				y+=150;
		}
		menuList.repaint();
		menuList.revalidate();
		menuList1.setOpaque(false);
		menuList1.setViewportView(menuList);
		menuList1.getViewport().setOpaque(false);
		menuList1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		menuList1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		menuList1.setVisible(true);
		menuList1.setBounds(0,200,480,160);
		menuList1.validate();
		menuList1.repaint();
		menuList1.setBorder(null); 
		return menuList1;
	}
	public static JLabel productDetail(String image,String name,String price,int x){
		JLabel productDetail=new JLabel();
		ImageIcon product=new ImageIcon(new File("menuPicture\\"+image).getAbsolutePath().toString());
		productDetail.setIcon(new ImageIcon(product.getImage().getScaledInstance(130,130,java.awt.Image.SCALE_SMOOTH)));
		JLabel productName=new JLabel("<html><font color='black' size='4'><u>"+name+"</u></font></html>");
		JLabel productPrice=new JLabel("<html><font color='black'>Rp."+price+"</font></html>");
		productName.setBounds(productDetail.getWidth()/2+25,0,150,50);
		productPrice.setBounds(productDetail.getWidth()/2+40,productDetail.getHeight()/2+30,200,50);
		productDetail.add(productName);
		productDetail.add(productPrice);
		JButton buy= new Tombol().customButton(Translation.buy(), 0 , 90);
		buy.addActionListener( new ActionListener()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        try {
					Database.databaseWriter(name+","+price, Settings.getOrderDatabase());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        try {
		        	waiterNote().removeAll();
					waiterNote().repaint();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		    }
		});
		productDetail.add(buy);
		productDetail.setBounds(x,0,130,130);
		productDetail.setOpaque(true);
		return productDetail;
	}
	public static JPanel Game(){
	game.setOpaque(false);
	game.setBounds(0,orderPending().getHeight(),Settings.getAppscreenwidth(),(Settings.getAppscreenheight()-orderPending().getHeight()));
	JLabel text=new JLabel("aaaaaa");
	text.setBounds(0,0,100,100);
	game.add(text);
	return game;
	}
	public static JPanel WaiterPage(){
		WaiterPage.setVisible(true);
		WaiterPage.setLayout(null);
		WaiterPage.setOpaque(false);
		WaiterPage.setBounds(0,0,Settings.getAppscreenwidth(),Settings.getAppscreenheight());
		int y=350;
		try {
			for (int i=1;i<=Database.countDatabase(Settings.getdatabaseNotification());i++){
			WaiterPage.add(waiterNotification(i,y));
			y-=110;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return WaiterPage;
	}
}
