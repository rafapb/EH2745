package Project2Package;

public class Kmeans {

	
	//////////////////////////////////////////////////////////////////////////////////////
	/////// Method for De-Normalizing the clusters to Voltage and Angle with real values //////
	/////////////////////////////////////////////////////////////////////////////////////

	// Average value of voltage and angle at each bus for each cluster
	double[] meanVoltAng1= new double[18];
	double[] meanVoltAng2= new double[18];
	double[] meanVoltAng3= new double[18];
	double[] meanVoltAng4= new double[18];
	
	public void DeNorm(double[][] FinalC1, double[][] FinalC2, double[][] FinalC3, double[][] FinalC4,
			           int counter1, int counter2, int counter3, int counter4){

		PointbyPoint MeasList = new PointbyPoint();
		double[][] rawPoints= MeasList.pointOfMeasurement(); 
		double[][] NormPoints= MeasList.NormMeasurement(rawPoints);
		double[][] VoltAng1= new double[counter1][18];
		double[][] VoltAng2= new double[counter2][18];
		double[][] VoltAng3= new double[counter3][18];
		double[][] VoltAng4= new double[counter4][18];

		////// Voltage/Angle of Cluster 1 //////
		for(int i=0; i<counter1; i++){
			double maxVol1= 0, minVol1= 0, maxAng1= 0, minAng1=0;
			for(int k=0; k<200; k++){ // going through all normalized points to find the target point in cluster1		
					if(FinalC1[i][0]==NormPoints[k][0]&&FinalC1[i][1]==NormPoints[k][1]&&FinalC1[i][2]==NormPoints[k][2]&&
					   FinalC1[i][3]==NormPoints[k][3]&&FinalC1[i][4]==NormPoints[k][4]&&FinalC1[i][5]==NormPoints[k][5]&&
					   FinalC1[i][6]==NormPoints[k][6]&&FinalC1[i][7]==NormPoints[k][7]&&FinalC1[i][8]==NormPoints[k][8]&&
					   FinalC1[i][9]==NormPoints[k][9]&&FinalC1[i][10]==NormPoints[k][10]&&FinalC1[i][11]==NormPoints[k][11]&&
					   FinalC1[i][12]==NormPoints[k][12]&&FinalC1[i][13]==NormPoints[k][13]&&FinalC1[i][14]==NormPoints[k][14]&&
					   FinalC1[i][15]==NormPoints[k][15]&&FinalC1[i][16]==NormPoints[k][16]&&FinalC1[i][17]==NormPoints[k][17]){
					   // Finding Max/Min voltage of the target point among measurements for turning back to real value
					   maxVol1=rawPoints[k][0];
					   minVol1=rawPoints[k][0];
						for (int m=0; m<18; m+=2) {
						    if (rawPoints[k][m] > maxVol1) {
						    	maxVol1 = rawPoints[k][m];
						    }
						    if (rawPoints[k][m] < minVol1) {
						    	minVol1 = rawPoints[k][m];
						    }
						}
						// Finding Max/Min angle of the target point among measurements for turning back to real value
						maxAng1=rawPoints[k][1];	
						minAng1=rawPoints[k][1];
						for (int h=1; h<18; h+=2) {
						    if (rawPoints[k][h] > maxAng1) {
						    	maxAng1 = rawPoints[k][h];
						    }
						    if (rawPoints[k][h] < minAng1) {
						    	minAng1 = rawPoints[k][h];
						    }
						}
					}
					
			} // Max/Min voltage and angle of the target point in cluster1 found
			
			// De-normalizing process for turning back to real value
			for(int v=0; v<18; v+=2){
				VoltAng1[i][v]=(FinalC1[i][v]*(maxVol1-minVol1))+minVol1;
			}
			for(int a=1; a<18; a+=2){
				VoltAng1[i][a]=(FinalC1[i][a]*(maxAng1-minAng1))+minAng1;
			}
		} // De-normalizing process for all points in cluster1 is done!
		
		// Repeating the same process as Cluster 1 for the rest of the Clusters //
		
	    ////// Voltage/Angle of Cluster 2 //////
			for(int i=0; i<counter2; i++){
				double maxVol2= 0, minVol2= 0, maxAng2= 0, minAng2=0;
				for(int k=0; k<200; k++){
					if(FinalC2[i][0]==NormPoints[k][0]&&FinalC2[i][1]==NormPoints[k][1]&&FinalC2[i][2]==NormPoints[k][2]&&
					   FinalC2[i][3]==NormPoints[k][3]&&FinalC2[i][4]==NormPoints[k][4]&&FinalC2[i][5]==NormPoints[k][5]&&
					   FinalC2[i][6]==NormPoints[k][6]&&FinalC2[i][7]==NormPoints[k][7]&&FinalC2[i][8]==NormPoints[k][8]&&
					   FinalC2[i][9]==NormPoints[k][9]&&FinalC2[i][10]==NormPoints[k][10]&&FinalC2[i][11]==NormPoints[k][11]&&
					   FinalC2[i][12]==NormPoints[k][12]&&FinalC2[i][13]==NormPoints[k][13]&&FinalC2[i][14]==NormPoints[k][14]&&
					   FinalC2[i][15]==NormPoints[k][15]&&FinalC2[i][16]==NormPoints[k][16]&&FinalC2[i][17]==NormPoints[k][17]){

							maxVol2=rawPoints[k][0];
							minVol2=rawPoints[k][0];
							for (int m=0; m<18; m+=2) {
							    if (rawPoints[k][m] > maxVol2) {
							    	maxVol2 = rawPoints[k][m];
							    }
							    if (rawPoints[k][m] < minVol2) {
							    	minVol2 = rawPoints[k][m];
							    }
							}
							maxAng2=rawPoints[k][1];	
							minAng2=rawPoints[k][1];
							for (int h=1; h<18; h+=2) {
							    if (rawPoints[k][h] > maxAng2) {
							    	maxAng2 = rawPoints[k][h];
							    }
							    if (rawPoints[k][h] < minAng2) {
							    	minAng2 = rawPoints[k][h];
							    }
							}
						}			
				}
			
				for(int v=0; v<18; v+=2){
					VoltAng2[i][v]=(FinalC2[i][v]*(maxVol2-minVol2))+minVol2;
				}
				for(int a=1; a<18; a+=2){
					VoltAng2[i][a]=(FinalC2[i][a]*(maxAng2-minAng2))+minAng2;
				}
			}
			
	 	    ////// Voltage/Angle of Cluster 3 //////
			for(int i=0; i<counter3; i++){
				double maxVol3= 0, minVol3= 0, maxAng3= 0, minAng3=0;
				for(int k=0; k<200; k++){
					if(FinalC3[i][0]==NormPoints[k][0]&&FinalC3[i][1]==NormPoints[k][1]&&FinalC3[i][2]==NormPoints[k][2]&&
					   FinalC3[i][3]==NormPoints[k][3]&&FinalC3[i][4]==NormPoints[k][4]&&FinalC3[i][5]==NormPoints[k][5]&&
					   FinalC3[i][6]==NormPoints[k][6]&&FinalC3[i][7]==NormPoints[k][7]&&FinalC3[i][8]==NormPoints[k][8]&&
					   FinalC3[i][9]==NormPoints[k][9]&&FinalC3[i][10]==NormPoints[k][10]&&FinalC3[i][11]==NormPoints[k][11]&&
					   FinalC3[i][12]==NormPoints[k][12]&&FinalC3[i][13]==NormPoints[k][13]&&FinalC3[i][14]==NormPoints[k][14]&&
					   FinalC3[i][15]==NormPoints[k][15]&&FinalC3[i][16]==NormPoints[k][16]&&FinalC3[i][17]==NormPoints[k][17]){
						
							maxVol3=rawPoints[k][0];
							minVol3=rawPoints[k][0];
							for (int m=0; m<18; m+=2) {
							    if (rawPoints[k][m] > maxVol3) {
							    	maxVol3 = rawPoints[k][m];
							    }
							    if (rawPoints[k][m] < minVol3) {
							    	minVol3 = rawPoints[k][m];
							    }
							}
							maxAng3=rawPoints[k][1];	
							minAng3=rawPoints[k][1];
							for (int h=1; h<18; h+=2) {
							    if (rawPoints[k][h] > maxAng3) {
							    	maxAng3 = rawPoints[k][h];
							    }
							    if (rawPoints[k][h] < minAng3) {
							    	minAng3 = rawPoints[k][h];
							    }
							}
						}			
				}
				for(int v=0; v<18; v+=2){
					VoltAng3[i][v]=(FinalC3[i][v]*(maxVol3-minVol3))+minVol3;
				}
				for(int a=1; a<18; a+=2){
					VoltAng3[i][a]=(FinalC3[i][a]*(maxAng3-minAng3))+minAng3;
				}
			}
			
		    ////// Voltage/Angle of Cluster 4 //////
			for(int i=0; i<counter4; i++){
				double maxVol4= 0, minVol4= 0, maxAng4= 0, minAng4=0;
				for(int k=0; k<200; k++){
					if(FinalC4[i][0]==NormPoints[k][0]&&FinalC4[i][1]==NormPoints[k][1]&&FinalC4[i][2]==NormPoints[k][2]&&
					   FinalC4[i][3]==NormPoints[k][3]&&FinalC4[i][4]==NormPoints[k][4]&&FinalC4[i][5]==NormPoints[k][5]&&
					   FinalC4[i][6]==NormPoints[k][6]&&FinalC4[i][7]==NormPoints[k][7]&&FinalC4[i][8]==NormPoints[k][8]&&
					   FinalC4[i][9]==NormPoints[k][9]&&FinalC4[i][10]==NormPoints[k][10]&&FinalC4[i][11]==NormPoints[k][11]&&
					   FinalC4[i][12]==NormPoints[k][12]&&FinalC4[i][13]==NormPoints[k][13]&&FinalC4[i][14]==NormPoints[k][14]&&
					   FinalC4[i][15]==NormPoints[k][15]&&FinalC4[i][16]==NormPoints[k][16]&&FinalC4[i][17]==NormPoints[k][17]){
					
							maxVol4=rawPoints[k][0];
							minVol4=rawPoints[k][0];
							for (int m=0; m<18; m+=2) {
							    if (rawPoints[k][m] > maxVol4) {
							    	maxVol4 = rawPoints[k][m];
							    }
							    if (rawPoints[k][m] < minVol4) {
							    	minVol4 = rawPoints[k][m];
							    }
							}
							maxAng4=rawPoints[k][1];	
							minAng4=rawPoints[k][1];
							for (int h=1; h<18; h+=2) {
							    if (rawPoints[k][h] > maxAng4) {
							    	maxAng4 = rawPoints[k][h];
							    }
							    if (rawPoints[k][h] < minAng4) {
							    	minAng4 = rawPoints[k][h];
							    }
							}
						}			
				}
				for(int v=0; v<18; v+=2){
					VoltAng4[i][v]=(FinalC4[i][v]*(maxVol4-minVol4))+minVol4;
				}
				for(int a=1; a<18; a+=2){
					VoltAng4[i][a]=(FinalC4[i][a]*(maxAng4-minAng4))+minAng4;
				}
			}
			
		////////////////////////////////////////////////////////////////////
		// Printing out mean voltage/angle value of each bus in cluster 1 //
		//////////////////////////////////////////////////////////////////
		System.out.println("\n ** Mean Voltage/Angle value of each Bus in each Cluster ** \n ");
		for(int j=0; j<18; j++){
			double sum=0;
			for(int i=0; i<VoltAng1.length; i++){
				sum=sum+VoltAng1[i][j];				
			}
			meanVoltAng1[j]=sum/VoltAng1.length;
			System.out.print(meanVoltAng1[j]+"\t");
		}
		// Printing out mean voltage/angle value of each bus in cluster 2
				System.out.print("\n");
				for(int j=0; j<18; j++){
					double sum=0;
					for(int i=0; i<VoltAng2.length; i++){
						sum=sum+VoltAng2[i][j];				
					}
					meanVoltAng2[j]=sum/VoltAng2.length;
					System.out.print(meanVoltAng2[j]+"\t");
				}
				// Printing out mean voltage/angle value of each bus in cluster 3
				System.out.print("\n");
				for(int j=0; j<18; j++){
					double sum=0;
					for(int i=0; i<VoltAng3.length; i++){
						sum=sum+VoltAng3[i][j];				
					}
					meanVoltAng3[j]=sum/VoltAng3.length;
					System.out.print(meanVoltAng3[j]+"\t");
				}
				// Printing out mean voltage/angle value of each bus in cluster 4
				System.out.print("\n");
				for(int j=0; j<18; j++){
					double sum=0;
					for(int i=0; i<VoltAng4.length; i++){
						sum=sum+VoltAng4[i][j];				
					}
					meanVoltAng4[j]=sum/VoltAng4.length;
					System.out.print(meanVoltAng4[j]+"\t");
				}
	} // Method Finished
	
