import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DfsTopologicalSort {

	public static class Graph {
		
		int vertices;
		ArrayList<Integer>[] adjacencyList;
		boolean visited[];
		int parent[];
		boolean isCycle = false;

		
		public Graph(int vertices) {
			this.vertices = vertices;
			adjacencyList = new ArrayList[vertices];
			visited = new boolean[vertices];
			parent = new int[vertices];
			
			for (int vertex = 0; vertex < vertices; vertex++) {
				adjacencyList[vertex] = new ArrayList<Integer>();
				visited[vertex] = false;				
			}
		}

		public void add(int source, int destination) {
			adjacencyList[source].add(destination);
		}

		public void DFStopologicalSort() {

			Stack<Integer> completedList = new Stack<Integer>();

			for (int i = 0; i < vertices; i++)
				if (visited[i] == false)
					topologicalSort(i, visited, completedList);
			
			if (!isCycle) {
				printDAG();
				System.out.print("Topological Sort : ");
				while (completedList.empty() == false)
					System.out.print(completedList.pop() + " ");
				System.out.println();
			}

		}

		public void topologicalSort(int vertex, boolean visted[], Stack<Integer> completedList) {
			if (!isCycle) {
				visited[vertex] = true;
				Integer i;

				Iterator<Integer> it = adjacencyList[vertex].iterator();

				while (it.hasNext()) {
					i = it.next();
					parent[i] = vertex;

					if (!visited[i])
						topologicalSort(i, visited, completedList);
					else if (!completedList.contains(i)) {
						isCycle = true;
						System.out.print("Cycle Detected at: ");
						printCycle(i);
						return;
					}
				}

				completedList.push(new Integer(vertex));
			}
		}

		public void printCycle(int vertex) {
			Stack<Integer> cycle = new Stack<Integer>();
			while (!cycle.contains(parent[vertex])) {
				cycle.add(parent[vertex]);
				vertex = parent[vertex];
			}

			while (!cycle.isEmpty()) {
				System.out.print(cycle.pop() + "-->");
			}
			System.out.println(vertex);
		}

		public void printDAG() {
			for (int i = 0; i < vertices; i++) {
				System.out.print("Edges from vertex-" + i + ":");
				Iterator<Integer> it = adjacencyList[i].iterator();
				if (!it.hasNext()) {
					System.out.print(" No edges");
				}
				while (it.hasNext()) {
					System.out.print(" " + it.next());
				}
				System.out.println();

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph acyclicGraph = new Graph(11);
		acyclicGraph.add(0, 1);
		acyclicGraph.add(0, 6);
		acyclicGraph.add(0, 8);
		acyclicGraph.add(1, 6);
		acyclicGraph.add(1, 5);
		acyclicGraph.add(2, 3);
		acyclicGraph.add(3, 9);
		acyclicGraph.add(4, 0);
		acyclicGraph.add(4, 2);
		acyclicGraph.add(4, 3);
		acyclicGraph.add(5, 7);
		acyclicGraph.add(5, 10);
		acyclicGraph.add(6, 5);
		acyclicGraph.add(7, 9);
		acyclicGraph.add(8, 3);
		acyclicGraph.add(8, 7);
		System.out.println("Graph1:");
		acyclicGraph.DFStopologicalSort();

		Graph cyclicGraph = new Graph(11);
		cyclicGraph.add(0, 1);
		cyclicGraph.add(0, 6);
		cyclicGraph.add(0, 8);
		cyclicGraph.add(1, 6);
		cyclicGraph.add(1, 5);
		cyclicGraph.add(2, 3);
		cyclicGraph.add(3, 9);
		cyclicGraph.add(4, 0);
		cyclicGraph.add(4, 2);
		cyclicGraph.add(3, 4);
		cyclicGraph.add(5, 7);
		cyclicGraph.add(5, 10);
		cyclicGraph.add(6, 5);
		cyclicGraph.add(7, 9);
		cyclicGraph.add(8, 3);
		cyclicGraph.add(8, 7);
		System.out.println("Graph2:");
		cyclicGraph.DFStopologicalSort();

	}
}
