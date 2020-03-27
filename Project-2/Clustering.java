package Project2Package;

import java.util.Random;

public class Clustering {
	

	///// Calculate distance between old and new centroids ////////
	double[] diff= new double[4];
	public double[] CentDistance(double[][] newCenters, double[][] oldCenters ){
		
			for(int j=0; j<4; j++){
				double sum=0;
				for(int k=0; k<18; k++){
			        sum = sum + Math.pow((newCenters[j][k] - oldCenters[j][k]),2); 
				}
				diff[j] = Math.sqrt(sum);
			}			
		return diff;
	}
	
	///// Creating Array of New Centroid //////
	double[][] newCenters = new double[4][18];
	public double[][] toArray(double[] Center1, double[] Center2, double[] Center3, double[] Center4){
		for(int i=0; i<4; i++){
			for(int j=0; j<18; j++){
				if(i==0){
				newCenters[i][j]=Center1[j];
				}
				if(i==1){
					newCenters[i][j]=Center2[j];
				}
				if(i==2){
					newCenters[i][j]=Center3[j];
				}
				if(i==3){
					newCenters[i][j]=Center4[j];
				}
			}
		}
		return newCenters;
	}

	//////// New Centroid of each Cluster ////////
	double[] Center1 = new double[18];
	double[] Center2 = new double[18];
	double[] Center3 = new double[18];
	double[] Center4 = new double[18];
	
	public void MeanCenter(double[][] cluster1, double[][] cluster2, double[][] cluster3, double[][] cluster4){
		
		boolean check1=false, check2=false, check3=false, check4=false;
		for(int i=0; i<200; i++){
			double sum1=0, sum2=0, sum3=0, sum4=0;
			for(int j=0; j<18; j++){
				sum1=sum1+cluster1[i][j];
				sum2=sum2+cluster2[i][j];
				sum3=sum3+cluster3[i][j];
				sum4=sum4+cluster4[i][j];
			}
			if(sum1!=0){
				check1=true;
			}
			if(sum2!=0){
				check2=true;
			}
			if(sum3!=0){
				check3=true;
			}
			if(sum4!=0){
				check4=true;
			}
		}
		
		//////////////////////////////////////////////////////////////////////
		////// Check for Empty Clusters and Calculate the New Center Value/////
		
		// Cluster 1
		// if the cluster1 is totally empty assign a new random value
		if(check1==false){
			Random rand = new Random();
			for (int j = 0; j<18; j++){
				Center1[j] = rand.nextDouble(); 
		    }
		}
		// if cluster1 is not empty, then calculate the mean value
		if(check1==true){
			double[] totalSum1= new double[18];
			int counter1=0;
			for(int i=0; i<200; i++){
				double sum=0;
				for(int j=0; j<18; j++){
					sum=sum+cluster1[i][j];
					totalSum1[j]=totalSum1[j]+cluster1[i][j];
				}
				if(sum!=0){
					counter1++;
				}
			}
			for(int k=0; k<18; k++){
				Center1[k]=totalSum1[k]/counter1;
			}
		}
		
		// Cluster 2
		if(check2==false){
			Random rand = new Random();
			for (int j = 0; j<18; j++){
				Center2[j] = rand.nextDouble(); 
		    }
		}
		if(check2==true){
			double[] totalSum2= new double[18];
			int counter2=0;
			for(int i=0; i<200; i++){
				double sum=0;
				for(int j=0; j<18; j++){
					sum=sum+cluster2[i][j];
					totalSum2[j]=totalSum2[j]+cluster2[i][j];
				}
				if(sum!=0){
					counter2++;
				}
			}
			for(int k=0; k<18; k++){
				Center2[k]=totalSum2[k]/counter2;
			}
		}
		
		// Cluster 3
		if(check3==false){
			Random rand = new Random();
			for (int j = 0; j<18; j++){
				Center3[j] = rand.nextDouble(); 
		    }
		}
		if(check3==true){
			double[] totalSum3= new double[18];
			int counter3=0;
			for(int i=0; i<200; i++){
				double sum=0;
				for(int j=0; j<18; j++){
					sum=sum+cluster3[i][j];
					totalSum3[j]=totalSum3[j]+cluster3[i][j];
				}
				if(sum!=0){
					counter3++;
				}
			}
			for(int k=0; k<18; k++){
				Center3[k]=totalSum3[k]/counter3;
			}
		}
		
		// Cluster 4
		if(check4==false){
			Random rand = new Random();
			for (int j = 0; j<18; j++){
				Center4[j] = rand.nextDouble(); 
		    }
		}
		if(check4==true){
			double[] totalSum4= new double[18];
			int counter4=0;
			for(int i=0; i<200; i++){
				double sum=0;
				for(int j=0; j<18; j++){
					sum=sum+cluster4[i][j];
					totalSum4[j]=totalSum4[j]+cluster4[i][j];
				}
				if(sum!=0){
					counter4++;
				}
			}
			for(int k=0; k<18; k++){
				Center4[k]=totalSum4[k]/counter4;	
			}
		}
		
  }// Finished Method
	public double[] getCenter1(){
		return Center1;
	}
	public double[] getCenter2(){
		return Center2;
	}
	public double[] getCenter3(){
		return Center3;
	}
	public double[] getCenter4(){
		return Center4;
	}
	

