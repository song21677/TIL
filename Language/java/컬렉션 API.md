# 컬렉션 API
다수의 데이터를 쉽게 처리할 수 있는 표준화 된 방법을 제공하는 클래스들이다.

## 1. 특징
* 참조형만 저장 가능하다. 기본형은 Wrapper로 auto boxing해 저장한다.
* 저장 Data Type에 제한을 가지지 않는다. (장단점이 있다.)
* 크기 변경이 가능하다. (추가, 수정, 삭제 가능, 작업에 따라 크기가 늘어나고 줄어든다.)
* 다양한 자료구조를 제공한다. 
  * Set 계열: 순서 x, 중복 x
  * List 계열: 순서 o, 중복 o
  * Map 계열: key/value 쌍으로 관리한다. 순서 x

## 2. 계층구조
* Collection (인터페이스)
  * Set (인터페이스)
    * HashSet
    * ...
  * List (인터페이스)
    * ArrayList
    * ...
<br><br>

* Map (인터페이스)
  * HashMap
  * ...

## 3. Collection 인터페이스
1. ``boolean add(Object o)``: 지정된 객체를 추가한다.
2. ``boolean addAll(Collection c)``: 지정된 Collection의 객체를 추가한다.
3. ``void clear()``: Collection의 모든 객체를 삭제한다.
4. ``boolean isEmpty()``: Collection이 비어있는지 확인한다.
5. ``int size()``: Collection에 저장된 객체의 개수를 반환한다.
6. ``Object[] toArray()``: Collection에 저장된 객체를 배열로 반환한다.

## 4. Map 인터페이스
1. ``Object put(Object key, Object value)``: Key객체를 id로 value 객체를 저장한다.
2. ``void putAll(Map m)``: Map의 모든 key-value를 추가한다.
3. ``void clear()``: Map의 모든 객체를 삭제한다.
4. ``bolean isEmpty()``: Map이 비어있는지 확인한다.
5. ``int size()``: Map에 저장된 객체의 개수를 반환한다.
6. ``Set keySet()``: Map에 저장된 모든 Key객체를 반환한다.

## 5. Set 계열(순서 없고 중복 저장 불가)
1. HashSet: Set에 객체를 저장하는데 Hash를 사용하여 처리 속도가 빠르다.
2. LinkedHashSet: HashSet과 거의 같다. 차이점은 Set에 추가되는 순서를 유지한다.
3. TreeSet: 객체의 Hash값에 의한 오름차순의 정렬을 유지한다.

## 6. List 계열(순서 있고 중복 저장 가능)
1. List: List의 요소에는 순서를 가진다.
2. ArrayList
   * List에서 객체를 얻어내는데 효율적이다.
   * 동기화(Synchronization)를 제공하지 않는다.
3. LinkedList
   * List에서 앞뒤의 데이터를 삽입하거나 삭제하는데 효율적이다.
   * 동기화를 제공하지 않는다.
4. Vector
   * 기본적으로 ArrayList와 동등하지만 Vector에서는 동기화를 제공한다.
   * 그래서 List객체들 중에서, 가장 성능이 좋지 않다.

## 7. Map 계열(key/value 저장 가능)
1. HashMap
   * Map에 키를 저장하는데 hash를 사용하여 성능이 좋다.
   * 저장되는 순서가 유지 되지 않는다.
   * 오직 하나의 null키를 가질 수 있다.
2. LinkedHashMap
   * HashMap과 거의 같다. 차이점은 Map에 추가되는 순서를 유지한다는 점
3. HashTable
   * 동기화(Synchronization)를 제공한다.
   * null키와 null값을 저장할 수 없다.

## 8. 출력
1. toString() 이용
2. for each 이용
3. Iterator 이용