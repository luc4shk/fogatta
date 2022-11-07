
const boton = document.getElementById('boton');
const formulario = document.getElementById('formulario');

boton.addEventListener("click", (e) => {

    if(document.getElementById("nombre").value == "" || document.getElementById("email").value == "" || document.getElementById('mensaje') == ''){
    }
    else{
        e.preventDefault();
        let timerInterval
        Swal.fire({
            icon: 'success',
            html: 'Su mensaje se enviará en <b></b> milisegundos.',
            timer: 3000,
            timerProgressBar: true,
            
            didOpen: () => {
                Swal.showLoading()
                const b = Swal.getHtmlContainer().querySelector('b')
                timerInterval = setInterval(() => {
                    b.textContent = Swal.getTimerLeft()
                }, 100)
                
            },
            willClose: () => {
                clearInterval(timerInterval)
                formulario.submit();
            }
        })
    }
})