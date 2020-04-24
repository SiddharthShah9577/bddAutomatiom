package pages;

import utils.CommonFunctions;

public class SignIn extends CommonFunctions {
	
	public void signInDetails() {
		try {
		sendValueToObject("signInEmail", data.get("Email Id"));
		sendValueToObject("signInPassword", data.get("password"));
		clickOnObject("signInButton");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
