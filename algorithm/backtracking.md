# 백트래킹
* 해를 찾는 도중에 '막히면', (즉, 해가 아니면) 되돌아가서 다시 해를 찾아가는 기법
* 어떤 노드의 유망성을 점검한 후에 유망(Promising)하지 않다고 결정되면 그 노드의 부모로 되돌아가(Backtracking) 다음 자식 노드로 간다.
  * 어떤 노드를 방문하였을 때 그 노드를 포함한 경로가 해답이 될 수 없으면 그 노드는 유망하지 않다고 한다.
  * 반대로 해답의 가능성이 있으면 유망하다고 한다.
* 가지치기(Pruning): 유망하지 않은 노드가 포함되는 경로는 더 이상 고려하지 않음
* 상태 공간 트리(State Space Tree): 초기 상태에서 목적 상태에 이르는 모든 상태들의 전이 가능 관계를 나타낸 트리
* 알고리즘 절차
  1. 상태 공간 Tree의 깊이 우선 탐색을 실시
  2. 각 노드가 유망한지를 점검
  3. 만일 그 노드가 유망하지 않으면, 그 노드의 부모 노드로 돌아가서 계속 검색
* 최적화(Optimization) 문제와 결정 (Decision) 문제를 해결할 수 있다.
  * 결정 문제: 문제의 조건을 만족하는 해가 존재하는지의 여부를 'Yes' 또는 'No'로 답하는 문제
    * 미로 찾기
    * n-Queen 문제
    * Map coloring
    * 부분 집합의 합(Subset Sum) 문제 등
* 예제
  1. 미로 찾기
     * 입구와 출구가 주어진 미로에서 입구부터 출구까지의 경로를 찾는 문제
     * 이동할 수 있는 방향은 4방향으로 제한
        ![{4BF31196-B456-49AB-963C-9159E21510C7}](https://user-images.githubusercontent.com/55786368/226828948-792ba0b1-6729-43de-97b5-12e4ad88fdd6.png)
  2. n-Queen
      * n*n의 정사각형 안에 n개의 queen을 배치하는 문제로, 모든 queen은 자신의 일직선상 및 대각선상에 아무 것도 놓이지 않아야 함
        ```
        void checknode (node v) {
            if (promising(v))
                if (there is a solution at v)
                    write the solution;
                else
                    for (each child u of v)
                        checknode(u);
        }
        ```
        ![{DEE4FC9A-C466-4477-B979-4F965DEF999F}](https://user-images.githubusercontent.com/55786368/226832021-297f4231-e413-48eb-ad2f-9390d3d78e7c.png)
        ```c
        #include <stdio.h>
        #define ABS(x) (x > 0 ? x : -(x))
        // ROW의 인덱스는 행, 값은 열
        int N, ROW[100] = {0};

        // 해를 (행, 열)로 표현
        void printResult() {
            static int cnt = 1;
            printf("%3d Resullt: ", cnt++);
            for (int i=1; i<=N; ++i)
                printf("(%d, %d) ", i, Row[i]);
            printf("\n");
        }

        // 가지치기
        bool promising(int q) {
            // 지금까지 놓은 퀸들과
            for (int i=1; i<q; ++i) {
                // 일직선에 있는지, 대각선에 있는지 검사
                if (Row[q] == Row[i] || ABS(Row[q] - Row[i]) == ABS(q-i))
                    return false;
            }
            return true;
        }

        void queens(int q) {
            // 가지치기
            if (!promising(q)) return;

            if (q == N) {
                printResult();
                return;
            }

            // 완전탐색 (4^4)
            for (int i=1; i<=N; ++i) {
                Row[q+1] = i;
                queens(q+1);
            }
        }
        int main() {
            scanf("%d", &N);
            queens(0);
            return 0;
        }
        ```
    3. Power Set
       * 어떤 집합의 공집합과 자기자신을 포함한 모든 부분집합
       * 구하고자 하는 어떤 집합의 원소 개수가 n일 경우 부분집합의 개수는 2^n이 나옴
       * 백트래킹을 이용하여 Power Set을 구할 수 있다.
       * True또는 False값을 가지는 항목들로 구성된 n개의 배열을 만들고 i번째 항목(True 혹은 False)은 i번째의 원소가 부분집합의 값인지 아닌지를 나타내는 값이다.
            ```c
            #include <stdio.h>
            #define MAXCANDIDATES 100
            #define NMAX 100

            void construct_candidates(int a[], int k, int n, int c[], int* ncandidates)
            {
                c[0] = 1;
                c[1] = 0;
                *ncandidates = 2;
            }

            void backtrack(int a[], int k, int input) 
            {
                int c[MAXCANDIDATES] = {0,};
                int ncandidates=0;

                if ( k == input ) // 답이면 원하는 작업을 한다.
                {
                    printf("(");
                    for ( i=0; i<=k; i++ ) {
                        if ( a[i] == 1 )
                            printf(" %d", i);
                    }
                    printf(")\n");
                }
                else
                {
                    k++;
                    construct_candidates(a, k, input, c, &ncandidates);
                    for ( i=0; i<ncandidates; i++ ) 
                    {
                        a[k] = c[i];
                        backtrack(a, k, input);
                    }
                }
            }

            void main(void)
            {
                int a[nMAX];
                backtrack(a, 0, 3);
            }
            ```
            ![{183F6664-4FAA-46C3-8C9E-00066BD95FD2}](https://user-images.githubusercontent.com/55786368/226836679-b5430dea-27e3-4041-894c-f78b3574afba.png)
    4. 순열
        ```c
        void construct_candidates(int[] a, int k, int n, int c[], int *ncandidates)
        {
            int i;
            bool in_perm[NMAX] = {0,};

            for (i=0; i<k; i++) in_perm[ a[i] ] = 1;

            *ncandidates = 0;
            for ( i=1; i<=n; i++ ) {
                if (in_perm[i] == 0)
                {
                    c[ *ncandidates ] = i;
                    (*ncandidates)++;
                }
            }
        }
        ```
        ![{1AC8E5D4-B303-495F-9BA3-EEB17B2C6B12}](https://user-images.githubusercontent.com/55786368/226840088-578adcd0-367d-479d-88e8-39e78c2ee503.png)

* 백트래킹 vs 깊이 우선탐색
  * 백트래킹
    * 어떤 노드에서 출발하는 경로가 해결책으로 이어질 것 같지 않으면 더 이상 그 경로를 따라가지 않음으로써 시도의 횟수를 줄임
    * 가지치기(Prunning)
    * 불필요한 경로의 조기 차단
    * N! 가지의 경우의 수를 가진 문제에 대해 백트래킹을 하면 일반적으로 경우의 수가 줄어들지만 이 역시 최악의 경우에는 여전히 지수함수 시간(Exponenital Time)을 요하므로 처리 불가능하다.
    * 모든 후보를 검사하지 않음
  * 깊이 우선 탐색(DFS)
    * 모든 경로를 추적
    * N! 가지의 경우의 수를 가진 문제에 대해 깊이 우선 탐색을 가하면 처리 불가능한 문제
    * 모든 후보를 검사함
