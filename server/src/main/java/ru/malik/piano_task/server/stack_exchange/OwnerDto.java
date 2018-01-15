package ru.malik.piano_task.server.stack_exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto implements Serializable{
    @JsonProperty(value = "display_name")
    private String displayName;
}
