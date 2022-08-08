import {dataHandler} from "./dataManager.js";
window.addEventListener("load", init);

const option = document.getElementById("1");

async function init() {
    console.log("IN INIT");
    let data = await dataHandler.getProductsByCategory(1);
    console.log(data);
}

