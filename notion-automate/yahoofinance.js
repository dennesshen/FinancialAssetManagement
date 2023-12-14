import fetch from "node-fetch";

const url = process.env.SEARCH_SERVICE_URL;

async function getPrice(symbol) {
    return fetch(url+symbol).then(response => response.json())
}

export default getPrice;
