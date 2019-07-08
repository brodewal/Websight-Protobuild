package Assiments;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ReadWriteFileDemo {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		UserInt usr=new UserInt();
		ArrayandMore array=new ArrayandMore();
		File fileIn= new File("C:\\Users\\gce\\Downloads\\PersonInfoDataFile.txt");
		File fileOutc= new File("C:\\Users\\gce\\Downloads\\InfoFileCopy.txt");
		FileWriter fileOut= new FileWriter("C:\\Users\\gce\\Downloads\\InfoFileCopy.txt");
		FileWriter fileOut2= new FileWriter("C:\\Users\\gce\\Downloads\\InfoFileCopy.txt");
	    Scanner table1 = new Scanner(fileIn); 
	    Scanner tableIn = new Scanner(fileIn);
	    Scanner tableOut = new Scanner(fileOutc);
	    Scanner input =new Scanner(System.in);
	    fileOutc.createNewFile();
	    while (table1.hasNextLine()) {
	    	String lineFromFile = table1.nextLine();
	    	System.out.println(lineFromFile);
	    	fileOut.write(lineFromFile);
	    }
	    fileOut.close();
	    
	    String [] Collums= {"EmployeeID", "LastName", "FirstName", "Rank", "SvcBranch", "DutyStatusCode", "EligibilityCode", "RemainingBenefits"};  
	    System.out.println("please enter a field you whish to search for in the file. You can search in the following caigories"+
	    	    "\n"+Arrays.toString(Collums));
	    String Search =input.nextLine();
	    
	    while (tableIn.hasNextLine()) {
	    	String lineFromFile = tableIn.nextLine();
	    	if(lineFromFile.contains(Search)){
	    		System.out.println(lineFromFile);
	    		break;
	    	}
	    }
	    Writer writer = new BufferedWriter(new OutputStreamWriter(
	            new FileOutputStream("C:\\Users\\gce\\Downloads\\InfoFileCopy.txt", true)));
	    String userLine="13	";
	    for(int i=1; i<Collums.length; i++) {
	    	System.out.println("Please enter the value of your employees: "+Collums[i]);
	    	String add =input.nextLine();
	    	if(i<(Collums.length-1)) {userLine+=(add+"	");}
	    	else {userLine+=add;}
	    }
	    System.out.println(userLine);
	    writer.write(userLine);
	    writer.write("\n");
	    writer.close();
    	
	    while (tableOut.hasNextLine()) {
	    	String lineFromFile = tableOut.nextLine();
	    	System.out.println(lineFromFile);
	    }
	    
	    while (tableOut.hasNextLine()) {
	    	String lineFromOutFile = tableOut.nextLine();
		    while (tableIn.hasNextLine()) {
		    	String lineFromInFile = tableIn.nextLine();
		    	if(lineFromOutFile.equalsIgnoreCase(lineFromInFile)){break;}
		    	else {System.out.println("InfoFileCopy contains an additional record(s)"+"\n"+lineFromOutFile+"\n");
		    }
		   }
	    }
		int num;
		boolean CH1= false;
		boolean CH2 = false;
		System.out.println("Please type a single key value and then press the 'Enter' key");
		num=usr.getUsetInt();
		System.out.println("the key you have pressed is the "+ (char)num +" key");
		System.out.println("the ASCII value of key you have pressed is "+ num);
		
		if(usr.checkUserInt(num)==true) {
			CH1=true;
		}else{
			CH1=false;
		}
		if(array.chechInBounds(num)==true) {
			CH2=true;
		}else{
			CH1=false;
		}
		array.displayArrayValue(num, CH1, CH2);
	}

}