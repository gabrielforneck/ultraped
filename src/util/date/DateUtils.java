package util.date;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {
	
	public static Date getCurrentSystemDate() {
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
