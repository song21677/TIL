## Queue<E> 인터페이스
* 큐 메모리 구조는 인터페이스 형태로 제공된다.
* Queue<E> 인터페이스를 상속받는 하위 인터페이스
  * Deque<E>
  * BlockingDeque<E>
  * BlockingQueue<E>
  * TransferQueue<E>
* Queue 인터페이스를 직간접적으로 구현한 클래스는 많지만, 그중에서도 **Deque 인터페이스를 구현한 LinkedList 클래스** 가 큐 메모리 구조를 구현하는 데 가장 많이 사용된다. <br>
  (앞에서 데이터를 추가하고 삭제하는 큐는 데이터의 추가 삭제가 쉬운 LinkedList가 적합하다.)
* Queue 인터페이스의 메소드
  * Collection 인터페이스의 메소드를 상속받는다.
  * 종류
    |메소드|설명|
    |---|---|
    |boolean add(E e)|해당 큐의 맨 뒤에 전달된 요소를 삽입함. <br> 만약 삽입에 성공하면 true를 반환하고, 큐에 여유 공간이 없어 삽입에 실패하면 IllegalStateException을 발생시킴.|
    |E poll()|해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환하고, 해당 요소를 큐에서 제거함. <br> 만약 큐가 비어있으면 null을 반환함.|
    |E peek()|해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환함. <br> 만약 큐가 비어있으면 null을 반환함.|
    |boolean offer(E e)|해당 큐의 맨 뒤에 전달된 요소를 삽입함. <br> 만약 삽입에 성공하면 true, 실패하면 false를 반환|
    |boolean remove(Object o)|인자로 전달된 객체와 같은 최초로 검출된 요소를 삭제함.|
    |E element()|해당 큐의 맨 앞에 있는(제일 먼저 저장된) 요소를 반환함. <br> peek과 달리 큐가 비어있으면 NoSuchElementException 발생함.|
* 예제
  ```java
    class Main {
        pubic static void main(String args) {
            LinkedList<String> qu = new LinkedList<String>();

            // add() 메소드를 이용한 요소의 저장
            System.out.println(qu.add("넷"));   // true
            qu.add("둘");
            qu.add("셋");
            qu.add("하나");

            // peek() 메소드를 이용한 요소의 반환
            System.out.println(qu.peek());  // 넷
            System.out.println(qu); // [넷, 둘, 셋, 하나]

            // poll() 메소드를 이용한 요소의 반환 및 제거
            System.out.println(qu.poll());  // 넷
            System.out.println(qu); // [둘, 셋, 하나]

            // remove() 메소드를 이용한 요소의 제거
            System.out.println(qu.remove("하나"));  // true
            System.out.println(qu); // [둘, 셋]
        }
    }
  ```
  * 더욱 복잡하고 빠른 큐를 구현하고 싶다면 Deque 인터페이스를 구현한 ArrayDeque 클래스를 사용하면 된다.
    * ``Deque<Integer> = new ArrayDeque<Integer>();
<br><br>
## Reference
* [TCP SCHOOL](http://www.tcpschool.com/java/java_collectionFramework_stackQueue)
* [Java 공식문서](https://docs.oracle.com/javase/8/docs/api/)