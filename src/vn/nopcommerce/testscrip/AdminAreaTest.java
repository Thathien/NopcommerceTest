package vn.nopcommerce.testscrip;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import vn.nopcommerce.common.Driver;
import vn.nopcommerce.testobject.TranslateScreen;
import vn.nopcommerce.util.ExcelUtils;

public class AdminAreaTest {
	private Object object;
	/* Declare global driver */
	WebDriver driver = null;
	
	@BeforeClass
	public void preTest() {
		final String browser = "chrome";
		String url = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
		
		// Init global Driver
		driver = Driver.initDriver(browser);
		
		// Access URL
		driver.get(url);
		
		// Set maximize
		driver.manage().window().maximize();
	}
	
	/**
	 * <b>Description:</b> Test translate EN -> VN<br>
	 * <b>Condition:</b> Input data normal<br>
	 * <b>Verify:</b> Verify translate correct
	 * @throws Exception 
	 */
	
	@Test
	public void testCase_E0001 () throws Exception {
		
		//login
		WebElement div_btn_login=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_LOGIN));
		div_btn_login.click();
		
		sleep();
		// customer -> list
		WebElement div_span_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER));
		div_span_cusstomer.click();
		
		sleep();
		//select item customer
		WebElement div_span_cusstomer_crud=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER_CRUD));
		div_span_cusstomer_crud.click();
		
		WebElement DIV_BTN_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_ADD_CUSTOMER));
		DIV_BTN_ADD_CUSTOMER.click();
		
		String email=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A1");
		String password=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A2");
		String firstname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A3");
		String lastname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A4");
		String gender=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A5");
		String birthdate=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A6");
		String companyname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A7");
		String istaxexemp=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A8");
		String[] newsletter=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A9").split(",");
		String [] role=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A10").split(",");
		String [] managerofvendor=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A11").split(",");
		String active=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A12");
		String admincomment=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "A13");
		
		sleep();
		WebElement div_input_email_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_EMAIL_ADD_CUSTOMER));
		div_input_email_add_cusstomer.sendKeys(email);
		
		sleep();
		WebElement div_input_password_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_PASSWORD_ADD_CUSTOMER));
		div_input_password_add_cusstomer.sendKeys(password);
		
		sleep();
		WebElement div_input_first_name_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_FIRST_NAME_ADD_CUSTOMER));
		div_input_first_name_add_cusstomer.sendKeys(firstname);
		
		sleep();
		WebElement div_input_last_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_LAST_NAME_ADD_CUSTOMER));
		div_input_last_name_add_cusstomer.sendKeys(lastname);
		
		sleep();
		if(gender.equals("MALE")) {
			WebElement div_radio_gender_male_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_MALE_ADD_CUSTOMER));
			div_radio_gender_male_add_cusstomer.click();
		}else {
			WebElement div_radio_gender_female_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_FEMALE_ADD_CUSTOMER));
			div_radio_gender_female_add_cusstomer.click();
		}
		sleep();
		WebElement div_datepicker_dayobirth_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_DATEPIKER_DAYOBIRTH_ADD_CUSSTOMER));
		div_datepicker_dayobirth_add_cusstomer.sendKeys(birthdate);
		
		sleep();
		WebElement div_input_company_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_COMPANY_NAME_ADD_CUSSTOMER));
		div_input_company_name_add_cusstomer.sendKeys(companyname);
		
		if(istaxexemp.equals("TRUE")) {
			WebElement div_textbox_tax_ememt_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKBOX_TAX_EXEMT_ADD_CUSSTOMER));
			div_textbox_tax_ememt_add_cusstomer.click();
			sleep();
		}
		
		if(newsletter.length!=0) {
			for(int i=0;i<newsletter.length;i++) {
				if(newsletter[i].equals("TEST STORE 2")) {
					WebElement div_listbox_news_letter_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LISTBOX_NEWS_LETER_ADD_CUSSTOMER));
					div_listbox_news_letter_add_cusstomer.click();
					WebElement div_listbox_news_letter_select_2_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_LI_NEWS_LETER_SELECT_2_ADD_CUSSTOMER));
					div_listbox_news_letter_select_2_add_cusstomer.click();
				}
			}
		}
		if(role.length!=0) {
			for(int i=0;i<role.length;i++) {
				if(role[i].equals("GUEST")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_guest_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_GUEST_ADD_CUSSTOMER));
					div_listbox_customer_role_select_guest_add_cusstomer.click();
				}
				if(role[i].equals("ADMIN")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_admin_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_ADMIN_ADD_CUSSTOMER));
					div_listbox_customer_role_select_admin_add_cusstomer.click();
				}
			}
		}
		if(managerofvendor.length!=0) {
			for(int i=0;i<managerofvendor.length;i++) {
				if(managerofvendor[i].equals("VENDOR 1")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER.click();
				}
				if(managerofvendor[i].equals("VENDOR 2")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER.click();
				}
			}
		}
		
		if(active.equals("TRUE")) {
			WebElement DIV_CHECKED_ACTIVE_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKED_ACTIVE_ADD_CUSSTOMER));
			DIV_CHECKED_ACTIVE_ADD_CUSSTOMER.click();
		}
		
		WebElement DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER));
		DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER.sendKeys(admincomment);
		
		WebElement DIV_BTN_SAVE_ADD_CUSSTOMER= driver.findElement(By.xpath(TranslateScreen.DIV_BTN_SAVE_ADD_CUSSTOMER));
		DIV_BTN_SAVE_ADD_CUSSTOMER.click();
		
