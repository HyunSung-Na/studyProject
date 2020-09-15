package com.infinite.study.service;

import com.infinite.study.error.NotFoundException;
import com.infinite.study.model.Id;
import com.infinite.study.model.user.Email;
import com.infinite.study.model.user.User;
import com.infinite.study.model.user.UserAccount;
import com.infinite.study.repository.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User join(String nickname, Email email, String password) {
        checkArgument(isNotEmpty(password), "password must be provided.");
        checkArgument(
                password.length() >= 4 && password.length() <= 15,
                "password length must be between 4 and 15 characters."
        );

        User user = new User(nickname, email, passwordEncoder.encode(password));
        User saved = insert(user);

        return saved;
    }

    @Transactional
    public User login(Email email, String password) {
        checkNotNull(password, "password must be provided.");

        User user = findByEmail(email)
                .orElseThrow(() -> new NotFoundException(User.class, email));
        user.login(passwordEncoder, password);
        user.afterLoginSuccess();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(user),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        update(user);
        return user;
    }


    @Transactional(readOnly = true)
    public Optional<User> findById(Id<User, Long> userId) {
        checkNotNull(userId, "userId must be provided.");

        return userRepository.findById(userId);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(Email email) {
        checkNotNull(email, "email must be provided.");

        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrNickName) throws UsernameNotFoundException {
        Email email = new Email(emailOrNickName);
        userRepository.findByEmail(email);
        return null;
    }

    private User insert(User user) {
        return userRepository.insert(user);
    }

    private void update(User user) {
        userRepository.update(user);
    }
}
