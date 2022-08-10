import {dataHandler} from "./dataManager.js";

export let productManager = {

    addEventListeners() {
        let categorySelectId = document.querySelector("#category-select");
        categorySelectId.addEventListener("change", function() {
            let options = document.querySelectorAll("option");
            options.forEach(option => {
                if (option.text === this.value) {
                    let id = option.id;
                    let data = productManager.getProductsByCategory(id);
                    data.then(product => console.log(product)); //TODO: Pass to HTMLFactory
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
                    data.then(product => console.log(product)); //TODO: Pass to HTMLFactory
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