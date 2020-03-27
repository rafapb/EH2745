package Project1Pack;

import java.util.ArrayList;
import java.util.List;

// main class for creating YBUS matrix
public class Ybus {

		//List <String> OUTput = new ArrayList<String>();
		static double Sbase = 1000 ; //MVA
		static double Zbase, puR, puX, puB, puG;
		static String Bus[]= new String[5]; // power system with 5 buses
		static Complex zero = new Complex(0,0);		
		static Complex Ybus[][] = new Complex[5][5];
		
		// method for building YBUS matrix
		public static Complex[][] yBusMaking(String EQ, String SSH) {
			
			// Setting the initial value of the YBUS matrix equal to zero
			ParsingXML parsingObject = new ParsingXML();
			parsingObject.parsingMethod(EQ, SSH);
			for (int i = 0; i < parsingObject.getBusBarList().size(); i++){
				  for (int j = 0; j < parsingObject.getBusBarList().size(); j++){
				      Ybus[i][j] = zero;
				  }
				}
			
			// Filling the Bus array with all the buses rdfIDs 
			int k=0;
		for (BusBarClass newBus : parsingObject.getBusBarList()) {
			List <String> tempBus = new ArrayList<String>();
			String BusrdfID = newBus.getrdfID();
			tempBus.add(BusrdfID);
			Bus[k]=tempBus.get(0); // here we have a list of buses
			k++;
		}
			/////////////////////////////////////////////////////////////////////////
	        // In this step we create a list of lines with their corresponding buses	
			// AC Line / FromBus / ToBus Matching **********************************
			for (ACLineClass newLine : parsingObject.getACLineList()) { // starting from ACLineClass
				List <String> tempLE = new ArrayList<String>(); // creating a temporary array list for refreshing with each new line
				double newR = newLine.getR();
				double newX = newLine.getX();		
				double newB = newLine.getBch();	
				double newG = newLine.getGch();
				String BaseID = newLine.getBaseVoltID();
				String LErdfID = newLine.getrdfID();
				tempLE.add(LErdfID); // adding the line rdfID in the temporary list
				
				// checking all the Terminal's Conducting Equipment ID with the added line rdfID
				// in case they match, the Terminal's Connectivity Node will be added to the line
				for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
					String TermCondEqID = newTerminal.getConductingEqID();
					String TermCNodeID = newTerminal.getConnectNodeID();
					if (newLine.getrdfID().equals(TermCondEqID)) {
						tempLE.add(TermCNodeID); // line's connectivity node was founded and added to the list
					}
				}
				// first line was matched with its corresponding connectivity nodes
				
				boolean check1=true;
				boolean check2=true;
				// Creating a list of breakers with their corresponding connectivity nodes ********************
				// the same procedure as Line is taken
				for (BreakerClass newBreaker  : parsingObject.getBreakerList()) {
					List <String> tempBR = new ArrayList<String>();
					String BRrdfID = newBreaker.getrdfID();
					boolean state= newBreaker.getBRstate();
					tempBR.add(BRrdfID);
					
					for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
						String TermCondEqID = newTerminal.getConductingEqID();
						String TermCNodeID = newTerminal.getConnectNodeID();
						if (newBreaker.getrdfID().equals(TermCondEqID)) {
							tempBR.add(TermCNodeID);
						}
					}
					// first breaker was matched with its corresponding connectivity nodes and added to temporary list
					
					//// Checking Breakers Status ************************************************
					// in this step, first we check if the selected line has a common connectivity node with this breaker
					// second, if the line was connected to this breaker, the status of the breaker must be checked
					// the first connectivity node of the line is checked
					if(tempLE.get(1).equals(tempBR.get(1)) || tempLE.get(1).equals(tempBR.get(2))){
						if( state==true){ // if the open state of the breaker is true, then the boolean flag change to false
							check1=false;							
						}
					}
					// repeating the same procedure for the second connectivity node of the line
					if(tempLE.get(2).equals(tempBR.get(1)) || tempLE.get(2).equals(tempBR.get(2))){
						if( state==true){ // if the open state of the breaker is true, then the boolean flag change to false
							check2=false;							
						}												
					}
				}// all the breakers status were checked
				// if any of the line's breakers is open, that line is out, boolean flag (check1/check2) is false
				
