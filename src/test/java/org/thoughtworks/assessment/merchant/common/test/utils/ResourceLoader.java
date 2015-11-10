package org.thoughtworks.assessment.merchant.common.test.utils;

import java.io.InputStream;

public final class ResourceLoader {
    
    private final Class<?> clazz;
    private final String resource;

    public ResourceLoader(final Class<?> clazz, final String resource) {
        this.clazz = clazz;
        this.resource = resource;
    }
    
    public InputStream getResource(){
        
        return clazz.getResourceAsStream(resource);
    }

}
