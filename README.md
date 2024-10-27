# 짧은 논문 
논문의 초록을 바탕으로 숏폼을 제작하는 프로젝트

## 구상도
![image](https://github.com/user-attachments/assets/1810b9e4-cf25-4922-92aa-65665ed2f5f4)

## 현재 진행 사항
- [x] kci open api 에 논문의 초록과 세부 정보를 가져오기 
- [x] kci 에서 가져온 초록으로 숏폼 대본 생성
- [x] 대본을 생성하고 DB에 대본과 논문의 정보를 저장하기
  - JPA를 사용해 DB 구축
  - Mysql을 사용
- [x] RestTemplate() ->  WebClient() 비동기 통신으로 변경
  - 문제   
    - 1개의 동영상을 만드는 API 응답으로 동영상의 URL을 받는데 10분 정도 걸린다
    - 평균적으로 한번에 7~8개의 영상을 만드는데 restTemplate 으로 요청을 보내면 동영상을 만드는 동안에는 1시간 이상 대기하게 된다   
     
 

