import java.util.*;
public class FirstFit{
	static void firstFit(int blockSize[],int processSize[],int m,int n){
		int[] allocation = new int[n];
		int[] status = new int[m];
		
		for(int i = 0;i<allocation.length;i++){
			status[i] = 0;
			allocation[i] = -1;
		}
		
		for(int i = 0;i<n;i++){
			int j = 0;
			int count = 0;
			while(j<m){
				count++;
				System.out.println("hello");
				if(blockSize[j] >= processSize[i] && status[j] == 0){
					allocation[i] = j;
					status[j] = 1;
					
					blockSize[j] -= processSize[i];
					break;
				}
				if(count == m){
					break;
				}
				j = (j+1) % m;
				
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

        firstFit(blockSize,processSize, m, n);
	}
}
