package scenarioTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import scenarioTest.strategy.*;
/**
 * Cette classe permet d'excuter les opérations stocker dans un fichier texte
 * Le format de stockage est : "nom_de_l'opéartion;argument1;...;argumentn;"
 * Chaque ligne ne doit contenir qu'une seule opération avec ses arguments
 * @author xavier
 *
 */
public class ScenarioTester {
	public static void executeScenario(String fileName){
		String[] returnValue;
		ArrayList<String> array=new ArrayList<String>();
		FileReader file = null;
		StrategyMethode SM=null;
		BufferedReader reader = null;
		try { 
			file = new FileReader(fileName); // a FileReader for reading byte
			reader = new BufferedReader(file);
			String line = "";
			while ((line = reader.readLine()) != null) {// read the file line
				returnValue=line.split(";");
				array.clear();
				for (String str:returnValue){
					array.add(str);
				}
				switch(array.get(0)){
				case "reseau": {
					SM=new ReseauCreation();
					SM.execute(array);
					break;
				}

				case "rest":{
					SM=new Reset();
					SM.execute(array);
					break;
				}
				case "station" : {
					SM=new StationCreation();
					SM.execute(array);
					break;
				}

				case "user" :{
					SM=new UserCreation();
					SM.execute(array);
					break;
				}
				case "rentabike":{
					SM=new Rentabike();
					SM.execute(array);
					break;
				}
				case "planningride" :{
					SM=new Planningride();
					SM.execute(array);
					break;
				}
				case "returnbike" :{
					SM=new Returnbike();
					SM.execute(array);
					break;
				}
				case "offline":{
					SM=new Offline();
					SM.execute(array);
					break;
				}
				case "initialisationsimulation" :{
					SM=new Initialisationsimulation();
					SM.execute(array);
					break;
				}
				case "simulationlocation" :{
					SM=new SimulationLocation();
					SM.execute(array);
					break;
				}
				case "statistique" :{
					SM=new Statistique();
					SM.execute(array);
					break;
				}
				case "statistiqueuser" :{
					SM=new Statistiqueuser();
					SM.execute(array);
					break;
				
				}
				case "subscribe" : {
					SM=new Subscribe();
					SM.execute(array);
				}
				}
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
		executeScenario("testScenario3.txt");
	}
}
