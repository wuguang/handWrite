package graph;
import java.util.ArrayList;

public class Node{
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    //this 必须写在构造方法里
    public Node(int value){
        this.value = value;
        this.in = 0;
        this.out = 0;
        //邻居节点
        this.nexts = new ArrayList<>();
        //和该点相连的边
        this.edges = new ArrayList<>();
    }
}
