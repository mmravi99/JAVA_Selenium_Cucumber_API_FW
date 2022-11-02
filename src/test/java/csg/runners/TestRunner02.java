package csg.runners;

import java.awt.AWTException;

import org.testng.annotations.BeforeClass;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/csg/features/Order.feature",tags = "@reg", glue = "csg.stepdefinitions", monochrome = true,plugin = {"html:target/cucumber.html"})
public class TestRunner02 extends AbstractTestNGCucumberTests{
	@BeforeClass
	public void beforeCalss() throws AWTException {
		
		
	}
}
