const btnEliminar = document.getElementById('btnEliminarP');

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
                url: "/admin/pedidos/eliminar/" + id

            });

            swalWithBootstrapButtons.fire(
                    'Eliminado',
                    'El pedido ha sido eliminado!',
                    'success'

                    ).then((result) => {
                if (result.isConfirmed) {
                    location.href = "/admin/pedidos";
                }
            })
        } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
                ) {

            swalWithBootstrapButtons.fire(
                    'Cancelado',
                    'El pedido esta seguro!',
                    'error'
                    )
        }
    })
}

