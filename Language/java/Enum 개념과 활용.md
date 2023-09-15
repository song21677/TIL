## int 상수의 문제점

```java
class {
  private static final int MONDAY = 1;
  private static final int JANUARY = 1;

  public static void main(String[] args) {
    // 상수를 비교하는 논리적으로 잘못된 행위를 함으로써 day 변수에 다른 상수값이 들어가버림
    if (DAY.MONDAY == MONTH.JANUARY) {
        day = MONTH.JANUARY;
    }

    // day 변수에 있는 상수는 MONTH 상수이기 때문에 조건문에서 걸러져야 되지만,
    // 결국 정수값이기 때문에 에러가 안나고 그대로 수행됨 -> 프로그램 버그 발생 가능성
    switch (day) {
        case DAY.MONDAY:
            System.out.println("월요일 입니다.");
            break;
    }
  }
}

// 출처: https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%B4%EA%B1%B0%ED%98%95Enum-%ED%83%80%EC%9E%85-%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9-%EC%A0%95%EB%A6%AC
```

상수는 결국 **정수값** 이기 때문에 비교하는 로직이 가능하거나, 잘못된 상수임에도 불구하고 컴파일 에러없이 실행한다. 상수는 **변하지 않는 값**이기도 하지만 **고유한 값** 이기도 하기 때문에 정수값이라는 이유로 허용되는 일들은 결국 문제가 발생하게 된다.

뿐만 아니라 접근 제어자로 인해 가독성도 좋지 않다. <br><br>

## enum

enum은 위와 같이 의도와 다르게 동작되는 문제점 그리고 상수 값이 변경되면, 상수를 사용하는 클라이언트도 다시 컴파일해야 하는 문제점 등을 해결하기 위해 등장하였다.

enum은 인터페이스와 같이 독립된 특수한 클래스로 구분한다. 열거 상수별로 인스턴스를 만들어 ``public static final`` 필드 형태로 제공한다.

enum, enum 상수들은 객체이기 때문에 힙 메모리에 저장되며 enum 상수들은 독립된 상수를 구성할 수 있는 것이다. (객체를 비교할 때, 객체의 주소로 값을 비교하기 때문에) <br><br>

### enum 문법

- enum 명은 클래스와 같이 첫 문자를 대문자로하고 나머지는 소문자로 구성한다.
- 관례적으로, 열거 상수는 모두 대문자로 작성한다.
- 열거 상수가 여러 단어로 구성될 경우, 단어 사이를 밑줄 (_)로 연결한다.
- enum도 하나의 데이터 타입으로 변수를 선언하고 사용하면 된다. <br><br>

### enum 메소드 종류

Enum 클래스는 ``java.lang.Enum``  클래스의 상속을 받는다. 따라서 ``java.lang.Enum`` 클래스에 정의되어 있는 메소드를 가져와 사용할 수 있다.

| 메소드 | 설명 | 리턴 타입 |
| --- | --- | --- |
| name() | 열거 객체의 문자열을 리턴 | String |
| ordinal() | 열거 객체의 순번(0부터 시작)을 리턴 | int |
| compareTo() | 열거 객체를 비교해서 순번 차이를 리턴 | int |
| valueOf(String name) | 문자열을 입력받아서 일치하는 열거 객체를 리턴 | enum |
| values() | 모든 열거 객체들을 배열로 리턴 | enum[] |

마찬가지로, ``java.lang.Enum``  클래스는 Object 클래스를 자동 상속해서 enum에서도 Object 클래스의 메소드를 그대로 사용이 가능하다. (단, enum에 변경을 줄 수 있는 4가지 메소드는 오버라이딩을 제한하고 있다.)

- `clone()`
- `finalize()`
- `hashCode()`
- `equals()`
<br><br>

### enum 예제

```java
// 결제 수단(현금, 카드)과 결제 종류(계좌이체, 무통장, 페이코 등)를 나타내는 enum이다.
enum PayGroup {
  CASH("현금", Arrays.asList("ACCOUNT_TRANSFER", "REMITTANCE", "TOSS")),
  CARD("카드", Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY")),
  ETC("기타", Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY")),
  EMPTY("없음", Collections.EMPTY_LIST)

  private String title;
  private List<String> payList;

  private PayGroup(String title, List<String> payList) {
    this.title = title;
    this.payList = payList;
  }
}
// 출처: https://techblog.woowahan.com/2527/
```
<br><br>

