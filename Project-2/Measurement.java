package Project2Package;

public class Measurement {
	String rdfid;
	String name;
	double time;
	double value;
	String sub_rdfid;

	
		public Measurement(String rdfid, String name, double time, double value,String sub_rdfid) {
			this.rdfid=rdfid;
			this.name=name;
			this.time=time;
			this.value=value;
			this.sub_rdfid=sub_rdfid;
		}
	public String getRdfid() {
		  return rdfid;
		}
	public String getname() {
		  return name;
		}
	public String getSubrdfid() {
		  return sub_rdfid;
		}
	public Double getTime() {
		  return time;
		}
	public Double getValue() {
		  return value;
		}
	
}


