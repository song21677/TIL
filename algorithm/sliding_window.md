# 슬라이딩 윈도우
* 고정 사이즈의 윈도우가 이동하면서 윈도우 내에 있는 데이터를 이용해 문제를 풀이하는 알고리즘
* 투포인터와 같으나 다른 점은 윈도우 사이즈가 고정되어 있다는 점이다.
  
* 예제
  ```java
  public class Main {
    public static int [] temp;
    public static int N, K;

    public static void main(String [] args) {
      Scanner sc = new Scanner(System.in);
      N = sc.nextInt();
      K = sc.nextInt();
          
      int [] temp = new int [N];
          
      for (int i=0; i<N; i++) {
        temp[i] = sc.nextInt();
      }

      System.out.println(slidingWindow());
    }

    public static int slidingWindow() {
      int max = 0;
      int sum = 0;

      for (int i=0; i<N; i++) {
        sum += temp[i];

        // 최초에 나온 합을 최댓값으로 잡아놓음
        if (i == K-1) {
          max = sum;
        }

        // 처음 길이를 벗어났을 때 부터 한칸씩 밀어주면서 최댓값 비교
        if (i>=K) {
          sum -= temp[i-K];
          max = Math.max(max, sum);
        }
      }
      return max;
    }
  }
  ```