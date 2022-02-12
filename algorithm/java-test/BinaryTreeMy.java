import java.util.Stack;

public class BinaryTreeMy {

    // recursive iteration
    public static void preOrderRecurs(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecurs(head.left);
        preOrderRecurs(head.right);
    }

    public static void preOrderIteration(Node head){
        if(head == null){
            return;
        }
        Stack<Node> s = new Stack<>();
        s.push(head);
        while(!s.isEmpty()){
            Node cur = s.pop();
            System.out.print(cur.value + " ");
            if(cur.right!=null){
                s.push(cur.right);
            }
            if(cur.left!=null){
                s.push(cur.left);
            }
        }
    }

    public static void inOrderIteration(Node head){
        if(head == null){
            return;
        }
        Stack<Node> s = new Stack<>();
        Node cur = head;
        //1、尝试添加cur
        // 2、弹出元素，打印
        while(!s.isEmpty() || cur != null){
            while(cur!=null){
                s.push(cur);
                cur = cur.left;
            }
            Node popNode = s.pop();
            System.out.print( popNode.value +" ");
            if(popNode.right != null){
                cur = popNode.right;
            }
        }
    }

    public static void inOrderRecurs(Node head){
        if(head == null){
            return;
        }
        inOrderRecurs(head.left);
        System.out.print(head.value + " ");
        inOrderRecurs(head.right);
    }


    public static void main(String [] args){
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.left.left.left = new Node(6);
        tree.left.left.right = new Node(7);

        tree.left.left.right.left = new Node(8);
        tree.left.left.right.left.left = new Node(9);
        tree.left.left.right.left.left.right = new Node(10);
        tree.left.left.right.left.left.right.right = new Node(11);
        
        /*
        preOrderRecurs(tree);

        System.out.println( " " );
        preOrderIteration(tree);

        System.out.println( " " );
        */
        System.out.println( "递归版中序遍历:" );
        inOrderRecurs(tree);

        System.out.println(" ");

        System.out.println( "迭代版中序遍历:" );
        inOrderIteration(tree);
    }
}
