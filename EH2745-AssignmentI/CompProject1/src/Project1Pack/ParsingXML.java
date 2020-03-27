package Project1Pack;

// Importing the required libraries for parsing data from XML files
import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//The PaesingXML class is created for extracting all the required data through defined method
public class ParsingXML {
	
	//Creating array list for each CIM object according to individual classes defined per object
	private List <BaseVoltClass> BaseVoltageList = new ArrayList<BaseVoltClass>();
	private List <SubstationClass> SubstationList = new ArrayList<SubstationClass>();
	private List <VoltLevelClass> VoltLevelList = new ArrayList<VoltLevelClass>();
	private List <GenUnitClass> GenUnitList = new ArrayList<GenUnitClass>();
	private List <SynchMachineClass> SynchMachineList = new ArrayList<SynchMachineClass>();
	private List <RegControlClass> RegCtrList = new ArrayList<RegControlClass>();
	private List <PowerTransClass> PowerTrList = new ArrayList<PowerTransClass>();
	private List <LoadClass> LoadList = new ArrayList<LoadClass>();
	private List <PowTrEndClass> TransWindList = new ArrayList<PowTrEndClass>();
	private List <BreakerClass> BreakerList = new ArrayList<BreakerClass>();
	private List <TapChangerClass> TapChangerList = new ArrayList<TapChangerClass>();
	private List <TerminalClass> TerminalList = new ArrayList<TerminalClass>();
	private List <ConnectivityNodeClass> ConnectNodeList = new ArrayList<ConnectivityNodeClass>();
	private List <ACLineClass> ACLineList = new ArrayList<ACLineClass>();
	private List <BusBarClass> BusBarList = new ArrayList<BusBarClass>();
	private List <ShuntClass> ShuntList = new ArrayList<ShuntClass>();
	
