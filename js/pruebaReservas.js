//Barra de Navegación
var nav = document.querySelector('.nav_cont'); 
var nav2 = document.querySelector('.nav'); 
var menu = document.querySelector('.menu'); 
var desp = document.querySelector('.desp'); 
var a = document.querySelector('.uno'); 
var b = document.querySelector('.dos'); 
var c = document.querySelector('.tres');
var d = document.querySelector('.cuatro'); 
var e = document.querySelector('.cinco'); 




window.addEventListener('scroll', function(event) { 
    if (window.scrollY <= 90) { 
        nav.style.backgroundColor = 'transparent'; 
        nav.style.height = "200px";
        menu.style.top="155px"
         desp.style.margin="-5px 0 0 0"
        // nav2.style.height = "200px";

        
    } else {
        nav.style.backgroundColor = 'black';
        nav.style.height = "100px";
        // nav2.style.height = "100px";
        nav.style.transition = "all 0.5s"
        menu.style.transition = "all 0.5s"
        menu.style.top="28px"
        desp.style.margin="-100px 0 0 0"
    }
});

function mostrar(){
    desp.classList.toggle('mostrar')
    a.classList.toggle('mostrar2')
    b.classList.toggle('mostrar2')
    c.classList.toggle('mostrar2')
    d.classList.toggle('mostrar2')
    e.classList.toggle('mostrar2')
}
menu.addEventListener('click', function(event){
    mostrar()

})

// a.addEventListener('click', function(event){
//     mostrar() 
// })

// b.addEventListener('click', function(event){
//     mostrar()    
// })

// c.addEventListener('click', function(event){
//     mostrar()    
// })

// d.addEventListener('click', function(event){
//     mostrar()    
// })

// e.addEventListener('click', function(event){
//     mostrar()    
// })

// let url ="https://api.generadordni.es/v2/profiles/person?results=20";
//     fetch(url)
//         .then(response => response.json())
//         .then(data => impresion(data))
//         .catch(error => console.log(error))


// function impresion(data){
// let respuesta=""
// let tipo=""
//     for(let i=0;i<data.length;i++){
//         if(i%2==0){
//             tipo="Activo"
//         }
//         else{
//             tipo="En espera"
//         }
//         document.getElementById("tbody").innerHTML += `
//         <tr>
//             <td>${4+i}</td>
//             <td>${data[i].name} ${data[i].surname}</td>
//             <td>${tipo}</td>
//             <td>${data[i].birthdate}</td>
//             <td><a href="#"><div class="editar"></div></a></td>
//             <td><a href="#"><div class="eliminar"></div></a></td>
//         </tr>
//         `;

        
//     }

// }

// function agregarFila() {

// }
