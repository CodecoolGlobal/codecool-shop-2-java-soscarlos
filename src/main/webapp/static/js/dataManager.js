export let dataHandler = {

    getProductsByCategory: async function (id) {
        return getApi(`/api/products/category?id=${id}`);
    },
    
    getProductsBySupplier: async function (id) {
        return getApi(`/api/products/supplier?id=${id}`);
    },

    addProduct: async function (id){
        return getApi(`/api/cart?addId=${id}`);
    },
    removeProduct: async function (id){
        return getApi(`/api/cart?removeId=${id}`);
    },
    getProducts: async function (){
        return getApi(`/api/cart`);
    }
}

async function getApi(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (response.ok) {
        return await response.json();
    } else {
        console.log("didn't work man/woman");
    }
}