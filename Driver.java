import com.sun.org.apache.xpath.internal.SourceTree;

public class Driver {
    public static void main(String... strings) {
        AdjacencyMapGraph adjacencyMapGraph = new AdjacencyMapGraph(true);
        Vertex v10 = adjacencyMapGraph.insertVertex(10);
        Vertex v9 = adjacencyMapGraph.insertVertex(9);
        Vertex v8 = adjacencyMapGraph.insertVertex(8);
        Vertex v7 =adjacencyMapGraph.insertVertex(7);
        Vertex v6 =adjacencyMapGraph.insertVertex(6);
        Vertex v5 = adjacencyMapGraph.insertVertex(5);
        Vertex v4=adjacencyMapGraph.insertVertex(4);
        Vertex v3=adjacencyMapGraph.insertVertex(3);
        Vertex v2=adjacencyMapGraph.insertVertex(2);
        Vertex v1=adjacencyMapGraph.insertVertex(1);
        adjacencyMapGraph.insertEdge(v1,v2 , "bağlı edge 1");
        adjacencyMapGraph.insertEdge(v2,v3 , "bağlı edge 2");
        adjacencyMapGraph.insertEdge(v3,v4 , "bağlı edge 3");
        adjacencyMapGraph.insertEdge(v4,v5 , "bağlı edge 4");
        adjacencyMapGraph.insertEdge(v5,v6 , "bağlı edge 5");
        adjacencyMapGraph.insertEdge(v6,v7 , "bağlı edge 6");
        adjacencyMapGraph.insertEdge(v7,v8 , "bağlı edge 7");
        adjacencyMapGraph.insertEdge(v8,v9 , "bağlı edge 8");
        Edge ed = adjacencyMapGraph.insertEdge(v9, v10, "bağlı edge 9");
        //Edge ed2 = adjacencyMapGraph.insertEdge(v10, v1, "bağlı edge 10");

        System.out.println(adjacencyMapGraph.toString());
        System.out.println("----------------------------");
        for (Object o : adjacencyMapGraph.vertices()) {
            System.out.println("Vertex  " + ((Vertex) o).getElement());
            //     System.out.println("Outgoing Edge   "+ adjacencyMapGraph.outgoingEdges((Vertex)o));
        }
        System.out.println("----------------------------");
        for (Object edge : adjacencyMapGraph.edges())
            System.out.println("edge    " + ((Edge) edge).getElement());
        System.out.println("----------------------------");
        System.out.println("opposite of " + v9.getElement() + " is " + adjacencyMapGraph.opposite(v9, ed).getElement());

        System.out.println("----------------------------");
        adjacencyMapGraph.removeEdge(ed);
        adjacencyMapGraph.removeVertex(v2);
        System.out.println(adjacencyMapGraph.toString());
        System.out.println("----------------------------");

       GraphAlgorithms.DFS(adjacencyMapGraph, v1, adjacencyMapGraph.set, GraphAlgorithms.DFSComplete(adjacencyMapGraph));
        Map<Vertex<Integer>, Edge<String>> map = GraphAlgorithms.DFSComplete(adjacencyMapGraph);
        for (Entry<Vertex<Integer>, Edge<String>> entry : map.entrySet())
            System.out.println("vertex " + entry.getKey().getElement() + " " + entry.getValue().getElement() + " " + adjacencyMapGraph.opposite(entry.getKey(), entry.getValue()).getElement());



        for (Object edges :GraphAlgorithms.findPath(adjacencyMapGraph,v4,v9)) {
            System.out.println("PATH FROM "+v4.getElement()+" "+((Edge<String>)edges).getElement()+ " to "+v9.getElement());
        }

    }
}