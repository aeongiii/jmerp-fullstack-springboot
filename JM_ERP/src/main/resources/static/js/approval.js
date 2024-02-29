const btn1 = document.querySelector(".btn-primary");
function btnClick() {
	
	if(btn1.classList.contains('btn-primary')){
	btn1.classList.replace('btn-primary','btn-secondary');				
	} else if(btn1.classList.contains('btn-secondary')){
		btn1.classList.replace('btn-secondary','btn-primary');
	};
	console.dir(document);
};
		
btn1.addEventListener("click",btnClick);
