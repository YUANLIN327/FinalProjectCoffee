import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CoffeePOS extends JFrame {


	private JPanel contentPane;
	BufferedImage buttonIcon1 = ImageIO.read(new File("TeaButton.jpg"));	
	BufferedImage buttonIcon2 = ImageIO.read(new File("LatteButton.jpg"));
	BufferedImage buttonIcon3 = ImageIO.read(new File("DripCoffeeButton.jpg"));
	BufferedImage buttonIcon4 = ImageIO.read(new File("FrappuccinoButton.jpg"));
	BufferedImage buttonIcon5 = ImageIO.read(new File("BlackTeaButton.jpg"));
	BufferedImage buttonIcon6 = ImageIO.read(new File("ChaiTeaButton.jpg"));
	BufferedImage buttonIcon7 = ImageIO.read(new File("HerbalTeaButton.jpg"));
	BufferedImage buttonIcon8 = ImageIO.read(new File("RegularCoffeeButton.jpg"));
	BufferedImage buttonIcon9 = ImageIO.read(new File("VanillaCoffeeButton.jpg"));
	BufferedImage buttonIcon10 = ImageIO.read(new File("PumpkinCoffeeButton.jpg"));
	//Button set 2./*
	BufferedImage buttonIcon11 = ImageIO.read(new File("ChocolateFrapButton.jpg"));
	BufferedImage buttonIcon12 = ImageIO.read(new File("VanillaFrapButton.jpg"));
	BufferedImage buttonIcon13 = ImageIO.read(new File("CaramelFrapButton.jpg"));
	BufferedImage buttonIcon14 = ImageIO.read(new File("HazelnutLatteButton.jpg"));
	BufferedImage buttonIcon15 = ImageIO.read(new File("MochaLatteButton.jpg"));
	BufferedImage buttonIcon16 = ImageIO.read(new File("VanillaLatteButton.jpg"));
	BufferedImage btnIconBack = ImageIO.read(new File("BackIcon.png")); 
	
	//initate card layout field instance
	CardLayout c1 = new CardLayout();
	CardLayout cmenu = new CardLayout();
	DefaultListModel<OrderItem> oidata = new DefaultListModel();	
	JList itemlist = new JList(oidata);
	
	
	private JTextField txtAmountDue;
	private JTextField textField_1;
	private static final double taxRate=0.0825;
	boolean isOrderEmpty = true;
	
	JLabel lblSubTotal;
	JLabel lblTax;
	JLabel lblTotal;
    JFrame Receipt = new JFrame();
    Desktop desktop = null;
	ArrayList<Order> orders = new ArrayList<Order>();	
	HashMap<String, Double> items = new HashMap<String, Double>();
	private JTextField textField_3;
    JTextArea ta = new JTextArea();
    String CashAmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoffeePOS posFrame = new CoffeePOS();
					posFrame.setVisible(true);
					posFrame.setSize(725,544);
					posFrame.setTitle("iCoffee Shop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	
    void drawLine(){
        ta.append("------------------------------------\n");
}
	public CoffeePOS() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 544);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//add container panel for menu and check out		
		JPanel pnlContainer = new JPanel();
		pnlContainer.setBounds(330, 90, 387, 428);
		//apply the card layout 
		pnlContainer.setLayout(c1);
		contentPane.add(pnlContainer);
		
		//add menu panel
		JPanel pnlMenu = new JPanel();
		pnlMenu.setLayout(null);
		pnlMenu.setBackground(new Color(51, 102, 153));	
		//add menu panel to container panel
		pnlContainer.add(pnlMenu,"Menu");		
		
		//add checkout panel
		JPanel pnlCheckout = new JPanel();
		pnlCheckout.setLayout(null);
		pnlCheckout.setBackground(SystemColor.window);
		//add checkout panel to container panel
		pnlContainer.add(pnlCheckout, "Checkout");
		
		txtAmountDue = new JTextField();
		txtAmountDue.setBounds(200, 19, 81, 31);
		pnlCheckout.add(txtAmountDue);
		txtAmountDue.setColumns(10);
		txtAmountDue.setHorizontalAlignment(SwingConstants.RIGHT);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(200, 87, 81, 31);
		pnlCheckout.add(textField_1);
		if(Desktop.isDesktopSupported())desktop=Desktop.getDesktop();
		
		JButton btnCash = new JButton("Cash");
        btnCash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        //CashAmt = JOptionPane.showInputDialog("Please Enter Cash Amount: ");
                        System.out.println(CashAmt);
                        //Reciept nw = new Reciept();
                        //nw.NewScreen();
                        if (e.getSource()==btnCash){
                                    Receipt.setSize(250, 250);
                                    Receipt.setVisible(true);
                                    Receipt.getContentPane().add(ta, BorderLayout.NORTH);
                                    JButton emailBtn = new JButton("Email Reciept");
                                    Receipt.getContentPane().add(emailBtn, BorderLayout.SOUTH);
                                       emailBtn.addActionListener(new ActionListener(){
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                String report$=ta.getText();
                                                String mailto="Claudia_zamudio@baylor.edu?SUBJECT=Reciept [iCoffee Shoppe]&BODY=" + report$;

                                                URI uri=null;

                                                try{
                                                    uri=new URI("mailto", mailto, null);
                                                    try {
                                                        desktop.mail(uri);
                                                    } catch (IOException e1) {
                                                        // TODO Auto-generated catch block
                                                        e1.printStackTrace();
                                                    }
                                                }catch (URISyntaxException e1){
                                                    e1.printStackTrace();

                                                }
                                            }

                                        });
                                    ta.setText("");
                                    drawLine();
                                    ta.append("\tiCoffeeShoppe\n");
                                    ta.append("\tRECIEPT\n");
                                    drawLine();
                                    ta.append("Total Amount Due: " + txtAmountDue.getText() + "\n"+ "Amount Tendered: " + textField_3.getText() + "\n"+
                                    "Change: " + textField_1.getText());

                        }
            }

});
		btnCash.setBounds(93, 207, 105, 39);
		pnlCheckout.add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(208, 207, 105, 39);
		pnlCheckout.add(btnCheck);
		
		JButton btnCoupon = new JButton("Coupon");
		btnCoupon.setBounds(93, 257, 105, 39);
		pnlCheckout.add(btnCoupon);
		
		JButton btnGiftCard = new JButton("Gift Card");
		btnGiftCard.setBounds(208, 257, 105, 39);
		pnlCheckout.add(btnGiftCard);
		
		JButton btnCreditdebitCard = new JButton("Credit/Debit Card");
		btnCreditdebitCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreditdebitCard.setBounds(112, 153, 172, 39);
		pnlCheckout.add(btnCreditdebitCard);
		
		JLabel lblNewLabel_1 = new JLabel("Amount Due: ");
		lblNewLabel_1.setBounds(108, 27, 87, 14);
		pnlCheckout.add(lblNewLabel_1);
		
		JLabel lblChange = new JLabel("Change: ");
		lblChange.setBounds(140, 95, 55, 14);
		pnlCheckout.add(lblChange);
		
		JButton btnBack = new JButton(new ImageIcon(btnIconBack));
		btnBack.setBackground(SystemColor.window);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c1.show(pnlContainer, "Menu");
			}
		});
		btnBack.setBounds(10, 11, 38, 39);
		btnBack.setBorderPainted(false);
		
		pnlCheckout.add(btnBack);
		
		JLabel label_1 = new JLabel("Amount Tendered: ");
		label_1.setBounds(75, 61, 120, 14);
		pnlCheckout.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(200, 53, 81, 31);
		pnlCheckout.add(textField_3);
		
		c1.show(pnlContainer, "Menu");		
		
		
		JButton btnTea = new JButton(new ImageIcon(buttonIcon1));
		btnTea.setBackground(new Color(0, 51, 51));
		btnTea.setText("Tea");	
		btnTea.setBounds(1, 0, 133, 110);
		pnlMenu.add(btnTea);
		
		JButton btnLatte = new JButton(new ImageIcon(buttonIcon2));
		btnLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLatte.setText("Latte");
		btnLatte.setBounds(0, 317, 133, 110);
		pnlMenu.add(btnLatte);
		
		JButton btnFrappuccino = new JButton(new ImageIcon(buttonIcon4));
		btnFrappuccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFrappuccino.setText("Frappuccino");
		btnFrappuccino.setBounds(0, 213, 133, 110);
		pnlMenu.add(btnFrappuccino);
		
		JButton btnBlackTea = new JButton(new ImageIcon(buttonIcon5));
		btnBlackTea.setText("Black Tea");
