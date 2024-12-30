'use strict';

const btn = document.querySelector('.btn-country');
const countriesContainer = document.querySelector('.countries');

// NEW COUNTRIES API URL (use instead of the URL shown in videos):
// https://restcountries.com/v2/name/portugal

// NEW REVERSE GEOCODING API URL (use instead of the URL shown in videos):
// https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=${lat}&longitude=${lng}

///////////////////////////////////////

const renderCountry = function(data, className=''){
    const html =`  <article class="country ${className}">
    <img class="country__img" src="${data.flag}" />
    <div class="country__data">
      <h3 class="country__name">${data.name}</h3>
      <h4 class="country__region">${data.region}</h4>
      <p class="country__row"><span>👫</span>${(
        +data.population / 1000000
      ).toFixed(1)} people</p>
      <p class="country__row"><span>🗣️</span>${data.languages[0].name}</p>
      <p class="country__row"><span>💰</span>${data.currencies[0].name}</p>
    </div>
  </article> `;

  countriesContainer.insertAdjacentHTML("beforeend",html);
  countriesContainer.style.opacity=1;
};

const renderError = function(msg){
    countriesContainer.insertAdjacentText('beforeend',msg);
    countriesContainer.computedStyleMap.opacity = 1;
};

const getJson = function(url, errorMsg = 'Something went wrong'){
      return fetch(url).then(response => {
            if(!response.ok) throw new Error(`${errorMsg}  (${response.status})`);
        return response.json();
    });
};

/* /////////////////////
Our first Ajax call :XMLHttpRequest  */

/*
const getCountryData = function(country){
    const request = new XMLHttpRequest();
    request.open('GET',`https://restcountries.com/v2/name/${country}`);
    request.send();
    request.addEventListener('load', function(){

        const [data] = JSON.parse(this.responseText);
        console.log(data);
        const html = ` <article class='country'>
                            <img class='country__img' src=${data.flags.png}/>
                            <div class='country__data'>
                                <h3 class='country__name'>${data.name}</h3>
                                <h4 class='country__region'>${data.region}</h4>
                                <p class="country__row"><span>👫</span>
                                ${(+data.population / 1000000).toFixed(1)} people</p>
                                <p class="country__row"><span>🗣️</span>${data.languages[0].name}</p>
                                <p class="country__row"><span>💰</span>${data.currencies[0].name}</p>
                            </div>
                        </article>
        `;
        countriesContainer.insertAdjacentHTML('beforeend',html);
        countriesContainer.style.opacity = 1;
    });
};

getCountryData('portugal');
getCountryData('usa');
getCountryData('germany');
*/

////////////////////////////
/// Welcome to callback hell

/*
const getCountryAndNeighbour = function (country) {

    // Ajax call country 1
    const request = new XMLHttpRequest();
    request.open('GET',`https://restcountries.com/v2/name/${country}`);
    request.send();
    request.addEventListener('load', function(){
        const [data] = JSON.parse(this.responseText);
        console.log(data);

        //render country 1
        renderCountry(data);

        // Get neighbour country 2

        const [neighbour] = data.borders;

        if(!neighbour) return;

        // Ajax call country 2

        const request2 = new XMLHttpRequest();
        //console.log(`https://restcountries.eu/rest/v2/alpha/${neighbour}`);
        request2.open('GET', `https://restcountries.eu/rest/v2/alpha/${neighbour}`);
        request2.send();

        request2.addEventListener('load', function(){
            const data2 = JSON.parse(this.responseText);
            console.log(data2);
            renderCountry(data2, 'neighbour');
        });
    });
};

getCountryAndNeighbour('usa');
*/

/*
setTimeout(() => {
    console.log('1 second passed');
    setTimeout(() => {
      console.log('2 seconds passed');
      setTimeout(() => {
        console.log('3 second passed');
        setTimeout(() => {
          console.log('4 second passed');
        }, 1000);
      }, 1000);
    }, 1000);
  }, 1000);

  */

  
  
//////////////// 
// Consuming promises
// Chaining Promises  
// Handling rejected promise
// Throwing errors manualy

/*
const getCountryData = country => {

    fetch(`https://restcountries.com/v2/name/${country}`)
        .then(response => {
            console.log(response);
            return response.json();
        })
        .then(data => {
            console.log(data);
            renderCountry(data[0]);
        })
};

getCountryData('portugal');  */

