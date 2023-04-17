# 최장 증가 수열(LIS, Longest Increasing Subsequence)
* 어떤 수열이 왼쪽에서 오른쪽으로 나열되어 있으면, 그 배열 순서를 유지하면서 크기가 점진적으로 커지는 가장 긴 부분수열을 추출하는 문제
* 부분 수열에 포함되는 요소들이 순서상 연속적일 필요는 없다.
* 예시) 3, 2, 6, 4, 5, 1의 최장 증가 수열은?
  * 3, 4, 5
  * 2, 4, 5
* 방법
  1. 완전 검색
     * 수열의 모든 부분 집합을 구하여 각 부분 집합이 증가 수열이 되는지 판별
     * 증가 수열이 되는 수열 중 가장 길이가 긴 수열을 찾음
     * 알고리즘
       ```
       // S: 수열
       FOR i in N -> 1 // i: 부분 수열의 길이
       // 수열 S에서 길이가 i인 모든 부분 수열에 대해 반복
        FOR ALL subsequence of S with length of i
            // 해당 부분 수열이 증가 수열인지 확인
            IF there is one increasing subsequence
                BREAK;
       ```
  2. LIS
    * 입력: 수열 a1, a2, ..., an
    * LIS(i): a1, a2, ..., ai에 존재하는 최장 증가 부분 수열의 길이
    * i보다 길이가 작은 수열들에 존재하는 최장 증가 수열의 길이: LIS(1), LIS(2), ..., LIS(i-1)
    * LIS(i)를 LIS(1), LIS(2), ..., LIS(i-1)와의 관계로 표현하기
      * Case1: LIS(i)가 ai를 포함하지 않는다면, LIS(i)=LIS(i-1)
      * Case2: LIS(i)가 ai를 포함한다면
        * 각 요소로 끝나는 최장 증가 수열들 중 가장 긴 수열이 최장 증가 수열이다.
            |수열 길이|최장 증가 수열|최장 증가 수열 길이|
            |---|---|---|
            |1|a1으로 끝나는 최장 증가 수열|3|1|
            |2|a2으로 끝나는 최장 증가 수열|2|1|
            |3|a3으로 끝나는 최장 증가 수열|2, 6 또는 3, 6|2|
            |4|a4으로 끝나는 최장 증가 수열|2, 4 또는 3, 4|2|
            |5|a5으로 끝나는 최장 증가 수열|2, 4, 5 또는 3, 4, 5|3|
            |6|a6으로 끝나는 최장 증가 수열|1|1|
        * 알고리즘
          * ai 바로 전에 위치하는 요소 찾기
          * 증가 수열의 관계인 ai < aj인 aj찾기(j<i)
          * j값을 알 수 없으므로 모두 검색
          * 그 중 최대값을 찾아 1 증가시켜 LIS(i)에 저장
    * DP접근 알고리즘
      * 시간 복잡도: O(n^2)
        ```
        // LIS[i]: ai로 끝나는 최장 증가 수열의 길이 저장
        // i를 수열 길이만큼 증가시키면서 반복
        FOR i in 1 -> n
            // ai를 포함하는 증가 수열에서 ai 자신은 반드시 포함
            LIS[i] = 1
            FOR j in 1 -> i-1
                IF aj < ai AND 1 + LIS[j] > LIS[i]
                    LIS[i] = 1 + LIS[j]
            RETURN max LIS[]
        ```
    * 이진 검색을 이용한 보다 효율적인 방법
      * 수열에서 순서대로 하나씩 읽어온다.
      * C[0] = -무한대
      * C배열을 이진 검색해 자신의 위치에 저장
      * 자신이 위치한 배열의 인덱스를 LIS로 저장
      * 시간 복잡도: O(nlogn)


### Reference
[swea SW 문제해결 응용 - 동적계획법의 활용](https://swexpertacademy.com/main/learn/course/lectureVideoPlayer.do)