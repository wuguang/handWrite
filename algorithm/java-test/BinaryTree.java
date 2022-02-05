import java.util.*;

public class BinaryTree {

    //后续遍历
    public static void posOrderUnRecur1(Node head){
        System.out.print("pos - order");
        if(head != null){
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while(!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while(!s2.isEmpty()){
                System.out.print(s2.pop().value);
            }
        }
        System.out.println("over~~~");
    }


    public static void preOrderUnRecur(Node head){
        System.out.println("pre-order :");
        if(head != null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);

            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");
                if(head.right != null){
                    stack.add(head.right);
                }
                if(head.left != null){
                    stack.add(head.left);
                }
            }
            System.out.println("---");

        }

    }

    class Node{
        public Integer value = null;
        public Node right = null;
        public Node left = null;

        /*
        Constructor(Integer val){
            value = val;
        }
        */
    }
}
