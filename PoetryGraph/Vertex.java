package assignment4;

import java.util.HashMap;
import java.util.Map;

public class Vertex<T>{
    private T name;
    private Map<T, Integer> edges; //T is vetex, INTEGER is weight


    public Vertex(T name){
        this.name =name;
        this.edges = new HashMap<>();
    }


    public T getName(){
        return name;
    }
    public Map<T, Integer> getEdges(){
        return edges;
    }


    public void addEdge(T vertex, int weight){
        edges.put(vertex, weight);
    }


}
