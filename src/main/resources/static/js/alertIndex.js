
const boton = document.getElementById('boton');
const formulario = document.getElementById('formulario');
const btnEliminar = document.getElementById('btnEliminar');

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

const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
    },
    buttonsStyling: false
})

swalWithBootstrapButtons.fire({
    title: 'Are you sure?',
    text: "You won't be able to revert this!",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'No, cancel!',
    reverseButtons: true
}).then((result) => {
    if (result.isConfirmed) {
            swalWithBootstrapButtons.fire(
        'Deleted!',
        'Your file has been deleted.',
        'success'
    )
  } else if (
    /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
  ) {
    swalWithBootstrapButtons.fire(
        'Cancelled',
        'Your imaginary file is safe :)',
        'error'
    )
  }
})