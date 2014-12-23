/*
 *******************************************************************************
 *
 * Purpose: Web page test step Base
 *
 *******************************************************************************
 * Copyright Monstrenyatko 2014.
 *
 * Distributed under the MIT License.
 * (See accompanying file LICENSE or copy at http://opensource.org/licenses/MIT)
 *******************************************************************************
 */

package com.monstrenyatko.desert.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.monstrenyatko.desert.util.PropertyLoader;


/**
 * Abstract class representation of a Page Steps in the UI. Page object pattern
 */
public abstract class Steps {

	private WebDriver driver;

	protected WebDriver getDriver() {
		return driver;
	}

	protected Steps(WebDriver driver) {
		this.driver = driver;
	}

	protected <T extends PageObject> T currentPageAt(final Class<T> pageObjectClass) {
		return PageFactory.initElements(getDriver(), pageObjectClass);
	}

	protected String loadProperty(final String name) {
		return PropertyLoader.loadProperty(name);
	}

	public void waitABit(final long delayInMilliseconds) {
		try {
			Thread.sleep(delayInMilliseconds);
		} catch (InterruptedException e) {
		}
	}
}
