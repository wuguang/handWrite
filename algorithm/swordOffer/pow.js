function pow(base,exp){
    if(exp===0){
        return 1;
    }

    if(exp <0 && base===0){
        throw error('0的负数次方无意义');
    }

    let result = base;

    //负次方以倒数处理
    if(exp<0){
        exp *= -1;
        result = 1/base;
        base = 1/base;
    }

    if(exp>0){
        while(exp>1){
            result *= base;
            exp--;
        }
    }

    return result;
}


test();

function test(){
    console.log(pow(2,3));
    console.log(pow(2,-3));
}