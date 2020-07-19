package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.util.ObjectUtils;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

	// test sign up
	@Test
	@Order(1)
	public void testSignUp() throws InterruptedException {
		// sign up
		driver.get("http://localhost:" + this.port + "/signup");
		driver.findElement(By.id("inputFirstName")).sendKeys("test24");
		driver.findElement(By.id("inputLastName")).sendKeys("test24");
		driver.findElement(By.id("inputUsername")).sendKeys("test24");
		driver.findElement(By.id("inputPassword")).sendKeys("test24");
		Thread.sleep(4000);
		driver.findElement(By.id("submit-button")).click();

		// redirect to login page
		driver.get("http://localhost:" + this.port + "/login");

		// login
		driver.findElement(By.id("inputUsername")).sendKeys("test24");
		driver.findElement(By.id("inputPassword")).sendKeys("test24");
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(4000);

		// check if user has successfully logged in
		Assertions.assertEquals("Home", driver.getTitle());
	}

	// test login
	@Test
	@Order(2)
	public void testValidLogin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/login");
		driver.findElement(By.id("inputUsername")).sendKeys("test24");
		driver.findElement(By.id("inputPassword")).sendKeys("test24");
		driver.findElement(By.id("submit-button")).click();
		Assertions.assertEquals("Home", driver.getTitle());
	}

	// test unauthorized access
	@Test
	@Order(3)
	public void testUnauthorizedLogin() throws InterruptedException {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	// test note
	@Test
	@Order(4)
	public void testNote() throws InterruptedException {
		// login
		driver.get("http://localhost:" + this.port + "/login");
		driver.findElement(By.id("inputUsername")).sendKeys("test24");
		driver.findElement(By.id("inputPassword")).sendKeys("test24");
		driver.findElement(By.id("submit-button")).click();

		// Switch to notes tab
		driver.findElement(By.id("nav-notes-tab")).click();
		Thread.sleep(2000);

		// Create a new note
		boolean noteCreated = false;
		try {
			driver.findElement(By.id("new-note")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("note-title")).sendKeys("note-title");
			driver.findElement(By.id("note-description")).sendKeys("note-description");
			driver.findElement(By.id("note-submit")).click();
			Thread.sleep(2000);
			noteCreated = true;
		} catch(Exception e) {
			System.out.println(e);
		}

		// Create another note
		try {
			driver.findElement(By.id("new-note")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("note-title")).sendKeys("note-title");
			driver.findElement(By.id("note-description")).sendKeys("note-description");
			driver.findElement(By.id("note-submit")).click();
			Thread.sleep(2000);
		} catch(Exception e) {
			System.out.println(e);
		}

		// Delete a note
		Thread.sleep(2000);
		boolean noteDeleted = false;
		WebElement notesTable = driver.findElement(By.id("userTable"));
		List<WebElement> noteLink = notesTable.findElements(By.tagName("a"));
		for (int i = 0; i < noteLink.size(); i++){
			WebElement deleteNoteButton = noteLink.get(i);
			deleteNoteButton.click();
			noteDeleted = true;
			break;
		}

		// Edit a note
		Thread.sleep(2000);
		notesTable = driver.findElement(By.id("userTable"));
		List<WebElement> noteList = notesTable.findElements(By.tagName("td"));
		boolean noteEdited = false;
		for (int i=0; i<noteList.size(); i++){
			WebElement row = noteList.get(i);
			WebElement editButton = null;
			editButton = row.findElement(By.tagName("button"));
			editButton.click();
			if (!ObjectUtils.isEmpty(editButton)){
				Thread.sleep(2000);
				driver.findElement(By.id("note-title")).sendKeys("-2");
				driver.findElement(By.id("note-description")).sendKeys("-2");
				driver.findElement(By.id("note-submit")).click();
				noteEdited = true;
				Assertions.assertEquals("Home", driver.getTitle());
				break;
			}
		}
		Assertions.assertTrue(noteCreated);
		Assertions.assertTrue(noteDeleted);
		Assertions.assertTrue(noteEdited);
	}

	// test credentials
	@Test
	@Order(5)
	public void testCredentials() throws InterruptedException {
		// login
		driver.get("http://localhost:" + this.port + "/login");
		driver.findElement(By.id("inputUsername")).sendKeys("test24");
		driver.findElement(By.id("inputPassword")).sendKeys("test24");
		driver.findElement(By.id("submit-button")).click();

		// Switch to credentials tab
		driver.findElement(By.id("nav-credentials-tab")).click();
		Thread.sleep(2000);

		// Create a new credential
		boolean credentialCreated = false;
		try {
			driver.findElement(By.id("new-credential")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("credential-url")).sendKeys("gmail.com");
			driver.findElement(By.id("credential-username")).sendKeys("test");
			driver.findElement(By.id("credential-password")).sendKeys("test");
			driver.findElement(By.id("credential-submit")).click();
			Thread.sleep(4000);
			credentialCreated = true;
		} catch(Exception e) {
			System.out.println(e);
		}

		try {
			driver.findElement(By.id("new-credential")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("credential-url")).sendKeys("gmail.com");
			driver.findElement(By.id("credential-username")).sendKeys("test");
			driver.findElement(By.id("credential-password")).sendKeys("test");
			driver.findElement(By.id("credential-submit")).click();
			Thread.sleep(4000);
		} catch(Exception e) {
			System.out.println(e);
		}

		// Delete a credential
		Thread.sleep(4000);
		boolean credentialDeleted = false;
		WebElement notesTable = driver.findElement(By.id("credentialTable"));
		List<WebElement> noteLink = notesTable.findElements(By.tagName("a"));
		for (int i = 0; i < noteLink.size(); i++){
			WebElement deleteNoteButton = noteLink.get(i);
			deleteNoteButton.click();
			credentialDeleted = true;
			break;
		}

		// Edit a credential
		Thread.sleep(2000);
		notesTable = driver.findElement(By.id("credentialTable"));
		List<WebElement> noteList = notesTable.findElements(By.tagName("td"));
		boolean credentialEdited = false;
		for (int i=0; i<noteList.size(); i++){
			WebElement row = noteList.get(i);
			WebElement editButton = null;
			editButton = row.findElement(By.tagName("button"));
			editButton.click();
			if (!ObjectUtils.isEmpty(editButton)){
				Thread.sleep(2000);
				driver.findElement(By.id("credential-url")).sendKeys(".uk");
				driver.findElement(By.id("credential-username")).sendKeys("-2");
				driver.findElement(By.id("credential-password")).sendKeys("-2");
				driver.findElement(By.id("credential-submit")).click();
				credentialEdited = true;
				Assertions.assertEquals("Home", driver.getTitle());
				break;
			}
		}
		Assertions.assertTrue(credentialCreated);
		Assertions.assertTrue(credentialDeleted);
		Assertions.assertTrue(credentialEdited);
	}
}
