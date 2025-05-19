package palywright;

import static org.testng.Assert.assertTrue;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class Practice5 {

	@Test
	public void videocapturePractice() throws InterruptedException {
		
		Playwright playwright=Playwright.create();
		Browser browser=playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				);
		BrowserContext context= browser.newContext(new NewContextOptions()
				.setRecordVideoDir(Paths.get("videoREC/")).setRecordVideoSize(640, 1080));
		Page page=browser.newPage();
		page.navigate("https://www.amazon.in/");

		Locator locator=page.locator("//*[@placeholder='Search Amazon.in']"); //using locator variable
		locator.fill("chetan bhagat");          //to send values
		page.locator("//*[@id='nav-search-submit-button']").click(); //without using locator variable
		Thread.sleep(1000);
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("./screenshot/image.png")));
		assertTrue(page.title().contains("chetan bhagat"),"title got matched"); //using testng assertions
		page.locator("//span[.='Get It by Tomorrow']").click();
		PlaywrightAssertions.assertThat(page).hasTitle("Amazon.in: Chetan Bhagat - Get It By Tomorrow"); //using playwright assertions
		page.locator("//span[.='Get It by Tomorrow']").click();
		assertTrue(page.title().toLowerCase().contains("chetan bhagat"),"title got matched");
		
		context.close();
		page.close(); 
		playwright.close();
	}
}
