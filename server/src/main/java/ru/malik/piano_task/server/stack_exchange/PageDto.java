package ru.malik.piano_task.server.stack_exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto<T extends Serializable> implements Serializable{
    List<T> items;
    long total;
}
