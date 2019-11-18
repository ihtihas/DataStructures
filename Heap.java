import java.util.Random;

/**
 * 
 */

/**
 * @author Sahithi
 *
 */
public class Heap {

	// Get Left Child Index
	public static int left(int i) {
		return 2 * i;
	}

	// Get Right child Index
	public static int right(int i) {
		return 2 * i + 1;
	}

	// Build Max Heap
	public static void build_heap(int[] list) {

		int size = list[0];

		for (int i = size / 2; i >= 1; i--) {// From the last level above the leaf nodes

			max_heapify(list, i, size);
		}

	}

	public static void max_heapify(int[] list, int node, int size) {
		int left = left(node);
		int right = right(node);
		int largest = node;
		if (left <= size && list[left] > list[node]) {
			largest = left;
		}
		if (right <= size && list[right] > list[largest]) {
			largest = right;
		}
		if (largest != node) {
			int temp = list[node];
			list[node] = list[largest];
			list[largest] = temp;
			max_heapify(list, largest, size);
		}

	}

	// Print the list
	public static void printlist(int[] list) {

		int size = list[0];
		for (int i = 1; i <= size; i++) {
			System.out.print(" " + list[i] + " ");

		}
		System.out.println("");
	}

	public static void heapSort(int[] list) {

		int size = list[0];

		for (int i = list[0]; i >= 2; i--) {
			// Swap first and i element, Reduce the heapSize
			int temp = list[i];
			list[i] = list[1];
			list[1] = temp;
			list[0] = list[0] - 1;
			// Heapify the reduced heap
			max_heapify(list, 1, list[0]);
		}
		list[0] = size; // Restore the size of sorted array
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list = new int[26];
		list[0] = 25;

		Random rand = new Random();

		for (int i = 1; i <= 25; i++) {
			list[i] = rand.nextInt(100) + 1;
		}

		System.out.println("Intial List:");
		printlist(list);

		build_heap(list);
		System.out.println("Unsorted Max-Heap:");
		printlist(list);

		heapSort(list);
		System.out.println("Sorted Max-Heap:");
		printlist(list);

	}

}
