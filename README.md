# MenuSelector_2018320192
'오늘은 어디서 밥을 먹지?' 하는 고민할 때 선택을 도와주는 프로그램입니다. 설치 파일은 release 폴더에 있는 MenuSelector.apk 파일입니다.
## 1. 프로그램 구성
1번 창 : 랜덤 식당을 출력하는 버튼과 식당 메뉴 설정 창(2번 레이아웃)으로 넘어가는 버튼(톱니 모양)으로 구성된다.

2번 창 : 식당 메뉴들을 출력하는 리스트뷰와 식당 메뉴의 추가(+ 모양) 및 초기화 버튼(X 모양)으로 구성된다. 리스트뷰의 아이템들은
클릭 시 수정 및 삭제 여부를 묻는 다이얼로그를 생성한다. 식당 메뉴의 추가 및 수정 시 이름과 선호도를 동시에 입력 받는 커스텀
다이얼로그가 나타난다. 리스트뷰의 아이템들은 식당 이름, 식당 선호도, 식당이 나올 확률 값을 출력한다.
### 클래스 구성
Main_Select, Sub_Setting : 1번 레이아웃 및 2번 레이아웃를 구현하고 실행하는 클래스이다.

InputDialog : 이름과 선호도, 두개를 입력받는 커스텀 다이얼로그이다.

Restaurant : 식당 메뉴에 해당하는 클래스. 이름, 선호도, 확률의 세가지 변수들을 가지고 있다.
### 레이아웃 구성
activity_main_select.xml, activity_sub_setting.xml : 각각 1번 창, 2번 창에 해당하는 레이아웃이다.

dialog_take_restaurant.xml : 이름과 선호도 두개를 입력 받는 커스텀 다이얼로그에 해당하는 레이아웃이다.

menu_main.xml, menu_sub.xml : 각각 1번 창, 2번 창의 액션바에 적용되는 레이아웃이다.
## 2. 식당 추가, 수정 및 삭제
식당 메뉴와 관련된 작업은 2번 창에서 할 수 있다. 식당 메뉴에 변동이 일어나면 리스트뷰의 각 아이템들이 출력하는 확률 값도 그에 맞게 수정된다.
### 식당 메뉴 추가
각각 이름과 선호도를 입력하여 식당 메뉴를 추가할 수 있다. 이 때 식당 이름은 이미 존재하는 이름일 수 없으며 선호도는 1에서 5
사이의 정수이어야만 한다. 만약 적절하지 않은 식당이 추가될 시 프로그램은 그 식당을 추가하지 않는다. 추가된 식당의 초기 확률 값은
그 식당의 선호도와 동일하게 설정된다. 새로운 식당 메뉴가 추가되면 리스트뷰의 아이템들이 출력하는 확률 값들이 추가된 메뉴를 포함하여
새롭게 계산된다.
### 식당 메뉴 수정
리스트뷰에서 수정하고자 하는 식당 메뉴를 클릭해 수정을 누르면 식당 메뉴의 수정이 가능하다. 이때 수정하고자 하는 식당 메뉴의 이름과 선호도로
새롭게 입력하면 그 식당 메뉴로 바뀌게 되며 이 때 가능한 이름과 선호도는 식당 메뉴를 추가할 때와 같은 규칙을 따른다. 수정된 식당 메뉴의 초기
확률 값은 수정 되기 이전의 확률 값과 동일하다.
### 식당 메뉴 삭제
리스트뷰에서 수정하고자 하는 식당 메뉴를 클릭해 삭제를 누르면 식당 메뉴의 삭제가 가능하다. 

모든 식당 메뉴들을 없애고 싶을 경우 초기화 버튼을 누름으로써 초기화할 수 있다. 이 때 모든 식당 메뉴들은 한 번에 삭제된다.
## 3. 랜덤 식당 출력
1번 창의 중앙에 있는 Randomizer 버튼을 누르면 랜덤한 식당 값 하나를 출력할 수 있다. 단, 등록된 식당 메뉴의 수가 2개 미만일 경우 Randomize 
버튼은 작동하지 않는다. 확률 값이 높을 수록 그 식당이 출력될 확률이 올라간다.

출력된 식당 메뉴의 확률 값은 0으로 고정된다. 출력되지 않은 다른 모든 식당 메뉴들의 확률 값은 각자 자신의 선호도만큼 올라간다. 이를 통해 바로 
전에 나왔던 식당 메뉴는 절대로 바로 다음에 나올 수는 없으며, 선호도가 높은 메뉴일 수록 이후에 나올 확률이 상승한다.
## 4. 데이터 저장 및 불러오기
프로그램을 종료하면 자동으로 현재 등록된 식당 메뉴들의 정보를 텍스트 파일로 저장한다. 다시 실행하면 저장된 텍스트 파일에서 식당 메뉴들의 정보를 
불러와 식당 메뉴를 구성한다.