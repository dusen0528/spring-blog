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
 * TODO#4 데이터베이스 관련 환경설정을(/resources/db.properties) 읽어서 객체(DbProperties)를 생성 후
 * Context에 bean으로 등록하는 Initializeable 인터페이스의 구현체입니다.
 * @InitOrder(value = 1)임으로 ApplicationContext가 초기화될 때 가장 처음으로 호출됩니다.
 * InitDbPropertiesReader를 구현하세요.
 */

@Slf4j
@InitOrder(value = 1)
public class InitDbPropertiesReader implements Initializeable {
    //데이터베이스 관련 환경설정 파일의 이름은 = "db.properties" 입니다.
    private static final String DEFAULT_DB_PROPERTIES_FILE="db.properties";
    private final String dbPropertiesFile;

    public InitDbPropertiesReader(){
        //TODO#4-1 기본 데이터베이스 환경설정 파일명(DEFAULT_DB_PROPERTIES_FILE)을 사용해서 생성자를 호출 합니다.
        dbPropertiesFile = null;
    }

    //데이터베이스 환경설정 기본 파일(DEFAULT_DB_PROPERTIES_FILE)을 사용하지 않고 직접 파일명을 입력받아 생성할 수 있는 생성자 입니다.
    public InitDbPropertiesReader(String dbPropertiesFile) {
        //TODO#4-2 dbPropertiesFile null or "" 이면  IllegalArgumentException이 발생 합니다.

        this.dbPropertiesFile = null;
    }

    /**
     *
     * @param context application이 구동되는 환경
     * application context가 초기화 과정에서 호출되는 method 입니다.
     */
    @Override
    public void initialize(Context context) {

        /**
         * 데이터베이스 환경설정 파일(resources/db.properties)을 HashTable의 구현체인 java.util.Properties를 이용해서 파싱하고
         * 파싱한 데이터를 DbProperties 객체 형태로 만들어서 context에 bean으로 등록합니다.
         * beanName은 DbProperties.BEAN_NAME을 사용합니다.
         * 아래 코드의 흐름에 따라서 해당 private 메서드를 구현합니다.
         */


        //TODO#4-3 readProperties() method를 호출 합니다.
        Properties properties = null;

        //TODO#4-4 getMapFromProperties(properties)를 호출해서 properties를 Map<String, String> 반환 합니다.
        Map<String, String> map = null;

        //TODO#4-5 getDbPropertiesFromMap(map) method를 이용해서  map<String,String> -> DbProperties 객체로 변환 합니다.
        DbProperties dbProperties = null;

        log.debug("context:{}",context);
        //TODO#4-6 context에 registerBean() method를 이용해서 다음고 같은 형태로 bean을 등록 합니다.
        // beanName = DbProperties.BEAN_NAME, Object = dbProperties

    }

    private Properties readProperties(){
        //properties 객체를 초기화 합니다.
        Properties properties = null;

        /**
         *  dbPropertiesFile에 해당하는 db.properties 파일을 읽고, java.util.Properties 객체에 로드합니다.
         *  java.util.Properties는 키-값 쌍을 저장하고, 파일 입출력을 통해 쉽게 설정을 로드하고 저장할 수 있는 유틸리티 클래스입니다.
         *  이를 통해 데이터베이스 연결 정보를 포함한 다양한 설정을 간편하게 관리할 수 있습니다.
         *  파일을 읽기 위해 getClassLoader().getResourceAsStream() 메서드를 사용합니다.
         *  이 메서드는 클래스패스에서 파일을 검색하고, 해당 파일의 InputStream을 반환합니다.
         *  예: InputStream input = this.getClass().getClassLoader().getResourceAsStream("db.properties");
         */
        try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(dbPropertiesFile)) {
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    /**
     *
     * @param properties 데이터베이스 설정정보 객체
     * @return Map<String key , String value)
     */
    private Map<String,String> getMapFromProperties(Properties properties) {

        Map<String,String> map = null;
        //properties -> map 변환
        return map;
    }

    /**
     * Map<String, String>를 DbProperties 객체로 변환 후 반환합니다.
     *
     * @param map Map<String, String>
     * @return DbProperties
     */
    private DbProperties getDbPropertiesFromMap(Map<String, String> map){

        String url = map.get("url");
        String username=map.get("username");
        String password=map.get("password");

        int initialSize = Integer.parseInt(map.get("initialSize"));
        int maxTotal = Integer.parseInt(map.get("maxTotal"));
        int maxIdle = Integer.parseInt(map.get("maxIdle"));
        int minIdle = Integer.parseInt(map.get("minIdle"));
        int maxWait = Integer.parseInt(map.get("maxWait"));
        String validationQuery = map.get("validationQuery");
        boolean testOnBorrow = Boolean.parseBoolean(map.get("testOnBorrow"));
        boolean spy = Boolean.parseBoolean(map.get("spy"));

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
