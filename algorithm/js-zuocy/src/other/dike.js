function node({name,to}){
    return {
        weight,
        from:'',
        to:''
    }
}

let a = {
    name:'a',
    to:{
        c:12,
        d:15,
    }
};

let c = {
    name:'c',
    to:{
        end:10,
        d:1
    }
}

let d = {
    name:'d',
    to:{
        end:3
    }
}

let b = {
    name:'b',
    to:{
        e:9,
        f:12
    }
}

let e = {
    name:'e',
    to:[{
        node:end,
        weight:14
    }]
}


let f = {
    name:'f',
    to:[{
        node:end,
        weight:12
    }]
}



let start = {
    name:'start',
    to:[{
        node:a,
        weight:12
    },{
        node:b,
        weight:7
    }]
}


function dike(start){

}