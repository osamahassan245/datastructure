package graph;

public class AdjacencyMatrix {

	private int numOfNodes;
	private boolean directed;
	private boolean weighted;
	private float[][] matrix;

	/*
	 * This will allow us to safely add weighted graphs in our class since we will
	 * be able to check whether an edge exists without relying on specific special
	 * values (like 0)
	 */
	private boolean[][] isSetMatrix;
	
	public AdjacencyMatrix(int numOfNodes, boolean directed, boolean weighted) {

	    this.directed = directed;
	    this.weighted = weighted;
	    this.numOfNodes = numOfNodes;

	    // Simply initializes our adjacency matrix to the appropriate size
	    matrix = new float[numOfNodes][numOfNodes];
	    isSetMatrix = new boolean[numOfNodes][numOfNodes];
	}

	public void addEdge(int source, int destination) {

	    int valueToAdd = 1;

	    if (weighted) {
	        valueToAdd = 0;
	    }

	    matrix[source][destination] = valueToAdd;
	    isSetMatrix[source][destination] = true;

	    if (!directed) {
	        matrix[destination][source] = valueToAdd;
	        isSetMatrix[destination][source] = true;
	    }
	}
	
	public void addEdge(int source, int destination, float weight) {

	    float valueToAdd = weight;

	    if (!weighted) {
	        valueToAdd = 1;
	    }

	    matrix[source][destination] = valueToAdd;
	    isSetMatrix[source][destination] = true;

	    if (!directed) {
	        matrix[destination][source] = valueToAdd;
	        isSetMatrix[destination][source] = true;
	    }
	}
	
	public void printMatrix() {
	    for (int i = 0; i < numOfNodes; i++) {
	        for (int j = 0; j < numOfNodes; j++) {
	            // We only want to print the values of those positions that have been marked as set
	            if (isSetMatrix[i][j])
	                System.out.format("%8s", String.valueOf(matrix[i][j]));
	            else System.out.format("%8s", "/  ");
	        }
	        System.out.println();
	    }
	}
	
	/*
	 We look at each row, one by one.
	 When we're at row i, every column j that has a set value represents that an edge exists from
	 i to j, so we print it
	*/
	public void printEdges() {
	    for (int i = 0; i < numOfNodes; i++) {
	        System.out.print("Node " + i + " is connected to: ");
	        for (int j = 0; j < numOfNodes; j++) {
	            if (isSetMatrix[i][j]) {
	                System.out.print(j + " ");
	            }
	        }
	        System.out.println();
	    }
	}
	
	public boolean hasEdge(int source, int destination) {
	    return isSetMatrix[source][destination];
	}

	public Float getEdgeValue(int source, int destination) {
	    if (!weighted || !isSetMatrix[source][destination])
	        return null;
	    return matrix[source][destination];
	}
	
	public static void main(String[] args) {
		  // Graph(numOfNodes, directed, weighted)
        AdjacencyMatrix graph = new AdjacencyMatrix(5, false, true);

        graph.addEdge(0, 2, 19);
        graph.addEdge(0, 3, -2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3); // The default weight is 0 if weighted == true
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        graph.printMatrix();

        System.out.println();
        System.out.println();

        graph.printEdges();

        System.out.println();
        System.out.println("Does an edge from 1 to 0 exist?");
        if (graph.hasEdge(0,1)) {
            System.out.println("Yes");
        }
        else System.out.println("No");
	}
}
