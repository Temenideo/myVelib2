package myVelib.SortingStations;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.Station;

public class MostUsed implements SortingMethods {
	
	public MostUsed() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Description a faire
	 */
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
				if(a<memo){
					pos=pos+1;
				}
			}
			usedNumber.add(pos, memo);
			stationSort.add(pos, stationList.get(k));
		}
		System.out.printf("%15d","Station Name");
		System.out.printf("%15d","Number of operation");
		for(int k=0;k<length;k++){
			System.out.printf("%15d",stationSort.get(k).getName());
			System.out.printf("%15d",usedNumber.get(k));
		}
		System.out.println();
	}
}
