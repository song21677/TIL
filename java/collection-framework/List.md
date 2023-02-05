## List 컬렉션 클래스

### 특징

- List 인터페이스를 구현한 클래스들이다.
- List 인터페이스에서 정의한 메소드 모두 사용할 수 있다.
- 요소의 저장 순서가 유지된다.
- 같은 요소의 중복 저장을 허용한다.
<br><br>

### 종류

- ArrayList<E>
- LinkedList<E>
- Vector<E>
- Stack<E>
<br><br>

### ArrayList<E> 클래스

- 가장 많이 사용되는 컬렉션 클래스 중 하나
- JDK 1.2부터 제공된 ArrayList 클래스는 내부적으로 배열을 이용하여 요소를 저장한다.
    - 인덱스를 이용해 배열 요소에 빠르게 접근할 수 있다.
    - 크기를 늘리기 위해서는 새로운 배열을 생성하고 기존의 요소들을 옮겨야 하는 복잡한 과정을 거쳐야 한다.
  
- 예제
    
    ```java
    ArrayList<Integer> arrList = new ArrayList<Integer>();
    
    // add() 메소드를 이용한 요소의 저장
    arrList.add(40);
    arrList.add(20);
    arrList.add(30);
    arrList.add(10);
    
    // for문과 get() 메소드를 이용한 요소의 출력
    for (int i=0; i<arrList.size(); i++) {
    	System.out.print(arrList.get(i) + " ");
    }
    
    // remove() 메소드를 이용한 요소의 제거
    arrList.remove(1);
    
    // Enhanced for문과 get() 메소드를 이용한 요소의 출력
    for (int e : arrList) {
    	System.out.print(e + " ");
    }
    
    // Collections.sort() 메소드를 이용한 요소의 정렬
    Collections.sort(arrList);
    
    // Iterator() 메소드와 get() 메소드를 이용한 요소의 출력
    Iterator<integer> iter = arrList.iterator();
    while (iter.hasNext()) {
    	System.out.print(iter.next() + " ");
    }
    
    // set() 메소드를 이용한 요소의 변경
    arrList.set(0, 20);
    
    for (int e : arrList) {
    	System.out.print(e + " ");
    }
    
    // size() 메소드를 이용한 요소의 총 개수
    System.out.println("리스트의 크기 : " + arrList.size();
    
    /*
    실행 결과
    40 20 30 10
    40 30 10
    10 30 40
    20 30 40
    리스트의 크기 : 3
    */
    ```
<br><br>

### LinkedList<E> 클래스

- ArrayList 클래스가 배열을 이용하여 요소를 저장함으로써 발생하는 단점을 극복하기 위해 고안된 클래스
- JDK 1.2부터 제공된 LinkedList 클래스는 내부적으로 연결 리스트(linked list)를 이용하여 요소를 저장한다.
- 단일 연결 리스트(singly linked list)
    - 다음 요소를 가리키는 참조만을 가지는 연결 리스트
    - 요소의 저장과 삭제 작업이 다음 요소를 가리키는 참조만 변경하며 되므로, 아주 빠르게 처리될 수 있다.
    - 현재 요소에서 이전 요소로 접근하기가 매우 어렵다.
- 이중 연결 리스트(doubly linked list)
    - 단일 연결 리스트의 단점 때문에 이중 연결리스트가 좀 더 많이 사용된다.
    
    ```java
    LinkedList<String> lnkList = new LinkedList<String>();
    
    // add() 메소드를 이용한 요소의 저장
    lnkList.add("넷");
    lnkList.add("둘");
    lnkList.add("셋");
    lnkList.add("하나");
    
    // for문과 get() 메소드를 이용한 요소의 출력
    for (int i=0; i<lnkList.size(); i++) {
    	System.out.print(lnkList.get(i) + " ");
    }
    
    // remove() 메소드를 이용한 요소의 제거
    lnkList.remove(1);
    
    // Enhanced for문과 get() 메소드를 이용한 요소의 출력
    for (String e : lnkList) {
    	System.out.print(e + " ");
    }
    
    lnkList.set(2, "둘");
    
    for (String e : lnkList) {
    	System.out.print(e + " ");
    }
    
    // size() 메소드를 이용한 요소의 총 개수
    System.out.println("리스트의 크기 : " + lnkList.size());
    
    /*
    실행 결과
    넷 둘 셋 하나
    넷 셋 하나
    넷 셋 둘
    리스트의 크기 : 3
    */
    ```    
<br><br>

### Vector<E> 클래스

- JDK 1.0부터 사용해 온 ArrayList 클래스와 같은 동작을 수행하는 클래스
- 기존 코드와의 호환성을 위해서만 남아있으므로, Vector 클래스보다는 ArrayList 클래스를 사용하는 것이 좋다.
<br><br>

### List 인터페이스 메소드

- Collection 인터페이스를 상속받으므로, Collection 인터페이스에서 정의한 메소드도 모두 사용할 수 있다.
- List 인터페이스에서 제공하는 주요 메소드
    
    
    | 메소드 | 설명 |
    | --- | --- |
    | boolean add(E e) | 해당 리스트(list)에 전달된 요소를 추가함. (선택적 기능) |
    | void add(int index, E e) | 해당 리스트의 특정 위치에 전달된 요소를 추가함. (선택적 기능) |
    | void clear() | 해당 리스트의 모든 요소를 제거함. (선택적 기능) |
    | boolean contains(Object o) | 해당 리스트가 전달된 객체를 포함하고 있는지를 확인함. |
    | boolean equals(Object o) | 해당 리스트와 전달된 객체가 같은지를 확인함. |
    | E get(int index) | 해당 리스트의 특정 위치에 존재하는 요소를 반환함. |
    | boolean isEmpty() | 해당 리스트가 비어있는지를 확인함. |
    | Iterator<E> iterator() | 해당 리스트의 반복자(iterator)를 반환함. |
    | boolean remove(Object o) | 해당 리스트에서 전달된 객체를 제거함. (선택적 기능) |
    | boolean remove(int index) | 해당 리스트의 특정 위치에 존재하는 요소를 제거함. (선택적 기능) |
    | E set(int index, E e) | 해당 리스트의 특정 위치에 존재하는 요소를 전달받은 객체로 대체함. (선택적 기능) |
    | int size() | 해당 리스트의 요소의 총 개수를 반환함. |
    | Object[] toArray() | 해당 리스트의 모든 요소를 Object 타입의 배열로 반환함. |