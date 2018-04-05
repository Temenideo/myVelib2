package scenarioTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScenarioTester {
	public static void executeScenario(String fileName){
		String[] returnValue;
		FileReader file = null;
		BufferedReader reader = null;
		try { 
			file = new FileReader(fileName); // a FileReader for reading byte
			reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null) {// read the file line
			returnValue=line.split(";");
			System.out.println(returnValue);
			}
		}
		catch (Exception e){
			throw new RuntimeException(e);
		}
		finally {
			if(reader!=null){
				try{reader.close();}
				catch(IOException e){}
			}
			if(file!=null){
				try{file.close();}
				catch(IOException e){}
			}
		}
	}
	public static void main(String[] args) {
		executeScenario("testScenario1.txt");
	}
}