package Graph;

import java.util.Set;

public interface GraphNode<T extends GraphNode<T>> {
    String getID();

    Set<T> getConnections();
}
