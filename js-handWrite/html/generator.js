const path = require('path');
const fs = require('fs').promises;
const co = require('co');
function* read() {
    let content = yield fs.readFile(path.resolve(__dirname, './name.txt'), 'utf8');
    let age = yield fs.readFile(content, 'utf8');
    return age;
}

co(read()).then(data => {
    console.log(data);  // 10
}, err => {
    console.log(err);
})