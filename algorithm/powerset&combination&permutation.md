```java
class Main {
    static int n = 3;
    static int r = 2;
    static int [] arr;  // 방문 표시
    static int [] result = new int [n];   // 순열 result
    static int [] num = {1, 2, 3};
    
    public static void main(String args[]) {
        System.out.println("powerset (재귀): ");
        arr = new int [n];
        recursion(0);
        
        System.out.println("powerset (비트): ");
        bit();
        
        System.out.println("combination (재귀): ");
        arr = new int [n];
        combination(0, 0);
        
        System.out.println("combination (백트래킹): ");
        arr = new int [n];
        combination_b(0, 0);
        
        System.out.println("permutation: ");
        permutation(0);
        
        System.out.println("powerset (반복문): ");
        // powerset(반복문)
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

    // powerset (비트)
    public static void bit() {
        for (int i=0; i< (1 << n); i++) {
            for (int j=0; j<n; j++) {
                if ((i & (1 << j)) != 0) System.out.print(num[j] + " ");
            }
            System.out.println();
        }
    }
    
    // powerset (재귀)
    public static void recursion(int idx) {
        if (idx == n) {
            for (int i=0; i<idx; i++) {
                if (arr[i] == 1) System.out.print(num[i] + " ");
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

        arr[idx] = 0;
        recursion(idx+1);

        arr[idx] = 1;
        recursion(idx+1);
    }

    // 조합 (재귀)
    public static void combination(int idx, int cnt) {
        if (cnt == r) {
            for (int i=0; i<n; i++) {
                if (arr[i] == 1) System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }

        if (idx == n) return;

        /*
        for (int i=1; i>=0; i--) {
            arr[idx] = i;
            if (i == 1) combination(idx+1, cnt+1);
            else combination(idx+1, cnt);
        }
        */
        arr[idx] = 1;
        combination(idx+1, cnt+1);

        arr[idx] = 0;
        combination(idx+1, cnt);
    }

    // 조합 (백트래킹)
    public static void combination_b(int idx, int cnt) {
        if (cnt == r) {
            for (int i=0; i<n; i++) {
                if (arr[i] == 1) System.out.print(num[i] + " ");
            }
            System.out.println();
            return;
        }

        for (idx=idx; idx<n; idx++) {
            arr[idx] = 1;
            combination_b(idx+1, cnt+1);
            arr[idx] = 0;
        }
    }

    // 순열
    public static void permutation(int cnt) {
        if (cnt == n) {
            for (int i=0; i<n; i++)
                System.out.print(result[i]);
            System.out.println();
            return;
        }
        
        for (int i=0; i<n; i++) {
            if (arr[i] == 1) continue;
            arr[i] = 1;
            result[cnt] = num[i];
            permutation(cnt+1);
            arr[i] = 0;
        }
    }
}
```