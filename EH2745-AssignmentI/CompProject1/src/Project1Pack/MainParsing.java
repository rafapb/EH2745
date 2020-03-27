package Project1Pack;

//Main class for extracting all the required data for importing into SQL database
public class MainParsing {
	//Main method for importing into SQL database
	public static void intoSQL(String EQ, String SSH, String USERN, String PASSW) {
		
		//Creating a new ParseObject by use of pre-defined ParsingXML class and parsingMethod
		ParsingXML parsingObject = new ParsingXML();
		parsingObject.parsingMethod(EQ, SSH);
		
		//Creating a new Object by use of pre-defined SQLdataBase class and corresponding methods
		SQLdataBase mySQL = new SQLdataBase(USERN, PASSW);
		mySQL.StartUp(); // starting up the connection with SQL server and create the desired database
		mySQL.createTables(); // creating the tables
		
		//Creating a new Object by use of BaseVoltClass and getting BaseVoltageList including all required data
		//Printing out the BaseVoltageList for the user
		//Inserting data into BaseVoltage table
		System.out.println("*** Base Voltage ***");
		for (BaseVoltClass newBaseV : parsingObject.getBaseVoltList()) {
			String BaseVrdfID = newBaseV.getrdfID();
			double BaseNom = newBaseV.getNomValue();
			mySQL.BaseVoltageTab(BaseVrdfID, BaseNom);
			System.out.println("rdfID: " + BaseVrdfID +"\n"+ "Nominal Value: " + BaseNom);
			}
		//Creating a new Object by use of SubstationClass and getting SubstationList including all required data
		//Printing out the SubstationList for the user
		//Inserting data into Substation table
		System.out.println("*** Substation ***");
		for (SubstationClass newSubstation : parsingObject.getSubList()) {
				String subrdfID = newSubstation.getrdfID();
				String subName = newSubstation.getName();
				String subRegionrdfID = newSubstation.getRegionID();
				mySQL.SubstationTab(subrdfID, subName, subRegionrdfID);
				System.out.println("rdfID: " + subrdfID +"\n"+ "Name: " + subName +"\n"+
				"region_ID: " + subRegionrdfID);
				}
		
		/////// Repeating the same procedure for all tables
		
		System.out.println("*** Voltage Level ***");
		for (VoltLevelClass newVoltLevel : parsingObject.getVoltList()) {
			String VoltrdfID = newVoltLevel.getrdfID();
			String VoltName = newVoltLevel.getName();
			String subrdfID = newVoltLevel.getSubrdfID();
			String baseVoltrdfID = newVoltLevel.getBaseVrdfID();
			mySQL.VoltageLevelTab(VoltrdfID, VoltName, subrdfID, baseVoltrdfID);
			System.out.println("rdfID: " + VoltrdfID +"\n"+ "Name: " + VoltName +"\n"+ "substation_ID: "
			+ subrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
			}
		//
		System.out.println("*** Generating Unit ***");
		for (GenUnitClass newGenUnit : parsingObject.getGenUnitList()) {
			String GenrdfID = newGenUnit.getrdfID();
			String GenName = newGenUnit.getName();
			double GenMaxP = newGenUnit.getMaxP();
			double GenMinP = newGenUnit.getMinP();
			String GenEqConID = newGenUnit.getEqContID();
			mySQL.GeneratingUnitTab(GenrdfID, GenName, GenMaxP, GenMinP, GenEqConID);
			System.out.println("rdfID: " + GenrdfID +"\n"+ "Name: " + GenName +"\n"+
			"Maximum Operating Power: " + GenMaxP +"\n"+ "Minimum Operating Power: " + GenMinP +"\n"
			+ "Equipment Container ID: " + GenEqConID);
			}
		//
		System.out.println("*** Synchronous Machine ***");
		for (SynchMachineClass newSynchMach : parsingObject.getSynchMachList()) {
			String SyncrdfID = newSynchMach.getrdfID();
			String SyncName = newSynchMach.getName();
			double SyncRatedS = newSynchMach.getRatedS();
			double SyncP = newSynchMach.getActiveP();
			double SyncQ = newSynchMach.getReactiveP();
			String SyncGenUnitID = newSynchMach.getGenUnitID();
			String SyncRegCtrID = newSynchMach.getRegContID();
			String SyncEqConID = newSynchMach.getEqContID();
			mySQL.SynchMachineTab(SyncrdfID, SyncName, SyncRatedS, SyncP, SyncQ, SyncGenUnitID, SyncRegCtrID, SyncEqConID);
			System.out.println("rdfID: " + SyncrdfID +"\n"+ "Name: " + SyncName +"\n"+
					"rated S: " + SyncRatedS +"\n"+ "Active Power: " + SyncP +"\n"+ "Reactive Power: " + SyncQ
					+"\n"+ "Generating Unit ID: " + SyncGenUnitID +"\n"+ "Regulating Control ID: " + SyncRegCtrID
					+"\n"+ "Equipment Container ID: " + SyncEqConID);
			}
		//
		System.out.println("*** Regulating Control ***");
		for (RegControlClass newRegCtrl : parsingObject.getRegCtrList()) {
			String RegrdfID = newRegCtrl.getrdfID();
			String RegName = newRegCtrl.getName();
			double TargetValue = newRegCtrl.getTargetValue();
			mySQL.RegControlTab(RegrdfID, RegName, TargetValue);
			System.out.println("rdfID: " + RegrdfID +"\n"+ "Name: " + RegName +"\n"+
			"Target Value: " +TargetValue);
			}
		//
		System.out.println("*** Power Transformer ***");
		for (PowerTransClass newPowTr : parsingObject.getPowTrList()) {
			String TransrdfID = newPowTr.getrdfID();
			String TransName = newPowTr.getName();
			String TransEqConID = newPowTr.getEqContID();
			mySQL.PowerTransformerTab(TransrdfID, TransName, TransEqConID);
			System.out.println("rdfID: " + TransrdfID +"\n"+ "Name: " + TransName +"\n"+
			"Equipment Container ID: " +TransEqConID);
			}
		//
		System.out.println("*** Energy Consumer ***");
		for (LoadClass newload : parsingObject.getLoadList()) {
			String LoadrdfID = newload.getrdfID();
			String LoadName = newload.getName();
			double LoadP = newload.getLoadActiveP();
			double LoadQ = newload.getLoadReactiveP();
			String LoadEqConID = newload.getEqContID();
			mySQL.EnergyConsumerTab(LoadrdfID, LoadName, LoadP, LoadQ, LoadEqConID);
			System.out.println("rdfID: " + LoadrdfID +"\n"+ "Name: " + LoadName +"\n"+
			"Active Power: " +LoadP +"\n"+ "Reactive Power: " + LoadQ +"\n"+
			"Equipment Container ID: " +LoadEqConID);
			}
		//
		System.out.println("*** Power Transformer End (Winding) ***");
		for (PowTrEndClass newTrWind : parsingObject.getTrWindList()) {
			String TrWindrdfID = newTrWind.getrdfID();
			String TrWindName = newTrWind.getName();
			double TrWindRvalue = newTrWind.getTransRvalue();
			double TrWindXvalue = newTrWind.getTransXvalue();
			String PowTransrdfID = newTrWind.getTransrdfID();
			String baseVoltrdfID = newTrWind.getBaseVoltrdfID();
			mySQL.TransformerWindingTab(TrWindrdfID, TrWindName, TrWindRvalue, TrWindXvalue, PowTransrdfID, baseVoltrdfID);
			System.out.println("rdfID: " + TrWindrdfID +"\n"+ "Name: " + TrWindName +"\n"+ "Resistance Value: "
			+ TrWindRvalue +"\n"+ "Reactance Value: " + TrWindXvalue +"\n"
			+ "Transformer_ID: " + PowTransrdfID +"\n"+ "BaseVoltage_ID: " + baseVoltrdfID);
			}
		//
		System.out.println("*** Breaker ***");
		for (BreakerClass newBreaker : parsingObject.getBreakerList()) {
			String BRrdfID = newBreaker.getrdfID();
			String BRName = newBreaker.getName();
			boolean BRState = newBreaker.getBRstate();
			String BREqConID = newBreaker.getEqContID();
			mySQL.BreakerTab(BRrdfID, BRName, BRState, BREqConID);
			System.out.println("rdfID: " + BRrdfID +"\n"+ "Name: " + BRName +"\n"+
			"State: " +BRState +"\n"+ "Equipment Container ID: " +BREqConID);
			}
		//
		System.out.println("*** Ratio Tap Changer ***");
		for (TapChangerClass newTapCh : parsingObject.getTapChngList()) {
			String TaprdfID = newTapCh.getrdfID();
			String TapName = newTapCh.getName();
			double TapStep = newTapCh.getStep();
			mySQL.TapChangerTab(TaprdfID, TapName, TapStep);
			System.out.println("rdfID: " + TaprdfID +"\n"+ "Name: " + TapName +"\n"+
			"Target Value: " +TapStep);
			}
		// Creating SQL database is DONE!
	
  // Extracting extra data for building YBUS matrix
		//
		System.out.println("*** Terminal ***");
		for (TerminalClass newTerminal : parsingObject.getTerminalList()) {
			String TermrdfID = newTerminal.getrdfID();
			String TermName = newTerminal.getName();
			String TermCondEqID = newTerminal.getConductingEqID();
			String TermConnectNodID = newTerminal.getConnectNodeID();
			System.out.println("rdfID: " + TermrdfID +"\n"+ "Name: " + TermName +"\n"+
			"Conducting Equipment ID: " +TermCondEqID +"\n"+ "Connectivity Node ID: " +TermConnectNodID);
			}
		//
		System.out.println("*** Connectivity Node ***");
		for (ConnectivityNodeClass newConNode : parsingObject.getConNodeList()) {
			String CNoderdfID = newConNode.getrdfID();
			String CNodeName = newConNode.getName();
			String CNodeContID = newConNode.getCNodeContainerID();
			System.out.println("rdfID: " + CNoderdfID +"\n"+ "Name: " + CNodeName +"\n"+
			"ConnectivityNode Container ID: " +CNodeContID);
			}
		//
		System.out.println("*** AC Line ***");
		for (ACLineClass newline : parsingObject.getACLineList()) {
			String LinerdfID = newline.getrdfID();
			String LineName = newline.getName();
			double LineR = newline.getR();
			double LineX = newline.getX();
			double LineBch = newline.getBch();
			String LineBaseVoltID = newline.getBaseVoltID();
			String LineEqContID = newline.getEqContID();
			System.out.println("rdfID: " + LinerdfID +"\n"+ "Name: " + LineName +"\n"+
					"Line Resistance: " +LineR +"\n"+ "Line Reactance: " + LineX +"\n"+ "Line Suseptance: " 
					+ LineBch +"\n"+ "BaseVoltage ID: " + LineBaseVoltID +"\n"+ "Equipment Container ID: " +LineEqContID);
			}
		//
		System.out.println("*** BusBar Section ***");
		for (BusBarClass newbus : parsingObject.getBusBarList()) {
			String BusrdfID = newbus.getrdfID();
			String BusName = newbus.getName();
			String BusEqConID = newbus.getEqContID();
			System.out.println("rdfID: " + BusrdfID +"\n"+ "Name: " + BusName +"\n"+
			"Equipment Container ID: " +BusEqConID);
			}
		//
		System.out.println("*** Linear Shunt Compensator ***");
		for (ShuntClass newshunt : parsingObject.getShuntList()) {
			String SHrdfID = newshunt.getrdfID();
			String SHName = newshunt.getName();
			double SHg = newshunt.getGvalue();
			double SHb = newshunt.getBvalue();
			String SHEqContID = newshunt.getEqContID();
			System.out.println("rdfID: " + SHrdfID +"\n"+ "Name: " + SHName +"\n"+
					"Shunt G: " +SHg +"\n"+ "Shunt B: " + SHb +"\n" 
					+ "Equipment Container ID: " +SHEqContID);
			}
		
		}
}


