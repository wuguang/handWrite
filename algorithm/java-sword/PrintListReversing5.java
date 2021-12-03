
import java.util.ArrayList;
import java.util.Stack;



public class PrintListReversing5 {

    public static class ListNode {
        int val; // 结点的值
        ListNode next; // 下一个结点
    }

    public static ArrayList<Integer> printListReverse2(ListNode headNode,ArrayList<Integer> list) {
        if (headNode != null) {
            if (headNode.next != null) {
                list = printListReverse2(headNode.next,list);
            }
            list.add(headNode.val);
        }
        return list;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode();
        root.val = 1;
        root.next = new ListNode();
        root.next.val = 2;
        root.next.next = new ListNode();
        root.next.next.val = 3;
        root.next.next.next = new ListNode();
        root.next.next.next.val = 4;
        root.next.next.next.next = new ListNode();
        root.next.next.next.next.val = 5;

        ArrayList<Integer> list = new ArrayList<Integer>();
        printListReverse2(root,list);
        System.out.println(list);
        //printListReverse2(root);
    }


}