//		btnBlackTea.setBounds(26, 30, 117, 105);
//		pnlMenu.add(btnBlackTea);
		
		JButton btnChai = new JButton("Chai");
		btnChai.setBounds(210, 184, 105, 68);
//		pnlMenu.add(btnChai);
		
		JButton btnDripCoffee = new JButton(new ImageIcon(buttonIcon3));
		btnDripCoffee.setText("Drip Coffee");
		btnDripCoffee.setBounds(0, 105, 133, 110);
		pnlMenu.add(btnDripCoffee);
		
		JPanel categoryContainer = new JPanel();
		categoryContainer.setBounds(132, 0, 255, 428);
		categoryContainer.setBackground(new Color(51, 102, 153));
		categoryContainer.setLayout(cmenu);
		pnlMenu.add(categoryContainer);
		
		JPanel teaMenu = new JPanel();
		teaMenu.setBounds(132, 0, 255, 428);
		teaMenu.setBackground(new Color(51, 102, 153));
		teaMenu.setLayout(null);
		categoryContainer.add(teaMenu,"Tea");
		
		btnBlackTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnBlackTea.setBounds(0, 33, 130, 105);
		teaMenu.add(btnBlackTea);
		
		JButton btnChaiTea = new JButton(new ImageIcon(buttonIcon6));
		btnChaiTea.setText("Chai Tea");
		btnChaiTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnChaiTea.setBounds(0, 135, 130, 105);
		teaMenu.add(btnChaiTea);
		
		JButton btnHerbalTea = new JButton(new ImageIcon(buttonIcon7));
		btnHerbalTea.setText("Herbal Tea");
		btnHerbalTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnHerbalTea.setBounds(0, 240, 130, 105);
		teaMenu.add(btnHerbalTea);
		cmenu.show(categoryContainer, "Tea");
		
		
		JPanel dripcoffeeMenu = new JPanel();
		dripcoffeeMenu.setBounds(132, 0, 255, 428);
		dripcoffeeMenu.setBackground(new Color(51, 102, 153));
		dripcoffeeMenu.setLayout(null);
		categoryContainer.add(dripcoffeeMenu,"Drip Coffee");
		
		JButton btnRegularCoffee = new JButton(new ImageIcon(buttonIcon8));
		btnRegularCoffee.setText("Regular Coffee");
		btnRegularCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnRegularCoffee.setBounds(0, 30, 130, 105);
		dripcoffeeMenu.add(btnRegularCoffee);
		
		
		JButton btnVanillaCoffee = new JButton(new ImageIcon(buttonIcon9));
		btnVanillaCoffee.setText("Vanilla Coffee");
		btnVanillaCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnVanillaCoffee.setBounds(0, 135, 130, 105);
		dripcoffeeMenu.add(btnVanillaCoffee);
		
		JButton btnPumpkinCoffee = new JButton(new ImageIcon(buttonIcon10));
		btnPumpkinCoffee.setText("Pumpkin Coffee");
		btnPumpkinCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnPumpkinCoffee.setBounds(0, 240, 130, 105);
		dripcoffeeMenu.add(btnPumpkinCoffee);
		
		JPanel frappuccinoMenu = new JPanel();
		frappuccinoMenu.setBackground(new Color(52, 102, 153));
		categoryContainer.add(frappuccinoMenu, "Frappuccino");
		frappuccinoMenu.setLayout(null);
		
		JButton btnChocolateFrap = new JButton(new ImageIcon(buttonIcon11));
		btnChocolateFrap.setText("Chocolate Frap");
		btnChocolateFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnChocolateFrap.setBounds(0, 30, 130, 105);
		frappuccinoMenu.add(btnChocolateFrap);
		
		JButton btnVanillaFrap = new JButton(new ImageIcon(buttonIcon12));
		btnVanillaFrap.setText("Vanilla Frap");
		btnVanillaFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnVanillaFrap.setBounds(0, 135, 130, 105);
		frappuccinoMenu.add(btnVanillaFrap);
		
		JButton btnCaramelFrap = new JButton(new ImageIcon(buttonIcon13));
		btnCaramelFrap.setText("Caramel Frap");
		btnCaramelFrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnCaramelFrap.setBounds(0, 240, 130, 105);
		frappuccinoMenu.add(btnCaramelFrap);
		
		JPanel latteMenu = new JPanel();
		latteMenu.setBackground(new Color(52, 102, 153));
		categoryContainer.add(latteMenu, "Latte");
		
		JButton btnHazelnutLatte = new JButton(new ImageIcon(buttonIcon14));
		btnHazelnutLatte.setText("Hazelnut Latte");
		btnHazelnutLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		latteMenu.setLayout(null);
		btnHazelnutLatte.setBounds(0, 30, 130, 105);
		latteMenu.add(btnHazelnutLatte);
		
		JButton btnMochaLatte = new JButton(new ImageIcon(buttonIcon15));
		btnMochaLatte.setText("Mocha");
		btnMochaLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnMochaLatte.setBounds(0, 135, 130, 105);
		latteMenu.add(btnMochaLatte);
		
		JButton btnVanillaLatte = new JButton(new ImageIcon(buttonIcon16));
		btnVanillaLatte.setText("Vanilla Latte");
		btnVanillaLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b =(JButton) e.getSource();
				addOrderItem(b.getText());
				System.out.println(b.getText());
				System.out.println(items.get(b.getText()));
			}
		});
		btnVanillaLatte.setBounds(0, 240, 130, 105);
		latteMenu.add(btnVanillaLatte);
		
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				oidata.removeAllElements();
				isOrderEmpty = true;
			}
		});
		btnNewButton.setBounds(10, 381, 84, 33);
		contentPane.add(btnNewButton);
		
		
		//edit item quantity button
		JButton btnEdit = new JButton("Edit Amount");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = itemlist.getSelectedIndex();
				Order currorder = orders.get(orders.size()-1);
				OrderItem curroi = (OrderItem)itemlist.getSelectedValue();
				
				if (sel>-1){
					JFrame fAmount = new JFrame();
					JTextField tfamount = new JTextField("        "+curroi.quantity);
					tfamount.setHorizontalAlignment(SwingConstants.RIGHT);
					JPanel  pbuttons = new JPanel();
					JButton btnIncrease = new JButton("Increase");
					JButton btnDecrease = new JButton("Decrease");
					JButton btnUpdate = new JButton("Update");
					pbuttons.add(btnIncrease);
					pbuttons.add(tfamount);
					pbuttons.add(btnDecrease);
					JLabel avgLabel = new JLabel();		
					fAmount.setSize(240, 200);					
					fAmount.getContentPane().add(btnUpdate, BorderLayout.SOUTH);			
					fAmount.getContentPane().add(pbuttons,BorderLayout.CENTER);
					fAmount.setVisible(true);
					
					btnIncrease.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int curramount =Integer.parseInt(tfamount.getText().trim());						
						    tfamount.setText(""+(++curramount));
						}
					});
					
					btnDecrease.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int curramount =Integer.parseInt(tfamount.getText().trim());
							if (curramount>0){							
								tfamount.setText(""+(--curramount));
							}
						}
					});
					
					btnUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							curroi.quantity=Integer.parseInt(tfamount.getText().trim());
							oidata.remove(sel);
							oidata.insertElementAt(curroi, sel);
							updateitemlabel(currorder);
						}
					});
						
				}
			}
		});
		btnEdit.setBounds(234, 381, 91, 33);
		contentPane.add(btnEdit);
		
		JButton btnOverridePrice = new JButton("Override Price");
		btnOverridePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = itemlist.getSelectedIndex();
				if (sel>-1){
					Order currorder = orders.get(orders.size()-1);
					OrderItem curroi = (OrderItem)itemlist.getSelectedValue();
					String newprice$ = JOptionPane.showInputDialog("Please enter the new price");
					curroi.unitprice = Double.parseDouble(newprice$);
					oidata.remove(sel);
					oidata.insertElementAt(curroi, sel);	
					updateitemlabel(currorder);
							
				}
				else {
					JOptionPane.showMessageDialog(null, "Please select an item");
				}
			
			}
		});
		btnOverridePrice.setBounds(101, 381, 125, 33);
		contentPane.add(btnOverridePrice);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isOrderEmpty){
					c1.show(pnlContainer, "Checkout");
					Order currOrder = orders.get(orders.size()-1);
					txtAmountDue.setText(""+currOrder.getTotal());
				}
				else {
					
				}
			}
		});
		btnSubmitOrder.setBounds(194, 469, 126, 49);
		contentPane.add(btnSubmitOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 315, 280);
		contentPane.add(scrollPane);
		itemlist.setFont(new Font("Courier New", Font.PLAIN, 12));
		
		
		scrollPane.setViewportView(itemlist);
		
		JLabel lblNewLabel = new JLabel("Sub Total:");
		lblNewLabel.setBounds(10, 450, 63, 14);
		contentPane.add(lblNewLabel);
		
		lblSubTotal = new JLabel("$0.00");
		lblSubTotal.setBounds(100, 450, 63, 14);
		contentPane.add(lblSubTotal);
		
		JLabel labeldiscount = new JLabel("Discount:");
		labeldiscount.setBounds(10, 425, 74, 14);
		contentPane.add(labeldiscount);
		
		JLabel lblDiscount = new JLabel("$0.00");
		lblDiscount.setBounds(100, 425, 63, 14);
		contentPane.add(lblDiscount);
		
		JLabel label_Toal = new JLabel("Total: ");
		label_Toal.setBounds(10, 500, 85, 14);
		contentPane.add(label_Toal);
		
		 lblTotal = new JLabel("$0.00");
		lblTotal.setBounds(100, 500, 61, 14);
		contentPane.add(lblTotal);
		
		JLabel label_Tax = new JLabel("Tax: ");
		label_Tax.setBounds(10, 475, 85, 14);
		contentPane.add(label_Tax);
		
		lblTax = new JLabel("$0.00");
		lblTax.setBounds(100, 475, 61, 14);
		contentPane.add(lblTax);
		
		JLabel lblCustomer = new JLabel("Quantity");
		lblCustomer.setForeground(new Color(0, 0, 0));
		lblCustomer.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCustomer.setBounds(89, 71, 69, 15);
		contentPane.add(lblCustomer);
		
		JLabel lblNewLabel_2 = new JLabel("Menu");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Georgia", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(506, 75, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblOrder = new JLabel("Name");
		lblOrder.setForeground(new Color(0, 0, 0));
		lblOrder.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblOrder.setBounds(10, 71, 61, 16);
		contentPane.add(lblOrder);
		
		JLabel lblUnitprice = new JLabel("Unit Price");
		lblUnitprice.setForeground(Color.BLACK);
		lblUnitprice.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblUnitprice.setBounds(192, 71, 83, 14);
		contentPane.add(lblUnitprice);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setForeground(Color.BLACK);
		lblTotal_1.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblTotal_1.setBounds(290, 71, 46, 14);
		contentPane.add(lblTotal_1);
		
		//Tea Prices
		items.put("Black Tea",1.5d);
		items.put("Chai Tea",1.75d);
		items.put("Herbal Tea",1.25d);
		//Coffee Prices
		items.put("Regular Coffee",1.25d);
		items.put("Vanilla Coffee",1.5d);
		items.put("Pumpkin Coffee",1.6d);
		//Frappuccino Prices
		items.put("Chocolate Frap",3.25d);
		items.put("Vanilla Frap",3.5d);
		items.put("Caramel Frap",3.6d);
		//Latte Price
		items.put("Hazelnut Latte",3.25d);
		items.put("Mocha",3.5d);
		items.put("Vanilla Latte",3.6d);
