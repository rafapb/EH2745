package Project1Pack;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
//
public class IDobject {

	private String rdfID;
	private String name;
		//
	    public Element extractNode (Node node){
			Element element = (Element) node;
			this.rdfID = element.getAttribute("rdf:ID");
			this.name = element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent();	
			return element;
		}
		//
		public String getrdfID() {
			return rdfID;
		}
		public String getName() {
			return name;
		}
	}


