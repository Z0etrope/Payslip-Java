import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  

public class Payslip {
	
	public static int gross(String income) {// function to calculate gross income
		try {
			int aYear = 12; // months per year
			int gross = Math.abs(Integer.valueOf(income)) / aYear; // convert string into integer
			return gross;
		}
		catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		return 0;
	}
	
	public static int tax(String income) {//function to calculate annual tax based on income
		try {
			int aYear = 12; // months per year
			int gross = Math.abs(Integer.valueOf(income)); // convert string into integer
			int tax = 0;
			
			if (gross < 18201) {// if income is below 18200
				return gross/aYear;
			}
			else if (gross > 18200 && gross < 37001) {// if income is between 18200 and 37000 dollar
				tax = (int) Math.round(0.19*(gross-18200)/aYear);
				return tax;
			}
			else if (gross > 37000 && gross < 87001) {//if income is between 37000 and 87000 dollar
				tax = (int) Math.round((0.325*(gross-37000)+3572)/aYear);
				return tax;
			}
			else if (gross > 87000 && gross < 180001) {//if income is between 87000 and 180000 dollar
				tax = (int) Math.round((0.37*(gross-87000)+19822)/aYear);
				return tax;
			}
			else if (gross > 180000) {//if income is above 180000 dollar
				tax = (int) Math.round((0.45*(gross-180000)+54232)/aYear);
				return tax;
			}
		}
		catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		return 0;
	}
	
	public static int superTax(String sprTax, int income) {//function to calculate super based on gross income and super rate
		try {
			String parsedInc = sprTax.replace("%","");//strip the string of special char before converting it to integer
			int superVal = Math.abs(Integer.valueOf(parsedInc));
			return Math.round(superVal * income / 100);
		}
		catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		return 0; 
	}
	
	public static void main(String[] args) {
		String line = "";  
		String splitBy = ",";  
		String fileName = args[0].replace(" ", "");//make sure no whitespace in the name
		File file = new File(fileName);
		if(!file.exists()) {
			System.out.println("File does not exist");
			return ;
		}
		
		try{
			FileWriter payWriter = new FileWriter("Payslip.csv");// Create a new CSV file
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null){  //reads the file line by line
				String[] Data = line.split(splitBy);    // use comma as separator
				int grossInc = gross(Data[2]);
				int taxInc = tax(Data[2]);
				int netInc = grossInc - taxInc;
				int superInc = superTax(Data[3],grossInc);
				
				// Write into .csv file
				payWriter.append(Data[0] + " " + Data[1]);
				payWriter.append(",");
				payWriter.append(Data[4] + ",");
				payWriter.append(grossInc + ",");
				payWriter.append(taxInc + ",");
				payWriter.append(netInc + ",");
				payWriter.append(superInc + "\n");
			}
			
			payWriter.flush();// flush and close the file
			payWriter.close();
		} 
		catch (IOException e){  
			e.printStackTrace();  
		}  
	}

}
