package Project2Package;
import java.util.Arrays;
import java.util.Comparator;

public class Classification {
	
	//////////// Sorting the Array ////////////////
	public double[][] Sort(double[][] theArray){   

	    Arrays.sort(theArray, new Comparator<double[]>(){
	        @Override
	        public int compare(double[] int1, double[] int2){
	            Double numOfKeys1 = int1[0];
	            Double numOfKeys2 = int2[0];
	            return numOfKeys1.compareTo(numOfKeys2);
	        }
	    });
	    //System.out.println(theArray[0][1]);
//	    for(int p = 0, q = 200 ; p < q; p++)
//	    {
//	        System.out.println(theArray[p][0] + " " + theArray[p][1]);
//	    }
		return theArray;    
	}

	
	//////////// Merging distances from 4 cluster in one main array with all the distance points //////////
	double[][] DistArray = new double[200][2];
	public double[][] mergeArray(double[][] distance1, double[][] distance2, double[][] distance3, double[][] distance4,
			                     int coun1, int coun2, int coun3, int coun4){
		
		//System.out.println("Distance Array: ");
		for(int i=0; i<coun1; i++){
				for(int k=0; k<2; k++){
					DistArray[i][k]=distance1[k][i];
				}
				//System.out.print(DistArray[i][0]+"\t");
				//System.out.println(DistArray[i][1]+"\t");
			}
		for(int i=coun1; i<coun1+coun2; i++){
				for(int k=0; k<2; k++){
					DistArray[i][k]=distance2[k][i-coun1];
				}
				//System.out.print(DistArray[i][0]+"\t");
				//System.out.println(DistArray[i][1]+"\t");
		}
		for(int i=coun1+coun2; i<coun1+coun2+coun3; i++){
			for(int k=0; k<2; k++){
				DistArray[i][k]=distance3[k][i-coun1-coun2];
			}
			//System.out.print(DistArray[i][0]+"\t");
			//System.out.println(DistArray[i][1]+"\t");
	    }
		for(int i=coun1+coun2+coun3; i<200; i++){
			for(int k=0; k<2; k++){
				DistArray[i][k]=distance4[k][i-coun1-coun2-coun3];
			}
			//System.out.print(DistArray[i][0]+"\t");
			//System.out.println(DistArray[i][1]+"\t");
	    }
		
		
		
		
		return DistArray;
		
	}
	
	
	
	////////////////// Calculating Distance between each Analog point and each point of each Cluster /////////////
	double[][] distance1 = new double[2][200];
	double[][] distance2 = new double[2][200];
	double[][] distance3 = new double[2][200];
	double[][] distance4 = new double[2][200];
	
	public void distanceCalc(double[][] C1, double[][] C2, double[][] C3, double[][] C4, 
			                 int coun1, int coun2, int coun3, int coun4, double[][] NORManalog, int iter){
		
		for(int i=0; i<coun1; i++){
			distance1[1][i]=1;
		}
		for(int i=0; i<coun2; i++){
			distance2[1][i]=2;
		}
		for(int i=0; i<coun3; i++){
			distance3[1][i]=3;
		}
		for(int i=0; i<coun4; i++){
			distance4[1][i]=4;
		}
				
	////// Calculate Euclidean Distance for Cluster 1 ////////
		//System.out.print("distance 1 \n");
				for(int i=0; i<coun1; i++){
					double distance=0;
						double sum=0;
						for(int k=0; k<18; k++){
					        sum = sum + Math.pow((C1[i][k] - NORManalog[iter][k]),2); 
						}
						distance = Math.sqrt(sum);
						distance1[0][i]=distance;
					//System.out.print(distance1[0][i]+"\t");
					//System.out.println(distance1[1][i]+"\t");	
				}
			////// Calculate Euclidean Distance for Cluster 2 ////////
				//System.out.print("distance 2 \n");
						for(int i=0; i<coun2; i++){
							double distance=0;
								double sum=0;
								for(int k=0; k<18; k++){
							        sum = sum + Math.pow((C2[i][k] - NORManalog[iter][k]),2); 
								}
								distance = Math.sqrt(sum);
								distance2[0][i]=distance;
							//System.out.print(distance2[0][i]+"\t");
							//System.out.println(distance2[1][i]+"\t");	
						}
					////// Calculate Euclidean Distance for Cluster 1 ////////
						//System.out.print("distance 3 \n");
						for(int i=0; i<coun3; i++){
							double distance=0;
								double sum=0;
								for(int k=0; k<18; k++){
							        sum = sum + Math.pow((C3[i][k] - NORManalog[iter][k]),2); 
								}
								distance = Math.sqrt(sum);
								distance3[0][i]=distance;
							//System.out.print(distance3[0][i]+"\t");
							//System.out.println(distance3[1][i]+"\t");	
						}
					////// Calculate Euclidean Distance for Cluster 4 ////////
						//System.out.print("distance 4 \n");
						for(int i=0; i<coun4; i++){
							double distance=0;
								double sum=0;
								for(int k=0; k<18; k++){
							        sum = sum + Math.pow((C4[i][k] - NORManalog[iter][k]),2); 
								}
								distance = Math.sqrt(sum);
								distance4[0][i]=distance;
							//System.out.print(distance4[0][i]+"\t");
							//System.out.println(distance4[1][i]+"\t");	
						}
						
											
				
	}// End Method
	
	public double[][] getDistance1(){
		return distance1;
	}
	public double[][] getDistance2(){
		return distance2;
	}
	public double[][] getDistance3(){
		return distance3;
	}
	public double[][] getDistance4(){
		return distance4;
	}
	
}// End Class

		

