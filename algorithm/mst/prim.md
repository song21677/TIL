# 프림 알고리즘
* 임의의 노드를 출발노드로 선택하고 출발 노드를 포함하는 트리를 점점 키워 나간다.
  ![{E84D0CF2-9897-46F9-8F06-30C59B4C0605}](https://user-images.githubusercontent.com/55786368/223345189-15384bc9-bdbe-484a-9c0c-a6d0cf8e0f67.png)
  * 매 단계에서 key값이 최소인 노드를 찾고, 에지 (f, π(f))를 선택한다.
    * key(v) : 이미 VA에 속한 노드와 자신을 연결하는 에지들 중 가중치가 최소인 에지 (u,v)의 가중치
    * π(v) : 그 에지 (u,v)의 끝점 u
  * key 값이 바뀌는 노드들만 key 값과 π 값을 갱신한다.

* sudo code
  ```
  MST-Prim(G, w, r)
  for each u ∈ V do
    key[u] <- ∞
    π[u] <- NIL
  end.
  VA <- {r}
  key[r] <- 0
  while |VA|<n do
    find u ∉ VA with the minimum key value;
    VA <- VA U {u}
    for each v ∉ VA adjacent to u do
        if key[v] > w(u,v) then
            key[v] <- w(u,v)
            π[v] <- u
        end.
    end.
  end.
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
    2 3 5
    1 3 20
    1 4 10
    4 5 1
    5 6 23
    3 6 3
    3 5 6
    7 6 9
    7 3 2
    2 7 7
    */

    class Node {
        int s, e, v;
        public Node(int s, int e, int v) {
            super();
            this.s = s;
            this.e = e;
            this.v = v;
        }
    }

    public class MSTByPrim {
        static int N;
        static int E;
        static ArrayList<Node>[] nodeList;
        static boolean visit[];
        static int ans;
        static ArrayList<Node> array = new ArrayList<Node>();

        public static void main(String[] args) throws NumberFormatException, IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ans = 0; // 최종 최소 비용 출력을 위한 변수
            N = Integer.valueOf(br.readLine());
            E = Integer.valueOf(br.readLine());
            visit = new boolean[N+1];

            nodeList = new ArrayList[N+1];

            for (int i=1; i<=N; i++) {
                nodeList[i] = new ArrayList<Node>();
            }

            String[] tempStr;
            int start;
            int end;
            int value;
            for (int i=0; i<E; i++) {
                tempStr = br.readLine().split(" ");
                start = Integer.valueOf(tempStr[0]);
                end = Integer.valueOf(tempStr[1]);
                value = Integer.valueOf(tempStr[2]);
                nodeList[start].add(new Node(start, end, value));
                nodeList[end].add(new Node(end, start, value));
            }

            MST();
            System.out.println();
        }

        public static void MST() {
            // 우선순위 큐를 활용해서 Min Heap을 구현
            Comp cp = new Comp();
            // 비용이 가장 작은 간선을 바로 뽑기 위한 우선순위 큐
            PriorityQueue<Node> pq = new PriorityQueue<>(cp);
            // 정점을 모두 방문하는데 쓸 큐
            Deque<Integer> dq = new ArrayDeque<>();
            dq.add(1);
            ArrayList<Node> tempList;
            Node tempNode;
            while(!dq.isEmpty()) {
                // 큐에서 하나 빼서 주변의 노드를 다 넣음
                int currentNode = dq.poll(); // 최초 currentNode는 1
                visit[currentNode] = true; // 해당 노드 방문 처리해서 한 번 방문해서 간선이 연결된 노드는 다시 처리하지 않음
                tempList = nodeList[currentNode];
                for (int i=0; i<tempList.size(); i++) {
                    if(!visit[tempList.get(i).e]) {
                        pq.add(tempList.get(i));
                    }
                }

                // 가장 작은 간선 빼서 값은 답으로 출력, 노드는 방문 처리
                // 만약 이미 방문한 것 중 작은 값이 나왔을 경우 한번 더 빼서 또 확인
                while(!pq.isEmpty()) {
                    tempNode = pq.poll();
                    if(!visit[tempNode.e]) {
                        // 선택할 간선에 연결된 정점이 이미 방문한 곳이면 아무것도 하지 않고 첫 방문이면 정점을 연결하고 연결된 간선이 최소 신장트리를 이루는 간선이므로  결과값에 더해줌
                        visit[tempNode.e] = true;
                        ans += tempNode.v;
                        dq.add(tempNode.e); // 연결된 노드를 큐에 넣어줌
                        break;
                    }
                    // 이미 선택되어 방문된 노드에서 최소값이 나왔을 경우 아무것도 안함
                }
            }
            System.out.println(ans);
        }
    }

    class Comp implements Comparator<Node> {
        // 비용에 대한 Min Heap을 만들기 위한 우선순위 큐의 Comparator
        @Override
        public int compare(Node arg0, Node arg1) {
            return arg0.v > arg1.v ? 1 : -1; 
        }
    }
    ```