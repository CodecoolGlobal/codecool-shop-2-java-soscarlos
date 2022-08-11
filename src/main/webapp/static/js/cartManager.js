import {dataHandler} from "./dataManager.js";
import {shopCartBuilder} from "./htmlFactory.js";

const productsContainer = document.getElementById("products");
const productCounter = document.getElementById("cart-count");
const shopCartContainer = document.getElementById("cart-content");
const totalPriceElement = document.getElementById("total-price");

export function loadButtons() {
    let cardContainers = productsContainer.children;

    for (const cardContainer of cardContainers) {
        let cardBody = cardContainer.firstChild.nextSibling.childNodes.item(5)
        let cardText = cardBody.childNodes.item(3);
        let cardButton = cardText.childNodes.item(1);

        cardButton.addEventListener("click", async function (evt) {
            let productId = evt.target.id;
            let productsInCart = await dataHandler.getIdProduct(productId);
            productCounter.innerText = productsInCart.length;
        });
    }
}

export async function fillShoppingCard() {
    let products = await dataHandler.getProducts();
    let totalPrice = 0;
    let productsReduced = Object.values(products.reduce(
        (r, {id, name, description, productCategory, supplier, defaultPrice}) => {
            r[name] ??= {id, name, description, productCategory, supplier, defaultPrice, count: 0, totalPrice: 0};
            r[name].count++;
            r[name].totalPrice = defaultPrice * r[name].count;
            return r;
        }, {}));

    for (const product of productsReduced) {
        let shoppingCard = shopCartBuilder(product);
        shopCartContainer.insertAdjacentHTML("beforeend", shoppingCard);

        let buttonUp = document.getElementById(`up-button${product.id}`);
        let buttonDown = document.getElementById(`down-button${product.id}`);

        buttonUp.addEventListener("click", function (evt) {
            console.log(evt.target.id);
        });
        buttonDown.addEventListener("click", function (evt) {
            console.log(evt.target.id);
        });
        totalPrice += product.totalPrice;
    }
    totalPriceElement.innerText = "EUR " + totalPrice.toString();

}