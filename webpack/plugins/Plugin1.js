class Plugin1{
    apply(complier){
        complier.hooks.emit.tap('Plugin1',(compilation)=>{
            console.log('emit.tap 111');
        })

        complier.hooks.emit.tapAsync('Plugin1',(compilation,cb)=>{
            setTimeout(()=>{
                console.log('tapAsync ~~~~111');
                cb();
            },1000)
        })

        complier.hooks.emit.tapPromise('Plugin1',(compilation)=>{
            return new Promise((resolve,reject)=>{
                setTimeout(()=>{
                    console.log('tapPromise ~~~~111');
                    resolve();
                },1000)
            })
            
        })

        complier.hooks.done.tap('Plugin1',(compilation)=>{
            console.log('done ~~~emit.tap 111');
        })
    }
}

module.exports = Plugin1;