## Stack
* 물건을 쌓아 올리듯 자료를 쌓아 올린 형태의 자료구조로, 프로그램에서 중요성과 활용도가 매우 높다.
* 선형구조를 가진다.
  * 선형구조 : 자료 간의 관계가 1대 1의 관계를 가짐
  * 비선형구조: 자료간의 관계가 1대 N의 관계를 가짐(예: 트리 자료구조)
* Stack에 자료를 삽입(push)하거나 자료를 꺼낼 수 있다(pop).
* 후입선출(LIFO, Last-In-First-Out)

<br><br>

### push 알고리즘
```
push(s, x)
  top <- top+1;
  if (top > Stack_size) then
    overflow;
  else
    s(top) <- x;
end push()
```

### pop 알고리즘
```
pop(s)
  if (top = 0) then underflow
  else
  {
    return s(top)
    top <- top-1
  }
end pop()
```

<br><br>

### 구현 방법
* 1차원 배열 사용
  * 장점 : 구현이 용이하다.
  * 단점 : Stack의 크기를 변경하기 어렵다.
* 동적 연결리스트를 이용
  * 장점 : 메모리를 효율적으로 사용할 수 있다.
  * 단점 : 1차원 배열로 구현하는 것보다 구현이 복잡하다.

<br><br>

### Stack 응용
* 괄호검사
  * 조건
    * 조건1 : 왼쪽 괄호의 개수와 오른쪽 괄호의 개수가 같아야 함
    * 조건2 : 같은 괄호에서 왼쪽 괄호는 오른쪽 괄호보다 먼저 나와야 함
    * 조건3 : 괄호 사이에는 포함 관계만 존재함
  * 문자열에 있는 괄호를 차례대로 조사
    * 왼쪽 괄호를 만나면 Stack에 삽입
    * 오른쪽 괄호를 만나면 Stack에서 top 괄호를 삭제한 후 오른쪽 괄호와 짝이 맞는지 확인
      * Stak이 비어있음 => 조건1 또는 조건2 위배
      * 괄호의 짝이 맞지 않음 => 조건3에 위배
      * 문자열 끝까지 조사한 후에도 Stack에 괄호가 남아있음 => 조건1에 위배
  * sudo 코드
    ```
    check_matching(expr)

    스택 초기화
    while (입력 expr의 끝이 아니면)
      ch <- expr의 다음 글자
      switch(ch)
        case '(': case '[': case '{':
          ch를 스택에 삽입
          break
        case ')': case ']': case'}':
          if (스택이 비어 있으면)
            then 오류 // 조건2 위반
          else 스택에서 open_ch를 꺼낸다
            if (ch와 open_ch가 같은 짝이 아니면)
              then 오류 // 조건3 위반
          break
    if (스택이 비어 있지 않으면)
      then 오류 // 조건1 위반
    ```
* 함수 호출관리
    1. 가장 마지막에 호출된 함수가 가장 먼저 실행을 완료하고 복귀하는 후입선출 구조이므로, 후입선출 구조의 Stack을 이용하여 수행순서 관리
    2. 함수 호출이 발생하면 호출한 함수 수행에 필요한 지역변수, 매개변수 및 수행 후 복귀할 주소 등의 정보를 Stack 프레임에 저장하여 시스템 Stack에 삽입
    3. 함수의 실행이 끝나면 시스템 Stack의 top원소(Stack 프레임)를 삭제(pop)하면서 프레임에 저장되어있던 복귀주소를 확인하고 복귀
    4. 함수 호출과 복귀에 따라 이 과정을 반복하여 전체 프로그램 수행이 종료되면 시스템 Stack은 공백 Stack이 됨
* 재귀호출
  * 자기 자신을 호출하여 순환 수행되는 것
  * 함수에서 실행해야 하는 작업의 특성에 따라 일반적인 호출방식보다 재귀 호출 방식을 사용하여 함수를 만들면 프로그램의 크기를 줄이고 간단하게 작성할 수 있음
  * 디버깅이 어렵고 잘못 작성하게 되면 수행 시간이 많이 소요됨
