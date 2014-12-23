/*
 *******************************************************************************
 *
 * Purpose: Web page object Base
 *
 *******************************************************************************
 * Copyright Monstrenyatko 2014.
 *
 * Distributed under the MIT License.
 * (See accompanying file LICENSE or copy at http://opensource.org/licenses/MIT)
 *******************************************************************************
 */

package com.monstrenyatko.desert.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.monstrenyatko.desert.util.PropertyLoader;


/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public abstract class PageObject {

	private WebDriver driver;

	protected WebDriver getDriver() {
		return driver;
	}

	protected PageObject(WebDriver driver) {
		this.driver = driver;
		// add a latency  to allow all page elements have been loaded
		waitABit(PropertyLoader.loadPropertyInteger("timeout.pageConstructorLatency"));
	}

	protected String loadProperty(final String name) {
		return PropertyLoader.loadProperty(name);
	}

	protected WebElement $(final String xpath) {
		return getDriver().findElement(By.xpath(xpath));
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void waitABit(final long delayInMilliseconds) {
		try {
			Thread.sleep(delayInMilliseconds);
		} catch (InterruptedException e) {
		}
	}

	public boolean isTextPresent(final String v) {
		return !getDriver().findElements(
				By.xpath("//*[contains(text(), '" + v + "')]")).isEmpty();
	}
}
