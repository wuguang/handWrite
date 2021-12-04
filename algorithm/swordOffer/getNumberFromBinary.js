/**
 * 第10题
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 
 * 
 *
 */

function getNumberFromBinary(n){
    let result = 0;
    while(n!==0){
        result ++;
        n = n & (n-1);
    }
    return result;
}


/*
//32 位是java的
function getNumberFromBinary02(n){
    let result = 0;
    for(let i=0;i<32;i++){
        result += (n&1)
        n>>>1;
    }
    return result;
}
*/

test();

function test(){
    console.log(getNumberFromBinary02(2));
}


