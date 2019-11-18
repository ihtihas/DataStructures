public class BinarySearchTree {

	public class Node {

		int value;

		Node leftChild, rightChild;

		/**
		 * @param value
		 */
		public Node(int value) {

			this.value = value;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	Node root;

	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(int value) {
		Node root = new Node(value);
		this.root = root;

	}

	public int recursiveInsert(Node node, int value) {

		if (node.value <= value) {

			if (node.rightChild == null) {
				Node newNode = new Node(value);
				node.rightChild = newNode;
				return 1;
			} else {
				return recursiveInsert(node.rightChild, value);
			}

		} else if (node.value > value) {

			if (node.leftChild == null) {
				Node newNode = new Node(value);
				node.leftChild = newNode;
				return 1;
			} else {
				return recursiveInsert(node.leftChild, value);
			}

		}

		return 0;
	}

	public void inOrderTrav(Node node) {

		if (node == null) {
			return;
		} else {
			inOrderTrav(node.leftChild);
			System.out.print(node.value + " ");
			inOrderTrav(node.rightChild);
		}
	}

	public void deleteNode(BinarySearchTree bst, int value) {

		Node node = bst.root;
		
		Node parentNode = null;
		Node deleteNode = null;

		// get the Delete Node and Parent Node
		while (node != null) {
			if (value < node.value) {
				parentNode = node;
				node = node.leftChild;
			} else if (value > node.value) {
				parentNode = node;
				node = node.rightChild;
			} else {
				deleteNode = node;
				break;
			}

		}

		// Check if it is a Leaf Node
		if (deleteNode.leftChild == null && deleteNode.rightChild == null) {

			if (deleteNode == bst.root) {// Delete Root without any children
				bst.root = null;
				return;
			}

			if (parentNode.leftChild == deleteNode)
				parentNode.leftChild = null;
			else
				parentNode.rightChild = null;

			return;
		}

		// delete node with a subtree containing both children
		if (deleteNode.leftChild != null && deleteNode.rightChild != null) {
			
			// get the Successor Node and its Parent
			Node succNode = deleteNode.rightChild;
			Node succParent = deleteNode;

			if (succNode.leftChild == null) { //if right child is the successor
				deleteNode.value = succNode.value;
				deleteNode.rightChild =  succNode.rightChild; // replace the right subtree
				
				return;
			}

			while (succNode.leftChild != null) {
				succParent = succNode;
				succNode = succNode.leftChild;
			}
			
			
			
			deleteNode.value = succNode.value;
			if (succNode.rightChild != null) {
				succParent.leftChild = succNode.rightChild;
			} else {
				succParent.leftChild = null;
			}
			

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree bst = new BinarySearchTree();

		bst.setRoot(65);
		bst.recursiveInsert(bst.root, 28);
		bst.recursiveInsert(bst.root, 83);
		bst.recursiveInsert(bst.root, 22);
		bst.recursiveInsert(bst.root, 46);
		bst.recursiveInsert(bst.root, 78);
		bst.recursiveInsert(bst.root, 89);
		bst.recursiveInsert(bst.root, 35);
		bst.recursiveInsert(bst.root, 48);
		bst.recursiveInsert(bst.root, 85);
		bst.recursiveInsert(bst.root, 91);
		bst.recursiveInsert(bst.root, 32);
		bst.recursiveInsert(bst.root, 40);
		bst.recursiveInsert(bst.root, 47);
		bst.recursiveInsert(bst.root, 55);
		bst.recursiveInsert(bst.root, 86);
		bst.recursiveInsert(bst.root, 93);

		System.out.println("In Order Traversal of Tree: ");
		bst.inOrderTrav(bst.getRoot());
		System.out.println(" ");
		
		System.out.println("Delete Leaf Node 32: ");
		bst.deleteNode(bst, 32);
		bst.inOrderTrav(bst.getRoot());
		System.out.println(" ");
		
		System.out.println("Delete Node with subTree 83: ");
		bst.deleteNode(bst, 83);
		bst.inOrderTrav(bst.getRoot());
		System.out.println(" ");
		
		System.out.println("Delete the Root Node 65: ");
		bst.deleteNode(bst, 65);
		bst.inOrderTrav(bst.getRoot());

	}

}
