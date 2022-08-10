import {dataHandler} from "./dataManager.js";
window.addEventListener("load", init);

const option = document.getElementById("1");

async function init() {
    console.log("IN INIT");
    let data = await dataHandler.getProductsByCategory(1);
    console.log(data);
}

document.querySelectorAll(".btn").forEach(button => {
    button.addEventListener('click', increaseCartItemNumber)
    function increaseCartItemNumber() {
        let cart = document.getElementById("cart-count");
        let number = parseInt(cart.textContent, 10) + 1;
        cart.textContent = number.toString();
    }
})

