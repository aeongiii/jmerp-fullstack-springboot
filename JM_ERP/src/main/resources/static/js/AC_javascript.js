// 조회 페이지 이동함수
function updatePage(page, keyword, category) {
    const pageUrl = `${window.location.pathname}?page=${page}&keyword=${keyword}&category=${category}`;
    window.location.href = pageUrl;
}

// 조회 없는 페이지 이동함수
function updatePage(page) {
    const pageUrl = `${window.location.pathname}?page=${page}`;
    window.location.href = pageUrl;
}

// 오늘 날짜를 받는 함수
function getTodayDate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); // 1월은 0으로 시작하므로 1을 더해줍니다.
    var yyyy = today.getFullYear();

    return yyyy + '-' + mm + '-' + dd;
}

// 년도와 월을 받는 함수
function getYearMonth(years, months) {
    var yearSelect = document.getElementById(years);
    var monthSelect = document.getElementById(months);

    var currentYear = new Date().getFullYear();
    var currentMonth = new Date().getMonth() + 1;

    // 년도 채우기
    for (var year = 2020; year <= currentYear; year++) {
        var option = document.createElement("option");
        option.text = year;
        option.value = year;
        yearSelect.add(option);
    }

    // 월 채우기
    for (var month = 1; month <= 12; month++) {
        // 현재 년도인 경우, 현재 월까지만 채웁니다.
        if (currentYear === parseInt(yearSelect.value, 10)) {
            if (month > currentMonth) {
                break;
            }
        }

        var option = document.createElement("option");
        option.text = month;
        option.value = month;
        monthSelect.add(option);
    }

    // 년도가 변경될 때마다 월 옵션을 업데이트
    yearSelect.addEventListener("change", function () {
        var selectedYear = parseInt(yearSelect.value, 10);
        monthSelect.innerHTML = ""; // 기존의 월 옵션 제거

        // 선택된 년도가 현재 년도와 같은지 확인
        var maxMonth = (selectedYear === currentYear) ? currentMonth : 12;

        // 새로운 월 옵션 추가
        for (var month = 1; month <= maxMonth; month++) {
            var option = document.createElement("option");
            option.text = month;
            option.value = month;
            monthSelect.add(option);
        }
    });
}

// 라디오버튼을 2개로 나눠서 이용하는 함수
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

// 채권 채무 수정 모달을 열고 닫는 함수
function modalOnClick(btnClass, modalId, modalNumberId, submitNumberId) {
    // 수정 버튼 클릭 시 모달 열기
    $(document).on('click', '.' + btnClass, function() {
        // 클릭된 버튼이 속한 행을 찾습니다.
        var $row = $(this).closest('tr');
        // 행에서 첫 번째 열의 텍스트(채권 번호 또는 채무 번호)를 가져옵니다.
        var number = $row.find('td:first').text().trim();
        // 가져온 번호를 출력합니다.
        $('#' + modalNumberId).text(number);
        $('#' + submitNumberId).val(number);
        // 모달 열기
        $('#' + modalId).modal('show');

        // 모달 닫기 버튼 클릭 시 모달 닫기
        $(document).on('click', '.close', function() {
            $('#' + modalId).modal('hide');
        });    
    });
}

// 입금 전표 모달을 열고 닫는 함수(안에 내용 변화가 없는 모달 - 임시조치)
function depositSlipModalOnClick(btnClass, modalId) {
    // 수정 버튼 클릭 시 모달 열기
    $(document).on('click', '.' + btnClass, function() {
		// 클릭된 버튼이 속한 행을 찾습니다.
        var $row = $(this).closest('tr')
        // 행의 순서를 찾습니다.
		var number = $row.find('td:first').text().trim();
		var trader = $row.find('td:nth-child(3)').text().trim();
		var description = $row.find('td:nth-child(4)').text().trim();
		var amount = parseInt($row.find('td:nth-child(5)').text().trim().replace(/,/g, ''));
		var createAt = $row.find('td:nth-child(7)').text().trim();
		
		$('#slipNumber').text(number);
		$('#trader1').text(trader);
		$('#trader2').text(trader);
		$('#trader3').text(trader);
		$('#description1').text(description);
		$('#description2').text(description);
		$('#description3').text(description);
		
		var cal = Math.round(amount * 1 / 11);
		
		var VAT = cal - (cal % 10);
		var netIncome = amount - VAT;
		
		// 반올림
        if (trader === '달토끼') {
            $('#amountR1').text(netIncome.toLocaleString());
            $('#amountR2').text(VAT.toLocaleString());         
        } else {
            $('#amountR1').text((amount).toLocaleString());
            $('#amountR2').text("");           
        }
        
        $('#amountL3').text((amount).toLocaleString());
        $('#totalAmountL').text((amount).toLocaleString());
        $('#totalAmountR').text((amount).toLocaleString());
        
        $('#createAt').text(createAt);

        // 모달 열기
        $('#' + modalId).modal('show');

        // 모달 닫기 버튼 클릭 시 모달 닫기
        $(document).on('click', '.close', function() {
            $('#' + modalId).modal('hide');
        });    
    });
}

