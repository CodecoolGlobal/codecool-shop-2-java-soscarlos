import {dataHandler} from "./dataManager.js";
import {shopCartBuilder} from "./htmlFactory.js";

const productsContainer = document.getElementById("products");
const productCounter = document.getElementById("cart-count");
const shopCartContainer = document.getElementById("cart-content");
const totalPriceElement = document.getElementById("total-price");
const cartSizeElement = document.getElementById("cart-size");

export function loadButtons() {
    let cardContainers = productsContainer.children;

    for (const cardContainer of cardContainers) {
        let cardBody = cardContainer.firstChild.nextSibling.childNodes.item(5)
        let cardText = cardBody.childNodes.item(3);
        let cardButton = cardText.childNodes.item(1);

        cardButton.addEventListener("click", async function (evt) {
            let productId = evt.target.id;
            let productsInCart = await dataHandler.addProduct(productId);
            productCounter.innerText = productsInCart.length;
        });
    }
}

export async function fillShoppingCard() {
    let products = await dataHandler.getProducts();
    let totalPrice = 0;
    let productsReduced = getReducedProducts(products);

    for (const product of productsReduced) {
        let shoppingCard = shopCartBuilder(product);
        shopCartContainer.insertAdjacentHTML("beforeend", shoppingCard);

        let inputTag = document.getElementById(`form${product.id}`);

        inputTag.addEventListener("input", async (evt) => changeCartStatus(evt, product));
        totalPrice += product.totalPrice;
    }
    totalPriceElement.innerText = "EUR " + totalPrice.toString();
}

function getReducedProducts(products) {
    return Object.values(products.reduce(
        (r, {id, name, description, productCategory, supplier, defaultPrice}) => {
            r[name] ??= {id, name, description, productCategory, supplier, defaultPrice, count: 0, totalPrice: 0};
            r[name].count++;
            r[name].totalPrice = defaultPrice * r[name].count;
            return r;
        }, {}));
}

async function changeCartStatus(evt, product){
    let inputValue = evt.target.value;
    let id = product.id;
    let newTotalPrice = 0;
    if (inputValue > product.count) {
        product.count += inputValue - product.count;

        let productsInCartPlus = await dataHandler.addProduct(id);
        let increasedProducts = getReducedProducts(productsInCartPlus);
        for (const increasedProduct of increasedProducts) {
            newTotalPrice += increasedProduct.totalPrice;
        }
        cartSizeElement.innerText = productsInCartPlus.length.toString() + " Items";
        totalPriceElement.innerText = "EUR " + newTotalPrice.toString();

    } else if (inputValue < product.count) {
        product.count -= product.count - inputValue;

        let productsInCartMinus = await dataHandler.removeProduct(id);
        let decreasedProducts = getReducedProducts(productsInCartMinus);
        for (const decreasedProduct of decreasedProducts) {
            newTotalPrice += decreasedProduct.totalPrice;
        }
        cartSizeElement.innerText = productsInCartMinus.length.toString() + " Items";
        totalPriceElement.innerText = "EUR " + newTotalPrice.toString();
    }
}