# Init Component Class

- 이 클래스는 스프링 프레임워크의 핵심 개념인 IOC(제어의 역전)과 DI(의존성 주입)를 구현

## Initialize 메소드
- 초기화 작업의 전체 프로세스 담당
1. 어노테이션이 붙은 클래스 스캔
2. 각 클래스를 역할별 분류
3. 객체 생성 후 Application Context에 등록

### TODO 5-1 클래스 스캔
```java
        /**
         * TODO#5-1 @Component 어노테이션을 기준으로 'com.nhnacademy.blog' 패키지 하위에 있는 클래스를 스캔합니다.
         * - @Repository, @Service 어노테이션은 @Component 어노테이션을 포함하고 있기 때문에,
         * - 결과적으로 @Component를 스캔했을 때 @Service, @Repository도 함께 스캔됩니다.
         * - ReflectionUtils.classScanByAnnotated() 메서드를 이용해서 구현합니다.
         */
        List<ClassWrapper> classWrappers =  ReflectionUtils.classScanByAnnotated(
                "com.nhnacademy.blog", Component.class
        );
```
- classScanByAnnotated() 메소드를 이용해 com.nhnacademy.blog 패키지 하위에서 @Component 어노테이션이 붙은 클래스를 스캔
- 결과적으로 @Component @Repository @Service가 붙은 모든 클래스가 스캔됨
- 이 과정은 런타임에 수행되며, IoC 컨테이너가 관리할 클래스를 자동으로 식별


### TODO 5-2 리스트 초기화 
```java
 /**
         * TODO#5-2 componentList, serviceList, repositoryList를 초기화합니다.
         * 일반적으로 비즈니스 로직 개발 시 Service에서 Repository를 주입받아 로직을 구현하므로
         * 1. Component -> 2. Repository -> 3. Service 순서로 초기화합니다.
         */
        //1순위
        List<Class<?>> componentList = new ArrayList<>();

        //2순위
        List<Class<?>> repositoryList = new ArrayList<>();

        //3순위
        List<Class<?>> serviceList = new ArrayList<>();
```
- 각각의 리스트는 어노테이션에 따라 분류된 클래스 저장
- componentList : 일반적인 컴포넌트 클래스
- repositoryList : 데이터 접근 계층 클래스
- serviceList : 비즈니스 로직 계층 클래스

### TODO 5-3 인터페이스 제외
```java
     /**
             * TODO#5-3 classWrapper.getClazz()의 타입이 interface이면 continue;를 호출합니다.
             * - 즉, @Component, @Repository, @Service를 정의한 인터페이스는 제외하려고 합니다.
             */

            if(classWrapper.getClazz().isInterface()){
                continue;
            }
```
- 인터페이스는 객체를 생성할 수 없기에 제외
- 왜냐하면 IoC 컨테이너는 구체적인 클래스를 관리해야 하므로 인터페이스는 스캔 결과에서 제외 됨

### TODO 5-4 클래스 분류
```java
   /**
             * TODO#5-4 classWrapper.getClazz()를 각각의 어노테이션 조건에 맞게 분리합니다.
             * - @Component를 포함하는 클래스 -> componentList에 담습니다.
             * - @Service를 포함하는 클래스 -> serviceList에 담습니다.
             * - @Repository를 포함하는 클래스 -> repositoryList에 담습니다.
             */
            Component component = classWrapper.getClazz().getAnnotation(Component.class);
            Repository repository = classWrapper.getClazz().getAnnotation(Repository.class);
            Service service = classWrapper.getClazz().getAnnotation(Service.class);

            if (repository != null) {
                repositoryList.add(classWrapper.getClazz());
            } else if (service != null) {
                serviceList.add(classWrapper.getClazz());
            } else if (component != null) {
                componentList.add(classWrapper.getClazz());
            }
```
- 각 클래스가 어떤 어노테이션이 있는지 확인 후 해당 리스트에 추가
- 어노테이션에 따라 클래스 분리하여 이후 단계에서 순차적으로 처리할 수 있도록 준비

