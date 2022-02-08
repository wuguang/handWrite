public class Code02_MaxDistance {

    public static void main(String[] args){
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.left.left = new Node(3);
        tree.left.right = new Node(4);
        tree.right = new Node(5);
        tree.right.left = new Node(6);
        tree.right.right = new Node(7);

        System.out.println("maxDistance = " + maxDistance(tree));
    }

    public static int maxDistance(Node head){
        return process(head).maxDistance;
    }

    public static class Info{
        public int maxDistance;
        public int height;
        public Info(int dis,int h){
            maxDistance = dis;
            height = h;
        }
    }

    public static Info process(Node x){
        if(x == null){
            //为空时 距离和高度都为0 
            return new Info(0,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + 1 + rightInfo.height;
        int maxDistance = Math.max(p3,Math.max(p1,p2));
        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        return new Info(maxDistance,height);

    }
}
