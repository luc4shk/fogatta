const menos = document.getElementById("menos");
const mas = document.getElementById("mas");
const contador = document.getElementById("numeroProducto");
const bebida = document.querySelector("#bebida");
const comida = document.querySelector("#comida");
const precio = document.getElementById("precio");

let numero = parseFloat(contador.innerHTML);
let bebidaSeleccionada;
let comidaSeleccionada;

const actualizarPrecio = () => {
  bebidaSeleccionada = bebida.options[bebida.selectedIndex].value;
  comidaSeleccionada = comida.options[comida.selectedIndex].value;
  const res = (parseFloat(bebidaSeleccionada) + parseFloat(comidaSeleccionada)) * numero;
  precio.innerHTML = `$${res}`;
};

window.addEventListener("load", () => {
  actualizarPrecio();
});

bebida.addEventListener("change", actualizarPrecio);
comida.addEventListener("change", actualizarPrecio);

menos.addEventListener("click", () => {
  if (numero > 1) {
    numero -= 1;
    contador.innerHTML = numero;
    actualizarPrecio();
  }
});

mas.addEventListener("click", () => {
  numero += 1;
  contador.innerHTML = numero;
  actualizarPrecio();
});
