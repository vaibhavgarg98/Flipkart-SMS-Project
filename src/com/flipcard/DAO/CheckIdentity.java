package com.flipcard.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.utils.DBUtil;
//Database level class to verifu use.
public class CheckIdentity {
	//Logger object to write to console.
	private Logger log = Logger.getLogger(CheckIdentity.class);
	
	//Method that check if a user with given credentials is present inside Users db.
	public String verifyCredentials(String emailId, String password) {
		String sql = SqlConstantQueries.VERIFY_USER;
		String role = null;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				role = rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return role;
	}
}