	public double[] getMeanVoltAng1(){
		return meanVoltAng1;
	}
	public double[] getMeanVoltAng2(){
		return meanVoltAng2;
	}
	public double[] getMeanVoltAng3(){
		return meanVoltAng3;
	}
	public double[] getMeanVoltAng4(){
		return meanVoltAng4;
	}

	///////////////////////////////////////////////////////////////////////
	////////// Method for printing out the final clusters of KMeans ////////////////
	/////////////////////////////////////////////////////////////////////
	double[][] FinalC1= new double[200][18];
	double[][] FinalC2= new double[200][18];
	double[][] FinalC3= new double[200][18];
	double[][] FinalC4= new double[200][18];
	int counter1=0, counter2=0, counter3=0, counter4=0;
	
	public void printingClusters(double[][] StoreCluster1, double[][] StoreCluster2, double[][] StoreCluster3,
			                     double[][] StoreCluster4){
		
    /////////Printing the Number of the Assigned Points to each Cluster ///////////
				
				for(int i=0; i<200; i++){
					double sum1=0, sum2=0, sum3=0, sum4=0;
					for(int j=0; j<18; j++){
						sum1=sum1+StoreCluster1[i][j];
						sum2=sum2+StoreCluster2[i][j];
						sum3=sum3+StoreCluster3[i][j];
						sum4=sum4+StoreCluster4[i][j];
					}
					if(sum1!=0){
						counter1++;
					}
					if(sum2!=0){
						counter2++;
					}
					if(sum3!=0){
						counter3++;
					}
					if(sum4!=0){
						counter4++;
					}
				}
				System.out.println("Cluster 1 Size: "+ counter1);
				System.out.println("Cluster 2 Size: "+ counter2);
				System.out.println("Cluster 3 Size: "+ counter3);
				System.out.println("Cluster 4 Size: "+ counter4);
				
				
	        	//////// Final Cluster1 //////////
				int count1=0;
				for(int i=0; i<200; i++){
					double sum=0;
					for(int j=0; j<18; j++){
						sum=sum+StoreCluster1[i][j];
					}
					if(sum==0){
						count1++;
					}else{
						for(int k=0; k<18; k++){
						FinalC1[i-count1][k]=StoreCluster1[i][k];
						}
					}
				}
				///////// Printing Cluster 1 ////////////
				System.out.println("\n ********** Cluster 1 of KMeans *********** ");
				for(int i=0; i<counter1; i++){
					System.out.print("\n");
					for(int j=0; j<18; j++){
						System.out.print(FinalC1[i][j]+"\t");
					}
				}
				
		        //////// Final Cluster2 //////////
				int count2=0;
				for(int i=0; i<200; i++){
					double sum=0;
					for(int j=0; j<18; j++){
						sum=sum+StoreCluster2[i][j];
					}
					if(sum==0){
						count2++;
					}else{
						for(int k=0; k<18; k++){
						FinalC2[i-count2][k]=StoreCluster2[i][k];
						}
					}
				}	
				///////// Printing Cluster2 ////////////
				System.out.println("\n");
				System.out.println("\n ********** Cluster 2 of KMeans ***********");
				for(int i=0; i<counter2; i++){
					System.out.print("\n");
					for(int j=0; j<18; j++){
						System.out.print(FinalC2[i][j]+"\t");
					}
				}
		        //////// Final Cluster3 //////////
				int count3=0;
				for(int i=0; i<200; i++){
					double sum=0;
					for(int j=0; j<18; j++){
						sum=sum+StoreCluster3[i][j];
					}
					if(sum==0){
						count3++;
					}else{
						for(int k=0; k<18; k++){
						FinalC3[i-count3][k]=StoreCluster3[i][k];
						}
					}
				}
			    ////////// Printing Cluster3 ////////////
				System.out.println("\n");
				System.out.println("\n ********** Cluster 3 of KMeans *********** ");
				for(int i=0; i<counter3; i++){
					System.out.print("\n");
					for(int j=0; j<18; j++){
						System.out.print(FinalC3[i][j]+"\t");
					}
				}
		        //////// Final Cluster4 //////////
				int count4=0;
				for(int i=0; i<200; i++){
					double sum=0;
					for(int j=0; j<18; j++){
						sum=sum+StoreCluster4[i][j];
					}
					if(sum==0){
						count4++;
					}else{
						for(int k=0; k<18; k++){
						FinalC4[i-count4][k]=StoreCluster4[i][k];
						}
					}
				}
			    ////////// Printing Cluster4 ////////////
				System.out.println("\n");
				System.out.println("\n ********** Cluster 4 of KMeans ***********");
				for(int i=0; i<counter4; i++){
					System.out.print("\n");
					for(int j=0; j<18; j++){
						System.out.print(FinalC4[i][j]+"\t");
					}
				}	
	} // Method Finished
	
