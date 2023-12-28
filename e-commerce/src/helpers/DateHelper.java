package helpers;

import authentication.AuthenticationSystem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// TODO doc: Helpers are classes with static methods only that provide
//  functionalities to all parts of the application. Methods that would otherwise
//  be defined in multiple classes resulting in redundant code
public class DateHelper {
	
	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static String format(Date date) {
		String format = getUsersDateFormat();

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String getUsersDateFormat() {
		String format = null;
		/*
		 * if (activeUser != null && activeUser.getPreferences() != null &&
		 * activeUser.getPreferences().getDateFormat!=null) {
		 * format=activeUser.getPreferences().getDateFormat; }
		 */
		try {
			format = AuthenticationSystem.getActiveUser().getPreferences().getDateFormat();
		} catch (NullPointerException e) {
		}

		if (format == null) {
			format = DEFAULT_DATE_FORMAT;
		}
		return format;
	}

	public static Date getTodayStartOfDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		calendar.add(Calendar.DATE, days);
		
		return calendar.getTime();
		
	}
}
