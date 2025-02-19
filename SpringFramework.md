# Spring Application Context

애플리케이션 컨텍스트를 생성, 관리, 구성하고 다양한 부가기능을 제공하는 IoC 컨테이너 
이를 통해 객체간 의존성을 효율적으로 관리

## 주요 역할
1. BEAN 관리
   - 객체 생성, 의존성 주입, 생명 주기 관리
   - 객체 간 결합도를 낮추고 유연한 구성 지원
2. 리소스 로딩
   - 파일 시스템, 클래스패스, URL 등 다양한 소스에서 리소스 로드
3. 이벤트처리
   - 이벤트 발행 및 구독을 지원해서 애플리케이션 내부에서 커뮤니케이션 가능
4. 환경 관리
   - 환강변수와 프로파일(@Profile) 기반의 설정 지원
5. AOP 지원
   - Aspect-Oriented Programming 기능 제공

> IoC 컨테이너
> 객체 생성 및 관리: 애플리케이션에서 사용되는 객체들을 생성하고 관리
> 의존성 주입: 객체가 필요로 하는 다른 객체들(의존성)을 자동으로 주입
> 생명주기 관리: 객체의 생성부터 소멸까지의 생명주기를 관리
> BeanFactory와 ApplicationContext가 Spring 프레임워크 대표 IoC 컨테이너 구현체

## Application Context의 종류

1. **ClassPathXmlApplicationContext**
   - 클래스패스에서 XML 파일을 로드하여 설정

2. **FileSystemXmlApplicationContext**
   - 파일 시스템 경로의 XML 파일을 로드하여 설정

3. **AnnotationConfigApplicationContext**
   - Java 기반 설정(@Configuration)을 사용

4. **WebApplicationContext**
   - Spring 웹 애플리케이션에서 사용하며, `DispatcherServlet`과 통합


## Dependency Injection (DI) in Spring Framework
- DI는 객체 간의 의존성을 외부에서 주입해주는 설계 패턴
- 이는 객체가 필요한 의존성을 직접 생성하지 않고, 외부에서 주입받아 코드의 결합도를 낮추고 유연성을 높이는 데 도움이 됨

### Q1. 구현체가 1개일 때는 @Autowired를 쓰든 안쓰든 상관없고 2개부터는 Qualifier를 쓰는건가?
- 구현체가 1개일 때:
    - @Autowired를 사용하든 안 하든 Spring이 자동으로 해당 구현체를 주입
    - 생성자가 하나뿐이라면 @Autowired를 생략 가능
      
- 구현체가 2개 이상일 때:
    - @Autowired만으로는 Spring이 어떤 구현체를 주입해야 할지 모르게 됨
    - 이때 @Qualifier를 사용하여 특정 구현체를 지정
 
---

## 스프링 의존성 주입(DI)의 유형과 핵심 개념에 대한 종합 분석
의존성 주입(Dependency Injection, DI)은 객체 지향 프로그래밍에서 객체 간의 결합도를 낮추고 유연한 코드 설계를 가능하게 하는 핵심 기법

### DI의 기본 개념과 구현 원리
**의존성 주입의 정의**
```java
// 전통적인 의존성 생성 방식
public class PaymentService {
    private PaymentProcessor processor = new CreditCardProcessor();
}

// DI 적용 방식
public class PaymentService {
    private PaymentProcessor processor;
    
    @Autowired
    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }
}
```
- 스프링 DI의 작동 메커니즘
  1. 빈 정의 : @Component Annotation or XML 설정을 통한 빈 등록
  2. 의존성 분석 : @Autowired 또는 생성자 주입을 통한 의존 관계 설정
  3. 주입 실행 : 컨테이너 초기화 시점에 의존성 객체 주입
  4. 빈 사용 : 애플리케이션 전역에서 주입된 빈 활용


1. 생성자 주입
```java
@Service
public class OrderService {
    private final PaymentGateway gateway;
    private final InventoryManager inventory;

    public OrderService(PaymentGateway gateway, InventoryManager inventory) {
        this.gateway = gateway;
        this.inventory = inventory;
    }
}
```
- final 키워드 사용으로 불변성(Immutable) 보장
- 컴파일 타임에 Null Pointer Exception 방지
- 순환 의존성 문제 조기 발견 가능

2. 세터 주입
```java
@Service
public class UserService {
    private AuditLogger logger;

    @Autowired
    public void setLogger(AuditLogger logger) {
        this.logger = logger;
    }
}
```
- Optional 의존성 처리에 유용
- public 메서드 노출로 캡슐화 약화 가능성
- Spring 3.x까지 권장되었으나 현재는 제한적 사용

3. 필드 주입
- 직접 필드에 @Autowired 적용하는 방식으로 코드 간결성 대신 여러 제약 사항을 가짐
```java
@Service
public class ReportService {
    @Autowired
    private DataFormatter formatter;
}
```
- 테스트 코드 작성 어려움
- DI 프레임워크 강제 종속성 발생
- SRP(단일 책임 원칙) 위반 가능성

