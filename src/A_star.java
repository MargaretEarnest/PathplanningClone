import java.util.ArrayList;
import java.util.PriorityQueue;

public class A_star {
    public static ArrayList<HospitalMap.Node> performSearch(HospitalMap.Node start, HospitalMap.Node end, HospitalMap map){
        PriorityQueue<HospitalMap.Node> frontier = new PriorityQueue<>();
        frontier.add(start);

        //In the event where no path is found return an empty path
        return new ArrayList<>();
    }

    private static double computeDistance(HospitalMap.Node a, HospitalMap.Node b){
        double distance,xDiff,yDiff = 0;
        xDiff = Math.abs(a.xcoord - b.xcoord);
        yDiff = Math.abs(a.ycoord  - b.ycoord);

        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance;
    }
}
