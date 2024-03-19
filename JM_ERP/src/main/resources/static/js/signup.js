/* 변수 선언, 특정 동작을 위해 코드가 모여 있는 상태에서 지역 스코프(범위)가 생성되는 것.*/
const sin = Math.sin;
const cos = Math.cos;
const PI = Math.PI;
const fov = 130; // 별이 사용자에게 오는 속도(숫자가 작을수록 빠름)

/* Dot class */
class Dot {
  constructor(x, y, z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

/* canvas 설정*/ 
let canvas;
let context;
let tempx, tempy, tempz;
let dots = [];
let dotsLength = (innerWidth+innerHeight)/20; /*윈도우 콘텐츠 영역의 너비를 px 단위로 변환*/

/* 자바스크립트 함수 설정 */
function setSize() {
  canvas.width = innerWidth;
  canvas.height = innerHeight;
  initDots();
  context.fillStyle = '#FEFD48'; /* 도형 색상 추가 */
  if (innerWidth < 800) {
    context.globalAlpha = 0.2; /* 투명도 */
  } else {
    context.globalAlpha = 0.9;
  }
}

function initDots() {
  dots = [];
  dotsLength = (innerWidth+innerHeight)/20;
  let x, y, z;
  for (let i = 0; i < dotsLength; i++) {
    x = Math.random()*innerWidth - innerWidth/3;
    y = Math.random()*innerHeight - innerHeight/3;
    z = Math.random()*innerWidth - innerWidth/3;
    dots.push(new Dot(x, y, z)); /* 배열, 기존의 마지막 원소 바로 뒤에 추가 및 배열의 길이 반환 */
  }
}

function drawDots(dot) {
  let scale, x2d, y2d, radius;
  scale = fov / (fov + dot.z);
  x2d = dot.x * scale + innerWidth / 2.1; // 별 애니메이션 x축 이동
  y2d = dot.y * scale + innerHeight / 2; // 별 애니메이션 y축 이동
  radius = scale * 2;

  // 원 그리기
  context.beginPath();
  context.arc(x2d, y2d, radius, 0, Math.PI * 2, false); // canvas에서 원을 그릴 수 있게 도와주는 메서드 [x좌표 중심, y좌표 중심, 시작 각도, 끝 각도, 방향 설정(false : 시계방향, true : 반시계 방향)]
  
  // 섬광 효과를 위한 그림자 설정
  context.shadowBlur = 20; // 그림자의 흐림 정도
  context.shadowColor = "white"; // 그림자(섬광) 색상

  context.fillStyle = "rgba(255, 255, 0, 1)"; // 원의 색상
  context.fill();

  // 그림자 설정을 초기화 (다음 그림에 영향을 주지 않도록)
  context.shadowBlur = 0;
  context.shadowColor = "transparent";
}

/*context.fillRect(x2d, y2d, scale*4, scale*3); /*사각형 그리기 */

function render() { /* 각 프레임마다 실행, 캔버스를 지우고 ('clearRect'), 모든 점들의 위치를 업데이트한 후 다시 그림. */
  context.clearRect(0, 0, canvas.width, canvas.height); /* html5 canvas 지우기 (비슷한 말로 초기화) */
  let dot;
  for (let i = 0; i < dots.length; i++) {
    dot = dots[i];
    dot.z -= 4; /* 속도 조절 및 무한 반복의 핵심 */
    if (dot.z < -fov) {
      dot.z += (innerWidth+innerHeight)/2;
    }
    drawDots(dot);
  }
  requestAnimationFrame(render);/* frame 주기에 맞춰 애니메이션 출력 후 다음 애니메이션 프레임 요청 */
}

function init() {
  canvas = document.querySelector('.canvas-signup'); /* document 내 요소를 검색 한 후 첫 번째 요소만 return하는 메서드 */
  context = canvas.getContext('2d'); /* 드로잉에 필요한 속성, 함수를 가진 객체를 생성 */
  setSize();
  render();
}

addEventListener('resize', setSize); /* event 효과 */
init();

/* box 갑자기 등장(진행 중, box 유지 완성을 하였지만 어떻게 되었는지는 모릅니다. 양해 바랍니다.)*/
const theme1 = document.querySelector('.theme1');

function animationWhendisplayed(){
  theme1.classList.add('on');
}

animationWhendisplayed();
