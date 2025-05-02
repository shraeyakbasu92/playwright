package palywright;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class Practice3 {
	
	@Test
	public void mouseoperations() throws InterruptedException {
	Playwright playwright=Playwright.create();
	Browser browser=playwright.chromium().launch(
			new LaunchOptions().setHeadless(false)
			);
	Page page=browser.newPage();
	page.navigate("https://www.amazon.in/");
	page.locator("//span[contains(text(),'Account & Lists')]").hover(); //mouse hovering
	page.locator("//span[.='Your Account']").click();
	Thread.sleep(3000);
	assertTrue(page.title().toLowerCase().contains("your account"));
	page.goBack();
	page.locator("//span[contains(text(),'Account & Lists')]").hover();
	page.locator("//span[.='Your Account']").dblclick();     //mouse double click
	page.close();
	}

}
