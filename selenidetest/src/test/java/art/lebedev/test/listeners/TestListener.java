package art.lebedev.test.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.apache.log4j.Logger;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    Logger logger = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test finished");
    }
}
