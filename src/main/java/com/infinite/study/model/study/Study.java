package com.infinite.study.model.study;

import com.infinite.study.model.Id;
import com.infinite.study.util.Writer;
import com.infinite.study.model.user.User;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class Study {

    private final Long seq;

    private final Id<User, Long> user_seq;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private String zones;

    private final Writer writer;

    private final LocalDateTime publishDateTime;

    public Study(Id<User, Long> user_seq, String title, String shortDescription, String fullDescription, String zones, Writer writer) {
        this(null, user_seq, title, shortDescription, fullDescription, zones, writer, null);
    }

    public Study(Long seq, Id<User, Long> user_seq, String title, String shortDescription, String fullDescription, String zones, Writer writer, LocalDateTime publishDateTime) {
        checkNotNull(user_seq, "userId must be provided.");
        checkNotNull(title, "title must be provided.");
        checkArgument(isNotEmpty(fullDescription), "fullDescription must be provided.");
        checkArgument(
                fullDescription.length() >= 4 && fullDescription.length() <= 1000,
                "Study fullDescription length must be between 4 and 1000 characters."
        );
        checkNotNull(zones, "zones must be provided.");

        this.seq = seq;
        this.user_seq = user_seq;
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.zones = zones;
        this.writer = writer;
        this.publishDateTime = defaultIfNull(publishDateTime, now());

    }

    public void modify(String title, String fullDescription, String shortDescription, String zones) {
        checkArgument(isNotEmpty(fullDescription), "fullDescription must be provided.");
        checkArgument(
                fullDescription.length() >= 4 && fullDescription.length() <= 1000,
                "study fullDescription length must be between 4 and 1000 characters."
        );
        checkArgument(isNotEmpty(shortDescription), "shortDescription must be provided.");
        checkArgument(
                shortDescription.length() >= 2 && shortDescription.length() <= 100,
                "study fullDescription length must be between 2 and 100 characters."
        );

        checkArgument(isNotEmpty(title), "title must be provided.");
        checkArgument(isNotEmpty(zones), "zones must be provided.");

        this.title = title;
        this.fullDescription = fullDescription;
        this.shortDescription = shortDescription;
        this.zones = zones;
    }


    public Long getSeq() {
        return seq;
    }

    public Id<User, Long> getUser_seq() {
        return user_seq;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getZones() {
        return zones;
    }

    public Writer getWriter() {
        return writer;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Study study = (Study) o;
        return Objects.equals(seq, study.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seq);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("seq", seq)
                .append("user_seq", user_seq)
                .append("title", title)
                .append("shortDescription", shortDescription)
                .append("fullDescription", fullDescription)
                .append("zones", zones)
                .append("writer", writer)
                .append("publishDateTime", publishDateTime)
                .toString();
    }

    static public class Builder {
        private Long seq;
        private Id<User, Long> user_seq;
        private String title;
        private String shortDescription;
        private String fullDescription;
        private String zones;
        private Writer writer;
        private LocalDateTime publishDateTime;

        public Builder() {
        }

        public Builder(Study study) {
            this.seq = study.seq;
            this.user_seq = study.user_seq;
            this.title = study.title;
            this.shortDescription = study.shortDescription;
            this.fullDescription = study.fullDescription;
            this.zones = study.zones;
            this.writer = study.writer;
            this.publishDateTime = study.publishDateTime;
        }

        public Study.Builder seq(Long seq) {
            this.seq = seq;
            return this;
        }

        public Study.Builder user_seq(Id<User, Long> user_seq) {
            this.user_seq = user_seq;
            return this;
        }

        public Study.Builder title(String title) {
            this.title = title;
            return this;
        }

        public Study.Builder shortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Study.Builder fullDescription(String fullDescription) {
            this.fullDescription = fullDescription;
            return this;
        }

        public Study.Builder zones(String zones) {
            this.zones = zones;
            return this;
        }

        public Study.Builder writer(Writer writer) {
            this.writer = writer;
            return this;
        }

        public Study.Builder publishDateTime(LocalDateTime publishDateTime) {
            this.publishDateTime = publishDateTime;
            return this;
        }

        public Study build() {
            return new Study(seq, user_seq, title, shortDescription, fullDescription, zones, writer, publishDateTime);
        }
    }
}
