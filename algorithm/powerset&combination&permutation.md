```java
class Main {
    static int [] arr = new int [3];
    static int [] num = {1, 2, 3};
    
    public static void main(String args[]) {
        recursion(0);
        System.out.println();
        System.out.println();
        bit();

        
        // 반복문
        for (int i=0; i<=1; i++) {
            arr[0] = i;
            for (int j=0; j<=1; j++) {
                arr[1] = j;
                for (int k=0; k<=1; k++) {
                    arr[2] = k;
                    for (int l=0; l<3; l++) {
                        if (arr[l] == 1) System.out.print(num[l] + " ");
                    }
                    System.out.println();    
                }
            }
        }
    }

    // 비트
    public static void bit() {
        for (int i=0; i< 1 << 3; i++) {
            for (int j=0; j<3; j++) {
                if ((i & 1 << j) != 0) System.out.print(num[j]);
            }
            System.out.println();
        }
    }
    
    // 재귀
    public static void recursion(int idx) {
        if (idx == 3) {
            for (int i=0; i<idx; i++) {
                if (arr[i] == 1) System.out.print(num[i]);
            }
            System.out.println();
            return;
        }
        /*
        for (int i=0; i<=1; i++) {
            arr[idx] = i;
            recursion(idx+1);
        }
        */

        arr[idx] = false;
        recursion(idx+1);

        arr[idx] = true;
        recursion(idx+1);
    }

    // 조합
    public static void combination() {
        
    }
}
```

```java
/*
조합: n개 중에서 r개 선택
*/
public class Combination {
    public static void main(String[] args) {
        int n = 4;
        int[] arr = {1, 2, 3, 4};
        boolean[] visited = new boolean[n];

        for (int i=1; i<=n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            comb(arr, visited, 0, n, i);
        }

        for (int i=1; i<=n; i++) {
            System.out.println("\n" + n + " 개 중에서 " + i + " 개 뽑기");
            combination(arr, visited, 0, n, i);
        }
    }

    // 백트래킹
    static void combination(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        for (int i=start; i<n; i++) {
            visited[i] = true;
            combination(arr, visited, i+1, n, r-1);
            visited[i] = false;
        }
    }

    // 재귀
    static void comb(int[] arr, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            print(arr, visited, n);
            return;
        }

        if (depth == n) {
            return;
        }

        visited[depth] = true;
        comb(arr, visited, depth+1, n, r-1);

        visited[depth] = false;
        comb(arr, visited, depth+1, n, r);
    }

    // 배열 출력
    static void print(int[] arr, boolean[] visited, int n) {
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}
```