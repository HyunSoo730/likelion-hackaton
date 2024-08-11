# 다오(DAO) 🌟

<div align="center">
<img width="450" alt="다오 이미지" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/71dcaa3d-b255-4f9d-b6e3-588662da0afd">
</div>

# 다오(DAO) - 어르신들을 위한 맞춤형 일자리 추천 서비스 👥
> **세종대학교 멋쟁이 사자처럼 해커톤 : 디지털 격차를 해결할 수 있는 서비스**  
> **개발기간: 2023.06 ~ 2023.08 (12주)**

## 💥 프로젝트 소개

다오(DAO)는 고령화 사회에서 어르신들의 재취업을 지원하기 위한 맞춤형 일자리 추천 서비스입니다. 어르신들의 거주 지역과 관심사를 고려하여 적합한 일자리를 추천하고, 새로운 일자리 정보를 실시간으로 알림으로 제공합니다. 💼

다오는 어르신들의 디지털 정보 접근성 부족 문제를 해결하고, 재취업 기회를 확대하는 것을 목표로 합니다. 사용자 친화적인 인터페이스와 직관적인 기능을 통해 어르신들도 쉽게 일자리 정보를 확인하고 지원할 수 있도록 도와줍니다. 📱

프로젝트를 통해 고령층의 고용 문제 해결에 기여하고, 어르신들의 사회 참여 기회를 높이는 것이 다오의 궁극적인 목표입니다. 앞으로도 다오는 어르신들의 목소리에 귀 기울이며, 지속적으로 서비스를 개선해 나갈 예정입니다. 🎯

## 👥 팀 소개

### 백엔드

