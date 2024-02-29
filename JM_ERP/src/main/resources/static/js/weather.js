const API_KEY = '7ab9dc2ff2210003bc72201a89cf4a72'; // KEY 값
const tempSection = document.querySelector('.temperature');
const placeSection = document.querySelector('.place');
const descSection = document.querySelector('.description');
const iconSection = document.querySelector('.wheather-icon');

window.onload=function(){
	
navigator.geolocation.getCurrentPosition(success, fail);
};

const success = (position) => {
	const latitude = position.coords.latitude;
	const longitude = position.coords.longitude;
	
	getWeather(latitude, longitude);
};
const fail = () => {
	alert("좌표를 받아올 수 없음");
}


const getWeather = (lat, lon) => {
	fetch(
		`https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`
	)
	.then((response) => {
		return response.json();
	})
	.then((json) => {
		console.log(json);
		const temperature = json.main.temp;
		const place = json.name;
		const description = json.weather[0].description;
		const icon = json.weather[0].icon;
		const iconURL = `https://openweathermap.org/img/wn/${icon}@2x.png`;
		
		tempSection.innerText = temperature+'`C';
		placeSection.innerText = place;
		descSection.innerText = description;
		iconSection.setAttribute('src', iconURL);
	})
	.catch((error) => {
		alert(error);
	});
	
};




