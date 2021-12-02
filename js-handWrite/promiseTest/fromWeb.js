class Promise {
    // 构造器
    constructor(executor) {
        
    }
    // then 方法
    then(onFulfilled, onRejected) {
      // onFulfilled 为可选参数 如果不为函数 则忽略onFulfilled 直接返回 value
      onFulfilled = isFunction(onFulfilled) ? onFulfilled : value => value;
      // onRejected 为可选参数 如果不为函数 则忽略onFulfilled 直接抛出错误
      onRejected = isFunction(onRejected) ? onRejected : error => { throw error};
      // 如果 Promise 为 成功状态 调用 onFulfilled 方法 传入成功的 value
      if(this.state === "fulfilled") {
        // 异步调用
        setTimeout(() => {
          try {
            let x = onFulfilled(this.value);
            resolvePromise(promise2, x, resolve, reject);
          } catch (error) {
            reject(error);
          }
        }, 0);
      }
      // 如果 Promise 为 失败状态 调用 onRejected 方法 传入失败的 reason
      if (this.state === "rejected") {
        // 异步调用
        setTimeout(() => {
          try {
            let x = onRejected(this.reason);
            resolvePromise(promise2, x, resolve, reject);
          } catch (error) {
            reject(error);
          }
        }, 0);
      }
      // 如果 Promise 为 等待状态 
      // 由于不知道 是成功还是失败，则需要两个数组，
      // 将成功或失败各自对应的方法 push 到各自的数组当中
      if (this.state === "pending") {
        // onFulfilled 传入到成功数组
        this.onResolvedCallbacks.push(() => {
          // 异步调用
          setTimeout(() => {
            try {
              let x = onFulfilled(this.value);
              resolvePromise(promise2, x, resolve, reject);
            } catch (error) {
              reject(error);
            }
          }, 0);
        });
        // onRejected 传入到成功的数组
        this.onRejectedCallbacks.push(() => {
          // 异步调用
          setTimeout(() => {
            try {
              let x = onRejected(this.reason);
              resolvePromise(promise2, x, resolve, reject);
            } catch (error) {
              reject(error);
            }
          }, 0);
        });
      }
    }
    // catch 方法
    catch() {}
    // finall 方法
    finally() {}
  }
  
  Promise.resolve = () => {}
  Promise.reject = () => {}
  Promise.all = () => {}
  Promise.allSettled = () => {}
  Promise.race = () => {}
  
  function resolvePromise(promise2, x, resolve, reject) {
    // 如果 x 和 promise2 指向相同
    if (x === promise2) {
      reject(new TypeError("Chaining cycle detected for promise"));
    }
    // 如果x为promise 则采用其状态
    if (isPromise(x)) {
      if (x.state === "pending") {
        return x.then(()=> {
          resolve(x.value);
        }, () => {
          reject(x.reason);
        })
      }
      if (x.state === "fulfilled") {
        return resolve(x.value);
      }
      if (x.state === "rejected") {
        return reject(x.reason);
      }
    } else if (isObject(x) || isFunction(x)) {
      // 如果x是对象或者函数
      let then;
      // 取 x.then
      try {
        then = x.then;
      } catch (error) { // 取x.then 如果报错则直接失败
        reject(error);
      }
      // 如果 then 是 函数
      if (isFunction(then)) {
        // 只可以调用一个
        let isCalled = false;
        try {
          then.call(x, y => {
            if (isCalled) {
              return;
            }
            isCalled = true;
            resolvePromise(promise2, y, resolve, reject);
          }, r => {
            if (isCalled) {
              return;
            }
            isCalled = true;
            reject(r);
          })
        } catch (error) { // 执行then报错则直接返回失败
          if (isCalled) {
            return;
          }
          isCalled = true;
          reject(error);
        }
      } else { // 如果不是方法 则把x作为promsie的值
        resolve(x);
      }
    } else { // x 既不是方法也不是函数  则把x作为promsie的值
      resolve(x);
    }
  }
  // 辅助方法
  // 判断 obj 是不是 Function
  function isFunction(obj) {
    return !!obj && typeof obj === "function";
  }
  // 判断 obj 是不是 Object
  function isObject(obj) {
    return !!obj && typeof obj === "object";
  }
  // 判断 obj 是不是 Promise
  function isPromise(obj) {
    return !!obj && (typeof obj === "object" || typeof obj === "function") && typeof obj.then === "function";
  }