package powerSimulation;

public class SmartAppliance extends RegularAppliance{
	private boolean onLow;
	private int wattageOnLow;
	private int difference;
	private int position;
	
	/**
	 * {@code public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart)}
	 * <p>
	 * Constructs a smart appliance object with the specified location ID, appliance description, 
	 * wattage, probability of it being "on", whether it is a smart appliance, and the watt reduction if it is a smart appliance (0.00 if not)
	 * <p>
	 * A unique ID is generated and given to it upon instantiation
	 * <p>
	 * The wattage of the smart appliance on "low" and the difference of the regular wattage and "low" mode is 
	 * calculated from the regular wattage and its watt reduction
	 * @param locID The location ID
	 * @param n The appliance description
	 * @param on The wattage
	 * @param pOn The probability of it being "on"
	 * @param s Whether it is a smart appliance
	 * @param pSmart The watt reduction if it is a smart appliance (0.00 if not)
	 */
	public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart) {
		super(locID, n, on, pOn, s, pSmart);
		this.setWattageOnLow((int)Math.round(super.getOnW() - (super.getProbSmart()*super.getOnW())));
		this.setDifference(super.getOnW() - this.getWattageOnLow());
	}
	
	/**
	 * {@code public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart, int ID)}
	 * <p>
	 * Constructs a smart appliance object with the specified location ID, appliance description, 
	 * wattage, probability of it being "on", whether it is a smart appliance, 
	 * and the watt reduction if it is a smart appliance (0.00 if not), and ID
	 * <p>
	 * The wattage of the smart appliance on "low" and the difference of the regular wattage and "low" mode is 
	 * calculated from the regular wattage and its watt reduction
	 * @param locID The location ID
	 * @param n The appliance description
	 * @param on The wattage
	 * @param pOn The probability of it being "on"
	 * @param s Whether it is a smart appliance
	 * @param pSmart The watt reduction if it is a smart appliance (0.00 if not)
	 * @param ID The ID
	 */
	public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart, int ID) {
		super(locID, n, on, pOn, s, pSmart, ID);
		this.setWattageOnLow((int)Math.round(super.getOnW() - (super.getProbSmart()*super.getOnW())));
		this.setDifference(super.getOnW() - this.getWattageOnLow());
	}
	
	/**
	 * {@code public void setOnLow(boolean on)}
	 * <p>
	 * Change whether the smart appliance is on "low" or not
	 * @param on The new state of the smart appliance
	 */
	public void setOnLow(boolean on) {
		onLow = on;
	}
	
	/**
	 * {@code public void setWattageOnLow(int wattageOnLow)}
	 * <p>
	 * Change the wattage when the smart appliance is on "low"
	 * @param wattageOnLow The new wattage when the smart appliance is on "low"
	 */
	public void setWattageOnLow(int wattageOnLow) {
		this.wattageOnLow = wattageOnLow;
	}
	
	/**
	 * {@code public boolean getOnLow()}
	 * <p>
	 * Returns the wattage of the smart appliance on "low"
	 * @return The wattage of the smart appliance on "low"
	 */
	public boolean getOnLow() {
		return onLow;
	}
	
	/**
	 * {@code public int getDifference()}
	 * <p>
	 * Returns the difference of the regular wattage and the wattage on "low"
	 * @return The difference of the regular wattage and the wattage on "low"
	 */
	public int getDifference() {
		return difference;
	}
	
	/**
	 * {@code public void setDifference(int difference)} {
	 * <p>
	 * Change the difference of the regular wattage and the wattage on "low"
	 * @param difference The new difference of the regular wattage and the wattage on "low"
	 */
	public void setDifference(int difference) {
		this.difference = difference;
	}
	
	/**
	 * {@code public int getPosition()}
	 * <p>
	 * Returns the position of the smart appliance for ordered list purposes
	 * @return The position of the smart appliance for ordered list purposes
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * {@code public void setPosition(int position)}
	 * <p>
	 * Change the position of the smart appliance to the position passed by the argument
	 * @param position The new position of the smart appliance
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * {@code public int getWattageOnLow()}
	 * <p>
	 * Returns the wattage of the smart appliance on "low"
	 * @return The wattage of the smart appliance on "low"
	 */
	public int getWattageOnLow() {
		return wattageOnLow;
	}
}
