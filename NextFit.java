import java.util.*;

public class NextFit{
	static void nextFit(int blockSize[],int processSize[],int m,int n){
		int[] allocation = new int[n];
		int[] status = new int[m];
		
		for(int i = 0;i<n;i++){
			allocation[i] = -1;
			status[i] = -1;
		}
		for(int i = 0;i<n;i++){
			int bestIdx = -1;
			for(int j = 0;j<m;j++){
				if(blockSize[j] >= processSize[i]){
					if(bestIdx == -1){
						bestIdx = j;
					}
					else if(blockSize[bestIdx] > blockSize[j]){
						bestIdx = j;
					}
				}
			}
			if(bestIdx != -1){
				allocation[i] = bestIdx;
			}
		}
		System.out.println("\nProcessNo \t Process Size \t Block No");
		for(int i = 0;i<n;i++){
			System.out.print("" + (i+1) + "\t\t" + processSize[i] + "\t\t");
			if(allocation[i] != -1){
				System.out.println(allocation[i] + 1);
			}
			else{
				System.out.println("Not Allocated");
			}
		}
	
	}	

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of process : ");
		int n = sc.nextInt();
		System.out.print("Enter number of Blocks : ");
		int m = sc.nextInt();
		int blockSize[] = new int[m];
        int processSize[] = new int[n];
		for(int i = 0;i<n;i++){
			System.out.print("Enter process size for process no." + (i+1) + " : ");
			processSize[i] = sc.nextInt();
		}
		for(int i = 0;i<m;i++){
			System.out.print("Enter Block size for Block no." + (i+1) + " : ");
			blockSize[i] = sc.nextInt();
		}

        nextFit(blockSize,processSize, m, n);
	}
}
