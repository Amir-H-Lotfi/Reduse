package com.site.rrus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class LinkResponse implements Serializable {
    @JsonProperty private String shortenedLink ;
}
