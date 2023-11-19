import java.util.Scanner;
import java.util.Arrays;
class SJF{
	public static void main(String[] args){
	    int temp;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of Processes : ");
		int n = sc.nextInt();
		
		int[] PID = new int[n];
		int[] cmpTime = new int[n];
		int[] burstTime = new int[n];
		int[] waitingTime = new int[n];
		int[] turnAroundTime = new int[n];
		
		float avgWaitingTime = 0;
		float avgTurnAroundTime = 0;
		
		for(int i = 0;i<n;i++){
			PID[i] = i+1;
			System.out.print("Enter Burst Time of Process " + (i+1) + " : ");
			burstTime[i] = sc.nextInt();
		}
		for(int i = 0;i<n;i++){
			for(int j = 0 ;j < n-i-1;j++){
				if(burstTime[j] > burstTime[j+1]){
					temp = PID[j];
					PID[j] = PID[j+1];	
					PID[j+1] = temp;
					
					temp = burstTime[j];
					burstTime[j] = burstTime[j+1];
					burstTime[j+1] = temp;
					
				}
			}
		}
		cmpTime[0] = burstTime[0];
		for(int i = 1;i<n;i++){
			cmpTime[i] = cmpTime[i-1]+burstTime[i];
		}
		waitingTime[0] = 0;
		for(int i = 1;i<n;i++){
			waitingTime[i] = cmpTime[i-1];
			avgWaitingTime+=waitingTime[i];	
		}
		for(int i = 0;i<n;i++){
			turnAroundTime[i] = waitingTime[i]+burstTime[i];
			avgTurnAroundTime += turnAroundTime[i];
		}
		System.out.println("\nProcess\tBurst Time\tWaiting Time\tCompletion Time\tTurnAround Time");
		for(int i = 0;i<n;i++){
			System.out.println(PID[i] + "\t\t"+burstTime[i]+"\t\t"+waitingTime[i]+"\t\t" +cmpTime[i] +"\t\t"+turnAroundTime[i]);
		}
		System.out.println("Waiting Time : " + avgWaitingTime );
		System.out.println("Average Waiting Time : " + avgWaitingTime/n );
		System.out.println("Average TurnAround Time : " + avgTurnAroundTime/n );
		sc.close();
	}
}
