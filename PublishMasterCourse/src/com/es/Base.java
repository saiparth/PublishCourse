package com.es;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base extends Jpanel {
	static WebDriver wd;
	static String courseId = "";

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\libs\\chromedriver.exe");
		wd = new ChromeDriver();// PhantomJSDriver();
		wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Opened Browser");
		// String[] output=input();
		login();
		for (int i = 0; i <= 10; i++) {
			try {
				ComposeMail(courseId);
				TypeMail();
			} catch (Exception e) 
			{
				System.err.println(e);
			}
			
		}
	}

	public static void login() {
		String url = "";
		String user = "";
		String pwd = "@1";
		wd.get(url);
		System.out.println("opening " + url);
		By username = By.id("loginname");
		By password = By.id("password");
		By loginbutton = By.cssSelector("button");
		By close = By.cssSelector("button[class='close']");
		By logoutArrow = By.cssSelector("div>span[class='fa fa-chevron-down txtwelcome']");
		By signOut = By.cssSelector("a[title='Sign out']");
		By SuccessMessage = By.xpath("span[@class='messagesucessfont']");
		By ddwnIcon = By.xpath("//a[@title='Communicate']/following-sibling::a/span");
		By mail = By.partialLinkText("Mail");
		wd.findElement(close).click();
		wd.findElement(username).sendKeys(user);
		wd.findElement(password).sendKeys(pwd);
		wd.findElement(loginbutton).click();
		// enter inside course
		System.out.println("Logged in " + wd.getTitle());
		wd.findElement(By.partialLinkText(courseId)).click();
		// mail
		javascriptClick(wd.findElement(ddwnIcon));
		wd.findElement(mail).click();
	}

	public static void ComposeMail(String courseName) {
		By composeButton = By.xpath("//button[text()='Compose New']");
		By To = By.cssSelector("input[value='To']");
		By frame = By.id("srcIframe");
		By popUpFrame = By.id("openModalPopupframe");
		WebDriverWait w = new WebDriverWait(wd, 60);
		String val = String.format("//span[contains(@class,'_ctl0_InnerPageContent_GlobalRecipientList') and text()='"
				+ courseName + "']/preceding::input[1]");
		By checkbox = By.xpath(val);
		By addRecipent = By.cssSelector("input[value='Add Recipients']");
		wd.findElement(composeButton).click();
		wd.switchTo().frame(wd.findElement(frame));
		wd.findElement(To).click();
		wd.switchTo().defaultContent();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		w.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
		wd.switchTo().frame(wd.findElement(popUpFrame));
		// ((JavascriptExecutor)wd).executeScript("arguments[0].scrollIntoView(true)",
		// checkbox);
		w.until(ExpectedConditions.elementToBeClickable(checkbox));
		javascriptClick(wd.findElement(checkbox));
		w.until(ExpectedConditions.elementToBeClickable(addRecipent));
		javascriptClick(wd.findElement(addRecipent));
		wd.switchTo().defaultContent();
	}

	public static void TypeMail() {
		By frame = By.id("srcIframe");
		By virwSoiurce = By.id("viewsource");
		By textarea = By.id("_ctl0_InnerPageContent_txtHTMLEditor_textarea");
		By send = By.cssSelector("input[value='Send']");
		By sub = By.cssSelector("input[name='_ctl0:InnerPageContent:txtSubject']");
		wd.switchTo().frame(wd.findElement(frame));
		wd.findElement(sub).sendKeys("AutoMail");
		wd.findElement(virwSoiurce).click();
		wd.findElement(textarea).clear();
		wd.findElement(textarea).sendKeys("AutoMail");
		WebDriverWait w = new WebDriverWait(wd, 60);
		w.until(ExpectedConditions.elementToBeClickable(send));
		wd.findElement(send).click();
		wd.switchTo().defaultContent();
	}

	public static void javascriptClick(WebElement ele) {
		((JavascriptExecutor) wd).executeScript("arguments[0].click();", ele);
	}

}


