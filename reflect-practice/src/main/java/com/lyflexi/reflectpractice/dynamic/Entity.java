package com.lyflexi.reflectpractice.dynamic;

import lombok.Data;

public class Entity {
    private Long id;
    private String name = "default";

    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Entity() {
        // Default constructor
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return id != null && id.equals(entity.id) &&
                name != null && name.equals(entity.name);
    }
}
