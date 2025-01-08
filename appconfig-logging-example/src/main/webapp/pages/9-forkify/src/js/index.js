
/*
import axios from 'axios';
import str from './models/Search';
//import {add, multi, ID} from './views/searchView';
//import {add as a, multi as m, ID} from './views/searchView';
import * as searchView from './views/searchView';

//console.log(`Imported from ${add(ID,2)}, ${multi(3,5)}, ${str} .`);

//console.log(`Imported from ${a(ID,20)}, ${m(3,5)}, ${str} .`);
console.log(`Imported from ${searchView.add(searchView.ID,25)}, ${searchView.multi(3,5)}, ${str} .`);

async function getResult(query){
    //const proxy = 'https://cors-anywhere.herokuapp.com/';
    const url = `https://forkify-api.herokuapp.com/api/search?q=${query}`;
    //const headers = {'Authorization':'Token 9c8b06d329136da358c2d00e76946b0111ce2c48'};
    //const result = await axios(url, {headers});
    try{

        const result = await axios(url);
        const recipes = result.data.recipes;
        console.log(recipes);
    } catch(error){
        alert(error);
    }
}

getResult('beef');

*/

/* Global state of the App
    -> Search object
    -> Current recipe object
    -> Shopping list object
    -> Liked recipe
*/

import { elements, renderLoader, clearLoader } from './views/base';
import Search from './models/Search';
import * as searchView from './views/searchView';
import Recipe from './models/Recipe';

const state = {};


/*
    Search controller
*/
const controlSeacrh = async () => {
    // 1) Get query from view
    const query = searchView.getInput(); 
    //console.log(query);
    if(query){
        // 2) new search object and add to state
        state.search = new Search(query);

        // 3) Prepare UI for result
        searchView.clearInput();
        searchView.clearResult();
        renderLoader(elements.searchRes);
        
        try {
            // 4) search for recipe
            await state.search.getResult();
    
            // 5) render results on UI
            //console.log(state.search.recipes);
            clearLoader();
            searchView.renderResults(state.search.recipes);

        } catch(err){
            alert('Something went wrong with search...');
            clearLoader();
        }

    }
};

elements.searchForm.addEventListener('submit', e=> {
    e.preventDefault();
    controlSeacrh();

});

elements.searchResPages.addEventListener('click', e => {
    const btn = e.target.closest('.btn-inline');
    console.log(btn);
    if(btn){
        const gotoPage = parseInt(btn.dataset.goto,10);
        searchView.clearResult();
        searchView.renderResults(state.search.recipes, gotoPage);
        console.log(gotoPage);
    }
});

/*
    Recipe controller
*/


const controlRecipe = async () => {

    // Get id from url
    const id = window.location.hash.replace('#','');
    console.log(id);
    if(id){
        // Prepare UI for changes

        // Create new recipe object
        state.recipe = new Recipe(id);

        try {
            
            // Get recipe data
            await state.recipe.getRecipe();
    
            // Calculate serving and time
            state.recipe.calcTime();
            state.recipe.calcServings();
            // Render recipe
            console.log(state.recipe);
        } catch(err) {
            alert('Error Processing recipe');
        }
    }
};

// window.addEventListener('hashchange',controlRecipe);
// window.addEventListener('load', controlRecipe);

['hashchange','load'].forEach(event => window.addEventListener(event,controlRecipe));