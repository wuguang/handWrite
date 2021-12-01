class MyPromise{
    /***
     * PENDING
     * FULFILLED
     * REJECTED
     * ***/
    constructor(fn){
        if(typeof fn !== 'function'){
            console.error(`MyPromise实例化时必须传入一个函数作为参数,当前传入参数类型为：${typeof fn}`);
            return;
        }
        this.status = 'PENDING';
        this.value = null;
        this.reason = null;
        this.onFulfilledCbs = [];
        this.onRejectedCbs = [];

        const resolve = (value)=>{
            if (value instanceof MyPromise) {
                return value.then(resolve, reject);
            }
            this.value = value;
            if(this.status === 'PENDING'){
                this.status = 'FULFILLED';
                this.onFulfilledCbs.forEach(sFn=>{
                    sFn();
                });
            }
        } 
        
        const reject = (reason)=>{
            this.reason = reason;
            if(this.status === 'PENDING'){
                this.status = 'REJECTED';
                this.onRejectedCbs.forEach(fFn=>{
                    if(typeof item === 'function'){
                        fFn();
                    }
                })
            }  
        }

        try{
            fn(resolve,reject);
        }catch(e){
            this.reason = e;
            reject(e);
        }
    }

    //可选参数
    then(onFulfilled, onRejected){
        /*
        2.2.3: If `onRejected` is a function,
        2.2.3.1: it must be called after `promise` is rejected, with `promise`’s rejection reason as its first argument.
        */
        onFulfilled = typeof onFulfilled === 'function'?onFulfilled:(value)=>value;
        onRejected = typeof onRejected === 'function'?onRejected:(reason)=> {throw reason};

        let promise2 = null;

        const micorTask = (fn)=>{
            setTimeout(fn);
        }


        //第二个promise 的 resolve 及 reject
        const resolvePromise =(promise2,x,resolve,reject)=>{
            // ??? why
            //resolvePromise;
            try{
                if(promise2 === x){
                    new TypeError("Chaining cycle");
                }
            }catch(e){
                reject(e);
            }
            
            let used;

            //thenable 对象
            if(x && typeof x.then === 'function'){
                x.then((y)=>{
                    if (used) return;
                    used = true;
                    resolvePromise(promise2,y,resolve, reject);
                },(r) => {
                    if (used) return;
                    used = true;
                    reject(r);
                })
            }else{
                if (used) return;
                used = true;
                //then里回调结果，当作下一个then的参数,执行resolve,触发状态改变
                resolve(x);
            }
        }

        const onFulfilledExecute = (promise2,resolve,reject)=>{
            micorTask((promise2)=>{
                try{
                    const x = onFulfilled(this.value);
                    //合理安排 resolve、及reject的执行就行了
                    resolvePromise(promise2,x,resolve,reject);
                }catch(e){
                    reject(e);
                }
            });
        }

        const onRejectedExecute = (promise2,resolve,reject)=>{
            micorTask(()=>{
                console.log(`async~~~ `);
                try{
                    const x = onRejected(this.reason);
                    //合理安排 resolve、及reject的执行就行了
                    resolvePromise(promise2,x,resolve,reject);
                }catch(e){
                    reject(e);
                }
            });
        }
        
        promise2 = new Promise((resolve,reject)=>{
            if(this.status === 'PENDING'){
                //此处的this指向promise2
                this.onFulfilledCbs.push(()=>{
                    onFulfilledExecute(promise2,resolve,reject);
                });

                this.onRejectedCbs.push(()=>{
                    onRejectedExecute(promise2,resolve,reject);
                });
            }

            if(this.status === 'FULFILLED'){
                onFulfilledExecute(promise2,resolve,reject);
            }

            if(this.status === 'REJECTED'){
                console.log(`sync `);
                onRejectedExecute(promise2,resolve,reject);
            }
        })

        return promise2;
    }

}

Promise.defer = Promise.deferred = function () {
    let dfd = {};
    dfd.promise = new MyPromise((resolve, reject) => {
        dfd.resolve = resolve;
        dfd.reject = reject;
    });
    return dfd;
};
module.exports = Promise;



//test();
function test(){
    /*
    new MyPromise((resolve,reject)=>{
        resolve('1');
    }).then(()=>{
        console.log('hello');
    });
    */
    new MyPromise((resolve=null,reject)=>{
        reject('no!!');
    }).then(null, function onRejected(reason) {
        console.log(`reason= ${reason}`);
    });

    console.log(`out ~~ reason=` );
}


// npm install promises-aplus-tests -g
// promises-aplus-tests myPromise.js