|    조현수      |          김민섭         |                                                                                                               
| :------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | 
|   <img width="160px" src="https://i.postimg.cc/d0G72ZM3/image.jpg" />    |                      <img width="160px" src="https://user-images.githubusercontent.com/50205887/207570536-f5a82e48-99a1-4399-91d3-75fc5f8f3349.png" />    |   
|   [@hyunsoo730](https://github.com/hyunsoo730)   |    [@k-ms1998](https://github.com/k-ms1998)  |   
| 세종대학교 소프트웨어학과 4학년 | 세종대학교 소프트웨어학과 4학년 |

### 프론트엔드

|    문성희      |          고혜린         |       전경원         |                                                                                                               
| :------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | 
|   <img width="160px" src="https://user-images.githubusercontent.com/50205887/207570536-f5a82e48-99a1-4399-91d3-75fc5f8f3349.png" />    |                      <img width="160px" src="https://user-images.githubusercontent.com/50205887/207570536-f5a82e48-99a1-4399-91d3-75fc5f8f3349.png" />    |                   <img width="160px" src="https://user-images.githubusercontent.com/50205887/207570536-f5a82e48-99a1-4399-91d3-75fc5f8f3349.png"/>   |
|   [@seong-hui](https://github.com/seong-hui)   |    [@hlynnn](https://github.com/hlynnn)  | [@jeonkyungwon](https://github.com/jeonkyungwon)  |
| 세종대학교 지능기전 4학년 | 세종대학교 지능기전 4학년 | 세종대학교 지능기전 4학년 |

## 🙋‍♂️ 백엔드 개발자 역할 및 기여도
| 이름 | 역할 및 기여도 |
|------|--------------|
| 조현수 | - 🚀 백엔드 개발 리드로서 백엔드 아키텍처 설계 및 개발 총괄<br>- 🎨 Spring Boot를 활용한 REST API 구현 및 문서화<br>- ⚙️ Spring Batch를 활용한 일자리 정보 수집 배치 작업 구현<br>- 📞 카카오톡 알림 API 연동 및 알림 발송 비즈니스 로직 개발<br>- 💾 MySQL 데이터베이스 설계 및 쿼리 최적화 |
| 김민섭 | - 🤝 아키텍처 설계 및 개발 & 백엔드 서버 개발 담당<br>- 🌿 디자인 패턴을 적용한 API 컨트롤러 개발 및 예외 처리<br>- 🗃️ MyBatis를 활용한 데이터 액세스 로직 개발 및 쿼리 작성<br>- 📊 사용자 행동 데이터 수집 및 분석을 위한 로그 설계 및 로깅 작업<br>- 🧪 단위 테스트 코드 작성을 통한 코드 품질 개선 활동 |

## 🌈 이용 가이드

### Requirements

프로젝트를 빌드하고 실행하기 위해 다음 환경이 필요합니다:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Node.js 14.19.3](https://nodejs.org/ca/blog/release/v14.19.3/)
- [Npm 9.2.0](https://www.npmjs.com/package/npm/v/9.2.0)

### Installation

1. 프로젝트 클론
```bash
$ git clone https://github.com/HyunSoo730/likelion-hackaton.git
$ cd likelion-hackaton

Back-End
$ ./mvnw clean install
$ java -jar target/[your_project].jar

Front-End
$ nvm use v.14.19.3
$ npm install 
$ npm run dev
```

## 🛠️ 기술 스택

### Environment

![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)
![Github](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white)             

### Config

![npm](https://img.shields.io/badge/npm-CB3837?style=for-the-badge&logo=npm&logoColor=white)   <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

### Development

![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=Javascript&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Bootstrap](https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=Bootstrap&logoColor=white)
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

### Communication

![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white)
![GoogleMeet](https://img.shields.io/badge/GoogleMeet-00897B?style=for-the-badge&logo=Google%20Meet&logoColor=white)

## 📊 데이터베이스 설계
### ERD (Entity-Relationship Diagram)
![ERD](https://i.postimg.cc/tC9B4RhZ/ERD.png)

프로젝트의 데이터베이스 설계는 위와 같은 ERD를 바탕으로 이루어졌습니다.

- `User` 테이블: 사용자 정보를 저장하는 테이블입니다. 사용자의 기본 정보와 함께 관심 지역(`Region`)과 매칭된 일자리(`Job`) 정보를 외래키로 참조합니다.
- `Region` 테이블: 지역 정보를 저장하는 테이블입니다. 사용자의 관심 지역과 일자리의 근무 지역을 관리합니다.
- `Job` 테이블: 일자리 정보를 저장하는 테이블입니다. 일자리의 상세 정보와 함께 근무 지역(`Region`) 정보를 외래키로 참조합니다.
- `Application` 테이블: 사용자의 일자리 지원 정보를 저장하는 테이블입니다. 사용자(`User`)와 일자리(`Job`)의 외래키를 참조하여 지원 내역을 관리합니다.
- `Notification` 테이블: 사용자에게 전송된 알림 정보를 저장하는 테이블입니다. 사용자(`User`)와 일자리(`Job`)의 외래키를 참조하여 알림 내역을 관리합니다.

ERD에서 볼 수 있듯이, `User`와 `Region`, `Job` 테이블은 다대다(Many-to-Many) 관계를 가지고 있습니다. 이는 한 사용자가 여러 지역에 관심을 가질 수 있고, 한 지역에 여러 사용자가 관심을 가질 수 있음을 나타냅니다. 마찬가지로 한 사용자는 여러 일자리에 지원할 수 있고, 한 일자리에는 여러 사용자가 지원할 수 있습니다.

`Application`과 `Notification` 테이블은 `User`와 `Job` 테이블의 관계를 나타내는 연결 테이블(Join Table)의 역할을 합니다. 이를 통해 사용자의 일자리 지원 내역과 알림 내역을 효과적으로 관리할 수 있습니다.

데이터베이스 설계는 프로젝트의 요구사항과 확장성을 고려하여 진행되었으며, 필요에 따라 추가적인 테이블이나 관계를 도입할 수 있는 유연성을 가지고 있습니다. 🗄️

## 📺 화면 구성

| 메인 페이지 | 통합 구직 정보 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" src="https://i.postimg.cc/XN5xKVXC/image.png"/> | <img width="400" src="https://i.postimg.cc/KvNPQ4bR/image.png"/> |
| 메인 페이지에서는 거주 지역 기반 일자리 추천 및 검색 기능을 제공합니다. 사용자는 간편하게 자신에게 맞는 일자리를 찾아볼 수 있습니다. 📍 | 통합 구직 정보 페이지에서는 다양한 일자리 정보를 한 눈에 확인할 수 있습니다. 일자리 카테고리별로 구분되어 있어 원하는 정보를 쉽게 찾을 수 있습니다. 🔎 |

| 채용 상세 정보 | 알림 신청 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" src="https://i.postimg.cc/LsqjHT11/image.png"/> | <img width="400" src="https://i.postimg.cc/QNg1xVH0/image.png"/> |
| 채용 상세 정보 페이지에서는 일자리의 자세한 내용과 지원 방법을 확인할 수 있습니다. 사용자는 이를 통해 해당 일자리가 자신에게 적합한지 판단할 수 있습니다. 📝 | 알림 신청 페이지에서는 관심 지역을 설정하고 일자리 알림을 신청할 수 있습니다. 관심 지역에 새로운 일자리가 등록되면 카카오톡으로 즉시 알림을 받을 수 있어 편리합니다. 🔔 |

| 구직 정보 신청 | 구직 정보 신청 팝업 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" src="https://i.postimg.cc/XNgB6hzP/image.png"/> | <img width="400" src="https://i.postimg.cc/B6pSRWnc/image.png"/> |
| 구직 정보 신청 페이지에서는 원하는 일자리에 간편하게 지원할 수 있습니다. 사용자 정보를 입력하고 지원 동기를 작성하여 기업에 어필할 수 있습니다. ✍️ | 구직 정보 신청 시 해당 일자리의 주요 정보를 한 번 더 확인할 수 있는 팝업 창이 제공됩니다. 이를 통해 사용자는 자신의 선택을 되돌아보고 신중하게 지원할 수 있습니다. 🤔 |

| 관심 지역 변경 팝업 | 구직 정보 신청 해지 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" src="https://i.postimg.cc/QN6NbrPj/image.png"/> | <img width="400" src="https://i.postimg.cc/P5DtTvqq/image.png"/> |
| 관심 지역 변경 팝업에서는 사용자의 관심 지역을 변경할 수 있습니다. 이사 등으로 인해 거주 지역이 변경된 경우 간편하게 수정할 수 있어 유용합니다. 🏠 | 구직 정보 신청 해지 페이지에서는 이전에 신청한 구직 정보를 취소할 수 있습니다. 사용자는 언제든지 자신의 선택을 철회하고 다른 일자리에 지원할 수 있습니다. ❌ |

| 신청한 공공 서비스 | 교육 정보 |
| :-------------------------------------------: | :-------------------------------------------: |
| <img width="400" src="https://i.postimg.cc/jjQtJVP0/image.png"/> | <img width="400" src="https://i.postimg.cc/0jhLBZD0/image.png"/> |
| 신청한 공공 서비스 페이지에서는 사용자가 신청한 공공 서비스 내역을 한 눈에 확인할 수 있습니다. 서비스 상태 추적과 관련 문의가 가능합니다. 📜 | 교육 정보 페이지에서는 어르신들의 역량 강화를 위한 다양한 교육 프로그램 정보를 제공합니다. 직업 교육부터 여가 생활까지 폭넓은 교육 콘텐츠를 확인할 수 있습니다. 🎓 |




## 📦 주요 기능
### ⭐️ 맞춤형 일자리 추천
사용자의 거주 지역을 기반으로 사용자 개개인에게 특화된 일자리를 제안합니다.

### ⭐️ 실시간 일자리 알림
사용자가 설정한 관심 지역에 새로운 일자리가 등록되면 카카오톡으로 실시간 알림을 보내줍니다. 어르신들은 알림을 통해 새로운 기회를 즉시 확인하고 지원할 수 있습니다. 📣

### ⭐️ 사용자 친화적 인터페이스
직관적이고 눈에 잘 띄는 디자인으로 어르신들도 쉽게 서비스를 이용할 수 있도록 UI/UX를 설계하였습니다. 불필요한 기능을 최소화하고, 한 눈에 필요한 정보를 파악할 수 있도록 구성하였습니다. 👀

### ⭐️ 교육 프로그램 연계
일자리 추천과 더불어 어르신들의 역량 강화를 위한 다양한 교육 프로그램 정보(서울시 공공 서비스)를 제공합니다. 직업 교육, 디지털 활용 교육 등을 통해 어르신들의 경쟁력을 높일 수 있도록 지원합니다. 🌱

### ⭐️ 안정적인 API 서버
백엔드는 Spring Boot로 구축된 RESTful API 서버로, 프론트엔드와의 원활한 통신을 위해 직관적이고 일관된 API 엔드포인트를 제공합니다.

### ⭐️ 빠른 데이터 조회
대량의 일자리 데이터를 효과적으로 관리하기 위해 데이터베이스 인덱싱과 쿼리 최적화 기법을 활용하였습니다.

### ⭐️ 자동화된 배치 작업
일자리 데이터 수집, 알림 발송 등의 반복적인 작업은 Spring Batch와 Quartz를 활용하여 자동화하였습니다. 정해진 스케줄에 따라 안정적으로 배치 작업이 수행되며, 사용자에게 최신 정보를 제공합니다.

### ⭐️ 단위 테스트
코드의 품질과 안정성을 보장하기 위해 JUnit5를 활용한 단위 테스트를 작성하여 개발 과정에서 발생할 수 있는 버그와 이슈를 사전에 방지하였습니다.

## 🏛️ 아키텍처
### 디렉토리 구조

```plaintext
root
├── backend
│   ├── Dockerfile
│   ├── dao.iml
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── likelion
│       │   │           └── dao
│       │   │               ├── DaoApplication.java
│       │   │               ├── config
│       │   │               │   ├── BatchConfig.java
│       │   │               │   ├── OpenApiConfig.java
│       │   │               │   └── WebMvcConfig.java
│       │   │               ├── controller
│       │   │               │   ├── JobController.java
│       │   │               │   ├── RegionController.java
│       │   │               │   └── UserController.java
│       │   │               ├── dto
│       │   │               │   ├── JobDto.java
│       │   │               │   ├── RegionDto.java
│       │   │               │   └── UserDto.java
│       │   │               ├── entity
│       │   │               │   ├── Job.java
│       │   │               │   ├── Region.java
│       │   │               │   └── User.java
│       │   │               ├── repository
│       │   │               │   ├── JobRepository.java
│       │   │               │   ├── RegionRepository.java
│       │   │               │   └── UserRepository.java
│       │   │               └── service
│       │   │                   ├── JobService.java
│       │   │                   ├── KakaoAlarmService.java
│       │   │                   ├── RegionService.java
│       │   │                   └── UserService.java
│       │   └── resources
│       │       ├── application.properties
│       │       └── application-prod.properties
│       └── test
│           └── java
│               └── com
│                   └── likelion
│                       └── dao
│                           └── DaoApplicationTests.java
├── frontend
│   ├── README.md
│   ├── node_modules
│   ├── package-lock.json
│   ├── package.json
│   ├── public
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   ├── logo192.png
│   │   ├── logo512.png
│   │   ├── manifest.json
│   │   └── robots.txt
│   └── src
│       ├── App.css
│       ├── App.js
│       ├── App.test.js
│       ├── components
│       │   ├── Footer.js
│       │   ├── Header.js
│       │   ├── JobDetail.js
│       │   ├── JobList.js
│       │   ├── KakaoAlarmModal.js
│       │   ├── MyPage.js
│       │   └── RegionSelect.js
│       ├── index.css
│       ├── index.js
│       ├── logo.svg
│       ├── pages
│       │   ├── AlarmPage.js
│       │   ├── JobDetailPage.js
│       │   ├── JobListPage.js
│       │   ├── MainPage.js
│       │   └── MyPage.js
│       ├── reportWebVitals.js
│       ├── services
│       │   ├── JobService.js
│       │   ├── KakaoAlarmService.js
│       │   ├── RegionService.js
│       │   └── UserService.js
│       └── setupTests.js
└── README.md

```


## 🎉 프로젝트 결과 및 성과
- 🏠 사용자의 거주 지역을 기반으로 맞춤형 일자리를 추천하여 고령층의 재취업 접근성을 높였습니다.
- 📣 카카오톡 알림 API를 활용해 새로운 일자리가 등록되면 사용자에게 알림을 전송하는 기능을 구현하였습니다.
- 🌐 Spring Boot와 MyBatis, JPA, Querydsl을 활용하여 안정적이고 효율적인 RESTful API 서버를 구축하였습니다.
 - MyBatis를 사용하여 데이터베이스 연동 및 쿼리 매핑을 간편하게 처리하였습니다.
 - JPA를 도입하여 객체-관계 매핑(ORM)을 통해 데이터베이스 작업을 편리하게 수행하였습니다.
 - Querydsl을 활용하여 동적 쿼리를 생성하고 실행함으로써 복잡한 검색 조건을 유연하게 처리하였습니다.
- 🗄️ 대량의 데이터를 효과적으로 관리하기 위해 데이터베이스 인덱싱과 쿼리 최적화를 진행하여 검색 및 조회 속도를 개선하였습니다.
- ⏰ Spring Batch와 Quartz를 활용해 데이터 수집과 알림 발송 등의 배치 작업을 자동화하여 운영 효율성을 높였습니다.
- ✅ JUnit5를 활용한 단위 테스트를 지속적으로 작성하고 수행하여 서비스의 안정성을 확보하였습니다.

## 🔧 개선 사항 및 추후 계획
- 🙌 사용자들의 피드백을 적극 수렴하여 서비스 사용성과 편의성을 지속적으로 개선해 나갈 예정입니다.
 - 예) 거주 지역 + 어르신들의 경력을 더해 추천 공고를 제공해볼 생각
- 🤝 디지털 리터러시가 낮은 고령층 사용자를 위해 서비스 이용 가이드 제작 및 첫 사용자 튜토리얼 기능을 도입할 계획입니다.
- 📈 서비스 모니터링 및 로깅 체계를 강화하여 잠재적인 문제를 사전에 감지하고 신속하게 대응할 수 있도록 할 예정입니다.
- 🚀 배치 작업의 성능을 향상시키기 위해 멀티 스레드 환경에서의 병렬 처리를 적용하는 방안을 생각하고 있습니다.

## 🚨 트러블슈팅 가이드
### N+1 문제 발생
- 증상: 일자리 상세 정보를 조회할 때 연관된 엔티티들을 즉시 로딩(EAGER)하여 불필요한 쿼리가 다수 발생하는 문제가 발견되었습니다.
- 원인: JPA의 즉시 로딩 전략으로 인해 연관된 엔티티를 조회할 때마다 추가 쿼리가 실행되어 성능 저하가 발생하였습니다.
- 해결: 지연 로딩(LAZY) 전략으로 변경하고, 필요한 경우에만 명시적으로 페치 조인(Fetch Join)을 사용하여 연관된 엔티티를 함께 조회하도록 최적화하였습니다. 이를 통해 불필요한 쿼리 실행을 방지하고 성능을 개선할 수 있었습니다.

### 대량 데이터 처리 시 OOM 발생
- 증상: 일자리 매칭 배치 작업을 수행할 때 대량의 데이터를 한 번에 메모리에 로딩하여 Out of Memory(OOM) 오류가 발생하였습니다.
- 원인: 배치 작업에서 대량의 데이터를 한 번에 처리하려고 시도하여 메모리 부족 현상이 발생하였습니다.
- 해결: Spring Batch의 Chunk 기반 처리를 적용하여 데이터를 적절한 크기로 분할하여 처리하도록 변경하였습니다. 이를 통해 메모리 사용량을 최소화하고 안정적으로 대량의 데이터를 처리할 수 있게 되었습니다.

### 카카오톡 알림 API 호출 실패
- 증상: 일자리 매칭 결과를 사용자에게 카카오톡으로 알림을 보낼 때 간헐적으로 API 호출이 실패하는 문제가 발생하였습니다. 
- 원인: 카카오톡 알림 API의 요청 제한(rate limit)을 초과하여 일시적으로 호출이 거부되는 것으로 파악되었습니다.
- 해결: 요청 제한을 고려하여 알림 발송 로직을 수정하였습니다. 일정 시간 간격을 두고 알림을 발송하도록 조절하고, 실패한 호출은 재시도하는 메커니즘을 추가하였습니다. 또한, 카카오톡 알림 API의 정책을 면밀히 확인하고 준수하여 안정적인 알림 발송을 유지할 수 있었습니다.

이러한 트러블슈팅 경험을 통해 애플리케이션의 성능과 안정성을 향상시키고, 사용자에게 더 나은 서비스를 제공할 수 있었습니다. 앞으로도 지속적인 모니터링과 개선을 통해 서비스 품질을 높여갈 계획입니다.




<br><br><br>

## 자료 준비
### 프로젝트 소개
<img width="1021" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/5af36eca-e8a2-4029-bd5c-2b33a17d4c61">

### 배경 및 목표
<img width="1029" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/82ba583a-cd8e-4b9b-998e-cbcafb729fb9">
<img width="1028" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/695eb0be-3858-41ac-a7ef-c6ad2bd0828f">
<img width="1023" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/638156f5-59a2-40c7-bba4-bc6dc30c10b8">

### 서비스 기능
<img width="1017" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/75df10eb-93a8-4103-8142-8af2b9fe2381">

### 서비스 특장점
<img width="1023" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/7920b151-5935-485b-94f5-28da29f66a66">
<img width="1013" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/65a6bd58-6cbe-4e35-a058-0f758fd3d07e">

### 비즈니스 모델
<img width="1020" alt="image" src="https://github.com/HyunSoo730/likelion-hackaton/assets/102610889/255e1473-bb2e-4b96-aeca-af5976c60a72">





