import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LRU {
    public static void main(String[] args) {
        // Example usage
        int[] pageRequests = {1, 2, 3, 4, 5, 2, 1, 6, 2, 1, 2, 3, 7, 8, 1};
        int frameSize = 3;

        simulateLRU(pageRequests, frameSize);
    }

    private static void simulateLRU(int[] pageRequests, int frameSize) {
        List<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        for (int i = 0; i < pageRequests.length; i++) {
            int page = pageRequests[i];

            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameSize) {
                    frames.add(page);
                } else {
                    frames.remove(0); // Remove the least recently used page (at the front)
                    frames.add(page);
                }
            } else {
                // If the page is already in frames, move it to the end (most recently used)
                frames.remove((Integer) page);
                frames.add(page);
            }

            // Print the current state of frames after each page request
            System.out.println("Page Request: " + page);
            System.out.println("Frames: " + frames);
            System.out.println("---------------------");
        }
        System.out.println("Total Page Faults: " + pageFaults);
    }
}

