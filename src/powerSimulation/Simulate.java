package powerSimulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Simulate {
	
	private static ArrayList<SmartAppliance> reportSmartAppliances = new ArrayList<SmartAppliance>();
	private static ArrayList<Location> reportLocations = new ArrayList<Location>();
	private static int reportFileNo = 0;
	private static int currentStep;
	private static int timeSteps;
	
	/**
	 * {@code private static void ReportPrint(int maxWattage, int totalWattage, int totalWattageChanged)}
	 * <p>
	 * Print summarized information regarding to the changes the algorithm made to the appliances/locations to the console.
	 * Afterwards, print a more detailed report to a text file.
	 * @param maxWattage The maximum wattage permitted for the system
	 * @param totalWattage The total wattage if no changes were made to applications/locations
	 * @param totalWattageChanged The total wattage after changes were made to applications/locations so that it is less than or equal to the maximum wattage
	 */
	private static void ReportPrint(int maxWattage, int totalWattage, int totalWattageChanged) {
		//Prints to console
		System.out.println("/// Time Step [" + (currentStep+1) + "] ///");
		System.out.printf("Max Allowed Wattage: %,d\t\t\tTotal Wattage before changes: %,d\t\t\tTotal Wattage after changes: %,d\n",maxWattage,totalWattage,totalWattageChanged);
		System.out.printf("Smart Appliances turned to low: %,d\t\t\tLocations browned out: %,d",reportSmartAppliances.size(),reportLocations.size());
		if(reportLocations.size() > 0)
		{
			System.out.printf("\t\t\tHighest Impact Location Affected: %d with %,d watts\n\n",reportLocations.get(reportLocations.size()-1).getLocationID(),reportLocations.get(reportLocations.size()-1).getSumWattage());
		}
		else {
			System.out.print("\n\n");
		}
		
		//Prints to text file
		
		//Create folder for text files
		File reportFolder = new File("Detailed Reports");
		reportFolder.mkdir();
		
		//If this is the first time step of the simulation, check if the file name is taken
		//If the file name is taken, create a new name for the file
		File reportFile = new File(reportFolder,"Report" + reportFileNo + ".txt");
		if(currentStep == 0)
		{
			while(reportFile.exists())
			{
				++reportFileNo;
				reportFile = new File(reportFolder, "Report" + reportFileNo + ".txt");
			}
		}
		
		//Print detailed report in a text file
		try 
		{
			FileWriter fw = new FileWriter(reportFile,true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("/// Time Step [" + (currentStep+1) + "] ///");
			pw.printf("Max Allowed Wattage: %,d\t\t\tTotal Wattage before changes: %,d\t\t\tTotal Wattage after changes: %,d\n",maxWattage,totalWattage,totalWattageChanged);
			pw.printf("Smart Appliances turned to low: %d\t\t\tLocations browned out: %d\n\n",reportSmartAppliances.size(),reportLocations.size());
			pw.println("Smart Appliances turned to low:");
			pw.printf("| ID   | Location | Name                                             | Wattage    | Reduction  |\n");
			for(SmartAppliance smartAppliance : reportSmartAppliances)
			{
				pw.printf("| %-4d | %-8d | %-48s | %4d watts | %4d watts |\n", 
						smartAppliance.getID(),smartAppliance.getLocationID(),smartAppliance.getAppName(),smartAppliance.getOnW(),smartAppliance.getDifference());
			}
			pw.println("\nLocations browned out:");
			pw.printf("| Location | Wattage     |\n");
			for(Location location : reportLocations)
			{
				pw.printf("| %-8d | %5d watts |\n",location.getLocationID(),location.getSumWattage());
			}
			pw.print("\n\n\n\n\n\n\n\n\n\n");
			pw.close();
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//If on the last time step, reference the location of the detailed report text file
		if(currentStep == timeSteps-1) {
			System.out.println("*Check \"" + reportFile.getAbsolutePath() + "\" for more info*\n");
		}
	}
	
	public static void main(ArrayList<RegularAppliance> applianceList, int maxWattage, int timeSteps) {
		Simulate.timeSteps = timeSteps;
		for(currentStep=0; currentStep<Simulate.timeSteps; ++currentStep) {
			
			//Populate "on" appliances list
			ArrayList<RegularAppliance> appOn = new ArrayList<RegularAppliance>();
			
			Random randGen = new Random();
			for(int j = 0; j < applianceList.size(); ++j){
				
				if(/*applianceList.get(j).getProbOn() <= 0.01*(randGen.nextInt(100)+1)*/     true){
					appOn.add(applianceList.get(j));
				}
			}
			
			//Calculate sum wattage of "on" list
			int totalWattage = 0;
			for(int i = 0; i < appOn.size(); ++i){
				totalWattage += appOn.get(i).getOnW();
			}
			int totalWattageChanged = totalWattage;
			
			//Check if the total wattage is less than or equal to the maximum permitted wattage
			//If it is, print the summarized and detailed reports and continue to the next time step
			if(totalWattage <= maxWattage) {
				ReportPrint(maxWattage,totalWattage,totalWattageChanged);
				continue;
			}
			
			//If the total wattage is greater than the maximum permitted wattage, make changes to
			//applications/locations to make it under the maximum wattage
			else 
			{
				ArrayList <SmartAppliance> SmartAppliances = new ArrayList<SmartAppliance>(); //Arraylist of appliances that are "ON" and are smart
				ArrayList <SmartAppliance> smartAppliances1 = new ArrayList<SmartAppliance>(); //a copy of smart appliance arraylist
				ArrayList <SmartAppliance> SmartApplianceOrganized = new ArrayList<SmartAppliance>(); //organized list of smart appliances in decreasing order based on their wat reduction
				
				//Iterate through the "on" appliances list and create smart appliance objects from the appliances that are smart
				//Add the created smart appliance objects to two smart appliance lists
				int counter = 0;
				for(int i = 0; i < appOn.size(); i++)
				{
					if(appOn.get(i).getSmart())
					{
						//Position value keeps track of the index where the smart appliance is in smart appliance array. 
						SmartAppliances.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart(), applianceList.get(i).getID() ));
						SmartAppliances.get(counter).setPosition(counter);
						smartAppliances1.add(new SmartAppliance(applianceList.get(i).getLocationID(), applianceList.get(i).getAppName(),applianceList.get(i).getOnW(), applianceList.get(i).getProbOn(), applianceList.get(i).getSmart(), applianceList.get(i).getProbSmart(), applianceList.get(i).getID() ));
						smartAppliances1.get(counter).setPosition(counter);
						counter++;
					}
				}
				
				//Sort the smart appliance list in the order of smallest watt reduction when turned to "low" to highest watt reduction when turned to "low"
				counter = 0;
				int min = smartAppliances1.get(0).getDifference();
				int placement = 0;
				while(counter < SmartAppliances.size())
				{
					placement = 0;
					min = smartAppliances1.get(0).getDifference();
					for(int i = 0; i < smartAppliances1.size(); i++)
					{
						if( min > smartAppliances1.get(i).getDifference())
						{
							placement = i;
							min = smartAppliances1.get(i).getDifference();
						}
					}
					SmartApplianceOrganized.add(smartAppliances1.get(placement));
					smartAppliances1.remove(placement);
					counter++;
				}
				
				//The temporary smart appliance list used for sorting is destroyed
				smartAppliances1 = null;
				
				//Turn the least impactful smart appliance that has not been turned to "low" to "low"
				//Continue doing the above until the total wattage of the system is less than the max wattage
				//or all the smart appliances have been turned to "low"
				for(int i = 0; i < SmartApplianceOrganized.size();i++)
				{
					totalWattageChanged = totalWattageChanged - SmartApplianceOrganized.get(i).getDifference();
					if(totalWattageChanged <= maxWattage)
					{
						reportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
						break;
					}
					else
					{
						reportSmartAppliances.add(  SmartAppliances.get( SmartApplianceOrganized.get(i).getPosition() )   );
					}
				}
				
				//If the total wattage of the system is still greater than the maximum permitted wattage, 
				//Brown out locations until you are under the max wattage
				if(totalWattageChanged > maxWattage) 
				{	
					//Create a list of all the location ID's then create location objects based off those location ID's
					ArrayList<Integer> locationIDList = Location.getLocationIDList(appOn);
					ArrayList<Location> locationList = new ArrayList<Location>();
					for(int i=0; i<locationIDList.size(); ++i) 
					{
						locationList.add(new Location(locationIDList.get(i)));
					}
					
					//Add the wattage of each appliance to their respective location object
					for(int i=0; i<appOn.size(); ++i) 
					{
						RegularAppliance currentAppliance = appOn.get(i);
						for(int j=0; j<locationList.size(); ++j) 
						{
							if(currentAppliance.getLocationID() == locationList.get(j).getLocationID()) 
							{
								if(currentAppliance.getSmart()) 
								{
									int smartApplianceWattage = (int)Math.round(currentAppliance.getOnW() * (1 - currentAppliance.getProbSmart()));
									locationList.get(j).addWattage(smartApplianceWattage);
								}
								else 
								{
									locationList.get(j).addWattage(currentAppliance.getOnW());
								}
								break;
							}
						}
					}
					
					//Find the least impactful location and subtract it from the system's total wattage
					Location minLocation = new Location(-1);
					int minWattLocPos = -1;
					while(totalWattageChanged > maxWattage)
					{
						minLocation.setSumWattage(Integer.MAX_VALUE);
						for(int i=0; i<locationList.size(); ++i)
						{
							if(locationList.get(i).getSumWattage() < minLocation.getSumWattage() && !locationList.get(i).getIsBrowned())
							{
								minLocation.setSumWattage(locationList.get(i).getSumWattage());
								minWattLocPos = i;
							}
						}
						totalWattageChanged -= minLocation.getSumWattage();
						locationList.get(minWattLocPos).setIsBrowned(true);
						reportLocations.add(locationList.get(minWattLocPos));
					}
				}
			}
			
			//Print the changes done by the algorithm
			ReportPrint(maxWattage,totalWattage,totalWattageChanged);
			
			//Empty the lists used for reporting changes
			reportSmartAppliances.clear();
			reportSmartAppliances.trimToSize();
			reportLocations.clear();
			reportLocations.trimToSize();
		}
	}
}
