public class Main {
    public static void main(String[] args) {
        Map.generateElementFromData(ReadCSV.readFromFile("src/testmapNodes.csv"), Map.Element.Node);
        Map.generateElementFromData(ReadCSV.readFromFile("src/testmapEdges.csv"), Map.Element.Edge);

        System.out.println(Map.nodes);
        System.out.println(Map.edges);
    }
}
