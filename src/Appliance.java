package powerSimulation;

public class Appliance {
	public int locationID;
	public String appName;
	public int onW;
	public double probOn;
	public boolean smart;
	public double probSmart;
	
	Appliance(int locID, String n, int on, double pOn, boolean s, double pSmart) {
		this.locationID = locID;
		this.appName = n;
		this.onW = on;
		this.probOn = pOn;
		this.smart = s;
		this.probSmart = pSmart;
	}
	
	public void setLocationID(int locID) {
		locationID = locID;
	}
	
	public void setName(String name) {
		appName = name;
	}
	
	public void setonW(int on) {
		onW = on;
	}
	
	public void setProbOn(double probO) {
		probOn = probO;
	}
	
	public void setSmart(boolean type) {
		smart = type;
	}
	
	public void setProbSmart(int probS) {
		probSmart = probS;
	}
	
	public int getLocationID() {
		return locationID;
	}
	
	public String getAppName() {
		return appName;
	}
	
	public int getOnW() {
		return onW;
	}
	
	public double getProbOn() {
		return probOn;
	}
	
	public boolean getSmart() {
		return smart;
	}
	
	public double getProbSmart() {
		return probSmart;
	}

	@Override
	public String toString() {
		return "Appliance: locationID=" + locationID + ", appName=" + appName + ", onW=" + onW + ", probOn=" + probOn + ", smart="
				+ smart + ", probSmart=" + probSmart;
	}
}
