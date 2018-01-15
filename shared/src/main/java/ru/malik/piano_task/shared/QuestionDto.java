package ru.malik.piano_task.shared;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class QuestionDto implements Serializable{
    private String title;
    private String creator;
    private Timestamp creationDate;
    private String url;
    private boolean answered;
}
