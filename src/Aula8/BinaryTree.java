package Aula8;

public class BinaryTree implements Tree {
	
	Node root;
	
	BinaryTree () {
		root = null;
	}

	@Override
	public int getHeight() {
		return root == null ? 0 : getHeight(root, -1);
	}
	
	private int getHeight(Node no, int actualHeight) {
		if (no != null) {
			int leftHeight = getHeight(no.left, actualHeight + 1);
			int rightHeight = getHeight(no.right, actualHeight + 1);
			actualHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
		}
		return actualHeight;
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
		show(root);		
	}
	
	private void show (Node i) {
		if (i == null) return;
		show(i.left);
		System.out.print(i.item + " ");
		show(i.right);
	}

	@Override
	public void showPrevious() {
		showPrevious(root);		
	}
	
	private void showPrevious(Node i) {
		if (i == null) return;
		System.out.print(i.item + " ");
		showPrevious(i.left);
		showPrevious(i.right);
	}

	@Override
	public void showPosterior() {
		showPosterior(root);
	}
	
	private void showPosterior(Node i) {
		if (i == null) return;
		showPosterior(i.left);
		showPosterior(i.right);
		System.out.print(i.item + " ");
	}

	@Override
	public boolean isComplete() {
		return isComplete(root);
	}
	
	private boolean isComplete(Node no) {
		if (no != null) {
			if (no.left == no.right) return true;
			else if ((no.left == null && no.right != null) ||
				(no.right == null && no.left != null))
				return false;
			
			boolean left = isComplete(no.left);
			if (!left) return left;
			
			boolean right = isComplete(no.right);
			if (!right) return right;
			
			int leftHeight = getHeight(no.left, 0);
			int rightHeight = getHeight(no.right, 0);
			return leftHeight == rightHeight;
		}
		return false;
	}
	
}
