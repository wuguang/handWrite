const removeDuplicates = function(s, k) {
    let stack = []
    for(let c of s) {
        let prevArr = stack.slice((k-1)*(-1));
        if(prevArr.length === k-1){
            for(let i=0;i<k-1;i++){
                if(prevArr[i] !==c){
                    stack.push(c);
                    break;
                }
            }
            //否则弹出
            let n = 1;
            while(n<k-1){
                stack.pop();
                n++;
            }
        }else{
            stack.push(c);
        }
    }
    return stack.join('')
};



const removeDuplicates02 = function(s, k) {
    let stack = []
    for(let c of s) {
        let prev = stack.pop()
        if(!prev || prev[0] !== c) {
            stack.push(prev)
            stack.push(c)
        } else if(prev.length < k-1) {
            stack.push(prev+c)
        }
    }
    return stack.join('')
};


removeDuplicates02("pbbbccgggtttciiippoooaaais",3);



