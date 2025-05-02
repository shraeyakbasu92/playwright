package palywright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Practice1 {
	
	@Test
	public void OpenCloseBrowser() {
		
		Playwright playwright=Playwright.create();
		Browser browser=playwright.chromium().launch(
				new LaunchOptions().setHeadless(false)
				);
		Page page=browser.newPage();
		page.navigate("https://www.amazon.in/");
		page.close();
	}

}
