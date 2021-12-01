

var promisesAplusTests = require("promises-aplus-tests");
//https://segmentfault.com/a/1190000023157856


class MyPromise{

    static deferred(){
        let dfd = {};
        dfd.promise = new MyPromise((resolve, reject) => {
            dfd.resolve = resolve;
            dfd.reject = reject;
        })
        return dfd;
    }

    constructor(executor){
        this.statusMap = {
            PENDING:'pending',
            FULFILLED:'fulfilled',
            REJECTED:'rejected'
        }
        this.status = this.statusMap.PENDING;
        this.onFulfillFun = null;
        this.onRejectFun = null;
        this.catchCb = null;
        this.executor = executor;
        try{
            //this.catch();
            executor(this.resolve.bind(this),this.reject.bind(this));
            //this.then();
        }catch(e){
            this.exeCatchCb(e);
        }
    }

    exeCatchCb(e){
        if(typeof this.catchCb === 'function'){
            this.catchCb(e||this.rejectedReason);
        } 
    }

    resolve(resolvedData){
        if(this.status === this.statusMap.PENDING){
            this.status = this.statusMap.FULFILLED;
            this._doForThen(resolvedData);
        }
    }

    reject(rejectedReason){
        if(this.status === this.statusMap.PENDING){
            this.status = this.statusMap.REJECTED;
            this._doForThen(rejectedReason);
        }
    }

    _doForThen(resultData){
        try{
            if(this.status === this.statusMap.FULFILLED){
                if(typeof this.onFulfillFun === 'function'){
                    setTimeout(()=>{
                        let thenFulfillData = this.onFulfillFun(resultData);
                        if(thenFulfillData instanceof MyPromise){
                            this.nextPromise = Object.assign(thenFulfillData,{onFulfillFun:this.nextPromise.onFulfillFun,onRejectFun:this.nextPromise.onRejectFun});
                            //以上是同步任务额，一定是pending状态...
                        }else{
                            //用宏任务替代，微任务
                            setTimeout(()=>{
                                this.nextPromise.resolve(thenFulfillData);
                            },0);
                        }
                    },0);
                }else{
                    setTimeout(()=>{
                        this.nextPromise.resolve(resultData);
                    },0);
                }
            }else if(this.status === this.statusMap.REJECTED){
                if(typeof this.onRejectFun === 'function'){
                    setTimeout(()=>{
                        this.onRejectFun(resultData);
                    },0);
                }else{
                    setTimeout(()=>{
                        //this.onRejectFun(resultData);
                        this.nextPromise.reject(resultData);
                    },0);
                }
            }
        }catch(e){
            this.exeCatchCb(e);  
        }

        if(typeof this.finally === 'function'){
            this.finally();
        }   
    }

    then(onFulfillFun,onRejectFun){
        this.onFulfillFun = onFulfillFun;
        this.onRejectFun = onRejectFun;
        this.nextPromise = new MyPromise((resolve,reject)=>{
            
        });

        return this.nextPromise;
    }


    catch(catchCb){
        this.catchCb = catchCb;
        return this;
    }

    finally(finallyCb){
        this.finallyCb = finallyCb;
        return this;
    }
}


promisesAplusTests(MyPromise, function (err) {
    // All done; output is in the console. Or check `err` for number of failures.
    console.log(`error=${err}`);
});