import '../less/one.less';
import './print.js';
import './go.js';
let img01 = require('../assets/03.jpg');

console.log('hello, world~~ I am index.js');



let root = document.getElementById('root');
let myImg01 = new Image();
myImg01.src = img01;
myImg01.setAttribute('class','one');
root.appendChild(myImg01);

