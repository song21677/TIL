# 깊이우선탐색(Depth First Search, DFS)
* 비선형구조의 모든 자료를 검색하는 방법 중 하나이다.
* 한 방향으로 갈 수 있는 경로가 있는 곳까지 깊이 탐색해 가다가 더 이상 갈 곳이 없게 되면, 가장 마지막에 만났던 갈림길 간선이 있는 정점으로 되돌아온다. 그리고, 다른 방향의 정점으로 탐색을 계속 반복하여 결국 모든 정점을 방문하는 순회방법이다.
* 가장 마지막에 만났던 갈림길의 정점으로 되돌아가서 다시 깊이우선탐색을 반복해야 하므로 후입선출 구조의 Stack을 사용한다. 
  * 시작 정점 v를 결정하여 방문
    * 정점 v에 인접한 정점 중에서 방문하지 않은 정점 w가 있으면, 정점 v를 Stack에 push하고 정점 w를 방문 -> w를 v로 하여 다시 반복
    * 방문하지 않은 정점이 없으면, 탐색의 방향을 바꾸기 위해서 Stack을 pop하여 받은 가장 마지막 방문 정점을 v로 하여 다시 반복
  * sudo 코드
    ```
    visited[], Stack[] 초기화
    DFS(v)
        v 방문;
        visited[v] <- true;
        do {
            if (v의 인접 정점 중 방문 안 한 w 찾기)
                push(v);
            while (w) {
                w 방문;
                visited[w] <- true;
                push(w);
                v <- w;
                v의 인접 정점 중 방문 안 한 w 찾기
            }
            v <- pop(Stack);
        } while(v)
    end DFS()
    ```
<br><br>

## Reference
* [SWEA - Stack 1](https://swexpertacademy.com/main/learn/course/subjectDetail.do?courseId=AVuPDN86AAXw5UW6&subjectId=AV184o76I7sCFAZN)