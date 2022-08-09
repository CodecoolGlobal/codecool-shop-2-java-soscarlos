export let dataHandler = {
    getProductsByCategory: async function (id){
        return getApi(`/api/category?id=${id}`);
    }
}

async function getApi(url) {
    await fetch(url)
        .then(response => response.json())
        .catch(error => console.log(error));
}