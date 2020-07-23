package PT2019.LambdaExpressions.LambdaExpressionsProject;

import java.util.Map;
import java.util.Map.Entry;

public class Start {
	
	public static void main(String[] arg) {
		Tasks t = new Tasks();
		t.readFromFile();
		System.out.print("\nThe number of distinct days that appears in the log is ");
		System.out.println(t.numberOfDays());
		System.out.print("\nHow many times has appeared each activity:\n");
		Map<String, Long> map = t.numberOfEachActivity();
		for(Entry<String, Long> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		System.out.println("\nNumber of each activity for every day:");
		t.eachActivityForEachDay();
		System.out.println("\nThe duration of each activity:\n");
		t.findDuration();
		System.out.println("\nThe total duration for each activity\n");
		t.findEntireDurationForEachActivity();
		System.out.print("\nThe activities which have duration less then 5 minutes: ");
		t.activitiesWithDurationLessThan5();
	}
}
