package PathPlanning;

import Graph.GraphNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class A_star {
    public static <T extends Graph.GraphNode> List<T> performSearch(T start, T end, Graph.Graph map){
        PriorityQueue<PathNode> frontier = new PriorityQueue<>();

        frontier.add(new PathNode<>(start, null, 0.0));

        while (!frontier.isEmpty()){
            PathNode current = frontier.poll();
            T currentNode = current.getNode();

            //if node found
            if (currentNode.equals(end)){
                break;
            }

            for (Object next:currentNode.getConnections()){
                double new_cost = current.getPriority() + A_star.computeDistance(currentNode, next);
            }
        }
        //In the event where no path is found return an empty path
    }

    private static <T extends Graph.GraphNode> double computeDistance(T a, T b){
        double distance,xDiff,yDiff = 0;
        xDiff = Math.abs(a.xcoord - b.xcoord);
        yDiff = Math.abs(a.ycoord - b.ycoord);

        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance;
    }
}
