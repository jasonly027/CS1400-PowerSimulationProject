package powerSimulation;

public class SmartAppliance extends RegularAppliance{
	
	/*
	TODO:
	Attributes:
		private boolean onLow;
		private int wattageOnLow; //calculates wattage appliances uses when set to low
		private int difference; //keeps track of the differences when set to low. <--- Pls make sure to add this, Part C depends on this.
		private int position;//keeps track of appliance position in smart array <---Pls make sure to add this, Part C depends on this.
		
	Methods: 
		public SmartAppliance () <---- takes in id, name, wattageOn, probOn, smart?, smartPercent (all attributes used in regularAppliance class)
		- this.setWattageOnLow((super.getOnW() -(int)(super.getProbSmart()*super.getOnW())));
		- this.setDifference(super.getOnW() - this.getWattageOnLow());
		
		Getters and Setters methods for all attributes
		
	*/
	private boolean onLow;
	private int wattageOnLow;
	private int difference;
	private int position;
	
	public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart) {
		super(locID, n, on, pOn, s, pSmart);
		this.setWattageOnLow((int)Math.round(super.getOnW() - (super.getProbSmart()*super.getOnW())));
		this.setDifference(super.getOnW() - this.getWattageOnLow());
	}
	
	public SmartAppliance(int locID, String n, int on, double pOn, boolean s, double pSmart, int ID) {
		super(locID, n, on, pOn, s, pSmart, ID);
		this.setWattageOnLow((int)Math.round(super.getOnW() - (super.getProbSmart()*super.getOnW())));
		this.setDifference(super.getOnW() - this.getWattageOnLow());
	}
	
	public void setOnLow(boolean on) {
		onLow = on;
	}
	
	public void setWattageOnLow(int wattageOnLow) {
		this.wattageOnLow = wattageOnLow;
	}
	
	public boolean getOnLow() {
		return onLow;
	}
	
	public int getDifference() {
		return difference;
	}

	public void setDifference(int difference) {
		this.difference = difference;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWattageOnLow() {
		return wattageOnLow;
	}
}
