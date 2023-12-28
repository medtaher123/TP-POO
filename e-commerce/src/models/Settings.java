package models;

public class Settings extends Model {
	private String defaultDateFormat;
	private double deliveryFees;
	private double startingCostForFreeDelivery;
	private double expressDeliveryFees;

	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}
	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}

	public double getDeliveryFees() {
		return deliveryFees;
	}

	public void setDeliveryFees(double deliveryFees) {
		this.deliveryFees = deliveryFees;
	}

	public double getStartingCostForFreeDelivery() {
		return startingCostForFreeDelivery;
	}

	public void setStartingCostForFreeDelivery(double startingCostForFreeDelivery) {
		this.startingCostForFreeDelivery = startingCostForFreeDelivery;
	}

	public double getExpressDeliveryFees() {
		return expressDeliveryFees;
	}

	public void setExpressDeliveryFees(double expressDeliveryFees) {
		this.expressDeliveryFees = expressDeliveryFees;
	}
}
