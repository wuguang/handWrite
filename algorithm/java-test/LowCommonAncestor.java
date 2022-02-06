import java.util.*;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

public class LowCommonAncestor {


    public static Node LowCommonAncestor(Node head,Node n1,Node n2){
        HashMap<Node,Node> fatherMap = new HashMap<>();
        fatherMap.put(head,null);
        //所有节点 先序方式建立Map映射
        process(head,fatherMap);
        HashSet<Node> nodeSet = new HashSet<>();
        Node target = null;

        //从低网上回溯
        Node cur = n1;
        //父节点，没有包括自己
        while(fatherMap.get(cur) != null){
            nodeSet.add(cur);
            cur = fatherMap.get(cur);
        }

        cur = n2;
        //父节点，没有包括自己
        while(fatherMap.get(cur) != null){

            if(nodeSet.contains(cur)){
                target = cur;
                break;
            }
            cur = fatherMap.get(cur);
        }

        return target;
    }

    public static void process(Node head,HashMap<Node,Node> fatherMap){
        if(head == null){
            return;
        }
        fatherMap.put(head.left,head);
        fatherMap.put(head.right,head);
        process(head.left,fatherMap);
        process(head.right,fatherMap);
    }

    public static Node lowestAncestor(Node head,Node n1, Node n2){
        //遇到空 返回空
        // 遇到制定节点  返回指定节点

        if(head == null || head == n1 || head == n2){
            return head;
        }

        Node left = lowestAncestor(head.left,n1,n2);
        Node right = lowestAncestor(head.right,n1,n2);

        //返回自己，自己就是最低公共祖先
        if(left != null && right != null){
            return head;
        }

        //有限返回不为空的节点
        return left !=null?left:right;
    }

    public static Node lowestAncestor02(Node head, Node n1, Node n2){
        if(head == null){
            return null;
        }
        if(n1 == n2){
            return n1;
        }
        if(head == n1 ||head == n2){
            return head;
        }
        Node leftNode = lowestAncestor02( head.left, n1, n2);
        Node rightNode = lowestAncestor02( head.right, n1, n2);
        if(leftNode !=null && rightNode != null){
            //就是它
            return head;
        }

        //优先返回 不为空的节点
        return leftNode !=null?leftNode:rightNode;

    }

    // 用 NodeP  
    // 找出下一个中序遍历的后继节点
    public static NodeP findParent(NodeP n1){
        // head
        NodeP target = null;
        //有节点的最左节点
        if(n1.right!= null){
            //初始化节点
            NodeP cur = n1.right;
            //有节点的 最左子节点
            while(cur.left!=null){
                cur = cur.left;
            };
            target = cur;
            return target;
        }else{
            NodeP cur = n1;
            while(cur!=null){
                //是父节点的左节点
                if(cur.parent !=null && cur == cur.parent.left){
                    target = cur.parent;
                    break;
                }
                cur = cur.parent;
            }
            return target;
        }
    }


    // 先序的形式 序列化一棵树

    public static String serialTreeByPre(Node head){
        if(head == null){
            return "#_";
        }
        String target = "";
        target += "_" + head.value.toString();
        target += serialTreeByPre(head.left);
        target += serialTreeByPre(head.right);
        return target;
    }

    //将以上字符串反序列换
    public static Node unSerialTreeByPre(String treeStr){
        String[] list = treeStr.split("_");
        Node tree = null;
        int i = 0;
        Queue<String> queue = new LinkedList<>();
        while(i<list.length){
            queue.add(list[i]);
        }   
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.parseInt((value)));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /*
    public static Node buildCurNode(){

    }
    */

    public static void main(String[] args) {
        //test
        NodeP tree = new NodeP(0,null);
        tree.right = new NodeP(1,tree);
        tree.left = new NodeP(4,tree);
        tree.left.left = new NodeP(5,tree.left);

        tree.right.right = new NodeP(2,tree.right);
        tree.right.right.right = new NodeP(3,tree.right.right);

        NodeP target = findParent(tree.right.right.right);

        if(target != null){
            System.out.println("target.value = " + target.value);
        }else{
            System.out.println("target.value = " + null);
        }
        

    }


}