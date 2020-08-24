import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class RunTasks {


    public static void main(String[] args){
        openApp();
    }
    public static void openApp() {

        AndroidDriver driver = null;
        try {
            driver = Utils.setUp();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Properties props = Utils.loadVariables("config.property");


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(MobileBy.id("com.taobao.taobao:id/uik_mdButtonClose")).click();

        MobileElement myTaobao = Utils.waitForVisible(driver, (MobileElement) driver.findElement(MobileBy.xpath("//android.widget.FrameLayout[@content-desc=\"我的淘宝\"]/android.widget.ImageView")));
        myTaobao.click();

        MobileElement userName = Utils.waitForVisible(driver, (MobileElement) driver.findElement(MobileBy.id("com.taobao.taobao:id/aliuser_recommend_login_account_et")));
        userName.sendKeys(props.getProperty("username"));
        MobileElement confirm = Utils.waitForClickable(driver, (MobileElement) driver.findElement(MobileBy.id("com.taobao.taobao:id/aliuser_recommend_login_next_btn")));
        confirm.click();
        MobileElement pwd = Utils.waitForVisible(driver, (MobileElement) driver.findElement(MobileBy.id("com.taobao.taobao:id/aliuser_recommend_login_password_et")));
        pwd.sendKeys(props.getProperty("password"));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

//        get page source to check element, this page cannot be captured by Appium inspector or UIautomatorViewer
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String source = driver.getPageSource();
//        System.out.println(source);

        MobileElement login = Utils.waitForClickable(driver, (MobileElement) driver.findElement(MobileBy.id("com.taobao.taobao:id/aliuser_recommend_login_next_btn")));
        login.click();
//        wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, 120);
        Utils.waitForElement(driver, By.xpath("//android.widget.FrameLayout[@content-desc=\"芭芭农场\"]"));
        MobileElement farm = Utils.waitForClickable(driver, (MobileElement) driver.findElement(MobileBy.xpath("//android.widget.FrameLayout[@content-desc=\"芭芭农场\"]")));
        farm.click();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Utils.waitForElement(driver, By.xpath("//android.widget.Image[@text='TB18vyNEFP7gK0jSZFjXXc5aXXa-56-56'])"));
//      This block is used to locate coordinate of entry, this element cannot be located in Appium inspector or
//      Uiautomatorviewer as the whole screen is a game canvas.
//        int x = 160;
//        int y = 710;
//        while (x< 175) {
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//            System.out.println("touch point: "+x +","+y);
//            TouchAction touchAction = new TouchAction(driver);
//            touchAction.press(point(x, y)).moveTo(point(x+1, y+1)).release().perform();
//            x += 1;
//            y += 2;
//        }
//      close overlay before tap entry (this overlay appears once after 8 hours)
        if(!driver.findElements(MobileBy.xpath("//android.view.View[@text='立即去收']")).isEmpty()){
            driver.findElement(MobileBy.xpath("//android.view.View[@text='立即去收']")).click();
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(point(160, 710)).moveTo(point(161, 711)).release().perform();
//      Press collect fertiliser wait for page to load
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Utils.waitForElement(driver, By.xpath("//android.view.View[@text='合种']"));

//        touchAction.press(point(800, 1270)).release().perform();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Boolean menuOpen = false;
        while (driver.findElements(MobileBy.xpath("//android.widget.Button[@text='去签到']")).isEmpty()){
            int x = 940;
            int y = 1570;
            touchAction.press(point(x, y)).release().perform();
            x+=5;
            y+=2;
        }
        if(!driver.findElements(MobileBy.xpath("//android.widget.Button[@text='去签到']")).isEmpty()){
            driver.findElement(MobileBy.xpath("//android.widget.Button[@text='去签到']")).click();
            menuOpen = true;
        }
        if(!driver.findElements(MobileBy.xpath("//android.widget.Button[@text='去领取']")).isEmpty()){
            driver.findElement(MobileBy.xpath("//android.widget.Button[@text='去领取']")).click();
        }
        while(!driver.findElements(MobileBy.xpath("//android.widget.Button[@text='去浏览']")).isEmpty()){
            driver.findElement(MobileBy.xpath("//android.widget.Button[@text='去浏览']")).click();
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            touchAction.press(point(45, 100)).release().perform();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
        if(!driver.findElements(MobileBy.xpath("//android.widget.Button[@text='去逛逛']")).isEmpty()){
            driver.findElement(MobileBy.xpath("//android.widget.Button[@text='去逛逛']")).click();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(MobileBy.xpath("//android.view.View[@text='继续赚肥料']")).click();
            touchAction.press(point(800, 1270)).release().perform();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            touchAction.press(point(945, 1570)).release().perform();
            if(!driver.findElements(MobileBy.xpath("//android.view.View[@text='领取']")).isEmpty()){
                driver.findElement(MobileBy.xpath("//android.view.View[@text='领取']")).click();
            }
            if(!driver.findElements(MobileBy.xpath("//android.view.View[@text='去签到']")).isEmpty()){
                driver.findElement(MobileBy.xpath("//android.view.View[@text='去签到']")).click();
            }
            if(!driver.findElements(MobileBy.xpath("//android.view.View[@text='去逛逛']")).isEmpty()){
                driver.findElement(MobileBy.xpath("//android.view.View[@text='去逛逛']")).click();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(menuOpen) {
            driver.findElement(MobileBy.xpath("//android.widget.Button[@text='关闭']")).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        int i =17;
        while (i>0) {
            touchAction.press(point(450, 1570)).waitAction(waitOptions(ofSeconds(2))).release().perform();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            i--;
        }
    }
}
