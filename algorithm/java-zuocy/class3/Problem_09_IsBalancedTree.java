package class3;

public class Problem_09_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static boolean isBalance02(Node head) {
		return process(head).isBalanced;
	}

	public static class ReturnType{
		public boolean isBalanced;
		public int height;
		public ReturnType(boolean isB,int hei){
			isBalanced = isB;
			height = hei;
		}
	}

	public static ReturnType process(Node x){
		if(x ==null){
			return new ReturnType(true,0);
		}
		ReturnType leftData = process(x.left);
		ReturnType rightData = process(x.right);
		int height = Math.max(leftData.height,rightData.height) + 1;
		boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height-rightData.height)<2 ?true:false;
		return new ReturnType(isBalanced,height);
	}


	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
