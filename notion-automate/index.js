import { Client } from "@notionhq/client"
import getPrice from "./yahoofinance.js";


const notion = new Client({ auth: process.env.NOTION_KEY })
const databaseId = process.env.NOTION_DATABASE_ID

async function getData() {
    return notion.databases
                 .query({database_id: databaseId})
                 .then(response => {
                    return response.results
                        .map(data=> [data.id, data.properties?.Symbol?.rich_text[0]?.plain_text])
                        .filter(data => data[1] !== undefined)
                        .reduce((map, [key, value]) => map.set(key, value), new Map())
                 })
}

async function getStockPrice(stock_symbol) {
    return getPrice(stock_symbol)
            .then(response => response.regularMarketPrice)
}

async function updatePage(page_id, stock_price) {
  const pageId = page_id;
  const response = await notion.pages.update({
    page_id: pageId,
    properties: {
        '單位現值':{
            number: stock_price
        }
    },
  });
  console.log(response)
}


getData().then(map => {
             console.log(map);
             map.forEach((value, key) => {
                 getStockPrice(value)
                     .then(stock_price => {
                         console.log(value + ":" + stock_price);
                         updatePage(key, stock_price);
                     });
             })
         });



