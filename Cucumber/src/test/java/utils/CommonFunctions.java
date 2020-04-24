package utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
 

public class CommonFunctions {
	public static String url;
	public static WebDriver driver;
	public static String browserType;
	public static String chromeDriverPath;
	public static String ieDriverPath;
	public static String ffDriverPath;
	public static long implicitWaitTime;
	public static String propertiesFileName;
	public static HashMap<String, String> data;

	public void launchBrowser() {
		try {
			globalConfig();
			if (browserType.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();

			} else if (browserType.equalsIgnoreCase("FireFox")) {
				System.setProperty("webdriver.gecko.driver", ffDriverPath);
				driver = new FirefoxDriver();
			} else if (browserType.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", ffDriverPath);
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Properties configReader(String fileType) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream;
		try {
			if (fileType.equalsIgnoreCase("ObjectProperty")) {
				inputStream = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\main\\resources\\objectProperties\\" + propertiesFileName + ".properties");
			} else {
				inputStream = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\main\\resources\\configuration\\globalConfig.properties");
			}
			properties.load(inputStream);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return properties;
	}
	
	public void launchURL() {
		try {
			getDriver().get(url);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void globalConfig() throws IOException {
		try {
		Properties properties = new Properties();
		properties = configReader("Global");
		url = properties.getProperty("URL");
		browserType = properties.getProperty("browserName");
		chromeDriverPath = properties.getProperty("ChromeDriverPath");
		ieDriverPath = properties.getProperty("IEDriverPath");
		ffDriverPath = properties.getProperty("MozilaFirefox");
		implicitWaitTime = Long.parseLong(properties.getProperty("ImplicitWait"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public String objectProperty(String objectName) {
		String element = null;
		Properties properties = new Properties();
		try {
			properties = configReader("ObjectProperty");
			element = properties.getProperty(objectName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return element;

	}
	
	public void excelReader(String testCaseID) {
		try
		{
			data = new HashMap<String, String>();
			FileInputStream file = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\testData\\TestCaseData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			Row row = null;
			for (int i = 1; i < rowCount+1; i++) {
		        row = sheet.getRow(i);
		        if(row.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseID)) {
		        	break;
		        }
		    } 
			 for (int j = 1; j < row.getLastCellNum(); j++) {
				    DataFormatter formatter = new DataFormatter();
		            Row headerRow = sheet.getRow(0);
		            String header = formatter.formatCellValue(headerRow.getCell(j));
		            String value = formatter.formatCellValue(row.getCell(j));
		            data.put(header, value);
		        }
			file.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
    public void clickOnObject(String objectName) {
		try {
			String object = objectProperty(objectName);
			WebElement element = getDriver().findElement(By.xpath(object));
			element.click();
		}
		catch(Exception ex) {
			Assert.fail("unable to click to Element "+objectName);
		}
	}
    
    public void clickOnObject(String objectName,int index) {
		try {
			String object = objectProperty(objectName);
			WebElement element = getDriver().findElements(By.xpath(object)).get(index);
			element.click();
		}
		catch(Exception ex) {
			Assert.fail("unable to click to Element "+objectName);
		}
	}
    
    public void clickOnElement(String objectName) {
		try {
			WebElement element = getDriver().findElement(By.xpath(objectName));
			element.click();
		}
		catch(Exception ex) {
			Assert.fail("unable to click to Element "+objectName);
		}
	}
	
	public void sendValueToObject(String objectName,String value) {
		try {
			String object = objectProperty(objectName);
			WebElement element = getDriver().findElement(By.xpath(object));
			element.clear();
			element.sendKeys(value);
		}
		catch(Exception ex) {
			Assert.fail("unable to send value "+value+" to Element "+objectName);
		}
	}
	
	public void selectDropDownValue(String objectName,String value) {
		try {
			String object = objectProperty(objectName);
			Select select = new Select(getDriver().findElement(By.xpath(object)));
			select.selectByValue(value);
		}
		catch(Exception ex) {
			Assert.fail("unable to select dropdown Element "+objectName);
		}
	}
	
	public String getText(String objectName) {
		String text = null;
		try {
			String object = objectProperty(objectName);
			WebElement element = getDriver().findElement(By.xpath(object));
			text = element.getText();
		}
		catch(Exception ex) {
			Assert.fail("unable to getText for Element "+objectName);
		}
		return text;
	}
	
	public void selectDropDownText(String objectName,String value) {
		try {
			if(value!=null) {
			String object = objectProperty(objectName);
			Select select = new Select(getDriver().findElement(By.xpath(object)));
			select.selectByVisibleText(value);
			}
			else {
				Assert.fail("Send Value to TextBox is Empty");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void waitforElement(String objectName) {
		try {
			String object = objectProperty(objectName);
			WebElement element = getDriver().findElement(By.xpath(object));
			if(element.isDisplayed() == true) {
				System.out.println(objectName+" is displayed");
			}
			
		}
		catch(Exception ex) {
			Assert.fail("Element is not visible "+objectName);
		}
	}
	
	public void windowsHandles() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle : allWindowHandles)
		{
		System.out.println("Window handle - > " + handle);
		}
	}
}
