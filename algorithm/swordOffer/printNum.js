function printNum(n){
    let len = n.toString();
    let targetNum = '';
    let curLen = 1;
    let first = true;
    while(curLen<=len){
        let i=0
        for(;i<length;i++){
            if(first){
                first = false;
            }
            console.log(targetNum+i+'');
        }
        //while(targetNum)

        for(let j=targetNum.length-1; j>=0; j--){
            let curNum = targetNum.chat(j);
            if(Number(curNum) + 1>9){}
        }

        curLen  += 1;
    }


}