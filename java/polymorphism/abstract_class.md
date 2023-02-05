## abstract method(추상 메소드)
* 자식 클래스에서 반드시 오버라이딩해야만 사용할 수 있는 메소드
* 선언부만 존재하고 구현부는 존재하지 않는다.
* 문법 : 
    ``
    abstract 반환타입 메소드이름();
    ``
<br><br>

## abstract class(추상 클래스)
* 추상 메소드를 하나 이상 가지고 있는 클래스
* 선언만 되어 있는 추상 메소드를 가지고 있기에 객체를 만들 수 없다.
* 사용하려면 자식 클래스에서 상속 받고, 추상 클래스의 추상 메소드를 오버라이딩해야 사용할 수 있다.
* 추상 메소드는 자식 클래스에서 꼭 오버라이딩해야하기 때문에 반드시 사용되어야 하는 메소드를 추상 메소드로 선언해야 한다. (이것이 추상 클래스가 존재하는 이유)
* 문법
  ```java
  abstract class {
    ...
    abstract 반환타입 메소드이름();
    ...
  }
  ```
* 예제
    ```java
    abstract class Animal {
        abstract void cry();
    }

    class Cat extends Animal {
        void cry() {
            System.out.println("냐옹냐옹!");
        }
    }

    class Dog extends Animal
    {
        void cry() {
            System.out.println("멍멍!");
        }
    }

    public class Polymorphism02 {
        public static void main(String args[]) {
            // Animal a = new Animal(); // 추상 클래스는 인스턴스를 생성할 수 없음.
            Cat c = new Cat();
            Dog d = new Dog();

            c.cry();
            d.cry();
        }
    }
    ```
<br><br>

## Reference
* [TCP SCHOOL](http://www.tcpschool.com/java/java_polymorphism_abstract)
