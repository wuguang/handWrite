// 定义promise的三个状态
const PENDING = "pending";
const FULFILLED = "fulfilled";
const REJECTED = "rejected";
function Promise(executor) {
  this.status = PENDING;
  this.value = "";
  this.reason = "";
  this.onFulfilled = []; //成功的回调
  this.onRejected = []; //失败的回调
  const resolve = (value) => {
    // 如果 resolve 的是 promise，则 new 出来的 promise 的状态由 resolve 参数中的 promise 决定
    if (value instanceof Promise) {
      return value.then(resolve, reject);
    }
    if (this.status === PENDING) {
      this.status = FULFILLED;
      this.value = value;
      this.onFulfilled.forEach((fn) => fn()); // 处理resolve函数异步执行
    }
  };
  const reject = (reason) => {
    if (this.status === PENDING) {
      this.status = REJECTED;
      this.reason = reason;
      this.onRejected.forEach((fn) => fn());
    }
  };
  try {
    // 如果Promise构造函数中的参数(回调函数)在执行过程中报错，
    // 则当前呢 new 的promsie对象的状态为 rejected，reason为报错的内容
    executor(resolve, reject);
  } catch (e) {
    reject(e);
  }
}

Promise.prototype.then = function (onFulfilled, onRejected) {
    onFulfilled =
      typeof onFulfilled === "function" ? onFulfilled : (value) => value;
    onRejected =
      typeof onRejected === "function"
        ? onRejected
        : (reason) => {
            throw reason;
            // return reason;
          };
    const queueMicrotask = (asyncTask)=>{
        setTimeout(()=>{
            asyncTask();
        },0); 
    }
    let promise2 = new Promise((resolve, reject) => {
      if (this.status === FULFILLED) {
        // 处理resolve同步执行
        // queueMicrotask实现微任务
        queueMicrotask(() => {
          // try、catch捕获执行onFulfilled函数执行过程的错误
          try {
            let x = onFulfilled(this.value);
            //  then方法返回的promise对象的状态由回调函数的返回值决定
            resolvePromise(promise2, x, resolve, reject);
          } catch (e) {
            reject(e);
          }
        });
      } else if (this.status === REJECTED) {
        queueMicrotask(() => {
          try {
            let x = onRejected(this.reason);
            resolvePromise(promise2, x, resolve, reject);
          } catch (e) {
            reject(e);
          }
        });
      } else if (this.status === PENDING) {
        // 处理resolve、reject异步执行
        // 思想：先把onFulilled函数存到数组中，当resolve函数异步执行时把数组中的元素（之前存的onFulfilled函数）执行
        this.onFulfilled.push(() => {
          queueMicrotask(() => {
            try {
              let x = onFulfilled(this.value);
              resolvePromise(promise2, x, resolve, reject);
            } catch (e) {
              reject(e);
            }
          });
        });
        this.onRejected.push(() => {
          queueMicrotask(() => {
            try {
              let x = onRejected(this.reason);
              resolvePromise(promise2, x, resolve, reject);
            } catch (e) {
              reject(e);
            }
          });
        });
      }
    });
    return promise2;
  };

  function resolvePromise(promise2, x, resolve, reject) {
    if (promise2 === x) {
      // 防止进入死循环
      reject(new TypeError("Chaining cycle"));
    }
    if ((x && typeof x === "object") || typeof x === "function") {
      let used;
      try {
        let then = x.then;
        if (typeof then === "function") {
          then.call(
            x,
            (y) => {
              if (used) return;
              used = true;
              resolvePromise(promise2, y, resolve, reject);
            },
            (r) => {
              if (used) return;
              used = true;
              reject(r);
            }
          );
        } else {
          if (used) return;
          used = true;
          resolve(x);
        }
      } catch (e) {
        if (used) return;
        used = true;
        reject(e);
      }
    } else {
      resolve(x);
    }
  }


//export default Promise;

Promise.defer = Promise.deferred = function () {
    let dfd = {};
    dfd.promise = new Promise((resolve, reject) => {
      dfd.resolve = resolve;
      dfd.reject = reject;
    });
    return dfd;
  };
  module.exports = Promise;


// npm install promises-aplus-tests -g
// promises-aplus-tests promise01.js


test();
function test(){
    new Promise((resolve,reject)=>{
        resolve('1');
    }).then(()=>{
        console.log('hello');
    });
}