	////// Three dimension to Two dimension Array //////
		double[][] cluster1= new double[200][18];
		double[][] cluster2= new double[200][18];
		double[][] cluster3= new double[200][18];
		double[][] cluster4= new double[200][18];
		
		public void two2three(double[][][] cluster){
			for(int i=0; i<4; i++){
				for(int j=0; j<200; j++){
					for(int k=0; k<18; k++){
						if(i==0){
						    cluster1[j][k]=cluster[i][j][k];
						}else if(i==1){
							cluster2[j][k]=cluster[i][j][k];
						}else if(i==2){
							cluster3[j][k]=cluster[i][j][k];
						}else if(i==3){
							cluster4[j][k]=cluster[i][j][k];
						}
					}
				}
			}
		}// Finished Method
		public double[][] getcluster1(){
			return cluster1;
		}
		public double[][] getcluster2(){
			return cluster2;
		}
		public double[][] getcluster3(){
			return cluster3;
		}
		public double[][] getcluster4(){
			return cluster4;
		}

	    ////// Calculate Euclidean Distance ////////
	    double[][][] cluster= new double[4][200][18];
		public double[][][] EucDistance(double[][] points, double[][] centroid ){
			
			for(int i=0; i<200; i++){
				double distance=0;
				int ClusterNum=3;
				double minDistance=1000;
				for(int j=0; j<4; j++){
					double sum=0;
					for(int k=0; k<18; k++){
				        sum = sum + Math.pow((centroid[j][k] - points[i][k]),2); 
					}
					distance = Math.sqrt(sum);
					if(distance < minDistance){
					minDistance=distance;
					ClusterNum=j;
					}
				}
				for(int k=0; k<18; k++){
					cluster[ClusterNum][i][k]=points[i][k];
					}				
			}
			return cluster;
		}
		
	 ////// Creating Random Center Point with Forgy Method ///////
	Random rand = new Random();
	double[][] RandCenter = new double[4][18];
	int[] index = new int[4]; 
	public void createRandom(double[][] points){
		for(int i=0; i<4; i++){
				index[i]=rand.nextInt(200);
		}
				for(int j=0; j<4; j++){
					for(int k=0; k<4; k++){
						if (index[j]==index[k] && k!=j){
							index[k]=rand.nextInt(199-0)+0;
						}
					}
				}
				System.out.println("\n"+" ** Forgy Method for Initializing ** ");
				System.out.println("Index of randomly selected points of measurements: "+index[0]+"\t"+index[1]+"\t"+index[2]+"\t"+index[3]+"\n");
				for(int i=0; i<4; i++){
					for(int j=0; j<18; j++){
						RandCenter[i][j]= points[index[i]][j];	
					}
				}
	}
	public double[][] getRandCenter(){
			return RandCenter;
	}
	
}// Class Finished