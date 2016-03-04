package uph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Panel {
	private static JFrame windows=new JFrame();
	private static JLabel backgroundPicture=new JLabel();
	private static JPanel waiterNote=new JPanel();
	private static JPanel menuList=new JPanel();
	private static JScrollPane menuList1=new JScrollPane(menuList);
	
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					backgroundPicture.add(waiterNote());
					backgroundPicture.revalidate();
					backgroundPicture.repaint();
			}
		});
		customer.revalidate();
		customer.repaint();
		loginPage.add(customer);
		
		//Waiter login button
		JButton waiter= new Tombol().customButton(Translation.waiterLoginText(), loginPage.getWidth()-350 , 200);
		waiter.setFont(Settings.getFont(15));
		loginPage.add(waiter);
		
		//Chef login button
		JButton chef= new Tombol().customButton(Translation.chefLoginText(), loginPage.getWidth()-350 , 250);
		chef.setFont(Settings.getFont(15));
		loginPage.add(chef);
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Panel.backgroundPage().remove(waiterNotification);
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
		customerPage.revalidate();
		customerPage.repaint();
		return customerPage;
	}
	public static JPanel waiterNote(){
		waiterNote.setBounds(20,20,250,420);
		waiterNote.setLayout(null);
		waiterNote.setVisible(true);
		JLabel welcomeText = new JLabel("<html><u>"+Translation.yourOrderText()+"</u></html>");
		welcomeText.setFont(Settings.getFont(20));
		welcomeText.setBounds(waiterNote.getWidth()/2-60,10,120,50);
		waiterNote.add(welcomeText);
		return waiterNote;
	}
	public static JScrollPane menuList() throws IOException{
		menuList.setOpaque(true);
		menuList.setLayout(new FlowLayout());
		menuList.setPreferredSize(new Dimension(800, 600));
		int y=0;
		if (Database.countDatabase(Settings.getMenuDatabase())<4){
			for (int j=0;j<Database.countDatabase(Settings.getMenuDatabase());j++){
				String[] hasil=(Database.checkDatabase(j+1, Settings.getMenuDatabase())).split(",");
				menuList.add(productDetail(hasil[1],hasil[0],hasil[2],y));
				menuList.revalidate();
				menuList.repaint();
				y+=150;
			}
		}
		else {
		int batas=0;
		for (int i=0;i<Database.countDatabase(Settings.getMenuDatabase())/4;i++){
				for (int j=batas;j<batas+4;j++){
					String[] hasil=(Database.checkDatabase(j+1, Settings.getMenuDatabase())).split(",");
					menuList.add(productDetail(hasil[1],hasil[0],hasil[2],y));
					menuList.revalidate();
					menuList.repaint();
				y+=150;
				}
				batas+=4;
		}
		}
		menuList.repaint();
		menuList.revalidate();
		menuList1.add(menuList,50,0);
		menuList1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		menuList1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		menuList1.setVisible(true);
		menuList1.setBounds(0,150,480,290);
		menuList1.validate();
		menuList1.repaint();
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
		productDetail.setBounds(x,0,130,130);
		productDetail.setOpaque(true);
		return productDetail;
	}
	public static JPanel Game(){
	JPanel game = new JPanel();
	return game;
	}
}
