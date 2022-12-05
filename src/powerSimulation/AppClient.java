package powerSimulation;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class AppClient {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<RegularAppliance> applianceList = new ArrayList<>();
		
		while(true) {
			appMenu();
			String userInput = scnr.nextLine();
			
			//Manually add a custom appliance by inputting data for it
			//or input "C" to return to main menu
			if (userInput.equals("A")) {
				while(true) {
					System.out.println("\nInput appliance data or type \"C\" to quit");
					System.out.println("/// Appliance data should be in the format of:\n    (Location ID (Integer),"
									 													  + "Appliance Description,"
									 													  + "Watt usage (Integer),"
									 													  + "Probability of appliance being \"on\" (0.00 to 1.00),"
									 													  + "Is it a smart appliance? (true or false),"
									 													  + "Percentage of watt reduction if it is a smart appliance (0.00 to 1.00)"
									 													  + "*Input 0.00 if it is a regular appliance");
					System.out.println("/// Example inputs: 10000001,VCR,45,0.05,false,0.0");
					System.out.println("                    10000002,Laptop,120,0.20,true,0.23\n");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) 
					{
						break;
					}
					else 
					{
						String []inputArray= userInput.split(",");
						
						//Validates input data before creating an appliance with it
						if(inputArray.length != 6) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Integer.parseInt(inputArray[0]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Integer.parseInt(inputArray[2]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							if(Double.parseDouble(inputArray[3]) < 0.00 || Double.parseDouble(inputArray[3]) > 1.00) {
								System.out.println(wrongInputDisplay());
								continue;
							}
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							Boolean.parseBoolean(inputArray[4]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						try {
							if(Double.parseDouble(inputArray[5]) < 0.00 || Double.parseDouble(inputArray[5]) > 1.00) {
								System.out.println(wrongInputDisplay());
								continue;
							}
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						
						//Create appliance from input data and add to list
						applianceList.add(new RegularAppliance(Integer.parseInt(inputArray[0]), 
															   inputArray[1], 
															   Integer.parseInt(inputArray[2]), 
															   Double.parseDouble(inputArray[3]), 
															   Boolean.parseBoolean(inputArray[4]), 
															   Double.parseDouble(inputArray[5])));
						System.out.println("Appliance has been successfully added");
					}
				}
				
			}
			
			//Remove an appliance by inputting the ID for it
			//or input "C" to return to main menu
			else if (userInput.equals("D")) {
				while(true) {
					
					//Check that appliance list isn't empty before manipulating it
					if(applianceList.size() == 0) {
						System.out.println("/// ERROR: Cannot use this option if the appliance list is empty ///\n");
						break;
					}
					
					System.out.println("Input valid appliance ID or type \"C\" to quit\n");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						break;
					}
					
					//Search for appliance in list with matching ID as inputed ID and remove it
					else
					{
						try {
							for(int i=0;i<applianceList.size();i++) {
								if(applianceList.get(i).getID() == Integer.parseInt(userInput)) {
									System.out.println("Appliance " + userInput + " has been successfully deleted\n");
									applianceList.remove(i);
									break;
								}
							}
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
					}
				}
			}
			
			//Print all of the appliances in the list
			else if (userInput.equals("L")) {
				System.out.println("Appliance List:");
				for(int i=0;i<applianceList.size();i++) {
					System.out.println("/// " + applianceList.get(i));
				}
				System.out.println();
			}
			
			//Generate a text file with random appliances and add to list
			else if (userInput.equals("G")) {
				try {
					ApplianceGenerator.main(args);
					File myfile = new File("output.txt");
					Scanner scanner = new Scanner(myfile);
					while(scanner.hasNextLine()) {
						String fileInput = scanner.nextLine();
						String []inputArray = fileInput.split(",");
						applianceList.add(new RegularAppliance(Integer.parseInt(inputArray[0]), 
															   inputArray[1], 
															   Integer.parseInt(inputArray[2]), 
															   Double.parseDouble(inputArray[3]), 
															   Boolean.parseBoolean(inputArray[4]), 
															   Double.parseDouble(inputArray[5])));
					}
					scanner.close();
				}
				catch(IOException ioe) {
					System.out.println("/// ERROR: Program cannot find the file \"ApplianceDetail.txt\" used for generating appliances ///\n");
				}
				System.out.println("Successfully added randomly generated appliances to the appliance list\n");
			}
			
			//Input the location of a text file and add appliances based off its content
			//or input "C" to return to main menu
			else if (userInput.equals("F")) {
				while(true) {
					System.out.println("Input path to text file or type \"C\" to quit\n");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						break;
					}
					try {
						File myfile = new File(userInput);
						Scanner scanner = new Scanner(myfile);
						while(scanner.hasNextLine()) {
							String fileInput = scanner.nextLine();
							String []inputArray = fileInput.split(",");
							applianceList.add(new RegularAppliance(Integer.parseInt(inputArray[0]), 
																   inputArray[1], 
																   Integer.parseInt(inputArray[2]), 
																   Double.parseDouble(inputArray[3]), 
																   Boolean.parseBoolean(inputArray[4]), 
																   Double.parseDouble(inputArray[5])));
						}
						scanner.close();
						System.out.println("Appliances from text file have been successfully added\n");
						break;
						
					}catch (IOException ioe) {
						System.out.println("/// ERROR: Program cannot find a file at the location \"" + userInput + "\" ///\n");
					}
					catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("/// ERROR: Text file has improper formatting ///\n");
					}
					catch (NumberFormatException e) {
						System.out.println("/// ERROR: Text file has improper formatting ///\n");
					}
				}
			}
			
			//Empty the appliance list
			else if (userInput.equals("X")) {
				//Empty
				applianceList.clear();
				applianceList.trimToSize();
				System.out.println("Appliance list successfully emptied\n");
			}
			
			//Start simulation
			else if (userInput.equals("S")) {
				
				//Check that the appliance list isn't empty before starting simulation
				if(applianceList.size() == 0) {
					System.out.println("/// ERROR: Cannot use this option if the appliance list is empty ///\n");
					continue;
				}
				String maxWatts = new String();
				String timeSteps = new String();
				
				//Ask user for total allowed wattage
				while(true) {
				System.out.println("Input total allowed wattage: ");
				maxWatts = scnr.nextLine();
				try {
					if (Integer.parseInt(maxWatts)<=0) {
						System.out.println(wrongInputDisplay());
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println(wrongInputDisplay());
					continue;
				}
					break;
				}
				
				//Ask user for number of time steps
				while(true) {
				System.out.println("Input time steps: ");
				timeSteps = scnr.nextLine();
				try {
					if (Integer.parseInt(timeSteps)<=0) {
						System.out.println(wrongInputDisplay());
						continue;
					}
				}catch(NumberFormatException e) {
					System.out.println(wrongInputDisplay());
					continue;
				}
					break;
				}
				
				//Start simulation with user inputed max wattage and number of time steps
				System.out.println("< - - - Simulation Start - - - >\n");
				Simulate.main(applianceList, Integer.parseInt(maxWatts), Integer.parseInt(timeSteps));
				System.out.println("< - - - Simulation End - - - >\n");
			}
			
			//Close program
			else if (userInput.equals("Q")) {
				//Quit
				System.out.println("Closing Program\n");
				System.exit(0);
			}
			
			//Tell user to try inputting a menu option again
			else {
				System.out.println(wrongInputDisplay());
			}
		}
	}
	
	/**
	 * {@code public static void appMenu()}
	 * <p>
	 * Prints the buttons used for navigating the menu and their respective purposes
	 */
	public static void appMenu() {
		System.out.println("Select an option:");
		System.out.println("/// Type \"A\" to add an appliance to the appliance list");
		System.out.println("/// Type \"D\" to delete an appliance from the appliance list");	
		System.out.println("/// Type \"L\" to print the appliance list");
		System.out.println("/// Type \"G\" to add randomly generated appliances to the appliance list");
		System.out.println("/// Type \"F\" to add appliances from a text file");
		System.out.println("/// Type \"X\" to empty the appliance list");
		System.out.println("/// Type \"S\" to start the simulation");
		System.out.println("/// Type \"Q\" to quit the program");
		System.out.println();
	}
	
	/**
	 * {@code public static String wrongInputDisplay()}
	 * <p>
	 * Returns the error message used when the program is given invalid input
	 * @return The error message used when the program is given invalid input 
	 */
	public static String wrongInputDisplay() {
		String output = "/// ERROR: Invalid input ///\n";
		return output;
	}

	
}
