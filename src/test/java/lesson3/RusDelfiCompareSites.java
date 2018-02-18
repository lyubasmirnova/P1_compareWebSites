package lesson3;
/*check of 5 first articles on rus.delfi.lv & m.rus.delfi.lv for FireFox browser. Test includes:
 - header comparision
 - comment amount*/

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.ws.WebEndpoint;
import java.util.List;


public class RusDelfiCompareSites {
    private String DELFI_MAIN = "http://rus.delfi.lv";
    private String DELFI_MOBILE = "http://m.rus.delfi.lv";


    @Test
    public void openPageMain() {
        runMainWebSite(DELFI_MAIN);
    }

    @Test
    public void openMobileMain() {
        runMobileWebSite(DELFI_MOBILE);
    }

    @Test
    public void compareSites() {
        System.setProperty("webdriver.gecko.driver", "C:/temp/geckodriver/geckodriver.exe");
        WebDriver driverMain = new FirefoxDriver();
        WebDriver driverMobile = new FirefoxDriver();
        driverMain.get(DELFI_MAIN);
        List<WebElement> elementsMain = driverMain.findElements(By.tagName("h3"));

        driverMobile.get(DELFI_MOBILE);
        List<WebElement> elementsMobile = driverMobile.findElements(By.className("md-mosaic-title"));

        for (int i=0; i<=4; i++) {
            String elementMainCommentTxt="";
            String elementMobileCommentTxt="";

            WebElement elementH3 = elementsMain.get(i);
            WebElement elementMainTitle = elementH3.findElement(By.className("top2012-title"));
            String elementMainTitleTxt = elementMainTitle.getText();

            WebElement elementDiv = elementsMobile.get(i);
            WebElement elementMobileTitle = elementDiv.findElement(By.className("md-scrollpos"));
            String elementMobileTitleTxt = elementMobileTitle.getText();

            try {
                WebElement elementMainComment = elementH3.findElement(By.className("comment-count"));
                elementMainCommentTxt = elementMainComment.getText();
            } catch (NoSuchElementException e) {
            }

            try {
                WebElement elementMobileComment = elementDiv.findElement(By.className("commentCount"));
                elementMobileCommentTxt = elementMobileComment.getText();
            } catch (NoSuchElementException e) {
            }

            int elementID=i+1;

            Assert.assertEquals("Article "+ elementID +" titles are not equal", elementMainTitleTxt, elementMobileTitleTxt);
            Assert.assertEquals("Article "+ elementID +" comments are not equal", elementMainCommentTxt, elementMobileCommentTxt);
            System.out.println("Article "+ elementID + " test is passed");
         }
    }

    public void runMainWebSite(String url) {
        System.setProperty("webdriver.gecko.driver", "C:/temp/geckodriver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        driver.get(url);

        //List<WebElement> elements = driver.findElements(By.xpath("//h3[contains@class, 'top2012-title']"));
        List<WebElement> elements = driver.findElements(By.tagName("h3"));
        System.out.println("Article count:"+elements.size());

        for (int i=0; i<=elements.size()-1; i++){
            WebElement elementH3 = elements.get(i);
            WebElement elementTitle = elementH3.findElement(By.className("top2012-title"));
            String elementH3Txt = elementH3.getText();
            String elementTitleTxt = elementTitle.getText();
            //System.out.println(i+")"+elementH3Txt);
            System.out.println(i+")"+elementTitleTxt);
            try {
                WebElement elementComment = elementH3.findElement(By.className("comment-count"));
                String elementCommentTxt = elementComment.getText();
                System.out.println(i+")"+elementCommentTxt);
            } catch (NoSuchElementException e) {
                System.out.println(i+")"+"No comment");
            }
        }
    }

    public void runMobileWebSite(String url) {
        System.setProperty("webdriver.gecko.driver", "C:/temp/geckodriver/geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().maximize();
        driver.get(url);

        //List<WebElement> elements = driver.findElements(By.xpath("//h3[contains@class, 'top2012-title']"));
        List<WebElement> elements = driver.findElements(By.className("md-mosaic-title"));
        System.out.println("Article count:"+elements.size());

        for (int i=0; i<=elements.size()-1; i++){
            WebElement elementDiv = elements.get(i);
            WebElement elementTitle = elementDiv.findElement(By.className("md-scrollpos"));
            String elementH3Txt = elementDiv.getText();
            String elementTitleTxt = elementTitle.getText();
            System.out.println(i + ")" + elementTitleTxt);
            try {
                WebElement elementComment = elementDiv.findElement(By.className("commentCount"));
                String elementCommentTxt = elementComment.getText();
                System.out.println(i + ")" + elementCommentTxt);
            } catch (NoSuchElementException e) {
                System.out.println(i + ")" + "No comment");
            }
        }
    }
}
