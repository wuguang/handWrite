const {SyncHook,SyncBailHook,AsyncParallelHook, AsyncSeriesHook} = require('tapable');


class Lesson {
    constructor(){
        this.hooks = {
            go:new SyncBailHook(['address']),
            //leave:new AsyncParallelHook(['name','age']),
            leave:new AsyncSeriesHook(['name','age']),
        }
    }

    register(){
        /*
        this.hooks.go.tap('class0318',(address)=>{
            console.log('I am trigger~~~~~-----------class0318',address);
            //return true;
        })

        this.hooks.go.tap('class0410',(address)=>{
            console.log('I am class0410',address);
        })
        */

        this.hooks.leave.tapAsync('class0510',(name,age,cb)=>{
            setTimeout(()=>{
                console.log(`class0510~~~~~--- name = ${name}----age = ${age}`);
                cb();
            },1000)
            //return true;
        })

        this.hooks.leave.tapPromise('class060',(name,age)=>{
            return new Promise((resolve,reject)=>{
                setTimeout(()=>{
                    console.log(`class060~~~~~--- name = ${name}----age = ${age}`);
                    resolve();
                },1500)
            });
            //return true;
        })
    }

    trigger(){
        this.hooks.leave.callAsync('wuguang',18,()=>{
            console.log('i am end~~~');
        });

    }
}

let l = new Lesson;

l.register();
l.trigger();