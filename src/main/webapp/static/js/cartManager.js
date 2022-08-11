import {dataHandler} from "./dataManager.js";

const productsContainer = document.getElementById("products");
const productCounter = document.getElementById("cart-count");
const shopCartContainer = document.getElementById("cart-content");

export function loadButtons(){
    let cardContainers = productsContainer.children;

    for (const cardContainer of cardContainers) {
        let cardBody = cardContainer.firstChild.nextSibling.childNodes.item(5)
        let cardText = cardBody.childNodes.item(3);
        let cardButton = cardText.childNodes.item(1);

        cardButton.addEventListener("click", async function (evt) {
            let productId = evt.target.id;
             let response = await dataHandler.getIdProduct(productId);
             productCounter.innerText = response.length;
            console.log(response.length);
        });
    }
}

function fillShopCart(products) {
    shopCartContainer.innerHTML = "";

    products.reduce((product, number) => product + number, 0)
}