package search;

import java.util.ArrayList;
import java.util.List;

public class Node {
    protected List<Node> neighbors = new ArrayList<>();
    protected boolean visited;

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void visit(){
        visited = true;
    }

    public void connect(Node other){
        this.neighbors.add(other);
        other.neighbors.add(this);
    }
}