				boolean check3=true;
				boolean check4=true;
				// the condition for adding the line to YBUS matrix is checking breaker status
				if (check1==true && check2==true){ //both breakers must be open
					for (BreakerClass newBreaker  : parsingObject.getBreakerList()) {
						List <String> tempBR = new ArrayList<String>();
						String BRrdfID = newBreaker.getrdfID();
						tempBR.add(BRrdfID);
						
						for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
							String TermCondEqID = newTerminal.getConductingEqID();
							String TermCNodeID = newTerminal.getConnectNodeID();
							if (newBreaker.getrdfID().equals(TermCondEqID)) {
								tempBR.add(TermCNodeID);
							}
						}
				// Skipping the breaker and finding the connectivity node at the other side of the breaker
				// if the line's CNode match with the breaker's FIRST CNode, we set the breaker's SECOND CNode to the line
					if (check3==true){
					if(tempLE.get(1).equals(tempBR.get(1))){
						check3=false;
						tempLE.set(1,tempBR.get(2));
					}else if(tempLE.get(1).equals(tempBR.get(2))){
						check3=false;
						tempLE.set(1,tempBR.get(1));
					}
					}
					
					if (check4==true){	
					if(tempLE.get(2).equals(tempBR.get(1))){
						check4=false;
						tempLE.set(2,tempBR.get(2));
					}else if(tempLE.get(2).equals(tempBR.get(2))){
						check4=false;
						tempLE.set(2,tempBR.get(1));						
					}
					}
			    } // Breakers were skipped
				
				// in this step, first we create a list of Buses with their corresponding connectivity nodes
				for (BusBarClass newBus : parsingObject.getBusBarList()) {
					List <String> tempBus = new ArrayList<String>();
					String BusrdfID = newBus.getrdfID();
					tempBus.add(BusrdfID);
					
					for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
						String TermCondEqID = newTerminal.getConductingEqID();
						String TermCNodeID = newTerminal.getConnectNodeID();
						if (newBus.getrdfID().equals(TermCondEqID)) {
							tempBus.add(TermCNodeID);
						}
					}
					
