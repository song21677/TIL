## Stack 클래스
* List 컬렉션 클래스의 Vector 클래스를 상속받는다.
* 스택 메모리 구조의 클래스를 제공한다.
* Vector 클래스의 5개 메소드만 상속받는다.
  |메소드|설명|
  |---|---|
  |E push(E item)|해당 스택의 제일 상단에 전달된 요소를 삽입함.|
  |E pop()|해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환하고, 해당 요소를 스택에서 제거함.|
  |E peek()|해당 스택의 제일 상단에 있는(제일 마지막으로 저장된) 요소를 반환함.|
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