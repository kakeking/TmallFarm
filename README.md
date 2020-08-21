# TmallFarm
Use Appium and Selenium web driver to automate tasks in Taobao Baba Farm.
This is not a test case, I create it to help complete game tasks for incentives in Taobao (Android).

To use this script, Appium environment should be set correctly, which can be very tricky. 
All user variables should be modified accordingly in config.property file before running task.

Install Android SDK, Appium desktop and server. 
Following environment variables must be set to right path (example for Mac in ~/.bash_profile):
```
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.5.jdk/Contents/Home"
export ANDROID_HOME="/.android/sdk"
```
For lastest version of Appium, set above variables in configurations before start server.

Some commands that might be useful for mobile automation:
```
#Get activity name currently open on phone
adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'"
#Check device uuid
adb devices
#Restart adb server
sudo adb kill-server
sudo adb start-server
#uiautomatorviewer cannot open (javac exception): open uiautomatorviewer as text and change last line to 
exec java -Xmx1600M -XstartOnFirstThread -Dcom.android.uiautomator.bindir="/.android/sdk/tools" -cp "/.android/sdk/tools/lib/x86_64/swt.jar":"/.android/sdk/tools/lib/*" com.android.uiautomator.UiAutomatorViewer
#Change and apply system environment variables
vim ~/.bash_profile
source ~/.bash_profile
```
