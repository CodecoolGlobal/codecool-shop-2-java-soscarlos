import {productManager} from "./productManager.js";

window.addEventListener("load", init);

async function init() {
    productManager.addEventListeners();
}

