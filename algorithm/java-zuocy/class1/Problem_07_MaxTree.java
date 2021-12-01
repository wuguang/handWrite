package class1;

import java.util.HashMap;
import java.util.Stack;

public class Problem_07_MaxTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getMaxTree1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
		Node[] nodes = new Node[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nodes[i] = new Node(arr[i]);
		}
		for (int i = 0; i < nodes.length; i++) {
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			if (left < nodes.length) {
				nodes[i].left = nodes[left];
			}
			if (right < nodes.length) {
				nodes[i].right = nodes[right];
			}
		}
		return nodes[0];
	}

	public static void heapInsert(int[] heap, int index) {
		while (index != 0) {
			int parent = (index - 1) / 2;
			if (heap[index] > heap[parent]) {
				swap(heap, parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}

	public static void swap(int[] heap, int index1, int index2) {
		int tmp = heap[index1];
		heap[index1] = heap[index2];
		heap[index2] = tmp;
	}

	public static Node getMaxTree2(int[] arr) {
		Node[] nodes = new Node[arr.length];
		for (int i = 0; i != arr.length; i++) {
			nodes[i] = new Node(arr[i]);
		}
		Stack<Node> stack = new Stack<Node>();
		HashMap<Node, Node> parents = new HashMap<Node, Node>();
		for (int i = 0; i < nodes.length; i++) {
			while (!stack.isEmpty() && stack.peek().value < nodes[i].value) {
				parents.put(stack.pop(), (stack.isEmpty() || (nodes[i].value < stack.peek().value)) ? nodes[i] : stack.peek());
			}
			stack.push(nodes[i]);
		}
		while (!stack.isEmpty()) {
			parents.put(stack.pop(), stack.isEmpty() ? null : stack.peek());
		}
		Node head = null;
		Node parent = null;
		for (int i = 0; i != arr.length; i++) {
			parent = parents.get(nodes[i]);
			if (parent == null) {
				head = nodes[i];
			} else if (parent.left == null) {
				parent.left = nodes[i];
			} else {
				parent.right = nodes[i];
			}
		}
		return head;
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	// for test -- print tree
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

	// for test -- print tree
	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		int[] uniqueArr1 = { 3, 4, 5, 1, 2 };
		Node head1 = getMaxTree1(uniqueArr1);
		printTree(head1);
		int[] uniqueArr2 = { 3, 4, 5, 1, 2 };
		Node head2 = getMaxTree2(uniqueArr2);
		printTree(head2);
	}

}
