# ReflectionUtils

## setFiled 메소드
- 주어진 객체의 특정 필드에 값을 강제로 할당
- 필드가 private, final 일 경우에도 접근 가능하게 처리 

```java
   public static void setField(Object target, String fieldName, Object value) {

        /**
         * TODO#5-12 객체의 특정 필드에 값을 강제로 할당합니다.
         * - 해당 필드의 접근 제한자가 private 또는 final일 수 있습니다.
         * - field.setAccessible(true)를 사용하여 필드에 접근할 수 있도록 설정합니다.
         * - field.set() 메서드를 사용하여 target 객체의 필드 값을 value로 설정합니다.
         * - [참고] https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/reflect/Field.html
         * - 예외처리 : ReflectionException 이 발생합니다.
         */
        try {
            Field field = target.getClass().getField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
```
- setAccessible(true) : 접근 제한 해제
- set(target, value) 값 설정

## classScan 메소드
```java
  public static <T>List<ClassWrapper<T>>  classScan(String packageName, Class<T> targetClass) {
        //TODO#5-13 classScan() 구현합니다.

        //Reflections객체를 packageName을 사용해서 생성합니다.
        Reflections reflections = new Reflections(packageName);

        //targetClass를 구현한 구현체를 Scan 합니다. , reflections.getSubTypesOf() 메서드를 사용하세요
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(targetClass);

        List<ClassWrapper<T>> classWrappers = new ArrayList<>();
        for(Class<? extends T> clazz : classes){
            //스캔된 class는 ClassWrapper 객체에 담아서 classWrappers 리스트에 등록 합니다.
            // new ClassWrapper(@InitOrder에 설정된 우선순위, class) 초기화 합니다.
            // @InitOrder 존재하지 않다면 1로 초기화 합니다.
            InitOrder initOrder = clazz.getAnnotation(InitOrder.class);
            int order = (initOrder!=null)? initOrder.value():Integer.MAX_VALUE;
            classWrappers.add(new ClassWrapper<T>(order, (Class<T>) clazz));

        }

        //@InitOrder value 기준으로 classWrappers내의 class들을 내림차순 정렬합니다.
        classWrappers.sort(Comparator.comparingInt(ClassWrapper::getOrder));

        return classWrappers;
    }
```
- 리플렉션과 제네릭을 활용해서 특정 패키지에서 클래스를 스캔 후 정렬된 리스트를 반환하는 메소드
- 특히 InitOrder 어노테이션을 기준으로 클래스들을 정렬하는 기능을 포함
- 제네릭 메서드: <T>는 메서드가 호출될 때 타입을 지정할 수 있도록 함
  - 예: classScan("com.example", MyInterface.class) → 여기서 T는 MyInterface로 추론됨
  - 매개변수:
    - packageName: 스캔할 패키지 이름 (예: "com.example").
    - targetClass: 특정 인터페이스나 부모 클래스. 이 클래스를 구현하거나 확장한 하위 클래스들을 찾음 
  - 반환값:
    - List<ClassWrapper<T>>: ClassWrapper 객체의 리스트를 반환함. 각 ClassWrapper는 클래스와 우선순위 정보를 담고 있음

```java
Set<Class<? extends T>> classes = reflections.getSubTypesOf(targetClass);
```
- 주어진 targetClass(인터페이스 또는 부모 클래스)를 구현하거나 확장한 모든 하위 클래스를 찾음 
- 결과는 Set<Class<? extends T>> 형태로 반환됨
  - 예: targetClass = MyInterface.class일 경우, 이를 구현한 모든 클래스가 반환됨

```java
List<ClassWrapper<T>> classWrappers = new ArrayList<>();
```
- 스캔된 클래스를 담을 리스트 생성
- 각 클래스는 ClassWrapper 객체로 감써져 추가됨

```java
for (Class<? extends T> clazz : classes) {
    InitOrder initOrder = clazz.getAnnotation(InitOrder.class);
    int order = (initOrder != null) ? initOrder.value() : Integer.MAX_VALUE;
    classWrappers.add(new ClassWrapper<T>(order, (Class<T>) clazz));
}
```
- 각 클래스를 순회하면서 이를 classWrapper 객체로 감쌈
- 우선순위 설정
  - 클래스에 붙어있는 InitOrder 어노테이션을 확인 후
    - 만약 어노테이션이 존재시 그 값을 가져오며
    - 어노테이션이 없으면 기본값으로 가장 낮은 우선순위 ('Integer.MAX_VALUE')를 설정
- Class<T> clazz 로 clazz가 제네릭 타입 T임을 명시적으로 알려줌

