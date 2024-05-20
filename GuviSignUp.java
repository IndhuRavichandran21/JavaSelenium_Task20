package task20;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GuviSignUp {

	public GuviSignUp() {
		//Set the Driver name and path of the driver
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\Desktop\\Indhu_ZenClass\\JavaPrograms\\eclipse-workspace\\WebDrivers\\chromedriver-win64\\chromedriver.exe");
	}
	
	WebDriver driver = new ChromeDriver(); //Instantiate Driver
	
	public void registration() {
		driver.navigate().to("https://www.guvi.in/");	//launch url
		driver.manage().window().maximize();  //maximize window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //wait 10s to load web elements
		driver.findElement(By.xpath("//li[contains(@class,'python-signup-btn')]")).click(); //click signup button
		driver.findElement(By.id("name")).sendKeys("Indhu"); //enter name
		driver.findElement(By.id("email")).sendKeys("qaautomationtest111@gmail.com"); //pass mail ID
		driver.findElement(By.id("password")).sendKeys("Automation@123"); //pass the password
		driver.findElement(By.id("mobileNumber")).sendKeys("9999999999"); //pass mobile no
		//if mail already exists then login else click sign up button
		List <WebElement> list1=driver.findElements(By.xpath("//div[text()=' Oh! A profile exists with this Email ID. ']"));
		if(list1.size()==1) {
			System.out.println("Profile already exists");
			driver.findElement(By.cssSelector("#email+div>a")).click();
			login();
		}
		
		else {
		driver.findElement(By.id("signup-btn")).click(); 
		
		//select dropdown values
		Select selectProfile = new Select(driver.findElement(By.id("profileDrpDwn")));
		selectProfile.selectByValue("Looking for a career");
		Select selectDegree = new Select(driver.findElement(By.id("degreeDrpDwn")));
		selectDegree.selectByValue("B.E. / B.Tech. ECE");
		driver.findElement(By.id("year")).sendKeys("2019");
		driver.findElement(By.id("details-btn")).click(); //select submit
		
		driver.findElement(By.cssSelector(".left-body>a[class*='gmail-redirect']")).click();  //select open gmail to activate the account	
		List<WebElement> list2 = driver.findElements(By.xpath("//a[text()='Sign in']"));
		if(list2.size()==1) {
			driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		}
		driver.findElement(By.id("identifierId")).sendKeys("qaautomationtest111@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		
		//print the message if unable to sign in
		if(driver.findElement(By.xpath("//h1//span[text()='Couldn’t sign you in']")).isDisplayed())
		{
			System.out.println(driver.findElement(By.xpath("//h1//span[text()='Couldn’t sign you in']")).getText());
		}
		
		//get the page title and print successful registration message
		String pageTitle = driver.getTitle();
		if (pageTitle.contains("courses")) {
			
			System.out.println("Registration is successful");
			driver.navigate().to("https://www.guvi.in/");
			driver.findElement(By.xpath("//li[contains(@class,'python-login-btn')]")).click();
			login();
		}
			
		else 
			System.out.println("Registration is not yet complete");
		/*
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlContains ("https://www.guvi.in/courses/"));
		String pageUrl = driver.getCurrentUrl();
		if(pageUrl.contains("https://www.guvi.in/courses/")) {
			System.out.println("Registration is successful");
		}
		*/
	}
	}
	
	public void login() {
		//pass the credentials to login
		driver.findElement(By.id("email")).sendKeys("qaautomationtest111@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Automation@123");
		driver.findElement(By.id("login-btn")).click();
		
		//if page url contains the text "courses", print successful login message.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains("courses"));
		String pageUrl = driver.getCurrentUrl();
		if(pageUrl.contains("courses")) {
			System.out.println("User Logged in successfully");
		}
		else
			System.out.println("User is not logged in");
		
		driver.quit(); //quit the driver instance
	}
	
	
	
	public static void main(String[] args) {
		GuviSignUp obj = new GuviSignUp();
		obj.registration(); //method call for registration
	}

}