//		WebElement DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER));
		WebElement DIV_BTN_SAVE_SUSCCED_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_SAVE_SUSCCED_ADD_CUSSTOMER));
		
//		String expectedResult = "Please enter a valid email address.";
//		String actualResult=DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER.getText();
		String expectedResult="×\n" + 
				"The new customer has been added successfully.";
		String actualResult=DIV_BTN_SAVE_SUSCCED_ADD_CUSSTOMER.getText().trim();
		System.out.println("---- : "+actualResult +": +++");
		Assert.assertEquals(actualResult, expectedResult);

	}
	
	@Test
	public void testCase_E0002 () throws Exception {
		driver.navigate().refresh();
//		//login
//		WebElement div_btn_login=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_LOGIN));
//		div_btn_login.click();
//		
//		sleep();
//		// customer -> list
//		WebElement div_span_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER));
//		div_span_cusstomer.click();
//		
//		sleep();
//		//select item customer
//		WebElement div_span_cusstomer_crud=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER_CRUD));
//		div_span_cusstomer_crud.click();
		
		WebElement DIV_BTN_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_ADD_CUSTOMER));
		DIV_BTN_ADD_CUSTOMER.click();
		
		String email=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B1");
		String password=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B2");
		String firstname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B3");
		String lastname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B4");
		String gender=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B5");
		String birthdate=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B6");
		String companyname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B7");
		String istaxexemp=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B8");
		String[] newsletter=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B9").split(",");
		String [] role=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B10").split(",");
		String [] managerofvendor=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B11").split(",");
		String active=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B12");
		String admincomment=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "B13");
		
		sleep();
		WebElement div_input_email_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_EMAIL_ADD_CUSTOMER));
		div_input_email_add_cusstomer.sendKeys(email);
		
		sleep();
		WebElement div_input_password_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_PASSWORD_ADD_CUSTOMER));
		div_input_password_add_cusstomer.sendKeys(password);
		
		sleep();
		WebElement div_input_first_name_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_FIRST_NAME_ADD_CUSTOMER));
		div_input_first_name_add_cusstomer.sendKeys(firstname);
		
		sleep();
		WebElement div_input_last_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_LAST_NAME_ADD_CUSTOMER));
		div_input_last_name_add_cusstomer.sendKeys(lastname);
		
		sleep();
		if(gender.equals("MALE")) {
			WebElement div_radio_gender_male_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_MALE_ADD_CUSTOMER));
			div_radio_gender_male_add_cusstomer.click();
		}else {
			WebElement div_radio_gender_female_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_FEMALE_ADD_CUSTOMER));
			div_radio_gender_female_add_cusstomer.click();
		}
		sleep();
		WebElement div_datepicker_dayobirth_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_DATEPIKER_DAYOBIRTH_ADD_CUSSTOMER));
		div_datepicker_dayobirth_add_cusstomer.sendKeys(birthdate);
		
		sleep();
		WebElement div_input_company_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_COMPANY_NAME_ADD_CUSSTOMER));
		div_input_company_name_add_cusstomer.sendKeys(companyname);
		
		if(istaxexemp.equals("TRUE")) {
			WebElement div_textbox_tax_ememt_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKBOX_TAX_EXEMT_ADD_CUSSTOMER));
			div_textbox_tax_ememt_add_cusstomer.click();
			sleep();
		}
		
		if(newsletter.length!=0) {
			for(int i=0;i<newsletter.length;i++) {
				if(newsletter[i].equals("TEST STORE 2")) {
					WebElement div_listbox_news_letter_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LISTBOX_NEWS_LETER_ADD_CUSSTOMER));
					div_listbox_news_letter_add_cusstomer.click();
					WebElement div_listbox_news_letter_select_2_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_LI_NEWS_LETER_SELECT_2_ADD_CUSSTOMER));
					div_listbox_news_letter_select_2_add_cusstomer.click();
				}
			}
		}
		if(role.length!=0) {
			for(int i=0;i<role.length;i++) {
				if(role[i].equals("GUEST")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_guest_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_GUEST_ADD_CUSSTOMER));
					div_listbox_customer_role_select_guest_add_cusstomer.click();
				}
				if(role[i].equals("ADMIN")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_admin_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_ADMIN_ADD_CUSSTOMER));
					div_listbox_customer_role_select_admin_add_cusstomer.click();
				}
			}
		}
		if(managerofvendor.length!=0) {
			for(int i=0;i<managerofvendor.length;i++) {
				if(managerofvendor[i].equals("VENDOR 1")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER.click();
				}
				if(managerofvendor[i].equals("VENDOR 2")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER.click();
				}
			}
		}
		
		if(active.equals("TRUE")) {
			WebElement DIV_CHECKED_ACTIVE_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKED_ACTIVE_ADD_CUSSTOMER));
			DIV_CHECKED_ACTIVE_ADD_CUSSTOMER.click();
		}
		
		WebElement DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER));
		DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER.sendKeys(admincomment);
		
		WebElement DIV_BTN_SAVE_ADD_CUSSTOMER= driver.findElement(By.xpath(TranslateScreen.DIV_BTN_SAVE_ADD_CUSSTOMER));
		DIV_BTN_SAVE_ADD_CUSSTOMER.click();
		
