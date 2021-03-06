## 프로그래밍 요구사항

* 인수테스트
* 문서화
* TDD
  * 커밋 단위를 TDD로 진행한다.

## 기능 목록
### 요금 정보 추가
* [x] 거리별 추가요금
    - 기본운임(10㎞ 이내) : 기본운임 1,250원
    - 이용 거리초과 시 추가운임 부과
        - 10km초과 ∼ 50km까지(5km마다 100원)
        - 50km초과 시 (8km마다 100원)
* [x] 노선별 추가요금  
     - 추가 요금이 있는 노선을 이용 할 경우 측정된 요금에 추가
     - 가장 높은 추가 요금만 적용
* [x] 경로 조회시 요금 정보를 함께 응답한다.
* [x] 할인 정책
  * 로그인 사용자만 할인이 가능하다.
  * 청소년(13세 이상~19세 미만): 운임에서 350원을 공제한 금액의 20%할인
  * 어린이(6세 이상~ 13세 미만): 운임에서 350원을 공제한 금액의 50%할인

### (선택) 가장 빠른 경로 도착 타입 추가