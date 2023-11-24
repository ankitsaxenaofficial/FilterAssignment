package com.assignment.automation.filter;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {
	WebDriver driver;

	public PageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void selectFilter(String filterName, String... options) throws InterruptedException {

		int counter = 0;

		//Find Category: Reason for taking it as a list to verify user not providing invalid Category values
		List<WebElement> category = driver.findElements(By.xpath("//legend[@data-testid='desktop-filter-group-name'][text()=' "+ filterName + " ']"));

		//If user provided invalid Category other than: "Deals, Brands, Operating System" then it will not proceed further
		if(category.size()!=0) {
			for(WebElement cat : category) {
				cat.click();
				
				//List for all the Checkboxes to be selected: Dynamic based on the Category provided
				List<WebElement> checkBoxes =  driver.findElements(By.xpath("//div[@aria-label='"+filterName+"']//span[@class='mat-checkbox-label']"));

				//List of all the checkboxes already selected
				List<WebElement> selectedCheckboxes = driver.findElements(By.xpath("//div[@aria-label='"+filterName+"']//span[@class='mat-checkbox-label']//preceding-sibling::span/input"));
				Thread.sleep(5000);

				//If condition to verify if "No" option is provided for the selection
				if(options.length>0) {
					for(String op : options) {		

						//If "all" the options are provided to be selected.
						if(op.equals("all")) {						
							for(WebElement e: checkBoxes) {
								System.out.println("Clicking options from: " + filterName + " Category --> " + e.getText());
								e.click();
							}
						}
						else {

							//This will take care of the particular options provided to be selected
							List<WebElement> checkBoxesSelect =  driver.findElements(By.xpath("//span[@class='filter-display-name'][text()=' "+ op + " ']"));
							if(checkBoxesSelect.size()!=0) {
								for(WebElement e : checkBoxesSelect) {
									String name = e.getText().trim();
									if(name.equals(op)) {
										System.out.println("Clicking Options from: "+filterName+ " Category --> " + e.getText());
										e.click();
									}
									else {
										System.out.println(op + " option not present in the "+filterName+ " Cateogry");
									}
								}
							}
							else {
								System.out.println("Invalid option "+ op + " provided for the "+filterName+ " Cateogry");
							}
						}
					}
				}

				/* This section will identify if already some options were selected from the particular category and then again from the same category No option is given.
		It will then give the number of options already selected.
				 */
				else {
					for(WebElement e : selectedCheckboxes) {
						if(e.isSelected()) {
							counter = counter+1;					
						}				
					}	
					if(counter == 0) {
						System.out.println(filterName+ " Category : No options are selected");	
					}
					else {
						System.out.println(filterName+ " Category : No options provided for selection but "+ counter + " option(s) were already selected previously");
					}
				}
			}
		}
		else {
			System.out.println("Invalid Category: " + filterName +" provided");
		}
	}
}
