package green.duplicates;

import java.util.HashMap;
import java.util.Map;

public class HowManyDuplicates {
	
	public static void main (String [] args){
		String [] list = new String []{
			"HAPPY" , "BIRTHDAY", "TODAY", "IS", "BIRTHDAY","IS", "YOURS", "HURRAY"
		};
		
		Map <String, Integer> map = new HashMap<String,Integer>();
		for (String key: list){
			Integer value = map.get(key);
			if (value == null){
				map.put(key,  1);
				
			}
			else {
				map.put(key, value++);
			}
		}
		
		
	}

}
