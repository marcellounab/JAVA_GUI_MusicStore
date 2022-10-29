package clay_music_store;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class UserMainForm extends JFrame implements MouseListener {
	private Font f;
	int selectedRow = -1;
	String logPassword;
	
	//temp idUser
	int idUser = 0;
	
	//temp id music and header
	Vector<String> idMusic, idHeader, resetTempPass;
	
	//date
	private String pattern = "yyyy-MM-dd";
	LocalDate dateObj = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	String date = dateObj.format(formatter);
	
	// attribute admin
	private JMenuBar menuBar;
	private JMenu menuUser, menuStore;
	private JMenuItem menuLogOff, menuBuyMusic, menuHistory;
	
	
	//attribute BuyMusicForm
	private JPanel mainPanel, leftPanel, rightPanel, centerPanel, btnPanel;
	private JInternalFrame internalFrame;
	
	private JScrollPane scrollPaneMusic, scrollPaneCart;
	private DefaultTableModel dtmMusic, dtmCart;
	private JTable tableMusic, tableCart;
	
	private JButton btnAddCart, btnRemoveCart, btnBuy;
	
	//attribute HistoryForm
	private JPanel mainPanel2, leftPanel2, rightPanel2, centerPanel2, btnPanel2;
	private JInternalFrame internalFrame2;
	
	private JScrollPane scrollPaneHeader, scrollPaneDetail;
	private DefaultTableModel dtmHeader, dtmDetail;
	private JTable tableHeader, tableDetail;
	
	private Vector<Object> tableContentMusic, tableContentCart, tableContentHeader, tableContentDetail;
	
	public Connect con = new Connect();
	public LoginForm log = new LoginForm();
	
	
	
	public void getTableDataMusic() {
		String query = "SELECT musics.id, musics.music_name, music_genres.genre_name, musics.music_price, musics.music_artist_name, musics.release_date "
				+ "FROM musics INNER JOIN music_genres ON musics.music_genre_id = music_genres.id";
		con.rs = con.execQuery(query);
		String[] columns = {"ID", "Nama", "Genre", "Price", "Artist Name", "Release Date"};
		dtmMusic = new DefaultTableModel(columns,0);
		
		try {
			while (con.rs.next()) {
				//ambil data, index sql mulai dari 1
				
				tableContentMusic = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContentMusic.add(con.rs.getObject(i).toString());
				
				}
//				tableContentMusic.add("entoq");
				System.out.println(tableContentMusic);
				dtmMusic.addRow(tableContentMusic);
				
			}
			
			tableMusic.setModel(dtmMusic);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void getTableDataHeader() {

		con.rs = con.ShowHeader(idUser);
		Object[] columns = {"ID", "Total Purchase (IDR)", "date Purchase"};
		dtmHeader = new DefaultTableModel(columns,0);
		
		try {
			while (con.rs.next()) {
				//ambil data, index sql mulai dari 1
				tableContentHeader = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContentHeader.add(con.rs.getObject(i).toString());
				}
				
				dtmHeader.addRow(tableContentHeader);
			}
			
			tableHeader.setModel(dtmHeader);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public void getTableDataDetail() {	
		Object[] columns1 = {"History ID", "Music Name", "Music Artist", "Music Price (IDR)"};
		dtmDetail = new DefaultTableModel(columns1,0);
		
		try {
			while (con.rs.next()) {
				//ambil data, index sql mulai dari 1
				tableContentDetail = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContentDetail.add(con.rs.getObject(i).toString());
				}
				
				dtmDetail.addRow(tableContentDetail);
			}
			
			tableDetail.setModel(dtmDetail);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public UserMainForm() {

		
		 f = new Font("Calibri", Font.PLAIN, 20);
		
		CreateMain();
		CreateJIFrame_BuyMusicForm();
		CreateJIFrame_HistoryForm();
		
		
		//jframe
		setTitle("UserMainForm");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(true);
		setLocationRelativeTo(null);
		
		//listener main
		menuLogOff.addMouseListener(this);
		menuBuyMusic.addMouseListener(this);
		menuHistory.addMouseListener(this);
	}

	public void CreateJIFrame_BuyMusicForm() {
		//jpanel
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(10, 10, 15, 10));
		
		centerPanel = new JPanel(new GridLayout(1, 2, 12, 0));
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		leftPanel = new JPanel(new BorderLayout());
		rightPanel = new JPanel(new BorderLayout());
		
		btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setBorder(new EmptyBorder(0, 10, 0, 6));
		
		//leftPanel - tabel music
		String[] columns = {"ID", "Nama", "Genre", "Price", "Artist Name", "Release Date"};
		btnAddCart = new JButton("Add To Cart");
		dtmMusic = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableMusic = new JTable(dtmMusic);
		scrollPaneMusic = new JScrollPane(tableMusic);
		leftPanel.add(scrollPaneMusic, BorderLayout.CENTER);
		leftPanel.add(btnAddCart, BorderLayout.SOUTH);
		centerPanel.add(leftPanel);
		getTableDataMusic();
		
		//rightPanel - tabel cart
		String[] columns1 = {"ID", "Nama", "Genre", "Price", "Artist Name", "Release Date"};
		btnRemoveCart = new JButton("Remove From Cart");
		dtmCart = new DefaultTableModel(columns1,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableCart = new JTable(dtmCart);
		scrollPaneCart = new JScrollPane(tableCart);
		rightPanel.add(scrollPaneCart, BorderLayout.CENTER);
		rightPanel.add(btnRemoveCart, BorderLayout.SOUTH);
		centerPanel.add(rightPanel);
		
		//buypanel
		btnBuy = new JButton("Buy");
		btnBuy.setPreferredSize(new Dimension(170, 30));
		btnPanel.add(btnBuy);
		
		
		//mainpanel
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		
		//internal frame
		internalFrame = new JInternalFrame("Buy Music");
		internalFrame.setVisible(true);
		internalFrame.setSize(800, 500);
		internalFrame.setMaximizable(true);
		internalFrame.setClosable(true);
		internalFrame.setResizable(false);
		internalFrame.add(mainPanel);
		

		
		//listener
		btnAddCart.addMouseListener(this);
		btnRemoveCart.addMouseListener(this);
		btnBuy.addMouseListener(this);
		tableMusic.addMouseListener(this);
		tableCart.addMouseListener(this);
	}


	private void CreateJIFrame_HistoryForm() {
		//jpanel
		mainPanel2 = new JPanel(new BorderLayout());
		mainPanel2.setBorder(new EmptyBorder(10, 10, 15, 10));
		
		centerPanel2 = new JPanel(new GridLayout(1, 2, 12, 0));
		centerPanel2.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		//leftPanel2 - tabel
		Object[] columns = {"ID", "Total Purchase (IDR)", "date Purchase"};
		
		dtmHeader = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableHeader = new JTable(dtmHeader);
		scrollPaneHeader = new JScrollPane(tableHeader);
		scrollPaneHeader.setFont(f);
		centerPanel2.add(scrollPaneHeader);
		getTableDataHeader();
		
		//rightPanel2 
		Object[] columns1 = {"History ID", "Music Name", "Music Artist", "Music Price (IDR)"};
		
		dtmDetail = new DefaultTableModel(columns1,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableDetail = new JTable(dtmDetail);
		scrollPaneDetail = new JScrollPane(tableDetail);
		scrollPaneDetail.setFont(f);
		centerPanel2.add(scrollPaneDetail);
		
		//mainpanel
		mainPanel2.add(centerPanel2, BorderLayout.CENTER);
	
		//internal frame
		internalFrame2 = new JInternalFrame("Buy Music");
		internalFrame2.setVisible(true);
		internalFrame2.setSize(800, 500);
		internalFrame2.setMaximizable(true);
		internalFrame2.setClosable(true);
		internalFrame2.setResizable(false);
		internalFrame2.add(mainPanel2);
		
		//listerner
		tableHeader.addMouseListener(this);
		tableDetail.addMouseListener(this);
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		//JIF - BuyMusicForm
		if (e.getSource() == btnAddCart) {
			
			selectedRow = tableMusic.getSelectedRow();
			
			if (selectedRow != -1) {
				String id = tableMusic.getValueAt(tableMusic.getSelectedRow(), 0).toString();
				String name = tableMusic.getValueAt(tableMusic.getSelectedRow(), 1).toString();
				String genre = tableMusic.getValueAt(tableMusic.getSelectedRow(), 2).toString();
				String price = tableMusic.getValueAt(tableMusic.getSelectedRow(), 3).toString();
				String artistName = tableMusic.getValueAt(tableMusic.getSelectedRow(), 4).toString();
				String releaseDate = tableMusic.getValueAt(tableMusic.getSelectedRow(), 5).toString();

				Object[] dataAddToCart = {id, name, genre, price, artistName, releaseDate};
				
				dtmCart.addRow(dataAddToCart);
				tableCart.setModel(dtmCart);
				
				selectedRow = -1;
				
			} else if (selectedRow == -1){
				JOptionPane.showMessageDialog(null, "Please select a row!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}else if (e.getSource() == btnRemoveCart) {

			selectedRow = tableCart.getSelectedRow();
			
			if (selectedRow != -1) {
				String id = tableCart.getValueAt(tableCart.getSelectedRow(), 0).toString();
				String name = tableCart.getValueAt(tableCart.getSelectedRow(), 1).toString();
				String genre = tableCart.getValueAt(tableCart.getSelectedRow(), 2).toString();
				String price = tableCart.getValueAt(tableCart.getSelectedRow(), 3).toString();
				String artistName = tableCart.getValueAt(tableCart.getSelectedRow(), 4).toString();
				String releaseDate = tableCart.getValueAt(tableCart.getSelectedRow(), 5).toString();
				
				dtmCart.removeRow(selectedRow);
				selectedRow = -1;
				
			} else if(selectedRow == -1){
				JOptionPane.showMessageDialog(null, "Please select any music from cart!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}else if (e.getSource() == btnBuy) {
			
			//variabel penampung header
			String datePurchase = date.toString();
			int totalPurchase = 0;
			String logPasswordTemp= log.getTempPassword().toString();
			String logPassword = logPasswordTemp.substring(1, logPasswordTemp.length()-1);
			
			
			//insert Header
			for (int i = 0; i < tableCart.getRowCount(); i++) {
				String strPrice = tableCart.getValueAt(i, 3).toString();
				int price = Integer.parseInt(strPrice);
				totalPurchase += price;
			}
			
			con.rs = con.checkIdUser(logPassword);
			try {
				if(con.rs.next()) {
					idUser = con.rs.getInt("id");
				} } catch (SQLException e1) { e1.printStackTrace(); }

			con.prepInsertHeader(idUser, totalPurchase, datePurchase);
			
			getTableDataHeader();
			//
			
			//insert detail
			idMusic = new Vector<String>();
			idHeader = new Vector<String>();
		
						for (int j = 0; j < tableCart.getRowCount(); j++) {
							String strIdMusic = tableCart.getValueAt(j, 0).toString();
							idMusic.add(strIdMusic);
						}
					
			dtmCart.setRowCount(0);
			selectedRow = -1;
			
			for (int j = 0; j < idMusic.size(); j++) {
				//insert detail
				String strIdHeader = tableHeader.getValueAt(tableHeader.getRowCount()-1, 0).toString();
				idHeader.add(strIdHeader);
				con.prepInsertDetail(Integer.parseInt(idHeader.get(j)), Integer.parseInt(idMusic.get(j)));
			}

			
		}
		
		//JIF - History
		if (e.getSource() == tableHeader) {
			con.ShowHeader(idUser);
			String id = tableHeader.getValueAt(tableHeader.getSelectedRow(), 0).toString();
			con.ShowDetail(id);
			getTableDataDetail();
			
		}
		
		//menu
		if (e.getSource() == menuBuyMusic) {
			internalFrame2.dispose();
			
			add(internalFrame);
			
			if (internalFrame.isClosed()) {
				internalFrame.setVisible(true);
			}
			
		} else if(e.getSource() == menuHistory){
			getTableDataHeader();
			
			internalFrame.dispose();
			
			add(internalFrame2);
	
			if (internalFrame2.isClosed()) {
				internalFrame2.setVisible(true);
			}
			
		} else if (e.getSource() == menuLogOff) {
			
			log.tempPassword.removeAllElements();
//			log.setTempPassword(resetTempPass);
			this.dispose();
			new LoginForm();
		}
		
	}
	
	
	public void CreateMain() {
		menuBar = new JMenuBar();
		menuUser = new JMenu("User");
		menuStore = new JMenu("Store");
		
		menuLogOff = new JMenu("Log Off");
		menuBuyMusic = new JMenu("Buy Music");
		menuHistory = new JMenu("History");
		
		menuUser.add(menuLogOff);
		menuStore.add(menuBuyMusic);
		menuStore.add(menuHistory);
		
		
		menuBar.add(menuUser);
		menuBar.add(menuStore);
		
		setJMenuBar(menuBar);
		
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
