package Project2Package;

import java.sql.*;

public class PointbyPoint {
	//import javax.sql.DataSource;

				// JDBC driver name and database URL
				private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
				private static final String DB_URL = "jdbc:mysql://localhost/";
				private static final String DISABLE_SSL = "?useSSL=false";
				//SSL stands for Security Sockets Layer. It says it is not secure to start without disabling it

				// Database credentials
				private static final String USER = "root";
				private static final String PASS = "root";
		
				
		///////////////////////////////////////////////////////////////////////////		
		///////////////////// Getting Normalized Values ///////////////////////////
		///////////////////////////////////////////////////////////////////////////
				
		double[][] NORMmeasurlist= new double [200][18];
		public double[][] NormMeasurement(double[][] measurlist){
			
	    //////// Normalization ////////////
			// Maximum/Minimum Voltage Value
			for(int k=0; k<200; k++){
				double maxVol=measurlist[k][0];
				double minVol=measurlist[k][0];
				for (int i=0; i<18; i+=2) {
					if (measurlist[k][i] > maxVol) {
			    	maxVol = measurlist[k][i];
			    }
					if (measurlist[k][i] < minVol) {
			    	minVol = measurlist[k][i];
			    }
			}
			for (int i=0; i<18; i+=2) {
				NORMmeasurlist[k][i]=(measurlist[k][i]-minVol)/(maxVol-minVol);
			}
			}
			// Maximum/Minimum Angle Value
			for(int k=0; k<200; k++){
			double maxAng=measurlist[k][1];	
			double minAng=measurlist[k][1];
			for (int i=1; i<18; i+=2) {
			    if (measurlist[k][i] > maxAng) {
			    	maxAng = measurlist[k][i];
			    }
			    if (measurlist[k][i] < minAng) {
			    	minAng = measurlist[k][i];
			    }
			}
			for (int i=1; i<18; i+=2) {
				NORMmeasurlist[k][i]=(measurlist[k][i]-minAng)/(maxAng-minAng);
				}
			}
			return NORMmeasurlist;			
		}
		
		////////////////////////////////////////////////////////		
		//////// Normalization of the Analog values ////////////
		////////////////////////////////////////////////////////
		
		double[][] NORManalogVlist= new double [20][18];
				
		public double[][] NormAnalogValues(double[][] analogVlist){
			
			    // Maximum/Minimum Voltage Value
				for(int k=0; k<20; k++){ 
				double maxVol=analogVlist[k][0];
				double minVol=analogVlist[k][0];
				for (int i=0; i<18; i+=2) {
				    if (analogVlist[k][i] > maxVol) {
				    	maxVol = analogVlist[k][i];
				    }					
				    if (analogVlist[k][i] < minVol) {
				    	minVol = analogVlist[k][i];
				    }
				}
				for (int i=0; i<18; i+=2) {
				NORManalogVlist[k][i]=(analogVlist[k][i]-minVol)/(maxVol-minVol);
				}
				}
				// Maximum/Minimum Angle Value
				for(int k=0; k<20; k++){
				double maxAng=analogVlist[k][1];	
				double minAng=analogVlist[k][1];
				for (int i=1; i<18; i+=2) {
				    if (analogVlist[k][i] > maxAng) {
				    	maxAng = analogVlist[k][i];
				    }
				    if (analogVlist[k][i] < minAng) {
				    	minAng = analogVlist[k][i];
				    }
				}
				for (int i=1; i<18; i+=2) {
					NORManalogVlist[k][i]=(analogVlist[k][i]-minAng)/(maxAng-minAng);
					}
				}
				return NORManalogVlist;		
		}

		
		////////////////////////////////////////
	    ////// Getting Measurement List ///////	
		///////////////////////////////////////
		
				Connection conn = null;
			    Statement stmt = null;
			    String sql = null;
    
		double[] pointlist= new double [3600];
		double[][] measurlist= new double [200][18];
		
		public double[][] pointOfMeasurement() {

		    try{
			      // Register JDBC driver
			      Class.forName(JDBC_DRIVER);
			      
			      // Open a connection
			      System.out.println("Connecting to SQL server...");
			      conn=DriverManager.getConnection(DB_URL + DISABLE_SSL, USER, PASS);

				  // execute a query to create database. It should have the same name as in the MySQL
				  System.out.println("\n"+"Creating database...");
				  stmt = conn.createStatement();
				  conn = DriverManager.getConnection(DB_URL + "system9Buses" + DISABLE_SSL, USER, PASS);
				  sql = "USE system9Buses";
				  stmt.executeUpdate(sql);
				  
				 ////////////////////////////////////////////////////// 
				//////Import Measurement Table from the Database///////
				///////////////////////////////////////////////////////  

				  sql = "SELECT * FROM measurements";
				  ResultSet rs = stmt.executeQuery(sql); // execute query
				  
				  int count=0;
					// Insert values into an ArrayList
					while (rs.next()) {	 //false if there are no more rows to take into account
						Measurement meas=new Measurement(rs.getString("rdfid"), rs.getString("name"),
								         rs.getDouble("time"),rs.getDouble("value"),rs.getString("sub_rdfid"));
						pointlist[count]=meas.getValue();
						count++;
						}
					for(int i=0;i<200;i++){
						for(int j=0;j<18 ;j++){
							measurlist[i][j]=pointlist[i*18+j];
						}
					}
				
					System.out.println("Database is working");
				
		
		    }catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
			return measurlist;
		} //Finish Method
		
		
		//////////////////////////////////////////////////
		/// Importing Analog Values /////////////////////
		////////////////////////////////////////////////
		
		double[][] analogVlist= new double [20][18];
		double[] analogVals= new double [360];
		
		public double[][] pointOfAnalog() {

		    try{
			      // Register JDBC driver
			      Class.forName(JDBC_DRIVER);
			      
			      // Open a connection
			      conn=DriverManager.getConnection(DB_URL + DISABLE_SSL, USER, PASS);

				  // execute a query to create database. It should have the same name as in the MySQL
				  stmt = conn.createStatement();
				  conn = DriverManager.getConnection(DB_URL + "system9Buses" + DISABLE_SSL, USER, PASS);
				  sql = "USE system9Buses";
				  stmt.executeUpdate(sql);
				  
				
            ////////////////////////////////////////////////////////
            //////// Import Analog_Values Table ////////////////////
           ////////////////////////////////////////////////////////
           
				  sql = "SELECT * FROM analog_values";
				  ResultSet rs = stmt.executeQuery(sql); // execute query

				  int countValues=0;
				  // Insert values into an ArrayList
				  while (rs.next()) {	 //false if there are no more rows to take into account
					  AnalogValues AnValues=new AnalogValues(rs.getString("rdfid"), rs.getString("name"),
							  rs.getDouble("time"),rs.getDouble("value"),rs.getString("sub_rdfid"));
					  analogVals[countValues]=AnValues.getValue();
					  countValues++;
				  }

				  for(int i=0;i<20;i++){
					  for(int j=0;j<18 ;j++){
						  analogVlist[i][j]=analogVals[i*18+j];
					  }
				  }		
		    }catch (SQLException se) {
		    	// Handle errors for JDBC
		    	se.printStackTrace();
		    } catch (Exception e) {
		    	// Handle errors for Class.forName
		    	e.printStackTrace();
		    }
		    return analogVlist;
		}

}//Finish Class

