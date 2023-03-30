# 분할 정복
* 분할(Divide): 해결할 문제를 여러 개의 작은 부분으로 나눔
* 정복(Conquer): 나눈 작은 문제를 각각 해결
* 통합(Combine): (필요하다면) 해결된 해답을 모음
  ![{9F64506F-E3F1-4A6C-B110-7F4A089ADC80}](https://user-images.githubusercontent.com/55786368/228705534-2cd13f4f-4ff1-4988-8e7b-c52570b7df74.png)
* 예시
  * 거듭제곱
    * 반복문
        ```
        O(n)
        Power( Base, Exponent )
        {
            i <- 0
            Result <- 1; /* C^0은 1이므로. */

            for i from to Exponent-1
                Result <- Result * Base;
            
            return Result;
        }
        ```
    * 분할정복
        ```
        /*
        * C^n
        * C^n/2 * C^n/2 (n은 짝수)
        * C^(n-1)/2 * C^(n-1)/2 * C (n은 홀수)
        * C^8 = C^4 * C^4 = (C^4)^2 = ((C^2)^2)^2
        */
        O(log2n)
        Power( Base, Exponent )
        {
            if Exponent = 1 then return Base;
            else if Base = 0 then return 1;

            if Exponent % 2 = 0 then
            {
                NewBase <- Power( Base, Exponent/2 );
                return NewBase * NewBase;
            }
            else
            {
                NewBase <- Power( Base, (Exponent-1)/2 );
                return (NewBase * NewBase) * Base;
            }
        }
        ```
  * 병합 정렬
      
  * 퀵 정렬
  * 이진 탐색(Binary Search)
      