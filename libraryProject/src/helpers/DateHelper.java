package helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import authentification.AuthenticationSystem;

public class DateHelper {
	// TODO: add to doc: Helpers are classes with static methods only that provide
	// functionalities to all parts of the application. Methods that would otherwise
	// be defined in multiple classes resulting in redundant code
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
		return new Date(date.getTime() + (1000 * 60 * 60 * 24) * days);
	}
}
