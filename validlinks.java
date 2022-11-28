package Testvalidlinks;

import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class validlinks {
public static void main(String[] args) {
		
		{
			System.setProperty("webdriver.chrome.driver","/Users/admin/Downloads/driverchrome");
			WebDriver driver=new ChromeDriver();
			
			driver.manage().window().maximize();
			
			driver.get("https://www.wikipedia.org/");
			int n =20;
			int count;
			List<WebElement> links=driver.findElements(By.tagName("a"));
			
			System.out.println("Total links are "+links.size());
			
			for(int i=0;i<links.size();i++)
			{
				
				WebElement ele= links.get(i);
				
				String url=ele.getAttribute("href");
				
				verifyLinkActive(url);
				
			}
			
		}
	}
		
		public static void verifyLinkActive(String linkUrl)
		{
			 int count = 0;
   		     int n = 20;
	        try 
	        {
	        	@SuppressWarnings("resource")
				FileWriter file = new FileWriter("/Users/admin/eclipse/java-2021-12/Eclipse.app/testvalid/src/test/java/output.txt",true);
	        	
	           URL url = new URL(linkUrl);
	           
	           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	           
	           httpURLConnect.setConnectTimeout(3000);
	           
	           httpURLConnect.connect();
	           
	           if(httpURLConnect.getResponseCode()==200)
	           {
	               file.write(linkUrl + " " +httpURLConnect.getResponseMessage());
	               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	            }
	          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	           {
	        	  if(httpURLConnect.getResponseCode()>400) {
	        		  
	        		  
	        		 count++;
					if(count==n) {
					
						
						System.out.println("count reached");

					}
					
	        		  
	        	  }
	        	 
	              System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	            }
	        } catch (Exception e) {
	           
	        }
	    } 
	}

