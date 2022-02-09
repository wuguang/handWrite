
function Node(value){
    return{
        value,
        left:null,
        right:null
    }
}
/*
任意2点的最近的公共祖先节点
*/
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

//获取 中序遍历的下一个节点!!
function getNextNodeForMiddleOrder(node){
    /*
    node.left;
    node.right;
    node.parent;
    */
    if(node === node.parent.left){
        return node.parent;
    }else if(node.right){
        //寻找有节点最左侧
        node = node.right;
        while(node.left){
            node = node.left;
        }
    }else{
        node = node.parent;
        while(node !== node.parent.left){
            if(node.parent === null){
                return null;
            }
            node = node.parent;
        }
        return node.parent;
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

