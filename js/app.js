//Barra de Navegación
var nav = document.querySelector('.nav_cont'); 
var sec = document.querySelector('#seccion_a'); 
var sec3 = document.querySelector('#seccion_c'); 
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
        sec.style.padding = "0 0 0 0"
        sec3.style.padding = "0 0 0 0"
        menu.style.top="155px"
        desp.style.margin="-5px 0 0 0"

        
    } else {
        nav.style.backgroundColor = 'black';
        nav.style.height = "100px";
        nav.style.transition = "all 0.5s"
        sec.style.transition = "all 0.5s"
        menu.style.transition = "all 0.5s"
        menu.style.top="28px"
        sec3.style.transition = "all 0.5s"
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

a.addEventListener('click', function(event){
    mostrar() 
})

b.addEventListener('click', function(event){
    mostrar()    
})

c.addEventListener('click', function(event){
    mostrar()    
})

// d.addEventListener('click', function(event){
//     mostrar()    
// })

// e.addEventListener('click', function(event){
//     mostrar()    
// })