	public double[][] getFinalC1(){
		return FinalC1;
	}
	public double[][] getFinalC2(){
		return FinalC2;
	}
	public double[][] getFinalC3(){
		return FinalC3;
	}
	public double[][] getFinalC4(){
		return FinalC4;
	}
	public int getCounter1(){
		return counter1;
	}
	public int getCounter2(){
		return counter2;
	}
	public int getCounter3(){
		return counter3;
	}
	public int getCounter4(){
		return counter4;
	}

	///////////////////////////////////////////////
	//////// Method for applying KMeans //////////
	//////////////////////////////////////////////
	double[][] StoreCluster1= null;
	double[][] StoreCluster2= null;
	double[][] StoreCluster3= null;
	double[][] StoreCluster4= null;
	
    /////////// Start KMeans Method	///////////
	public void KMeansMethod() {
		
		// Creating new list of measurement points and normalized values
		PointbyPoint measurementList = new PointbyPoint();
		double[][] rawPoints= measurementList.pointOfMeasurement();
		double[][] points= measurementList.NormMeasurement(rawPoints);

	// Defining boolean variable as the stop criteria for while loop
	boolean check= true;
	int iteration= 0; // keep the number of iterations
	double toler= 0.0000000000001; // Desirable difference between old and new centers
	int maxIter = 1000000000; // Maximum iteration to stop the while loop

	// Saving the values of old centroids // 
	double[][] oldCenters = null;
	
	while(check==true && iteration<maxIter){
	
		
		Clustering newCluster= new Clustering();
		
		// First iteration
		if(iteration==0){
			newCluster.createRandom(points);
			double[][] random= newCluster.getRandCenter();
			double[][][] cluster= newCluster.EucDistance(points,random);// Assigning points to Clusters in a 3D array
			newCluster.two2three(cluster); // Divide 3D array to 4 two dimension Clusters
			double[][] C1= newCluster.getcluster1(); // Cluster1
			double[][] C2= newCluster.getcluster2(); // Cluster2
			double[][] C3= newCluster.getcluster3(); // Cluster3
			double[][] C4= newCluster.getcluster4(); // Cluster4
			newCluster.MeanCenter(C1, C2, C3, C4); // Calculating new Centroid for each Cluster
			double[] newC1= newCluster.getCenter1(); // New Center1
			double[] newC2= newCluster.getCenter2(); // New Center2
			double[] newC3= newCluster.getCenter3(); // New Center3
			double[] newC4= newCluster.getCenter4(); // New Center4
			double[][] newCenters= newCluster.toArray(newC1, newC2, newC3, newC4); // Combining New Centers in a two dimension array
			double[] diff= newCluster.CentDistance(newCenters, random);// Difference between new centers and initial ones
				
			// Checking if difference is less than tolerance
			if(diff[0]<toler && diff[1]<toler && diff[2]<toler && diff[3]<toler){
					check=false;
				}else{
					oldCenters= newCenters;
				}
			// After the first iteration 
		}else{
			double[][][] cluster= newCluster.EucDistance(points,oldCenters);// Assigning points to Clusters in a 3D array
			newCluster.two2three(cluster); // Divide 3D array to 4 two dimension Clusters
			double[][] C1= newCluster.getcluster1(); // Cluster1
			double[][] C2= newCluster.getcluster2(); // Cluster2
			double[][] C3= newCluster.getcluster3(); // Cluster3
			double[][] C4= newCluster.getcluster4(); // Cluster4
			newCluster.MeanCenter(C1, C2, C3, C4); // Calculating new Centroid for each Cluster
			double[] newC1= newCluster.getCenter1(); // New Center1
			double[] newC2= newCluster.getCenter2(); // New Center2
			double[] newC3= newCluster.getCenter3(); // New Center3
			double[] newC4= newCluster.getCenter4(); // New Center4
			double[][] newCenters= newCluster.toArray(newC1, newC2, newC3, newC4); // Combining New Centers in an two dimension array
			double[] diff= newCluster.CentDistance(newCenters, oldCenters); // Difference between old and new centers
			StoreCluster1= C1; // Storing values of cluster 1
			StoreCluster2= C2; // Storing values of cluster 2
			StoreCluster3= C3; // Storing values of cluster 3
			StoreCluster4= C4; // Storing values of cluster 4
			
			// Checking if difference is less than tolerance
				if(diff[0]<toler && diff[1]<toler && diff[2]<toler && diff[3]<toler){
					check=false;
				}else{
					oldCenters= newCenters;
				}	
		}// end of if statement

		iteration++;
	} // Finished While loop			
		
 }// KMeans Method Finished
	
