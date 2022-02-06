import java.util.*;

public class IsFullTree {
    public static class Info{
        //高度
        public int height;
        //节点数
        public int nodes;

        // 每层节点数满足  2^(n-1)
        // 树总节点数 满足 2^n-1
        public Info(int h,int n){
            height = h;
            nodes = n;
        }
    }  
    
    public static Info f(Node x){
        if(x == null){
            //定义 null节点的Info内容
            return new Info(0,0);
        }
        Info leftInfo = f(x.left);
        Info rightInfo = f(x.right);
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info(height,nodes);
    }

    public static boolean isF(Node head){
        if(head == null){
            return true;
        }
        Info info = f(head);
        return info.nodes == ((1<<info.height) - 1);
    }

    public static boolean isFTree(Node head){
        if(head == null){
            return true;
        }
        LinkedList<Node> layerNodes = new LinkedList<>();
        LinkedList<Node> nextLayerNodes = new LinkedList<>();
        //第一层 初始化
        layerNodes.add(head);
        int curLayer = 1;
        boolean isFullBST = true;
        int curLayerNodes = 0;

        while(!layerNodes.isEmpty()){
            
            Node curNode = layerNodes.poll();
            curLayerNodes++;

            //为下一层做准备
            if(curNode.left != null){
                nextLayerNodes.add(curNode.left);
            }

            if(curNode.right != null){
                nextLayerNodes.add(curNode.right);
            }
            //当该层节点遍历完毕之后
            //计算节点是否满足 需求
            if(layerNodes.isEmpty()){
                if(curLayerNodes != Math.pow(2,curLayer-1)){
                    return false;
                }
                //满足 就看下一层
                // cur 和 next 交换
                layerNodes = nextLayerNodes;
                nextLayerNodes = new LinkedList<>();
                curLayerNodes = 0;
                curLayer ++;
            }
        }
        return isFullBST;
    }


    public static void main(String[] args) {
        //test
        Node tree = new Node(5);
        
        tree.left = new Node(4);
        tree.left.left = new Node(7);
        tree.left.right = new Node(8);
        tree.right = new Node(6);
        tree.right.left = new Node(7);
        tree.right.right = new Node(8); 

        System.out.println("result = " + isFTree(tree)) ;
    }

}
