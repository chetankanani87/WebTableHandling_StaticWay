package webTableHandling;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import lib.BrowserDriverUtility;
import lib.EmailWithAttachmentUtility;
import lib.ExtentReportUtility;
import lib.ScreenshotUtility;

public class WebTableHandling_StaticWay {
	WebDriver dr;
	ExtentReports report;
	ExtentTest logger;
	WebElement ele;
	String path1, path2, path3;

	public WebTableHandling_StaticWay() {
		try {
			dr = BrowserDriverUtility.InvokeBrowser("webdriver.chrome.driver",
					"C:\\Chetan\\SeleniumSuite\\WebDrivers\\chromedriver.exe", "http://www.redbus.in");
			report = ExtentReportUtility.InvokeExtentReport();
			logger = report.createTest("WebTable Handling in Static Way - Test");
			path1 = ScreenshotUtility.CaptureScreenshot(dr, "1_MainPage");
			logger.pass("Main Page - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void AccessDateTable() {
		try {
			dr.findElement(By.xpath("//div[@class='fl search-box date-box gtm-onwardCalendar']")).click();
			Thread.sleep(2000);
			path2 = ScreenshotUtility.CaptureScreenshot(dr, "2_OnwardDateDropDownPage");
			logger.pass("Calender selection page - Screenshot taken.",
					MediaEntityBuilder.createScreenCaptureFromPath(path2).build());

			// Selection of today's date
			dr.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='wd day'][contains(text(),'30')]"))
					.click();
			path3 = ScreenshotUtility.CaptureScreenshot(dr, "3_DateSelectedPage");
			logger.pass("Main Page - Screenshot taken.", MediaEntityBuilder.createScreenCaptureFromPath(path3).build());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void tearDown() {
		try {
			EmailWithAttachmentUtility.SendEmail(
					"Test Case for WebTable or Calender Handling is Passed - File is uploaded successfully...!!!",
					"Congratulations...Bro!!!", path1, "Screenshot of Main page which is working fine...!!!", path2,
					"Screenshot of selected date is also working fine", path3, "Screenshot of selected date in From Date, which is working fine...!!!");
			report.flush();
			Thread.sleep(1000);
			dr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		WebTableHandling_StaticWay wh = new WebTableHandling_StaticWay();
		wh.AccessDateTable();
		wh.tearDown();
	}

}