/*
const getCountryData = country => {

    // country 1

    fetch(`https://restcountries.com/v2/name/${country}`)
        .then(response => {
            console.log(response);
            if(!response.ok) 
                throw new Error(`Country not found ${response.status}`);
            return response.json();

        })
        .then(data => {
            console.log(data);
            renderCountry(data[0]);
            const neighbour = data[0].borders[0];
            //const neighbour = 'dsfsdgf';
            if(!neighbour) return;
            // country 2
            return fetch(`https://restcountries.eu/rest/v2/alpha/${neighbour}`);
        })
        .then(response => {
            if(!response.ok)
                throw new Error(`Country not found ${response.status}`);
            return response.json();
        })
        .then(data => renderCountry(data,'neighbour'))
        .catch(err => {
                  console.error(`${err} 💥💥💥`);
                  renderError(`Something went wrong 💥💥 ${err.message}. Try again!`);
                })
        .finally(() => {
                  countriesContainer.style.opacity = 1;
                });
};

getCountryData('usa');

*/

/*
const getCountryData = country => {
    // Country 1
    getJson(  `https://restcountries.com/v2/name/${country}`,
        'Country not found')
        .then(data =>{
            renderCountry(data[0]);
            const neighbour = data[0].borders[0];
            if(!neighbour) throw new Error('No neighbour found!');
            // Country 2
            getJson(`https://restcountries.eu/rest/v2/alpha/${neighbour}`,
                'Country not found'
            );
        })
        .then(data => renderCountry(data, 'neighbour'))
        .catch(err => {
            console.error(`${err} 💥💥💥`);
            renderError(`Something went wrong 💥💥 ${err.message}. Try again!`);
          })
          .finally(() => {
            countriesContainer.style.opacity = 1;
          });

};

btn.addEventListener('click',function(){
    getCountryData('usa');
});

// getCountryData('australia');
*/

/*
const whereAmI = (lat,lng) => {

    fetch(`https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=${lat}&longitude=${lng}`)
    .then(response => {
        if(!response.ok) throw new Error(`Country not found ${response.status}`);
        return response.json();
    })
    .then(data => {
        console.log(data);
        console.log(`You are in ${data.city}, ${data.countryCode}`);
        return fetch(`https://restcountries.com/v2/name/${data.countryName}`)
    })
    .then(res => {
        if (!res.ok) throw new Error(`Country not found (${res.status})`);
  
        return res.json();
      })
    .then(data => renderCountry(data[0]))
    .catch(err => console.error(`${err.message} 💥`));
};

whereAmI(52.508, 13.381);
whereAmI(19.037, 72.873);
whereAmI(-33.933, 18.474);

*/
///////////////////////////////////////
// The Event Loop in Practice
/*
console.log('Test start');
setTimeout(() => console.log('0 sec timer'), 0);
Promise.resolve('Resolved promise 1').then(res => console.log(res));

Promise.resolve('Resolved promise 2').then(res => {
  for (let i = 0; i < 1000000000; i++) {}
  console.log(res);
});

console.log('Test end');
*/
//////////////////////
// Building a simple promise

/*
const lotteryPromise = new Promise(function(resolve,reject){
    console.log('Lotter draw is happening 🔮');
    setTimeout(function(){
        if(Math.random >= 0.5) {
      resolve('You WIN 💰');
    } else {
      reject(new Error('You lost your money 💩'));
    }
    },2000);
});

lotteryPromise.then(result => console.log(result)).catch(err => console.error(err));
*/

// Promisifying setTimeOut

/*
const wait = function(second) {
    return new Promise(function(resolve, reject){
        setTimeout(resolve,second * 1000);
    });
};

wait(1).then(()=>{
    console.log(`1 second pass`);
    return wait(1);
}).then(()=>{
    console.log(`2 second pass`);
    return wait(1);
}).then(()=>{
    console.log(`3 second pass`);
    return wait(1);
}).then(()=>console.log(`4 second pass`));

*/

/*
setTimeout(() => {
   console.log(`1 second pass`) ;
   setTimeout(() => {
        console.log(`2 second pass`);
        setTimeout(()=>{
            console.log(`3 second pass`);
            setTimeout(() => {
                console.log(`4 second pass`);
            }, 1000);
        },1000)
   }, 1000);
}, 1000);  */

// Promise.resolve('abc').then(x => console.log(x));
// Promise.reject(new Error('Problem!')).catch(x=>console.error(x));

