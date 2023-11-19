import java.util.Scanner;

public class RoundRobin {
    public static void main(String args[]) {
        int n, i, quantumTime, count = 0, temp, currentTime = 0;
        int[] burstTime, waitingTime, turnaroundTime, remainingTime;

        Scanner s = new Scanner(System.in);

        System.out.print("Enter the number of processes (maximum 10) = ");
        n = s.nextInt();

        burstTime = new int[n];
        waitingTime = new int[n];
        turnaroundTime = new int[n];
        remainingTime = new int[n];

        System.out.print("Enter the burst time of the process\n");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + " = ");
            burstTime[i] = s.nextInt();
            remainingTime[i] = burstTime[i];
        }

        System.out.print("Enter the quantum time: ");
        quantumTime = s.nextInt();

        while (true) {
            for (i = 0, count = 0; i < n; i++) {
                temp = quantumTime;
                if (remainingTime[i] == 0) {
                    count++;
                    continue;
                }
                if (remainingTime[i] > quantumTime)
                    remainingTime[i] -= quantumTime;
                else if (remainingTime[i] > 0) {
                    temp = remainingTime[i];
                    remainingTime[i] = 0;
                }
                currentTime += temp;
                turnaroundTime[i] = currentTime;
            }
            if (n == count)
                break;
        }
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Process\tBurst Time\tTurnaround Time\tWaiting Time");
        System.out.println("--------------------------------------------------------------------------------");

        for (i = 0; i < n; i++) {
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            System.out.println(" " + (i + 1) + "\t\t" + burstTime[i] + "\t\t" + turnaroundTime[i] + "\t\t"
                    + waitingTime[i]);
        }

        double averageWaitingTime = 0;
        double averageTurnaroundTime = 0;

        for (i = 0; i < n; i++) {
            averageWaitingTime += waitingTime[i];
            averageTurnaroundTime += turnaroundTime[i];
        }

        averageWaitingTime /= n;
        averageTurnaroundTime /= n;

        System.out.println("\nAverage Waiting Time = " + averageWaitingTime + "\n");
        System.out.println("Average Turnaround Time = " + averageTurnaroundTime);
    }
}

