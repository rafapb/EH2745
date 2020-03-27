package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PowTrEndClass extends IDobject {
	
	private double TransRvalue, TransXvalue, TransBvalue, TransGvalue;
	private String TransrdfID,baseVoltrdfID;
	//
	public Element extractNode (Node node){	
		Element element = super.extractNode(node);
		this.TransRvalue = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent());
		this.TransXvalue = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent());
		this.TransBvalue = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.b").item(0).getTextContent());
		this.TransGvalue = Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.g").item(0).getTextContent());
		this.TransrdfID = element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		this.baseVoltrdfID = element.getElementsByTagName("cim:TransformerEnd.BaseVoltage").item(0).getAttributes().item(0).getTextContent().replaceAll("#","");
		return element;
		}
	//
	public double getTransRvalue() {
	    return TransRvalue;
	}
	public double getTransXvalue() {
	    return TransXvalue;
	}
	public double getTransBvalue() {
		return TransBvalue;
		}	
	public double getTransGvalue() {
		return TransGvalue;
		}
	public String getTransrdfID() {
	    return TransrdfID;
	}
	public String getBaseVoltrdfID() {
	    return baseVoltrdfID;
	}
}
