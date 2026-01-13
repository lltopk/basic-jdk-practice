package com.hm.streampractice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CannotStreamPassReference {
    public static void main(String[] args) {
        canModifyCollectionLocalReference();
        cannotModifyCollectionPassReference();
        selfModifyCollectionPassReference();
        modifyObjReference();
    }

    private static void canModifyCollectionLocalReference(){
        List<StringBuffer> list = new ArrayList<StringBuffer>(){{
            add(new StringBuffer("one"));
            add(new StringBuffer("two"));
        }};

        list = list.stream().filter((l)->{
            return "one".equals(l.toString());
        }).collect(Collectors.toList());

        log.info("{}", list);
    }

    /**
     * stream的终端操作如collect/reduce都是对副本操作, 因此无法修改通过函数传递过来的引用list
     */
    private static void cannotModifyCollectionPassReference(){
        List<StringBuffer> list = new ArrayList<StringBuffer>(){{
            add(new StringBuffer("one"));
            add(new StringBuffer("two"));
        }};

        passReferenceFail(list);

        log.info("cannot modify collection pass reference {}", list);
    }

    private static void passReferenceFail(List<StringBuffer> list) {
        list.stream().filter((l)->{
            return "one".equals(l.toString());
        }).collect(Collectors.toList());
    }

    /**
     * list需要自身操作如set/removeIf才可以修改函数引用
     */
    private static void selfModifyCollectionPassReference(){
        List<StringBuffer> list = new ArrayList<StringBuffer>(){{
            add(new StringBuffer("one"));
            add(new StringBuffer("two"));
        }};

        passReferenceSuccess(list);

        log.info("self modify collection pass reference {}", list);
    }

    private static void passReferenceSuccess(List<StringBuffer> list) {

        //[three, two]
        list.set(0,new StringBuffer("three"));

        //[three]
        list.removeIf((l)->{
            return "two".equals(l.toString());
        });
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class MyObj{
        private String key;
        private String value;
    }

    private static void modifyObjReference(){
        MyObj myObj = new MyObj("key", "value");

        extract(myObj);

        log.info("{}", myObj);
    }

    private static void extract(MyObj myObj) {
        myObj.setKey("newKey");
        myObj.setValue("newValue");
    }
}
