package PathPlanning;

import Graph.GraphNode;

import java.util.*;

public class A_star {
    public static <T extends Graph.GraphNode> List<T> performSearch(T start, T end, Graph.Graph map){
        List<T> path = new ArrayList<>();

        PriorityQueue<PathNode> frontier = new PriorityQueue<>();
        frontier.add(new PathNode<>(start, null, 0.0));

        HashMap<T, T> cameFrom = new HashMap<T, T>();
        HashMap<T, Double> costSoFar = new HashMap<T, Double>();
        cameFrom.put(start, null);
        costSoFar.put(start, 0.0);
        double priority = 0.0;

        PathNode current;
        T currentNode;

        while (!frontier.isEmpty()){
            current = frontier.poll();
            currentNode = current.getNode();

            //if node found
            if (currentNode.equals(end)){
                break;
            }

//            for (Object next:currentNode.getConnections()){

//            Set<T> c = currentNode.getConnections();
//            for (Iterator<T> next = c.iterator(); next.hasNext();){
//                double newCost = current.getPriority() + A_star.computeDistance(currentNode, next);
//            }

//            List<T> connectedNodes = currentNode.getConnections();
//            for (T next:connectedNodes){

            List<T> connectedNodes = currentNode.getConnections();
            for (T next:connectedNodes){
                double newCost = current.getPriority() + computeDistance(currentNode, next);

                if (costSoFar.containsKey(next) || newCost < costSoFar.get(next)){
                    costSoFar.put(next, newCost);
                    priority = newCost + computeDistance(end, next);
                    frontier.add(new PathNode<>(next, currentNode, priority));
                    cameFrom.put(next, currentNode);
                }
            }
        }

        //reverse through cameFrom to get the properly ordered path from start to end
        while (cameFrom.get(currentNode) != null){
            path.add(0,currentNode);
            currentNode = cameFrom.get(currentNode);
        }

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
