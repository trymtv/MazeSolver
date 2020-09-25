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

    /**
     * Connecting two nodes of the same type
     * @param other - the node to connect to
     *
     * @throws IllegalArgumentException - the two nodes that connects are not the same type
     */
    public void connect(Node other){
        if(this.getClass().equals(other.getClass())){
            this.neighbors.add(other);
            other.neighbors.add(this);
        }
        else{
            throw new IllegalArgumentException("Can't connect two different type of nodes.");
        }
    }
    public void disconnect(Node other){
        this.neighbors.remove(other);
        other.neighbors.remove(this);
    }
}
