# studyProject

1. 프로젝트 : 스터디모임 웹사이트<br>

2. 사용 언어와 기술 : Java 8, Springboot 2.3.2, SpringSecurity, IntelliJ IDEA , Jwt, JDBC, MariaDB, Junit5<br>

3. 주요 기능 : 로그인, 회원가입, 자유게시판, 좋아요, 댓글, 스터디게시판, 페이징, RestfulAPI<br>

4. 프로젝트 Backend Code 입니다!


#### API 명세



#### [인증 APIs](http://localhost:8080/swagger-ui.html#/인증 APIs)Authentication Rest Controller



**POST**[/api/auth](http://localhost:8080/swagger-ui.html#/operations/인증 APIs/authenticationUsingPOST)

사용자 로그인 (API 토큰 필요없음)



#### Parameters



| Name                | Description                                                  |
| :------------------ | :----------------------------------------------------------- |
| authRequest *(body) | **authRequestExample Value**                                                                                                  Model  `{  "credentials": "string",  "principal": "string" }`                         Parameter content typeapplication/json |



#### Responses

Response content type

application/json



| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK  Example Value**                                                                                                                               Model  `{  "error": {    "message": "string",    "status": 0  },  "response": {    "apiToken": "string",    "user": {      "create_at": "2020-10-07T09:09:22.307Z",      "email": {        "address": "string"      },      "last_login_at": "2020-10-07T09:09:22.307Z",      "login_count": 0,      "name": "string",      "seq": 0    }  },  "success": true }` |





#### [사용자 APIs](http://localhost:8080/swagger-ui.html#/사용자 APIs)User Rest Controller



**GET**[/api/user/me](http://localhost:8080/swagger-ui.html#/operations/사용자 APIs/meUsingGET)

내 정보



#### Parameters

No parameters

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK   Example Value**                                                                                                                              Model`{  "error": {    "message": "string",    "status": 0  },  "response": {    "create_at": "2020-10-07T09:16:12.662Z",    "email": {      "address": "string"    },    "last_login_at": "2020-10-07T09:16:12.662Z",    "login_count": 0,    "name": "string",    "seq": 0  },  "success": true }` |





**POST**[/api/user/join](http://localhost:8080/swagger-ui.html#/operations/사용자 APIs/joinUsingPOST)

회원가입 (API 토큰 필요없음)



#### Parameters

Try it out

| Name                | Description                                                  |
| :------------------ | :----------------------------------------------------------- |
| joinRequest *(body) | joinRequestExample ValueModel`{  "credentials": "string",  "name": "string",  "principal": "string" }`Parameter content typeapplication/json |

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK   Example Value**                                                                                                                                Model`{  "error": {    "message": "string",    "status": 0  },  "response": {    "apiToken": "string",    "user": {      "create_at": "2020-10-07T09:17:35.826Z",      "email": {        "address": "string"      },      "last_login_at": "2020-10-07T09:17:35.826Z",      "login_count": 0,      "name": "string",      "password": "string",      "seq": 0    }  },  "success": true }` |



**POST**[/api/user/exists](http://localhost:8080/swagger-ui.html#/operations/사용자 APIs/checkEmailUsingPOST)

이메일 중복확인 (API 토큰 필요없음)



#### Parameters

Try it out

| Name          | Description                                                  |
| :------------ | :----------------------------------------------------------- |
| request(body) | example: {"address": "[test00@gmail.com](mailto:test00@gmail.com)"}                                         Example Value                     Model `{  "additionalProp1": "string",  "additionalProp2": "string",  "additionalProp3": "string" }`  Parameter content typeapplication/json |

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK  Example Value**                                                                                                                              Model`{  "error": {    "message": "string",    "status": 0  },  "response": true,  "success": true }` |





#### [study-rest-controller](http://localhost:8080/swagger-ui.html#/study-rest-controller) Study Rest Controller



**GET**[/api/user/{userId}/study/list](http://localhost:8080/swagger-ui.html#/operations/study-rest-controller/studysUsingGET)

스터디 목록 조회



#### Parameters

Try it out

| Name                          | Description                        |
| :---------------------------- | :--------------------------------- |
| limit ref(query)              | 최대 조회 갯수*Default value* : 20 |
| offset ref(query)             | 페이징 offset*Default value* : 0   |
| userId *integer($int64)(path) | userId                             |

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK  Example Value**                                                                                                                             Model  `{  "error": {    "message": "string",    "status": 0  },  "response": [    {      "fullDescription": "string",      "publishDateTime": "2020-10-07T09:23:23.208Z",      "seq": 0,      "shortDescription": "string",      "title": "string",      "writer": {        "email": {          "address": "string"        },        "name": "string"      },      "zones": "string"    }  ],  "success": true }` |





#### [post-rest-controller](http://localhost:8080/swagger-ui.html#/post-rest-controller)Post Rest Controller



**PATCH**[/api/user/{userId}/post/{postId}/like](http://localhost:8080/swagger-ui.html#/operations/post-rest-controller/likeUsingPATCH)

포스트 좋아요



#### Parameters

Try it out

| Name                         | Description    |
| :--------------------------- | :------------- |
| postId integer($int64)(path) | 대상 포스트 PK |
| userId integer($int64)(path) | 조회대상자 PK  |

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK Example Value**                                                                                                                               Model `{  "error": {    "message": "string",    "status": 0  },  "response": {    "comments": 0,    "contents": "string",    "createAt": "2020-10-07T09:26:03.336Z",    "likes": 0,    "likesOfMe": true,    "seq": 0,    "title": "string",    "writer": {      "email": {        "address": "string"      },      "name": "string"    }  },  "success": true }` |





**GET**[/api/user/{userId}/post/list](http://localhost:8080/swagger-ui.html#/operations/post-rest-controller/postsUsingGET)

포스트 목록 조회



#### Parameters

Try it out

| Name                         | Description                        |
| :--------------------------- | :--------------------------------- |
| limit ref(query)             | 최대 조회 갯수*Default value* : 20 |
| offset ref(query)            | 페이징 offset*Default value* : 0   |
| userId integer($int64)(path) | 조회대상자 PK)                     |

#### Responses

Response content type

application/json

| Code | Description                                                  |
| ---- | ------------------------------------------------------------ |
| 200  | **OK Example Value**                                                                                                                              Model  `{  "error": {    "message": "string",    "status": 0  },  "response": [    {      "comments": 0,      "contents": "string",      "createAt": "2020-10-07T09:27:29.291Z",      "likes": 0,      "likesOfMe": true,      "seq": 0,      "title": "string",      "writer": {        "email": {          "address": "string"        },        "name": "string"      }    }  ],  "success": true }` |
