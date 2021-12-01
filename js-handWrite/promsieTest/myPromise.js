const { resolvePromise } = require("./KPromise-master/ForPromises_A_PlusTest/KPromise");


const utils ={};
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

        onFulfilled = typeof onFulfilled === 'function'?onFulfilled:(value)=>value;
        onRejected = typeof onRejected === 'function'?onRejected:(reason)=> {throw reason};

        const micorTask = (fn)=>{
            setTimeout(fn);
        }

        //第二个promise 的 resolve 及 reject
        const resolvePromise =(promise2,x,resolve,reject)=>{
            // ??? why
            //resolvePromise;
            if(promise2 === x){
                reject(new TypeError('A nested loop'));
            }

            //thenable 对象
            if(x && typeof x.then === 'function'){
                x.then((y)=>{
                    resolvePromise(promise2,y,resolve, reject);
                })
            }else{
                //then里回调结果，当作下一个then的参数,执行resolve,触发状态改变
                resolve(x);
            }
        }
        
        let promise2 = new Promise((resolve,reject)=>{
            //此处的this指向promise2
            this.onFulfilledCbs.push(()=>{
                micorTask(()=>{
                    try{
                        const x = onFulfilled(this.value);
                        //合理安排 resolve、及reject的执行就行了
                        resolvePromise(promise2,x,resolve,reject);
                    }catch(e){
                        reject(e);
                    }
                });
            });

            this.onRejectedCbs.push(()=>{
                micorTask(()=>{
                    try{
                        const x = onRejected(this.reason);
                        resolvePromise(promise2,x,resolve,reject)
                    }catch(e){
                        reject(e);
                    }
                });
            });
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

// npm install promises-aplus-tests -g
// promises-aplus-tests myPromise.js

