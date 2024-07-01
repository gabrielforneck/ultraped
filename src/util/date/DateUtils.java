package util.date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {
	
	public static Date getCurrentSystemDate() {
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static String ApplyDefaultDateFormat(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
}
