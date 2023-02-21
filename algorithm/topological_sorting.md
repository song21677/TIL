# 위상 정렬
* 모든 노드를 순서대로 방문하기 위한 알고리즘이다.
* 사이클이 없는 방향 그래프(DAG) 에서 사용할 수 있다.
* 방식 (큐가 빌 때까지 반복한다.)
  * 진입 차수가 0인 노드를 큐에 넣는다.
  * 큐가 빌 때까지 다음 과정을 반복한다.
    * 큐에서 원소를 꺼내 해당 노드에서 나가는 간선을 그래프에서 제거한다. 
    * 새롭게 진입차수가 0이 된 노드를 큐에 삽입한다.
* 각 노드가 큐에 들어온 순서가 위상 정렬을 수행한 결과이다.
* 한 단계에서 큐에 새롭게 들어가는 원소가 2개 이상인 경우, 여러 가지 답이 존재할 수 있다.
* 모든 원소를 방문하기 전에 큐가 비게 된다면 사이클이 존재한다고 판단할 수 있다.

<br><br>

## 구현
```java
// 백준 2252번
// 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램
import java.util.*;

public class Main {
    static int N; // 그래프 정점의 수 (학생 수)
    static int M; // 그래프 간선의 수 (키 비교 횟수)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        int[] cntOfLink = new int[N+1]; // 간선의 수에 대한 배열

        // 가중치가 없는 그래프(인접 리스트 이용)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i=0; i<N+1; i++) {
            graph.add(new ArrayList<>());
        }

        // 단방향 연결 설정
        for (int i=0; i<M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            graph.get(v1).add(v2);
            cntOfLink[v2]++;
        }

        // 위상 정렬 (A B: A가 B앞에 선다. A가 선행)
        topologicalSort(graph, cntOfLink);
    }

        /*
        위상 정렬
        */
        static void topologicalSort(ArrayList<ArrayList<Integer>> graph, int [] cntOfLink) {
            Queue<Integer> queue = new LinkedList();

            // 초기: 선행 정점을 가지지 않는 정점을 큐에 삽입
            for (int i=1; i<N+1; i++) {
                // 해당 정점의 간선의 수가 0이면
                if (cntOfLink[i] == 0) {
                    queue.add(i);
                }
            }

            // 정점의 수 만큼 반복
            for (int i=0; i<N; i++) {
                int v = queue.remove(); // 1. 큐에서 정점 추출
                System.out.print(v + " "); // 정점 출력

                // 2. 해당 정점과 연결된 모든 정점에 대해
                for (int nextV : graph.get(v)) {
                    cntOfLink[nextV]--; // 2-1. 간선의 수 감소

                    // 2-2. 선행 정점을 가지지 않는 정점을 큐에 삽입
                    // 해당 정점의 간선의 수가 0이면
                    if (cntOfLink[nextV] == 0) {
                        queue.add(nextV);
                    }
                }
            }
        }
}
```

<br><br>

## Reference
* https://gmlwjd9405.github.io/2018/08/27/algorithm-topological-sort.html