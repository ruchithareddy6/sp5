/** Starter code for SP5
 *  @author rbk
 */

// change to your netid
package rxp200069;

import idsa.Graph;
import idsa.Graph.Vertex;
import idsa.Graph.GraphAlgorithm;
import idsa.Graph.Factory;

import java.io.File;
import java.util.*;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {
    public static class DFSVertex implements Factory {
	int cno;
	public DFSVertex(Vertex u) {
	}
	public DFSVertex make(Vertex u) { return new DFSVertex(u); }
    }

    public DFS(Graph g) {
	super(g, new DFSVertex(null));
    }

    public static DFS depthFirstSearch(Graph g) {
	return null;
    }

    // Member function to find topological order
    public List<Vertex> topologicalOrder1() {

        Boolean isAcylic=true;
        if(isCyclic()){
            isAcylic=false;
        }
        List<Vertex> topologicalOrder = new LinkedList<>();
        if(isAcylic){
            HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
            Vertex[] vertexArray= this.g.getVertexArray();
            Stack<Vertex> stack = new Stack<Vertex>();
            for(int i=0;i<vertexArray.length;i++) visited.put(vertexArray[i], false);

            for (int i = 0; i < vertexArray.length; i++)
                if (visited.get(vertexArray[i]) == false)
                    topologicalOrderingHelper(vertexArray[i], visited, stack);

            while (stack.empty() == false)
                topologicalOrder.add(stack.pop());

            return  topologicalOrder;
        }
        return null;
    }
    boolean isCyclic(){
        HashMap<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
        HashMap<Vertex, Boolean> recStack = new HashMap<Vertex, Boolean>();

        Vertex[] vertexArray= this.g.getVertexArray();

        for(int i=0;i<vertexArray.length;i++) visited.put(vertexArray[i], false);
        for(int i=0;i<vertexArray.length;i++) recStack .put(vertexArray[i], false);

        for (int i = 0; i < vertexArray.length; i++)
            if (isCyclicUtil(vertexArray[i], visited, recStack))
                return true;

        return false;
    }

    private boolean isCyclicUtil(Vertex i, HashMap<Vertex, Boolean> visited, HashMap<Vertex, Boolean> recStack)
    {

        if (recStack.get(i))
            return true;

        if (visited.get(i))
            return false;

        visited.put(i, true);
        recStack.put(i, true);

        Iterator<Graph.Edge> outE= this.g.outEdges(i).iterator();
        Vertex v;
        while (outE.hasNext()) {
            v = outE.next().toVertex();
            if (isCyclicUtil(v, visited, recStack))
                return true;
        }
        recStack.put(i, false);

        return false;
    }

    void topologicalOrderingHelper(Vertex v, HashMap<Vertex, Boolean> visited, Stack<Vertex> stack) {
        visited.put(v,true);
        Vertex i;
        Iterator<Graph.Edge> outE= this.g.outEdges(v).iterator();
        while (outE.hasNext()) {
            i = outE.next().toVertex();
            if (!visited.get(i))
                topologicalOrderingHelper(i, visited, stack);
        }
        stack.push(v);
    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
	return 0;
    }

    // After running the connected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
	return get(u).cno;
    }
    
    // Find topological oder of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {

	DFS d = new DFS(g);
	return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
	return null;
    }

    public static void main(String[] args) throws Exception {
        String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
	    Scanner in;
	// If there is a command line argument, use it as file from which
	// input is read, otherwise use input from string.
	    in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
	
	// Read graph from input
        Graph g = Graph.readGraph(in, true);
	    g.printGraph(false);

        System.out.println(topologicalOrder1(g));
	// print the vertices in topological order
	// write the code
	

	
    }
}