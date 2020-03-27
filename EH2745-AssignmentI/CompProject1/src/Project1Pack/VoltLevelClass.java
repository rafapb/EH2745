package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

  public class VoltLevelClass extends IDobject{
	
	private String subrdfID;
	private String baseVrdfID;
	//	
    public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.subrdfID = element.getElementsByTagName("cim:VoltageLevel.Substation").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		this.baseVrdfID = element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
	}
    //
    public String getSubrdfID(){
	return subrdfID;
	}

  public String getBaseVrdfID() {
	return baseVrdfID;
	}
}

