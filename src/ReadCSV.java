import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadCSV {

	static <T> List<T> readFromFile(String path, Class<T> element) {
		List<T> returnList = new ArrayList<T>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			boolean skip = true;
			while ((line = br.readLine()) != null) {
				if (skip) {
					skip = false;
				} else {
					String[] values = line.split(",");
					if(element == MapNode.class) {
						returnList.add((T) new MapNode(Arrays.asList(values)));
					} else {
						returnList.add((T) new MapEdge(Arrays.asList(values)));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnList;
	}

	private enum MapElement { Node, Edge }

	static class MapNode {
		protected final String id, building, nodeType, longname, shortname, teamassigned;
		protected final int xcoord, yfloor, floor;

		private MapNode(List<String> nodeInit) {
			this.id = nodeInit.get(0);
			this.xcoord = Integer.parseInt(nodeInit.get(1));
			this.yfloor = Integer.parseInt(nodeInit.get(2));
			this.floor = Integer.parseInt(nodeInit.get(3));
			this.building = nodeInit.get(4);
			this.nodeType = nodeInit.get(5);
			this.longname = nodeInit.get(6);
			this.shortname = nodeInit.get(7);
			this.teamassigned = nodeInit.get(8);
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

	static class MapEdge {
		protected final String id, startNode, endNode;

		private MapEdge(List<String> nodeInit) {
			this.id = nodeInit.get(0);
			this.startNode = nodeInit.get(1);
			this.endNode = nodeInit.get(2);
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
