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
        menu.style.top="155px"
        desp.style.margin="-5px 0 0 0"

        
    } else {
        nav.style.backgroundColor = 'black';
        nav.style.height = "100px";
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

let url ="https://api.generadordni.es/v2/profiles/person?results=20";
    fetch(url)
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.log(error))


function impresion(data){
let respuesta=""
let tipo=""
    for(let i=0;i<data.length;i++){
        if(i%2==0){
            tipo="Activo"
        }
        else{
            tipo="En espera"
        }
        document.getElementById("reseñas").innerHTML += `
        <div class="individual_res">
                    <div class="res_up">
                        <div class="res_up_img"></div>
                        <div class="res_up_info">
                            <p class="res_nombre">Luis Ascencio</p>
                            <p class="res_fecha">15/05/25</p>
                            <p class="res_puntuacion">⭐⭐⭐⭐⭐</p>
                        </div>
                    </div>
                    <textarea class="res_text"name="" id="" cols="30" rows="10" disabled>En cuanto a la comida es excelente los platillos abundantes y de muy buen gusto, vale la pena poder visitar este lugar donde está lleno de ambiente y servicio de primera. En cuanto a la comida es excelente los platillos abundantes y de muy buen gusto, vale la pena poder visitar este lugar donde está lleno de ambiente y servicio de primera.</textarea>
                </div>
        `;

        
    }

}

function agregarFila() {

}
