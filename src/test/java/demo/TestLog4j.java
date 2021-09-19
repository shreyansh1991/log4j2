package demo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/*
Rolling file is used when we need to roll over the logs based on conditions like file size, date, time, compression, etc.
This helps in maintenance if the log grows. This also helps in archiving old logs and gives ability to retain the latest onces.

With file, you will have a single file that can grow huge over the period of time. It can consume resources and if you delete it,
all logs will be gone. Also you will have to do all this manually.
*/
public class TestLog4j {
	@Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser) {
	String path="logs"+File.separator+browser;
	 File file = new File(path);
	 if(!file.exists())
		 file.mkdirs();
	 ThreadContext.put("ROUTINGKEY", path);
	}
	@Test
	public void test() {
// ALL <  DEBUG < INFO <WARN <ERROR <FATAL <OFF
		Logger logger = LogManager.getLogger(TestLog4j.class.getName());
		logger.info("This is the info message");
		logger.debug("This is the debug message");
		logger.error("This is the error message");
		logger.warn("This is the warning message");
		
	}

}
