public class Morris {

    public static void main(String[] args){

        Node tree = new Node(1);

        tree.left = new Node(2);
        tree.right = new Node(3);

        tree.left.left = new Node(4);
        tree.left.right = new Node(5);

        tree.left.left.left = new Node(6);
        tree.left.left.right = new Node(7);

        //byMorris(tree);
        morrisPos(tree);

    }

    public static void morrisPos(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node node){
        Node tail =reverseEdge(node);
        Node cur = tail;
        while (cur != null ){
            System.out.print(cur.value+" ");
            cur =cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node node){
        Node pre = null;
        Node next = null;
        while (node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void morrisPre(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            mostRight = cur.left;
            if(mostRight != null){
                // cur有左孩子，找到cur左子树最右节点
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                // mostRight的右孩子指向空，让其指向cur，cur向左移动
                if(mostRight.right == null){
                    mostRight.right = cur;
                    System.out.print(cur.value+" ");
                    cur = cur.left;
                    continue;
                }else {
                    // mostRight的右孩子指向cur，让其指向空，cur向右移动
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }


    public static void morrisIn(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while (mostRight.right !=null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value+" ");
            cur = cur.right;
        }
        System.out.println();
    }
    public static void byMorris(Node head){

        if(head == null){
            return;
        }
        
        Node cur = head;
        while(cur !=null){
            System.out.print("; " + cur.value);
            //有左侧节点
            if(cur.left != null){
                //找到最左侧的最右节点
                Node lastRightNode = cur.left;
                while(lastRightNode.right != null){
                    lastRightNode = lastRightNode.right;
                }

                if(lastRightNode.right == null){
                    lastRightNode.right = cur;   
                    cur = cur.left;
                }else if(lastRightNode.right == cur){
                    lastRightNode.right = null;
                    cur = cur.right;
                }

            }else{
                //没有左侧节点
                //找右节点
                cur = cur.right;
            }
        }
    }

    public static void byMorris02(Node head){

        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;

        while(cur !=null ){
            System.out.print("cur = " + cur);
            mostRight = cur.left;
            //右左节点是，没有就是左节点本身
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                //第一次没有右指针
                //添加右指针
                if(mostRight.right == null){
                    mostRight.right = cur;
                    //寻找下一个节点
                    cur = cur.left;
                }else{
                    //不然清空右指针
                    mostRight.right = null;
                }
            }
            //下一个节点是右节点
            cur = cur.right;
        }
    }
}
