//1、 有右树的话，找有树的1左节点
//2、无有树,往上走,直到找到是父节点的左子节点

/**
 * 求某个节点的中序遍历时的后继节点，该树有parent指针！
 */


function getSuccessorNode(node){

    console.log(`current = ${node.value}`);
    if(node === null){
        return node;
    }

    //如果有右节点
    if(node.right){
        node = node.right;
        while(node.left){
            node = right.left;
        }
        return node;
    }else{
        //如果没有右节点
        let parent = node.parent;
        while(parent.left !== node){
            if(parent.parent){
                parent = parent.parent
                node = node.parent;
            }else{
                return null;
            } 
        }
        return node;
    }
}


function Node(value){
    return{
        value,
        left:null,
        right:null,
        parent:null
    }
}



function test(){
    let root = Node(1);
    root.left = Node(2);
    root.left.parent = root;
    root.left.left = Node(3);
    root.left.left.parent = root.left;
    root.left.right = Node(4);
    root.left.right.parent = root.left;

    root.left.left.left = Node(5)
    root.left.left.left.parent = root.left.left;

    root.left.left.right = Node(7)
    root.left.left.right.parent = root.left.left;

    root.right = Node(6);
    root.right.parent = root;
    root.right.left = Node(8);
    root.right.left.parent = root.right;
    root.right.right = Node(9);
    root.right.right.parent = root.right;
    
    root.right.left.left = Node(10)
    root.right.left.left.parent = root.right.left;

    root.right.left.right = Node(11)
    root.right.left.right.parent = root.right.left;



    let node = getSuccessorNode(root.right.right);

    console.log(`node.value = ${node && node.value}`);
}

test();