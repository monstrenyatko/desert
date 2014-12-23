/*
 *******************************************************************************
 *
 * Purpose: WebDriver factory implementation
 *
 *******************************************************************************
 * Copyright Monstrenyatko 2014.
 *
 * Distributed under the MIT License.
 * (See accompanying file LICENSE or copy at http://opensource.org/licenses/MIT)
 *******************************************************************************
 */

package com.monstrenyatko.desert.util;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {
	private static WebDriver driver = null;

	synchronized public static WebDriver getDriver(Capabilities capabilities) {
		if (driver == null) {
			try {
				driver = createLocalDriver(capabilities);
				driver.manage().window().setSize(new Dimension(
					PropertyLoader.loadPropertyInteger("browser.window.width"),
					PropertyLoader.loadPropertyInteger("browser.window.height")
				));
				driver.manage().timeouts().implicitlyWait(
					PropertyLoader.loadPropertyInteger("timeout.implicitlyWait"),
					TimeUnit.MILLISECONDS
				);
				driver.manage().timeouts().pageLoadTimeout(
					PropertyLoader.loadPropertyInteger("timeout.pageLoadTimeout"),
					TimeUnit.MILLISECONDS
				);
			} catch (Exception e) {
				dismissAll();
				e.printStackTrace();
				Assert.fail("Driver setup problem");
			}
		}
		return driver;
	}

	synchronized public static void dismissAll() {
		dismissDriver();
	}

	private static WebDriver createLocalDriver(Capabilities capabilities) {
		String browserType = capabilities.getBrowserName();
		if (browserType.equals(BrowserType.FIREFOX))
			return new FirefoxDriver(capabilities);
		if (browserType.equals(BrowserType.IE))
			return new InternetExplorerDriver(capabilities);
		if (browserType.equals(BrowserType.CHROME))
			return new ChromeDriver(capabilities);
		if (browserType.equals(BrowserType.SAFARI))
			return new SafariDriver(capabilities);

		throw new Error("Unsupported browser type: " + browserType);
	}

	private static void dismissDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
