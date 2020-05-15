package me.donghun.springdatajpainflearn;

import javax.persistence.Embeddable;

@Embeddable
public class Address { // value 타입

    private String street;
    private String city;
    private String state;

}
