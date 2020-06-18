package com.flipcard.DAO;

import com.flipcard.bean.RegistrationDetails;
//This interface contains methods to add or fetch registration details from registerDetails DB.
public interface RegistrationDetailsDao {
	RegistrationDetails getRegisterationDetails(String studentId);
	RegistrationDetails doRegistration(String studentId, int paymentId);
}
