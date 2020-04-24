package pages;
import utils.CommonFunctions;

public class SignUp extends CommonFunctions {
	
	public void createNewUser() {
		try {
		propertiesFileName = "signUp";
		sendValueToObject("signUpEmailId", data.get("Email Id"));
		waitforElement("createanAccount");
		clickOnObject("createanAccount");
		waitforElement("mrgender");
		if(data.get("Title").equalsIgnoreCase("Mr.")) {
			clickOnObject("mrgender");
		}else {
			clickOnObject("mrsgender");
		}
		sendValueToObject("customerFirstName", data.get("FirstName"));
		sendValueToObject("customerLastName", data.get("LastName"));
		selectDropDownValue("customerDOBDay", data.get("Date").toString());
		selectDropDownValue("customerDOBMonth", data.get("Month").toString());
		selectDropDownValue("customerDOBYear", data.get("Year").toString());
		sendValueToObject("customerPassWord", data.get("password"));
		sendValueToObject("addressFirstName", data.get("Address FirstName"));
		sendValueToObject("addressLastName", data.get("Address LastName"));
		sendValueToObject("companyName", data.get("Company Name"));
		sendValueToObject("addressLine1", data.get("Address Line 1"));
		if(data.get("Address Line 2") != null) {
		 sendValueToObject("addressLine2", data.get("Address Line 2"));
		}
		sendValueToObject("addressCity", data.get("City"));
		selectDropDownText("addressState", data.get("State").toString());
		sendValueToObject("addressPostCode", data.get("ZipCode"));		
		sendValueToObject("assignedAddress", data.get("Assigned Address Name"));
		sendValueToObject("mobilePhone", data.get("Mobile Phone"));
		clickOnObject("registerButton");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
