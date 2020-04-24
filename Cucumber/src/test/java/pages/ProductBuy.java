package pages;

import static org.testng.Assert.assertEquals;
import utils.CommonFunctions;

public class ProductBuy extends CommonFunctions {

	public void orderProduct() {
		try {
			propertiesFileName = "buyProduct";
			String product = "//a[@title='" + data.get("Product Name").toString() + "']";
			clickOnElement(product);
			getDriver().switchTo().frame(0);
			sendValueToObject("increaseQty", "2");
			clickOnObject("addToCartButton");
			getDriver().switchTo().defaultContent();
			waitforElement("product");
			clickOnObject("proccedToCheckOut");
			waitforElement("shopppingSummary");
			clickOnObject("proccedToCheckOut",1);
			waitforElement("address");
			clickOnObject("submitButton");
			waitforElement("terms");
			clickOnObject("terms");
			clickOnObject("processSubmit");
			waitforElement("payByBankWire");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void orderPayment() {
		try {
			propertiesFileName = "buyProduct";
			if (data.get("Payment").equalsIgnoreCase("Check")) {
				clickOnObject("payByCheck");
			} else {
				clickOnObject("payByBankWire");
			}
			waitforElement("confirmOrder");
			clickOnObject("confirmOrder");
			waitforElement("totalPrice");
			String actualText = getText("totalPrice");
			data.put("Total Price", actualText);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void verifyTotalPrice() {
		try {
			String profile = "//span[text()='" + data.get("FirstName").toString() + " " + data.get("LastName") + "']";
			clickOnElement(profile);
			propertiesFileName = "signUp";
			waitforElement("orderDetails");
			clickOnObject("orderDetails");
			propertiesFileName = "buyProduct";
			waitforElement("orderHistory");
			String actual = getText("orderHistoryTotalPrice");
			assertEquals(actual, data.get("Total Price").toString(), "Total Price is Equal");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
