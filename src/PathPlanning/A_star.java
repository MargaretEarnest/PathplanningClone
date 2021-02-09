package PathPlanning;

import Graph.GraphNode;
import com.sun.org.apache.xpath.internal.operations.Variable;

import java.util.*;

public class A_star {
    public static <T extends Graph.GraphNode> List<T> performSearch(T start, T end, Graph.Graph map){

        PriorityQueue<PathNode> frontier = new PriorityQueue<>();
        //all visited nodes-- stored in a Pathnode with the node, the last node visited, and the cost to it
        //access the pathnode by the ID of the current node
        HashMap<String,PathNode> visited = new HashMap<String,PathNode>();
        //start from first node
        PathNode<T> first = new PathNode<>(start, null, 0.0);
        frontier.add(first);
        visited.put(start.getID(), first);

        double priority = 0.0;

        PathNode current = first;
        T currentNode = start;

        while (!frontier.isEmpty()){
            current = frontier.poll();
            currentNode = (T)current.getNode();

            //if node found
            if (currentNode.equals(end)){
               // System.out.println("Found!");
                break;
            }

            List<T> connectedNodes = currentNode.getConnections();
            for (T next:connectedNodes){
                double newCost = current.getPriority() + computeDistance(currentNode, next);
                if (!visited.containsKey(next.getID()) || newCost < visited.get(next.getID()).getPriority()){
                    //System.out.println("Visiting node" + next.getID());
                    //make the PathNode that stores where it came from & its cost; add it to visited nodes
                    PathNode<T> newPath = new PathNode<T>(next, currentNode, newCost);
                    visited.put(next.getID(), newPath);

                    priority = newCost + computeDistance(end, next);
                    frontier.add(new PathNode<>(next, currentNode, priority));
                }
            }
        }

        List<T> path = new ArrayList<>();
        //reverse through cameFrom to get the properly ordered path from start to end
        while (current.getPrevious() != null){
            //System.out.println("Adding " + currentNode.getID() + " to path...");
            path.add(currentNode);

            current = visited.get(current.getPrevious().getID());
            currentNode = (T)current.getNode();
        }
        path.add(currentNode);

        Collections.reverse(path);
        return path;
    }

    private static <T extends Graph.GraphNode> double computeDistance(T a, T b){
        double distance,xDiff,yDiff = 0;
        xDiff = Math.abs(a.getXcoord() - b.getXcoord());
        yDiff = Math.abs(a.getYcoord() - b.getYcoord());

        distance = Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));

        return distance;
    }
}