* DFS
* 계산기
  1. Stack을 이용하여 중위표기법의 수식을 후위표기법으로 변경한다.
      1. 중위표기법 -> 후위표기법
         1. 수식의 각 연산자에 대해서 우선순위에 따라 괄호를 사용하여 다시 표현한다. ((A*B) - (C/D))
         2. 각 연산자를 그에 대응하는 오른쪽 괄호의 뒤로 이동한다. ((A B) * (C D)/)-
         3. 괄호를 제거한다. AB*CD/-
      2. 알고리즘
         1. 입력 받은 중위표기식에서 토큰을 읽는다.
         2. 토큰이 피연산자이면 토큰을 출력한다.
         3. 토큰이 연산자(괄호포함)일 경우
            * Stack top과 비교,   
            * 우선순위가 높으면 => Stack에 push
            * 우선순위가 높지 않으면 => 이전 연산자의 우선순위가 토큰의 우선순위보다 작을 때까지 Stack에서 pop한 후 토큰의 연산자를 push
            * 만약 top에 연산자가 없으면 => push
          4. 토큰이 오른쪽 괄호 ')'일 경우
             * Stack top에 왼쪽 괄호 '('가 올 때까지 Stack에 pop 연산을 수행
             * pop한 연산자를 출력
             * 왼쪽 괄호를 만나면 pop만 하고 출력하지는 않음
          5. 중위표기식에 더 읽을 것이 없다면 중지, 더 읽을 것이 있다면 1부터 반복
          6.  Stack에 남아 있는 연산자를 모두 pop하여 출력
          * Stack 밖의 왼쪽 괄호는 우선 순위가 가장 높으며, Stack 안의 왼쪽 괄호는 우선 순위가 가장 낮다.
          ![{0955A1C1-CFAB-4397-BFD6-096C72233300}](https://user-images.githubusercontent.com/55786368/226905734-9135bf1a-1e20-4694-8496-1585bd5683c8.png)
          ![{D72C002A-7F2D-49EB-AE4B-6E6109387C52}](https://user-images.githubusercontent.com/55786368/226905761-0d75fdbd-9eba-4138-9431-08b8600a6b71.png)

          ![{595BDEF9-838A-431F-90AD-2CC11217A4E9}](https://user-images.githubusercontent.com/55786368/226905785-48208448-4290-4255-84b8-23a4d5ef264f.png)

          ![{DE87AA43-1712-4173-9940-61F680708A88}](https://user-images.githubusercontent.com/55786368/226905819-c86eeb85-ba94-46c4-834c-98de4469a3d1.png)

          ![{0F627718-AA5C-4E23-AEB8-7DCDD40B540A}](https://user-images.githubusercontent.com/55786368/226905863-cafdff17-c52e-48e2-a07e-efce75c1391f.png)

          ![{B90693D6-FCD7-4E22-9C8C-AEF3A22E500A}](https://user-images.githubusercontent.com/55786368/226905897-d60d4431-71be-43f0-a166-6bbaddef93b0.png)

          ![{EE22C997-0E48-4F2A-9F55-F044D60BD146}](https://user-images.githubusercontent.com/55786368/226905979-ce7ca6ce-faf7-4f8e-a7f1-0fecdd0eefe7.png)

          ![{039C7759-8554-45CA-A38F-5EDA2DDC8EA8}](https://user-images.githubusercontent.com/55786368/226906062-cc0917af-0838-4c8c-bf4f-4dee29b31f07.png)

          ![{76A8C4B7-6E7B-4997-8D83-7ABEBF966AF2}](https://user-images.githubusercontent.com/55786368/226906115-cdcfc8af-2036-459a-9630-18a745805983.png)

          ![{80C41F2E-C455-4606-B04E-67461F091395}](https://user-images.githubusercontent.com/55786368/226906152-1b7283c2-f402-44a8-bca2-df7e59836fe7.png)

          ![{E054E3E4-1589-4F69-82BE-A97F0EA626F9}](https://user-images.githubusercontent.com/55786368/226906181-9f70c124-abe1-4a06-a6d2-340c1895c40e.png)


          ![{C9B6624A-87DF-4B54-A3F7-7B51885CB66F}](https://user-images.githubusercontent.com/55786368/226906321-44f36a76-eb10-485d-ad7b-83679161572f.png)
          ![{767FFB99-0A5F-4184-8B80-4CC8CFDFBFB4}](https://user-images.githubusercontent.com/55786368/226907348-bbe9ded0-a11e-4bc3-9c9d-97970c60d98a.png)
          ![{31C7EDCE-CBD4-4542-9212-D4404A4A6643}](https://user-images.githubusercontent.com/55786368/226907623-b43e328a-9acc-4655-9157-27eb9acaa6fd.png)
          ![{68B4B9FB-5C05-49D7-8ABC-AB8BA7C3C95B}](https://user-images.githubusercontent.com/55786368/226907802-2cd3a756-0a12-4454-83c8-823ad4183c4a.png)


2.  후위표기법의 수식을 Stack을 이용하여 계산한다.
    1. 피연산자를 만나면 Stack에 push함
    2. 연산자를 만나면 필요한 만큼의 피연산자를 Stack에서 pop하여 연산하고 연산결과를 다시 Stack에 push함
    3. 수식이 끝나면, 마지막으로 Stack을 pop하여 출력
    4. 후위표기식을 계산할 때는 피연산자를 Stack에 쌓아 계산한다.
  

<br><br>

## Stack 클래스
* List 컬렉션 클래스의 Vector 클래스를 상속받는다. <br>
  (뒤에서 데이터를 추가하고 삭제하는 스택은 배열 기반의 클래스가 적합하다.)
* 스택 메모리 구조의 클래스를 제공한다.
* Vector 클래스의 5개 메소드만 상속받는다.
  |메소드|설명|
  |---|---|
  |E push(E item)|해당 스택의 제일 상단에 전달된 요소를 삽입함.|
  |E pop()|해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환하고, 해당 요소를 스택에서 제거함. <br> 스택이 비어 있다면, EmptyStackException이 발생함.|
  |E peek()|해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환함. <br> 스택이 비어 있다면, EmptyStackException이 발생함.|
  |int search(Object o)|해당 스택에서 전달된 객체가 존재하는 위치의 인덱스를 반환함. 찾지 못하면 -1을 반환한다. <br> 이때 인덱스는 제일 상단에 있는(제일 마지막으로 저장된) 요소의 위치부터 0이 아닌 1로 시작함.|
  |boolean empty()|해당 스택이 비어 있으면 true를, 비어 있지 않으면 false를 반환함.|
* 예제
  ```java
    class Main {
        public static void main() {
            Stack<Integer> st = new Stack<Integer>();

            // push() 메소드를 이용한 요소의 저장
            st.push(4);
            st.push(2);
            st.push(3);
            st.push(1);

            // peek() 메소드를 이용한 요소의 반환
            System.out.println(st.peek());  // 1
            System.out.println(st); // [4, 2, 3, 1]

            // pop() 메소드를 이용한 요소의 반환 및 제거
            System.out.println(st.pop()); // 1
            System.out.println(st); // [4, 2, 3]

            // search() 메소드를 이용한 요소의 위치 검색
            System.out.println(st.search(4)); // 3
            System.out.println(st.search(3)); // 1
        }
    }
  ```
  * 더욱 복잡하고 빠른 스택을 구현하고 싶으면 Deque 인터페이스를 구현한 ArrayDeque 클래스를 사용하면 된다. <br>
    * ``Deque<Integer> st = new ArrayDeque<Integer>();``
    * ArrayDeque 클래스는 Stack 클래스와 달리 search() 메소드는 지원하지 않는다.

<br><br>

## Reference
* [TCP SCHOOL](http://www.tcpschool.com/java/java_collectionFramework_stackQueue)
* [Java 공식문서 - Stack](https://docs.oracle.com/javase/8/docs/api/)
* [SWEA - Stack 1](https://swexpertacademy.com/main/learn/course/subjectDetail.do?courseId=AVuPDN86AAXw5UW6&subjectId=AV184o76I7sCFAZN)
* [스택 괄호 검사](https://comdolidol-i.tistory.com/45)