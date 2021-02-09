package PathPlanning;

import java.util.*;


public class DepthFirstSearch {

    public static <T extends Graph.GraphNode> ArrayList<T> performSearch(T start, T end, Graph.Graph map){
        Stack<T> frontier = new Stack<>();
        Stack<ArrayList<T>> pathStack = new Stack<>();
        Set<T> visited = new HashSet<>();

        //Initialize frontier with start node
        frontier.push(start);
        ArrayList<T> pathInit = new ArrayList<>();
        pathInit.add(start);
        pathStack.push(pathInit);

        while(!frontier.empty()){
            ArrayList<T> currPath = pathStack.pop();
            T currNode = frontier.pop();
            visited.add(currNode);
            List<T> connectedNodes = currNode.getConnections();
            for (T node:connectedNodes){
                if (!visited.contains(node)){
                    ArrayList<T> tempPath =  new ArrayList(currPath);
                    tempPath.add(node);

                    if (node == end){
                        return tempPath;
                    }else{
                        frontier.push(node);
                        pathStack.push(tempPath);
                    }
                }
            }
        }
        //In the event where no path is found return an empty path
        return new ArrayList<>();
    }

}
