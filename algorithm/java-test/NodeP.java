public class NodeP{
    public Integer value = null;
    public NodeP right = null;
    public NodeP left = null;
    public NodeP parent = null;


    public NodeP(Integer val,NodeP p){
        value = val;
        parent = p;
    }

}
