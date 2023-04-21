# 순회 외판원 문제(Traveling Salesman Problem, TSP)
* 외판원이 자신의 집이 위치하고 있는 도시에서 출발하여 도시들을 각각 한번씩만 방문하고, 다시 자기 도시로 돌아오는 가장 짧은 일주여행경로(tour)를 결정하는 문제
* 음이 아닌 가중치가 있는 방향성 그래프를 대상으로 한다.
* 그래프 상의 일주여행경로: 한 정점을 출발하여 다른 모든 정점을 한번씩만 거쳐서 다시 그 정점으로 돌아오는 경로
* 최적일주여행경로(optimal tour): 여러 개의 일주여행경로 중 최소 길이 경로

## 무작정 알고리즘
* 가능한 모든 일주여행경로를 다 고려한 후, 그 중 가장 짧은 일주여행경로 선택
* 가능한 일주여행경로의 총 개수는 (n-1)!이므로 정점의 개수가 증가할 때 시간복잡도는 O(n!)이다.
* 순회 외판원 문제의 모든 일주 여행 경로 조사 방법 외 효율적인 다른 방법은 아직 없으나, 동적 계획법을 적용하면 중복 작업 감소가 가능하다.
* 동적 계획법 적용
  * V: 모든 정점의 집합, A: V의 부분집합
  * D[vi][A]: A에 속한 각 정점을 정확히 한번씩만 거쳐서 vi에서 v1로 가는 최단경로
  * 점화식
    * i ≠ 1이고 vi가 A에 속하지 않음
    * D[vi][A] = minVi∈A(W[i][j] + D[vj][A-{vj}]) if A ≠ ∅
    * D[vi][∅] = W[i][j]
      * D[v1][V-{v1}]: 정점 v1을 제외한 모든 정점들을 한번씩 거쳐 v1부터 v1로 가는 최단 경로 
      * W[1][j]: 정점 사이 간선의 가중치
      * D[vj][V-{v1, vj}]: 정점 vj에서 시작하여 v1과 vj를 제외한 정점들을 한번씩 방문하고 v1로 가는 최단 경로
      * min2<=j<=n: vj를 선택 후 남은 부분 문제의 최단 경로를 더한 값들 중 최솟값을 구한다.
  * 과정
      ![{7B9D25C8-3843-4982-8D3E-A305324348D1}](https://user-images.githubusercontent.com/55786368/233622683-cb98141e-ced9-405d-8e43-fafa74394e8c.png)
      ![{A333D405-D419-4D94-BFA2-0B863A7EB83D}](https://user-images.githubusercontent.com/55786368/233622740-7ea7dde5-9276-432f-bd2e-b0a0b22be30a.png)
      ![{91D1C20B-8F83-4383-8A25-3A39D1F4DC8F}](https://user-images.githubusercontent.com/55786368/233622799-6b5ffe53-c08b-48c9-a67e-8409818a65fc.png)
    ![{F2040A39-B31E-46BF-A10C-FCBA7854EBE1}](https://user-images.githubusercontent.com/55786368/233622817-6f70548c-dc9d-451b-a289-c6191ca7ffef.png)
  * 알고리즘
    ```
    // W[][]: 인접 행렬, minlength: 최적일주여행경로의 가중치 합, n: 정점의 개수
    // D[][]: 부분 문제의 해를 저장하기 위한 저장소, 2차 배열 아님.
    travel(n, W[][])
        number D[1..n][subset of V - {v1}]

        FOR i in 2->n
            D[i][emptyset] <- W[i][1]

        // k: 거쳐가는 정점 집합의 크기
        FOR k in 1->n-2
            // v1을 뺀 나머지 정점들의 집합에 원소의 수가 k개인 모든 부분 집합에 대해 반복
            FOR all subsets A = {V-{v1}} containing k vertices
                // v1을 뺀 나머지 정점들을 각각 시작점 i로 선택하고 i를 포함하지 않는 부분집합에 대해서만 반복
                FOR i such that i ≠ 1 AND vi ∈ A
                    // 집합 A에 포함된 원소의 수만큼 비교
                    D[i][A] <- minimumvj∈A(W[i][j] + D[vj][A - {vj}])
        
        // 시작 정점 v1로 하고 나머지 정점들을 거쳐가는 정점으로 고려하여 계산
        D[1][V - {v1}] <- minimum2<=j<=n(W[1][j] + D[vj][A - {v1}]
        // 최적 일주여행 경로의 가중치를 minlength에 저장
        minlength = D[1][V - {v1}]
    ```
   * 시간 복잡도
     * n>=1를 만족하는 모든 n(정점의 개수)에 대하여 다음이 성립한다.
      ![{A9337639-E72C-47D7-919D-0A1BE172876F}](https://user-images.githubusercontent.com/55786368/233627653-01f6b818-b240-4a1f-90f9-76af08eac7d0.png)
    * 입력 크기: 정점의 개수 n
    * 단위 연산
      * 중간에 위치한 루프가 수행시간 지배
      * vj의 각 값에 대해 수행되는 명령문(덧셈 명령문 포함)
    * 분석
      ```
      // n-1개의 정점에서 k개를 뽑는 경우의 수
      FOR all subsets A = {V-{v1}} containing k vertices
      // v1을 빼고 (1)에서 선택한 k개의 정점들의 집합 A에 속하지 않는 정점 개수만큼 반복
        FOR i such that i ≠ 1 AND vi ∈ A
        // 집합 A의 크기 K만큼 반복
            D[i][A] <- minimumvj∈A(W[i][j] + D[vj][A - {vj}])
      ```
      * n이 20일 때 각 일주여행경로의 길이 계산에 걸리는 시간 1msec
        * 무작정 알고리즘: (20-1)! = 19!msec = 3857년
        * 동적 계획법 기반 알고리즘: 45초 정도 소요, 2100만개의 배열 슬롯 필요
          * n=40 => 6년 이상의 시간  필요
        * 동적 계획법이 실용적인 해결책인가? 더 빠른 시간에 해를 구하는 다른 방법은 없을까?
          * 최악의 경우 시간 복잡도가 지수 시간보다 좋은 TSP 알고리즘을 찾은 사람은 아무도 없다.
          * 지수 시간보다 좋은 알고리즘을 찾는 것이 불가능하다고 증명한 사람도 없다.
  * 공간 복잡도
    * 배열 D[vi][A]와 P[vi][A]가 얼마나 커야 하는지 결정
    * V - {v1}는 n-1개의 정점을 가짐 => 2^n-1개의 부분집합 A를 가짐
      * n개의 아이템 부분집합의 개수 2^n

### Reference
* [swea - 동적 계획법의 활용](https://swexpertacademy.com/main/learn/course/subjectDetail.do?courseId=AVuPDYSqAAbw5UW6&subjectId=AV5Ld_2KDpADFAXc&&)