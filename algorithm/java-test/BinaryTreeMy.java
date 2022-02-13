import java.util.LinkedList;
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

    public static void postOrderRecurs(Node head){
        if(head == null){
            return;
        }
        postOrderRecurs(head.left);
        postOrderRecurs(head.right);
        System.out.print(head.value + " ");
    }

    public static void postOrderIteration(Node head){
        if(head == null){
            return;
        }
        Stack<Node> s = new Stack<>();
        Stack<Node> reversStack = new Stack<>();
        s.push(head);

        while(!s.isEmpty()  ){
            Node cur = s.pop();
            reversStack.push(cur);
            if(cur.left != null){
                s.push(cur.left);
            }
            if(cur.right != null){
                s.push(cur.right);
            }
        }

        while(!reversStack.isEmpty()){
            System.out.print(reversStack.pop().value + " ");
        }
    }

    //层次遍历
    public static void hierarchicalTraversal(Node head){
        if(head == null){
            return;
        }
        LinkedList<Node> curQueue = new LinkedList<>();
        LinkedList<Node> nextQueue = new LinkedList<>();
        curQueue.add(head);
        while(!curQueue.isEmpty()){
            Node cur = curQueue.pop(); 
            System.out.print(cur.value + " ");
            if(cur.left != null){
                nextQueue.add(cur.left);
            }
            if(cur.right != null){
                nextQueue.add(cur.right);
            }
            if( curQueue.isEmpty() && !nextQueue.isEmpty()){
                //进入一行进行计算
                curQueue = nextQueue;
            }
        }
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
    
        System.out.println( "递归版中序遍历:" );
        inOrderRecurs(tree);

        System.out.println(" ");

        System.out.println( "迭代版中序遍历:" );
        inOrderIteration(tree);
        
        
        System.out.println( "递归版后序遍历:" );
        postOrderRecurs(tree);

        System.out.println(" ");

        System.out.println( "迭代版后序遍历:" );
        postOrderIteration(tree);
       

        System.out.println( "BFS-层次遍历:" );
        hierarchicalTraversal(tree);
         
        */
        
        System.out.println( "postOrderIteration 遍历:" );
        postOrderIteration(tree);
        System.out.println( "" );
        System.out.println( "postOrderRecurs 遍历:" );
        postOrderRecurs(tree);

        System.out.println( "" );
        System.out.println( "morrisForPost 遍历:" );
        morrisForPost(tree);
        System.out.println( "" );

    }

    
    public static void morrisForPost(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        while(cur != null){
            Node leftNode = cur.left;
            if(leftNode != null){
                Node rightEstNode = leftNode;
                while(rightEstNode.right != null && rightEstNode.right != cur){
                    rightEstNode = rightEstNode.right;
                }

                if(rightEstNode.right == null){
                    rightEstNode.right = cur;
                  
                    cur = cur.left;
                }else{
                    rightEstNode.right = null;
                    
                    printLeftEdge(leftNode);
                    //第二来到 cur
                    cur = cur.right;
                }
            }else{
                cur = cur.right;
            }
        }
        printLeftEdge(head);

    }

    public static void printLeftEdge(Node leftNode){
        if(leftNode == null){
            return;
        }
        leftNode = reverseNode(leftNode);
        while(leftNode != null){
            System.out.print(leftNode.value + " ");
            leftNode = leftNode.right;
        }
        reverseNode(leftNode);
    }

    public static Node reverseNode(Node node){
        Node pre = null;
        Node cur = node;
        while(cur != null){
            Node next = cur.right;
            cur.right = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }



    public static void morrisForIn(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        while(cur != null){
            Node leftNode = cur.left;
            if(leftNode != null){
                Node rightEstNode = leftNode;
                while(rightEstNode.right != null && rightEstNode.right != cur){
                    rightEstNode = rightEstNode.right;
                }

                if(rightEstNode.right == null){
                    rightEstNode.right = cur;
                  
                    cur = cur.left;
                }else{
                    rightEstNode.right = null;
                    // 第一次
                    System.out.print( cur.value + " ");
                    //第二来到 cur
                    cur = cur.right;
                }
            }else{
                // 
                System.out.print( cur.value + " ");
                cur = cur.right;
            }
        }
    }



    public static void morrisForPre(Node head){
        if(head == null){
            return;
        }

        Node cur = head;
        while(cur != null){
            Node leftNode = cur.left;
            if(leftNode != null){
                Node rightEstNode = leftNode;
                while(rightEstNode.right != null && rightEstNode.right != cur){
                    rightEstNode = rightEstNode.right;
                }

                if(rightEstNode.right == null){
                    rightEstNode.right = cur;
                    // 第一次
                    System.out.print( cur.value + " ");
                    cur = cur.left;
                }else{
                    rightEstNode.right = null;
                    //第二来到 cur
                    cur = cur.right;
                }
            }else{
                // 
                System.out.print( cur.value + " ");
                cur = cur.right;
            }
        }
    }


}