/////////////////////////////////
// Promisifying Geolocation API
/*
const getPosition  = function(){
    return new Promise(function(resolve,reject) {
        // navigator.geolocation.getCurrentPosition(
        //     position => resolve(position), err => reject(err)
        // );
        navigator.geolocation.getCurrentPosition(resolve,reject);
    });
};

//getPosition().then(pos => console.log(pos));

const whereAmI = function () {
    getPosition()
        .then(pos => {
                const { latitude: lat, longitude: lng} = pos.coords;
                return fetch(`https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=${lat}&longitude=${lng}`);
        })
        .then(res => {
            if(!res.ok) throw new Error(`Problem with geocoding ${res.status}`);
            return res.json();
        })
        .then(data => {
            console.log(data);
            console.log(`You are in ${data.city} , ${data.countryCode}`);
            return fetch(`https://restcountries.com/v2/name/${data.countryCode}`);
        })
        .then(res=>{
            if(!res.ok) throw new Error(`Country not found (${res.status})`);
            return res.json();
        })
        .then(data=> {
            renderCountry(data[0]);
        }) 
        .catch(err => console.error(`${err.message} 💥`));
};

btn.addEventListener('click', whereAmI);

*/
/*
const wait = function(second) {
    return new Promise(function(resolve){
        setTimeout(resolve,second*1000);
    });
};

const imgContainer = document.querySelector('.images');

const createImage = function(imagePath){
    return new Promise(function(resolve,reject){
        const  img = document.createElement('img');
        img.src=imagePath;
        img.addEventListener('load', function(){
            imgContainer.append(img);
            resolve(img);
        });

        img.addEventListener('error',function(){
            reject(new Error('Image not found'));
        });
    });
};

let currentImg;

createImage('img/img-1.jpg')
    .then(img => {
        currentImg = img;
        console.log('Image 1 loaded');
        return wait(2);
    })
    .then(() => {
        currentImg.style.display = 'none';
        return createImage('img/img-2.jpg');
    })
    .then(img => {
        currentImg = img;
        console.log('Image 2 loaded');
        return wait(2);
    })
    .then(() => {
        currentImg.style.display='none';
    })
    .catch(err => console.error(err));
*/
    /////////////////////////////
    // Consuming promise with Async/wait
    // Error  handling with try ... catch

    /*
    const getPosition = function(){
        return new Promise(function(resolve,reject){
            navigator.geolocation.getCurrentPosition(resolve,reject);
        });
    };



    const whereAmI = async function(){
        try {
            // Geo location
            const pos = await getPosition();
            const {latitude: lat,longitude:lng} = pos.coords;
            // Reverse geo coding
            const resGeo = await fetch(`https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=${lat}&longitude=${lng}`);
            if(!resGeo.ok) throw new Error('Problem getting location data');
            const dataGeo = await resGeo.json();
            console.log(dataGeo);
             // Country data
            const res = await fetch(
                `https://restcountries.com/v2/name/${dataGeo.countryCode}`
            );
            
            // BUG in video:
            // if (!resGeo.ok) throw new Error('Problem getting country');
            
            // FIX:
            if (!res.ok) throw new Error('Problem getting country');
            const data = await res.json();
            console.log(data);
            renderCountry(data[0]);
        } catch(err){
            console.error(`${err} 💥`);
            renderError(`💥 ${err.message}`);
        } 
    };

whereAmI();
whereAmI();
whereAmI();
console.log('FIRST');
*/



///////////////////////////////////////
// Returning Values from Async Functions

/*
const getPosition = function () {
    return new Promise(function (resolve, reject) {
      navigator.geolocation.getCurrentPosition(resolve, reject);
    });
  };
  
  const whereAmI = async function () {
    try {
      // Geolocation
      const pos = await getPosition();
      const { latitude: lat, longitude: lng } = pos.coords;
  
      // Reverse geocoding
      const resGeo = await fetch(`https://api.bigdatacloud.net/data/reverse-geocode-client?latitude=${lat}&longitude=${lng}`);
      if (!resGeo.ok) throw new Error('Problem getting location data');
      const dataGeo = await resGeo.json();
  
      // Country data
      const res = await fetch(
        `https://restcountries.com/v2/name/${dataGeo.countryCode}`
      );
      if (!resGeo.ok) throw new Error('Problem getting country');
      const data = await res.json();
      renderCountry(data[0]);
  
      return `You are in ${dataGeo.city}, ${dataGeo.countryCode}`;
    } catch (err) {
      console.error(`${err} 💥`);
      renderError(`💥 ${err.message}`);
  
      // Reject promise returned from async function
      throw err;
    }
  };
  
  console.log('1: Will get location');
  const city = whereAmI();
  console.log(city);
  
//   whereAmI()
//     .then(response => console.log(`2: ${response}`))
//     .catch(err => console.error(`2: ${err.message} 💥`))
//     .finally(() => console.log('3: Finished getting location'));
  

(async function(){
    try{
        const response = await whereAmI();
        console.log(`2: ${response}`);

    } catch(err){
        console.error(`2: ${err.message}`)
    }
    console.log('3: Finished getting location')
})();

*/

