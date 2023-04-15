# LCS
## Longest Common Substring (최장 공통 부분 문자열)
* 'ABCDHEF'와 'BCDEF'의 최장 공통 부분 문자열 길이: 3
* LCSuff(S1..p, T1..q)
  * = LCSuff(S1..p-1, T1..q-1)+1    if S[p] = T[q]
  * = 0     otherwise.
  ```java
  class Main {
    
    public static void main(String args[]) {
        char [] A = "ABCDHEF".toCharArray();
        char [] B = "BCDEF".toCharArray();

        System.out.println(LCS(A, B));
    }

    private static int LCS(char [] A, char [] B) {
        int ans = 0;

        int [][] LCS = new int [A.length+1][B.length+1];
        for (int i=1; i<=A.length; i++) {
            for (int j=1; j<=B.length; j++) {
                if (A[i-1] == B[j-1]) {
                    LCS[i][j] = LCS[i-1][j-1]+1;
                    if (ans < LCS[i][j]) ans = LCS[i][j];
                }
            }
        }

        return ans;
     }
  }
  ```
## Longest Common Subsequence (최장 공통 부분 수열)
* 'ABCDHEF'와 'BCDEF'의 최장 공통 부분 수열의 길이: 5
   ```java
    class Main {
        public static void main(String args[]) {
            char [] A = "ABCDHEF".toCharArray();
            char [] B = "BCDEF".toCharArray();

            LCS2(A, B);
        }

        private static void LCS2(char [] A, char [] B) {
            int [][] LCS = new int [A.length+1][B.length+1];

            for (int i=1; i<=A.length; i++) {
                for (int j=1; j<=B.length; j++) {
                    if (A[i-1] == B[j-1]) {
                        LCS[i][j] = LCS[i-1][j-1]+1;
                    } else {
                        LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);
                    }
                }
            }

            System.out.println(LCS[A.length][B.length]);
        }
    }
   ```

### LCS에 해당하는 부분수열 구하기
```java
    class Main {
        public static void main(String args[]) {
            char [] A = "ABCDHEF".toCharArray();
            char [] B = "BCDEF".toCharArray();

            System.out.println(LCS2(A, B));
        }

        private static String LCS2(char [] A, char [] B) {
            int [][] LCS = new int [A.length+1][B.length+1];
            String [][] solution = new String [A.length+1][B.length+1];

            for (int i=1; i<=A.length; i++) {
                for (int j=1; j<=B.length; j++) {
                    if (A[i-1] == B[j-1]) {
                        LCS[i][j] = LCS[i-1][j-1]+1;
                        solution[i][j] = "diagonal";
                    } else {
                        LCS[i][j] = Math.max(LCS[i][j-1], LCS[i-1][j]);

                        if (LCS[i][j] == LCS[i-1][j]) solution[i][j] = "top";
                        else solution[i][j] = "left";
                    }
                }
            }

            StringBuilder sb = new StringBuilder();

            int a = A.length;
            int b = B.length;
            
            while(solution[a][b] != null) {
                if (solution[a][b] == "diagonal") {
                    sb.append(A[a-1]);
                    
                    a--;
                    b--;
                } else if (solution[a][b] == "top") {
                    a--;
                } else if (solution[a][b] == "left") {
                    b--;
                }
            }

            return sb.reverse().toString();
        }
    }
```

### Reference
* https://mygumi.tistory.com/126