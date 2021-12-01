
function Node(value){
    return{
        value,
        left:null,
        right:null
    }
}


//判断 2个的公共 祖先节点是谁
function lowestAncestor(node,t1,t2){
    if(node === null || node ===t1 ||node===t2){
        return node;
    }
    let left = lowestAncestor(node.left,t1,t2);
    let right = lowestAncestor(node.right,t1,t2);
    if(left!==null && right!==null){
        return node
    }else{
        if(left!==null){
            return left;
        }else if(right !==null){
            return right;
        }else{
            return null;
        }
    }
    //return left!==null?left:right;
}


/*
求一个节点先序遍历的下一个节点是谁？ 
节省遍历的方法
*/
function getNextNodeForPreOrder(node){
    if(node.left !==null){
        return node.left;
    }else if(node.right !== null){
        return node.right;
    }else{
        let nParent = node.parent;
        if(nParent!==null && nParent.parent&& nParent.parent.right){
            return nParent.parent.right
        }
    }
}

/*
求一个节点中序遍历的下一个节点是谁？ 
节省遍历的方法
*/
function getNextNodeForMiddleOrder(node){
    let pNode = node.parent;
    //或最左侧节点
    let getLeftestNode = (node)=>{
        while(node.left!==null){
            node = node.left;
        }
        return node;
    }

    if(node.right!==null){
        return getLeftestNode(rightNode);
    }else if(node === pNode.left){
        return pNode;
    }else{
        if(pNode){
            let ppNode = pNode.parent;
            return getNextNodeForMiddleOrder(ppNode);
        }
        
        
    }

}



testgetNextNodeForMiddleOrder();

function testgetNextNodeForMiddleOrder(){
        let head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;
        let test = head.left.left;

		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.left.left.right;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.left;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.left.right;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.left.right.right;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.right.left.left;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.right.left;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.right;
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test).value);
		test = head.right.right; // 10's next is null
		console.log(test.value + " next: " + getNextNodeForMiddleOrder(test));
}


function main() {
    let head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);
    head.right.right.left = new Node(8);
    

    let o1 = head.right.right;
    let o2 = head.right.right.left;

    // ���β�ѯ--ԭ����
    console.log("o1 : " + o1.value);
    console.log("o2 : " + o2.value);
    console.log("ancestor : " + lowestAncestor(head, o1, o2).value);



}

//main();

