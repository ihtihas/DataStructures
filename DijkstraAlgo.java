import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraAlgo {

	static ArrayList<Integer> completed;
	static PriorityQueue<Node> priorityQueue;
	static int[] distance;

	/*
	 * Edge details:source vertex, destination vertex and weight
	 * maintain adjacency list
	 */

	public static class Edge {

		int sourceVertex, destinationVertex, weight;

		public Edge(int sourceVertex, int destinationVertex, int weight) {

			this.sourceVertex = sourceVertex;
			this.destinationVertex = destinationVertex;
			this.weight = weight;
		}

	}

	/*
	 * Maintains distance of each vertex from initial source 
	 * Comparator in Priority Queue
	 */
	public static class Node implements Comparator<Node> {

		int vertex, weight;

		public Node() {

		}

		public Node(int vertex, int weight) {

			this.vertex = vertex;
			this.weight = weight;
		}

		public int getVertex() {
			return vertex;
		}

		@Override
		public int compare(Node n1, Node n2) {
			// TODO Auto-generated method stub
			if (n1.weight < n2.weight)
				return -1;
			if (n1.weight > n2.weight)
				return 1;
			return 0;
		}

	}

	public static class Graph {

		int vertices;

		ArrayList<Edge>[] adjacencyList;

		public Graph(int vertices) {

			this.vertices = vertices;
			adjacencyList = new ArrayList[vertices];

			for (int vertex = 0; vertex < vertices; vertex++) {
				adjacencyList[vertex] = new ArrayList<Edge>();
			}

		}

		public void add(int sourceVertex, int destinationVertex, int weight) {
			Edge edge = new Edge(sourceVertex, destinationVertex, weight);
			adjacencyList[sourceVertex].add(edge);
			edge = new Edge(destinationVertex, sourceVertex, weight);
			adjacencyList[destinationVertex].add(edge);
		}
	}

	public static void getShortestPath(Graph graph, int start) {

		/*
		 * Maintain a list of distances of all vertices from start and initialize to
		 * infinity
		 */
		distance = new int[graph.vertices];
		for (int i = 0; i < graph.vertices; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		
		distance[start] = 0; // initialize start vertex distance to 0
		Node startVertex = new Node(start, distance[start]);
		priorityQueue = new PriorityQueue<Node>(graph.vertices, new Node());
		priorityQueue.add(startVertex);
		completed = new ArrayList<Integer>();

		//loop through all the incident Edges
		while (!priorityQueue.isEmpty()) { 

			Node node = priorityQueue.poll();
			int vertex = node.getVertex();
			completed.add(vertex);

			ArrayList<Edge> list = graph.adjacencyList[vertex];

			for (int i = 0; i < list.size(); i++) {
				Edge edge = list.get(i);
				int destination = edge.destinationVertex;

				if (!completed.contains(destination)) {

					int currentDistance = distance[destination];
					int newDistance = distance[vertex] + edge.weight;

					if (currentDistance > newDistance) {
						Node p = new Node(destination, newDistance);
						priorityQueue.add(p);
						distance[destination] = newDistance;
					}

				}
			}

		}

	}

	public static void printDistances(Graph graph, int sourceVertex) {
		for (int i = 0; i < graph.vertices; i++) {
			System.out.println("From vertex:" + sourceVertex + " To vertex:" + i + " Distance:" + distance[i]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int vertices = 10;
		Graph graph = new Graph(vertices);
		
		graph.add(0, 1, 2);
		graph.add(0, 2, 3);
		graph.add(1, 3, 4);
		graph.add(1, 5, 5);
		graph.add(1, 6, 6);
		graph.add(2, 4, 1);
		graph.add(2, 3, 2);
		graph.add(3, 5, 1);
		graph.add(3, 6, 2);
		graph.add(4, 7, 4);
		graph.add(5, 8, 8);
		graph.add(5, 9, 4);
		graph.add(6, 9, 3);
		graph.add(4, 9, 1);
		graph.add(7, 3, 1);
		graph.add(7, 9, 2);

		DijkstraAlgo.getShortestPath(graph, 0);
		DijkstraAlgo.printDistances(graph, 0);

	}

}
