import java.util.ArrayList;

public class BST {

	Node root;

	public boolean isBST() {
		return auxIsBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean auxIsBST(Node node, int min, int max) {
		if (node == null) {
			return true;
		}

		if (node.key < min || node.key > max) {
			return false;
		}

		return (auxIsBST(node.left, min, node.key - 1) && auxIsBST(node.right, node.key + 1, max));
	}

	public boolean isBST(BST tree) {
		if (tree.isBST()) {
			System.out.println("Árvore é binária de busca");
			return true;
		} else {
			System.out.println("Árvore não é binária de busca");
			return false;
		}
	}

	public void insert(int key) {
		root = insert(root, key);
	}

	private Node insert(Node currentNode, int key) {
		if (currentNode == null) {
			return new Node(key);
		}

		if (key < currentNode.key) {
			currentNode.left = insert(currentNode.left, key);
		} else if (key > currentNode.key) {
			currentNode.right = insert(currentNode.right, key);
		} else {
			return currentNode;
		}

		return currentNode;
	}

	public boolean checkNode(int key) {
		return checkNode(root, key);
	}

	private boolean checkNode(Node currentNode, int key) {
		if (currentNode == null) {
			return false;
		}
		if (key == currentNode.key) {
			return true;
		}

		return key < currentNode.key ? checkNode(currentNode.left, key) : checkNode(currentNode.right, key);
	}

	public void remove(int key) {
		root = remove(root, key);
	}

	private Node remove(Node currentNode, int key) {
		if (currentNode == null) {
			return null;
		}

		if (key == currentNode.key) {
			if (currentNode.left == null && currentNode.right == null) {
				return null;
			}

			if (currentNode.right == null) {
				return currentNode.left;
			}

			if (currentNode.left == null) {
				return currentNode.right;
			}

			int smallestValue = findSmallestValue(currentNode.right);
			currentNode.key = smallestValue;
			currentNode.right = remove(currentNode.right, smallestValue);

			return currentNode;
		}

		if (key < currentNode.key) {
			currentNode.left = remove(currentNode.left, key);
			return currentNode;
		}

		currentNode.right = remove(currentNode.right, key);
		return currentNode;
	}

	public BST removeEvens() {
		BST treeCopy = new BST();
		this.traverseInOrderSavingOdds(root, treeCopy);

		return treeCopy;
	}

	public boolean areEvensRemoved(ArrayList<Integer> list, BST tree) {
		for (int item : list) {
			if (tree.checkNode(item)) {
				System.out.println("Pares não removidos");
				return false;

			}
		}
		System.out.println("Pares removidos");
		return true;
	}

	private int findSmallestValue(Node root) {
		return root.left == null ? root.key : findSmallestValue(root.left);
	}

	public void traverseInOrder(Node node) {
		if (node != null) {
			traverseInOrder(node.left);
			traverseInOrder(node.right);
		}
	}

	private void traverseInOrderSavingOdds(Node node, BST treeCopy) {
		if (node != null) {
			if (node.key % 2 != 0) {
				treeCopy.insert(node.key);
			}

			traverseInOrderSavingOdds(node.left, treeCopy);
			traverseInOrderSavingOdds(node.right, treeCopy);
		}
	}

	private void countNodes(Node node, ArrayList<Integer> counter) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				counter.add(0);
			}

			countNodes(node.left, counter);
			countNodes(node.right, counter);
		}
	}

	public int countNodes() {
		ArrayList<Integer> nodeList = new ArrayList<Integer>();
		countNodes(root, nodeList);

		return nodeList.size();
	}

}
