## Generic?
* specific과 대조되는 의미로 data type을 일반화시키는 것을 의미한다.

<br><br>

### 1. Generics(제네릭 클래스)
* 클래스를 설계할 때 data type을 구체적으로 지정하지 않고 type parameter(T, E, V 등)를 넣어 설계한 클래스
*  그 클래스를 바탕으로 객체를 생성할 때 구체적인 data type을 지정해주어 클래스의 재사용성을 높인다.
*  예시
    ```java
    class ExClassGeneric<T> {
        private T t;
        public void setT(T t) {
            this.t = t;
        }
        public T getT() {
            return t;
        }
    }
    ```

<br><br>

#### Object vs Generics
* Object를 이용하면 generic programming을 할 수 있다. 그렇다면 Generics와 어떤 차이가 있는 걸까?
* Object를 이용한 generic programming
  ```java
    public class Box {
        private Object t;
        public void set(Object t) {
            this.t = t;
        }
        public Object get() {
            return t;
        }
    }

    public class Main {
        public static void main(String args[]) {
            Box box = new Box();
            box.set(new Integer(10));
            Integer a = (Integer)box.get();
        }
    }
  ```
    => Box 클래스의 get함수는 Object type을 return하기 때문에 위의 예시에선 Integer로 type casting한 것 처럼 type casting을 매번 해줘야 한다.
    <br>
    => type casting은 컴파일러가 자동으로 해주는 일으로, 프로그래머가 직접 하는 것은 좋지 않은 프로그래밍이다.
    <br>
    => 따라서, 같은 generic programming이더라도 Generics를 이용한 것이 더 좋은 프로그래밍이라 할 수 있다.