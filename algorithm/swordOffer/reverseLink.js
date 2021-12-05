
function reverseLinkedList(linkedList){
    let head = linkedList;
    let next = head.next;
    if(head){
        if(next){
            reverseLinkedList(next);
        }
        console.log(head.value);
    }
}


test();

function test(){
    const node  = (val,next)=>{
        return {
            value:val,
            next:next||null
        }
    }

    let linkedList = node(1);
    linkedList.next = node(5);
    linkedList.next.next = node(8);
    linkedList.next.next.next = node(12);
    linkedList.next.next.next.next = node(18);
    linkedList.next.next.next.next.next = node(23);

    reverseLinkedList(linkedList);
}