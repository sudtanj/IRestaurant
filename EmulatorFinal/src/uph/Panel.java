package uph;
import javafx.application.Application;
import javafx.scene.media.*;
import javafx.scene.web.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
	private static JPanel languagePanel=new JPanel();
	private static int languagePanelCounter=0;
	private static JPanel customerOrderPage = new JPanel();
	private static JPanel menuChange1=new JPanel();
	private static JScrollPane menuChange=new JScrollPane(menuChange1);
 
	
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
						System.gc();
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
		Thread speak=new Thread(){
			public void run(){
			Speech.speak(Translation.loginQuestionSpeak());
			}};
		speak.start();
		return loginPage;
	}
	public static JPanel languagePanel(){
		languagePanel.setVisible(true);		
		String[] langlist= {"US English","Chinese（中国語）","Japanese（日本語）"};
		String[] langflag= {"us.png","chn.png","jpn.png"};
		for (int i=0;i<3;i++){
			JLabel lang=new JLabel(new ImageIcon(Main.class.getResource(langflag[i])));
			lang.setToolTipText(langlist[i]);
			if (i==0){
			lang.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					Settings.setLang("English");
					Panel.backgroundPage().removeAll();
					Panel.backgroundPage().add(Panel.loginPage());
					Panel.backgroundPage().repaint();
				}
			});
			}
			if (i==1){
				lang.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						Settings.setLang("Chinese");
						Panel.backgroundPage().removeAll();
						Panel.backgroundPage().add(Panel.loginPage());
						Panel.backgroundPage().repaint();
					}
				});
			}
			if (i==2){
				lang.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						Settings.setLang("Japanese");
						Panel.backgroundPage().removeAll();
						Panel.backgroundPage().add(Panel.loginPage());
						Panel.backgroundPage().repaint();
					}
				});
			}
			lang.setVisible(true);
			if (i==0)
				lang.setBounds(i+50,Settings.getAppscreenheight()/4,200,200);
			else
			lang.setBounds(i*250+50,Settings.getAppscreenheight()/4,200,200);
			languagePanel.add(lang);
		}
		languagePanel.setLayout(null);
		languagePanel.setOpaque(false);
		languagePanel.setSize(Settings.getAppscreenwidth(),Settings.getAppscreenheight());
		languagePanel.repaint();
		Thread speak=new Thread(){
			public void run(){
			Speech.speak("Please choose your language");
			}};
		speak.start();
		return languagePanel;
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
		if (hasil[0].equals("order")){
			JLabel notif=new JLabel();
			notif.setText("<html><font size='5'><strong>Order Up</strong></font><br><u>Customer name</u><br> <font color='red'>"+hasil[1]+"</font><br><u>Table</u> <br><font color='green'>"+hasil[2]+"</font></html>");
			notif.setBounds(0, 0, 100, 100);
			waiterNotification.add(notif);
			JButton terima = new Tombol().customButton("Confirm",5,5);
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
		System.out.println("test");
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
				JOptionPane.showInputDialog(null, Translation.payCreditCardDescription(), "Credit Card Payment Gateway", 0);
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
				Speech.speak(Translation.payCashDescription());
				if(Settings.getLang()=="Chinese"){
					JOptionPane.showMessageDialog(null, "服务生将在这里很快办理现金支付");
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
				for (int i=1;i>=0;i--){
				timer.setText("<html><strong>"+String.valueOf(i)+" Second before your meal ready</strong></html>");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (i==0){
					try {
						for (int j=1;j<=Database.countDatabase(Settings.getOrderDatabase());j++)
						Database.databaseRemove(Database.checkDatabase(j, Settings.getOrderDatabase()), Settings.getOrderDatabase());
					} catch (IOException ex){}
					try {
						Database.databaseWriter("order,Tom,2;", Settings.getdatabaseNotification());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				}
			}
		};
		clock.start();
		Thread speak=new Thread(){
			public void run(){
				Speech.speak(Translation.pendingOrderDescription());
			}
		};
		speak.start();
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
		WaiterPage.add(customerOrderPage());
		return WaiterPage;
	}
	public static JScrollPane menuChange(){
		ArrayList<String> menu=new ArrayList();
		try {
			for (int i=1;i<=Database.countDatabase(Settings.getMenuDatabase());i++){
			menu.add(Database.checkDatabase(i,Settings.getMenuDatabase()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i=0;i<menu.size();i++){
			JTextField input1=new JTextField();
			JTextField input2=new JTextField();
			JTextField input3=new JTextField();
			JButton save=new JButton("Save Menu Change");
			JButton up=new JButton("Move Up");
			JButton down=new JButton("Move Down");
			up.setBounds(600, i*40+50, 200, 20);
			up.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e){
					if ((input1.getY()-50)/40 != 0){
					String temp = menu.get((input1.getY()-50)/40);
					menu.set((input1.getY()-50)/40, menu.get(((input1.getY()-50)/40)-1));
					menu.set(((input1.getY()-50)/40)-1, temp);
					for (int i=0;i<menu.size();i++){
						input1.setText(menu.get(i).split(",")[0]);
						input2.setText(menu.get(i).split(",")[1]);
						input3.setText(menu.get(i).split(",")[2]);
						input1.revalidate();
						input2.revalidate();
						input3.revalidate();
						input1.repaint();
						input2.repaint();
						input3.repaint();
					}
					menuChange1.repaint();
					}
					System.gc();
				}
			});
			down.setBounds(820,i*40+50, 200, 20);
			down.addActionListener(new ActionListener(){
				public void actionPerformed (ActionEvent e){
					if (menu.size()!=(input1.getY()-50)/40){
					String temp = menu.get((input1.getY()-50)/40);
					menu.set((input1.getY()-50)/40, menu.get(((input1.getY()-50)/40)+1));
					menu.set(((input1.getY()-50)/40)+1, temp);
					for (int i=0;i<menu.size();i++){
						input1.setText(menu.get(i).split(",")[0]);
						input2.setText(menu.get(i).split(",")[1]);
						input3.setText(menu.get(i).split(",")[2]);
						input1.revalidate();
						input2.revalidate();
						input3.revalidate();
						input1.repaint();
						input2.repaint();
						input3.repaint();
					}
					menuChange1.repaint();
					}
					System.gc();
				}
			});
			menuChange1.add(up);
			menuChange1.add(down);
			save.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					menu.set((input1.getY()-50)/40, input1.getText()+","+input2.getText()+","+input3.getText());
					menuChange1.repaint();
				}
			});
			save.setBounds(380, i*40+50, 200, 20);
			menuChange1.add(save);
			input3.setText(menu.get(i).split(",")[2]);
			input3.setBounds(260, i*40+50, 100, 20);
			menuChange1.add(input3);
			input2.setText(menu.get(i).split(",")[1]);
			input2.setBounds(140, i*40+50, 100, 20);
			menuChange1.add(input2);
			input1.setText(menu.get(i).split(",")[0]);
			input1.setBounds(20, i*40+50, 100, 20);
			menuChange1.add(input1);
		}
		JButton savetofile=new JButton("Save To Database");
		savetofile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					Database.databaseRemoveAll(Settings.getMenuDatabase());
				for (int i=0;i<menu.size();i++)
					try {
						Database.databaseWriter(menu.get(i), Settings.getMenuDatabase());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		savetofile.setBounds(70,menu.size()*40+60 , 200, 20);
		menuChange1.add(savetofile);
		menuChange.setBorder(null);
		menuChange1.setOpaque(false);
		menuChange1.setVisible(true);
		menuChange1.setLayout(null);
		menuChange.setOpaque(false);
		menuChange.setVisible(false);
		menuChange.getViewport().setOpaque(false);
		menuChange.setBounds(10,50,480,300);
		menuChange.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		menuChange.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		menuChange1.setPreferredSize(new Dimension(1500,1500));
		menuChange.setViewportView(menuChange1);
		return menuChange;
	}
	public static JPanel customerOrderPage(){
		customerOrderPage.setVisible(true);
		JLabel data = new JLabel();
		JScrollPane temp=menuChange();
		JButton customerOrder = new Tombol().customButton ("Customer Page",5,5);
		customerOrder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				data.setVisible(true);
				temp.setVisible(false);
			}
		});
		customerOrderPage.add(temp);
		JButton changeMenu = new Tombol().customButton("Change Menu", 200, 5);
		changeMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				data.setVisible(false);
				temp.setVisible(true);
			}
		});
		customerOrderPage.add(changeMenu);
		data.setText("<html><table width=300 height=300 border=2><tr><td colspan=2><center>Customer order</center></td></tr>");
		try {
		for (int i=1;i<=Database.countDatabase(Settings.getOrderDatabase());i++){
			String[] order=Database.checkDatabase(i,Settings.getOrderDatabase()).split(",");
			data.setText(data.getText()+"<tr><td>"+order[0]+"</td><td>"+order[1]+"</td></tr>");
		}
		} catch (IOException ex){}
		customerOrderPage.add(customerOrder);
		data.setBounds(10,10,500,300);
		data.setText(data.getText()+"</table></html>");
		customerOrderPage.add(data);
		customerOrderPage.setOpaque(false);
		customerOrderPage.setLayout(null);
		customerOrderPage.setBounds(300, 0,500 ,Settings.getAppscreenheight());
		return customerOrderPage;
	}
}
