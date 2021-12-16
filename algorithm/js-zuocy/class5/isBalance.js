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




class isBa02{
    isBalanced = false;
    constructor(head){
        this.isBalanced = this.processNode(head).isBalanced;
    }
    processNode(node){
        if(node === null){
            return {
                height:0,
                isBalanced:true
            }
        }else{
            let {left,right} = node;
            let leftResult = this.processNode(left);
            let rightResult = this.processNode(right);
            let curHeight = Math.max(leftResult.height, rightResult.height) + 1;
            let isBalanced = Math.abs(leftResult.height - rightResult.height)<=1?true:false;

            return {
                height:curHeight,
                isBalanced:isBalanced
            }
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
    //head.right.right.left.left = new Node(12);

    console.log(new isBa02(head).isBalanced);
}
