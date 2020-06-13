package com.city.common.bio.proxy;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 请求传输对象
 */
public class RpcRequest implements Serializable {

    private String className;
    private String method;
    private Object[] parameters;
    //参数类型
    private Class[] types;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", method='" + method + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
