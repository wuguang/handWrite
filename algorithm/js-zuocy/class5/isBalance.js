class Node{
    constructor(val,left=null,right=null){
        this.value = val;
        this.left = left;
        this.right = right;
    }
}

class IsBalanceTree{
    isBalanced(head){
        return this.process(head).isBalanced;
    }

    process(node){
        if(node===null){
            return{
                isBalanced:true,
                height:0
            }
        }
        let {left,right} = node;
        let leftObj = this.process(left);
        let rightObj = this.process(right);
        let height = Math.max(leftObj.height,rightObj.height) + 1;
        let heightAbs = Math.abs(leftObj.height-rightObj.height);
        let isBalanced = false;
        if(leftObj.isBalanced && rightObj.isBalanced && heightAbs<2){
            isBalanced = true;
        }
        return {
            height,
            isBalanced
        }
    }
}


test();

function test(){
    let head = new Node(1);

    head.left = new Node(2);
    head.right = new Node(3);

    head.left.left = new Node(4);
    head.left.right = new Node(8);

    head.right.left = new Node(7);
    head.right.right = new Node(9);
    head.right.right.left = new Node(10);
    head.right.right.left.left = new Node(12);

    console.log(new IsBalanceTree().isBalanced(head));
}