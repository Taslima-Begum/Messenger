package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

class JUnitTest {
	
	DataBase db=new DataBase();
	@org.junit.jupiter.api.Test
	void testRegister() throws SQLException {
		db.st.executeUpdate("INSERT INTO CREDENTIALS VALUES ('username','password','screenname','"+false+"');");
		assertFalse(db.registerUser("username", " ", " "));
		assertFalse(db.registerUser(" ", " ", "screenname"));
		assertTrue(db.registerUser(" ", "password", " "));
		assertTrue(db.registerUser("s", "s", "s"));
	}
	
	void testLogin() throws SQLException {
		assertFalse(db.checkLogin("false", "password"));
		assertFalse(db.checkLogin("username", " "));
		assertTrue(db.checkLogin("username", "password"));
	}
	
	void testChangeScreenName() throws SQLException {
		db.changeScreenName("screenname", "new");
		ResultSet rs = db.st.executeQuery("SELECT SCREENNAME FROM CREDENTIALS WHERE LOGGEDON='true';");
		String screenName = null;
		while (rs.next()){
			screenName=rs.getString("screenname");
		}
		assertNotEquals(screenName,"new");
	}

}
