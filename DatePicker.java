package task20;

import java.util.Date;
import java.text.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePicker {

	public DatePicker() {
		//set the driver name and path
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Desktop\\Indhu_ZenClass\\JavaPrograms\\eclipse-workspace\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
	}
	
	WebDriver driver = new ChromeDriver(); //chrome driver instantiation
	
	
	public void launchSite() throws ParseException {
		driver.navigate().to("https://jqueryui.com/datepicker/"); //launch url
		driver.manage().window().maximize(); //maximize the browser window
		
		WebElement frameElement = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frameElement); //switch to iframe
		
		driver.findElement(By.className("hasDatepicker")).click(); //click on the input field
		
		driver.findElement(By.xpath("//a[contains(@class,'ui-datepicker-next')]")).click(); //click on the next arrow
		
		String month = driver.findElement(By.className("ui-datepicker-month")).getText(); //get the text of month
		String year = driver.findElement(By.className("ui-datepicker-year")).getText();	//get the text of year
		
		WebElement element_date = driver.findElement(By.linkText("22")); 
		String date = element_date.getText(); //get the text of date
		element_date.click(); //select the date
		
		//print the selected month date and year
		String selectedDate =month+date+year;	 
		Date dateToFormat = new SimpleDateFormat("MMMMddyyyy").parse(selectedDate); //parsing: converting String to Date	
		System.out.println("The selected Date is "+new SimpleDateFormat("MM/dd/yyyy").format(dateToFormat)); //format: converting Date to String
		driver.quit(); //quit the driver instance
	}
	public static void main(String[] args) throws ParseException {
		DatePicker obj = new DatePicker(); //object creation
		obj.launchSite(); //method call

	}

}
