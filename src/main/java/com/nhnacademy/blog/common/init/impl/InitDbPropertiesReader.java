package com.nhnacademy.blog.common.init.impl;

import com.nhnacademy.blog.common.context.Context;
import com.nhnacademy.blog.common.annotation.InitOrder;
import com.nhnacademy.blog.common.db.DbProperties;
import com.nhnacademy.blog.common.init.Initializeable;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 4 데이터베이스 관련된 환경설정을(/resources/db.properties) 읽어서  객체(DbProperties)를 생성 후
 * Context에 bean으로 등록하는 Initializeable interface의 구현체 입니다.
 * @InitOrder(value = 1)임으로 ApplicationContext가 초기화 될 때 가장 처음으로 호출 됩니다.
 * InitDbPropertiesReader를 구현하세요.
 */

@Slf4j
@InitOrder(value = 1)
public class InitDbPropertiesReader implements Initializeable {
    //데이터베이스 관련 환경설정 파일의 이름은 = "db.properties" 입니다.
    private static final String DEFAULT_DB_PROPERTIES_FILE="db.properties";
    private final String dbPropertiesFile;

    public InitDbPropertiesReader(){
        //1 기본 데이터베이스 환경설정 파일명(DEFAULT_DB_PROPERTIES_FILE)을 사용해서 생성자를 호출 합니다.
        this(DEFAULT_DB_PROPERTIES_FILE);
    }

    //데이터베이스 환경설정 기본 파일(DEFAULT_DB_PROPERTIES_FILE)을 사용하지 않고 직접 파일명을 입력받아 생성할 수 있는 생성자 입니다.
    public InitDbPropertiesReader(String dbPropertiesFile) {
        //2 dbPropertiesFile null or "" 이면  IllegalArgumentException이 발생 합니다.
        if(dbPropertiesFile == null || dbPropertiesFile.isBlank()) {
            throw new IllegalArgumentException(String.format("%s cannot be null or empty", dbPropertiesFile));
        }
        this.dbPropertiesFile = dbPropertiesFile;
    }

    /**
     *
     * @param context application이 구동되는 환경
     * application context가 초기화 과정에서 호출되는 method 입니다.
     */
    @Override
    public void initialize(Context context) {

        /**
         *  데이터베이스 환경설정 파일(resourcs/db.properties)를  HashTable의 구현체인 java.util.Properties 를 이용해서 파싱하고
         * 파싱한 데이터를 DbProperties 객체형테로 만들어서 context에 bean으로 등록 합니다.
         * beanName은 DbProperties.BEAN_NAME 을 사용 합니다.
         * 아라 코드의 흐름에 따라서 해당 private method를 구현 합니다.
         */

        //3 readProperties() method를 호출 합니다.
        Properties properties = readProperties();

       //4 createDbProperties() 메서드를 호출하여 DbProperties객체를 생성 합니다.
        DbProperties dbProperties = createDbProperties(properties);

        log.debug("context:{}",context);
        //5 context에 registerBean() method를 이용해서 다음고 같은 형태로 bean을 등록 합니다.
        // beanName = DbProperties.BEAN_NAME, Object = dbProperties
        context.registerBean(DbProperties.BEAN_NAME,dbProperties);
    }

    private Properties readProperties(){
        //properties 객체를 초기화 합니다.
        Properties properties = new Properties();

        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(dbPropertiesFile)) {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     * properties -> DbProperties 객체를 생성합니다.
     * @param properties 데이터베이스 접속 정보를 담고 있는 Properties 객체
     * @return DbProperties를 반환합니다.
     */

    private DbProperties createDbProperties(Properties properties) {

        String url = properties.getProperty("url");
        String username=properties.getProperty("username");
        String password=properties.getProperty("password");

        int initialSize = Integer.parseInt(properties.getProperty("initialSize"));
        int maxTotal = Integer.parseInt(properties.getProperty("maxTotal"));
        int maxIdle = Integer.parseInt(properties.getProperty("maxIdle"));
        int minIdle = Integer.parseInt(properties.getProperty("minIdle"));
        int maxWait = Integer.parseInt(properties.getProperty("maxWait"));
        String validationQuery = properties.getProperty("validationQuery");
        boolean testOnBorrow = Boolean.parseBoolean(properties.getProperty("testOnBorrow"));
        boolean spy = Boolean.parseBoolean(properties.getProperty("spy"));

        DbProperties dbProperties = new DbProperties(
                url,
                username,
                password,
                initialSize,
                maxTotal,
                maxIdle,
                minIdle,
                maxWait,
                validationQuery,
                testOnBorrow,
                spy
        );
        log.debug("dbProperties: {}", dbProperties);

        return dbProperties;
    }

}
