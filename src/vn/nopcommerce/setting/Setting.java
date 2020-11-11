package vn.nopcommerce.setting;

public class Setting {

	/* Write console log mode: true (On), false (Off) */
	private static final boolean WRITE_CONSOLE_LOG_MODE = true;
	
	/* Chrome driver path */
	public static final String CHROME_DRIVER_PATH = "resources/driver/ChromeDriver/85.0.4183.83/chromedriver.exe";
	
	/* Firefox driver path */
	public static final String FIREFOX_DRIVER_PATH = "resources/driver/FirefoxDriver/geckodriver.exe";
	
	/* IE driver path */
	public static final String IE_DRIVER_PATH = "resources/driver/IEDriver/64bit/IEDriverServer.exe";

	/* Chrome driver property */
	public static final String CHROME_DRIVER_PROP = "webdriver.chrome.driver";
	
	/* FIREFOX driver property */
	public static final String FIREFOX_DRIVER_PROP = "webdriver.gecko.driver";
	
	/* IE driver property */
	public static final String IE_DRIVER_PROP = "webdriver.ie.driver";
	
	/* Path: Test Data */
	public static final String TEST_DATA_PATH = "resources/";
	
	/* Path: Test Report */
	public static final String TEST_REPORT_PATH = "resources/";


	/**
	 * <p><b>Check write log console mode</b></p>
	 * 
	 * @return true (write log), false (no log)
	 */
	public static boolean isWriteLogConsole() {
		return WRITE_CONSOLE_LOG_MODE;
	}
}
