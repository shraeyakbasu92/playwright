package palywright;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;

public class Practice2 {
	
	@Test
	public void locatorPractice() throws InterruptedException {
		
		Playwright playwright=Playwright.create();
		Browser browser=playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				);
		Page page=browser.newPage();
		page.navigate("https://www.amazon.in/");
		Locator locator=page.locator("//*[@placeholder='Search Amazon.in']"); //using locator variable
		locator.fill("chetan bhagat");          //to send values
		page.locator("//*[@id='nav-search-submit-button']").click(); //without using locator variable
		Thread.sleep(1000);
		assertTrue(page.title().contains("chetan bhagat"),"title got matched"); //using testng assertions
		page.locator("//span[.='Get It Today']").click();
		PlaywrightAssertions.assertThat(page).hasTitle("Amazon.in: Chetan Bhagat - Get It Today"); //using playwright assertions
		page.locator("//span[.='Get It Today']").click();
		assertTrue(page.title().toLowerCase().contains("chetan bhagat"),"title got matched");
		page.close();
	}
}
