# Short-Form Script Generator

![대표 이미지](https://github.com/user-attachments/assets/4c68c44b-7a3e-4281-8532-00bb74675c96)

> **"논문 초록을 기반으로 GPT가 자동으로 숏츠 대본을 생성하고, 영상까지 자동 제작하는 서비스입니다."**

## 🚀 프로젝트 개요

### 개발 배경

전문적인 연구 논문은 중요한 정보를 담고 있지만, 대중에게는 접근성이 떨어집니다.
이를 해결하기 위해, **논문의 핵심 내용을 누구나 빠르게 이해할 수 있도록**
GPT-4와 영상 API를 활용해 숏폼 영상 콘텐츠를 자동으로 생성하는 시스템을 개발하게 되었습니다.

### 프로젝트 목표
* 논문 초록을 기반으로 **영상 스크립트를 자동 생성**
* 영상 제작 과정을 **API 기반으로 자동화**
* 사용자는 논문만 업로드하면, **짧은 숏츠 영상이 자동 생성**됨

## 🛠 주요 기능
* **논문 수집**
  * KCI Open API를 통해 논문의 메타데이터 및 초록을 수집합니다.
  * 수집한 논문 정보는 MySQL에 저장되어 관리됩니다.
* **대본 생성**
  * GPT-4를 활용하여 숏츠 영상용 대본을 자동 생성합니다.
  * 초록 내용을 이해하기 쉽게 요약할 수 있도록 프롬프트 구조를 설계하고 튜닝했습니다.
* **영상 자동 제작**
  * Pixabay API 등에서 관련된 영상 리소스를 수집합니다.
  * OpenCV를 활용해 텍스트 기반 스크립트를 영상으로 자동 생성합니다.
* **비동기 처리**
  * 영상 생성 작업은 비동기로 처리되어, 서버의 응답성을 유지합니다.
* **데이터 관리**
  * 생성된 영상 스크립트와 메타데이터는 모두 DB에 저장됩니다.
  * 동일한 논문에 대해 영상 재요청도 가능하도록 설계했습니다.

## 🧩 프로젝트 구조
### 전체 흐름
<img width="600" alt="image" src="https://github.com/user-attachments/assets/b3a78317-874a-4787-a43f-245c56b27783" />

### 기술 스택
* **Backend**: Java 17, Spring Boot 3, Spring WebFlux, SpringAI
* **AI Integration**: GPT-4 (via API), Prompt Engineering
* **Infra**: Docker, MySQL, Redis, FFmpeg, OpenCV
* **API 연동**: KCI OPEN API, Pixabay API
* **비동기 처리**: `WebClient`, `@Async`, `ExecutorService`


## 👥 팀 소개

| [이영재](https://github.com/YeongJae0114)                  | [오예찬](https://github.com/happy-yeachan)                |
| -------------------------------------- | ------------------------------------------------------ |
| <img src="https://github.com/YeongJae0114.png" width="300">   | <img src="https://github.com/happy-yeachan.png" width="300"> |
| 백엔드 (Spring Boot, 영상 API, GPT 연동, </br> KCI API, 비동기 로직, 최적화) | 백엔드 (동영상 생성 API 구현)                                    |


## 📁 시작 가이드

```bash
# 1. 프로젝트 클론
$ git clone https://github.com/YeongJae0114/shorts-generator.git

# 2. 백엔드 디렉토리로 이동
$ cd backend

# 3. 실행
$ ./gradlew bootRun
```

> ⚠️ `application.yml` 내 GPT, Redis, DB 설정 필요
> ⚠️ Docker 기반 Redis, MySQL 구성 권장


## 🧠 프로젝트 인사이트
* **AI + 영상 자동화**라는 새로운 조합을 실현하며, 정보 전달 방식의 변화를 실험
* **Prompt 최적화 + 비동기 처리**로 현실적인 서비스 구현 가능성 확보
* 복잡한 로직은 최대한 **모듈화**하여 유지보수가 용이하도록 구성

## 📘 자세한 문서 보기 (노션)
- 프로젝트 기획부터 설계, 구현, 배포, 회고까지의 전 과정을 문서화한 페이지입니다.
- 각 기능의 설계 배경, 화면 흐름도, 팀원 역할 등 실제 개발의 맥락을 담고 있습니다.

[![Notion](https://img.shields.io/badge/Archive-Notion-000000?logo=notion&logoColor=white)](https://lopsided-stallion-c16.notion.site/1cdc94c8d6d180839392cedced75d68d?pvs=4)

## 📎 관련 링크
* [KCI API 문서](https://www.kci.go.kr/kciportal/po/openapi/openApiMain.kci)
* [Pixabay API 문서](https://pixabay.com/api/docs/)
