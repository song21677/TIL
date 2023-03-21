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