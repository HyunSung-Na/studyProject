package com.infinite.study.model.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class User {

    private final Long seq;

    private final String nickname;

    private final Email email;

    private String password;

    private int login_count;

    private final LocalDateTime create_at;

    private LocalDateTime last_login_at;

    public User(String nickname, Email email, String password) {
        this(null, nickname, email, password, 0, null, null);
    }

    public User(Long seq, String nickname, Email email, String password, int login_count, LocalDateTime create_at, LocalDateTime last_login_at) {
        checkArgument(isNotEmpty(nickname), "name must be provided.");
        checkArgument(
                nickname.length() >= 1 && nickname.length() <= 10,
                "name length must be between 1 and 10 characters."
        );
        checkNotNull(email, "email must be provided.");
        checkNotNull(password, "password must be provided.");

        this.seq = seq;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.login_count = login_count;
        this.create_at = defaultIfNull(create_at, now());
        this.last_login_at = last_login_at;
    }

    public Long getSeq() {
        return seq;
    }

    public String getNickname() {
        return nickname;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLogin_count() {
        return login_count;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public Optional<LocalDateTime> getLast_login_at() {
        return ofNullable(last_login_at);
    }

    public void login(PasswordEncoder passwordEncoder, String credentials) {
        if (!passwordEncoder.matches(credentials, password))
            throw new IllegalArgumentException("Bad credential");
    }

    public void afterLoginSuccess() {
        login_count++;
        last_login_at = now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(seq, user.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("seq", seq)
                .append("nickname", nickname)
                .append("email", email)
                .append("password", "[PROTECTED]")
                .append("login_count", login_count)
                .append("create_at", create_at)
                .append("last_login_at", last_login_at)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private String nickname;
        private Email email;
        private String password;
        private int login_count;
        private LocalDateTime create_at;
        private LocalDateTime last_login_at;

        public Builder() {}

        public Builder(User user) {
            this.seq = user.seq;
            this.nickname = user.nickname;
            this.email = user.email;
            this.password = user.password;
            this.login_count = user.login_count;
            this.create_at = user.create_at;
            this.last_login_at = user.last_login_at;
        }

        public Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }


        public Builder login_count(int login_count) {
            this.login_count = login_count;
            return this;
        }

        public Builder create_at(LocalDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public Builder last_login_at(LocalDateTime last_login_at) {
            this.last_login_at = last_login_at;
            return this;
        }

        public User build() {
            return new User(seq, nickname, email, password, login_count, create_at, last_login_at);
        }
    }
}
