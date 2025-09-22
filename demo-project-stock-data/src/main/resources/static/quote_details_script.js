

urlParams = new URLSearchParams(window.location.search);
const symbol2 = urlParams.get('symbol')

const func2 = async() => {

const fetchQuote = async(symbol) =>{
  res = await fetch(`http://localhost:8080/api/companydata?symbol=${symbol}`)
  result = await res.json()
  return result
}


const fetchNews = async(symbol) => {
   res = await fetch(`http://localhost:8080/api/news?symbol=${symbol}`)
   result = await res.json()
   return result
  }

const fetchHistory = async(symbol) => {
  res = await fetch(`http://localhost:8080/api/history?symbol=${symbol}`)
  result = await res.json()
  return result
}

try{
const [data,news,history] = await Promise.all([fetchQuote(symbol2),fetchNews(symbol2),fetchHistory(symbol2)]) 

const {stockProfile,stockQuoteDTO} = data


const dummyStockData = {
  data: {
    name: stockProfile.name,
    price: stockQuoteDTO.c,
    change: stockQuoteDTO.d,
    changePercent: stockQuoteDTO.dp.toFixed(2) + '%',
    marketCap: stockProfile.marketCapitalization,
    peRatio: 28.5,
    volume: '75M',
    open: stockQuoteDTO.o,
    high: stockQuoteDTO.h,
    low: stockQuoteDTO.l,
    yearHigh: 198.23,
    yearLow: 150.23,
  }
};

const dummyNews = news.map((x,i) => ({id:i + 1, title:x.headline,source:x.source,time:x.datetime,symbol:symbol,url:x.url}))


const stockDetailsDiv = document.getElementById('stock-details');

const companyNameHeader = document.getElementById('company-name');

const newsFeedDiv = document.getElementById('news-feed');

function displayStockDetails(stock, symbol = 'N/A') {
  if (stock) {
    companyNameHeader.textContent = `${stock.name} (${symbol})`;
    const changeClass = stock.change > 0
      ? 'text-green-500 dark:text-green-400'
      : 'text-red-500 dark:text-red-400';
      stockDetailsDiv.innerHTML = `
      <div class="flex justify-between items-baseline">
          <p class="text-3xl font-bold">${stock.price.toFixed(
            2
          )} <span class="text-xs text-gray-500 dark:text-gray-400">USD</span></p>
          <p class="text-lg ${changeClass}">${stock.change > 0 ? '+' : ''}${stock.change} (${
stock.changePercent
})</p>
      </div>
      <hr class="my-2 border-gray-200 dark:border-gray-600">
      <div class="grid grid-cols-2 gap-x-4 gap-y-1 text-sm">
          <p><strong>Open:</strong> ${stock.open.toFixed(2)}</p>
          <p><strong>High:</strong> ${stock.high.toFixed(2)}</p>
          <p><strong>Low:</strong> ${stock.low.toFixed(2)}</p>
          <p><strong>Volume:</strong> ${stock.volume}</p>
          <p><strong>Mkt Cap:</strong> ${stock.marketCap}</p>
      </div>
  `;
  } else {
    companyNameHeader.textContent = 'Company Overview';
    stockDetailsDiv.innerHTML = `<p class="text-sm text-gray-600 dark:text-gray-400">Search for a stock to see details.</p>`;
  }
}



function populateNewsFeed() {

  let filteredNews = dummyNews;

    filteredNews = dummyNews.slice(0, 5);
  newsFeedDiv.innerHTML = filteredNews.length
    ? filteredNews
        .map(
          (news) => `
          <div class="bg-gray-50 dark:bg-gray-700 p-4 rounded-lg shadow hover:shadow-md transition-shadow duration-200">
          <a href="${news.url}" target="_blank" class="block hover:text-indigo-600 dark:hover:text-indigo-400">
              <h4 class="text-md font-semibold text-gray-800 dark:text-gray-100 mb-1">${news.title}</h4>
          </a>
          <div class="flex justify-between text-xs text-gray-500 dark:text-gray-400">
              <span>${news.source}</span>
              <span>${news.time}</span>
          </div>
      </div>
            `
        )
        .join('')
    : '<p class="text-sm text-gray-500 dark:text-gray-400">No news available at the moment.</p>';
}


populateNewsFeed();
displayStockDetails(dummyStockData.data,stockQuoteDTO.symbol);

}catch(err){
  console.log(err)
}






document.addEventListener('DOMContentLoaded', () => {
  // populateNewsFeed();
  // displayStockDetails(dummyStockData.AAPL);

  document.querySelectorAll('nav a[href^="#"]').forEach((anchor) => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();
      const targetId = this.getAttribute('href');
      const targetElement = document.querySelector(targetId);
      if (targetElement) {
        const navbarHeight = document.querySelector('nav').offsetHeight;
        const tickerHeight =
          document.querySelector('.ticker-wrap').offsetHeight;
        const offsetPosition =
          targetElement.offsetTop - navbarHeight - tickerHeight - 20;

        window.scrollTo({
          top: offsetPosition,
          behavior: 'smooth',
        });

        if (!mobileMenu.classList.contains('hidden')) {
          mobileMenu.classList.add('hidden');
          mobileMenuButton.setAttribute('aria-expanded', 'false');
          mobileMenuButton
            .querySelectorAll('svg')
            .forEach((icon) => icon.classList.toggle('hidden'));
        }
      }
    });
  });
});

}


func2()




