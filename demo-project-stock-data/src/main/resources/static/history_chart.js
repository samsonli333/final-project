const chart = document.getElementById('chart')

urlParams = new URLSearchParams(window.location.search);
const symbol = urlParams.get('symbol')

const fetchHistory = async(symbol,start) => {
  res = await fetch(`http://localhost:8080/api/history?symbol=${symbol}&start=${start}`)
  result = await res.json()
  return result
}


const func = async(num = 0) => {

const [history] = await Promise.all([fetchHistory(symbol,num)]) 

  if(history) chart.innerHTML = '<div id="myDiv" ></div>'


var trace = {
  x: history["date"],
  close: history["close"],
  high: history["high"],
  low: history["low"],
  open: history["open"],
  
  // cutomise colors
  increasing: {line: {color: '#17BECF'}},
 decreasing: {line: {color: '#7F7F7F'}},
   line: {color: 'rgba(31,119,180,1)'},

  type: 'candlestick',
  xaxis: 'x',
  yaxis: 'y'
};

var data = [trace];

var layout = {
  dragmode: 'zoom',
  showlegend: true,
  xaxis: {
    rangeslider: {
		 visible: false
	 }
  }
};

const myDiv = document.getElementById('myDiv');
Plotly.newPlot(myDiv, data, layout,{responsive:true});

}


func();


const past1Btn = document.getElementById("addToWatchlistBtn")

const past2Btn = document.getElementById("setPriceAlertBtn")

past1Btn.addEventListener('click',() => func(1))

past2Btn.addEventListener('click',() => func(2))