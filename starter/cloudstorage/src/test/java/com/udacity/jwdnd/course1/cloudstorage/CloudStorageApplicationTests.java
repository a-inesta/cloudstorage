package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testUserSignupLoginAndAddNote() {
		String username = "qwegf";
		String password = "dasdqwefaasd";
		String firstname = "Jerry";
		String lastname = "Lee";
		String[] titles = new String[5];
		String[] descriptions = new String[5];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = "title " + i;
			descriptions[i] = "description" + i;
		}


		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage sp = new SignupPage(driver);
		sp.signup(firstname,lastname,username,password);

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage lp = new LoginPage(driver);
		lp.login(username,password);

		HomePage hp = null;
		for (int i = 0; i < titles.length; i++) {
			driver.get("http://localhost:" + this.port + "/home");
			hp = new HomePage(driver);
			hp.addNewNote(titles[i],descriptions[i]);

			driver.get("http://localhost:" + this.port + "/result");
			ResultPage rp = new ResultPage(driver);
			rp.toHomePage();
		}


		driver.get("http://localhost:" + this.port + "/home");
		hp = new HomePage(driver);

		List<WebElement> noteTitle = driver.findElements(By.className("noteTitle"));
		List<WebElement> noteDescription = driver.findElements(By.className("noteDescription"));
		for (int i = 0; i < noteTitle.size(); i++) {
			assertEquals(titles[i], noteTitle.get(i).getText());
			assertEquals(descriptions[i], noteDescription.get(i).getText());
		}


	}


}
