import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {

		BST tree = new BST();
		Random rng = new Random();

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			int randomNumber = rng.nextInt(999999);

			if (randomNumber % 2 == 0) {
				list.add(randomNumber);
			}

			tree.insert(randomNumber);
		}

		tree = tree.removeEvens();
		tree.areEvensRemoved(list, tree);

		System.out.println("Número de nós folhas: " + tree.countNodes());

		tree.isBST(tree);

		tree.root = new Node(4);
		tree.root.left = new Node(5);
		tree.root.right = new Node(2);
		tree.root.left.left = new Node(1);
		tree.root.right.right = new Node(3);

		tree.isBST(tree);

	}

}
