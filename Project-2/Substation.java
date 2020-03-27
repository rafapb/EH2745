package Project2Package;

public class Substation {
	String rdfid;
	String name;
	String region_id;
	
	public Substation (String rdfid, String name, String region_id) {
		this.rdfid=rdfid;
		this.name=name;
		this.region_id=region_id;
		
	}
	public String getRdfid() {
		  return rdfid;
		}
	public String getname() {
		  return name;
		}
	public String getRegionid() {
		  return region_id;
		}
	
}
