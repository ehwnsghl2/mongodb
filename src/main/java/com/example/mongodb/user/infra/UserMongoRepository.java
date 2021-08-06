package com.example.mongodb.user.infra;


import com.example.mongodb.user.domain.User;
import com.example.mongodb.user.domain.UserRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String>, UserRepository {

}
