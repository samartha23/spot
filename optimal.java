import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class optimal {
    public static void main(String[] args) {
        // Example usage
        int[] pageRequests = {1, 2, 3, 4, 5, 2, 1, 6, 2, 1, 2, 3, 7, 8, 1};
        int frameSize = 3;

        simulateOptimal(pageRequests, frameSize);
    }

    private static void simulateOptimal(int[] pageRequests, int frameSize) {
        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        for (int i = 0; i < pageRequests.length; i++) {
            int page = pageRequests[i];

            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameSize) {
                    frames.add(page);
                } else {
                    int pageToReplace = findPageToReplace(pageRequests, frames, i + 1);
                    frames.set(frames.indexOf(pageToReplace), page);
                }
            }

            // Print the current state of frames after each page request
            System.out.println("Page Request: " + page);
            System.out.println("Frames: " + frames);
            System.out.println("---------------------");
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }

    private static int findPageToReplace(int[] pageRequests, List<Integer> frames, int startIndex) {
        int farthestIndex = -1;
        int farthestPage = -1;

        for (Integer frame : frames) {
            int index = indexOfPage(pageRequests, frame, startIndex);
            if (index == -1) {
                return frame; // If a page will not be used in the future, replace it
            }
            if (index > farthestIndex) {
                farthestIndex = index;
                farthestPage = frame;
            }
        }

        return farthestPage;
    }

    private static int indexOfPage(int[] pageRequests, int page, int startIndex) {
        for (int i = startIndex; i < pageRequests.length; i++) {
            if (pageRequests[i] == page) {
                return i;
            }
        }
        return -1;
    }
}

