package PathPlanning;

public class PathNode<T extends Graph.GraphNode> implements Comparable<PathNode> {
    private final T node;
    private T previous;
    private double priority;

    PathNode(T current){
        this(current, null, Double.POSITIVE_INFINITY);
    }

    PathNode(T current, T previous, double score){
        this.node = current;
        this.previous = previous;
        this.priority = score;
    }

    public T getNode() {
        return node;
    }
    public T getPrevious() {
        return previous;
    }
    public double getPriority() {
        return priority;
    }

    public int compareTo(PathNode other){
        if (other.priority == this.priority){
            return 0;
        }else if(other.priority < this.priority){
            return 1;
        }else{
            return 1;
        }
    }
}
