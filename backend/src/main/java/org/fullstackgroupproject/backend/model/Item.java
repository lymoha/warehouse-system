package org.fullstackgroupproject.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private String id;
    private String name;
    private int amount;


}
