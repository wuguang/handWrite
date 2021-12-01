const obj = {};
Object.defineProperty(obj,'name',{
    value:'will',
    writable:false
})

obj.name = 'mynameis';

console.log(obj,obj.name);

Object.defineProperty(obj,'age',{
    get(){
        console.log(`get data `);
        return age;
    },
    set(newValue){
        console.log(`set data,key=age; obj.age =${newValue}`);
        age = newValue;
    }
});

obj.age = 16;
console.log(`${obj.age}`);