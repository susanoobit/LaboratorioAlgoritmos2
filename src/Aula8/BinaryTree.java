package Aula8;

public class BinaryTree implements Tree {
	
	Node root;
	
	BinaryTree () {
		root = null;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	private Node insert(Node no, int item) {
		if (no == null)
			no = new Node(item, null, null);
		else if (item < no.item)
			no.left = insert(no.left, item);
		else if (item > no.item)
			no.right = insert(no.right, item);
		else
			System.out.println("Element already inserted.");
		return no;
	}
	
	private Node remove(Node no, int item) {
		if (no == null) throw new NullPointerException();
		else if (item < no.item) no.left = remove(no.left, item);
		else if (item > no.item) no.right = remove(no.right, item);
		else if (no.right == null) no = no.left;
		else if (no.left == null) no = no.right;
		else no.left = previous(no, no.left);
		return no;
	}
	
	private Node previous(Node i, Node j) {
		if (j.right != null) j.right = previous(i, j.right);
		else i.item = j.item; j = j.left;
		return j;
	}

	@Override
	public void insert(int item) {
		root = insert(root, item);
	}

	@Override
	public int remove(int item) {
		root = remove(root, item);
		return item;
	}
	
	public static void main (String []args) {
		BinaryTree teste = new BinaryTree();
		teste.insert(5);
		System.out.println(teste.root.item);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	private void show (Node i) {
		if (i == null) return;
		
	}

	@Override
	public void showPrevious() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPosterior() {
		// TODO Auto-generated method stub
		
	}
	
}
