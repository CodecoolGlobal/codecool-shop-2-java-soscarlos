import {dataHandler} from "./dataManager.js";
import {shopCartBuilder} from "./htmlFactory.js";

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
        });
    }
}

export async function fillShoppingCard(){
    let products = await dataHandler.getProducts();
    let totalPriceElement = document.getElementById("total-price");
    let totalPrice = 0;
    let productsReduced = Object.values(products.reduce((r, {id, name, description, productCategory, supplier, defaultPrice}) => {
        r[name] ??= {id, name, description, productCategory, supplier, defaultPrice, count: 0, totalPrice: 0};
        r[name].count++;
        r[name].totalPrice = defaultPrice * r[name].count;
        return r;
    }, {}));

    for (const product of productsReduced) {
        let shoppingCard = shopCartBuilder(product);
        shopCartContainer.insertAdjacentHTML("beforeend", shoppingCard);
        totalPrice += product.totalPrice;
    }
    totalPriceElement.innerText = "EUR " + totalPrice.toString();
}