const btnEliminar = document.getElementById('btnEliminarR');


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
                url: "/admin/reservas/eliminar/" + id

            });

            swalWithBootstrapButtons.fire(
                    'Eliminada',
                    'La reserva ha sido eliminada!',
                    'success'

                    ).then((result) => {
                if (result.isConfirmed) {
                    location.href = "/admin/reservas";
                }
            })
        } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
                ) {

            swalWithBootstrapButtons.fire(
                    'Cancelado',
                    'La reserva esta segura!',
                    'error'
                    )
        }
    })
}

