# 정렬

## 종류
- 교환 방식: 키를 비교하고 교환하여 정렬하는 방식(선택 정렬, 버블 정렬, 퀵 정렬)
- 삽입 방식: 키를 비교하고 삽입하여 정렬하는 방식(삽입 정렬, 셸 정렬)
- 병합 방식: 키를 비교하고 병합하여 정렬하는 방식(2-way 병합, n-way 병합)
- 분배 방식: 키를 구성하는 값을 여러 개의 부분집합에 분배하여 정렬하는 방식(기수 정렬)
- 선택 방식: 이진 트리를 사용하여 정렬하는 방식(힙 정렬, 트리 정렬)
<br><br>

## 선택 정렬
- 전체 원소들 중에서 기준 위치에 맞는 원소를 선택하여 자리를 교환하는 방식으로 정렬한다.
- 전체 원소 중에서 가장 작은 원소를 찾아서 선택하고 첫 번째 원소와 자리를 교환한다.
- 그 다음 두 번째로 작은 원소를 찾아 선택하여 두 번째 원소와 자리를 교환하고, 그 다음에는 세 번째로 작은 원소를 찾아서 세 번째 원소와 자리를 교환한다.
- 이 과정을 반복하면서 정렬을 완성한다.
  
```java
class Sort {
    public void selectionSort(int a[]) {
        int idx1, idx2, min;

        for (idx1 = 0; idx1<a.length-1; idx1++) {
            min = idx1;
            for (idx2 = idx1+1; idx2<a.length; idx2++) {
                if (a[idx2] < a[min]) min = idx2;
            }
            swap(a, idx1, min);

            System.out.printf("\n선택 정렬 %d단계 : ", idx1+1);
            for (idx2=0; idx2<a.length; idx2++)
                System.out.printf("%3d ", a[idx2]);
        }
    }

    public void swap(int a[], int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }
}

class Main {
    public static void main(String args[]) {
        int a[] = {69, 10, 30, 2, 16, 8, 31, 22};
        Sort S = new Sort();

        System.out.printf("\n정렬할 원소 : ");
        for (int i=0; i<a.length; i++)
            System.out.printf(" %d", a[i]);
        System.out.println();

        S.selectionSort(a);
    }
}
```
<br><br>

## 버블 정렬
- 인접한 두개의 원소를 비교하여 자리를 교환하는 방식
- 첫번째 원소부터 마지막 원소까지 반복하면 가장 큰 원소가 마지막 자리로 정렬하게 된다.

```java
class Sort {
    public void bubbleSort(int a[]) {
        int idx1, idx2, size = a.length;

        for(idx2 = size - 1; idx2>0; idx2--) {
            System.out.printf("\n버블 정렬 %d단계 : ", size - idx2);

            for (idx1=0; idx1<idx2; idx1++) {
                if (a[idx1] > a[idx1+1]) swap(a, idx1);
                System.out.printf("\n\t");
                
                for(int k=0; k<size; k++)
                System.out.printf("%3d ", a[k]);
            }
        }
    }

    public void swap(int a[], int idx) {
        int tmp = a[idx];
        a[idx] = a[idx+1];
        a[idx+1] = tmp;
    }
}

class Main {
    public static void main(String args[]) {
        int a[] = {69, 10, 30, 2, 16, 8, 31, 22};
        Sort S = new Sort();

        System.out.printf("\n정렬할 원소 : ");
        for (int i=0; i<a.length; i++)
            System.out.printf(" %d", a[i]);
        System.out.println();
        
        S.bubbleSort(a);
    }
}
```
<br><br>

## 퀵 정렬
- 퀵 정렬은 다음의 두 가지 기본 작업을 반복 수행하여 완성한다.
  - 분할(divide) : 정렬할 자료들을 기준값(pivot)을 중심으로 2개의 부분집합으로 분할한다.
  - 정복(conquer) : 부분집합의 원소들 중에서 기준값(pivot)보다 작은 원소들은 왼쪽 부분집합으로, 기준값(pivot)보다 큰 원소들은 오른쪽 부분집합으로 정렬한다. 부분집합의 크기가 1 이하로 충분히 작지 않으면 순환 호출을 이용하여 다시 분할한다.

- 실행 성능이 가장 좋은 경우는 pivot에 의해서 원소들이 왼쪽 부분집합과 오른쪽 부분집합으로 정확히 n/2개씩 이등분이 되는 경우가 반복되어 수행 단계 수가 최소가 되는 경우이다.
- 실행 성능이 가장 나쁜 경우는 pivot에 의해 원소들을 분할하였을 때 1개와 n-1개로 한쪽으로 치우쳐 분할되는 경우가 반복되어 수행 단계 수가 최대가 되는 경우이다.
- 일반적으로 퀵 정렬의 평균 시간 복잡도는 O(nlog2n)이다.
- 퀵 정렬은 같은 시간 복잡도를 가지는 다른 정렬에 비해서 자리 교환 횟수를 줄임으로써 실행 시간 성능을 향상시킨 정렬 방법이다.

```java
class Sort {
    int i=0;
    public int partition(int a[], int begin, int end) {
        int pivot, temp, L, R, t;

        L = begin;
        R = end;
        pivot = (begin + end)/2;
        System.out.printf("\n [퀵정렬 %d단계 : pivot=%d]\n", ++i, a[pivot]);

        while(L<R) {
            while ((a[L]<=a[pivot]) && (L<=R)) L++;
            while ((a[R]>a[pivot]) && (L<=R)) R--;
            if(L<=R) {
                temp = a[L];
                a[L] = a[R];
                a[R] = temp;

                if(R == pivot) { // L과 R 원소를 교환하여, 결과적으로 피봇원소의 위치가 변경된 경우
                    for(t=0; t<a.length; t++)
                        System.out.printf("%3d ", a[t]);
                    System.out.println();

                    return L;
                }
            }
        }
        // (L>R)이 된 경우
        temp = a[pivot];
        a[pivot] = a[R];
        a[R] = temp;
        for(t=0; t<a.length; t++)
            System.out.printf("%3d ", a[t]);
        System.out.println();
        return R;
    }

    public void quickSort(int a[], int begin, int end) {
        if (begin < end) {
            int p;
            p = partition(a, begin, end);
            quickSort(a, begin, p-1);
            quickSort(a, p+1, end);
        }
    }
}

class Main {
    public static void main(String args[]) {
        int a[] = {69, 10, 30, 2, 16, 8, 31, 22};
        Sort S = new Sort();
        
        System.out.printf("\n정렬할 원소 : ");
        for (int i=0; i<a.length; i++)
            System.out.printf("%3d", a[i]);
        System.out.println();

        S.quickSort(a, 0, 7);
    }
}
```
