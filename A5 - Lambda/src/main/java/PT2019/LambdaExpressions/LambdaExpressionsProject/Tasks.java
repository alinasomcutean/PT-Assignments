package PT2019.LambdaExpressions.LambdaExpressionsProject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {

	private List<MonitoredData> list = new ArrayList<MonitoredData>();
	private String fileName = "E:\\Facultate\\Anul II\\Sem II\\Fundamental Programming Techniques\\Homeworks\\HW_5\\Activities.txt";
	private Stream<String> line = null;
	private List<Integer> days = new ArrayList<>();
	
	public void readFromFile() {
		File file = new File(fileName);
		try {
			line = Files.lines(Paths.get(fileName));
			list = line.map(MonitoredData::new).collect(Collectors.toList());
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getStartTime() + " " + list.get(i).getFinishTime() + " " + list.get(i).getActivity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long numberOfDays() {
		long count = 0;
		days = list.stream().map(m -> m.getDayOfStartedDate()).distinct().collect(Collectors.toList());
		count = days.stream().count();
		return count;
	}
	
	public Map<String, Long> numberOfEachActivity() {
		Map<String, Long> map = new HashMap<String, Long>();
		map = list.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		return map;
	}
	
	public void eachActivityForEachDay() {
		for(int i = 0; i < numberOfDays(); i++) {
			List<MonitoredData> l = new ArrayList<MonitoredData>();
			int d = i;
			l = list.stream().filter(m -> m.getDayOfStartedDate() == days.get(d)).collect(Collectors.toList());
			Map<String, Long> map = new HashMap<String, Long>();
			map = l.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
			System.out.println("\nDay " + days.get(i) + ": ");
			for(Entry<String, Long> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}
	}
	
	public void findDuration() {
		 for(int i = 0; i < list.size(); i++) {
			 long duration = list.get(i).findDurationInMilliseconds();
			 long[] arr = list.get(i).transformInHoursMinutesSeconds(duration);
			 System.out.print(arr[0] + " hours, ");
			 System.out.print(arr[1] + " minutes, ");
			 System.out.println(arr[2] + " seconds.");
		 }
	}
	
	public void findEntireDurationForEachActivity() {
		Map<String, Long> duration = new HashMap<String, Long>();
		duration = list.stream().collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingLong(MonitoredData::findDurationInMilliseconds)));
		for(Entry<String, Long> entry : duration.entrySet()) {
			MonitoredData m = new MonitoredData();
			long[] arr = m.transformInHoursMinutesSeconds(entry.getValue());
			System.out.println(entry.getKey() + " : " + arr[0] + " hours, " + arr[1] + " minutes, " + arr[2] + " seconds.");
		}
	}
	
	public void activitiesWithDurationLessThan5() {
		List<Long> number = new ArrayList<>();
		List<Long> totalNumber = new ArrayList<>();
		List<String> activities = new ArrayList<String>();
		int j = 0;
		Map<String, Long> map = new HashMap<String, Long>();
		map = numberOfEachActivity();
		for(Entry<String, Long> entry : map.entrySet()) {
			List<MonitoredData> l = new ArrayList<MonitoredData>();
			l = list.stream().filter(m -> m.getActivity().equals(entry.getKey())).collect(Collectors.toList());
			number.add(l.stream().filter(m -> m.findDurationInMilliseconds() < 300000).count());
			totalNumber.add(entry.getValue());
			activities.add(entry.getKey());
		}
		for(int i = 0; i < number.size(); i++) {
			float percent = number.get(i)/totalNumber.get(i);
			if(percent > 0.9) {
				System.out.println(activities.get(i));
			}
		}
	}
}
