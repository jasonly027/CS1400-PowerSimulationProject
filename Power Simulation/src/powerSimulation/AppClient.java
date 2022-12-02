package powerSimulation;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.io.File;
import java.io.IOException;

//------------------------------------------------------------------------------------------------------//
						////////////////////////////////////
						/// ADDITIONAL IS THIS WHOLE FILE //
						////////////////////////////////////
//------------------------------------------------------------------------------------------------------//

public class AppClient {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<RegularAppliance> ApplianceList = new ArrayList<>();
		
		appMenu();
		while(true) {
			String userInput = scnr.nextLine();
			if (userInput.equals("A")) {
				//Add
				while(true) {
					System.out.println("Input appliance data or type \"C\" to quit");
					System.out.println("location, appliance description, watt used, probability of appliance \"on\", smart appliance?: True or False, percentage reduction (0.0 for regular)");
					System.out.println("ex) 10000001,VCR,45,0.05,false,0.0");
					System.out.println("ex) 10000002,Laptop,0.20,true,0.23"); 
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}else {
						String []inputArray= userInput.split(",");
						
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
							Double.parseDouble(inputArray[3]);
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
							Double.parseDouble(inputArray[5]);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						
						ApplianceList.add(new RegularAppliance(inputArray[0], inputArray[1], inputArray[2], inputArray[3], inputArray[4], inputArray[5]));
					}
				}
				
			}
			else if (userInput.equals("D")) {
				//Remove
				while(true) {
					System.out.println("Input valid appliance ID or type \"c\" to quit");
					userInput = scnr.nextLine();
					if(userInput.equals("C")) {
						appMenu();
						break;
					}else {
						try {
							Integer.parseInt(userInput);
						}catch(NumberFormatException e) {
							System.out.println(wrongInputDisplay());
							continue;
						}
						for(int i=0;i<applianceList.size();i++) { //This will delete appliances with the same ID. Pls fix! It should remove a specific appliance!
							if(applianceList.get(i).getID().equals(userInput)) {
								applianceList.remove(i);
								break;
							}						
						}
					}
				}
			}
			else if (userInput.equals("L")) {
				//Print List
				for(int i=0;i<applianceList.size();i++) {
					System.out.println(applianceList.get(i));
				}
			}
			else if (userInput.equals("G")) {
				//Generate
				ApplianceGenerator.main(args);
				try {
					File myfile = new File("output.txt");
					Scanner scanner = new Scanner(myfile);
					for(int i=0;i<applianceList.size();i++) {
						String fileInput = scanner.nextLine();
						String []array = fileInput.split(",");
						ApplianceList.add(new RegularAppliance(inputArray[0], inputArray[1], inputArray[2], inputArray[3], inputArray[4], inputArray[5]));
					}						
				}
				}
				catch(IOException ioe) {
					System.out.println("File not found");
				}
				appMenu();

			}
			else if (userInput.equals("X")) {
				//Empty
				applianceList.clear();
				applianceList.trimToSize();
			}
			else if (userInput.equals("S")) {
				//Simulation
				//Prompt max wattage & time steps
				String maxWatts = new String();
				String timeSteps = new String();
				while(true) {
				System.out.println("Input total allowed wattage");
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
				while(true) {
				System.out.println("Input time steps");
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
				System.out.println("Starting simulation");
				
				Simulate.main(ApplianceList, Integer.parseInt(maxWatts), Integer.parseInt(timeSteps));
				
			}
			else if (userInput.equals("Q")) {
				//Quit
				System.exit(0);
			}
			else {
				//Re-prompt
				System.out.println(wrongInputDisplay());
				appMenu();
			}
		}
	}
	
	
	public static void appMenu() {
		System.out.println("Select an option:");
		System.out.println("\t Type \"A\" Add an appliance");
		System.out.println("\t Type \"D\" Delete an appliance");	
		System.out.println("\t Type \"L\" Print the appliances");
		System.out.println("\t Type \"G\" Generate text file");
		System.out.println("\t Type \"X\" Delete all appliances");
		System.out.println("\t Type \"S\" To Start the simulation");
		System.out.println("\t Type \"Q\" Quit the program");
	}
	
	public static String wrongInputDisplay() {
		String output = "/// Wrong input /// \n";
		return output;
	}

	
}
