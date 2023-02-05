## 다중 상속
* 여러 개의 클래스를 상속받는 것을 의미한다.
* 다양한 동작을 수행할 수 있지만, 메소드 출처의 모호성 등 여러 가지 문제가 발생할 수 있다.
* 따라서, 자바에서는 다중 상속을 지원하지 않는다.
<br><br>
## interface(인터페이스)
* 추상 메소드와 상수만을 가지는 추상 클래스
* 다중상속을 대체한다.
* 문법
    ```java
    접근제어자 interface 인터페이스이름 {
        // public static final, public abstract는 생략할 수 있다.
        public static final 타입 상수이름 = 값;
        ...
        public abstract 타입 함수이름(매개변수 목록);
        ...
    }

    // 만약, 모든 추상 메소드를 구현하지 않는다면, abstract 키워드를 사용하여 추상 클래스로 선언해야 한다.
    class implements 인터페이스이름 {
        ...
    }
    ```
* 예제
    ```java
    interface Animal { public abstract void cry(); }

    class Cat implements Animal {
        public void cry() {
            System.out.println("냐옹냐옹!");
        }
    }

    class Dog implements Animal {
        public void cry() {
            System.out.println("멍멍!");
        }
    }

    public class Polymorphism03 {
        public static void main(String[] args) {
            Cat c = new Cat();
            Dog d = new Dog();
            c.cry();
            d.cry();
        }
    }
    ```
* 상속과 구현을 동시에 할 수 있다. <br>
  ``class 클래스이름 extends 상위클래스이름 implements 인터페이스이름 {...}``

<br><br>

### 인터페이스를 사용한 다중 상속
* 예제
  ```java
    interface Animal { public abstract void cry(); }
    interface Pet { public abstract void play(); }

    class Cat implements Animal, Pet {
        public void cry() {
            System.out.println("냐옹냐옹!");
        }

        public void play() {
            System.out.println("쥐 잡기 놀이하자~!");
        }
    }

    class Dog implements Animal, Pet {
        public void cry() {
            System.out.println("멍멍!");
        }

        public void play() {
            System.out.println("산책가자~!");
        }
    }

    public class Polymorphism04 {
        public static void main(String[] args) {
            Cat c = new Cat();
            Dog d = new Dog();

            c.cry();
            c.play();
            d.cry();
            d.play();
        }
    } 
  ```
* 인터페이스의 메소드는 추상 메소드만 존재하기 때문에 다중상속의 상속받은 메소드 출처의 모호성 문제를 방지할 수 있다.
    ```java
        interface Animal { public abstract void cry(); }
        
        interface Cat extends Animal { public abstract void cry(); }
        interface Dog extends Animal { public abstract void cry(); }
        
        class MyPet implements Cat, Dog {
            public void cry() {
                System.out.println("멍멍! 냐옹냐옹!");
            }
        }

        public class Polymorphism05 {
            public static void main(String[] args) {
                MyPet p = new MyPet();
                p.cry();
            }
        }
    ```
<br><br>

### 인터페이스 장점

<br><br>
## Reference
* [TCP SCHOOL](http://www.tcpschool.com/java/java_polymorphism_interface)