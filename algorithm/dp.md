## 예시
### 1) 이항 계수
* nCk (n은 자연수, k는 정수)
* 재귀함수 이용
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

<br>

* 반복문 이용
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
* 재귀함수 이용
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
<br>

* 반복문 이용
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
<br>

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

<br>

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