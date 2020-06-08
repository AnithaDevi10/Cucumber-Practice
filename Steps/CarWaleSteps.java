package Steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CarWaleSteps {
	
	
	public ChromeDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public static List<Integer> vehicleKM;
	public  static List<Integer> sortedKM;
	public static  Map<Integer, String> kmmap;

		@Given("Go to https://www.carwale.com/")
	public void  openLink() {
			System.setProperty("webdriver.chrome.silentOutput", "true");
			
System.setProperty("webdriver.chrome.driver","/Users/kumanananitha/eclipse-workspace/Selenium/drivers/chromedriver" );
driver=new ChromeDriver();
driver.get("https://www.carwale.com/");
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
DesiredCapabilities cap = new DesiredCapabilities();
cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
options.merge(cap);

driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 wait = new WebDriverWait(driver, 30);
 js =  (JavascriptExecutor) driver;


		}

	@Given("Click on Used")
	public void clickOnUsed() {
driver.findElementByXPath("//li[@data-tabs='usedCars']").click();	
	   	}

	@Given("Select the City as Chennai")
	public void selectCityChennai() throws InterruptedException {
		 driver.findElementById("usedCarsList").sendKeys("Chennai",Keys.ENTER);
		 Thread.sleep(500);

	   	}

	@Given("Select budget min 8L and max 12L and Click Search")
	public void selectBudgetClickSearch() throws InterruptedException {
		driver.findElementById("budgetBtn").click();
	      driver.findElementById("minInput").sendKeys("8",Keys.ENTER);
	      driver.findElementById("maxInput").sendKeys("12",Keys.ENTER);
	      driver.findElementById("btnFindCar").click();
	      Thread.sleep(1000);
	      driver.findElementByXPath("//input[@id='placesQuery']").sendKeys("Chennai,Tamil Nadu",Keys.TAB);
	      Thread.sleep(500);
	      driver.findElementByXPath("//span[@id='closeLocIcon']").click(); 
	      Thread.sleep(1000);

	}

	@Given("Select Cars with Photos under Only Show Cars With")
	public void selectCarsPhotos() {
		 wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Cars with Photos']")));
	      driver.findElementByXPath("//span[text()='Cars with Photos']").click();

	}

	@Given("Select Manufacturer as Hyundai --> Creta")
	public void select_Manufacturer_as_Creta() throws InterruptedException {
		 Thread.sleep(500);
	      WebElement Hyundai=driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']/span)[1]");
	      js.executeScript("arguments[0].click()",Hyundai);
	      WebElement Creta=driver.findElementByXPath("//span[text()='Creta']");
	      js.executeScript("arguments[0].click()",Creta);

	   	}

	@Given("Select Fuel Type as Petrol")
	public void select_Fuel_Type_as_Petrol() {
		WebElement fueltype=driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]");
	      js.executeScript("arguments[0].click()",fueltype);
	//driver.findElementByXPath("//h3[text()[normalize-space()='Fuel Type']]").click();
	      WebElement petrol=driver.findElementByXPath("//span[text()='Petrol']");
	      js.executeScript("arguments[0].click()",petrol);
 
	}

	@Given("Select Best Match as KM: Low to High")
	public void select_Best_Match_as() {
		WebElement match= driver.findElementById("sort");
		Select select=new Select(match);
		select.selectByValue("2");
	
	}
    


	@Given("Validate the Cars are listed with KMs Low to High")
	public void validate_the_Cars_are_listed_with_KMs_Low_to_High() {
		List<WebElement> noofcars=driver.findElementsByXPath("//span[contains(@class,'slkms vehicle')]");
       // Map<Integer, String> kmmap = new LinkedHashMap<Integer,String>();
         vehicleKM=new ArrayList<Integer>();       
         kmmap = new LinkedHashMap<Integer,String>();
        for(int i=0;i< noofcars.size();i++)
        { 
       	 String rawKM=noofcars.get(i).getText(); 
       	int intKM = Integer.parseInt(rawKM.replaceAll("\\D", ""));
       	vehicleKM.add(intKM);
       kmmap.put(intKM,rawKM );
          }
        sortedKM=new ArrayList<Integer> (vehicleKM);
        Collections.sort(sortedKM);
        if(vehicleKM.equals(sortedKM))
       	 System.out.println("Huyandi car kms are sorted");
        else System.out.println("Huyandi car kms are not sorted");
       System.out.println("less km ran car" + sortedKM.get(0));

	     }

	@Given("Add the least KM ran car to Wishlist")
	public void add_the_least_KM_ran_car_to_Wishlist() throws InterruptedException {
//		Thread.sleep(1000);
//		Actions builder= new Actions(driver);
//		WebElement shortListIcon = driver.findElementByXPath("//span[@class='shortlist-icon--inactive shortlist'][1]");
//		builder.moveToElement(shortListIcon).click().perform();

		 Integer lessKM=sortedKM.get(0);
		 String lessKMcar="";
	
		for(Entry<Integer,String> eachEntry:kmmap.entrySet())
		 { 
			if(eachEntry.getKey().equals(lessKM))
			{ lessKMcar=eachEntry.getValue();  }
		 
		 }
		 System.out.println("Less km utilised by Creta Car"+lessKMcar);
		 Thread.sleep(500);
		 //span[@class='shortlist-icon--inactive shortlist'])[1]
		 WebElement lesskmwishlist=driver.findElementByXPath("//span[@class='shortlist-icon--inactive shortlist'][1]");

		
		// WebElement lesskmwishlist=driver.findElementByXPath("//span[text()='"+lessKMcar+"']/ancestor::div[@class='card-detail-block']/preceding-sibling::div//span[contains(@class,'shortlist-icon')]");
//				//span[text()='8,000 km']/ancestor::div[@class='card-detail-block']/preceding-sibling::div//span[contains(@class,'shortlist-icon')]		 
		js.executeScript("arguments[0].click();",lesskmwishlist);
 	
	}

	@Given("Go to Wishlist and Click on More Details")
	public void go_to_Wishlist_and_Click_on_More_Details() throws InterruptedException {
		Thread.sleep(500);
		driver.findElementByXPath("//li[@data-action='ShortList&CompareWindow_Click']").click();
	   // driver.findElementByXPath("//li[@class='action-box shortlistBtn']").click();
	   Thread.sleep(500);
		driver.findElementByPartialLinkText("More details »").click();

		//driver.findElementByLinkText("More details »").click();
  
	}

	@When("Print all the details under Overview in the Same way as displayed in application")
	public void print_all_the_details_under_Overview_in_the_Same_way_as_displayed_in_application() {
		Set<String> winset=driver.getWindowHandles();
		List<String> winlist=new ArrayList<String> (winset);
		driver.switchTo().window(winlist.get(1));
		List <WebElement> cardesc=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		List <WebElement> carspec=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		Map<String,String> cardetails= new LinkedHashMap<String,String> ();
		System.out.println("description size"+cardesc.size());
		System.out.println("specification size"+carspec.size());
		for(int i=0;i<cardesc.size();i++)
		{ String desc=cardesc.get(i).getText();
		   String spec=carspec.get(i).getText();
		   cardetails.put(desc,spec);
		   }
		for( Entry<String,String> eachEntry:cardetails.entrySet())
		{
			System.out.println(eachEntry.getKey()+"---->"+eachEntry.getValue());
		}
  
	
	}

	@Then("Close the browser")
	public void close_the_browser() {
		driver.quit();

	   	}


}
