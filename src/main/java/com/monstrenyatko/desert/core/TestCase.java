/*
 *******************************************************************************
 *
 * Purpose: Web page test case Base
 *
 *******************************************************************************
 * Copyright Monstrenyatko 2014.
 *
 * Distributed under the MIT License.
 * (See accompanying file LICENSE or copy at http://opensource.org/licenses/MIT)
 *******************************************************************************
 */

package com.monstrenyatko.desert.core;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.monstrenyatko.desert.util.PropertyLoader;
import com.monstrenyatko.desert.util.WebDriverFactory;


/**
 * Base class for all test classes
 */
public abstract class TestCase {

	private static Capabilities capabilities;

	private WebDriver driver;

	protected WebDriver getDriver() {
		return driver;
	}

	@ClassRule
	public static ExternalResource webDriverProperties = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			capabilities = PropertyLoader.loadCapabilities();
		};
	};

	@Rule
	public ExternalResource webDriver = new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			driver = WebDriverFactory.getDriver(capabilities);
		}

		@Override
		protected void after() {
			WebDriverFactory.dismissAll();
		};
	};

	protected String loadProperty(final String name) {
		return PropertyLoader.loadProperty(name);
	}
}
