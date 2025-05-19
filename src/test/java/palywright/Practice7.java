package palywright;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Practice7 {

@Test
public void windowswitch() throws InterruptedException {
		
		Playwright playwright=Playwright.create();
		Browser browser=playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				);
		BrowserContext context= browser.newContext();
		Page page=context.newPage();
		page.navigate("https://www.amazon.in/");
		Locator locator=page.locator("//*[@placeholder='Search Amazon.in']"); //using locator variable
		locator.fill("chetan bhagat");          //to send values
		page.keyboard().press("Enter");         // pressing keyboard enter key
		Thread.sleep(1000);
		assertTrue(page.title().contains("chetan bhagat"),"title got matched"); //using testng assertions
		Page newpage= context.waitForPage(()->
		{
		page.locator("//span[.='11 Rules For Life: Secrets to Level Up']").click();
		});
		newpage.waitForLoadState();
		assertTrue(newpage.title().contains("11 Rules For Life: Secrets to Level Up : Bhagat, Chetan: Amazon.in: Books"),"title matched");
		page.bringToFront();                // brings the control to the previous page
		System.out.println(page.title());
		playwright.close();
	}
}
