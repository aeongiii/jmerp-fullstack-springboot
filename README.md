# JumptoMoon
< 규칙 >
1. 엔티티 칼럼명 적을 때 두 단어 이상이면 언더바(_)로 연결하기(JPA에서 대문자로 작성하면 알아서 언더바로 만들어줌)
2. 변수명이 두 단어 이상이면 앞은 소문자, 뒤는 대문자로 적기 ( ex) businessNumber )
3. 다른 부서 값을 변경할 일이 생기면 담당 인원과 먼저 조율하기
4. main 에 push 할 시(오류수정 등) 카톡방에 알리기
5. main 브랜치는 merge 용도로 사용
6. 부서별 조회 권한부여 : 버튼 비활성화 (권한이 없습니다) 버튼은 보임


=====================================================
<merge 한 후에 pull 하는 과정>

1. git fetch origin : 원격브랜치 업데이트
2. git checkout Development : 기존 작업하던 브랜치에서 Development 브랜치로 이동
3. git pull : 머지했던 내용을 로컬로 내려받기
	* 충돌 발생할 경우, 해당 파일에 직접 들어가 수정 -> 수정된 파일 commit -> git bash에서 현재 상태가 Merging일 경우 "git status" 입력해서 Merging 상태 해제
4. ***pull완료된 것 확인한 후에*** 더이상 사용하지 않는 브랜치 삭제. 
   로컬 브랜치 삭제할 경우 : git branch -d (브랜치 이름)
   원격 브랜치 삭제할 경우 : git push origin --delete (브랜치 이름)

=====================================================
<새로운 브랜치 만들기>

1. git fetch origin : 원격 브랜치 목록 갱신 
	* 만약 이미 삭제된 기존 브랜치때문에 오류 발생하는 경우 *
		git branch --list -r 입력해서 원격 브랜치 목록 확인 후 이상 있으면
		C:\Users\040\git\JumptoMoon\.git에서 config 파일 열어서
		[remote "origin"] 부분을
   		 url = https://github.com/MoonSungBin95/JumptoMoon.git
    		fetch = +refs/heads/*:refs/remotes/origin/* 
		으로 수정하면 모든 브랜치 검색 가능해짐
2. git checkout Development : 현재 위치를 'Development' 브랜치로 전환
3. git checkout -b (새로운 브랜치 이름) : 새 브랜치 만들고 새 브랜치로 이동하기
	Ex) git checkout -b feature/nav/KYE
4. git push origin (새로운 브랜치 이름) : 새 브랜치를 원격 저장소에 푸시
5. git 웹페이지에 새 브랜치가 제대로 올라갔는지 확인!
	* 변경사항 commit 후 처음 push할 경우, 만약 --set-upstream 으로 push하라고 뜨면 그냥 그렇게 하면 됨
======================================================
