import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

public class Utils {
    private static final Properties PROPERTY = new Properties();
    private static final String PREFIX= "./";
    private static final Logger LOGGER = Logger.getLogger( Utils.class.getName() );;

    public static MobileElement waitForVisible(AndroidDriver driver, MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
//        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static MobileElement waitForClickable( AndroidDriver driver, MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }
    public static void waitForElement(AndroidDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static Properties loadVariables(String propertyfile){
        URL res = Utils.class.getClassLoader().getResource(propertyfile);
        try{
            PROPERTY.load(new FileInputStream(String.valueOf(Paths.get(res.toURI()))));
        }catch (IOException | URISyntaxException ex){
            LOGGER.info("IOException in the loadVariable() method of the util class" + ex);
            Runtime.getRuntime().halt(0);
        }
        return PROPERTY;
    }

    public static AndroidDriver setUp() throws MalformedURLException {
        Properties props = loadVariables("config.property");
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", props.getProperty("deviceName"));
        cap.setCapability("udid", props.getProperty("udid"));
        cap.setCapability("platformName", props.getProperty("platform"));
        cap.setCapability("platformVersion", props.getProperty("androidVersion"));
        cap.setCapability("appPackage", "com.taobao.taobao");
        cap.setCapability("appActivity", "com.taobao.tao.TBMainActivity");
        cap.setCapability("autoGrantPermissions", "true");
        cap.setCapability("newCommandTimeout", 50);
        cap.setCapability("unicodeKeyboard", true);
        cap.setCapability("resetKeyboard", true);

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        AndroidDriver driver = new AndroidDriver(url, cap);
        System.out.println("Application started...");
        return driver;
    }

    public static void swipeDown(AndroidDriver driver, int howManySwipes) {
        Dimension size = driver.manage().window().getSize();
        // calculate coordinates for vertical swipe
        int startVerticalY = (int) (size.height * 0.3);
        int endVerticalY = (int) (size.height * 0.7);
        int startVerticalX = (int) (size.width / 2.1);
        try {
            for (int i = 1; i <= howManySwipes; i++) {
                new TouchAction<>(driver).longPress(point(startVerticalX, startVerticalY))
                        .waitAction(waitOptions(ofSeconds(2))).moveTo(point(startVerticalX, endVerticalY))
                        .release().perform();
                System.out.println(startVerticalX+", "+startVerticalY);
            }
        } catch (Exception e) {
            LOGGER.info("Exception in the swipedown method of the util class" + e);
        }
    }
}
