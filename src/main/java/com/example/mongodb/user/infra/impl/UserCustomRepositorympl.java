package com.example.mongodb.user.infra.impl;

import java.util.List;

import com.example.mongodb.user.domain.User;
import com.example.mongodb.user.domain.UserCustomRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepositorympl implements UserCustomRepository {

    private final MongoTemplate mongoTemplate;

    public UserCustomRepositorympl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);

    }

    @Override
    public User addNewUser(User user) {
        mongoTemplate.save(user);
        // Now, user object will contain the ID as well
        return user;
    }

    @Override
    public void nameUpdateUser(String userId, String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));

        Update update = Update.update("name", name);//.inc("age", decreaseAge);

         mongoTemplate.updateFirst(query, update, User.class);

        /*// 수정과 동시에 조회
        User user = mongoTemplate.findAndModify(
                Query.query(Criteria.where("userId").is(name)),
                Update.update("name", name),
                User.class
        );

        // 단일 데이터 수정
        UpdateResult result = mongoTemplate.updateFirst(
                Query.query(Criteria.where("userId").is(name)),
                Update.update("name", name),
                User.class
        );

        // 복수 데이터 수정
        UpdateResult result = mongoTemplate.updateMulti(
                Query.query(Criteria.where("userId").is(name)),
                Update.update("name", name),
                User.class
        );*/

    }

    @Override
    public Object getAllUserSettings(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        return user != null ? user.getUserSettings() : "User not found.";
    }

    @Override
    public String getUserSetting(String userId, String key) {
        Query query = new Query();
        query.fields().include("userSettings");
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
        User user = mongoTemplate.findOne(query, User.class);
        return user != null ? user.getUserSettings().get(key) : "Not found.";
    }

    @Override
    public String addUserSetting(String userId, String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        User user = mongoTemplate.findOne(query, User.class);
        if (user != null) {
            user.getUserSettings().put(key, value);
            mongoTemplate.save(user);
            return "Key added.";
        } else {
            return "User not found.";
        }
    }

    /*// 전체 조회
    List<User> users = mongoTemplate.findAll(User.class);

    // 목록 조회
    List<User> users = mongoTemplate.find(
            Query.query(Criteria.where("name").is("john")),
            User.class
    );

    // 단건 조회
    User user = mongoTemplate.findOne(
            Query.query(Criteria.where("name").is("john")),
            User.class
    );

    // count 조회
    long count = mongoTemplate.count(new Query(), User.class);

    // distinct 조회
    List<String> names = mongoTemplate.findDistinct("name", User.class, String.class);

    // pagination : 1페이지 10개 데이터 조회
    List<User> users = mongoTemplate.find(new Query().with(PageRequest.of(0, 10)), User.class);

    // 정렬 결과 조회
    List<User> users = mongoTemplate.find(new Query().with(Sort.by(Sort.Direction.DESC, "age")), User.class);

    // or 조건 조회
    List<User> users = mongoTemplate.find(
            Query.query(
                    Criteria.where("name").is("john")
                            .and("password").is("0000")
                            .orOperator(
                                    Criteria.where("email").regex("naver.com"),
                                    Criteria.where("email").regex("google.com")
                            )
            ),
            User.class
    );

    // 특정 필드 exclude 조회
    Query query = new Query();
    query.fields().exclude("_id");
    List<Document> users = mongoTemplate.find(query, Document.class, "users");*/
}
