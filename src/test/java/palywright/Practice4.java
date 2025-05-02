package palywright;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;

public class Practice4 {
	
	@Test
	public void handlingdropdown() throws InterruptedException {
	Playwright playwright=Playwright.create();
	Browser browser=playwright.chromium().launch(
			new LaunchOptions().setHeadless(false)
			);
	Page page=browser.newPage();
	page.navigate("https://www.amazon.in/");
	Locator dropdown= page.locator("//select[@class='nav-search-dropdown searchSelect nav-progressive-attrubute nav-progressive-search-dropdown']");
	// select by visible text
	dropdown.selectOption("Appliances");
	page.locator("//input[@id='nav-search-submit-button']").click();
	Thread.sleep(2000);
	System.out.println(page.title());
	assertTrue(page.title().contains("Amazon.in: Appliances"));
	page.goBack();
	// select by index
	Thread.sleep(1000);
	dropdown.selectOption(new SelectOption().setIndex(3));
	page.locator("//input[@id='nav-search-submit-button']").click();
	Thread.sleep(2000);
	System.out.println(page.title());
	assertTrue(page.title().contains("Fashion Store: Buy Clothing, Footwear and Accessories Online - Amazon.in"));
	page.goBack();
	// select by value
	Thread.sleep(1000);
	dropdown.selectOption(new SelectOption().setValue("search-alias=amazon-pharmacy"));
	page.locator("//input[@id='nav-search-submit-button']").click();
	Thread.sleep(2000);
	System.out.println(page.title());
	assertTrue(page.title().contains("Buy Health & Personal Care Appliances Online at Best Prices In India"));
	page.goBack();
		
	page.close();
	}


}
