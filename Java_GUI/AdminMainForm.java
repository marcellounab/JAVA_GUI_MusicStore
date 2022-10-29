package clay_music_store;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminMainForm extends JFrame implements MouseListener, ItemListener{
	String genreNameMusic;
	
	//Atributt Main
	private JMenuBar menuBar;
	private JMenu menuUser, menuManage;
	private JMenuItem menuLogOff, menuManageMusic, menuManageMusicGenre;
	
	//Atributt JinternalFrame-ManageMusicForm (1)
	private JPanel mainPanel, leftPanel, rightPanel, btnPanel;
	private JInternalFrame internalFrameMusic;
	
	private JScrollPane scrollPane;
	private DefaultTableModel dtmMusic;
	private JTable tableMusic;
	
	private JTextField txtMusicName, txtArtistName;
	private JLabel lblMusicName, lblGenreName, lblArtistName, lblPrice;
	private JComboBox genreBox;
	private Vector<Object> genreList, genreList2;
	private JSpinner priceSpinner;
	//date
	private String pattern = "yyyy-MM-dd";
	LocalDate dateObj = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	String date = dateObj.format(formatter);
	
	

	private JButton btnAdd, btnUpdate, btnDelete;
	
	//Atributt JinternalFrame-ManageMusicGenreForm (2)
	private JPanel mainPanel2, leftPanel2, rightPanel2, btnPanel2;
	private JInternalFrame internalFrameMusicGenre;
	
	private JScrollPane scrollPane2;
	private DefaultTableModel dtmMusicGenre;
	private JTable tableMusicGenre;
	
	private JTextField txtGenreName2;
	private JLabel lblGenreName2;
	
	private JButton btnAdd2, btnUpdate2, btnDelete2;
	
	Font  f = new Font("Calibri", Font.PLAIN, 20);
	int selectedRow = -1;
	
	private Vector<Object> tableContent2, tableContent;

	
	public Connect con = new Connect();
	public LoginForm log = new LoginForm();
	
	public void getTableDataGenre() {
		String query = "SELECT * FROM music_genres";
		con.rs = con.execQuery(query);
		String[] columns = {"ID", "Genre"};
		dtmMusicGenre = new DefaultTableModel(columns,0);
		
		try {
			while (con.rs.next()) {
				//ambil data, index sql mulai dari 1
				tableContent2 = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContent2.add(con.rs.getObject(i).toString());
				}
				
				dtmMusicGenre.addRow(tableContent2);
			}
			
			tableMusicGenre.setModel(dtmMusicGenre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void ShowGenre() {
		String query = "SELECT genre_name FROM music_genres";
		con.rs = con.execQuery(query);
		genreList = new Vector<Object>();
		try {
			while (con.rs.next()) {
				
					genreList.add(con.rs.getString("genre_name"));

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void getTableDataMusic() {
		String query = "SELECT musics.id, musics.music_name, music_genres.genre_name, musics.music_price, musics.music_artist_name, musics.release_date "
				+ "FROM musics INNER JOIN music_genres ON musics.music_genre_id = music_genres.id";
		con.rs = con.execQuery(query);
		String[] columns = {"ID", "Nama", "Genre", "Price", "Artist Name", "Release Date"};
		dtmMusic = new DefaultTableModel(columns,0);
		
		try {
			while (con.rs.next()) {
				//ambil data, index sql mulai dari 1
				tableContent = new Vector<Object>();
				for (int i = 1; i < con.rsm.getColumnCount()+1; i++) {
					tableContent.add(con.rs.getObject(i).toString());
				}
				
				dtmMusic.addRow(tableContent);
			}
			
			tableMusic.setModel(dtmMusic);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public AdminMainForm() {
		
		CreateMain();
		
		CreateJIFrame_ManageMusicForm();
		CreateJIFrame_ManageMusicGenreForm();

		
		//jframe
		setTitle("Admin Main Form");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//mouselistener
		menuLogOff.addMouseListener(this);
		menuManageMusic.addMouseListener(this);
		menuManageMusicGenre.addMouseListener(this);
		
	}
	
	public void CreateJIFrame_ManageMusicForm() {
		
		//jpanel
		mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel.setBorder(new EmptyBorder(0, 10, 20, 0));
		leftPanel = new JPanel();
		rightPanel = new JPanel(new GridLayout(9, 1, 0, -1));
		rightPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
		btnPanel = new JPanel();
		
		
		//leftPanel - tabel
		Object[] columns = {"ID", "Nama", "Genre", "Price", "Artist Name", "Release Date"};
		dtmMusic = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		tableMusic = new JTable(dtmMusic);
		scrollPane = new JScrollPane(tableMusic);
		scrollPane.setFont(f);
		leftPanel.add(scrollPane);
		getTableDataMusic();
		
		//rightPanel 
		
		
		////music name
		lblMusicName = new JLabel("Music Name");
		txtMusicName = new JTextField();
		txtMusicName.setPreferredSize(new Dimension(50, 10));
		rightPanel.add(lblMusicName);
		rightPanel.add(txtMusicName);
		
		////genre name
		lblGenreName = new JLabel("Genre");
		ShowGenre();
		genreBox = new JComboBox<>(genreList);
		genreBox.setPreferredSize(new Dimension(50, 10));
		rightPanel.add(lblGenreName);
		rightPanel.add(genreBox);


		
		////Artist name
		lblArtistName = new JLabel("Artist Name");
		txtArtistName = new JTextField();
		txtArtistName.setPreferredSize(new Dimension(50, 10));
		rightPanel.add(lblArtistName);
		rightPanel.add(txtArtistName);
		
		////Price
		lblPrice = new JLabel("Price");
		priceSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		priceSpinner.setPreferredSize(new Dimension(50, 10));
		rightPanel.add(lblPrice);
		rightPanel.add(priceSpinner);
		
		//// button add,update,delete
		btnAdd = new JButton("Add");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnPanel.add(btnAdd);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnDelete);
		rightPanel.add(btnPanel);
		
		
		
		//mainpanel
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		
		//listener
		btnAdd.addMouseListener(this);
		btnUpdate.addMouseListener(this);
		btnDelete.addMouseListener(this);
		tableMusic.addMouseListener(this);
		genreBox.addItemListener(this);
		
		//internal frame
		internalFrameMusic = new JInternalFrame("Manage Music");
		internalFrameMusic.setVisible(true);
		internalFrameMusic.setSize(800, 500);
		internalFrameMusic.setMaximizable(true);
		internalFrameMusic.setClosable(true);
		internalFrameMusic.setResizable(false);
		internalFrameMusic.add(mainPanel);
		
	}

	public void CreateJIFrame_ManageMusicGenreForm() {
		
		//jpanel
		mainPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mainPanel2.setBorder(new EmptyBorder(0, 10, 20, 0));
		leftPanel2 = new JPanel();
		rightPanel2 = new JPanel(new GridLayout(3, 1, 0, -2));
		rightPanel2.setBorder(new EmptyBorder(0, 0, 330, 0));
		btnPanel2 = new JPanel();
		
		
		//leftPanel2 - tabel
		String[] columns = {"ID", "Genre"};

		dtmMusicGenre = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableMusicGenre = new JTable(dtmMusicGenre);
		scrollPane2 = new JScrollPane(tableMusicGenre);
		scrollPane2.setFont(f);
		leftPanel2.add(scrollPane2);
		getTableDataGenre();
		//rightPanel2 
		
		////genre name
		lblGenreName2 = new JLabel("Genre Name");
		txtGenreName2 = new JTextField();
		txtGenreName2.setPreferredSize(new Dimension(50, 10));
		rightPanel2.add(lblGenreName2);
		rightPanel2.add(txtGenreName2);
		
		
		//// button add,update,delete
		btnAdd2 = new JButton("Add");
		btnUpdate2 = new JButton("Update");
		btnDelete2 = new JButton("Delete");
		btnPanel2.add(btnAdd2);
		btnPanel2.add(btnUpdate2);
		btnPanel2.add(btnDelete2);
		rightPanel2.add(btnPanel2);
		
		
		
		//mainpanel
		mainPanel2.add(leftPanel2);
		mainPanel2.add(rightPanel2);
		
		//listener
		btnAdd2.addMouseListener(this);
		btnUpdate2.addMouseListener(this);
		btnDelete2.addMouseListener(this);
		tableMusicGenre.addMouseListener(this);
		
		//internal frame
		internalFrameMusicGenre = new JInternalFrame("Manage Music Genre");
		internalFrameMusicGenre.setVisible(true);
		internalFrameMusicGenre.setSize(800, 500);
		internalFrameMusicGenre.setMaximizable(true);
		internalFrameMusicGenre.setClosable(true);
		internalFrameMusicGenre.setResizable(false);
		internalFrameMusicGenre.add(mainPanel2);

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			ShowGenre();
			genreNameMusic = (String) genreBox.getSelectedItem();
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//gettext music genre
		String genreName = txtGenreName2.getText();
		
		//gettext music
		String musicName = txtMusicName.getText();
		int musicPrice = (Integer) priceSpinner.getValue();
		String artistName = txtArtistName.getText();
		String releaseDate = date.toString();
		int idGenre = 0;
		
		//JFI manageMusic


		
		if (e.getSource() == btnAdd) {

			if (!musicName.isEmpty() && !artistName.isEmpty() && musicPrice != 0) {
				String genreNameMusic = genreBox.getItemAt(genreBox.getSelectedIndex()).toString();
				// saat di get genrebox cuman muncul sport doang. harusnya kalo bnr di println harus sesuai sama genre yg kita pilih
				System.out.println(genreNameMusic);
				//
				
				con.checkIdGenre(genreNameMusic);
				try {
					if(con.rs.next()) {
						idGenre = con.rs.getInt("id");
					} } catch (SQLException e1) { e1.printStackTrace(); }
				
				con.prepInsertMusic(idGenre, musicName, musicPrice, artistName, releaseDate);
				getTableDataMusic();
				
			} else if (musicName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input Music Name!", "Alert", JOptionPane.WARNING_MESSAGE);
				
			}else if (artistName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input Artist Name!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (musicPrice == 0) {
				JOptionPane.showMessageDialog(null, "Please input price!", "Alert", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if (e.getSource() == btnUpdate) {
			
			selectedRow = tableMusic.getSelectedRow();
				
			if (!musicName.isEmpty() && selectedRow != -1 && !artistName.isEmpty() && musicPrice != 0) {
				String genreNameMusic = genreBox.getItemAt(genreBox.getSelectedIndex()).toString();
				// saat di get genrebox cuman muncul sport doang. harusnya kalo bnr di println harus sesuai sama genre yg kita pilih
				System.out.println(genreNameMusic);
				//
				
				con.checkIdGenre(genreNameMusic);
				try {
					if(con.rs.next()) {
						idGenre = con.rs.getInt("id");
					} } catch (SQLException e1) { e1.printStackTrace(); }
				
					String id = tableMusic.getValueAt(tableMusic.getSelectedRow(), 0).toString();
					con.prepUpdateMusic(Integer.parseInt(id), idGenre, musicName, musicPrice, artistName);
					getTableDataMusic();
					
					selectedRow = -1;
					
			}else if (musicName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input Music Name!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (artistName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input Artist Name!", "Alert", JOptionPane.WARNING_MESSAGE);
			}else if (musicPrice == 0) {
					JOptionPane.showMessageDialog(null, "Please input price!", "Alert", JOptionPane.WARNING_MESSAGE);
					
			}else if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "Message", JOptionPane.INFORMATION_MESSAGE);
					
			}
				
		}else if (e.getSource() == btnDelete) {

			selectedRow = tableMusic.getSelectedRow();
			
			if (selectedRow != -1) {
				String id = tableMusic.getValueAt(tableMusic.getSelectedRow(), 0).toString();
				con.prepDeleteMusic(Integer.parseInt(id));
				getTableDataMusic();
				
				selectedRow = -1;
				
			} else{
				JOptionPane.showMessageDialog(null, "Please select a row!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		//JFI manageMusicGenre
		if (e.getSource() == btnAdd2) {
			
			if (!genreName.isEmpty()) {
				con.prepInsertGenre(genreName);
				getTableDataGenre();
				
			} else if (genreName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input Genre Name!", "Alert", JOptionPane.WARNING_MESSAGE);
			}

		
		
		
		}else if (e.getSource() == btnUpdate2) {
			selectedRow = tableMusicGenre.getSelectedRow();
			
				if (!genreName.isEmpty() && selectedRow != -1 ) {
					String id = tableMusicGenre.getValueAt(tableMusicGenre.getSelectedRow(), 0).toString();
					con.prepUpdateGenre(Integer.parseInt(id), genreName);
					getTableDataGenre();
					
					selectedRow = -1;
					
				}else if (genreName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please input Genre Name!", "Alert", JOptionPane.WARNING_MESSAGE);
					
				} else if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "Message", JOptionPane.INFORMATION_MESSAGE);
					
				}
			
			
		}else if (e.getSource() == btnDelete2) {

			selectedRow = tableMusicGenre.getSelectedRow();
			
			if (!genreName.isEmpty() && selectedRow != -1) {
				String id = tableMusicGenre.getValueAt(tableMusicGenre.getSelectedRow(), 0).toString();
				con.prepDeleteGenre(Integer.parseInt(id));
				getTableDataGenre();
				
				selectedRow = -1;
				
			} else if (genreName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input Genre Name!", "Alert", JOptionPane.WARNING_MESSAGE);
				
			} else if(selectedRow == -1) {
				JOptionPane.showMessageDialog(null, "Please select a row!", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
		//menu
		if (e.getSource() == menuManageMusic) {
			ShowGenre();
			internalFrameMusicGenre.dispose();
			
			add(internalFrameMusic);
			if (internalFrameMusic.isClosed()) {
				internalFrameMusic.setVisible(true);
			}
			
		} else if(e.getSource() == menuManageMusicGenre){
			internalFrameMusic.dispose();
			
			add(internalFrameMusicGenre);
			if (internalFrameMusicGenre.isClosed()) {
				internalFrameMusicGenre.setVisible(true);
			}
			
		} else if (e.getSource() == menuLogOff) {
			log.tempPassword.removeAllElements();
			this.dispose();
			new LoginForm();
		}
		
		
	}
	
	private void CreateMain() {
		menuBar = new JMenuBar();
		menuUser = new JMenu("User");
		menuManage = new JMenu("Manage");
		
		menuLogOff = new JMenu("Log Off");
		menuManageMusic = new JMenu("Manage Music");
		menuManageMusicGenre = new JMenu("Manage Music Genre");
		
		menuUser.add(menuLogOff);
		menuManage.add(menuManageMusic);
		menuManage.add(menuManageMusicGenre);
		
		menuBar.add(menuUser);
		menuBar.add(menuManage);
		
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
