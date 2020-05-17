package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification req;
	//String baseUri = "https://rahulshettyacademy.com";

	public RequestSpecification reqSpecificationAddPlace() throws IOException
	{
		PrintStream log = new PrintStream(new FileOutputStream(new File("logs.txt"),true));
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		 return req;
	}
	
	public static String getGlobalValues(String value) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Satish\\Java_Project\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(value);
		//return prop;
	}
	
	public String gerRespoinsePlace(Response response, String key) {
		
		String responseStr = response.asString();
		 JsonPath js = new JsonPath(responseStr);
		 return js.get(key).toString();
		
	}
}

