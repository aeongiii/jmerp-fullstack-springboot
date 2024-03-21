function checkUserAuth() {
    const userHasPermission = true; // 권한 확인 로직을 통해 결정

    return userHasPermission;
}

// 페이지 이동을 처리하는 함수
function navigateToMemlist(event) {
    // 이벤트의 기본 동작을 막음
    event.preventDefault();

    // 사용자 권한 확인
    const hasPermission = checkUserAuth();

    // 권한이 없는 경우 모달 창을 띄움
    if (hasPermission) {
        document.getElementById('myModal').style.display='block';
    } else {
        // 권한이 있는 경우 페이지 이동
        window.location.href = "/HR/memlist";
    }
}

// 페이지에서 이벤트를 처리할 요소를 선택
const memlistLink = document.querySelector('.dropdown-item');

// 이벤트 핸들러를 등록
memlistLink.addEventListener("click", navigateToMemlist);