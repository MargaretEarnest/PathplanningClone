import java.util.*;


public class DepthFirstSearch {

    public static ArrayList<HospitalMap.Node> performSearch(HospitalMap.Node start, HospitalMap.Node end, HospitalMap map){
        Stack<HospitalMap.Node> frontier = new Stack<>();
        Stack<ArrayList<HospitalMap.Node>> pathStack = new Stack<>();
        Set<HospitalMap.Node> visited = new HashSet<>();

        //Initialize frontier with start node
        frontier.push(start);
        ArrayList<HospitalMap.Node> pathInit = new ArrayList<>();
        pathInit.add(start);
        pathStack.push(pathInit);

        while(!frontier.empty()){
            ArrayList<HospitalMap.Node> currPath = pathStack.pop();
            HospitalMap.Node currNode = frontier.pop();
            visited.add(currNode);
            for (HospitalMap.Node n:currNode.connectedNodes){
                if (!visited.contains(n)){
                    ArrayList<HospitalMap.Node> tempPath =  new ArrayList(currPath);
                    tempPath.add(n);

                    if (n == end){
                        return tempPath;
                    }else{
                        frontier.push(n);
                        pathStack.push(tempPath);
                    }
                }
            }
        }
        //In the event where no path is found return an empty path
        return new ArrayList<>();
    }

    public static class A_star {
        public static ArrayList<Graph.GraphNode> performSearch(HospitalMap.Node start, HospitalMap.Node end, HospitalMap map){
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
}
