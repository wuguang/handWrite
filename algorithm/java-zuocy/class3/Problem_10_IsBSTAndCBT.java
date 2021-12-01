package class3;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_10_IsBSTAndCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	//isBST 02 的方法！！！
	public static class ReturnData{
		public boolean isBST;
		public int min;
		public int max;

		public ReturnData(boolean is,int mi,int ma){
			isBST = is;
			min = mi;
			max = ma;
		}
	}

	public static ReturnData process02(Node x){
		if(x == null){
			return null;
		}
		ReturnData leftData = process02(x.left);
		ReturnData rightData = process02(x.right);

		int min = x.value;
		int max = x.value;
		if(leftData!=null){
			min = Math.min(min,leftData.min);
			max = Math.max(max,leftData.max);
		}
		if(rightData!=null){
			min = Math.min(min,rightData.min);
			max = Math.max(max,rightData.max);
		}
		boolean isBST = true;
		if(leftData!=null && (!leftData.isBST||leftData.max>=x.value)){
			isBST = false;
		}

		if(rightData!=null && (!rightData.isBST||rightData.min<=x.value)){
			isBST = false;
		}

		return new ReturnData(isBST,min,max);
	}
	
	public static boolean isBST(Node head) {
		if (head == null) {
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}


	public static boolean isBST_w_02(Node head) {
		if (head == null) {
			return true;
		}
		Boolean result = true;
		Node left  = head.left;
		Node right = head.right;
		if(left!=null){
			if(head.value<=left.value){
				return false;
			}
			result = isBST_w_02(left);
			if(!result){
				return false;
			}
		}
		if(right!=null){
			if(head.value>=right.value){
				return false;
			}
			result = isBST_w_02(right);
			if(!result){
				return false;
			}
		}
		

		return result;
	}

	public static boolean isCBT(Node head) {
		if (head == null) {
			return true;
		}
		Queue<Node> queue = new LinkedList<Node>();
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			//有you无zuo，直接false
			if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
				return false;
			}
			if (l != null) {
				queue.add(l);
				//queue.offer(l);
			}
			if (r != null) {
				queue.add(r);
				//queue.offer(r);
			} 
			
			//else 
			if(l==null || r==null){
				leaf = true;
			}
		}
		return true;
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

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(9);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(isBalance02(head));
		System.out.println(isBST_w_02(head));
		System.out.println(isBST(head));
		System.out.println(isCBT(head));

	}
}