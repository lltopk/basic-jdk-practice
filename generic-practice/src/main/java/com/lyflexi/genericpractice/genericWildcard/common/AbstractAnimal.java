package com.lyflexi.genericpractice.genericWildcard.common;

import lombok.Data;

@Data
public abstract class AbstractAnimal implements IAnimalAction
{
    public String name;
}
