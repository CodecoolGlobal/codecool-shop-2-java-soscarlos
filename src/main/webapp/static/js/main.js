import {dataHandler} from "./dataManager.js";
import {loadButtons} from "./cartManager.js";
window.addEventListener("load", init);

const option = document.getElementById("1");

async function init() {
    console.log("IN INIT");
    await loadButtons();
    // let data = await dataHandler.getProductsByCategory(1);
    // console.log(data);
}