				///// if the CNodes of the Line and the Bus match with each other, add that bus to the line list
					if(tempLE.get(1).equals(tempBus.get(1))){
						tempLE.set(1,tempBus.get(0));
					}else if(tempLE.get(2).equals(tempBus.get(1))){
						tempLE.set(2,tempBus.get(0));						
					}
				} // the list of first line with its corresponding buses were obtained
				System.out.println("Line,FromBus,ToBus: "+ tempLE + "\n");
				
				///////////////////////////////////////////////////////////////////////
				// Calculating Zbase and per-unit values
				for (BaseVoltClass newBaseV : parsingObject.getBaseVoltList()) {
					if (BaseID.equals(newBaseV.getrdfID())){
						Zbase = Math.pow(newBaseV.getNomValue(), 2)/Sbase;
						puR= newR/Zbase;
						puX= newX/Zbase;
						puB= (newB*Zbase)/2.0;
						puG= (newG*Zbase)/2.0;
						tempLE.add(Double.toString(puR));
						tempLE.add(Double.toString(puX));
						tempLE.add(Double.toString(puG));
						tempLE.add(Double.toString(puB));
					} // adding per-unit values of R, X, G and B to the line list
				}
				
				////////////////////////////////////////////////////////////////////////
				// Creating YBUS Matrix ***********************************************
				for (int i=0; i<parsingObject.getBusBarList().size(); i++) {
					if(tempLE.get(1).equals(Bus[i])){
						Complex Z1= new Complex(Double.parseDouble(tempLE.get(3)),Double.parseDouble(tempLE.get(4))); // impedance of the line
						Complex BG1= new Complex(Double.parseDouble(tempLE.get(5)),Double.parseDouble(tempLE.get(6))); // admittance of the line
						Complex one = new Complex(1.0, 0.0);
						Complex y1= one.divides(Z1);
						Complex y11= y1.plus(BG1);
						Ybus[i][i]= y11.plus(Ybus[i][i]); // adding diagonal elements
						for(int j=0; j<parsingObject.getBusBarList().size(); j++){
							if(tempLE.get(2).equals(Bus[j])){
								Complex Z2= new Complex(Double.parseDouble(tempLE.get(3)),Double.parseDouble(tempLE.get(4)));
								Complex BG2= new Complex(Double.parseDouble(tempLE.get(5)),Double.parseDouble(tempLE.get(6)));
								Complex y2= one.divides(Z2);
								Complex y22= y2.plus(BG2);
								Ybus[j][j]= y22.plus(Ybus[j][j]); // adding diagonal elements
								Complex neg= new Complex(-1.0, 0.0);
								Complex negy= y2.times(neg);
								Ybus[i][j]= negy.plus(Ybus[i][j]); // adding non-diagonal elements
								Ybus[j][i]= negy.plus(Ybus[j][i]); // adding non-diagonal elements
							}
						}
					}
				}
			}
			// repeating the same procedure for adding each line to the YBUS matrix
		}
			
			///////////////////////////////////////////////////////////////////////////////////////
			// In this step we create a list of Transformers with their corresponding buses	
			// Transformer / FromBus / ToBus Matching ************************************
			// Exactly the same procedure as the line is taken ***************************
			for (PowerTransClass newTrans: parsingObject.getPowTrList()) {
				List <String> tempTR = new ArrayList<String>();
				String TRrdfID = newTrans.getrdfID();
				tempTR.add(TRrdfID);
				
				for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
					String TermCondEqID = newTerminal.getConductingEqID();
					String TermCNodeID = newTerminal.getConnectNodeID();
					if (newTrans.getrdfID().equals(TermCondEqID)) {
						tempTR.add(TermCNodeID);
					}
				}// first Transformer was matched with its corresponding connectivity nodes
				
				boolean check1=true;
				boolean check2=true;
				// Creating a list of breakers with their corresponding connectivity nodes
				for (BreakerClass newBreaker  : parsingObject.getBreakerList()) {
					List <String> tempBR = new ArrayList<String>();
					String BRrdfID = newBreaker.getrdfID();
					boolean state= newBreaker.getBRstate();
					tempBR.add(BRrdfID);
					
					for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
						String TermCondEqID = newTerminal.getConductingEqID();
						TermCondEqID = TermCondEqID.startsWith("#") ? TermCondEqID.substring(1) : TermCondEqID;	
						String TermCNodeID = newTerminal.getConnectNodeID();
						TermCNodeID = TermCNodeID.startsWith("#") ? TermCNodeID.substring(1) : TermCNodeID;	
						if (newBreaker.getrdfID().equals(TermCondEqID)) {
							tempBR.add(TermCNodeID);
						}
					}// first breaker was matched with its corresponding connectivity nodes
				
					//// Checking Breakers Status ************************************************
					if(tempTR.get(1).equals(tempBR.get(1)) || tempTR.get(1).equals(tempBR.get(2))){
						if( state==true){
							check1=false;							
						}
					}
					if(tempTR.get(2).equals(tempBR.get(1)) || tempTR.get(2).equals(tempBR.get(2))){
						if( state==true){
							check2=false;		
						}	
					}
				}// all the breakers status were checked
				// if any of the transformer's breakers is open, that transformer is out, boolean flag (check1/check2) is false
				
				boolean check3=true;
				boolean check4=true;
				// the condition for adding the transformer to YBUS matrix is checking breaker status
				if (check1==true && check2==true){
				for (BreakerClass newBreaker  : parsingObject.getBreakerList()) {
					List <String> tempBR = new ArrayList<String>();
					String BRrdfID = newBreaker.getrdfID();
					tempBR.add(BRrdfID);
					
					for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
						String TermCondEqID = newTerminal.getConductingEqID();
						TermCondEqID = TermCondEqID.startsWith("#") ? TermCondEqID.substring(1) : TermCondEqID;	
						String TermCNodeID = newTerminal.getConnectNodeID();
						TermCNodeID = TermCNodeID.startsWith("#") ? TermCNodeID.substring(1) : TermCNodeID;	
						if (newBreaker.getrdfID().equals(TermCondEqID)) {
							tempBR.add(TermCNodeID);
						}
					}
		// Skipping the breaker and finding the connectivity node at the other side of the breaker
		// if the transformer's CNode match with the breaker's FIRST CNode, we set the breaker's SECOND CNode to the transformer
					if (check3==true){
					if(tempTR.get(1).equals(tempBR.get(1))){
						check3=false;
						tempTR.set(1,tempBR.get(2));
					}else if(tempTR.get(1).equals(tempBR.get(2))){
						check3=false;
						tempTR.set(1,tempBR.get(1));
					}
					}
					if (check4==true){	
					if(tempTR.get(2).equals(tempBR.get(1))){
						check4=false;
						tempTR.set(2,tempBR.get(2));
					}else if(tempTR.get(2).equals(tempBR.get(2))){
						check4=false;
						tempTR.set(2,tempBR.get(1));						
					}
					}
				}
				// Breakers were skipped
				
				//////// list of Buses with their corresponding connectivity nodes
				for (BusBarClass newBus : parsingObject.getBusBarList()) {
					List <String> tempBus = new ArrayList<String>();
					String BusrdfID = newBus.getrdfID();
					tempBus.add(BusrdfID);
					
					for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
						String TermCondEqID = newTerminal.getConductingEqID();
						String TermCNodeID = newTerminal.getConnectNodeID();
						if (newBus.getrdfID().equals(TermCondEqID)) {
							tempBus.add(TermCNodeID);
						}
					}
				///// if the CNodes of the transformer and the Bus match with each other, add that bus to the transformer list
					if(tempTR.get(1).equals(tempBus.get(1))){
						tempTR.set(1,tempBus.get(0));
					}else if(tempTR.get(2).equals(tempBus.get(1))){
						tempTR.set(2,tempBus.get(0));						
					}
				}// the list of first transformer with its corresponding buses were obtained
				System.out.println("Transformer,FromBus,ToBus: "+ tempTR+ "\n");
				
				// Finding corresponding power transformer end (winding)
				for(PowTrEndClass newTrafoEnd : parsingObject.getTrWindList()){
					String PoTrdfID = newTrafoEnd.getTransrdfID();
					
					if(PoTrdfID.equals(TRrdfID)){
						if(newTrafoEnd.getTransRvalue()!=0){
							double TrafoR = newTrafoEnd.getTransRvalue();
							double TrafoX  = newTrafoEnd.getTransXvalue();
							double TrafoG = newTrafoEnd.getTransGvalue();
							double TrafoB  = newTrafoEnd.getTransBvalue();
							
							String TrafoBase = newTrafoEnd.getBaseVoltrdfID();
							for (BaseVoltClass newBaseV : parsingObject.getBaseVoltList()) {
								if(newBaseV.getrdfID().equals(TrafoBase)){
									Zbase = Math.pow(newBaseV.getNomValue(), 2)/Sbase;
									puR= TrafoR/Zbase;
									puX= TrafoX/Zbase;
									puG= TrafoG*Zbase;
									puB= TrafoB*Zbase;
									tempTR.add(Double.toString(puR));
									tempTR.add(Double.toString(puX));
									tempTR.add(Double.toString(puG));
									tempTR.add(Double.toString(puB));
								}// adding per-unit values of R, X, G and B to the transformer list
							}
						}
					}	
				}
                 ////////////////////////////////////////////////////////////////////////
                // Adding to YBUS Matrix ***********************************************
				for (int i=0; i<parsingObject.getBusBarList().size(); i++) {
					if(tempTR.get(1).equals(Bus[i])){
						Complex Z1= new Complex(Double.parseDouble(tempTR.get(3)),Double.parseDouble(tempTR.get(4)));
						Complex BG1= new Complex(Double.parseDouble(tempTR.get(5)),Double.parseDouble(tempTR.get(6)));
						Complex one = new Complex(1.0, 0.0);
						Complex y1= one.divides(Z1);
						Complex y11=y1.plus(BG1);
						Ybus[i][i]=y11.plus(Ybus[i][i]);
						for(int j=0; j<parsingObject.getBusBarList().size(); j++){
							if(tempTR.get(2).equals(Bus[j])){
								Complex Z2= new Complex(Double.parseDouble(tempTR.get(3)),Double.parseDouble(tempTR.get(4)));
								Complex BG2= new Complex(Double.parseDouble(tempTR.get(5)),Double.parseDouble(tempTR.get(6)));
								Complex y2= one.divides(Z2);
								Complex y22=y2.plus(BG2);
								Ybus[j][j]=y22.plus(Ybus[j][j]);
								Complex neg= new Complex(-1.0, 0.0);
								Complex negy=y2.times(neg);
								Ybus[i][j]=negy.plus(Ybus[i][j]);
								Ybus[j][i]=negy.plus(Ybus[j][i]);
							}
						}
					}
				}
			}// repeating the same procedure for adding each transformer to the YBUS matrix
		}
			////////////////********* YBUS MATRIX with Line and Transformer *********//////////////////
			System.out.println("YBUS with Line and Transformer: ");
			for (int i=0; i<5;i++){
				for(int j=0; j<5; j++){
					System.out.print(Ybus[i][j]+"\t");	
				}
				System.out.println("\t");
			}
						
			//////////////////// Adding Linear Shunt Compensator to YBUS matrix ////////////////////////
			// Matching Shunt Compensator with corresponding Bus **************************************
			for (ShuntClass newShunt: parsingObject.getShuntList()) {
				List <String> tempSH = new ArrayList<String>();
				double newG = newShunt.getGvalue();	
				double newB = newShunt.getBvalue();
				String SHrdfID = newShunt.getrdfID();
				tempSH.add(SHrdfID);
				String ShEqID = newShunt.getEqContID();
				
				for (BusBarClass newBus : parsingObject.getBusBarList()) {
					String BusrdfID = newBus.getrdfID();
					String BusEqID = newBus.getEqContID();
					if(ShEqID.equals(BusEqID)){
						tempSH.add(BusrdfID);
					}
				}
				System.out.println( "\n"+ "Shunt, Bus: "+ tempSH);
				// Per-unit values
				Zbase = Math.pow(newShunt.getNomVolt(), 2)/Sbase;
				puB= newB*Zbase; // B per section
				puG= newG*Zbase; // G per section
				puB= puB*4; // shunt with 4 sections
				puG= puG*4; // shunt with 4 sections
				tempSH.add(Double.toString(puG));
				tempSH.add(Double.toString(puB));
				
		///////////////////////////////////////////////////////
		// Adding to YBUS matrix ******************************
		for (int i=0; i<parsingObject.getBusBarList().size(); i++) {
			if(tempSH.get(1).equals(Bus[i])){
				Complex Y1= new Complex(Double.parseDouble(tempSH.get(2)),Double.parseDouble(tempSH.get(3)));
				Ybus[i][i]=Y1.plus(Ybus[i][i]);
				for(int j=0; j<parsingObject.getBusBarList().size(); j++){
					if(tempSH.get(2).equals(Bus[j])){
						Complex Y2= new Complex(Double.parseDouble(tempSH.get(2)),Double.parseDouble(tempSH.get(3)));
						Ybus[j][j]=Y2.plus(Ybus[j][j]);
					}
				}
			}
		}		
	}
			
////////////////********* YBUS MATRIX with Line , Transformer and Shunt Compensator *********//////////////////
			System.out.println("\n"+"YBUS with Line, Transformer and Shunt Compensator: ");
			for (int i=0; i<parsingObject.getBusBarList().size();i++){
				for(int j=0; j<parsingObject.getBusBarList().size(); j++){
					System.out.print(Ybus[i][j]+"\t");	
				}
				System.out.println("\t");
			}
		return Ybus;
}

		public double getZbase(){
			return Zbase;
		}
		public Complex[][] getY(){
			return Ybus;
		}
		
}

