package ru.malik.piano_task.server.domain;


import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Question {
    private String title;
    private Timestamp date;
    private String owner;
    private String url;
    private boolean answered;
}
