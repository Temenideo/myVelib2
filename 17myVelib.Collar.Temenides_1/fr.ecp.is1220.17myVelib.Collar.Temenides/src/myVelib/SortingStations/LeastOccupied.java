package myVelib.SortingStations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import myVelib.Station;

public class LeastOccupied implements SortingMethods {
	/**
	 * Description a faire
	 */
	@Override
	public void sortStation(ArrayList<Station> stationList) throws ParseException {
		ArrayList<Float> rateNumber=new ArrayList<Float>();
		ArrayList<Station> stationSort=new ArrayList<Station>();
		float memo;
		int pos=0;
		int length=stationList.size();
		stationSort.add(stationList.get(0));
		String string = "1500.07.04 AD at 12:08:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date start = format.parse(string);
		Date end=Calendar.getInstance().getTime();
		rateNumber.add(stationList.get(0).getRateOfOccupation(start,end));
		for(int k=1;k<length;k++){
			memo=stationList.get(k).getRateOfOccupation(start,end);
			pos=0;
			for(float a:rateNumber){
				if(a<memo){
					pos=pos+1;
				}
			}
			rateNumber.add(pos, memo);
			stationSort.add(pos, stationList.get(k));
		}
		System.out.printf("%15d","Station Name");
		System.out.printf("%15d","Number of operation");
		for(int k=0;k<length;k++){
			System.out.printf("%15d",stationSort.get(k).getName());
			System.out.printf("%15d",rateNumber.get(k));
		}
		System.out.println();
	}

}

