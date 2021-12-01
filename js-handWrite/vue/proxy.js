const target = {};

const p = new Proxy(target,{
    get(obj,prop){
        console.log(`get data =  ${obj[prop]}`);
        return obj[prop]
    },
    set(obj,prop,value){
        if(prop == 'name' && typeof value == 'string'){
            obj[prop] = value;
            return true;
        }else{
            console.log(`cannot set value target[${prop}] = ${value}`);
            return false;
        }
        
    }
});

p.name = 999;
console.log(target.name);
p.name = 'will';
console.log(target.name);