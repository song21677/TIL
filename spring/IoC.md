# IoC Container 역할

`Bean` 을 생성하고 `Bean` 의 생명주기를 관리하며 의존관계를 설정해 의존하고 있는 객체를 주입해주는 역할을 담당한다.

`Bean` 이란 IoC Container가 관리하는 객체를 말한다.

> 스프링이 여타 프레임워크와 차별화돼서 제공해주는 기능은 의존관계 주입이라는 새로운 용어를 사용할 때 분명하게 드러난다. 그래서 초기에는 주로 IoC 컨테이너라고 불리던 스프링이 지금은 의존관계 주입 컨테이너 또는 그 영문약자를 써서 DI 컨테이너라고 많이 불리고 있다.        -토비의 스프링 1장-
> 

# 스프링 컨테이너 생성, 빈 등록, 의존관계 주입

스프링 컨테이너의 종류

`ApplicationContext` 인터페이스의 구현체이다.

1. `AnnotaionConfigApplicationContext`
2. `GenericXmlApplicationContext`
3. …

스프링 컨테이너마다 빈 등록 방법이 다르다.

1. `AnnotaionConfigApplicationContext`
- 클래스를 하나 만들고 해당 클래스를 `@Configuration` annotation을 붙이면 스프링 컨테이너는 해당 클래스를 설정 정보로 사용한다.
- `@Bean` annotation을 붙이면 스프링 컨테이너는 해당 메서드를 모두 호출해 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 등록된 객체를 빈이라 한다.
- Spring 3.0부터 지원한다.

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 클래스 정보(`AppConfg.class`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

```java
@Configuration
class AppConfig{
    // 빈 이름은 메서드 이름을 사용한다.
    // 빈 이름을 직접 부여할 수도 있다.
    // @Bean(name="memberService2")
    // 빈 이름은 항상 다른 이름을 부여해야 한다. 
    // 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(membrRepository());
    }

		@Bean
		public MemberRepository memberRepository() {
				return new MemoryMemberRepository();
		}
}

class MemberServiceImpl implements MemberService {
		private MemberRepository;
		
		public MemberService(MemberRepository memberRepository) {
				this.memberRepository = memberRepository;
		}
}

class MemomryMemberRepository implements MemberRepository {
	public MemoryMemberRepository() {

	}
}
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.

```java
public class MemberAPP {
		public static void main(String[] args) {
				ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
				MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
MemberService memberService = applicationContext.getBean(MemberService.class);
		}
}
```

- `applicationContext.getBeanDefinitionNames()`: 스프링에 등록된 모든 빈 이름을 조회한다.
- `applicationContext.getBean(빈 이름, 타입);` 혹은 `applicationContext.getBean(타입);` 으로 빈 객체(인스턴스)를 조회한다.
- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
    
    `applicationContext.getBean(MemberRepository.class);` ⇒ `MemoryMemberRepository` 와 `JpaMemberRepository`
    
    (`Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다.)
    
- 타입으로 조회 시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 빈 이름을 지정해야 한다.
- 빈 이름이 없으면 `NoSuchBeanDefinitionException: No bean named '~~~~' available` 에러가 발생한다.

