# 치킨 브랜드 선호도 조사
## 1. 요구사항 - 어떤 결과가 구현되어야 하는가
1. 회원만 설문조사에 참여할 수 있다.
2. 설문조사 항목을 사용자가 추가할 수 있다.
3. 설문조사 현황 확인은 비회원도 사용 가능
4. 전체 현황 확인할 수 있어야 한다.
5. 나이대별로 설문조사의 결과를 확인할 수 있어야 한다.
<div style="width: 80%;">

![flow chart](/img/FlowChart.png)
</div>

<br/>
<br/>

## 2. 데이터베이스 모델 - 결과 동작을 위해 필요한 데이터가 무엇인가
[여기](/surveyapplication/sqlQuery)를 눌러 SQL 쿼리를 확인할 수 있습니다.

**토글을 눌러 해당 테이블의 상세 정보를 확인하세요.**
<details>
<summary>INFO table - 회원의 정보를 저장하는 테이블</summary>

- INFO_NUMBER : 회원의 고유 번호
- NAME : 회원 이름 저장
- PSWD : 회원 비밀번호 저장
- AGE : 회원 나이 저장
    - 사용자 지정 함수(`AGE_RANGE`)를 통해 나이를 입력하면 나이대가 저장된다.
</details>

<details>
<summary>BRAND table - 치킨 브랜드를 저장하는 테이블</summary>

- BRAND_NUMBER : 브랜드의 고유 번호
- TITLE : 브랜드명
</details>

<details>
<summary>VOTE table - 설문조사 결좌를 저장하는 테이블</summary>

- VOTE_NUMBER : 투표 결과의 고유 번호
- INFO_NUMBER : 투표한 회원의 고유 번호를 참조
- BRAND_NUMBER : 투표한 브랜드의 고유 번호를 참조
</details>
<div style="width: 80%;">

![ER 다이어그램](/img/ClassDiagram.png)
</div>

<br/>
<br/>

## 3. 콘솔 출력 포멧 설계(UI) - 결과를 어떤 형태로 보여줄 것인가
- eclipse console을 통해 설문조사를 실행한다.

<br/>
<br/>

## 4. 기능 설계 - 결과 동작을 위한 클래스 정의
### JdbcTemplate
자주 쓰이는 JDBC API를 클래스로 정의하여 사용한다.

<br/>

### BrandVo
BRAND 테이블의 튜플을 저장하는 용도

<br/>

### VoteVo
VOTE 테이블의 튜플을 저장하는 용도

<br/>

### InfoDAO
|구분|이름|기능|
|---|---|---|
|constructor|`InfoDAO()`|설문조사 참여 메뉴의 서브 메뉴(로그인/회원가입)를 콘솔에 출력한다.|
|method|`signUp(String name, String pswd, int age)`|사용자가 입력한 값으로 회원가입을 한다.<br/>INFO 테이블에 값을 넣는다.|
|method|`isExist(String name, String pwd)`|회원가입 시 INFO 테이블에 이름, 비밀번호 모두가 동일한 튜플이 존재하는지 확인한다.<br/>존재 시 `false`, 미존재 시 `true` 반환|
|method|`login(String name, String pwd)`|INFO 테이블에 입력받은 정보와 동일한 튜플이 존재하는지 확인한다.<br/>존재하면 해당 튜플의 INFO_NUMBER를 반환|

<br/>

### BrandDAO
|구분|이름|기능|
|---|---|---|
|method|`selectAll()`|사용자가 설문에 참여할 때 콘솔에 출력할 항목을 반환한다.<br/>BRAND 테이블에서 모든 데이터를 select 한 후 BrandVo를 생성해서 ArrayList에 추가한 후 해당 리스트를 반환<br/>|
|method|`selectBrand(int infoNum, int brandNum)`|사용자가 투표한 결과를 VOTE 테이블에 저장한다.|
|method|`insertBrand(int infoNumber, String brand)`|사용자가 새로운 투표 항목을 추가할 때 사용된다. <br/>사용자의 고유 번호와 새로운 브랜드명을 입력받아 BRAND 테이블에 항목을 추가한 후 `selectBrand` 메서드를 통해 투표한 결과를 저장한다.|
|method|`brandNumber(int i)`|BRAND 테이블에서 모든 브랜드의 고유 번호를 ArrayList에 담아서 반환한다.<br/>사용자가 선택한 항목이 존재하는지 확인하는 용도로 사용함|

<br/>

### VoteDAO
|구분|이름|기능|
|---|---|---|
|constructor|`VoteDao()`|설문 현황 확인 메뉴의 서브 메뉴들을 콘솔에 출력한다.|
|method|`selectAll()`|VOTE 테이블에서 `group by`를 사용하여 해당 브랜드에 몇 명이 투표했는지 콘솔에 출력한다.|
|method|`selectAge()`|`getAges`를 통해 투표 데이터가 존재하면 해당 나이대에서 몇 명이 투표했는지와 투표 결과를 콘솔에 출력한다.|
|method|`getAges()`|VOTE 테이블에서 투표한 나이대를 ArrayList에 담아 반환한다.<br/>'나이대 별 선호도' 메뉴에서 **투표한 나이대만 콘솔에 출력**하기 위한 메서드|
|method|`getVotes(int age)`|검색하려는 나이대를 입력받아 해당 나이대의 전체 투표수를 key값으로 하고 해당 나이대의 투표 결과를 value로 하는 HashMap을 반환한다.|

<br/>
<br/>
<hr>

# 추후 수정할 기능들
**수정이 완료되면 체크합니다.**
- [x] 중복회원 처리하기
    - 회원가입(`SignUp()`)을 할 때 **이름과 비밀번호가 같으면** 이미 존재하는 회원으로 간주하기
- [x] INFO 테이블에서 나이를 입력하면 나이대로 구분하는 제약조건 추가하기  
    | 사용자 정의 함수 `AGE_RANGE` 생성 및 추가
- [x] 새로운 항목 추가하면 자동으로 VOTE 테이블에 추가 되기
    - [x] 항목 추가 시 번호를 다시 입력하라고 뜸  
        | ~~`checkRange()` 조건문에서 i < length +1 로 변경~~
- [x] 사용자가 메뉴 입력 시 예상과 다른 문자를 입력하면 경고 후 처음부터 다시 시작
- [x] 사용자가 투표를 할 때 범위 외의 값을 입력하면 경고 후 재투표
    1. ~~0 ~ 최대값 사이에 입력한 값이 존재하면 실행, 아니면 다시 입력~~
    2. **where 절을 입력해서 하기**
- [x] 설문조사 항목 번호를 기준으로 오름차순으로 출력
- [x] 나이대 별 선호도 보기에서 총 ~명 값이 이상함
