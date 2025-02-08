package udemycourse.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DataReader {
	
	public List<HashMap<String, String>> getJSONDataToMap() throws IOException {
		
		//read JSON to string
		String JSONContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//udemycourse//data//PurchaseOrder.json"), StandardCharsets.UTF_8);
		
		/*Another way to read JSON to string
		Path path=Path.of("C://Users//monie//eclipse-workspace//SeleniumFramework//src//test//java//udemycourse//data//PurchaseOrder.json");
		String JSONContent=Files.readString(path); //this method reads data from JSON and stores the data in string variable
*/		
		//To convert String to HashMap- we need jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(JSONContent, new TypeReference<List<HashMap<String,String>>>(){		
		});
		return data;
		
		
		
	}

}
