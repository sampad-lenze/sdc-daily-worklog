const dob = '09/10/1990'
let employee = {
    fName: 'sampad',
    lName: 'barman',
    age: 34,
    dob: dob
}

let employees = ['1', '2']
employees[2] = employee

function greet(name){
    return name + ', you are in greet function';
}

console.log(greet('javascript'))