| 기준                | 생성자 주입          | 세터 주입            | 필드 주입          |
|---------------------|---------------------|---------------------|-------------------|
| 불변성 보장          | ⭕ (final 사용)      | ❌                  | ❌                |
| 순환 의존성 검출     | 컴파일 단계         | 런타임 오류         | 런타임 오류       |
| 테스트 용이성        | ⭕                  | △                   | ❌                |
| 코드 간결성          | △                   | ⭕                  | ⭕                |
| Spring 버전 호환성   | 4.3+ 권장           | 3.x+               | 모든 버전         |



## Qualifier vs Autowired 차이점
- Autowired
   - 스프링이 알아서 이거 붙어있는거 스캔하고 컨텍스트에 등록되어 있는 빈을 가져오는 것 (Configuration에서 빈을 등록)
   - 구현체가 하나일 때 쓰는게 아니다
   - 관련이 없음 무조건 쓰는게 아니고 쓰는 상황이 존재
   - 보통 이건 필드에서 쓴다
- Qualifier
   - 구현체가 여러개일 때 사용할 수 있다.
   - 퀄리파이어 매개변수에 밸류값을 명시적으로 작성해줘야 스프링에서 처리한다

     ..??

  > GPT 답변
  @Autowired
@Autowired는 스프링의 핵심 어노테이션 중 하나로, 다음과 같은 특징을 가집니다:

자동 의존성 주입: 스프링 컨테이너가 자동으로 해당 타입의 빈을 찾아 주입합니다.

다양한 위치에서 사용 가능: 생성자, 세터 메소드, 필드 등 다양한 위치에서 사용할 수 있습니다.

타입 기반 주입: 기본적으로 타입을 기준으로 빈을 주입합니다.

@Qualifier
@Qualifier는 @Autowired와 함께 사용되며, 다음과 같은 목적으로 사용됩니다:

특정 빈 지정: 동일한 타입의 빈이 여러 개 있을 때 특정 빈을 지정하는 데 사용됩니다.

모호성 해결: 여러 구현체가 있을 때 어떤 빈을 주입할지 명확히 지정할 수 있습니다.

주요 차이점
사용 목적:

@Autowired: 자동 의존성 주입을 위해 사용

@Qualifier: 동일 타입의 여러 빈 중 특정 빈을 선택하기 위해 사용

동작 방식:

@Autowired: 타입을 기반으로 빈을 자동 주입

@Qualifier: 이름 또는 지정된 값을 기반으로 특정 빈을 선택

사용 상황:

@Autowired: 대부분의 의존성 주입 상황에서 사용

@Qualifier: 동일 타입의 빈이 여러 개 존재하는 경우에 사용

구현체 수와의 관계:

@Autowired: 구현체의 수와 관계없이 사용 가능

@Qualifier: 주로 구현체가 여러 개일 때 사용

### 그럼 Qualifier만 쓰면 되지 않나?
- 1. @Qualifier는 단독으로 사용할 수 없다
@Qualifier는 의존성 주입을 위한 보조 어노테이션입니다. 즉, 스프링이 어떤 빈을 주입해야 할지 명확히 지정하는 역할을 하지만, 의존성 주입 자체를 수행하지는 않습니다. @Autowired가 없으면 스프링은 의존성 주입을 수행하지 않으므로, @Qualifier는 반드시 @Autowired와 함께 사용되어야 합니다.
- 2. 기본 동작과 확장성
@Autowired의 기본 동작:
단일 빈이 존재하면 자동으로 주입됩니다. 구현체가 하나뿐인 경우에는 굳이 @Qualifier를 사용할 필요가 없습니다.
확장성:
만약 나중에 동일한 타입의 빈이 추가되더라도, 기존 코드에서 @Autowired와 함께 적절히 @Qualifier를 사용하면 쉽게 관리할 수 있습니다. 반대로, 처음부터 모든 의존성에 대해 무조건 @Qualifier를 사용하는 것은 불필요한 코드 복잡성을 초래할 수 있습니다.
- 3. 구현체가 하나일 경우
구현체가 하나만 존재하는 상황에서는 굳이 @Qualifier를 사용할 필요가 없습니다. 아래와 같은 경우에는 단순히 @Autowired만 사용하면 됩니다:

```java
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository; // 단일 구현체
}
```
여기서 스프링은 자동으로 적절한 빈을 찾아 주입합니다.
- 4. 구현체가 여러 개일 경우
구현체가 여러 개일 때는 모호성을 해결하기 위해 반드시 @Qualifier를 사용해야 합니다. 예를 들어:
```java
@Component("mysqlRepository")
public class MySQLRepository implements UserRepository {
    // ...
}

@Component("mongoRepository")
public class MongoRepository implements UserRepository {
    // ...
}

@Service
public class UserService {
    @Autowired
    @Qualifier("mysqlRepository") // 특정 구현체 선택
    private UserRepository userRepository;
}
```
위 코드에서 UserRepository의 구현체가 두 개(MySQLRepository, MongoRepository)이므로, 스프링은 어떤 빈을 주입해야 할지 알 수 없습니다. 이때 @Qualifier로 명확히 지정해주는 것이 필요합니다.

- 5. 결론: 왜 둘 다 필요한가?
자동화와 간결함:
구현체가 하나인 경우에는 @Autowired만으로 충분하며 코드가 간결해집니다.
명시적 지정:
구현체가 여러 개일 때는 @Autowired와 함께 @Qualifier를 사용해 특정 빈을 지정해야 합니다.
