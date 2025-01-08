import axios from 'axios';
/*
export default function Search(query){
    this.query = query;
}

Search.prototype.getResult = async function(){
    const url = `https://forkify-api.herokuapp.com/api/search?q=${this.query}`;
    try{
        const result = await axios(url);
        this.recipes = result.data.recipes;
        console.log(this.recipes);
    } catch(error){
        alert(error);
    }
}; */

export default class Search {
    constructor(query){
        this.query = query;
    }

    async getResult(){
        const url = `https://forkify-api.herokuapp.com/api/search?q=${this.query}`;
    try{
        const result = await axios(url);
        this.recipes = result.data.recipes;
        //console.log(this.recipes);
    } catch(error){
        alert(error);
    }   
    }
}

