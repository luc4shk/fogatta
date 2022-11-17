

const btnEliminar = document.getElementById('btnEliminar');


function eliminar(id) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: true
    })
    swalWithBootstrapButtons.fire({

        title: '¿Estás seguro?',
        text: "No podras revertír estos cambios",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Si, eliminar!',
        cancelButtonText: 'No, cancelar!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "/admin/producto/eliminar/" + id

            });

            swalWithBootstrapButtons.fire(
                    'Eliminado',
                    'Tu producto ha sido eliminado!',
                    'success'

                    ).then((result) => {
                if (result.isConfirmed) {
                    location.href = "/admin/home";
                }
            })
        } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
                ) {

            swalWithBootstrapButtons.fire(
                    'Cancelado',
                    'Tu producto esta seguro!',
                    'error'
                    )
        }
    })
}




