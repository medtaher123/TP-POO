package models;

public class Settings extends Model {
	private String defaultDateFormat;
	private int bookingMaxDuration;
	private int lateReturnFeePerDay;
	private int damagedBookFee;
	private int lostBookFee;
	private int subscriptionDuration;
	private int subscriptionFee;
	private int bookingLimit;

	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}

	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}

	public int getBookingMaxDuration() {
		return bookingMaxDuration;
	}

	public void setMaxReturnDays(int bookingMaxDuration) {
		this.bookingMaxDuration = bookingMaxDuration;
	}

	public int getLateReturnFeesPerDay() {
		return lateReturnFeePerDay;
	}

	public int getSubscriptionDuration() {
		return subscriptionDuration;
	}

	public int getDamagedBookFees() {
		return damagedBookFee;
	}

	public int getLostBookFees() {
		return lostBookFee;
	}

	public int getSubscriptionFee() {
		return subscriptionFee;
	}

	public int getBookingLimit() {
		return bookingLimit;
	}
}
