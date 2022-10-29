package clay_music_store;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private String dbName = "clay_music_store";
	private String url = "jdbc:mysql://localhost:3306/" +dbName;
	private String user = "root";
	private String password = "";
	
	public Statement stat;
	public ResultSet rs; 
	public ResultSetMetaData rsm; 
	
	//PREPARED STATEMENT
	public PreparedStatement pstat;
	
	public Connection con;
	
	public Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			 con = DriverManager.getConnection(url, user, password);
			
			stat = con.createStatement();
			System.out.println("connect :)");
		} catch (Exception e) {
			System.out.println("not connect :(");
		}
	}
	
	//query buat select
	public ResultSet execQuery(String query) {
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	//insert user register
	public void prepInsertUser(String username, String email, String password, int role, String gender) {
		String query = "INSERT INTO users (username, email, password, role, gender) VALUES (?,?,?,?,?)";
		
		try {
			pstat = con.prepareStatement(query);
			pstat.setString(1, username);
			pstat.setString(2, email);
			pstat.setString(3, password);
			pstat.setInt(4, role);
			pstat.setString(5, gender);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// select login
	public ResultSet checkLogin (String email, String password) {
	String query = "SELECT * FROM users WHERE email = ? AND password = ?";
	
		try {
			pstat = con.prepareStatement(query);
			pstat.setString(1,  email);
			pstat.setString(2, password);
			
			rs = pstat.executeQuery();
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//insert genre
	public void prepInsertGenre (String genre_name) {
	String query = "INSERT INTO music_genres (genre_name) VALUES (?);";
	
		try {
			pstat = con.prepareStatement(query);
			
			pstat.setString(1,  genre_name);
	
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update genre
	public void prepUpdateGenre(int id, String genre_name) {
	String query = "UPDATE music_genres SET genre_name = ? WHERE id = ?";
	
		try {
			pstat = con.prepareStatement(query);
	
			pstat.setString(1, genre_name);
			pstat.setInt(2, id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete genre
	public void prepDeleteGenre(int id) {
	String query = "DELETE FROM music_genres WHERE id = ?";
	
		try {
			pstat = con.prepareStatement(query);
	
			pstat.setInt(1, id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// select ID Genre
	public ResultSet checkIdGenre (String genreName) {
	String query = "SELECT id FROM music_genres WHERE genre_name = ?";
	
	try {
		pstat = con.prepareStatement(query);
		pstat.setString(1, genreName);
		
		rs = pstat.executeQuery();
		rsm = rs.getMetaData();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rs;
}
	
	//insert music
	public void prepInsertMusic (int music_genre_id, String music_name, int music_price, String music_artist_name, String release_date) {
	String query = "INSERT INTO musics (music_name, music_genre_id, music_price, music_artist_name, release_date) VALUES (?,?,?,?,?)";
	
		try {
			pstat = con.prepareStatement(query);
			
			pstat.setString(1,  music_name);
			pstat.setInt(2,  music_genre_id);
			pstat.setInt(3,  music_price);
			pstat.setString(4,  music_artist_name);
			pstat.setString(5,  release_date);
	
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update music
	public void prepUpdateMusic(int id, int music_genre_id, String music_name, int music_price, String music_artist_name) {
	String query = "UPDATE musics SET music_name = ?, music_genre_id = ?, music_price = ?, music_artist_name = ? WHERE id = ?";
	
		try {
			pstat = con.prepareStatement(query);
	
			pstat.setString(1,  music_name);
			pstat.setInt(2,  music_genre_id);
			pstat.setInt(3,  music_price);
			pstat.setString(4,  music_artist_name);
			pstat.setInt(5,  id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//delete music
	public void prepDeleteMusic(int id) {
	String query = "DELETE FROM musics WHERE id = ?";
	
		try {
			pstat = con.prepareStatement(query);
	
			pstat.setInt(1, id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Insert  Header History
	public void prepInsertHeader (int user_id, int total_purchase, String date_purchase) {
		String query = "INSERT INTO history_header (total_purchase, date_purchase, user_id) VALUES (?,?,?)";
		
		try {
			pstat = con.prepareStatement(query);
			
			pstat.setInt(1,  total_purchase);
			pstat.setString(2,  date_purchase);
			pstat.setInt(3,  user_id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//select Header History
	public ResultSet ShowHeader (int user_id) {
	String query = "SELECT * FROM history_header WHERE user_id = ?;";
	
	try {
		pstat = con.prepareStatement(query);
		pstat.setInt(1, user_id);
		
		rs = pstat.executeQuery();
		rsm = rs.getMetaData();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rs;
}
	
	//Insert Detail History
	public void prepInsertDetail (int history_id, int music_id) {
		String query = "INSERT INTO history_detail (history_id, music_id) VALUES (?,?);";
		
		try {
			pstat = con.prepareStatement(query);
			pstat.setInt(1,  history_id);
			pstat.setInt(2,  music_id);
			
			pstat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// select ID user
	public ResultSet checkIdUser (String password) {
	String query = "SELECT id FROM users WHERE password = ?";
	
	try {
		pstat = con.prepareStatement(query);
		pstat.setString(1, password);
		
		rs = pstat.executeQuery();
		rsm = rs.getMetaData();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rs;
}
	
	
	//select detail
	public ResultSet ShowDetail (String idHeader) {
	String query = "SELECT history_detail.history_id, musics.music_name, musics.music_artist_name, musics.music_price "
			+ "FROM history_header INNER JOIN history_detail ON history_header.id = history_detail.history_id "
			+ "INNER JOIN musics ON history_detail.music_id = musics.id WHERE history_header.id = ?;";
	
	try {
		pstat = con.prepareStatement(query);
		pstat.setString(1, idHeader);
		
		rs = pstat.executeQuery();
		rsm = rs.getMetaData();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rs;
}
	
	
}
