import {dataHandler} from "./dataManager.js";

let categoryId;

export let productManager = {

    addEventListeners() {
        let categorySelectId = document.querySelector("#category-select");
        categorySelectId.addEventListener("change", function() {
            console.log(this.value);
        })
    },

    async getProducts(id) {
        let data = await dataHandler.getProducts(1);
    }
}