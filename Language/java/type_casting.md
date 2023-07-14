# type casting(형변환)
### primitive type(기본형)
* 명시적(downcasting)
  * 형변환 연산자 ``(데이터타입)``로 형변환해준다.
    ```java
    int n = 10;
    short n2 = (short)n;
    ```
* 묵시적(upcasting)
  * 자동으로 형변환해준다.
  * 크기가 큰 데이터 타입으로 형변환해줄 수 있다.(byte -> short -> int -> long -> -> float -> double)
    ```JAVA
    byte b = 10;
    double f = b;
    ```
  * char -> int 일 때 int 타입 변수에 문자의 아스키코드가 저장된다.
    ```java
    char c = 'A';
    int n = c;
    ```
  * int보다 작은 Data형의 연산결과는 int로 반환된다.
    ```java
    short s = 10;
    short s2 = 10;
    int s3 = s + s2;
    ```
  * 큰 type과 작은 type 연산은 큰 type으로 반환한다. 
    ```java
    float f = 3.14f;
    int n = 5;
    float f2 = f + n;
    ```