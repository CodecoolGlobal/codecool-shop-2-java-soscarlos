import {dataHandler} from "./dataManager.js";
import {cardBuilder} from "./htmlFactory.js";
import {loadButtons} from "./cartManager.js";

const productsContainer = document.getElementById("products");

export let productManager = {

    addEventListeners() {
        let categorySelectId = document.querySelector("#category-select");
        categorySelectId.addEventListener("change", function() {
            let options = document.querySelectorAll("option");
            options.forEach(option => {
                if (option.text === this.value) {
                    let id = option.id;
                    let data = productManager.getProductsByCategory(id);
                    data.then(products => fillContainer(products));
                }
            })
        });

        let supplierSelectId = document.querySelector("#supplier-select");
        supplierSelectId.addEventListener("change", function() {
            let options = document.querySelectorAll("option");
            options.forEach(option => {
                if (option.text === this.value) {
                    let id = option.id;
                    let data = productManager.getProductsBySupplier(id);
                    data.then(products => fillContainer(products));
                }
            })
        })
    },

    async getProductsByCategory(id) {
        return await dataHandler.getProductsByCategory(id);
    },

    async getProductsBySupplier(id) {
        return await dataHandler.getProductsBySupplier(id);
    }
}

function fillContainer(products){
    productsContainer.innerHTML = "";
    for (const product of products) {
        let card = cardBuilder(product);
        productsContainer.insertAdjacentHTML("beforeend", card);
    }
    loadButtons();
}