///////////////////////////////////////
// Running Promises in Parallel
/*
const get3Countries = async function (c1, c2, c3) {
    try {
            // const [data1] = await getJson(
            //   `https://restcountries.com/v2/name/${c1}`
            // );
            // const [data2] = await getJson(
            //   `https://restcountries.com/v2/name/${c2}`
            // );
            // const [data3] = await getJson(
            //   `https://restcountries.com/v2/name/${c3}`
            // );
            // console.log([data1.capital, data2.capital, data3.capital]);

            const data = await Promise.all([
                getJson(`https://restcountries.com/v2/name/${c1}`),
                getJson(`https://restcountries.com/v2/name/${c2}`),
                getJson(`https://restcountries.com/v2/name/${c3}`),
              ]);
              console.log(data);
              console.log(data.map(d=>d[0].capital));
        } catch(err){
            console.error(err);
        }
    };  

    get3Countries('portugal', 'canada', 'tanzania');

    */


    

///////////////////////////////////////
// Other Promise Combinators: race, allSettled and any
// Promise.race

/*
(async function () {
    const res = await Promise.race([
      getJson(`https://restcountries.com/v2/name/italy`),
      getJson(`https://restcountries.com/v2/name/egypt`),
      getJson(`https://restcountries.com/v2/name/mexico`),
    ]);
    console.log(res[0]);
  })();


  const timeout = function (sec) {
    return new Promise(function (_, reject) {
      setTimeout(function () {
        reject(new Error('Request took too long!'));
      }, sec * 1000);
    });
  };
  
  Promise.race([
    getJson(`https://restcountries.com/v2/name/tanzania`),
    timeout(5),
  ])
    .then(res => console.log(res[0]))
    .catch(err => console.error(err));

    // Promise.allSettled
Promise.allSettled([
    Promise.resolve('Success'),
    Promise.reject('ERROR'),
    Promise.resolve('Another success'),
  ]).then(res => console.log(res));
  
  Promise.all([
    Promise.resolve('Success'),
    Promise.reject('ERROR'),
    Promise.resolve('Another success'),
  ])
    .then(res => console.log(res))
    .catch(err => console.error(err));
  
  // Promise.any [ES2021]
  Promise.any([
    Promise.resolve('Success'),
    Promise.reject('ERROR'),
    Promise.resolve('Another success'),
  ])
    .then(res => console.log(res))
    .catch(err => console.error(err));
*/

const wait = function (seconds) {
    return new Promise(function (resolve) {
      setTimeout(resolve, seconds * 1000);
    });
  };

  const imgContainer = document.querySelector('.images');

const createImage = function (imgPath) {
  return new Promise(function (resolve, reject) {
    const img = document.createElement('img');
    img.src = imgPath;

    img.addEventListener('load', function () {
      imgContainer.append(img);
      resolve(img);
    });

    img.addEventListener('error', function () {
      reject(new Error('Image not found'));
    });
  });
};

let currentImg;

// createImage('img/img-1.jpg')
//   .then(img => {
//     currentImg = img;
//     console.log('Image 1 loaded');
//     return wait(2);
//   })
//   .then(() => {
//     currentImg.style.display = 'none';
//     return createImage('img/img-2.jpg');
//   })
//   .then(img => {
//     currentImg = img;
//     console.log('Image 2 loaded');
//     return wait(2);
//   })
//   .then(() => {
//     currentImg.style.display = 'none';
//   })
//   .catch(err => console.error(err));

  
//PART 1
const loadNPause = async function () {
    try {
      // Load image 1
      let img = await createImage('img/img-1.jpg');
      console.log('Image 1 loaded');
      await wait(2);
      img.style.display = 'none';
  
      // Load image 1
      img = await createImage('img/img-2.jpg');
      console.log('Image 2 loaded');
      await wait(2);
      img.style.display = 'none';
    } catch (err) {
      console.error(err);
    }
  };
 //loadNPause();

 
// PART 2
const loadAll = async function (imgArr) {
    try {
      const imgs = imgArr.map(async img => await createImage(img));
      const imgsEl = await Promise.all(imgs);
      console.log(imgsEl);
      imgsEl.forEach(img => img.classList.add('parallel'));
    } catch (err) {
      console.error(err);
    }
  };
  loadAll(['img/img-1.jpg', 'img/img-2.jpg', 'img/img-3.jpg']);
  
  