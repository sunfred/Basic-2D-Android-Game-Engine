package GameEngine;

import java.util.Calendar;
import java.util.Random;

public class Utils {	
	// Public Properties
	public static final Random Random = new Random();
	public static String GetDate() {
		Calendar c = Calendar.getInstance();
		String date = "" + c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR) + " " + c.get(Calendar.HOUR_OF_DAY) + ":";
		if(c.get(Calendar.MINUTE) < 10){
			date += "0" + c.get(Calendar.MINUTE);
		} else {
			date += c.get(Calendar.MINUTE);
		}
		return date;
	}
	
	public static int getRandomBetween(int min, int max){
		return Random.nextInt(max - min) + min;
	}
	
	public static String getNumberString(int value){
		String s = null;
		if(value < 10){
			s = "0" + value;
		} else {
			s = "" + value;
		}
		return s;
	}
	
	public static int nextInt(int n){
		return Random.nextInt(n);
	}
}
