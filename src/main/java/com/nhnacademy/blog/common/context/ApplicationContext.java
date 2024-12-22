package com.nhnacademy.blog.common.context;

import com.nhnacademy.blog.common.context.exception.BeanNotFoundException;
import com.nhnacademy.blog.common.init.Initializeable;
import com.nhnacademy.blog.common.reflection.ClassWrapper;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import com.nhnacademy.blog.common.reflection.exception.ReflectionException;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class ApplicationContext  implements Context{
    private final ConcurrentMap<String, Object> beanMap;

    public ApplicationContext() {
        //TODO#1-2 applicationContext 초기화
        //beanMap을 초기화, initialize() method를 호출 합니다.
        this.beanMap = null;

    }

    private synchronized void initialize(){
        log.debug("Initializeable based, initializing application context");

        /*TODO#1-3 Initializeable 구현 class를 scan 한다.
               ReflectionUtils.classScan() method를 이용해서 구현 합니다.
        */
        List<ClassWrapper<Initializeable>> classWrappers = null;

        for(ClassWrapper<Initializeable> classWrapper : classWrappers){
            log.debug("registering bean : {}", classWrapper.getClazz().getSimpleName());

            /* TODO#1-4 classWrappers를 순회하면서 initialize() 메서드를 호출하여 초기화합니다.
             * Exception 발생 시 ReflectionException을 이용하여 처리합니다.
             */

        }
        log.debug("size:{}", classWrappers.size());
    }

    @Override
    public void registerBean(String name, Object object) {

        /*TODO#1-5 beanMap에 object를 등록 합니다.
         - 등록하는 과정에서 objectNameCheck method를 이용해서 name에 해당되는 객체가 존재하는지 체크 합니다.
         - 객체를 등록하는 과정은 멀티 쓰레드 환경에서 안정성이 보장되어야 합니다.
        */

    }

    @Override
    public void removeBean(String name) {
        /* TODO#1-6 beanMap에서 name에 해당하는 객체를 삭제합니다.
          - 삭제 과정에서 objectNameCheck 메서드를 이용하여 name에 해당하는 객체가 존재하는지 확인합니다.
          - 삭제 과정은 멀티 쓰레드 환경에서 안정성이 보장되어야 합니다.
         */


    }

    @Override
    public Object getBean(String name) {

        /* TODO#1-7 beanMap에서 name에 해당하는 객체를 반환합니다.
           - name에 해당하는 객체가 존재하지 않는다면 BeanNotFoundException 예외가 발생합니다.
         */
        objectNameCheck(name);
        Object object =  null;

        return object;
    }

    private void objectNameCheck(String name){
        //TODO#1-8 name이 null or "" 이면 IllegalArgumentException이 발생 합니다.

    }
}