function getNumberOfOne(n){
    let result = 0;
    for(let i=0;i<31;i++){
        result += (n&1);
        n>>>1;
    }
    return result;
}



function numberOfOne2(n) {
    // 记录数字中1的位数
    let result = 0;

    // 数字的二进制表示中有多少个1就进行多少次操作
    while (n != 0) {
        result++;
        // 从最右边的1开始，每一次操作都使n的最右的一个1变成了0，
        // 即使是符号位也会进行操作。
        n = (n - 1) & n;
    }

    // 返回求得的结果
    return result;
}

console.log(numberOfOne2(11));










