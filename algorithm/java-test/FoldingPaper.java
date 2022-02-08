

import java.util.*;
// 一张纸条，保持正面对自己，从上往下折，
// 不断重复，
// n次后，展开，
// 打印纸条从上到下的折痕凹凸性s

public class FoldingPaper {

    public static String result = "";
    public static String getNTrace(int n){
        String result = "";
        getResultByCenter(n);

        return "";
    }

    public static String getResultByCenter(int n){
        if(n>0){
            Node tree = new Node(0);
            LinkedList<Node> queue = new LinkedList<>();
            //LinkedList<Node> nextQueue = new LinkedList<>();
            queue.add(tree);
            int i = 1;
            while(i<=n){
                Node curNode = queue.poll();
                //值任意定义，不关心
                curNode.left = new Node(0);
                curNode.right = new Node(0);
                queue.add(curNode.left);
                queue.add(curNode.right);
                i++;
            }

           String result = blByCenter(tree);

            // 以上构建完毕
            // 以下遍历树
            Node cur = tree;
            while(cur!=null){
                cur = cur.left;
            }
            //找到了最左节点



        }


        return "";
    }

    //遍历一个节点
    public static String blByCenter(Node head){
        if(head == null){
            return "";
        }
        while(head != null){
            head = head.left;
        }
        //左节点
        result += "凹,";

        // 当前节点
        result += "凹,";

        // 右节点
        result +=  blByCenter(head.right);

        return result;

    }
}