	// The general method for parsing the CIM data and return them as a array list for each CIM object
	public void parsingMethod(String EQ, String SSH) {
		
		try {
			//The files we are going to parse now named XMLFileEQ/SSH
			File XmlFileEQ = new File(EQ);
			File XmlFileSSH = new File(SSH);
					
			//We create the Document object and parse them
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document docEQ = dBuilder.parse(XmlFileEQ);
			Document docSSH = dBuilder.parse(XmlFileSSH);
					 
			// Return root element of the file and normalize XML files
			docEQ.getDocumentElement().normalize();
			docSSH.getDocumentElement().normalize();
					 
			// The next step is to extract information 
					 
			// Extract required data from the EQ file
			NodeList baseVoltList = docEQ.getElementsByTagName("cim:BaseVoltage");
			NodeList subList = docEQ.getElementsByTagName("cim:Substation");
			NodeList voltList = docEQ.getElementsByTagName("cim:VoltageLevel");
			NodeList genList = docEQ.getElementsByTagName("cim:GeneratingUnit");
			NodeList syncList = docEQ.getElementsByTagName("cim:SynchronousMachine");
			NodeList regList = docEQ.getElementsByTagName("cim:RegulatingControl");
			NodeList powTrList = docEQ.getElementsByTagName("cim:PowerTransformer");
			NodeList energyConList = docEQ.getElementsByTagName("cim:EnergyConsumer");
			NodeList powTrEndList = docEQ.getElementsByTagName("cim:PowerTransformerEnd");
			NodeList breakList = docEQ.getElementsByTagName("cim:Breaker");
			NodeList tapList = docEQ.getElementsByTagName("cim:RatioTapChanger");
			 
			// EQ for the YBUS
			NodeList terminalList = docEQ.getElementsByTagName("cim:Terminal");
			NodeList CNodeList = docEQ.getElementsByTagName("cim:ConnectivityNode");
			NodeList LineList = docEQ.getElementsByTagName("cim:ACLineSegment");
			NodeList busList = docEQ.getElementsByTagName("cim:BusbarSection");
			NodeList shuntList = docEQ.getElementsByTagName("cim:LinearShuntCompensator");
				 
			// Extract required data from the SSH file
			NodeList syncListSSH = docSSH.getElementsByTagName("cim:SynchronousMachine");
			NodeList regListSSH = docSSH.getElementsByTagName("cim:RegulatingControl");
			NodeList energyConListSSH = docSSH.getElementsByTagName("cim:EnergyConsumer");
			NodeList breakListSSH = docSSH.getElementsByTagName("cim:Breaker");
			NodeList tapListSSH = docSSH.getElementsByTagName("cim:RatioTapChanger");
			
			     
			//Base Voltage List
			for (int i = 0; i<baseVoltList.getLength(); i++) {
				BaseVoltClass baseV = new BaseVoltClass();
				baseV.extractNode(baseVoltList.item(i));
				BaseVoltageList.add(baseV);
				}
			// Substation List
	        for (int i = 0; i<subList.getLength(); i++) {							
				SubstationClass substation = new SubstationClass();
				substation.extractNode(subList.item(i));
				SubstationList.add(substation);							
				}
	        //Voltage Level List
			for (int i = 0; i<voltList.getLength(); i++) {
				VoltLevelClass voltLevel = new VoltLevelClass();
				voltLevel.extractNode(voltList.item(i));
				VoltLevelList.add(voltLevel);
				}
			//Generating Unit List
			for (int i = 0; i<genList.getLength(); i++) {
				GenUnitClass genUnit = new GenUnitClass();
				genUnit.extractNode(genList.item(i));
				GenUnitList.add(genUnit);
				}
			//Synchronous Machine List
			for (int i = 0; i<syncList.getLength(); i++) {
				SynchMachineClass synchMach = new SynchMachineClass();
				synchMach.extractNode(syncList.item(i));
				synchMach.extractNodeSSH(syncListSSH.item(i));
				SynchMachineList.add(synchMach);
				}
			//Regulating Control List
			for (int i = 0; i<regList.getLength(); i++) {
				RegControlClass regCtrl = new RegControlClass();
				regCtrl.extractNode(regList.item(i));
				regCtrl.extractNodeSSH(regListSSH.item(i));
				RegCtrList.add(regCtrl);	
				}
			//Power Transformer List
			for (int i = 0; i<powTrList.getLength(); i++) {
				PowerTransClass PowTrans = new PowerTransClass();
				PowTrans.extractNode(powTrList.item(i));
				PowerTrList.add(PowTrans);
				}
			//Energy Consumer List
			for (int i = 0; i<energyConList.getLength(); i++) {
				LoadClass load = new LoadClass();
				load.extractNode(energyConList.item(i));
				load.extractNodeSSH(energyConListSSH.item(i));
				LoadList.add(load);
				}
		    //Power Transformer End (Winding) List
			for (int i = 0; i<powTrEndList.getLength(); i++) {
				PowTrEndClass transEnd = new PowTrEndClass();
				transEnd.extractNode(powTrEndList.item(i));
				TransWindList.add(transEnd);
				}
			//Breaker List
			for (int i = 0; i<breakList.getLength(); i++) {
				BreakerClass breaker = new BreakerClass();
				breaker.extractNode(breakList.item(i));
				breaker.extractNodeSSH(breakListSSH.item(i));
				BreakerList.add(breaker);
				}
			//Ratio Tap Changer List
			for (int i = 0; i<tapList.getLength(); i++) {
				TapChangerClass tapChng = new TapChangerClass();
				tapChng.extractNode(tapList.item(i));
				tapChng.extractNodeSSH(tapListSSH.item(i));
				TapChangerList.add(tapChng);
			}
			
			//// For YBUS matrix			     
				  //Terminal List
					for (int i = 0; i<terminalList.getLength(); i++) {
						TerminalClass terminal = new TerminalClass();
						terminal.extractNode(terminalList.item(i));
						TerminalList.add(terminal);
						}
				  //Connectivity Node List
					for (int i = 0; i<CNodeList.getLength(); i++) {
						ConnectivityNodeClass ConNode = new ConnectivityNodeClass();
						ConNode.extractNode(CNodeList.item(i));
						ConnectNodeList.add(ConNode);
						}
				  //AC Line List
					for (int i = 0; i<LineList.getLength(); i++) {
						ACLineClass line = new ACLineClass();
						line.extractNode(LineList.item(i));
						ACLineList.add(line);
						}
				  //BusBar List
					for (int i = 0; i<busList.getLength(); i++) {
						BusBarClass bus = new BusBarClass();
						bus.extractNode(busList.item(i));
						BusBarList.add(bus);
						}
				  //Linear Shunt Compensator List
					for (int i = 0; i<shuntList.getLength(); i++) {
						ShuntClass shunt = new ShuntClass();
						shunt.extractNode(shuntList.item(i));
						ShuntList.add(shunt);
						}
																
		}
		catch(Exception e){
			e.printStackTrace();
			}
		}
	
	//Returning the list of array for each CIM objects
	public List <BaseVoltClass> getBaseVoltList(){
		return BaseVoltageList;
	}
	public List <SubstationClass> getSubList(){
		return SubstationList;
	}
	public List <VoltLevelClass> getVoltList(){
		return VoltLevelList;
	}
	public List <GenUnitClass> getGenUnitList(){
		return GenUnitList;
	}
	public List <SynchMachineClass> getSynchMachList(){
		return SynchMachineList;
	}
	public List <RegControlClass> getRegCtrList(){
		return RegCtrList;
	}
	public List <PowerTransClass> getPowTrList(){
		return PowerTrList;
	}
	public List <LoadClass> getLoadList(){
		return LoadList;
	}
	public List <PowTrEndClass> getTrWindList(){
		return TransWindList;
	}
	public List <BreakerClass> getBreakerList(){
		return BreakerList;
	}
	public List <TapChangerClass> getTapChngList(){
		return TapChangerList;
	}
	public List <TerminalClass> getTerminalList(){
		return TerminalList;
	}
	public List <ConnectivityNodeClass> getConNodeList(){
		return ConnectNodeList;
	}
	public List <ACLineClass> getACLineList(){
		return ACLineList;
	}
	public List <BusBarClass> getBusBarList(){
		return BusBarList;
	}
	public List <ShuntClass> getShuntList(){
		return ShuntList;
	}
}

