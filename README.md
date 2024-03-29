# HappyHouse: 권덕주, 김시언


## 구현 여부

### 요구 사항

|      구현 기능      | 구현 여부 |
| :-----------------: | :-------: |
|      메인 화면       |     ✔     |
| 실거래가 검색, 결과   |     ✔     |
|      회원 관리       |     ✔     |
|   로그인/로그아웃    |     ✔     |
|    공지사항 관리     |     ✔     |

<br>

### UseCase Diagram

<img src="./img/happyhouse_usecase_diagram.png" />

<br>

- Controller

  - [AuthController.java](src/main/java/com/ssafy/web/controller/AuthController.java)
  - [BoardController.java](src/main/java/com/ssafy/web/controller/BoardController.java)
  - [HouseController.java](src/main/java/com/ssafy/web/controller/HouseController.java)
- Service

  - [BoardService.java](src/main/java/com/ssafy/web/service/BoardService.java)
  - [HouseDealService.java](src/main/java/com/ssafy/web/service/HouseDealService.java)
  - [UserService.java](src/main/java/com/ssafy/web/service/UserService.java)

- DAO

  - [BoardDAO.java](src/main/java/com/ssafy/web/dao/BoardDAO.java)
  - [UserDAO.java](src/main/java/com/ssafy/web/dao/UserDAO.java)

- DTO
  - [BoardDTO.java](src/main/java/com/ssafy/web/dto/BoardDTO.java)
  - [UserDTO.java](src/main/java/com/ssafy/web/dto/UserDTO.java)
  - [HouseDealDTO.java](src/main/java/com/ssafy/web/dto/HouseDealDTO.java)

<br>

## 실행 화면

### 1. 메인 페이지

#### 첫 화면: [index.jsp](src/main/webapp/WEB-INF/views/index.jsp)

![image_info](./img/메인_페이지.png)

<br>

### 2. 실거래가 검색/조회

#### 초기 화면: [search.jsp](src/main/webapp/WEB-INF/views/search.jsp)

![image_info](./img/HouseDeal/초기화면.png)

<br>

#### 동별실거래가 조회: search.jsp

![image_info](./img/HouseDeal/실거래가 조회.png)


<br>

#### 상세정보 조회: search.jsp => 모달을 이용하여 구현

![image_info](./img/HouseDeal/상세정보 조회.png)


<br>


### 3. 로그인/로그아웃

<br>

#### 로그인 초기 화면
<br>

![image_info](./img/userinfo/초기 로그인 화면.png)

#### 로그인 성공
<br>

![image_info](./img/userinfo/로그인 성공.png)


#### 로그인 실패
<br>

![image_info](./img/userinfo/로그인 실패.png)


<br>


### 4. 회원 관리
<br>

#### 회원 가입 페이지
<br>

![image_info](./img/userinfo/회원 가입 페이지.png)

#### 회원 가입 성공시
<br>

![image_info](./img/userinfo/회원 가입 성공.png)

#### 회원 가입 실패 - 항목을 모두 입력하지 않았을 경우
<br>

![image_info](./img/userinfo/회원 가입 실패1.png)

#### 회원 가입 실패 - 이미 존재하는 아이디로 가입하려 했을 경우
<br>

![image_info](./img/userinfo/회원 가입 실패2.png)

#### 아이디 몇 개를 회원 가입한 후 db 상태
<br>

![image_info](./img/userinfo/회원 가입 db.png)

#### ssafy1 아이디로 로그인 후 마이페이지 진입(우측 상단의 아이콘 클릭 시)
<br>

![image_info](./img/userinfo/마이 페이지.png)

#### 회원 정보 변경(예시에서는 이름, 이메일 전화번호 변경)
<br>

![image_info](./img/userinfo/회원 정보 변경 성공.png)

#### 회원 정보 변경 후 db
<br>

![image_info](./img/userinfo/회원 정보 변경 db.png)

#### 회원 탈퇴
<br>

![image_info](./img/userinfo/회원 탈퇴.png)

#### 회원 탈퇴 후 db
<br>

![image_info](./img/userinfo/회원 탈퇴 db.png)


<br>



### 5. 공지사항 관리

#### 초기 화면 : postList.jsp


![image_info](./img/board/최초 화면.png)

<br>

#### 글 작성 화면 : [addPost.jsp](src/main/webapp/WEB-INF/views/board/addPost.jsp)

![image_info](./img/board/글 작성 중.png)


<br>

#### 몇 개 글 작성 이후 : [postList.jsp](src/main/webapp/WEB-INF/views/board/postList.jsp)

![image_info](./img/board/글 작성.png)

<br>

#### 글 작성 후 DB 상태

![image_info](./img/board/글 작성 db.png)

<br>

#### 글 수정 : [updatePost.jsp](src/main/webapp/WEB-INF/views/board/updatePost.jsp)

![image_info](./img/board/글 수정.png)

<br>

#### 글 수정 반영됨 : postList.jsp

![image_info](./img/board/수정 반영.png)

<br>

#### 글 수정이 반영된 상세 화면 : [post.jsp](src/main/webapp/WEB-INF/views/board/post.jsp)

![image_info](./img/board/수정 반영2.png)

<br>

#### 글 수정 후 DB 상태

![image_info](./img/board/수정 반영 db.png)

<br>

#### 2번 글 삭제 후 화면: postList.jsp

![image_info](./img/board/2번 글 삭제.png)

<br>

#### 글 삭제 후 DB 상태

![image_info](./img/board/삭제 반영 db.png)

<br>
