import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.offset.PointOption.point;

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

        //         get source to check element, this page cannot be captured by Appium inspector or UIautomatorViewer
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String source = driver.getPageSource();
//        System.out.println(source);
        MobileElement login = Utils.waitForClickable(driver, (MobileElement) driver.findElement(MobileBy.id("com.taobao.taobao:id/aliuser_recommend_login_next_btn")));
        login.click();
        MobileElement farm = Utils.waitForClickable(driver, (MobileElement) driver.findElement(MobileBy.xpath("//android.widget.FrameLayout[@content-desc=\"芭芭农场\"]")));
        farm.click();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        TouchAction touchAction=new TouchAction(driver);
        touchAction.press(point(100, 800)).perform();
    }
}
