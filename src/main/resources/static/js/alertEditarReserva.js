const form = document.getElementById("formu")
const btn=document.getElementById("buttonEditar")

btn.addEventListener("click",(e)=>{
    e.preventDefault();
     Swal.fire({
  icon: 'success',
  title: 'Editado con éxito!',
  width: 500,
  padding: '3em',
  color: '#000',
  confirmButtonText: 'OK',
  background: '#fff url(/images/trees.png)',
  backdrop: `
    rgba(0,0,123,0.2)
    url("/img/gatito.gif")
    left top
    no-repeat
  `
}).then((result) => {
  if (result.isConfirmed) {
    form.submit();
  }
    })
})
    

function editar() {
   
    }