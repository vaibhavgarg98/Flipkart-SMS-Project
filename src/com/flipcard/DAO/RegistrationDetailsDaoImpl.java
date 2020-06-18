package com.flipcard.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import com.flipcard.bean.RegistrationDetails;
import com.flipcard.constants.SqlConstantQueries;
import com.flipcard.utils.DBUtil;
//This is implementer class for RegistrationDetailsDao.
public class RegistrationDetailsDaoImpl implements RegistrationDetailsDao {
	private static Logger log = Logger.getLogger(RegistrationDetailsDaoImpl.class);
	//This method fetch registration details from registration details database.
	@Override
	public RegistrationDetails getRegisterationDetails(String studentId) {
		// TODO Auto-generated method stub
		String sql = SqlConstantQueries.CHECK_REGISTRATION_DETAILS;
		RegistrationDetails details = null;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setString(1,  studentId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				details = new RegistrationDetails();
				details.setStudentId(rs.getString("studentId"));
				details.setDateAndTime(rs.getDate("registerTime").toString());
				details.setRegisterId(rs.getInt("registerId"));
				details.setPaymentId(rs.getInt("paymentId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return details;
	}
	//This method generates new registration id and add registration details to database.
	@Override
	public RegistrationDetails doRegistration(String studentId, int paymentId) {
		// TODO Auto-generated method stub
		String sql = SqlConstantQueries.GET_REGISTRATION_ID;
		RegistrationDetails details = null;
		try {
			PreparedStatement stmt = DBUtil.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			int id = 0;
			if(rs.next()) 
				id = rs.getInt(1)+1;
			sql = SqlConstantQueries.DO_REGISTRATION;
			stmt = DBUtil.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setString(2, studentId);
		
			Date date = new Date();
			Object dateTime = new Timestamp(date.getTime());
			stmt.setObject(3, dateTime);
			stmt.setInt(4, paymentId);
			stmt.executeUpdate();
			details = this.getRegisterationDetails(studentId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		return details;
	}

}
