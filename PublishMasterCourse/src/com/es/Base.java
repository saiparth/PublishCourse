package com.es;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base extends Jpanel{

	public static void main(String[] args) throws Exception {
		String path=property("driverpath");
		System.setProperty("phantomjs.binary.path",path );
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setJavascriptEnabled(true);
		WebDriver wd=new PhantomJSDriver();
		System.out.println("Opened Browser");
		String[] output=input();
	
		 String url=output[0];
		 String user=output[1];
		 String pwd=output[2];
		 String courseId=output[3];
		 
		 wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wd.get(url);
		 System.out.println("opening "+url);
		 String message="Course published successfully";
		 
		  By username=By.id("username");
		  By password=By.id("password");
		  By loginbutton=By.cssSelector("button");
		  By Cmenu=By.xpath("//img[@src='../../../images/admin/icn_contextual.gif']");
		  By textBox=By.id("txtCourseDetail");
		  By radioButton=By.id("rdPegasusID");
		  By searchButton=By.xpath("//img[@onclick='searchCourse();']");
		  By PublishCourse=By.xpath("//div[@id='referenceId2']//a[contains(text(),'Publish Master Course')]");
		  By publishAcceptButton=By.id("imgbtnSave");
		  //By cancelButton=By.xpath("//*[@onclick='javascript:window.close();']");
		  By logoutArrow=By.cssSelector("div>span[class='fa fa-chevron-down txtwelcome']");
		  By signOut=By.cssSelector("a[title='Sign out']");
		  By SuccessMessage=By.xpath("span[@class='messagesucessfont']");
		  
		  wd.findElement(username).sendKeys(user);
		  wd.findElement(password).sendKeys(pwd);
		  wd.findElement(loginbutton).click();
		  
		  System.out.println("Logged in "+wd.getTitle());
		  wd.switchTo().frame(wd.findElement(By.id("ifrmright")));
		  
		 
		  wd.findElement(radioButton).click();
		  wd.findElement(textBox).sendKeys(courseId);
  		  WebElement search=wd.findElement(searchButton);
  		  search.click();
  		  
		  JavascriptExecutor js= (JavascriptExecutor) wd ;
		  js.executeScript("javascript:window.scrollBy(250,0)" );
		  
		  
		  if(wd.findElement(Cmenu).isDisplayed())
		  {
			  JOptionPane.showMessageDialog( null, "Please check your course ID", null, JOptionPane.ERROR_MESSAGE);
		  }
		  wd.findElement(Cmenu).click();
		  
		  wd.findElement(PublishCourse).click();
		  
		  String parent=wd.getWindowHandle();
		  
		  	Set<String>iterator=wd.getWindowHandles();
			Iterator<String>winIterator=iterator.iterator();
			long count=0;
			while (winIterator.hasNext()) 
				{
					String s=winIterator.next().toString();
					wd.switchTo().window(s);
					if (count==1) 
						{
							break;
						}
					count++;
				}
			  
		 wd.findElement(publishAcceptButton).click();
		 System.out.println("Clicked on publish button");
		 wd.switchTo().window(parent);
		  
		  String actualMessage=wd.findElement(SuccessMessage).getAttribute("innerHTML");
		  
		  if (actualMessage.contains(message)) 
			  {
				  JOptionPane.showMessageDialog( null, actualMessage, null, JOptionPane.INFORMATION_MESSAGE);
			  }  
		  
		  else
			  {
				  JOptionPane.showMessageDialog( null, "No message dispalyed after publishing course", null, JOptionPane.ERROR_MESSAGE);
			  }
		  wd.findElement(logoutArrow).click();
		  wd.findElement(signOut).click();
						  
		  wd.quit();
		}

	}


