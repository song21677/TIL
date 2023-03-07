# 최소 스패닝 트리(Minimum Spanning Tree)

* 사이클이 없는 무방향 가중치 연결 그래프
* 가중치의 합이 최소가 되어야 한다.
  * 사이클이 있을 경우, 사이클을 만드는 간선을 하나 제거하더라도 모든 정점이 연결되어 있다. (그러므로 사이클을 만들어 가중치를 더해줄 필요는 없음.)
* 해가 유일하지는 않다.
  * 문제 : 최소의 비용으로 모든 도시들이 서로 연결되게 한다.
  * (b, c)가 아닌 (a, h)를 연결해도 된다.
![{908E22D2-4151-4F95-86F5-9D4E9BE5CF1A}](https://user-images.githubusercontent.com/55786368/223039183-56890a97-5884-4384-8d01-279fe01bc6fa.png)
* 종류
  * 크루스칼(kruskal) 알고리즘
  * 프림(prim) 알고리즘
<br><br>

## Generic MST 알고리즘
* 어떤 MST의 부분집합 A에 대해서 A U {(u,v)}도 역시 어떤 MST의 부분집합이면 에지 (u,v)는 A에 대해서 안전하다(safe)고 한다.
1. 처음에는 A는 공집합이다.
2. 집합 A에 대해서 안전한 에지를 하나 찾은 후 이것을 A에 더한다.
3. 에지의 개수가 n-1개가 될 때까지 2번을 반복한다.
```
// sudo code
GENERIC-MST(G, w)
A <- ∅
while A does not form a spanning tree
    do find an edge (u,v) that is safe for A
        A <- A U {(u,v)}
return A
```
<br><br>

## cut, cross, respect
* 컷(cut) : 그래프의 정점들을 두 개의 집합 S, V-S로 분할한 것
* 에지 (u,v)에 대해서 u ∈ S이고 v ∈ V-S일 때 에지 (u,v)는 컷 (S,V-2)를 cross한다고 말한다.
* 에지들의 부분집합 A에 속한 어떤 에지도 컷 (S,V-S)를 cross하지 않을 때 컷 (S,V-S)는 A를 존중한다(respect)고 말한다.
![{28E2A0D3-0F61-47F9-9F43-3B25F5A474EC}](https://user-images.githubusercontent.com/55786368/223049349-9bd4a45d-2ec0-4092-bd75-114eee7e6d9a.png)
* A가 어떤 MST의 부분집합이고, (S,V-S)는 A를 존중하는 컷이라고 하자. 이 컷을 cross하는 에지들 중 가장 가중치가 작은 에지(u,v)는 A에 대해서 안전하다.
![{8D292A94-8C0C-4C88-8F3B-A45378243375}](https://user-images.githubusercontent.com/55786368/223049839-d5c41689-f3df-40c2-8191-9c854cf89b2e.png)

