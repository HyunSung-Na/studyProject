package com.infinite.study.repository.user;

import com.infinite.study.model.user.ConnectedUser;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.Id;
import com.infinite.study.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User insert(User user);

    void update(User user);

    Optional<User> findById(Id<User, Long> userId);

    Optional<User> findByEmail(Email email);

    Optional<User> findByName(Id<User, String> name);

    List<ConnectedUser> findAllConnectedUser(Id<User, Long> userId);

    List<Id<User, Long>> findConnectedIds(Id<User, Long> userId);
}
