import java.util.ArrayDeque;
import java.util.Queue;

public class FIFOCache {
    public static void main(String[] args) {
        // Example usage
        int[] pageRequests = {1, 2, 3, 4, 5, 2, 1, 6, 2, 1, 2, 3, 7, 8, 1};
        int frameSize = 3;

        simulateFIFO(pageRequests, frameSize);
    }

    private static void simulateFIFO(int[] pageRequests, int frameSize) {
        Queue<Integer> frames = new ArrayDeque<>(frameSize);
        int pageFault = 0;

        for (int page : pageRequests) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() >= frameSize) {
                    frames.poll(); // Remove the oldest page (at the front)
                }
                frames.offer(page); // Add the new page to the back
            }

            // Print the current state of frames after each page request
            System.out.println("Page Request: " + page);
            System.out.println("Frames: " + frames);
            System.out.println("---------------------");
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }
}
	
