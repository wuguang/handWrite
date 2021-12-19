console.log('I am go.js');

if(module.hot){
    module.hot.accept('./go.js',function(){
        console.log('I am go.js--- 1111');
    })
}