	public double[][] getCluster1(){
		return StoreCluster1;
	}
	public double[][] getCluster2(){
		return StoreCluster2;
	}
	public double[][] getCluster3(){
		return StoreCluster3;
	}
	public double[][] getCluster4(){
		return StoreCluster4;
	}


	////////////////////////////////////////////////////////
	///////////// Labeling the clusters ////////////////////
	////////////////////////////////////////////////////////
			
	// High load rate during peak hours
	// Shut down of generator for maintenance
	// Low load rate during might
	// Disconnection of a line for maintenance
		
	String [][] ClustersLabel = new String [4][1];
	double [][] MeansTogether = new double [4][18];
	
	public void labelClusters(double[] meanVoltAng12, double[] meanVoltAng22,
			double[] meanVoltAng32, double[] meanVoltAng42) {
		
		// Input: Final Centroids
				
		for(int i=0; i<4; i++){
			//Centroid cluster 1 in MeansTogether double[][]
			if(i==0){
				for(int k=0; k<18; k++){
				MeansTogether[i][k]= meanVoltAng12[k];
				}
			}
			//Centroid cluster 2 in MeansTogether double[][]
			if(i==1){
				for(int k=0; k<18; k++){
				MeansTogether[i][k]= meanVoltAng22[k];
				}
			}
			//Centroid cluster 3 in MeansTogether double[][]
			if(i==2){
				for(int k=0; k<18; k++){
				MeansTogether[i][k]= meanVoltAng32[k];
				}
			}
			//Centroid cluster 4 in MeansTogether double[][]
			if(i==3){
				for(int k=0; k<18; k++){
				MeansTogether[i][k]= meanVoltAng42[k];
				}
			}			
		}// End for
		
	
		//Define Variables (Indexes and Adds)
		int initialValue = 0;
		int indexGD = 0;		
		double  sumDif =0;
		int indexLR =0;
		double sumLR=0;		
		double LowLF =  1000;
		int indexLow = 0;
		int indexHigh = 0;
		
		//Labeling 1: Disconnection of a line for maintenance
			//Les reactive in the system -> More positive angles
		for (int i=0; i<4; i++){
			int count = 0;
			sumLR=0;
			double sumPF=0;
					
			for (int p=1; p<18; p+=2){
				if (MeansTogether[i][p]>0){
					count++; //Number of positive angles
					if(count > initialValue){ // N. positive angles higher than a value
						initialValue = count;
						indexGD = i; //Index of the cluster labeled 
					}
				}
			}//End labeling 1
			
			
			//Labeling 2: Low load rate during night
				//Higher voltages in the buses
			for (int p=0; p<18; p+=2){
				//add voltages 
				sumLR =sumLR +MeansTogether[i][p];			
				if(sumLR > sumDif && i!=indexGD){ //Can't be the groud already labeled.
					sumDif = sumLR;
					indexLR = i;
				}
				}// End Labeling 2
					
			
			//Labeling 3: Shut down generator for maintenance
				/// Lowest power flow values
			
			//Power Flow for the 9 lines added together.   P=((Ek·Vk)/Xline)*cos(Angk-Angx)
			sumPF = sumPF +(MeansTogether[i][0]*MeansTogether[i][6]*Math.sin(Math.toRadians(MeansTogether[i][1]-MeansTogether[i][7])));
			sumPF = sumPF +(MeansTogether[i][2]*MeansTogether[i][14]*Math.sin(Math.toRadians(MeansTogether[i][3]-MeansTogether[i][15])));
			sumPF = sumPF +(MeansTogether[i][4]*MeansTogether[i][10]*Math.sin(Math.toRadians(MeansTogether[i][5]-MeansTogether[i][11])));			
			sumPF = sumPF +(MeansTogether[i][6]*MeansTogether[i][16]*Math.sin(Math.toRadians(MeansTogether[i][7]-MeansTogether[i][17])));
			sumPF = sumPF +(MeansTogether[i][16]*MeansTogether[i][14]*Math.sin(Math.toRadians(MeansTogether[i][17]-MeansTogether[i][15])));
			sumPF = sumPF +(MeansTogether[i][12]*MeansTogether[i][14]*Math.sin(Math.toRadians(MeansTogether[i][13]-MeansTogether[i][15])));
			sumPF = sumPF +(MeansTogether[i][10]*MeansTogether[i][12]*Math.sin(Math.toRadians(MeansTogether[i][11]-MeansTogether[i][13])));
			sumPF = sumPF +(MeansTogether[i][8]*MeansTogether[i][10]*Math.sin(Math.toRadians(MeansTogether[i][9]-MeansTogether[i][11])));
			sumPF = sumPF +(MeansTogether[i][6]*MeansTogether[i][8]*Math.sin(Math.toRadians(MeansTogether[i][7]-MeansTogether[i][9])));
					
			if (sumPF < LowLF && i!=indexLR && i!=indexGD){ //Can't repeat clusters
				LowLF = sumPF;
				indexLow = i; //Index from the lower power flow result
			}// End Labeling 3
			
			
			//Labeling 4: Shut down generator for maintenance
				// Highest power flow values
				// The Cluster left
			for(int q=0; q<4; q++){
				
			if (q!=indexLow && q!=indexLR && q!=indexGD ){
				indexHigh = q;
			}	
			}//End Labeling 4		
		}//End of all the labelings
		
		//Printing the Labels
		System.out.println("\n");
		ClustersLabel[indexGD][0]=("Disconnecion of a line for maintenance");
		System.out.println("\n"+"The Disconnected line for maintenance are from cluster: "+(indexGD+1));
		
		ClustersLabel[indexLR][0]=("Low load rate during night");	
		System.out.println("\n"+"The Low load rate during night are from cluster: "+(indexLR+1));
		
		ClustersLabel[indexLow][0]=("Shut down generator for maintenance");	
		System.out.println("\n"+"The Shut down generator for maintenance are from cluster: "+(indexLow+1));
		
		ClustersLabel[indexHigh][0]=("High Load rate during peak hours");
		System.out.println("\n"+"The High Load rate during peak hours are from cluster: "+(indexHigh+1));


	}// End of Labeling Method	
	
	
	
}//End Kmeans Class