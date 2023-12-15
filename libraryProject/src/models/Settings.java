package models;

public class Settings extends Model {
	private String defaultDateFormat;
	private int maxReturnDays;
	private int lateReturnFeesPerDay;
	private int damagedBookFees;
	private int lostBookFees;
	private int subscriptionDuration;
	private int subscriptionFee;

	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}

	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}

	public int getMaxReturnDays() {
		return maxReturnDays;
	}

	public void setMaxReturnDays(int maxReturnDays) {
		this.maxReturnDays = maxReturnDays;
	}

	public int getLateReturnFeesPerDay() {
		return lateReturnFeesPerDay;
	}

	public int getSubscriptionDuration() {
		return subscriptionDuration;
	}

	public int getDamagedBookFees() {
		return damagedBookFees;
	}

	public int getLostBookFees() {
		return lostBookFees;
	}

	public int getSubscriptionFee() {
		return subscriptionFee;
	}
}
