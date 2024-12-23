package com.nhnacademy.blog.topic.repository.impl;

import com.nhnacademy.blog.common.annotation.stereotype.Repository;
import com.nhnacademy.blog.common.db.exception.DatabaseException;
import com.nhnacademy.blog.common.reflection.ReflectionUtils;
import com.nhnacademy.blog.common.transactional.DbConnectionThreadLocal;
import com.nhnacademy.blog.topic.domain.Topic;
import com.nhnacademy.blog.topic.dto.TopicUpdateRequestDto;
import com.nhnacademy.blog.topic.repository.TopicRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("java:S1192")

//TODO#5-3 @Repository를 작성해주세요
public class JdbcTopicRepository implements TopicRepository {

    public static final String BEAN_NAME = "changeMe";

    @Override
    public void save(Topic topic) {
        // TODO#5-4: 주제를 데이터베이스에 저장하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. INSERT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하고 생성된 키를 받아옵니다.
        //  5. 주제 ID를 topic 객체에 설정합니다.
    }

    @Override
    public void update(TopicUpdateRequestDto topicUpdateRequestDto) {
        // TODO#5-5: 주제의 정보를 업데이트하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. UPDATE SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 주제 정보를 업데이트합니다.
    }

    @Override
    public void deleteByTopicId(Integer topicId) {
        // TODO#5-6: 주제 ID를 이용하여 주제를 삭제하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. DELETE SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 주제를 삭제합니다.
    }

    @Override
    public Optional<Topic> findByTopicId(Integer topicId) {
        // TODO#5-7: 주제 ID를 이용하여 주제를 조회하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. SELECT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 결과를 ResultSet으로 받습니다.
        //  5. ResultSet에서 데이터를 추출하여 Topic 객체를 생성합니다.
        //  6. 조회된 주제를 Optional로 반환합니다.
        return Optional.empty();
    }

    @Override
    public List<Topic> findAll(Integer topicPid) {
        // TODO#5-8: 상위 주제 ID를 이용하여 모든 하위 주제를 조회하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. SELECT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 결과를 ResultSet으로 받습니다.
        //  5. ResultSet에서 데이터를 추출하여 Topic 객체 리스트를 생성합니다.
        //  6. 생성된 Topic 객체 리스트를 반환합니다.
        return new ArrayList<>();
    }

    @Override
    public boolean existByTopicId(Integer topicId) {
        // TODO#5-9: 주제 ID로 주제가 존재하는지 확인하는 기능을 구현합니다.
        //  1. DB 커넥션을 가져옵니다.
        //  2. SELECT SQL 문을 작성합니다.
        //  3. PreparedStatement를 생성하고 값을 설정합니다.
        //  4. SQL을 실행하여 결과를 ResultSet으로 받습니다.
        //  5. 결과가 존재하면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
        return false;
    }
}
