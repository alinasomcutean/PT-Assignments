package PT2019.LambdaExpressions.LambdaExpressionsProject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonitoredData {

	private Date start_time;
	private Date finish_time;
	private String activity;
	private SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public MonitoredData() {
		
	}
	
	public MonitoredData(String str) {
		String[] array = str.split("		");
		try {
			Date start_time = formater.parse(array[0]);
			Date finish_time = formater.parse(array[1]);
			String activity = array[2];
			this.start_time = start_time;
			this.finish_time = finish_time;
			this.activity = activity;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Date getStartTime() {
		return this.start_time;
	}
	
	public Date getFinishTime() {
		return this.finish_time;
	}
	
	public String getActivity() {
		return this.activity;
	}
	
	public int getDayOfStartedDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start_time);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}
	
	public long findDurationInMilliseconds() {
		return finish_time.getTime() - start_time.getTime();
	}
	
	public long[] transformInHoursMinutesSeconds(Long time) {
		long durationSeconds = time / 1000 % 60;
		long durationMinutes = time / (60 * 1000) % 60;
		long durationHours = time / (60 * 60 * 1000) % 24;
		if(durationHours < 0 || durationMinutes < 0 || durationMinutes < 0) {
			durationSeconds = 60 + durationSeconds;
			durationMinutes = 60 + durationMinutes;
			durationHours = 24 + durationHours;
		}
		long[] d = {durationHours, durationMinutes, durationSeconds};
		return d;
	}
}
