import java.util.Stack;

/**
 * 
 * 二叉树的遍历
 * 
 **/
class BinaryTreeTraverse{
    //先序递归方式 打印二叉树
    public static void preOrderUseRecursion (Node head){
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderUseRecursion (head.left);
        preOrderUseRecursion (head.right);
    }

    //  先序方式 打印二叉树
    // 【迭代】
    public static void preOrderUseIteration(Node head){
        if(head == null){
            return;
        }
        Stack<Node> myStack = new Stack<>();
        myStack.push(head);
        while(!myStack.isEmpty()){
            Node cur = myStack.pop();
            System.out.print( cur.value + " ");
            if(cur.right != null){
                myStack.add(cur.right);
            }
            if(cur.left != null){
                myStack.add(cur.left);
            } 
        }

        /*
        System.out.print(head.value + " ");
        preOrderUseRecursion (head.left);
        preOrderUseRecursion (head.right);
        */

    }



    //中序递归方式 打印二叉树
    public static void inOrderUseRecursion (Node head){
        if(head == null){
            return;
        } 
        inOrderUseRecursion (head.left);
        System.out.print(head.value + " ");
        inOrderUseRecursion (head.right);
    }

    //迭代 中序  非递归
    public static void inOrderUseIteration(Node head){
        if(head == null ){
            return;
        }
        Stack <Node> myStack = new Stack<>();
        Node cur = head;
        // myStack.push(cur);
        // cur 是添加一子树进来的流程
        // 一定要找到下一课子树是谁?
        while(cur!=null || !myStack.isEmpty()){
            while(cur!=null){
                myStack.push(cur);
                cur = cur.left;
            }
            Node popNode = myStack.pop();
            System.out.print(popNode.value + " ");
            if(popNode.right != null){
                cur = popNode.right;
            }
        }
    }


    //后序 【递归】方式 打印二叉树
    public static void postOrderUseRecursion (Node head){
        if(head == null){
            return;
        } 
        postOrderUseRecursion (head.left);
        postOrderUseRecursion (head.right);
        System.out.print(head.value + " ");
    }

    //后序 用【迭代】方式 打印二叉树
    public static void postOrderUseIteration(Node head){
        if(head == null){
            return;
        }
        Stack<Node> myStack = new Stack<>();
        Node cur = head;
        //myStack.push(cur);
        //不停，分解
        while(cur!=null || !myStack.isEmpty()){
            //先找最左侧 节点 ,添加节点
            while(cur!=null){
                myStack.push(cur);
                //先找到最左侧节点，再找最右侧节点
                if(cur.left != null){
                    
                    cur = cur.left;
                }else{
                    if(cur.right != null){
                        
                    }
                    cur = cur.right; 
                }
            }

            //打印
            Node popNode = myStack.pop();
            System.out.print(popNode.value + " ");




            /*
            Node popNode = myStack.pop();
            System.out.print(popNode.value + " ");
            // 寻找下一个cur
            // 右节点
            if(curParent.right != null){
                cur = curParent;
            }
            */
        }
    }

    public static void main(String[] args){
        /*
        */
        Node tree = new Node(1);
        
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.left.left.left = new Node(6);
        tree.left.left.right = new Node(7);


        tree.right.left = new Node(8);
        tree.right.right = new Node(9);

        /*
        preOrderUseRecursion(tree);
        System.out.println("-----这里是优美分界线-------");
        preOrderUseIteration(tree);
        

        inOrderUseRecursion(tree);
        System.out.println(" ");
        System.out.println("-----这里是优美分界线-------");
        inOrderUseIteration(tree);
        */



        postOrderUseRecursion(tree);
        System.out.println(" ");
        System.out.println("-----后续遍历这里是优美分界线-------");
        postOrderUseIteration(tree);
        
    }


}