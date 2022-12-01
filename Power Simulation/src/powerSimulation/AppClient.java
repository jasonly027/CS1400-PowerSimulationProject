package powerSimulation;

import java.util.Scanner;
import java.util.ArrayList;

//------------------------------------------------------------------------------------------------------//
						////////////////////////////////////
						/// ADDITIONAL IS THIS WHOLE FILE //
						////////////////////////////////////
//------------------------------------------------------------------------------------------------------//

public class AppClient {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<RegularAppliance> ApplianceList = new ArrayList<>();
		
		while(true) {
			String userInput = scnr.next();
			if (userInput.equals("A")) {
				//Add
			}
			else if (userInput.equals("D")) {
				//Remove
			}
			else if (userInput.equals("L")) {
				//Print List
			}
			else if (userInput.equals("G")) {
				//Generate
			}
			else if (userInput.equals("X")) {
				//Empty
			}
			else if (userInput.equals("S")) {
				//Simulation
				//Prompt max wattage & time steps
				
				Simulate.main(ApplianceList, maxWatts, timeSteps);
			}
			else if (userInput.equals("Q")) {
				//Quit
			}
			else {
				//Re-prompt
			}
		}
	}
}
