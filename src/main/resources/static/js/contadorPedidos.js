const menos = document.getElementById("menos")
const mas = document.getElementById("mas")
const contador = document.getElementById("numeroProducto")

let numero=parseFloat(contador.innerHTML);

menos.addEventListener("click", ()=>{
    if(numero>1){
    numero=numero-1
    contador.innerHTML=numero
    }
})

mas.addEventListener("click", ()=>{
    numero=numero+1
    contador.innerHTML=numero
    console.log("hola")

})