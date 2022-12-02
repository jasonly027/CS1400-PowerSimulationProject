package powerSimulation;

public class Appliance {
	public String appName;
	public int onW;
	public int offW;
	public double probOn;
	public boolean smart;
	public double probSmart;
	
	Appliance(String n, int on, int off, double pOn, boolean s, double pSmart) {
		this.appName = n;
		this.onW = on;
		this.offW = off;
		this.probOn = pOn;
		this.smart = s;
		this.probSmart = pSmart;
	}
	
	public void setName(String name) {
		appName = name;
	}
	
	public void setonW(int on) {
		onW = on;
	}
	
	public void setoffW(int off) {
		offW = off;
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
	
	public String getAppName() {
		return appName;
	}
	
	public int getOffW() {
		return offW;
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
		return "Appliance [appName=" + appName + ", onW=" + onW + ", offW=" + offW + ", probOn=" + probOn + ", smart="
				+ smart + ", probSmart=" + probSmart + "]";
	}
}
