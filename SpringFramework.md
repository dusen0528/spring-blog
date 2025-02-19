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
