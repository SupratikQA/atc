package com.demo.operation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.demo.config.DriverManager;
import com.demo.config.XlsReader;
import com.demo.entity.TestEntity;

public class TestExecutor {

	WebDriver wd;
	WebDriverWait wait;
	private static final Logger logs = Logger.getLogger(TestExecutor.class);

	@Test(priority = 1)
	public void scenario() throws FileNotFoundException, NumberFormatException, InterruptedException {

		String testcases[] = { "Testcase2.xlsx" };

		for (int i = 0; i < testcases.length; i++) {
			DriverManager dmgr = new DriverManager();
			wd = dmgr.loadConfig("chrome");

			List<TestEntity> ls = XlsReader.getTest(testcases[i]);

			logs.info("Tescase (" + testcases[i] + ") Execution ----> Begins");

			for (TestEntity te : ls) {
				switch (te.getKeyWord()) {
				case "OPEN":
					try {
						logs.info(te.getActionName() + " --> " + te.getTestData());
						wd.get(te.getTestData());
						logs.info(te.getSuccessMessage());
					} catch (Exception e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "CLICK":
					try {
						logs.info(te.getActionName() + " --> " + te.getIdentifier());
						wait = new WebDriverWait(wd, 100);
						WebElement clickableElement = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(te.getIdentifier())));
						clickableElement.click();
						logs.info(te.getSuccessMessage());
					} catch (Exception e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "SETVALUE":
					try {
						logs.info(te.getActionName() + " --> " + te.getTestData() + " on " + te.getIdentifier());
						wait = new WebDriverWait(wd, 100);
						WebElement typeableElement = wait
								.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(te.getIdentifier())));
						typeableElement.clear();
						typeableElement.sendKeys(te.getTestData());
						logs.info(te.getSuccessMessage());
					} catch (Exception e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "VERIFY":
					try {
						WebElement verifiableElement = wd.findElement(By.xpath(te.getIdentifier()));
						logs.info(te.getActionName() + " --> " + te.getIdentifier());
						if (te.getTestData().equalsIgnoreCase("verify_attribute")) {
							Assert.assertEquals(verifiableElement.getAttribute(te.getAdditionalIdentifier()),
									te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("verify_cssvalue")) {
							Assert.assertEquals(verifiableElement.getCssValue(te.getAdditionalIdentifier()),
									te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("verify_tag")) {
							Assert.assertEquals(verifiableElement.getTagName(), te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("verify_text")) {
							Assert.assertEquals(verifiableElement.getText(), te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("verify_input")) {
							JavascriptExecutor js = ((JavascriptExecutor) wd);
							String stext = (String) js.executeScript(te.getAdditionalIdentifier());
							if (stext.equalsIgnoreCase(te.getExpectedResult())) {
								logs.info(te.getTestData() + " Verified");
							}
						}
						logs.info(te.getSuccessMessage());
					} catch (Exception e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "SOFTVERIFY":
					logs.info(te.getActionName() + " --> " + te.getIdentifier());
					WebElement softVerifiableElement = wd.findElement(By.xpath(te.getIdentifier()));
					SoftAssert sf = new SoftAssert();
					try {
						if (te.getTestData().equalsIgnoreCase("sf_verify_attribute")) {
							sf.assertEquals(softVerifiableElement.getAttribute(te.getAdditionalIdentifier()),
									te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("sf_verify_cssvalue")) {
							sf.assertEquals(softVerifiableElement.getCssValue(te.getAdditionalIdentifier()),
									te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("sf_verify_tag")) {
							sf.assertEquals(softVerifiableElement.getTagName(), te.getExpectedResult());
						} else if (te.getTestData().equalsIgnoreCase("sf_verify_text")) {
							sf.assertEquals(softVerifiableElement.getText(), te.getExpectedResult());
						}
						logs.info(te.getSuccessMessage());
					} catch (Exception e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "WAIT":
					logs.info(te.getActionName() + " --> " + te.getIdentifier());
					wait = new WebDriverWait(wd, 100);
					if (te.getTestData().equalsIgnoreCase("visibility")) {
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(te.getIdentifier())));
						logs.info(te.getIdentifier() + " got located");
					} else if (te.getTestData().equalsIgnoreCase("invisibility")) {
						wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(te.getIdentifier())));
						logs.info(te.getIdentifier() + " got disappeared");
					}
					break;
				case "FIXWAIT":
					logs.info(te.getActionName());
					Thread.sleep(Integer.parseInt(te.getTestData()));
					logs.info(te.getSuccessMessage());
					break;
				case "JSEXEC":
					try {
						logs.info(te.getActionName() + " --> " + te.getTestData());
						JavascriptExecutor js = ((JavascriptExecutor) wd);
						js.executeScript(te.getTestData());
						logs.info("JS Executed");
					} catch (Exception e) {
					}
					break;
//				case "ACTION":
//					Actions action = new Actions(wd);
//					WebElement we = wd.findElement(By.xpath(te.getIdentifier()));
//					action.moveToElement(we).moveToElement(wd.findElement(By.xpath(te.getAdditionalIdentifier()))).click().build().perform();
//					break;
				case "ALERTACTION":
					if (te.getTestData().equalsIgnoreCase("accept")) {
						wd.switchTo().alert().accept();
					} else if (te.getTestData().equalsIgnoreCase("dismiss")) {
						wd.switchTo().alert().dismiss();
					}
					break;
				case "SCREENSHOT":
					File screenshot = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
					try {
						FileUtils.copyFile(screenshot, new File(
								System.getProperty("user.dir") + "/Screenshot/" + Math.random() + "screenshot.png"));
						logs.info(te.getSuccessMessage());
					} catch (IOException e) {
						logs.info(te.getFailureMessage());
					}
					break;
				case "SELECT":
					Select value = new Select(wd.findElement(By.xpath(te.getIdentifier())));
					if (te.getAdditionalIdentifier().equalsIgnoreCase("value")) {
						value.selectByValue(te.getTestData());
					} else if (te.getAdditionalIdentifier().equalsIgnoreCase("index")) {
						value.selectByIndex(Integer.parseInt(te.getTestData()));
					}
					break;
				default:
					logs.info("Invalid Action");
					break;
				}
			}
			logs.info("Tescase (" + testcases[i] + ") Execution ----> Ends");
			logs.info("-----------------------------------------------");
			wd.close();
		}
	}

	@AfterMethod
	public void end() {
		logs.info("DONE");
		wd.quit();
	}

}
