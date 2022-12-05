package powerSimulation;

public class Appliance {
	public int locationID;
	public String appName;
	public int onW;
	public double probOn;
	public boolean smart;
	public double probSmart;
	
	/**
	 * {@code Appliance(int locID, String n, int on, double pOn, boolean s, double pSmart)}
	 * <p>
	 * This constructs an appliance with the specified location ID, appliance description, wattage, 
	 * probability of being "on", if it is a smart appliance, and the watt reduction if it is a smart appliance (0.00 if not)
	 * 
	 * @param locID The numerical location of the appliance
	 * @param n The description of the appliance
	 * @param on Watt usage
	 * @param pOn Probability of being "on" in the simulation
	 * @param s Whether this is a smart appliance or not
	 * @param pSmart The watt reduction if it is a smart appliance (0.00 if not)
	 */
	Appliance(int locID, String n, int on, double pOn, boolean s, double pSmart) {
		this.locationID = locID;
		this.appName = n;
		this.onW = on;
		this.probOn = pOn;
		this.smart = s;
		this.probSmart = pSmart;
	}
	
	/**
	 * {@code public void setLocationID(int locID)}
	 * <p>
	 * Change the location ID attribute of this appliance to the argument passed to this setter
	 * @param locID The location ID that will replace this appliance's current location ID
	 */
	public void setLocationID(int locID) {
		locationID = locID;
	}
	
	/**{@code public void setName(String name)}
	 * <p>
	 * Change the appliance description attribute of this appliance to the argument passed to this setter
	 * @param name The appliance description that will replace this appliance's current appliance description
	 */
	public void setName(String name) {
		appName = name;
	}
	
	/**
	 * {@code public void setonW(int on)}
	 * <p>
	 * Change the watt usage attribute of this appliance to the argument passed to this setter
	 * @param on The watt usage that will replace this appliance's current watt usage
	 */
	public void setonW(int on) {
		onW = on;
	}
	
	/**
	 * {@code public void setProbOn(double probO)}
	 * <p>
	 * Change the probability attribute of this appliance being "on" to the argument passed to this setter
	 * @param probO The probability that will replace this appliance's current probability
	 */
	public void setProbOn(double probO) {
		probOn = probO;
	}
	
	/**
	 * {@code public void setSmart(boolean type)}
	 * <p>
	 * Change whether this is a smart appliance or not to the argument passed to this setter
	 * @param type The watt usage that will replace this appliance's current watt usage
	 */
	public void setSmart(boolean type) {
		smart = type;
	}
	
	/**
	 * {@code public void setProbSmart(int probS)}
	 * <p>
	 * Change the watt reduction of this appliance to the argument passed to this setter
	 * @param probS The watt reduction that will replace this appliance's current reduction
	 */
	public void setProbSmart(int probS) {
		probSmart = probS;
	}
	
	/**
	 * {@code public int getLocationID()}
	 * <p>
	 * Returns the location ID attribute of this appliance
	 * @return The location ID
	 */
	public int getLocationID() {
		return locationID;
	}
	
	/**
	 * {@code public String getAppName()}
	 * <p>
	 * Returns the appliance description attribute of this appliance
	 * @return The appliance description
	 */
	public String getAppName() {
		return appName;
	}
	
	/**
	 * {@code public int getOnW()}
	 * <p>
	 * Returns the watt usage attribute of this appliance
	 * @return The watt usage
	 */
	public int getOnW() {
		return onW;
	}
	
	/**
	 * {@code public double getProbOn()}
	 * <p>
	 * Returns the probability of this appliance being "on" for the simulation
	 * @return The probability
	 */
	public double getProbOn() {
		return probOn;
	}
	
	/**
	 * {@code public boolean getSmart()}
	 * <p>
	 * Returns whether this appliance is a smart appliance or not
	 * @return Whether this appliance is a smart appliance or not
	 */
	public boolean getSmart() {
		return smart;
	}
	
	/**
	 * {@code public double getProbSmart()}
	 * <p>
	 * Returns the watt reduction if this smart appliance is switched to "low" 
	 * @return The watt reduction
	 */
	public double getProbSmart() {
		return probSmart;
	}
	
	@Override
	public String toString() {
		return "Appliance: locationID=" + locationID + ", appName=" + appName + ", onW=" + onW + ", probOn=" + probOn + ", smart="
				+ smart + ", probSmart=" + probSmart;
	}
}
