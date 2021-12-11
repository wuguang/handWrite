import add from './add.js'
import count from './count.js';

console.log(add(1,2));
console.log(count(3,1));

setTimeout(()=>{
    console.log(add(3,4));
    console.log(count(5,6));
},1000);