package ithorian;

public class MapEditor {
	void addNode(HospitalMap.Node node) {

	}
	void removeNode(HospitalMap.Node node) {
		for(HospitalMap.Node neighbor : node.connectedNodes) {
			neighbor.connectedNodes.remove(node);
		}
	}
	void edit() {}
}
