package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties p;
	

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException 
	{
		FileReader file=new FileReader("D:\\eclipse\\OpenCart\\src\\test\\resources\\config.properties");
		//loading properties files
			p=new Properties();
				p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.print("no matching os");
				return;
			}
			
			switch(br.toLowerCase()) {
			case "chrome": capabilities.setBrowserName("chrome");break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
			default :System.out.print("no matching browser");return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);		
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) {
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;		
			case "firefox" : driver=new FirefoxDriver(); break;
			default:System.out.print("Invalid browser");  return;
			}
		}
			
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL2"));
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	public String randomString() {
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
	}
	
	public String randomNumber() {
		String  generatednumber=RandomStringUtils.randomNumeric(10);
		return(generatednumber);
	}
	
	public String randomalphaNumeric() {
		String generatedString =RandomStringUtils.randomAlphabetic(3);
		String  generatednumber=RandomStringUtils.randomNumeric(3);
		return(generatedString+"@"+generatednumber);
	}
	
	public String  captureScreen(String tname) {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File sourceFile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" +timestamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
		
		
	}
}