// 출금 전표 모달을 열고 닫는 함수(안에 내용 변화가 없는 모달 - 임시조치)
function withdrawalSlipModalOnClick(btnClass, modalId) {
    // 수정 버튼 클릭 시 모달 열기
    $(document).on('click', '.' + btnClass, function() {
		// 클릭된 버튼이 속한 행을 찾습니다.
        var $row = $(this).closest('tr')
        // 행의 순서를 찾습니다.
		var number = $row.find('td:first').text().trim();
		var trader = $row.find('td:nth-child(3)').text().trim();
		var description = $row.find('td:nth-child(4)').text().trim();
		var amount = parseInt($row.find('td:nth-child(5)').text().trim().replace(/,/g, ''));
		var createAt = $row.find('td:nth-child(7)').text().trim();		
		
		$('#slipNumber').text(number);
		$('#trader1').text(trader);
		$('#trader2').text(trader);
		$('#trader3').text(trader);
		$('#description1').text(description);
		$('#description2').text(description);
		$('#description3').text(description);
		
		var cal = Math.round(amount * 1 / 11);
		
		var VAT = cal - (cal % 10);
		var netIncome = amount - VAT;
		
		// 반올림
        $('#amountL1').text(netIncome.toLocaleString());
        $('#amountL2').text(VAT.toLocaleString());
        $('#amountR3').text((amount).toLocaleString());
        $('#totalAmountL').text((amount).toLocaleString());
        $('#totalAmountR').text((amount).toLocaleString());
        
        $('#createAt').text(createAt);

        // 모달 열기
        $('#' + modalId).modal('show');

        // 모달 닫기 버튼 클릭 시 모달 닫기
        $(document).on('click', '.close', function() {
            $('#' + modalId).modal('hide');
        });    
    });
}

// 판매 전표 모달을 열고 닫는 함수(안에 내용 변화가 없는 모달 - 임시조치)
function saleSlipModalOnClick(btnClass, modalId) {
    // 수정 버튼 클릭 시 모달 열기
    $(document).on('click', '.' + btnClass, function() {
		// 클릭된 버튼이 속한 행을 찾습니다.
        var $row = $(this).closest('tr')
        // 행의 순서를 찾습니다.
		var number = $row.find('td:first').text().trim();
		var description = $row.find('td:nth-child(4)').text().trim();
		var amount = parseFloat($row.find('td:nth-child(5)').text().trim().replace(/,/g, ''));
		var createAt = $row.find('td:nth-child(7)').text().trim();
		var seller = $row.find('td:nth-child(9)').text().trim();
		
		$('#slipNumber').text(number);
		$('#trader1').text(seller);
		$('#trader2').text(seller);
		$('#trader3').text(seller);
		$('#description1').text(description);
		$('#description2').text(description);
		$('#description3').text(description);
		
		var cal = Math.round(amount * 1 / 11);
		
		var VAT = cal - (cal % 10);
		var netIncome = amount - VAT;
		
		// 반올림
        $('#amountR1').text(netIncome.toLocaleString());
        $('#amountR2').text(VAT.toLocaleString());
        $('#amountL3').text((amount).toLocaleString());
        $('#totalAmountL').text((amount).toLocaleString());
        $('#totalAmountR').text((amount).toLocaleString());
        
        $('#createAt').text(createAt);

        // 모달 열기
        $('#' + modalId).modal('show');
        
        $('#info').text('판매 : ' + seller + ' / ' + Math.round(amount * 10 / 11).toLocaleString() + ' / ' + Math.round(amount * 1 / 11).toLocaleString());

        // 모달 닫기 버튼 클릭 시 모달 닫기
        $(document).on('click', '.close', function() {
            $('#' + modalId).modal('hide');
        });    
    });
}

// 모달의 폼 제출 함수
function modalSubmit(submitId, priceFieldId, amountId, maturityDateId, formId) {
    $('#' + submitId).click(function(event) {
        // 폼 안에 있는 입력 요소들의 값을 가져옴
        var priceField = $('#' + priceFieldId).val();
        var amount = $('#' + amountId).val();
        var maturityDate = $('#' + maturityDateId).val();
        
        // 증감 구분, 만기일자가 null 값이고, 변제 금액이 0인 경우 폼 제출을 막음
        if (priceField === '0' && !maturityDate && amount === '0') {
            event.preventDefault(); // 폼 제출 막기
            alert('하나 이상의 변경값을 입력해 주세요.'); // 사용자에게 알림
        } else {
            $('#' + formId).submit(); // 조건이 충족되면 폼 제출
        }
    });
}

// 모달의 select 중 pricelabel 설명을 표시
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