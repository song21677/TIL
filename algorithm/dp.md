# 동적 계획법
## 정의
* 그리디 알고리즘 설계 기법과 같이 최적화 문제를 해결하는 알고리즘 설계기법
* 먼저 입력 크기가 작은 부분 문제들을 모두 해결한 후에 그 해들을 이용하여 보다 큰 크기의 부분 문제들을 해결
* 최종적으로 원래 주어진 입력의 문제 해결

<br><br>

## 예시
### 1) 피보나치 수열
* 0과 1로 시작하고 이전 두 수 합을 다음 항으로 하는 수열 (0, 1, 1, 2, 3, 5, 8, 13, ...)
* 피보나치 수열의 i번째 값을 계산하는 함수 F를 정의하면 다음과 같음
  ```
  F0 = 0, F1 = 1
  Fi = Fi-1 + Fi-2 for i>=2
  ```
* 위의 정의로부터 피보나치 수열의 i번째 항을 반환하는 함수를 재귀 함수로 구현할 수 있음
  ```
  // 중복 호출의 문제점이 있음
  fibo(n)
    if (n < 2) then
        return 1;
    else
        return fibo(n-1) + fibo(n-2);
  end fibo(n)
  ```
* Memoization
  * 컴퓨터 프로그램을 실행할 때 이전에 계산한 값을 메모리에 저장해서 매번 다시 계산하지 않도록 하여 전체적인 실행속도를 빠르게 하는 기술
  * DP(동적계획)의 핵심이 되는 기술
  * 피보나치 수를 구하는 알고리즘에서 fibo(n)의 값을 계산하자마자 저장하면 실행시간을 줄일 수 있다.
    ```
    memo를 위한 배열을 할당하고, 모두 0으로 초기화 한다;
    memo[0]을 0으로 memo[1]는 1로 초기화한다;

    fib1(n)
        if n<2 and memo[n] is zero then
            memo[n] <- fib1(n-1) + fib1(n-2);
        return memo[n];
    end fib1()
    ```
* DP를 이용한 피보나치 수열 구하기 (2가지 방식)
  * recursive 방식 : fib1()
    * 재귀적 구조는 내부에 시스템 호출 Stack을 사용하는 overhead가 발생할 수 있다.
  * iterative 방식 : fib2()
    * Memoization을 재귀적 구조에 사용하는 것보다 반복적 구조로 DP를 구현한 것으로 성능면에서 보다 효율적이다.
    1. 문제를 부분 문제로 분할
         * Fibonacci(n) 함수는 Fibonacci(n-1)과 Fibonacci(n-2)의 합
         * Fibonacci(n-1)은 Fibonacci(n-2)와 Fibonacci(n-3)의 합
         * Fibonacci(2)는 Fibonacci(1)과 Fibonacci(0)의 합
         * Fibonacci(n)은 Fibonacci(n-1), Fibonacci(n-2), ..., Fibonacci(2), Fibonacci(1), Fibonacci(0)의 부분집합으로 나뉨
    2. 부분 문제로 나누는 일을 끝냈으면 가장 작은 부분 문제부터 해를 구함
    3. 그 결과는 테이블에 저장하고, 테이블에 저장된 부분 문제의 해를 이용하여 상위 문제의 해를 구함
        ```
        fib2(n)
            f[0] <- 0;
            f[1] <- 1;

            for (i<-2; i<=n; i<-i+1) do
                f[i] <- f[i-1] + f[i-2];

                return f[n];
        end fib2(n)
        ```
<br><br>

### 2) 이항 계수
* nCk (n은 자연수, k는 정수)
* recursive 방식
    ```java
    int binomial(int n, int k)
    {
        if (n==k || k==0)
            return 1;
        else if (binom[n][k]>-1) // 배열 binom이 -1로 초기화되어 있다고 가정
        else {
            binom[n][k] = binomial(n-1, k) + binomial(n-1, k-1);
            return binom[n][k];
        }
    }
    ```

* iterative 방식
    ```java
    int binomial(int n, int k) 
    {
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=k && j<=i; j++) {
                if (k==0 || n==k)
                    binom[i][j] = 1;
                else
                    binom[i][j] = binom[i-1][j-1] + binom[i-1][j];
            }
        }
        return binom[n][k];
    }
  ```

<br><br>

### 2) 최소합
* n*n 행렬에서 (1,1)에서 (n,n)까지 이르는 최소합 (오른쪽이나 아래쪽 방향으로만 이동할 수 있다.)
* L[i,j] : (1,1)에서 (i,j)까지 이르는 최소합
  * L[i,j]
    * if i=1 and j=1, mij
    * if j=1, L[i-1,j]+mij
    * if i=1, L[i,j-1]+mij
    * otherwise, min(L[i-1,j], L[i,j-1])+mij
* recursive 방식
    ```java
    int mat(int i, int j)
    {
        if (L[i][j] != -1) 
            return L[i][j];
        if (i==1 && j==1)
            L[i][j] = m[i][j];
        else if (i==1)
            L[i][j] = mat(1, j-1) + m[i][j];
        else if (j==1)
            L[i][j] = Math.min(mat(i-1,j), mat(i,j-1)) + m[i][j];
        return L[i][j];
    }
    ```

* iterative 방식
    ```java
    int mat()
    {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i==1 && j==1)
                    L[i][j] = m[1][1];
                else if (i==1)
                    L[i][j] = m[i][j] + L[i][j-1];
                else if (j==1)
                    L[i][j] = m[i][j] + L[i-1][j];
                else
                    L[i][j] = m[i][j] + Math.min
            }
        }
        return L[n][n];
    }
    ```

    ```java
    // initialize L with L[0][j]=L[i][0]=무한 for all i and j
    int mat()
    {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i==1 && j==1)
                    L[i][j] = m[1][1];
                else
                    L[i][j] = m[i][j] + Math.min(L[i-1][j], L[i][j-1]);
            }
        }
        return L[n][n];
    }
    ```

    ```java
    int mat() {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n ; j++) {
                if (i==1 && j==1) {
                    L[i][j] = m[1][1];
                    P[i][j] = '-';
                }
                else {
                    if (L[i-1][j]<L[i][j-1]) {
                        L[i][j] = m[i][j] + L[i-1][j];
                        P[i][j] = 'l';
                    }
                    else {
                        L[i][j] = m[i][j] + L[i][j-1];
                        P[i][j] = 'u';
                    }
                }
            }
        }
        return L[n][n];
    }

    void printPath()
    {
        int i=n, j=n;
        while (P[i][j] != '-') {
            print(i + " " + j);
            if (P[i][j] == 'l')
                j = j-1;
            else
                i = i-1;
        }
        print(i +  " " + j);
    }

    void printPathRecursive()
    {
        if (P[i][j] == '-')
            print(i + " " + j);
        else {
            if (P[i][j] == 'l')
                PrintPathRecursive(i, j-1);
            else
                PrintPathRecursive(i-1, j);
            print(i + " " + j);
        }
    }
    ```
<br><br>

## Reference
* [SWEA - Stack 1](https://swexpertacademy.com/main/learn/course/subjectDetail.do?courseId=AVuPDN86AAXw5UW6&subjectId=AV184o76I7sCFAZN)