export let dataHandler = {
    getProductsByCategory: async function (id){
        return getApi(`/api/category?id=${id}`);
    }
}

async function getApi(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (response.ok) {
        return await response.json();
    }
}