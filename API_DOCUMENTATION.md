# API 명세서

---

## 1. Script Controller

### **POST** `/sendShortForm`
- **설명**: 논문 초록 데이터를 기반으로 숏폼 동영상을 생성을 요청합니다.
- **요청 본문**:
    ```json
    {
        "shortFormScript": "논문 대본 내용",
        "articleId": "논문 ID"
    }
    ```
- **응답**:
    ```json
    {
        "status": "success"
    }
    ```

### **POST** `/save/video`
- **설명**: 동영상이 다 만들어졌다면 DB에 저장합니다.
- **요청 본문**:
    ```json
    {
        "scriptId": "ART12345",
        "videoUrl": "https://example.com/video.mp4"
    }
    ```
- **응답**:
    ```json
    {
        "status": "success",
        "message": "동영상이 성공적으로 저장되었습니다."
    }
    ```

---

## 2. KCI Controller

### **GET** `/search`
- **설명**: KCI Open API를 사용하여 논문 정보를 검색합니다.
- **요청 파라미터**:
    - `query` (string): 검색 제목
    - `query` (string): 검색 소속
    - `query` (string): 검색 키워드
    - `query` (string): 검색 저자
- **응답**:
    ```json
    {
        "status": "success",
        "results": [
            {
                "articleId": "12345",
                "title": "논문 제목",
                "authors": ["저자1", "저자2"],
                "abstract": "초록 내용",
                "url": "KCI의 논문 정보 URL",
                "pubYear": "발행년도"
            }
        ]
    }
    ```

---

## 3. API Controller

### **GET** `/api/script`
- **설명**: 저장된 모든 스크립트 정보를 가져옵니다.
- **응답**:
    ```json
    {
        "status": "success",
        "scripts": [
            {
                "id": 0,
                "articleId": "string",
                "articleTitle": "string",
                "shortFormScript": "string",
                "url": "string",
                "pubYear": 0,
                "videoUrl": "string"
            }
        ]
    }
    ```

### **GET** `/api/script/{articleId}`
- **설명**: 특정 논문 ID에 대한 스크립트 정보를 가져옵니다.
- **요청 경로 변수**:
    - `articleId` (string): 논문 ID
- **응답**:
    ```json
    {
        "status": "success",
        "script": {
            "id": 0,
            "articleId": "string",
            "articleTitle": "string",
            "shortFormScript": "string",
            "url": "string",
            "pubYear": 0,
            "videoUrl": "string"
        }
    }
    ```
