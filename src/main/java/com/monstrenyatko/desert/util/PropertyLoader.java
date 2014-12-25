/*
 *******************************************************************************
 *
 * Purpose: Property loader implementation
 *
 *******************************************************************************
 * Copyright Monstrenyatko 2014.
 *
 * Distributed under the MIT License.
 * (See accompanying file LICENSE or copy at http://opensource.org/licenses/MIT)
 *******************************************************************************
 */

package com.monstrenyatko.desert.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * Class that extracts properties from the prop file.
 */
public class PropertyLoader {

	public static Capabilities loadCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(loadProperty("browser.name"));
		return capabilities;
	}

	public static String loadProperty(final String name) {
		String res = get().load(name);
		if (res == null) {
			Assert.fail("Can't get property, name: "+name);
		}
		return res;
	}

	public static Integer loadPropertyInteger(final String name) {
		String value = loadProperty(name);
		try {
			return new Integer(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Assert.fail("Can't parse Integer property, name: "+name);
		}
		return null;
	}

	private static final String PROPERTIES_LIBRARY = "/desert-base.properties";
	private static final String PROPERTIES_DEFAULT = "/desert.properties";
	private static final String PROPERTIES_ENV_VAR = "config.path";
	private static PropertyLoader instance;
	private Properties propertyLibrary;
	private Properties propertyDefault;
	private Properties property;

	private String load(final String name) {
		String res = null;
		if (property != null) {
			res = property.getProperty(name);
		}
		if (res == null && propertyDefault != null) {
			res = propertyDefault.getProperty(name);
		}
		if (res == null && propertyLibrary != null) {
			res = propertyLibrary.getProperty(name);
		}
		return res;
	}

	synchronized private static PropertyLoader get() {
		if (instance == null) {
			try {
				instance = new PropertyLoader();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Can't initialize property loader");
			}
		}
		return instance;
	}

	private PropertyLoader() throws IOException {
		{
			Properties tmp = new Properties();
			tmp.load(PropertyLoader.class.getResourceAsStream(PROPERTIES_LIBRARY));
			propertyLibrary = tmp;
		}
		{
			Properties tmp = new Properties();
			InputStream stream = PropertyLoader.class.getResourceAsStream(PROPERTIES_DEFAULT);
			if (stream != null) {
				tmp.load(stream);
			}
			propertyDefault = tmp;
		}
		{
			String propertyPath = System.getProperty(PROPERTIES_ENV_VAR);
			if (propertyPath != null) {
				Properties tmp = new Properties();
				tmp.load(PropertyLoader.class.getResourceAsStream(propertyPath));
				property = tmp;
			}
		}
	}
}