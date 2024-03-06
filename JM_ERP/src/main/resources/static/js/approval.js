var div = document.querySelector(".btn-group-lg");
var button = div.getElementsByTagName("button");
const authList = ["ROLE_관리자", "ROLE_구매","ROLE_인사", "ROLE_생산"];

window.onload = function() {
	
	if(div.getAttribute("id")==2){		
		if(div.getAttribute("name")==authList[0]){
			for(var i=0; i<button.length; i++){
				button[i].classList.remove("disabled");
				button[i].addEventListener("click",()=>{
					if(button[0].innerText==='미완료'){
				for(var i=0; i<button.length; i++){
					button[i].classList.replace('btn-secondary', 'btn-primary');
					button[i].innerText = '완료';	
				}
			} else {
				for(var i=0; i<button.length; i++){
					button[i].classList.replace('btn-primary','btn-secondary');
					button[i].innerText='미완료';
				}
			}
				});
			}
			
			
		}
		
		if(div.getAttribute("name")==authList[1]){
				button[0].classList.remove("disabled");
				button[0].addEventListener("click",()=>{
					if(button[0].innerText === '구매부'){
						button[0].classList.replace('btn-secondary', 'btn-primary');
						button[0].innerText = '완료';					
					} else {
						button[0].classList.replace('btn-primary', 'btn-secondary');
						button[0].innerText = '구매부';	
					}
				});
		}
		if(div.getAttribute("name")==authList[2]){
				button[1].classList.remove("disabled");
				button[1].addEventListener("click",()=>{
					if(button[1].innerText === '인사부'){
						button[1].classList.replace('btn-secondary', 'btn-primary');
						button[1].innerText = '완료';					
					} else {
						button[1].classList.replace('btn-primary', 'btn-secondary');
						button[1].innerText = '인사부';	
					}
				});
		}
		
		if(div.getAttribute("name")==authList[3]){
				button[2].classList.remove("disabled");
				button[2].addEventListener("click",()=> {
					if(button[2].innerText === '생산부'){
						button[2].classList.replace('btn-secondary', 'btn-primary');
						button[2].innerText = '완료';					
					} else {
						button[2].classList.replace('btn-primary', 'btn-secondary');
						button[2].innerText = '생산부';	
					}
					
				});
		}
		
	}
};
