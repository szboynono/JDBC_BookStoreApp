import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;

public class AppGui {

	private JFrame frame;
	private JTextField textField;
	
	private float tempForTT;
	private String tempClubForIS;
	private String tempTitleForIS;
	private int tempYearForIS;
	
	
	private JLabel lblNewLabel;
	JdbcUse ju = new JdbcUse();
	private JLabel lblCid;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JTextField txtAttribute;
	private JTextField txtValue;
	private JButton btnUpdate;
	private JTextField txtTitle;
	private JTextField txtYear;
	private JLabel lblSelectACategory;
	private JList<String> list;
	private JLabel lblFindBookResult;
	private JLabel lblMin;
	private JLabel lblSelectABook;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_1;
	private JTextField txtQuantity;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_5;
	private JButton btnPurchase;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGui window = new AppGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Fake Book Store");
		frame.setBounds(100, 100, 999, 682);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnNewButton = new JButton("find Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cusPrint = null;
				Customer cusOut = ju.find_customer(Integer.parseInt((textField.getText())));
				if(cusOut != null){
					cusPrint = (ju.find_customer(Integer.parseInt((textField.getText())))).toString();
					lblNewLabel.setText(cusPrint);
					btnUpdate.setVisible(true);

				}else{
					cusPrint = "Invaild cid, try again";
					lblNewLabel.setText(cusPrint);
					btnUpdate.setVisible(false);
					btnNewButton_2.setVisible(false);
					txtAttribute.setVisible(false);
					txtValue.setVisible(false);
				}
				
			}
		});
		btnNewButton.setBounds(10, 73, 175, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(70, 17, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 48, 432, 15);
		frame.getContentPane().add(lblNewLabel);
		
		lblCid = new JLabel("Cid -->");
		lblCid.setBounds(10, 20, 50, 15);
		frame.getContentPane().add(lblCid);
		
		btnNewButton_1 = new JButton("Disconnect and Quit");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ju.getConDB().commit();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					ju.getConDB().close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(423, 604, 219, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Update it!");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAttribute.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please enter attribute");
				}else if(txtValue.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please enter value");
				}else{
					ju.update_customer(Integer.parseInt((textField.getText())), txtAttribute.getText(), txtValue.getText());
					btnNewButton.doClick();
					btnUpdate.setVisible(true);
					btnNewButton_2.setVisible(false);
					txtAttribute.setVisible(false);
					txtValue.setVisible(false);
				}
			}
		});
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(461, 73, 147, 23);
		btnNewButton_2.setVisible(false);
		frame.getContentPane().add(btnNewButton_2);
		
		txtAttribute = new JTextField();
		txtAttribute.setText("attribute");
		txtAttribute.setBounds(405, 42, 123, 21);
		txtAttribute.setVisible(false);
		frame.getContentPane().add(txtAttribute);
		txtAttribute.setColumns(10);
		
		txtValue = new JTextField();
		txtValue.setText("value");
		txtValue.setBounds(542, 42, 100, 21);
		txtValue.setVisible(false);
		frame.getContentPane().add(txtValue);
		txtValue.setColumns(10);
		
		btnUpdate = new JButton("update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(null, "do you want to update");
				if(answer == JOptionPane.YES_OPTION){
					btnNewButton_2.setVisible(true);
					txtAttribute.setVisible(true);
					txtValue.setVisible(true);
			}
			}
		});
		btnUpdate.setBounds(195, 73, 93, 23);
		btnUpdate.setVisible(false);
		frame.getContentPane().add(btnUpdate);
		
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setToolTipText("Category");
		comboBox.setBounds(10, 140, 203, 23);
		for(int i = 0;i < ju.fetch_categories().size();i++){
			comboBox.addItem(ju.fetch_categories().get(i).getoCat());
		}
		frame.getContentPane().add(comboBox);
		
		
		
		txtTitle = new JTextField();
		txtTitle.setText("title");
		txtTitle.setBounds(10, 173, 143, 21);
		frame.getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		List<Book> tempBookList = new ArrayList<>();
		DefaultListModel<String> model = new DefaultListModel<>();
		list = new JList<>(model);
		list.setVisible(false);
		list.setBounds(253, 143, 695, 213);
		frame.getContentPane().add(list);
		
		JButton btnNewButton_3 = new JButton("Find books");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() != null && txtTitle.getText() != null){
					model.removeAllElements();
					list.setVisible(true);
					lblSelectABook.setVisible(true);
					lblMin.setVisible(true);
					btnNewButton_4.setVisible(true);
					for(int i = 0; i < (ju.find_book(comboBox.getSelectedItem().toString(), txtTitle.getText()).size()); i++){
						Book bok =ju.find_book(comboBox.getSelectedItem().toString(), txtTitle.getText()).get(i);
						tempBookList.add(bok);
						model.addElement(bok.toString());
					}	
				}else{
					JOptionPane.showMessageDialog(null, "Error,Please enter all 3 values correctly");
				}
			}
		});
		btnNewButton_3.setBounds(20, 204, 133, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		lblSelectACategory = new JLabel("Enter Category and title below");
		lblSelectACategory.setBounds(10, 121, 237, 15);
		frame.getContentPane().add(lblSelectACategory);
		
		
		
		lblFindBookResult = new JLabel("Find book Result");
		lblFindBookResult.setBounds(548, 121, 123, 15);
		frame.getContentPane().add(lblFindBookResult);
		
		lblSelectABook = new JLabel("Select a book from above and Click to find min price -->");
		lblSelectABook.setBounds(253, 377, 378, 15);
		lblSelectABook.setVisible(false);
		frame.getContentPane().add(lblSelectABook);
		
		lblMin = new JLabel("min:");
		lblMin.setBounds(253, 409, 682, 15);
		lblMin.setVisible(false);
		frame.getContentPane().add(lblMin);
		
		
		btnNewButton_4 = new JButton("Find Min_price");
		btnNewButton_4.setVisible(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty() || (ju.find_customer(Integer.parseInt((textField.getText()))) == null)){
					JOptionPane.showMessageDialog(null, "Please select a book and enter cid above");
				}else{
					int moIndex = list.getSelectedIndex();
					Offer offTemp = ju.min_price(tempBookList.get(moIndex).getoTitle(), tempBookList.get(moIndex).getoYear(), Integer.parseInt(textField.getText()));
					lblMin.setText("min: " + (offTemp).toString() );
					
					tempForTT = offTemp.getoPrice();
					tempClubForIS = offTemp.getoClub();
					tempTitleForIS = offTemp.getoTitle();
					tempYearForIS = offTemp.getoYear();
					
					lblNewLabel_1.setVisible(true);
					txtQuantity.setVisible(true);
					lblNewLabel_2.setVisible(true);
					btnNewButton_5.setVisible(true);
				}
			}
		});
		btnNewButton_4.setBounds(688, 373, 147, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		lblNewLabel_1 = new JLabel("Total: ");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setBounds(253, 473, 189, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtQuantity = new JTextField();
		txtQuantity.setText("Quantity");
		txtQuantity.setVisible(false);
		txtQuantity.setBounds(500, 444, 66, 21);
		frame.getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Enter Quantity then click:");
		lblNewLabel_2.setBounds(253, 447, 237, 15);
		lblNewLabel_2.setVisible(false);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton_5 = new JButton("Calculate Total");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qnty5 = Integer.parseInt(txtQuantity.getText()); 
				float totalPrice = qnty5 * tempForTT;
				lblNewLabel_1.setText("Total:  $" + String.valueOf(totalPrice));
				btnPurchase.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(630, 443, 156, 23);
		btnNewButton_5.setVisible(false);
		frame.getContentPane().add(btnNewButton_5);
		
		btnPurchase = new JButton("Purchase!");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int promptResult = JOptionPane.showConfirmDialog(null, "Do you approve the purchase?");
				if(promptResult == JOptionPane.YES_OPTION){
					Purchase pc = new Purchase();
					pc.setoCid(Integer.parseInt(textField.getText()));
					pc.setoClub(tempClubForIS);
					pc.setoQnty(Integer.parseInt(txtQuantity.getText()));
					pc.setoTitle(tempTitleForIS);
					pc.setoYear(tempYearForIS);
					pc.setoWhen(new java.sql.Timestamp(System.currentTimeMillis()));
					ju.insert_purchase(pc);
					lblNewLabel_3.setText("Thank you for your purchase");
				}
			}
		});
		btnPurchase.setForeground(Color.BLUE);
		btnPurchase.setVisible(false);
		btnPurchase.setBounds(473, 507, 112, 23);
		frame.getContentPane().add(btnPurchase);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(423, 540, 248, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("-- By      Baidi,Liu liubd@my.yorku.ca");
		lblNewLabel_4.setBounds(10, 618, 318, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		
	}
}
