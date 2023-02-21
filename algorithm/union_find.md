# Union-Find

## Disjoint Set
* 서로소 집합 자료구조

<br><br>

## Union-Find
* Disjoint Set을 표현할 때 사용되는 알고리즘

<br><br>

### 연산
1. union(x,y) : x가 속한 집합과 y가 속한 집합을 합친다.
2. find(x) : x가 속한 집합의 대표값(루트 노드 값)을 반환한다.

<br><br>

### 구현
```java
public class Main {
    public static int [] parent = new int [1000001];
    public static int [] rank = new int [1000001];

    public static void main(String args[]) {
        for (int i=1; i<=8; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        for (int i=1; i<=8; i++) {
            System.out.println("초기" + i + "의 부모는 " + parent[i]);
        }

        union(1, 2);
        union(2, 3);
        
        System.out.println();
        for (int i=1; i<=8; i++) {
            System.out.println("union 연산 후" + i + "의 부모는 " + parent[i]);
        }
        
        System.out.println();
        System.out.println("1과 3은 연결되어 있나요?" + isSameParent(1, 3));
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        // 두 값의 root가 같으면(이미 같은 트리) 합치지 않는다.
        if (x == y)
            return;
        
        // union-by-rank 최적화
        // 항상 높이가 더 낮은 트리를 높이가 높은 트리 밑에 넣는다. 
        // 즉, 높이가 더 높은 쪽을 root로 삼음
        if (rank[x] < rank[y]) {
            root[x] = y; // x의 root를 y로 변경
        } else {
            root[y] = x; // y의 root를 x로 변경

            if (rank[x] == rank[y])
                rank[x]++; // 만약 높이가 같다면 합친 후 (x의 높이 + 1)
        }
    }

    // 같은 부모 노드를 가지는지 확인
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        return x == y;
    }

    public static int find(int x) {
        if (x == parent[x]) 
            return x;
        else
            // 경로 압축(Path Compression), 시간 복잡도 : O(logN)
            return parent[x] = find(parent[x]);
    }
}

```

<br><br>

## Reference
* https://gmlwjd9405.github.io/2018/08/31/algorithm-union-find.html
* https://brenden.tistory.com/33