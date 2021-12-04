/*
public static long getFibonacci2(int n) {
    long lastNum = 1;
    long preNum = 1;
    if (n <= 0)
        return 0;
    if (n == 1 || n == 2) {
        return 1;
    }

    while (n-- > 2) {
        preNum += lastNum;
        lastNum = preNum - lastNum;
    }
    return preNum;
}
*/

function getFibonacci(n) {
    let lastNum = 1;
    let preNum = 1;
    if (n <= 0)
        return 0;
    if (n == 1 || n == 2) {
        return 1;
    }
    let index = 2;
    while (index < n) {
        index++;
        preNum += lastNum;
        lastNum = preNum - lastNum;
    }
    return preNum;
}

console.log(getFibonacci(10));

//1,1,2,3,5,8,13,21,34,55