package myVelib.SortingStations;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.Station;
/**
 * Classe contenant la méthode pour trier les stations selon leur utilisation
 * @author xavier
 *
 */
public class MostUsed implements SortingMethods {
	
	public MostUsed() {
		super();
	}
	@Override
	public void sortStation(ArrayList<Station> stationList) throws ParseException {
		ArrayList<Integer> usedNumber=new ArrayList<Integer>();
		ArrayList<Station> stationSort=new ArrayList<Station>();
		int memo;
		int pos=0;
		int length=stationList.size();
		stationSort.add(stationList.get(0));
		usedNumber.add(stationList.get(0).numberOfRentsOperation()+stationList.get(0).numberOfReturnOperation());
		for(int k=1;k<length;k++){
			memo=stationList.get(k).numberOfRentsOperation()+stationList.get(k).numberOfReturnOperation();
			pos=0;
			for(int a:usedNumber){
				if(a>memo){
					pos=pos+1;
				}
			}
			usedNumber.add(pos, memo);
			stationSort.add(pos, stationList.get(k));
		}
		System.out.print("Number of operation"+"        "+"Station Name");
		for(int k=0;k<length;k++){
			System.out.println();
			System.out.printf("%13d",usedNumber.get(k));
			System.out.print("              "+stationSort.get(k).getName());
			

		}
	}
}
