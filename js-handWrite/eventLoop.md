
#### 事件循环机制


关键词:

同步、异步。

macro-task(宏任务):script 、setTimeout、setInterval 、setImmediate 、I/O 、UI rendering

micro-task(微任务) MutationObserver、Promise.then()或reject()
Mutation Observer API 用来监视 DOM 变动。比如节点的增减、属性的变动、文本内容的变动。


事件循环机制关心本质是：本质上关心得是代码的执行者顺序。
js单线程,因为有异步，代码执行顺序发生改变。

同步 

异步-- 微任务
       宏任务


先从一道题目进入

```javascript
console.log(0);

setTimeout(()=>{
    console.log(1);
},0);

console.log(2);

new Promise((resolve,reject)=>{
    console.log(3);
    setTimeout(()=>{
        console.log(4);
    },0);
    resolve();
}).then(()=>{
    console.log(5);
    setTimeout(()=>{
        console.log(6);
    },0);
}).then(()=>{
    console.log(7);
});

console.log(8);

```

分析:
主线称同步任务队列,从上到下:
```javascript
console.log(0);
console.log(2);
console.log(3);
console.log(8);
//------异步队列---微任务---
console.log(5);
console.log(7);
//------异步队列---宏任务---
console.log(1);
console.log(4);
console.log(6);
```

比较
```javascript

setTimeout(()=>{
    console.log(0);
})

new Promise((resolve,reject)=>{
    console.log(4);
    reject(5);
}).catch((e)=>{
    console.log(`e=${e}`);
})

new Promise((resolve,reject)=>{
    console.log(1);
    resolve();
}).then(()=>{
    console.log(2);
})

console.log(3);

```

vue next-tick更新视图异步原理之微任务的应用：
https://github.com/vuejs/vue/blob/dev/src/core/util/next-tick.js

React setState 更新是通过 batchedUpdates 标识进行，默认是合并更新，进入异步循环里，batchedUpdates会被置为false,可以同步更新了。
https://zhuanlan.zhihu.com/p/78516581

js代码执行循序优先按，主线程同步队列、异步线程微任务、异步线程宏任务的顺序，执行。
同步队列先执行完，不管任务有多长，完毕；将微任务队列加入同步任务队列，执行完毕；将异步任务队列加入同步任务队列,执行之.................

1、如果在执行微任务过程中有新的微任务如何排队
新的微任务在同步任务队列执行完毕，继续加入同步任务队列，不会执行异步任务队列的
例子
```javascript

setTimeout(()=>{
    console.log('macro-task---000001');
});
new Promise((resolve,reject)=>{
    resolve()
}).then(()=>{
    console.log('micro-task--001');
    return Promise.reject();
}).catch(e=>{
    console.log('micro-task--002');
});

```

2、如果如果宏任务执行过程中产生新的微任务如何排队
```javascript
setTimeout(()=>{
    console.log('macro-task-001');
    Promise.resolve().then(()=>{
        console.log(`macro-task-001==>new micro-task-001`);
        setTimeout(()=>{
            console.log(`macro-task-001==>new micro-task-001==>macro-task-001`);
        },0)
    });
});

setTimeout(()=>{
    console.log('macro-task-002');
});

setTimeout(()=>{
    console.log('macro-task-003');
});
```




