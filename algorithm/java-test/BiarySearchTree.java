
import java.util.*;

public class BiarySearchTree {
    //检查是否是二叉搜索树
    public static boolean checkBST3(Node head){
        if(head !=null){
            int preValue = Integer.MIN_VALUE;
            Stack<Node> stack = new Stack<Node>();
            while(!stack.isEmpty() || head !=null){
                if(head!=null){
                    stack.push(head);
                    head = head.left;
                    //遍历找到最左端的点
                    //第一个点

                }else{
                    // 上一个节点 左边为null
                    // 回到 当前节点 head
                    // head.right

                    head = stack.pop();
                    if(head.value <= preValue){
                        return false;
                    }else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    public static boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<Node>();
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while(!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;
            if((l==null && r!=null)||(leaf && (l!=null||r!=null))){
                return false;
            }

            if(l!=null){
                queue.add(l);
            }

            if(r!=null){
                queue.add(r);
            }

            // 左右，有一个不双全的 结构，则 leaf 为true
            // 以后不能改变!
            if(l==null || r==null){
                leaf = true;
            }
        }
        
        return true;
    }

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

    // 处理一个节点，返回三个属性
    // 
    public static ReturnData process(Node x){
        if(x == null){
            return null;
        }
        
        ReturnData leftData = process(x.left);
        ReturnData rightData = process(x.right);

        int curMin = x.value;
        int curMax = x.value;
        
        if(leftData != null){
            curMin = Math.min(curMin,leftData.min);
            curMax = Math.max(curMax,leftData.max);
        }
        if(rightData != null){
            curMin = Math.min(curMin,rightData.min);
            curMax = Math.max(curMax,rightData.max);
        }
        boolean isBST = true;
        if(leftData!=null && (leftData.max >= x.value || !leftData.isBST)){
            isBST = false;
        }
        if(rightData!=null && (rightData.min <= x.value || !rightData.isBST)){
            isBST = false;
        }
        return new ReturnData(isBST,curMin,curMax);
    }

    public static void main(String[] args) {
        //test
        Node tree = new Node(5);
        tree.left = new Node(4);
        tree.left.left = new Node(3);
        tree.left.left.left = new Node(2);
        tree.right = new Node(6);
        tree.right.right = new Node(6);

        ReturnData result = process(tree);

        System.out.println("result = " + result.isBST);
    }
}