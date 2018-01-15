package ru.malik.piano_task.server.stack_exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import ru.malik.piano_task.server.utils.UnixTimestampDeserializer;

import java.io.Serializable;
import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto implements Serializable {
    private String title;
    private OwnerDto owner;
    @JsonProperty(value = "creation_date")
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Timestamp creationDate;
    private String link;
    @JsonProperty(value = "is_answered")
    private boolean answered;
}