//		items.put("Hot Coffee",3.5d);
//		items.put("Mocha",4.5d);
//		items.put("Frappuccino",5.5d);
//		items.put("Black Tea",2.2d);
//		items.put("Chai",1.5d);
		
		
		btnTea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cmenu.show(categoryContainer, "Tea");
			}
		});	
		
		btnDripCoffee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "Drip Coffee");
			}
		});
		btnFrappuccino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "Frappuccino");
			}
		});
		btnLatte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmenu.show(categoryContainer, "Latte");
			}
		});

	}
	
	public void addOrderItem(String itemname){
		
		OrderItem oi = new OrderItem(itemname,items.get(itemname));
		
		if (isOrderEmpty){
			Order newOrder = new Order();	
			isOrderEmpty=false;
			newOrder.orderitems.add(oi);
			oidata.addElement(oi);
			updateitemlabel(newOrder);
			
			
		}
		else {
			Order currOrder = orders.get(orders.size()-1);
			if(currOrder.orderitems.contains(oi)){
				
			}
			else {
				currOrder.orderitems.add(oi);
				oidata.addElement(oi);
				updateitemlabel(currOrder);
			}
		}	
		
	}

	private void updateitemlabel(Order currOrder) {
		lblSubTotal.setText("$"+currOrder.getSubtotal());
		lblTax.setText("$"+currOrder.getTax());
		lblTotal.setText("$"+currOrder.getTotal());
	}
	
	class Order{
		ArrayList<OrderItem> orderitems = new ArrayList<OrderItem>();
		
//		double amountsTendered=0.0;
//		double totalDue=total()-amountsTendered;
//		double couponamount;
//		double tax;		
//		double totaldue;
		
		Order() {
			orders.add(this);
		}
		
		public double getSubtotal(){
			double subtotal=0.0;
			for (OrderItem oi: orderitems){
				subtotal += oi.quantity*oi.unitprice;
			}
			return subtotal;
			
		}
		
		public double getTax(){
			return Math.round(taxRate*getSubtotal()*100.00)/100.0;
		}
		
		public double getTotal(){
			return  Math.round((getSubtotal()+getTax())*100.0)/100.00;
		}
		
		
		
	}
	
	class Customer {
		
	}
	
	class OrderItem {
		String name;
		double unitprice;
		int quantity;
		OrderItem(){
			
		}
		OrderItem (String name, Double price){
			this.name= name;
			this.unitprice = price;
			this.quantity = 1;
		}
		
		
		public String toString(){
			
			return String.format("%-17s", name)+ String.format("%-10s", quantity) + 
					String.format("%7s", unitprice)+String.format("%10s", quantity*unitprice);
		}
	}
	
	class Item {
		private String name;
		private Double price;
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}