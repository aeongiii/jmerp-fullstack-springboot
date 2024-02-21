function nextPage(currentPage, keywordValue, categoryValue) {
    const nextPageUrl = `${window.location.pathname}?page=${currentPage+1}&keyword=${keywordValue}&category=${categoryValue}`
    window.location.href = nextPageUrl;
}

function prevPage(currentPage, keywordValue, categoryValue) {
    const prevPageUrl = `${window.location.pathname}?page=${currentPage-1}&keyword=${keywordValue}&category=${categoryValue}`
    window.location.href = prevPageUrl;
}

function goToPage(pageNum, keywordValue, categoryValue) {

    const PageUrl = `${window.location.pathname}?page=${pageNum}&keyword=${keywordValue}&category=${categoryValue}`;
    window.location.href = PageUrl;
}

// 오늘 날짜를 받는 함수
function getTodayDate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // 1월은 0으로 시작하므로 1을 더해줍니다.
    var yyyy = today.getFullYear();

    return yyyy + '-' + mm + '-' + dd;
}

//라디오버튼을 2개로 나눠서 이용하는 함수
function initializePage(option1ContentId, option2ContentId) {
    $(document).ready(function () {
        var selectedOption = localStorage.getItem('selectedOption') || "option1";

        if (selectedOption === "option1") {
            $("#" + option1ContentId).show();
            $("#" + option2ContentId).hide();
        } else if (selectedOption === "option2") {
            $("#" + option1ContentId).hide();
            $("#" + option2ContentId).show();
        }
        $("input[name='btnradio'][value='" + selectedOption + "']").prop('checked', true);

        // 라디오 버튼 변경 시 로컬 스토리지에 저장
        $("input[name='btnradio']").change(function () {
            selectedOption = $(this).val();
            localStorage.setItem('selectedOption', selectedOption);

            // 페이지를 0으로 설정
            localStorage.setItem('currentPage', 0);

            // 현재 URL에서 페이지 파라미터를 제거한 뒤 페이지를 0으로 설정하여 리로드
            var currentUrl = window.location.href.split('?')[0];
            window.location.href = currentUrl + "?page=0";
        });
    });
}

function showPriceField(typeId, priceLabelId, priceLabelNameId) {
    var selectBox = document.getElementById(typeId);
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    var priceLabel = document.getElementById(priceLabelId);

    if (selectedValue === "추가") {
        priceLabel.style.display = "block";
        document.getElementById(priceLabelNameId).textContent = "추가 금액:";
    } else if (selectedValue === "할인") {
        priceLabel.style.display = "block";
        document.getElementById(priceLabelNameId).textContent = "할인 금액:";
    } else {
        priceLabel.style.display = "none";
    }
}