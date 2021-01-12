package Engine;

import Driver.DriverClass;
import Driver.PropertiesReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KeyWordEngine {

    public WebDriver driver;
    public Properties prop;
    PropertiesReader propertiesReader;

    public static Workbook book;
    public static Sheet sheet;

    public DriverClass driverClass;
    public WebElement element;
    public final String SCENARIO_SHEET_PATH = "C:\\Users\\JuNiOr\\IdeaProjects\\KeywordDrivenFrameworkGit\\src\\test\\java\\Elements\\Elements.xlsx";

    WebDriverWait wait =null;

    Logger logger = Logger.getLogger(KeyWordEngine.class);

    public WebElement findElement(String element_Name,String sheetName) {

        String locatorType = null;

        String locatorValue = null;

        FileInputStream file = null;

        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);

        int k = 0;

        for(int i = 0 ; i<sheet.getLastRowNum() ; i++){

            String element_name_fromExcel = sheet.getRow(i+1).getCell(k).toString().trim();

            if(element_name_fromExcel.equalsIgnoreCase(element_Name)){
                locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                break;
            }
        }


        switch (locatorType){

            case "id":
                element =  driver.findElement(By.id(locatorValue));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(locatorValue));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(locatorValue));
                break;
            case "text":
                element = driver.findElement(By.xpath("//*[text()=\""+element_Name+"\"]"));
            default:
                break;
        }
        return element;
    }

    public void sendKeysFunctionality(String elementName , String sheetName , String value){

        logger.info("Sending keys in the " + elementName);

        WebElement sendkeysElemenet = findElement(elementName , sheetName);

        wait = new WebDriverWait(driver , 10);

        waitUntilVisible(sendkeysElemenet);

        sendkeysElemenet.clear();
        ReadExcelReusableMethods readExcelReusableMethods = new ReadExcelReusableMethods();

        sendkeysElemenet.sendKeys(readExcelReusableMethods.getSpecificData(value));

    }

    public void clickFunctionality(String elementName , String sheetName ){

        logger.info("Clicking on the " + elementName);

        WebElement  clickElement = findElement(elementName , sheetName);

        waitUntilClickable(clickElement );

        clickElement.click();

    }

    public void verifyElementsCount(String elementName ,int expectedCount, String sheetName ){

        logger.info("Verifying element  " + elementName + " is " + expectedCount);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement>  clickElement = findElements(elementName , sheetName);


        Assert.assertTrue("Expected count is " + expectedCount + " but actual count is " + clickElement.size(),clickElement.size() == expectedCount  );

    }

