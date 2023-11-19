import java.util.*;
import java.io.*;

public class AssemblerPass1{
	public static void main(String[] args) throws IOException{
		String inputFile = "input.txt";
		String outputFile = "output.txt";
		String symFile = "symbol.txt";
		String LitFile = "literal.txt";
		processPass(inputFile,outputFile,symFile,LitFile);
	}
	public static void processPass(String inputFile,String outputFile,String symFile,String LitFile) throws IOException {
		
		int Lcount = 0;
		int Scount = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		PrintWriter writer = new PrintWriter(new FileWriter(outputFile));
		PrintWriter writer1 = new PrintWriter(new FileWriter(symFile));
		PrintWriter writer2 = new PrintWriter(new FileWriter(LitFile));
		writer1.println(String.format("Symbol \t\t Location"));
		writer2.println(String.format("Literal \t\t Location"));
		int location = 0;
		String line;
		while((line = reader.readLine()) != null){
			line = line.trim();
			location++;
			if(line.isEmpty() || line.startsWith(";")){
				continue;
			}
			String[] tokens = line.split("\\s+");
			for(String token : tokens){
				
				if(isInstruction(token)){
					writer.print(String.format("( IS " + getOpcodeValue(token) + " )"));
				}
				else if(isASD(token)){
					writer.print(String.format("( AD " + getOpcodeValue(token) + " )"));
				}
				else if(isREG(token)){
					writer.print(String.format("( RG " + getOpcodeValue(token) + " )"));
				}
				else if(token.matches("[0-9]+")){
					writer.println(String.format("( CC " + token + " )"));
					location += Integer.parseInt(token);
				}
				else if(token.startsWith("=")){
					writer.println(String.format("( L " + Lcount + " )"));
					writer2.println(String.format(token + "\t\t" + location));
					Lcount++;
				}
				else if(token.startsWith("VAR1") || token.startsWith("RESULT")){
					writer.println(String.format("( S " + Scount + " )"));
					Scount++;
					writer1.println(String.format(token + "\t\t" + location));
				}
			}
		}
		System.out.println("Pass 1 Execulted Successfully!!");
		writer.close();
		writer1.close();
		writer2.close();
	}
	public static boolean isInstruction(String token){
		String[] instructions = {"MOVER","MOVEM","ADD","SUB","MULT","DIV","READ","PRINT"};
		for(String instruction : instructions){
			if(token.matches(instruction)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isASD(String token){
		String[] instructions = {"START","STOP","END","ORIGIN","LTROG"};
		for(String instruction : instructions){
			if(token.matches(instruction)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isREG(String token){
		String[] instructions = {"AREG","BREG","CREG"};
		for(String instruction : instructions){
			if(token.matches(instruction)){
				return true;
			}
		}
		return false;
	}	
	public static int getOpcodeValue(String token){
		switch(token){
			case "START":
				return 01;
			case "MOVER":
				return 04;
			case "MOVEM":
				return 05;
			case "ADD":
				return 01;
			case "SUB":
				return 01;
			case "MULT":
				return 02;
			case "DIV":
				return 02;
			case "STOP":
				return 00;
			case "LTROG":
				return 01;
			case "ORIGIN":
				return 01;
			case "END":
				return 02;
			case "AREG":
				return 01;
			case "BREG":
				return 02;
			case "CREG":
				return 03;
			default:
				return -1;
		}
	}
}