```java
classWrappers.sort(Comparator.comparingInt(ClassWrapper::getOrder));
```
- 리스트 정렬
- classWrapper 객체의 order 필드 기준
- ClassWrapper::getOrder는 람다식 (wrapper) -> wrapper.getOrder()와 동일한 표현
- 즉, 각 ClassWrapper 객체의 getOrder() 메서드를 호출하여 비교 기준으로 사용


## classScanByAnnotated
- 특정 패키지에서 주어진 어노테이션이 선언된 클래스를 스캔
- 스캔된 클래스들을 @InitOrder 어노테이션의 value 값을 기준으로 오름차순 정렬
```java
 public static List<ClassWrapper> classScanByAnnotated(String packageName, Class<? extends Annotation> annotatedClass) {
        //TODO#5-14 classScanByAnnotated() 구현 합니다.

        //Reflections객체를 packageName을 사용해서 생성합니다.
        Reflections reflections = new Reflections(packageName);

        //annotatedClass <-- anootation이 선언되어 있는 class를 스켄 합니다.
        Set<Class<?>> annotatedClasses = reflections.get(Scanners.TypesAnnotated.with(annotatedClass).asClass());

        List<ClassWrapper> classWrappers = new ArrayList<>();

        for(Class<?> clazz : annotatedClasses){

            //스캔된 class는 ClassWrapper 객체에 담아서 classWrappers 리스트에 등록 합니다.
            // new ClassWrapper(@InitOrder에 설정된 우선순위, class) 초기화 합니다.
            // @InitOrder 존재하지 않다면 1로 초기화 합니다.

            InitOrder initOrder = clazz.getAnnotation(InitOrder.class);
            int order = (initOrder!=null)?initOrder.value():Integer.MAX_VALUE;
            classWrappers.add(new ClassWrapper<>(order,clazz));

        }

        //@InitOrder value 기준으로 classWrappers내의 class들을 내림차순 정렬합니다.
        classWrappers.sort(Comparator.comparingInt(ClassWrapper::getOrder));

        return classWrappers;
    }

```

- Reflections reflections = new Reflections(packageName);
  - 지정된 패키지 내의 클래스들을 스캔하기 위한 Reflections 객체를 생성
- Set<Class<?>> annotatedClasses = reflections.get(Scanners.TypesAnnotated.with(annotatedClass).asClass());
  - Scanners.TypesAnnotated.with(annotatedClass): 지정된 어노테이션이 붙은 타입(클래스)을 찾음
  - .asClass(): 찾은 결과를 Class 객체로 변환

- 스캔된 클래스들을 순회하며 ClassWrapper 객체 생성:
```java
for(Class<?> clazz : annotatedClasses){
    InitOrder initOrder = clazz.getAnnotation(InitOrder.class);
    int order = (initOrder!=null)?initOrder.value():Integer.MAX_VALUE;
    classWrappers.add(new ClassWrapper<>(order,clazz));
}
```
- 각 클래스에 대해:
  - @InitOrder 어노테이션이 있는지 확인
  - 있다면 그 값을, 없다면 Integer.MAX_VALUE를 우선순위로 사용 
  - ClassWrapper 객체를 생성하여 리스트에 추가

```java
classWrappers.sort(Comparator.comparingInt(ClassWrapper::getOrder));
```
- ClassWrapper::getOrder를 기준으로 오름차순 정렬 
- 이는 @InitOrder 값이 작을수록 앞쪽에 위치

| 구분 | 클래스 스캔 (classScan) | 어노테이션 스캔 (classScanByAnnotated) |
|------|------------------------|-----------------------------------|
| 스캔 기준 | 특정 인터페이스 또는 부모 클래스 (targetClass) | 특정 어노테이션 (annotatedClass) |
| 스캔 대상 | 주어진 인터페이스/부모 클래스를 구현하거나 상속받은 하위 클래스 | 주어진 어노테이션이 붙은 클래스 |
| Reflections 메서드 | reflections.getSubTypesOf(targetClass) | reflections.get(Scanners.TypesAnnotated.with(annotatedClass).asClass()) |
| 사용 목적 | - 특정 인터페이스를 구현한 모든 클래스를 찾고 처리 | - 특정 어노테이션이 붙은 클래스만 필터링하여 처리 |
| 예시 | - IoC 컨테이너에서 인터페이스 기반으로 빈 등록 | - 어노테이션 기반으로 빈 등록 (e.g., @Service, @Repository) |


