package csg.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import csg.TestComponents.ResponseTags;
import csg.TestComponents.APIBaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestHooks extends APIBaseClass {
	@Before
	public void before(Scenario scenario) throws IOException, AWTException {
	   
	    this.scenario = scenario;
	    test = extent.createTest(scenario.getName());
	    logInfo.set(test);
	    prop = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//csg//resources//GlobalData.properties");
		prop.load(fis);
		baseURI=prop.getProperty("baseurl");
		username=prop.getProperty("apiusername");
		password=prop.getProperty("apipassword");
		RestAssured.baseURI =baseURI;
		
		String reqBody="{\n"
				+ "    \"username\" : "+username+",\n"
				+ "    \"password\" : "+password+"\n"
				+ "}";
		String response = given()
                .header("Content-type", "application/json")
                .and()
                .body(reqBody)
                .when()
                .post("/auth")
                .then()
                .extract().response().asString();	
		
		JsonPath js = new JsonPath(response);
		token = js.getString(ResponseTags.TOKEN);
		System.out.println("Token : "+token);
		
	}
	
	@After
	public void after() {
		extent.flush();

	}

}
