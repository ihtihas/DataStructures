import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */

/**
 * @author Sahithi
 *
 */
public class SingleLinkedList {
	
	

	/**
	 * 
	 */
	Node head;
	
	public class Node {
		
		int value;
		Node nextNode;
		
		
		/**
		 * @param value
		 */
		public Node(int value) {
			this.value = value;
			this.nextNode = null;
		}
		
		
		
		

	}
	

	// Insert new node at the end of the list
	/**
	 * @param list
	 * @param newNode
	 */
	public void insert(SingleLinkedList list, Node newNode) {

		if (list.head == null) { //If empty list , add the node as head 
			list.head = newNode;
		}

		else { 
			Node tmp = list.head;
			while (tmp.nextNode != null) {// Find an empty place and add the node
				tmp = tmp.nextNode;
			}
			tmp.nextNode = newNode;
		}

	}

	// Traversal through list from given Node
	/**
	 * @param node
	 */
	public void traversal(Node node) {

		if (node != null) {
			System.out.print(node.value + " ");
			traversal(node.nextNode);
		}
		

	}

	// sort list

	public void sortList(SingleLinkedList list) {

		Node startNode = list.head;

		if (startNode.nextNode != null) {

			Node minNodePrev = null; // Pointer to previous of minNode

			Node startNodePrev = null; // Pointer to previous of StartNode
			
			while (startNode.nextNode != null) { // compare till the 2nd node from last

				Node minNode = startNode; // assume Minimum as start value
				
				Node traversalNode = startNode; // Pointer to traverse through the list

				while (traversalNode.nextNode != null) { //traverse through the list and find min
					
					if (traversalNode.nextNode.value < minNode.value) { //if found value less than min, change the min

						minNode = traversalNode.nextNode;
						
						minNodePrev = traversalNode;

					}
					traversalNode = traversalNode.nextNode;
				}

				if (startNodePrev == null) { //For the first iteration (head position)
					list.head = minNode;
				}

				if (minNode.value == startNode.value) { // If the min is in correct position, go to the next sorting position

					startNodePrev = startNode;
					startNode = startNode.nextNode;
					

				}

				else {
					
					
					
					Node temp = null; // Temp variable to unlink and link the nodes
					
					//store the previous nodes of start and min nodes for swapping

					if (startNodePrev != null) {

						startNodePrev.nextNode = minNode;
					}
					if (minNodePrev != null) {
						minNodePrev.nextNode = startNode;
					}
					
					//swap the nodes
					
					temp = startNode.nextNode;
					startNode.nextNode = minNode.nextNode;
					minNode.nextNode = temp;

					startNodePrev = minNode;
					startNode = minNode.nextNode;
					

				}
				
							}

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SingleLinkedList list = new SingleLinkedList();
		
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		for (int i =1 ; i< 21 ; i++) {
			
			Node n = list.new Node(i);
			
			nodes.add(n);
		}
		
		Collections.shuffle(nodes); // get random combination of integers 1 to 20
		
		for(int i=0;i<20;i++) {
			list.insert(list, nodes.get(i)); // insert into single linked list
		}
		
		System.out.println("original List");
		list.traversal(list.head);
		System.out.println(" ");

		list.sortList(list);

		System.out.println("sorted List");
		list.traversal(list.head);
		System.out.println(" ");
		
	}

}
