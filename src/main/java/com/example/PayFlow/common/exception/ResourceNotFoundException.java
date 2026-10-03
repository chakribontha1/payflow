package com.example.PayFlow.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final String resourceName;
    private final Object identifer;
    public ResourceNotFoundException(String resourceName, Object identifer) {
        super("Resource " + resourceName + " not found" + identifer);
        this.resourceName = resourceName;
        this.identifer = identifer;
    }
}
