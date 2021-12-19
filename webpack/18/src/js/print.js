console.log('I am print.js');

if(module.hot){
    module.hot.accept('./print.js',function(){
        console.log('I am print.js--- i am change~~');
    })
}