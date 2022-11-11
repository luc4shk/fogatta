
const formulario = document.getElementById('formulario');
const inputs = document.querySelectorAll('#formulario input');

const expresiones = {
    nombre: /^[a-zA-ZÀ-ÿ\s]{3,20}$/, // Letras y espacios, pueden llevar acentos.
    apellido: /^[a-zA-ZÀ-ÿ\s]{3,30}$/, // Letras y espacios, pueden llevar acentos.
    correo: /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/,
    password: /^.{10,15}$/, // 5 a 15 digitos
    telefono: /^\d{10}$/, // 10 digitos (solo números)
    direccion: /^.{5,30}$/ // Letras y espacios, pueden llevar acentos.
}

const  campos = {
    nombre: false,
    apellido: false,
    email: false,
    password: false,
    telefono: false,
    direccion: false,
    fecha_nacimiento: false
}

const validarFormulario = (e) => {

    switch(e.target.name){
        case "nombre":
            validarCampo(expresiones.nombre, e.target, 'nombre');
        break;
        case "apellido":
            validarCampo(expresiones.apellido, e.target, 'apellido');
        break;
        case "email":
            validarCampo(expresiones.correo, e.target, 'email');
        break;
        case "password":
            validarCampo(expresiones.password, e.target, 'password');
        break;
        case "num_celular":
            validarCampo(expresiones.telefono, e.target, 'telefono');
        break;
        case "direccion":
            validarCampo(expresiones.direccion, e.target, 'direccion');
        break;
        case "fecha_nacimiento":
            validarFecha();
        break;
    }

}


const validarFecha = () => {

    const campo_fecha = document.querySelector('input[type="date"]').value;

    const valor_fecha = new Date(campo_fecha)
    
    valor_fecha.setMinutes(valor_fecha.getMinutes() + valor_fecha.getTimezoneOffset());

    let fecha_actual = new Date();
    let dia, mes, anio;

    dia = fecha_actual.getDate();
    mes = fecha_actual.getMonth();
    anio = fecha_actual.getFullYear();

    let fecha_actual_final = new Date(anio, mes, dia);

    let calculoAnio = calcularEdad(valor_fecha);/*fecha_actual_final.getFullYear() - valor_fecha.getFullYear();*/

    if(valor_fecha > fecha_actual_final || calculoAnio < 18 || calculoAnio > 75){
        document.querySelector(`#grupo__fecha_nacimiento .formulario__input-error`).classList.add('formulario__input-error-activo');
    }
    else{
        document.querySelector(`#grupo__fecha_nacimiento .formulario__input-error`).classList.remove('formulario__input-error-activo');
        campos["fecha_nacimiento"] = true;
    }


}


function calcularEdad(fecha_nacimiento) {
    var fecha2 = new Date();
    let dia, mes, anio;
    dia = fecha2.getDate();
    mes = fecha2.getMonth();
    anio = fecha2.getFullYear();
    let fecha3 = new Date(anio,mes,dia);
    var cumpleanos = new Date(fecha_nacimiento);
    var edad = fecha3.getFullYear() - cumpleanos.getFullYear();
    var m = fecha3.getMonth() - cumpleanos.getMonth();
    if (m < 0 || (m === 0 && fecha3.getDate() < cumpleanos.getDate())) {
        edad--;
    }
    return edad;
}


const validarCampo = (expresion, input, campo) => {

    if(expresion.test(input.value)){
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
    }
    else{
        document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
        document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;

    }

}

inputs.forEach((input) => {
    input.addEventListener('keyup', validarFormulario);
    input.addEventListener('blur', validarFormulario);
});


function validarDatos(){

    let valido = false;

    if(campos.nombre && campos.apellido && campos.email && campos.password && campos.telefono && campos.direccion &&  campos.fecha_nacimiento){

        valido = true;

    }
    else{
        document.getElementById('msjGlobal').innerHTML = "Favor rellenar los campos del formulario correctamente";
    }

    return valido;

}