### 타입 안전 열거 패턴

enum이 나오기 전, jdk 1.5 밑 버전에서 enum을 다음과 같이 사용했다.

```java
// enum 내부 구현이라 볼 수 있다.
final class PayGroup {
	public static final PayGroup CASH = new PayGroup("현금", Arrays.asList("ACCOUNT_TRANSFER", "REMITTANCE", "TOSS"));
	public static final PayGroup CARD = new PayGroup("카드", Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY"));
	public static final PayGroup ETC = new PAYGROUP("기타", Arrays.asList("POINT", "COUPON"));
	public static final PayGroup EMPTY = new PayGroup("없음", Collections.EMPTY_LIST);

	private String title;
	private List<String> payList;

	PayGroup(String title, List<String> payList) {
		this.title = title;
		this.payList = payList;
	}
}
```

enum의 상수는 위와 같이 자기 자신의 인스턴스이며, enum은 그러한 상수를 가지는 **클래스**이다. 따라서 enum은 다른 클래스와 같이 멤버(필드, 메소드)를 가질 수 있다. 다음 코드는 enum에 메소드를 추가한 코드이다.

```java
enum PayGroup {
  CASH("현금", Arrays.asList("ACCOUNT_TRANSFER", "REMITTANCE", "TOSS")),
  CARD("카드", Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY")),
  ETC("카드", Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY")),
  EMPTY("없음", Collections.EMPTY_LIST)

  private String title;
  private List<String> payList;

  private PayGroup(String title, List<String> payList) {
    this.title = title;
    this.payList = payList;
  }

  // 결제 수단으로 결제 종류 찾기
  public static PayGroup findByPayCode(String code) {
    return Arrays.stream(PayGroup.values())
            .filter(payGroup -> payGroup.hasPayCode(code))
            .findAny()
            .orElse(EMPTY);
  }

  public boolean hasPayCode(String code) {
    return payList.stream()
							.anyMatch(pay -> pay.equals(code));
	}
}

class PayGroupTest {
  @Test
  public void PayGroup에게_직접_결제종류_물어보기_문자열 () throws Exception {
    String payCode = selectPayCode();
    PayGroup payGroup = payGroup.findByPayCode(payCode);
    assertThat(payGroup.name()).isEqualTo("CARD");
  }
}

// 출처: https://techblog.woowahan.com/2527/
```
<br><br>

### enum 특징

- enum은 final 클래스로 외부에서 접근 가능한 생성자가 없다. (private 생성자를 가짐)
- 새로운 객체를 생성하거나 상속을 통한 확장을 할 수 없기에 선언된 enum 상수 이외의 객체는 사용할 수 없다.
- 컴파일 시점에 형 안정성을 보장한다.
- namespace로 분리해 같은 이름의 상수가 공존할 수 있다.
- enum에 추상 메서드를 선언하고 각 상수마다 익명 클래스로 재정의할 수 있도록 만들 수 있다. 이렇게 각 상수마다 다른 역할을 하는 메소드를 가질 수 있다.
- enum 타입 속에 enum 타입을 넣은 중첩 enum을 만들 수 있다.
<br><br>

## enum 활용

### 1. 상태와 행위를 한곳에서 관리

- 똑같은 기능을 하는 메소드를 중복 생성할 수 있다.
- 메소드를 누락할 수 있다.

### 2. 데이터 그룹관리
- 관계를 파악하기 좋다.
- 입력값과 결과값이 예측 가능하다.
    - enum을 활용하지 않으면 검증 없이 파라미터로 모두 전달되며, 검증코드가 필요하게 된다.
    - enum을 이용하면 지정된 값만 받을 수 있게 된다.
- 그룹별 기능을 추가하기 쉽다.
    - enum을 활용하지 않으면 if문이 많이 발생한다.
 <br><br>
 
### Reference
* https://techblog.woowahan.com/2527/
* https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%B4%EA%B1%B0%ED%98%95Enum-%ED%83%80%EC%9E%85-%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9-%EC%A0%95%EB%A6%AC
* https://sedangdang.tistory.com/240
