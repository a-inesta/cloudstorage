package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.Pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.Pages.LoginPage;
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

			hp.toHomePage();

		}


		driver.get("http://localhost:" + this.port + "/home");
		hp = new HomePage(driver);
		hp.toNoteTab();
		List<WebElement> noteTitle = driver.findElements(By.className("noteTitle"));
		List<WebElement> noteDescription = driver.findElements(By.className("noteDescription"));


		//默认notetab没有被选中, isDisplayed() = false, 因此webdriver获取不到文本
		//修改页面当前元素,或者当前元素父元素的CSS,使元素的isDisplayed()值为true.
		//使用getAttribute("innerHTML")获取文本值
		for (int i = 0; i < noteTitle.size(); i++) {
			assertEquals(titles[i], noteTitle.get(i).getAttribute("innerHTML"));
			assertEquals(descriptions[i], noteDescription.get(i).getAttribute("innerHTML"));
		}
	}

	@Test
	public void testEditNote() {
		String username = "qwegfffdd";
		String password = "dasdqwefaasd";
		String firstname = "Jerry";
		String lastname = "Lee";
		String originalTitle = "originalTitle";
		String newTitle = "newTitle";
		String originalDescription = "originalDescription";
		String newDescription = "newDescription";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage sp = new SignupPage(driver);
		sp.signup(firstname,lastname,username,password);

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage lp = new LoginPage(driver);
		lp.login(username,password);

		HomePage hp = null;
		driver.get("http://localhost:" + this.port + "/home");
		hp = new HomePage(driver);
		hp.addNewNote(originalTitle,originalDescription);
		hp.toHomePage();

		hp.editNote(originalTitle,newTitle, newDescription);


		driver.get("http://localhost:" + this.port + "/home");
		hp = new HomePage(driver);
		hp.toNoteTab();
		List<WebElement> noteTitle = driver.findElements(By.className("noteTitle"));
		List<WebElement> noteDescription = driver.findElements(By.className("noteDescription"));


		//默认notetab没有被选中, isDisplayed() = false, 因此webdriver获取不到文本
		//修改页面当前元素,或者当前元素父元素的CSS,使元素的isDisplayed()值为true.
		//使用getAttribute("innerHTML")获取文本值
		for (int i = 0; i < noteTitle.size(); i++) {
			assertEquals(newTitle, noteTitle.get(i).getAttribute("innerHTML"));
			assertEquals(newDescription, noteDescription.get(i).getAttribute("innerHTML"));
		}
	}


	@Test
	public void testDeleteNote() {
		String username = "qwegfffddaa";
		String password = "dasdqwefaasd";
		String firstname = "Jerry";
		String lastname = "Lee";
		String originalTitle = "test";
		String originalDescription = "originalDescription";

		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage sp = new SignupPage(driver);
		sp.signup(firstname,lastname,username,password);

		driver.get("http://localhost:" + this.port + "/login");
		LoginPage lp = new LoginPage(driver);
		lp.login(username,password);

		HomePage hp = null;
		driver.get("http://localhost:" + this.port + "/home");
		hp = new HomePage(driver);
		hp.addNewNote(originalTitle,originalDescription);
		hp.toHomePage();

		List<WebElement> noteTitle = driver.findElements(By.className("noteTitle"));
		List<WebElement> noteDescription = driver.findElements(By.className("noteDescription"));

		assertEquals(noteTitle.size(),1);
		assertEquals(noteDescription.size(),1);

		hp.deleteNote(originalTitle);
		noteTitle = driver.findElements(By.className("noteTitle"));
		noteDescription = driver.findElements(By.className("noteDescription"));
		assertEquals(noteTitle.size(),0);
		assertEquals(noteDescription.size(),0);

	}
}
