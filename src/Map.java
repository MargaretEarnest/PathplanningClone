import java.util.ArrayList;
import java.util.List;

public class Map {
    // Lists that hold arrays of map nodes and edges based on csv input
    static List<Node> nodes = new ArrayList<>();
    static List<Edge> edges = new ArrayList<>();

    public enum Element { Node, Edge }

    public static void generateElementFromData(List<List<String>> data, Element element){
        for (List<String> values:data) {
            if (element == Element.Node)
                nodes.add(new Node(values));
            else
                edges.add(new Edge(values));
        }
    }

    private static class Node {
        private final String id, building, nodeType, longname, shortname, teamassigned;
        private final int xcoord, yfloor, floor;

        private Node(List nodeInit) {
            this.id = (String) nodeInit.get(0);
            this.xcoord = Integer.parseInt((String) nodeInit.get(1));
            this.yfloor = Integer.parseInt((String) nodeInit.get(2));
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
                    ", yfloor=" + yfloor +
                    ", floor=" + floor +
                    '}';
        }
    }

    private static class Edge {
        private final String id, startNode, endNode;

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
