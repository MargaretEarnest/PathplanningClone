import java.util.ArrayList;
import java.util.List;

public class Map {
    // Lists that hold arrays of map nodes and edges based on csv input
    static ArrayList<Node> nodes = new ArrayList<>();
    static ArrayList<Edge> edges = new ArrayList<>();

    public enum Element { Node, Edge }

    public static void generateElementFromData(List<List<String>> data, Element element){
        for (List<String> values:data) {
            if (element == Element.Node)
                nodes.add(new Node(values));
            else
                edges.add(new Edge(values));
        }
    }

    public static class Node {
        public final String id, building, nodeType, longname, shortname, teamassigned;
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

}
