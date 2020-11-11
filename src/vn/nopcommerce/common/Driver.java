package vn.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import vn.nopcommerce.setting.Setting;

public class Driver {
	/**
	 * <p><b>Initialize the driver corresponding to the selected browser</b></p>
	 * 
	 * @param browser (<b>Chrome</b>/<b>Firefox</b>/<b>IE</b>)
	 * @return WebDriver
	 */
	public static WebDriver initDriver(String browser) {
		WebDriver driver = null;
		
		switch (browser.toLowerCase()) {
			case "firefox":
				System.setProperty(Setting.FIREFOX_DRIVER_PROP, Setting.FIREFOX_DRIVER_PATH);
				driver = new FirefoxDriver(); 
				break;
			case "ie":
			case "internetexplorer":
				System.setProperty(Setting.IE_DRIVER_PROP, Setting.IE_DRIVER_PATH);
				driver = new InternetExplorerDriver();
				break;
			case "chrome":
			default:
				System.setProperty(Setting.CHROME_DRIVER_PROP, Setting.CHROME_DRIVER_PATH);
				driver = new ChromeDriver(); 
		}

		return driver;
	}
}
