# 다익스트라 알고리즘
* 시작 정점에서 거리가 최소인 정점부터 선택해 나가면서 최단 경로를 구하는 방식
* 설명
  * s: 최단 경로의 후보 해가 되는 정점들의 집합, 최초 s = {r}
  * v: V-S (선택되지 않은 정점들의 집합)
  * d[v]: 출발점 s~정점 v까지의 최단 경로 가중치 합 , 최초 d[r] = 0, d[v] = ∞
    ```
    // G: 그래프, r: 시작 정점, S: 선택된 정점 집합
    // D: 출발점에서 각 정점까지 최단 경로 가중치 합을 저장
    // P: 최단 경로 트리 저장, ADJ(u): 정점 u의 인접 정점 집합
    Dijkstra(G, r)
        S <- ∅ 
        FOR ALL v ∈ V
            D[v] <- ∞
            p[V] <-NULL
        D[r] <- 0

        WHILE S ≠ V
            D[u]가 최소인 정점 u ∈ V-S를 선택
            S <- S U {u}
            FOR ALL v ∈ ADJ(u)
                IF v ∈ V-S AND D[v] > D[u] + weight(u, v)
                    D[v] = D[u] + weight(u, v)
                    P[v] = u
    ```
    ![{8EAF5DDC-FDDE-4BC7-8F12-2E8FFBAE42E5}](https://user-images.githubusercontent.com/55786368/232371273-4494a2d0-46d1-442a-92da-502f771897c2.png)
    ![{4C0286C2-F5AB-4EFF-9B2C-C81332E90454}](https://user-images.githubusercontent.com/55786368/232371479-7170c68e-6bf5-442e-a263-5cc26b101a3f.png)
    ![{C3FEB0C5-9426-40DA-90E4-B621CFA73580}](https://user-images.githubusercontent.com/55786368/232371787-a9b708d1-90c9-4749-aeb1-53efc3454aae.png)
    ![{AC58B08C-B699-4664-8FE9-9E009E342CFF}](https://user-images.githubusercontent.com/55786368/232372005-fdab9b1e-29a1-4e07-8164-d2379c852ab4.png)
* 구현
  * 인접행렬을 이용한 구현 (시간 복잡도: O(V^2))
    ```java
    import java.util.ArrayList;
    import java.util.Scanner;
    /*
    sample input
    6 11
    1
    1 2 3
    1 3 5
    2 3 1
    3 2 1
    2 4 6
    3 4 3
    3 5 6
    4 5 2
    4 6 3
    5 6 6
    */

    // 도착 지점과, 도착 지점으로 가는 비용을 의미하는 클래스를 정의한다.
    class Node {
        int idx;
        int cost;

        Node (int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public class Dijkstra {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            // 노드와 간선의 개수
            int V = sc.nextInt();
            int E = sc.nextInt();
            // 출발지점
            int start = sc.nextInt();

            //1. 인접리스트를 이용한 그래프 초기화
            ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

            // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기
            for (int i=0; i<V+1; i++) {
                graph.add(new ArrayList<>());
            }
            // 그래프에 값을 넣는다.
            for (int i=0; i<E; i++) {
                // a로부터 b로 가는 값은 cost이다.
                int a = sc.nextInt();
                int b = sc.nextInt();
                int cost = sc.nextInt();

                grap.get(a).add(new Node(b, cost));
            }

            // 2. 방문 여부를 확인할 boolean 배열, start 노드부터 end 노드까지의 최소 거리를 저장할 배열을 만든다.
            boolean[] visited = new boolean[V+1];
            int[] dist = new int[V+1];

            // 3. 최소 거리 정보를 담을 배열을 초기화 한다.
            for (int i=0; i<V+1; i++) {
                // 출발 지점 외 나머지 지점까지의 최소 비용은 최대로 지정해둔다.
                dist[i] = Integer.MAX_VALUE;
            } 
            // 출발 지점의 비용은 0으로 시작한다.
            dist[start] = 0;

            // 4. 다익스트라 알고리즘을 진행한다.
            // 모든 노드를 방문하면 종료하기 때문에, 노드의 개수 만큼만 반복을 한다.
            for (int i=0; i<V; i++) {
                // 4-1. 현재 거리 비용 중 최소 지점을 선택한다.
                int nodeValue = Integer.MAX_VALUE;
                int nodeIdx=0;
                // 인덱스 0은 생각하지 않기 때문에 0부터 반복을 진행한다.
                for (int j=1; j<V+1; j++) {
                    // 해당 노드를 방문하지 않았고, 현재 모든 거리비용 중 최솟값을 찾는다.
                    if (!visited[j] && dist[j] < nodeValue) {
                        nodeValue = dist[j];
                        nodeIdx = j;
                    }
                }
                // 최종 선택된 노드를 방문처리 한다.
                visited[nodeIdx] = true;

                // 4-2. 해당 지점을 기준으로 인접 노드의 최소 거리 값을 갱신한다.
                for (int j=0; j<graph.get(nodeIdx).size(); j++) {
                    Node adjNode = graph.get(nodeIdx).get(j);
                    // 인접 노드가 현재 가지는 최소 비용과
                    // 현재 선택된 노드의 값 + 현재 선택된 노드의 값 + 현재 노드에서 인접 노드로 가는 값을 비교하여 더 작은 값으로 갱신한다.
                    if (dist[adjNode.idx] > dist[nodeIdx] + adjNode.cost) {
                        dist[adjNode.idx] = dist[nodeIdx] + adjNode.cost;
                    }
                }
            }

            // 5. 최종 비용을 출력한다.
            for (int i=1; i<V+1; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println("INF");
                } else {
                    System.out.println(dist[i]);
                }
            }
            sc.close();
        }
    }
    ```
  * 우선순위 큐를 이용한 구현(시간 복잡도: O((V+E)logV))
    * 비용을 나타내는 배열에서 갱신된 노드를 제외하고는 여전히 INF의 값을 갖으므로, 갱신된 값에 대해서만 최소 비용을 갖는 다음 노드를 선택해주면 된다.
    * 따라서 우선 순위 큐에 삽입하는 최대 횟수는 간선의 개수이며, 최대 O(ElogE)의 시간이 걸리고, 희소 그래프의 경우 E <= V^2이므로, 최대 O(ElogV)의 시간이 걸린다.
    * 또한, 각 노드들을 우선순위 큐에 추출해주는 연산에 대해서는 최대 V개를 추출하므로 O(VlogV)의 시간이 걸린다.
    * 최대 모든 노드, 간선에 대하여 우선순위큐를 계산해줘야 하므로 O((V+E)logV)의 시간이 걸린다.
        ```java
        /*
        sample input
        6 11
        1
        1 2 3
        1 3 5
        2 3 1
        3 2 1
        2 4 6
        3 4 3
        3 5 6
        4 5 2
        4 6 3
        5 6 6
        */

        class Node {
            int idx;
            int cost;

            Node (int idx, int cost) {
                this.idx = idx;
                this.cost = cost;
            }
        }
        public class Dijkstra2 {

            Scanner sc = new Scanner(System.in);
                // 노드와 간선의 개수
                int V = sc.nextInt();
                int E = sc.nextInt();
                // 출발지점
                int start = sc.nextInt();

                // 인접리스트를 이용한 그래프 초기화
                ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

                // 노드의 번호가 1부터 시작하므로, 0번 인덱스 부분을 임의로 만들어 놓기
                for (int i=0; i<V+1; i++) {
                    graph.add(new ArrayList<>());
                }
                // 그래프에 값을 넣는다.
                for (int i=0; i<E; i++) {
                    // a로부터 b로 가는 값은 cost이다.
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    int cost = sc.nextInt();

                    grap.get(a).add(new Node(b, cost));
                }

                // start 노드부터 end 노드까지의 최소 거리를 저장할 배열을 만든다.
                int[] dist = new int[V+1];

                // 최소 거리 정보를 담을 배열을 초기화 한다.
                for (int i=0; i<V+1; i++) {
                    dist[i] = Integer.MAX_VALUE;
                } 

                // 주의점 1. 다익스트라 알고리즘의 최소비용을 기준으로 추출해야 한다. 최대 비용을 기준으로 하는 경우 최악의 경우 지수시간 만큼의 연산을 해야한다!
                PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
                // 시작 노드에서, 시작 노드로 가는 값이 초기에 가장 짧은 비용을 갖는 노드이다.
                // 즉, 도착 정점은 start, 비용은 0인 노드를 가장 먼저 선택할 것이다.
                q.offer(new Node(start, 0));
                
                dist[start] = 0;
                while (!q.isEmpty()) {
                    Node curNode = q.poll();
                    // 목표 정점이 꺼내졌다면, 해당 노드까지는 최솟값 갱신이 완료되었다는 것이 확정이다(다익스트라 알고리즘).
                    // 따라서, 반복문을 종료해도 되지만, 해당 코드는 시작 정점에 대하여 모든 정점으로의 최단 경로를 구하는 것을 가정한다.
                    // 아래 주석된 코드는 목표 정점이 구해졌다면 빠르게 탈출할 수 있는 조건이다.
                    // if (curNode.idx == end) {
                    //    System.out println(dist[curNode.idx]);
                    //     return;
                    //  }

                    // 꺼낸 노드 = 현재 최소 비용을 갖는 노드.
                    // 즉, 해당 노드의 비용이 현재 dist배열에 기록된 내용보다 크다면 고려할 필요가 없으므로 스킵한다.
                    // 그래프 입력이 만일 완전 그래프의 형태로 주어진다면, 이 조건을 생략한 것 만으로 시간 복잡도가 E^2에 수렴할 가능성이 생긴다.
                    if (dist[curNode.idx] < curNode.cost) continue;

                    // 선택된 노드의 모든 주변 노드들까지 최단 거리를 갱신한다.
                    for (int i=0; i<graph.get(curNode).size(); i++) {
                        Node nxtNode = graph.get(curNode.idx).get(i);
                        // 만일, 주변 노드까지의 현재 dist값(비용)과 현재 선택된 노드로부터 주변 노드로 가는 비용을 비교하고, 더 작은 값을 선택한다.
                        if (dist[nxtNode.idx] > curNode.cost + nxtNode.cost) {
                            // 갱신
                            dist[nxtNode.idx] = curNode.cost + nxtNode.cost;
                            // 갱신된 경우에만 큐에 넣는다.
                            q.offer(new Node(nxtNode.idx, dist[nxtNode.idx]));
                        }
                    }
                }

                // 결과 출력
                System.out.println(Arrays.toString(dist));
            }
        }
        ```

    ### Reference
    * https://sskl660.tistory.com/59