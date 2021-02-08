import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class HospitalMap {

    // Lists that hold arrays of map nodes and edges based on csv input
    static ArrayList<Node> nodes = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>(); //potentially not needed as a global variable here
    static HashMap<String, Node> nodesHash = new HashMap<String, Node>();



    public enum Element {Node, Edge}

    public static void generateElementFromData(List<List<String>> nodesList, List<List<String>> edgesList) {
        for (List<String> values : nodesList) {
            Node currNode = new Node(values);
            nodes.add(currNode);
            nodesHash.put(currNode.id, currNode);
        }

        //iterates through edges and connects respective nodes
        for (List<String> values : edgesList) {
            Edge currEdge = new Edge(values);
            Node a = nodesHash.get(currEdge.startNode);
            Node b = nodesHash.get(currEdge.endNode);
            a.connectedNodes.add(b);
            b.connectedNodes.add(a);
        }
    }

    public static class Node {
        public final String id, building, nodeType, longname, shortname, teamassigned;
        public ArrayList<Node> connectedNodes = new ArrayList<>();
        public final int xcoord, ycoord, floor;

        private Node(List nodeInit) {
            this.id = (String) nodeInit.get(0);
            this.xcoord = Integer.parseInt((String) nodeInit.get(1));
            this.ycoord = Integer.parseInt((String) nodeInit.get(2));
            this.floor = Integer.parseInt((String) nodeInit.get(3));
            this.building = (String) nodeInit.get(4);
            this.nodeType = (String) nodeInit.get(5);
            this.longname = (String) nodeInit.get(6);
            this.shortname = (String) nodeInit.get(7);
            this.teamassigned = (String) nodeInit.get(8);
        }

        @Override
        public String toString() {
            return "MapNode{" +
                    "id='" + id + '\'' +
                    ", building='" + building + '\'' +
                    ", nodeType='" + nodeType + '\'' +
                    ", longname='" + longname + '\'' +
                    ", shortname='" + shortname + '\'' +
                    ", teamassigned='" + teamassigned + '\'' +
                    ", xcoord=" + xcoord +
                    ", ycoord=" + ycoord +
                    ", floor=" + floor +
                    '}';
        }
    }

    public static class Edge {
        public final String id, startNode, endNode;

        private Edge(List nodeInit) {
            this.id = (String) nodeInit.get(0);
            this.startNode = (String) nodeInit.get(1);
            this.endNode = (String) nodeInit.get(2);
        }

        @Override
        public String toString() {
            return "MapEdge{" +
                    "startNode='" + startNode + '\'' +
                    ", endNode='" + endNode + '\'' +
                    ", id=" + id +
                    '}';
        }
    }


    /**
     * nodeToCSV converts list of HospitalMap Nodes into a CSV file
     *
     * @param
     * @param nodes
     * @return nodeCSV
     */
    public static StringBuilder nodeToCSV(List<Node> nodes) {
        StringBuilder nodeCSV = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {

            nodeCSV = nodeCSV.append(nodes.get(i).id + " , " + nodes.get(i).building + " , " + nodes.get(i).nodeType + " , " + nodes.get(i).longname + " , " + nodes.get(i).shortname + " , " + nodes.get(i).teamassigned + " , " + nodes.get(i).xcoord + " , " + nodes.get(i).ycoord + " , " + nodes.get(i).floor + '\n');

            //System.out.println(nodeCSV);

        }
        return nodeCSV;

    }

    /**
     * edgeToCSV converts list of HospitalMap nodes into a CSV file
     *
     * @param edges
     * @return edgeCSV
     */
    public static StringBuilder edgeToCSV(List<Edge> edges) {
        StringBuilder edgeCSV = new StringBuilder();
        for (int i = 0; i < edges.size(); i++) {

            edgeCSV = edgeCSV.append(edges.get(i).startNode + " , " + edges.get(i).endNode + " , " + edges.get(i).id + " , " +  '\n');
            System.out.println(edgeCSV);


        }
        return edgeCSV;

    }
}
