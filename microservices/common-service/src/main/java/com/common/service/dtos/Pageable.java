package com.common.service.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class Pageable implements Serializable {

    private static final long serialVersionUID = -4112436951057744454L;

    // Indiactes record index from where the data is fetched
    private Long startIndex;

    // Indicates the number of records fetched in the response
    private Long pageSize;
}
