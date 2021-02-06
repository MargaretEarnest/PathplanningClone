import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

	// Lists that hold arrays of map nodes and edges based on csv input
	static List<MapNode> mapNodes = new ArrayList<>();
	static List<MapEdge> mapEdges = new ArrayList<>();

	public static void main(String[] args) {
		readFromFile("src/MapPFaulkner1Nodes.csv", MapElement.Node);
		readFromFile("src/MapPFaulkner1Edges.csv", MapElement.Edge);
		System.out.println(mapNodes);
		System.out.println(mapEdges);
	}

	private static void readFromFile(String path, MapElement element) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			boolean skip = true;
			while ((line = br.readLine()) != null) {
				if (skip) {
					skip = false;
				} else {
					String[] values = line.split(",");
					if(element == MapElement.Node) {
						mapNodes.add(new MapNode(Arrays.asList(values)));
					} else {
						mapEdges.add(new MapEdge(Arrays.asList(values)));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private enum MapElement { Node, Edge }

	private static class MapNode {
		private final String id, building, nodeType, longname, shortname, teamassigned;
		private final int xcoord, yfloor, floor;

		private MapNode(List nodeInit) {
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

	private static class MapEdge {
		private final String id, startNode, endNode;

		private MapEdge(List nodeInit) {
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
