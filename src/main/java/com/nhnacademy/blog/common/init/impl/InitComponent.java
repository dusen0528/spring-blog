package com.nhnacademy.blog.common.init.impl;

import com.nhnacademy.blog.common.annotation.InitOrder;
import com.nhnacademy.blog.common.annotation.stereotype.Component;
import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.annotation.stereotype.Service;
import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.init.Initializeable;
import com.nhnacademy.blog.common.reflection.ClassWrapper;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import com.nhnacademy.blog.common.reflection.exception.ReflectionException;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.*;

/* TODO#5 Stereotype Bean 생성
 * - Stereotype Bean (@Component, @Service, @Repository) 어노테이션이 포함된 클래스를 스캔하고 해당 클래스를 생성 후 Application Context에 bean으로 등록합니다.
 * - 여기서 Bean이라는 표현은 Application Context에서 관리하는 객체를 의미합니다.
 */


@Slf4j
@InitOrder(value = 4)
@SuppressWarnings("java:S3740")
public class InitComponent implements Initializeable {

    @Override
    public void initialize(Context context) {

        /**
         * TODO#5-1 @Component 어노테이션을 기준으로 'com.nhnacademy.blog' 패키지 하위에 있는 클래스를 스캔합니다.
         * - @Repository, @Service 어노테이션은 @Component 어노테이션을 포함하고 있기 때문에,
         * - 결과적으로 @Component를 스캔했을 때 @Service, @Repository도 함께 스캔됩니다.
         * - ReflectionUtils.classScanByAnnotated() 메서드를 이용해서 구현합니다.
         */
        List<ClassWrapper> classWrappers =  ReflectionUtils.classScanByAnnotated(
                "com.nhnacademy.blog", Component.class
        );


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


        for (ClassWrapper<Component> classWrapper : classWrappers) {
            log.debug("find stereotype class: {}", classWrapper.getClazz().getSimpleName());

            /**
             * TODO#5-3 classWrapper.getClazz()의 타입이 interface이면 continue;를 호출합니다.
             * - 즉, @Component, @Repository, @Service를 정의한 인터페이스는 제외하려고 합니다.
             */

            if(classWrapper.getClazz().isInterface()){
                continue;
            }
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

        }//end for

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

    }//end method

    /**
     * className -> beanName으로 변경
     * ex)PasswordEncoder -> passwordEncoder, MemberService - > memberService
     * @param clazz
     * @return beanName
     */
    public static String classNameToBeanName(Class<?> clazz) {
        //TODO#5-8 classNameToBeanName()를 구현 합니다.
        String className = clazz.getSimpleName();
        log.debug("className:{}",className);
        return Character.toLowerCase(className.charAt(0))+className.substring(1);
    }

    /**
     * targetClass를 생성해서 context에 등록합니다. 등록시 객체를 식별하기 위해서 beanName을 사용합니다.
     * @param context
     * @param targetClass
     * @param beanName
     */
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
}