//		WebElement DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER));
		WebElement DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER));
		
//		String expectedResult = "Please enter a valid email address.";
//		String actualResult=DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER.getText();
		String expectedResult="Please enter a valid email address.";
		String actualResult=DIV_SPAN_EMAIL_ERROR_ADD_CUSTOMER.getText().trim();
		System.out.println("---- :"+actualResult +": +++");
		Assert.assertEquals(actualResult, expectedResult);

	}
	
	@Test
	public void testCase_E0003 () throws Exception {
		driver.navigate().refresh();
//		//login
//		WebElement div_btn_login=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_LOGIN));
//		div_btn_login.click();
//		
//		sleep();
//		// customer -> list
//		WebElement div_span_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER));
//		div_span_cusstomer.click();
//		
//		sleep();
//		//select item customer
//		WebElement div_span_cusstomer_crud=driver.findElement(By.xpath(TranslateScreen.DIV_SPAN_CUSTOMER_CRUD));
//		div_span_cusstomer_crud.click();
		
//		WebElement DIV_BTN_ADD_CUSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_BTN_ADD_CUSTOMER));
//		DIV_BTN_ADD_CUSTOMER.click();
		
		String email=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C1");
		String password=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C2");
		String firstname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C3");
		String lastname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C4");
		String gender=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C5");
		String birthdate=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C6");
		String companyname=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C7");
		String istaxexemp=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C8");
		String[] newsletter=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C9").split(",");
		String [] role=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C10").split(",");
		String [] managerofvendor=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C11").split(",");
		String active=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C12");
		String admincomment=ExcelUtils.readData("TestData/InputData/Data_Add_Cusstomer.xlsx","Sheet1", "C13");
		
		sleep();
		WebElement div_input_email_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_EMAIL_ADD_CUSTOMER));
		div_input_email_add_cusstomer.sendKeys(email);
		
		sleep();
		WebElement div_input_password_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_PASSWORD_ADD_CUSTOMER));
		div_input_password_add_cusstomer.sendKeys(password);
		
		sleep();
		WebElement div_input_first_name_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_FIRST_NAME_ADD_CUSTOMER));
		div_input_first_name_add_cusstomer.sendKeys(firstname);
		
		sleep();
		WebElement div_input_last_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_LAST_NAME_ADD_CUSTOMER));
		div_input_last_name_add_cusstomer.sendKeys(lastname);
		
		sleep();
		if(gender.equals("MALE")) {
			WebElement div_radio_gender_male_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_MALE_ADD_CUSTOMER));
			div_radio_gender_male_add_cusstomer.click();
		}else {
			WebElement div_radio_gender_female_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_RADIO_GENDER_FEMALE_ADD_CUSTOMER));
			div_radio_gender_female_add_cusstomer.click();
		}
		sleep();
		WebElement div_datepicker_dayobirth_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_DATEPIKER_DAYOBIRTH_ADD_CUSSTOMER));
		div_datepicker_dayobirth_add_cusstomer.sendKeys(birthdate);
		
		sleep();
		WebElement div_input_company_name_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_INPUT_COMPANY_NAME_ADD_CUSSTOMER));
		div_input_company_name_add_cusstomer.sendKeys(companyname);
		
		if(istaxexemp.equals("TRUE")) {
			WebElement div_textbox_tax_ememt_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKBOX_TAX_EXEMT_ADD_CUSSTOMER));
			div_textbox_tax_ememt_add_cusstomer.click();
			sleep();
		}
		
		if(newsletter.length!=0) {
			for(int i=0;i<newsletter.length;i++) {
				if(newsletter[i].equals("TEST STORE 2")) {
					WebElement div_listbox_news_letter_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LISTBOX_NEWS_LETER_ADD_CUSSTOMER));
					div_listbox_news_letter_add_cusstomer.click();
					WebElement div_listbox_news_letter_select_2_add_cusstomer= driver.findElement(By.xpath(TranslateScreen.DIV_LI_NEWS_LETER_SELECT_2_ADD_CUSSTOMER));
					div_listbox_news_letter_select_2_add_cusstomer.click();
				}
			}
		}
		if(role.length!=0) {
			for(int i=0;i<role.length;i++) {
				if(role[i].equals("GUEST")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_guest_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_GUEST_ADD_CUSSTOMER));
					div_listbox_customer_role_select_guest_add_cusstomer.click();
				}
				if(role[i].equals("ADMIN")) {
					WebElement div_listbox_customer_role_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LIXBOX_CUSTOMER_ROLE_ADD_CUSSTOMER));
					div_listbox_customer_role_add_cusstomer.click();
					WebElement div_listbox_customer_role_select_admin_add_cusstomer=driver.findElement(By.xpath(TranslateScreen.DIV_LI_CUSTOMER_ROLE_SELECT_ADMIN_ADD_CUSSTOMER));
					div_listbox_customer_role_select_admin_add_cusstomer.click();
				}
			}
		}
		if(managerofvendor.length!=0) {
			for(int i=0;i<managerofvendor.length;i++) {
				if(managerofvendor[i].equals("VENDOR 1")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_1_ADD_CUSSTOMER.click();
				}
				if(managerofvendor[i].equals("VENDOR 2")) {
					WebElement DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER));
					DIV_SELECT_MANAGER_OF_VENDOR_ADD_CUSSTOMER.click();
					WebElement DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER));
					DIV_OPTION_MANAGER_OF_VENDOR_2_ADD_CUSSTOMER.click();
				}
			}
		}
		
		if(active.equals("TRUE")) {
			WebElement DIV_CHECKED_ACTIVE_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_CHECKED_ACTIVE_ADD_CUSSTOMER));
			DIV_CHECKED_ACTIVE_ADD_CUSSTOMER.click();
		}
		
		WebElement DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER));
		DIV_AREA_ADMIN_COMMENT_ADD_CUSSTOMER.sendKeys(admincomment);
		
		WebElement DIV_BTN_SAVE_ADD_CUSSTOMER= driver.findElement(By.xpath(TranslateScreen.DIV_BTN_SAVE_ADD_CUSSTOMER));
		DIV_BTN_SAVE_ADD_CUSSTOMER.click();
		
		WebElement DIV_DATEPIKER_DAYOBIRTH_ERROR_ADD_CUSSTOMER=driver.findElement(By.xpath(TranslateScreen.DIV_DATEPIKER_DAYOBIRTH_ERROR_ADD_CUSSTOMER));
		
		String expectedResult="The value '"+birthdate+"' is not valid for Date of birth.";
		String actualResult=DIV_DATEPIKER_DAYOBIRTH_ERROR_ADD_CUSSTOMER.getText().trim();
		System.out.println("---- :"+actualResult +": +++");
		Assert.assertEquals(actualResult, expectedResult);

	}
	public static void sleep() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
