import java.util.ArrayList;
import java.util.Collections;

public class HeapArrayList {

	public static int parent(int i) {
		return i / 2;
	}

	public static int left(int i) {
		return 2 * i;
	}

	public static int right(int i) {
		return 2 * i + 1;
	}

	public static void build_heap(ArrayList<Integer> list) {

		int size = list.get(0);

		for (int i = size / 2; i >= 1; i--) {

			max_heapify(list, i, size);
		}

	}

	public static void max_heapify(ArrayList<Integer> list, int node, int size) {
		int left = left(node);
		int right = right(node);
		int largest = node;
		if (left <= size && list.get(left) > list.get(node) ){
			largest = left;
		}
		if (right <= size && list.get(right) > list.get(largest) ){
			largest = right;
		}
		if (largest != node) {
			int temp =list.get(node);
			list.set(node, list.get(largest))  ;
			list.set(largest, temp) ;
			max_heapify(list, largest, size);
		}

	}

	public static void printlist(ArrayList<Integer> list) {
		
		for (int i = 1; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");

		}
		System.out.println("");
	}

	public static void heapSort(ArrayList<Integer> list) {
		
		
		
		for (int i = list.get(0); i>=2 ; i-- ) {
			int temp = list.get(i);
			list.set(i,list.get(1));
			list.set(1, temp);
			list.set(0, list.get(0)-1);
			max_heapify(list,1,list.get(0));
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//int[] list = new int[26];

		ArrayList<Integer> listing = new ArrayList<Integer>();
		listing.add(25);
		ArrayList<Integer> listing2 = new ArrayList<Integer>();

		for (int i = 1 ; i < 26; i++) {
			listing2.add(i*3);
		}
		Collections.shuffle(listing2);
		
		listing.addAll(listing2);
				

		printlist(listing);

		build_heap(listing);

		printlist(listing);
		
		heapSort(listing);
		
		printlist(listing);

	}

}
