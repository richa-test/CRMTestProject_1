package Utilities;

import java.io.File;
import java.io.IOException;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class CaptureScreenShot {

	public String screenShotfolder = System.getProperty("user.dir") + "\\ScreenShots";
	public String screenshotFileName;
	public DateTime dt = new DateTime();
	
	@Test
	public String getScreenShot(WebDriver driver,String testCaseName) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//	screenshotFileName=screenShotfolder + "\\" +testCaseName+"_"+dt.getDate(0) + ".jpg";
	screenshotFileName=screenShotfolder + "\\" +testCaseName+"_"+System.currentTimeMillis()+ ".png";
	//screenshotFileName=screenShotfolder + "\\" +testCaseName+"_"+System.currentTimeMillis()+ ".jpg";
		FileUtils.copyFile(scrFile, new File(screenshotFileName));

		/*for latest version of selenium use filehandler
		           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);             
		The below method will save the screen shot in destination directory with name "screenshot.png"            
		 FileHandler.copy(scrFile, new File("D:/reports/screenshot.png"));
		 */

		return screenshotFileName;
	}
}