## findFirstConstructor 메소드
```java 
public static <T>Constructor<T> findFirstConstructor(Class<?> clazz) {
        //TODO#5-15 findFirstConstructor()를 구현 합니다.

        //clazz로 부터 생성자 리스트를 구합니다.
        Constructor<?>[] constructors = clazz.getDeclaredConstructors(); // 모든 생성자 가져오기

        //constructors >0 면 처음 발견된 생성자를 반환 합니다.
        //constructors==0 이면  ReflectionException이 발생 합니다.
        if(constructors.length==0){
            throw new ReflectionException("Not Constructors found for class: "+ clazz.getName());
        }

        Constructor<T> firstConstructor = (Constructor<T>) constructors[0];

        return firstConstructor;
    }
```
- <T>는 클래스 타입을 지정할 수 있도록 함
- 예시로 MyClass 입력시 Constructor<MyClass>가 반환됨
- clazz : 생성자를 찾고자하는 클래스
- clazz.getDeclaredConstructors()는 해당 클래스에 정의된 모든 생성자를 배열로 반환


## getParameterFromContext 메소드
```java
public static Object[] getParameterFromContext(Context context, Constructor constructor){
  //TODO#5-16 constructor 생성자에 주입되는 parameter를 조회하고 해당 parameter에 해당되는 객체를 Context로 부터 Bean(객체)를 주입 받아서 Object[] 형태로 반환 합니다.

  // 생성자가 필요로 하는 파라미터 개수 확인
  int paramCount = constructor.getParameterCount();

  //생성자의 파라미터 개수가 == 0이면 new Object[0]을 반환합니다.
  if(paramCount == 0){
    return new Object[0];
  }

  //parameters 배열을 생성자의 파라미터 개수 만큼 배열 크기를 초기화 합니다.
  Object[] parameters = new Object[paramCount];

  for (int i=0; i<constructor.getParameterCount(); i++) {
    /**
     * 생성자의 파라미터 개수만큼 순회하면서 해당 파라미터에 해당되는 Bean을 찾아 Object[] 배열로 반환합니다.
     * 다음과 같은 @Service가 있다고 가정해봅시다.
     *
     * BlogInfoServiceImpl 생성자에 다음과 같이 파라미터가 존재합니다:
     * - @Qualifier(JdbcBlogRepository.BEAN_NAME) BlogRepository blogRepository,
     * - @Qualifier(JdbcBlogMemberMappingRepository.BEAN_NAME) BlogMemberMappingRepository blogMemberMappingRepository
     *
     * BlogRepository blogRepository는 Application Context에서 @Qualifier(JdbcBlogRepository.BEAN_NAME)의 BeanName('blogInfoService')에 해당하는 객체를 찾아서 주입합니다.
     *
     * 즉, Object[] 배열에 파라미터의 순서에 맞춰 Context로부터 Bean(객체)를 할당합니다.
     *
     * 예시:
     *
     * @Service(BlogInfoServiceImpl.BEAN_NAME)
     * public class BlogInfoServiceImpl implements BlogInfoService {
     *
     *     public static final String BEAN_NAME = "blogInfoService";
     *     private final BlogRepository blogRepository;
     *     private final BlogMemberMappingRepository blogMemberMappingRepository;
     *
     *     public BlogInfoServiceImpl(
     *         @Qualifier(JdbcBlogRepository.BEAN_NAME) BlogRepository blogRepository,
     *         @Qualifier(JdbcBlogMemberMappingRepository.BEAN_NAME) BlogMemberMappingRepository blogMemberMappingRepository
     *     ) {
     *         this.blogRepository = blogRepository;
     *         this.blogMemberMappingRepository = blogMemberMappingRepository;
     *     }
     *     //...
     * }
     */

    Parameter parameter = constructor.getParameters()[i];

    //@qulifier annotation에 정의된 beanName을 구합니다.
    Qualifier qualifier = parameter.getAnnotation(Qualifier.class);


    // qualifier 가 존재하지 않다면 ReflectionException 발생 합니다.
    if(qualifier == null){
      throw new ReflectionException("No @Qualifier found for parameter:" + parameter.getName());
    }

    //qualifier로 부터 beanName을 얻습니다.
    String beanName = qualifier.value();

    //context에서 @Qulifier(value="{beanName}")에 해당된 Bean(객체)를 얻습니다.
    Object bean = context.getBean(beanName);

    //Bean이 Context에 존재하지 않으면 'BeanNotFoundException'이 발생합니다.
    if(qualifier==null){
      throw new BeanNotFoundException("Bean not found for name:"+ beanName);
    }
    //bean(객체)을 순서에 맞춰 parameter[i]로 할당 합니다.
    parameters[i]=bean;
  }
  return parameters;
}
```
- 생성자의 파라미터에 해당되는 객체를 @Qualifier 어노테이션에 정의된 beanName에 의해서 조회 후 Object[] 반환
- 