1. `GenericXmlApplicationContext` 

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 정보(`AppConfg.xml`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

- id: 빈의 이름

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <!--  빈 등록 -->
<bean id="memberService" class="com.service.MemberServiceImpl">
	<constructor-arg name="memberRepository" ref="memberRepository" />
</bean>

 <bean id="memberRepository" class="com.dao.MemoryMemberRepository"></bean>
  
  
</beans>
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.
# IoC Container 역할

`Bean` 을 생성하고 `Bean` 의 생명주기를 관리하며 의존관계를 설정해 의존하고 있는 객체를 주입해주는 역할을 담당한다.

`Bean` 이란 IoC Container가 관리하는 객체를 말한다.

> 스프링이 여타 프레임워크와 차별화돼서 제공해주는 기능은 의존관계 주입이라는 새로운 용어를 사용할 때 분명하게 드러난다. 그래서 초기에는 주로 IoC 컨테이너라고 불리던 스프링이 지금은 의존관계 주입 컨테이너 또는 그 영문약자를 써서 DI 컨테이너라고 많이 불리고 있다.        -토비의 스프링 1장-
> 

# 스프링 컨테이너 생성, 빈 등록, 의존관계 주입

스프링 컨테이너의 종류

`ApplicationContext` 인터페이스의 구현체이다.

1. `AnnotaionConfigApplicationContext`
2. `GenericXmlApplicationContext`
3. …

스프링 컨테이너마다 빈 등록 방법이 다르다.

1. `AnnotaionConfigApplicationContext`
- 클래스를 하나 만들고 해당 클래스를 `@Configuration` annotation을 붙이면 스프링 컨테이너는 해당 클래스를 설정 정보로 사용한다.
- `@Bean` annotation을 붙이면 스프링 컨테이너는 해당 메서드를 모두 호출해 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 등록된 객체를 빈이라 한다.
- Spring 3.0부터 지원한다.

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 클래스 정보(`AppConfg.class`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

```java
@Configuration
class AppConfig{
    // 빈 이름은 메서드 이름을 사용한다.
    // 빈 이름을 직접 부여할 수도 있다.
    // @Bean(name="memberService2")
    // 빈 이름은 항상 다른 이름을 부여해야 한다. 
    // 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(membrRepository());
    }

		@Bean
		public MemberRepository memberRepository() {
				return new MemoryMemberRepository();
		}
}

class MemberServiceImpl implements MemberService {
		private MemberRepository;
		
		public MemberService(MemberRepository memberRepository) {
				this.memberRepository = memberRepository;
		}
}

class MemomryMemberRepository implements MemberRepository {
	public MemoryMemberRepository() {

	}
}
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.

```java
public class MemberAPP {
		public static void main(String[] args) {
				ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
				MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
MemberService memberService = applicationContext.getBean(MemberService.class);
		}
}
```

- `applicationContext.getBeanDefinitionNames()`: 스프링에 등록된 모든 빈 이름을 조회한다.
- `applicationContext.getBean(빈 이름, 타입);` 혹은 `applicationContext.getBean(타입);` 으로 빈 객체(인스턴스)를 조회한다.
- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
    
    `applicationContext.getBean(MemberRepository.class);` ⇒ `MemoryMemberRepository` 와 `JpaMemberRepository`
    
    (`Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다.)
    
- 타입으로 조회 시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 빈 이름을 지정해야 한다.
- 빈 이름이 없으면 `NoSuchBeanDefinitionException: No bean named '~~~~' available` 에러가 발생한다.

1. `GenericXmlApplicationContext` 

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 정보(`AppConfg.xml`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

- id: 빈의 이름

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <!--  빈 등록 -->
<bean id="memberService" class="com.service.MemberServiceImpl">
	<constructor-arg name="memberRepository" ref="memberRepository" />
</bean>

 <bean id="memberRepository" class="com.dao.MemoryMemberRepository"></bean>
  
  
</beans>
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.
4) # IoC Container 역할

`Bean` 을 생성하고 `Bean` 의 생명주기를 관리하며 의존관계를 설정해 의존하고 있는 객체를 주입해주는 역할을 담당한다.

`Bean` 이란 IoC Container가 관리하는 객체를 말한다.

> 스프링이 여타 프레임워크와 차별화돼서 제공해주는 기능은 의존관계 주입이라는 새로운 용어를 사용할 때 분명하게 드러난다. 그래서 초기에는 주로 IoC 컨테이너라고 불리던 스프링이 지금은 의존관계 주입 컨테이너 또는 그 영문약자를 써서 DI 컨테이너라고 많이 불리고 있다.        -토비의 스프링 1장-
> 

# 스프링 컨테이너 생성, 빈 등록, 의존관계 주입

스프링 컨테이너의 종류

`ApplicationContext` 인터페이스의 구현체이다.

1. `AnnotaionConfigApplicationContext`
2. `GenericXmlApplicationContext`
3. …

스프링 컨테이너마다 빈 등록 방법이 다르다.

1. `AnnotaionConfigApplicationContext`
- 클래스를 하나 만들고 해당 클래스를 `@Configuration` annotation을 붙이면 스프링 컨테이너는 해당 클래스를 설정 정보로 사용한다.
- `@Bean` annotation을 붙이면 스프링 컨테이너는 해당 메서드를 모두 호출해 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 등록된 객체를 빈이라 한다.
- Spring 3.0부터 지원한다.

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 클래스 정보(`AppConfg.class`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

```java
@Configuration
class AppConfig{
    // 빈 이름은 메서드 이름을 사용한다.
    // 빈 이름을 직접 부여할 수도 있다.
    // @Bean(name="memberService2")
    // 빈 이름은 항상 다른 이름을 부여해야 한다. 
    // 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(membrRepository());
    }

		@Bean
		public MemberRepository memberRepository() {
				return new MemoryMemberRepository();
		}
}

class MemberServiceImpl implements MemberService {
		private MemberRepository;
		
		public MemberService(MemberRepository memberRepository) {
				this.memberRepository = memberRepository;
		}
}

class MemomryMemberRepository implements MemberRepository {
	public MemoryMemberRepository() {

	}
}
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.

```java
public class MemberAPP {
		public static void main(String[] args) {
				ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
				MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
MemberService memberService = applicationContext.getBean(MemberService.class);
		}
}
```

- `applicationContext.getBeanDefinitionNames()`: 스프링에 등록된 모든 빈 이름을 조회한다.
- `applicationContext.getBean(빈 이름, 타입);` 혹은 `applicationContext.getBean(타입);` 으로 빈 객체(인스턴스)를 조회한다.
- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
    
    `applicationContext.getBean(MemberRepository.class);` ⇒ `MemoryMemberRepository` 와 `JpaMemberRepository`
    
    (`Object` 타입으로 조회하면, 모든 스프링 빈을 조회한다.)
    
- 타입으로 조회 시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 빈 이름을 지정해야 한다.
- 빈 이름이 없으면 `NoSuchBeanDefinitionException: No bean named '~~~~' available` 에러가 발생한다.

1. `GenericXmlApplicationContext` 

1) 스프링 컨테이너를 생성해 스프링 빈 저장소를 만든다.

```java
ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
```

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |

2) 파라미터로 넘어온 설정 정보(`AppConfg.xml`)를 이용하여 스프링 빈을 등록하고 의존관계를 주입해준다.

- id: 빈의 이름

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

   <!--  빈 등록 -->
<bean id="memberService" class="com.service.MemberServiceImpl">
	<constructor-arg name="memberRepository" ref="memberRepository" />
</bean>

 <bean id="memberRepository" class="com.dao.MemoryMemberRepository"></bean>
  
  
</beans>
```

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
|  memberRepository | MemoryMemberRepository@x02 |

3) 빈을 사용한다.
   ```java
public class MemberAPP {
		public static void main(String[] args) {
				ApplicationContext applicationContext = new GenericXmlApplicationContext("appConfig.xml");
				MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
				MemberService memberService = applicationContext.getBean(MemberService.class);
		}
}
```
