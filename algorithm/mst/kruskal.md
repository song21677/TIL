# 크루스칼 알고리즘
* 간선의 가중치를 기준으로 오름차순으로 정렬한다.
* 에지들을 순서대로 하나씩 선택한다. 단, 이미 선택된 에지들과 사이클을 형성하면 선택하지 않는다.
* n-1개의 에지가 선택되면 종료한다.
* union find 알고리즘을 이용한다.
* 과정
  ![{274BDC70-A255-40FE-9189-17A624AADBE7}](https://user-images.githubusercontent.com/55786368/223122141-7dc4b352-a983-4cb9-921a-088973196f32.png)

  ![{0D253A53-05CF-4674-935F-61C31A2A8909}](https://user-images.githubusercontent.com/55786368/223121133-df2925f4-c0dd-484d-a0a0-ccc487927c07.png)
* sudo code
  ```
  MST-KRUSKAL(G,w)
  A <-∅
  for each vertex v ∈ V[G]
    do MAKE-SET(v)
  sort the edges of E into nondecreasing order by weight w
  for each edge (u,v) ∈ E, taken in nondecreasing order by weight
    do if FIND-SET(u) FIND-SET(v)
        then A <- A U {(u,v)}
            UNION(u,v)
  return A
  ```
* code

    ```java
    import java.util.*;
    import java.io.*;
    /*
    sample input(첫 줄의 첫 숫자는 정점의 개수, 두 번째 숫자는 간선의 개수).
    7 
    11
    1 2 2
    2 7 7
    7 6 9
    6 5 23
    5 4 1
    4 1 10
    1 3 3
    2 3 3
    3 7 4
    3 6 3
    3 5 6
    */

    class A implements Comparable<A> {
        int s;
        int e;
        int v;
        public A(int s, int e, int v) {
            super();
            this.s = s;
            this.e = e;
            this.v = v;
        }
        @Override
        public int compareTo(A arg0) {
            // min Heap을 만들기 위한 우선순위 큐용 Comparable 메서드
            return arg0.v >= this.v ? -1 : 1;
        }
    }

    public class Main {
        static int N; // 정점의 개수
        static int E; // 간선의 개수
        // 간선 값을 Min Heap으로 하는 우선순위 큐
        static PriorityQueue<A> pq;
        static int[] parent;
        static int[] rank;
        static boolean[] visit;
        static int result;

        // 루트 노드를 찾은 이후에는 O(1)
        // 하지만 루트를 찾는데 O(h)
        public static int find(int a) {
            if (a==parent[a]) return a;
            // find할 때마다 부모는 최상위부모로 설정 (성능 향상)
            parent[a] = find(parent[a]);
            // return find(parent[a]); <- 최상위 부모를 저장하지 않고 매번 여러 단계를 올라가 찾으면 시간 초과 발생
            return parent[a];
        }

        public static void union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);
            if (rank[a] < rank[b]) {
                parent[a] = b;
            } else {
                parent[b] = a;

                if (rank[a] == rank[b])
                    rank[a]++;
            }
        }

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.valueOf(br.readLine());
            E = Integer.valueOf(br.readLine());

            parent = new int[N+1];
            rank =new int[N+1];
            visit = new boolean[N+1];
            result = 0;

            pq = new PriorityQueue<A>();
            String[] tempStr;
            for (int i=0; i<E; i++) {
                tempStr = br.readLine().split(" ");
                pq.add(new A(Integer.valueOf(tempStr[0]), Integer.valueOf(tempStr[1]), Integer.valueOf(tempStr[2])));
            } // 모든 간선에 대해 [시작, 끝, 비용]을 가진 클래스로 우선순위 큐에 add

            for (int i=1; i<=N; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            for (int i=0; i<E; i++) {
                A oneNode = pq.poll();
                int start = oneNode.s;
                int end = oneNode.e;
                // 사이클 되는지 확인
                int a = find(start);
                int b = find(end);
                if(a==b) continue;

                union(start,end);
                result += oneNode.v;
            }
            System.out.println(result);
        }
    }

    ```
* 시간 복잡도
  * Initiaize A : O(1)
  * First for loop : |V| MAKE-SETs
  * Sort E : O(|E|log|E|)
  * Second for loop : O(|E|) FIND-SETs and UNIONs ?