### TODO 5-5~5-7 빈 생성 및 등록
```java
 /**
         * TODO#5-5 @Component로 선언된 클래스의 인스턴스를 createInstance() 메서드를 이용해서 생성합니다.
         * - @Component Bean 생성 및 등록(1순위)
         * - beanName이 존재하지 않는 경우 classNameToBeanName() 메서드를 호출해서 이름을 설정합니다.
         *   - 예) @Component("BlogDataSource")라고 선언되었다면 beanName = "blogDataSource"입니다.
         *     - 즉, 해당 클래스의 첫 번째 위치하는 알파벳 M을 소문자인 m으로 변경하여 bean의 이름으로 사용합니다.
         *     - 자바에서는 일반적으로 객체의 이름(Bean의 이름)을 첫 글자를 소문자로 변경하여 짓습니다.
         *     - BlogDataSource -> blogDataSource
         *     - BlogDataSource blogDataSource = new BlogDataSource()
         *
         * @Component
         * class BlogDataSource {
         *     ...
         * }
         *
         */

        for (Class<?> aClass : componentList) {
            Component component = aClass.getAnnotation(Component.class);
            String beanName = component.value().isEmpty()?
                    classNameToBeanName(aClass):component.value();
            createInstance(context, aClass, beanName);
        }

        /** TODO#5-6 @Repository로 선언된 클래스의 인스턴스를 createInstance() 메서드를 이용해서 생성합니다.
         * - Bean 생성 및 등록(2순위)
         * - beanName이 존재하지 않는 경우 classNameToBeanName() 메서드를 호출해서 이름을 설정합니다.
         */
        for(Class<?> aClass : repositoryList) {
            Repository repository = aClass.getAnnotation(Repository.class);
            String beanName = repository.value().isEmpty()?
                    classNameToBeanName(aClass):repository.value();
            createInstance(context, aClass, beanName);
        }

        /** TODO#5-7 @Service로 선언된 클래스의 인스턴스를 createInstance() 메서드를 이용해서 생성합니다.
         * - Bean 생성 및 등록(3순위)
         * - beanName이 존재하지 않는 경우 classNameToBeanName() 메서드를 호출해서 이름을 설정합니다.
         */

        for (Class<?> aClass : serviceList) {
            Service service = aClass.getAnnotation(Service.class);
            String beanName = service.value().isEmpty()?
                    classNameToBeanName(aClass):service.value();
            createInstance(context, aClass, beanName);
        }

        //모든 Stereotype Bean이 생성되어 Application Context에 등록되었습니다.
```
- 각 리스트에 대해 반복문을 실행
- 어노테이션 값 확인해서 빈 이름을 결정
  - 어노테이션에 값이 없으면 클래스 이름을 기반으로 빈 이름 생성
- 객체 생성 후 ApplicationContext에 등록
- IoC 컨테이너가 관리할 객체(빈)을 실제로 생성하고 등록하는 단계

## classNameToBeanName 메소드
```java
public static String classNameToBeanName(Class<?> clazz) {
//TODO#5-8 classNameToBeanName()를 구현 합니다.
String className = clazz.getSimpleName();
log.debug("className:{}",className);
return Character.toLowerCase(className.charAt(0))+className.substring(1);
}
```
- 클래스 이름 기반으로 빈 이름을 생성
- JAVA 네이밍 컨벤션에 맞춰 빈 이름을 자동으로 생성하기 위함 

```
예시
입력 : UserService.class
출력 : "userService"
 
클래스 이름의 첫 글자만 소문자로 바꾸고 나머지는 그대로 유지
```

## createInstance 메소드
```java
 private void createInstance(Context context, Class targetClass, String beanName) {

        // TODO#5-9 객체 생성에 사용할 생성자를 ReflectionUtils.findFirstConstructor() 메서드를 사용하여 구합니다.
        // 이 메서드는 클래스의 첫 번째 생성자를 반환하며, Reflection을 통해 동적으로 객체를 생성하는 데 사용됩니다.
        Constructor constructor = ReflectionUtils.findFirstConstructor(targetClass);

        /** TODO#5-10 생성자에 해당하는 파라미터를 순서대로 Object[] 배열 형태로 반환합니다.
         * - 파라미터는 ReflectionUtils.getParameterFromContext() 메서드를 호출하여 얻을 수 있습니다.
         * - 파라미터에 해당하는 객체는 ApplicationContext에 등록된 Bean입니다.
         */

        Object[] parameters = ReflectionUtils.getParameterFromContext(context, constructor);

        /** TODO#5-11 parameters를 이용해서 객체를 생성하고, 생성된 객체는 context.registerBean() 메서드를 이용해서 Bean(객체)로 등록합니다.
         * - 모든 Stereotype Bean이 생성되어 Application Context에 등록되었습니다.
         * - 즉, Bean은 Application Context가 관리하는 객체를 의미합니다.
         * - Stereotype으로 선언되어 Application Context에 의해 관리되는 Bean(객체)은 여러분이 지금 구현하고 있는 ApplicationContext에게 제어권이 있습니다.
         * - 이러한 형태로 관리되는 것을 제어권의 역전이라고 표현합니다. 즉, IoC(Inversion of Control, 제어의 역전)이라고 합니다.
         * - 이렇게 생성된 Bean들은 IoC Container에 의해 관리된다고 표현합니다. Servlet이 Servlet Container에 의해 라이프 사이클이 관리되는 것과 동일하게 생각할 수 있습니다.
         * - IoC Container는 Bean의 라이프 사이클을 관리합니다. Bean의 생성, 초기화, 사용, 소멸 단계에 이르기까지 모든 과정을 제어합니다.
         */

        try{
            Object instance = constructor.newInstance(parameters);
            context.registerBean(beanName, instance);
        } catch (Exception e) {
            throw new ReflectionException("Failed to create instance of "+targetClass.getName()+e);
        }
    }
```
- findFirstConstructor() 메소드를 사용해 대상 클래스의 첫번째 생성자를 찾고
- 의존성 주입을 위해 어떤 생성자를 사용할지 결정하는 단계
- ReflectionUtils.getParameterFromContext() 메소드를 사용해 생성자의 파라미터 타입에 맞는 객체를 ApplicationContext에서 가져옴
- 이를 통해 의존성 주입이 이루어짐
- constructor.newInstance() 메소드 : Reflection API를 사용해 객체를 동적으로 생성하고
- context.registerBean()메소드를 사용해 생성된 객체를 ApplicationContext에 등록하여 이를 통해 IoC 컨테이너가 해당 객체를 관리