//    Handling the dropdown using select class
    public void handleDropdown(String elementName  , String elementToChoose , String sheetName){

        logger.info("In the dropdown choosing following text " + elementName);

        WebElement  clickElement = findElement(elementName , sheetName);

        Select select = new Select(clickElement);

        waitUntilClickable(clickElement );

        ReadExcelReusableMethods readExcelReusableMethods = new ReadExcelReusableMethods();

        elementToChoose = readExcelReusableMethods.getSpecificData(elementToChoose);

        select.selectByVisibleText(elementToChoose);

    }

    public void clickOnTheOneElementInTheList(String List1Name , String List2Name ,String valueToClick , String sheetName){

        logger.info("Clicking on the element in the list   " + List1Name);

        List<WebElement>  list1Elements = findElements(List1Name , sheetName);
        List<WebElement>  list2Elements = findElements(List2Name , sheetName);

        System.out.println( list1Elements.size());
        for(int i = 0 ; i< list1Elements.size() ; i++){

            System.out.println(list1Elements.get(i).getText());
            System.out.println(valueToClick);

            if(list1Elements.get(i).getText().equals(valueToClick)){
                list2Elements.get(i).click();
            }
        }
    }

    public void handleAlert(String AlertAction){
        try {
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        switch (AlertAction){
            case "Okay":
                driver.switchTo().alert().accept();
                break;
            case "Dismiss":
                driver.switchTo().alert().dismiss();
                break;
        }
    }

    public void verifyDataIsDisplayed(String elementName , String expectedResult , String sheetName){

        logger.info("Verifying the data is displayed or not " + elementName);
        WebElement  clickElement = findElement(elementName , sheetName);

        Assert.assertEquals(clickElement.getText() , expectedResult);

    }



    public void clickingOnEveryElementInTheList(String elementName , String sheetName){

        logger.info("Removing every element in the following element name  " + elementName);
        List<WebElement> allElements = findElements(elementName , sheetName);

        for(int i = 0 ; i < allElements.size() ; i++){

            allElements.get(i).click();

            try{
                driver.switchTo().alert().accept();
            }catch (Exception e){

            }


        }

    }

//     After removing the data need to test is the data removed. using this in the address page checking all the address
    public void verifyDataIsRemoved(String elementName , String expectedResult , String sheetName){

        logger.info("Verifying the data is removed or not " + elementName);
        List<WebElement> clickElement = findElements(elementName , sheetName);

        ReadExcelReusableMethods readExcelReusableMethods = new ReadExcelReusableMethods();

        expectedResult= readExcelReusableMethods.getSpecificData(expectedResult);

        for(int i = 0 ; i<clickElement.size() ; i++){

            Assert.assertTrue( !clickElement.get(i).equals(expectedResult));
        }

    }

//    Waiting until the element to clickable before clicking on the element I am waiting for each element this will reduce the
//      count of the error.
    public void waitUntilClickable(WebElement waitElement){

        wait = new WebDriverWait(driver , 10);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(waitElement));
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    public void waitUntilVisible(WebElement waitElement){

        wait = new WebDriverWait(driver , 10);

        try {
            wait.until(ExpectedConditions.visibilityOf(waitElement));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void hoverOver( String element_name , String SheetName){
        BasicConfigurator.configure();
        logger = Logger.getLogger("devpinoyLogger");
        logger.debug("Hover over on " + element_name);

        WebElement overElement = findElement(element_name , SheetName);

        Actions action = new Actions(driver);

        action.moveToElement(overElement).perform();

    }

    public void startSteps(String whichAction){

        switch (whichAction){

            case"Open":
                driverClass = new DriverClass();
                propertiesReader = new PropertiesReader();
                prop = propertiesReader.init_properties();
                driver = driverClass.init_driver(prop.getProperty("browser"));
                break;

            case"Enter URL":
                driver.get( prop.getProperty("url"));
                break;

            case "quit":
                logger.info("------------------------------QUIT---------------------------");
                driver.quit();
                break;

            default:
                break;
        }

    }

    public List<WebElement> findElements(String element_Name,String sheetName) {

        List<WebElement> allElements = new ArrayList<>();


        String locatorType = null;

        String locatorValue = null;

        FileInputStream file = null;

        try {
            file = new FileInputStream(SCENARIO_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        }  catch (Exception e) {
            e.printStackTrace();
        }

        sheet = book.getSheet(sheetName);

        int k = 0;

        System.out.println(sheet.getLastRowNum());
        for(int i = 0 ; i<sheet.getLastRowNum() ; i++){

            String element_name_fromExcel = sheet.getRow(i+1).getCell(k).toString().trim();

            if(element_name_fromExcel.equalsIgnoreCase(element_Name)){
                locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                break;
            }
        }

        wait = new WebDriverWait(driver , 10);
        switch (locatorType){
            case "id":
//                Added try catch because if I am looking for the element which is removed and I would like to get the element and verify element is removed
//                  in this step wait.until is throwing an error that is why added try catch block.
                 try{
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                }catch (Exception e){

                }

                allElements =  driver.findElements(By.id(locatorValue));
                break;
            case "xpath":
                try{
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                }catch (Exception e){

                }
                allElements =  driver.findElements(By.xpath(locatorValue));
                break;
            case "css":
                try{
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                }catch (Exception e){

                }
                allElements =  driver.findElements(By.cssSelector(locatorValue));
                break;

        }


        return allElements;
    }

}
