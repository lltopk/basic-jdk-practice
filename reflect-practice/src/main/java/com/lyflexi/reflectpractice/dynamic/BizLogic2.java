package com.lyflexi.reflectpractice.dynamic;

public class BizLogic2 {
    private Long id;
    private String name = "default";

    public BizLogic2(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public BizLogic2() {
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
        BizLogic2 bizLogic1 = (BizLogic2) o;
        return id != null && id.equals(bizLogic1.id) &&
                name != null && name.equals(bizLogic1.name);
    }

    public void doSomething(String action) {
        // 业务逻辑方法
        System.out.println("Doing something with BizLogic2" + action);
    }
}
