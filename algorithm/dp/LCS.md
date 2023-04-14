# LCS
## Longest Common Substring (최장 공통 부분 문자열)
* 'ABCDHEF'와 'BCDEF'의 최장 공통 부분 문자열 길이: 3
* LCSuff(S1..p, T1..q)
  * = LCSuff(S1..p-1, T1..q-1)+1    if S[p] = T[q]
  * = 0     otherwise.
  ```java
  class Main {
    static int [][] LCS = new int [5][5];
    public static void main(String args[]) {
        char [] A = "ABCD".toCharArray();
        char [] B = "AEBD".toCharArray();

        LCS(A, B);
    }

    private static void LCS(char [] A, char [] B) {
        for (int i=1; i<=A.length; i++) {
            for (int j=1; j<=B.length; j++) {
                if (A[i-1] == B[j-1]) {
                    LCS[i][j] = LCS[i-1][j-1]+1;
                }
            }
        }
    }
  }
  ```
## Longest Common Subsequence (최장 공통 부분 수열)
* 'ABCDHEF'와 'BCDEF'의 최장 공통 부분 수열의 길이: 5
   ```java
    ```java
    class Main {
        static int [][] LCS = new int [5][5];
        public static void main(String args[]) {
            char [] A = "ABCD".toCharArray();
            char [] B = "AEBD".toCharArray();

            LCS2(A, B);
        }

        private static void LCS(char [] A, char [] B) {
            for (int i=1; i<=A.length; i++) {
                for (int j=1; j<=B.length; j++) {
                    if (A[i-1] == B[j-1]) {
                        LCS[i][j] = LCS[i-1][j-1]+1;
                    } else {
                        LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
                    }
                }
            }
        }
    }
   ```

### Reference
* https://mygumi.tistory.com/126