const e = require("express");

class Stack{
    constructor(){
        this.list = [];
    }
    push(val){
        this.list.push(val);
    }

    pop(){
        if(this.list.length>0){
            return this.list.shift();
        }
    }

    isEmpty(){
        return this.list.length === 0 ? true:false;
    }
}

function mockQueue(){
    let stack1 = new Stack();
    let stack2 = new Stack();

    return {
        pop(){
            if(!stack1.isEmpty){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            stack2.pop(); 
        },
        push(val){
            stack1.push(val)
        }
    }
}