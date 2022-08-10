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
                    data.then(products => fillProducts(products));
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
                    data.then(products => fillProducts(products));
                }
            })
        })
    },

    async getProductsByCategory(id) {
        let data = await dataHandler.getProductsByCategory(id);
        return data;
    },

    async getProductsBySupplier(id) {
        let data = await dataHandler.getProductsBySupplier(id);
        return data;
    }
}

function fillProducts(products){
    productsContainer.innerHTML = "";
    for (const product of products) {
        let card = cardBuilder(product);
        productsContainer.insertAdjacentHTML("beforeend", card);
    }
    loadButtons();
}