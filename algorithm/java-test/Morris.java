public class Morris {

    public static void main(String[] args){
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(3);
        tree.left.left = new Node(4);
        tree.left.right = new Node(5);
        tree.left.left.left = new Node(6);
        tree.left.left.right = new Node(7);

        
        tree.left.left.right.right = new Node(8);
        tree.left.left.right.right.right = new Node(9);
        tree.left.left.right.right.right.left = new Node(10);
        tree.left.left.right.right.right.left.left = new Node(11);
        tree.left.left.right.right.right.left.left.right = new Node(12);
        tree.left.left.right.right.right.left.left.right.right = new Node(13);
        tree.left.left.right.right.right.left.left.right.right.right = new Node(14);
        
        
        //byMorrisSelf(tree);
        morrisPos(tree);
        morriesPosSelf02(tree);
        morriesPosSelf03(tree);
        //byMorrisSelf(tree);
    }

    public static void morriesPosSelf03(Node head){
        System.out.println(" ");
        if(head == null){
            return;
        }
        Node cur = head;
        while(cur!=null){
            Node leftNode = cur.left;
            if(leftNode!=null){
                Node rightEstNode = leftNode;
                while(rightEstNode.right != null && rightEstNode.right != cur){
                    rightEstNode = rightEstNode.right;
                }
                if(rightEstNode.right == null){
                    //第一册遍历自己
                    //rightEstNode.right == null true
                    rightEstNode.right = cur;
                    //遍历自己
                    cur = cur.left;
                }else{
                    rightEstNode.right = null;
                    printPosLeftNode(leftNode);
                    // 第二次进入自己
                    cur = cur.right;
                }
            }else{
                //没有左节点的 遍历顺序
                // 先自己，在下一个，
                //下一个是右节点
                cur = cur.right;
            }
        }
        printPosLeftNode(head);
    }

    public static void printPosLeftNode(Node leftNode){

        leftNode = reverseRightNode02(leftNode);
        Node cur = leftNode;
        while(cur !=null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }

        reverseRightNode02(leftNode);
    }

    public static Node reverseRightNode02(Node leftNode){
        if(leftNode == null){
            return leftNode;
        }
        Node cur = leftNode;
        Node next = null;
        Node pre = null;
        while(cur != null){
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static void morriesPosSelf02(Node head){
        if(head == null){
            return;
        }
        Node cur = head;
        while(cur !=null){
            Node leftNode = cur.left;
            if(leftNode != null){
                Node rightEstNode = leftNode;
                while(rightEstNode.right != null && rightEstNode.right != cur){
                    rightEstNode = rightEstNode.right;
                }

                if(rightEstNode.right == null){
                    rightEstNode.right = cur;
                    // cur第一次来
                    cur = cur.left;
                }else{
                    rightEstNode.right = null;
                    printLeftOrder(cur.left);
                    //cur 第二次来
                    cur = cur.right;
                }
            }else{
                cur = cur.right;
            }
        }
        printLeftOrder(head);
    }

    public static void printLeftOrder(Node leftNode){
        if(leftNode == null){
            return;
        }
        leftNode = reverseRightNode(leftNode);

        Node cur = leftNode;
        while(cur!=null){
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseRightNode(leftNode);
    }

    public static Node reverseRightNode(Node leftNode){
        Node cur = leftNode;
        Node next = null;
        Node pre = null;
        //最有节点逆序
        while(cur != null){
            next = cur.right;
            cur.right = pre;

            //重新迭代
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void morrisPosSelf(Node head){
        if(head == null){
            //中断执行
            return;
        }
        Node cur = head;
        while(cur !=null){
            if(cur.left !=null){
                Node rightEstNode = cur.left;
                //继续寻找最右介节点
                // 如果cur是第二次来到，那么就会进入右节点的死循环，这里需要右终止的判断
                // 
                while(rightEstNode.right != null && rightEstNode.right !=cur){
                    rightEstNode = rightEstNode.right;
                }

                // 到这里就二种情况了
                // 要没第一次来到 就rightEstNode.right == null
                // 要没第二次来到 就rightEstNode.right == cur
                if(rightEstNode.right == null){
                    // 找到了最右节点了
                    // 后面没有右节点了，建立右节点保证可以回到cur节点
                    rightEstNode.right = cur;
                    cur = cur.left;
                }else{
                    //第二次来打印 该节点的左节点的最右节点边界的逆序
                    rightEstNode.right = null;
                    printEdge02(cur.left);
                    //System.out.print(cur.value + " ");
                    // 寻找下一个 找右节点
                    cur = cur.right;
                }
            }else{
                //一定会有右节点
                // 如果没有前面的程序会自动添加（建立）
                //System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        //结束时打印整棵树的右边界逆序
        printEdge02(head);
        System.out.println(" ");
    }

    // 逆序打印该节点右边界 
    public static void printEdge02(Node node){
        Node tail = reverseEdge(node);
        Node cur = tail;
        while (cur != null ){
            System.out.print(cur.value+" ");
            cur =cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge02(Node node){
        Node pre = null;
        Node next = null;

        next = node.right;
        node.right = null;
        pre = node;

        while (node != null){
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void morrisInSelf(Node head){
        if(head == null){
            //中断执行
            return;
        }
        Node cur = head;
        while(cur !=null){
            if(cur.left !=null){
                Node rightEstNode = cur.left;
                //继续寻找最右介节点
                // 如果cur是第二次来到，那么就会进入右节点的死循环，这里需要右终止的判断
                // 
                while(rightEstNode.right != null && rightEstNode.right !=cur){
                    rightEstNode = rightEstNode.right;
                }

                // 到这里就二种情况了
                // 要没第一次来到 就rightEstNode.right == null
                // 要没第二次来到 就rightEstNode.right == cur
                if(rightEstNode.right == null){
                    // 找到了最右节点了
                    // 后面没有右节点了，建立右节点保证可以回到cur节点
                    rightEstNode.right = cur;
                    cur = cur.left;
                }else{
                    //第二次来 不打印
                    rightEstNode.right = null;
                    System.out.print(cur.value + " ");
                    // 寻找下一个 找右节点
                    cur = cur.right;
                }
            }else{
                //一定会有右节点
                // 如果没有前面的程序会自动添加（建立）
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println(" ");
    }


    public static void morrisPreSelf(Node head){
        if(head == null){
            //中断执行
            return;
        }
        Node cur = head;
        while(cur !=null){
            if(cur.left !=null){
                Node rightEstNode = cur.left;
                //继续寻找最右介节点
                // 如果cur是第二次来到，那么就会进入右节点的死循环，这里需要右终止的判断
                // 
                while(rightEstNode.right != null && rightEstNode.right !=cur){
                    rightEstNode = rightEstNode.right;
                }

                // 到这里就二种情况了
                // 要没第一次来到 就rightEstNode.right == null
                // 要没第二次来到 就rightEstNode.right == cur
                if(rightEstNode.right == null){
                    // 找到了最右节点了
                    // 后面没有右节点了，建立右节点保证可以回到cur节点
                    rightEstNode.right = cur;
                    System.out.print(" " + cur.value);
                    cur = cur.left;
                }else{
                    //第二次来 不打印
                    rightEstNode.right = null;
                    // 寻找下一个 找右节点
                    cur = cur.right;
                }
            }else{
                //一定会有右节点
                // 如果没有前面的程序会自动添加（建立）
                System.out.print(" " + cur.value);
                cur = cur.right;
            }
        }
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


    // morris 前序遍历
    public static void morrisPre(Node head) {
        if(head == null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null){
            // cur表示当前节点，mostRight表示cur的左孩子的最右节点
            // 没有最右节点的话，就是 cur.left 自己
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
                    //转移到下一个 节点
                    cur = cur.left;
                    // 进入下一次循环
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

    public static void byMorrisSelf(Node head){

        if(head == null){
            return;
        }
        
        Node cur = head;

        while(cur !=null){
            
            //有左侧节点
            if(cur.left != null){
                
                //找到最左侧的最右节点
                Node lastRightNode = cur.left;
                while(lastRightNode.right != null && lastRightNode.right != cur){
                    lastRightNode = lastRightNode.right;
                }

                if(lastRightNode.right == null){
                    System.out.print(cur.value+" ");
                    lastRightNode.right = cur;   
                    cur = cur.left;
                }else if(lastRightNode.right == cur){
                    lastRightNode.right = null;
                    cur = cur.right;
                }
            }else{
                System.out.print(cur.value + " ");
                //没有左侧节点
                //找右节点
                cur = cur.right;
                
            }
        }
    }

    public static void byMorrisSelf02(Node head){

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
