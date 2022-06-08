package com.kc.pingpang.platform.freamwork.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public enum Bool {
	Y(true, "Y", "是"), 
    N(false, "N", "否");

    private static final Map<Boolean, Bool> value2Bool;
    private static final Map<String, Bool> code2Bool;
    private static Map<String, Bool> name2Bool;


    private boolean value;
    private String code;
    private String name;

    static {
        value2Bool = new HashMap<Boolean, Bool>();
        code2Bool = new HashMap<String, Bool>();
        
        for (Bool bool : Bool.values()) {
            value2Bool.put(bool.getValue(), bool);
            code2Bool.put(bool.getCode(), bool);
        }
    }

    public static Bool fromValue(Boolean value) {
    	if (value == null) {
    		return null;
    	}
    	
        return value2Bool.get(value);
    }

    public static Bool fromCode(String code) {
    	if (code == null) {
    		return null;
    	} else {
            code = code.toUpperCase();
        }
        
        return code2Bool.get(code);
    }
    
    public static Bool fromName(String name) {
    	if(name2Bool == null){
    		name2Bool = new ConcurrentHashMap<String, Bool>();
    		for (Bool bool : Bool.values()) {               
                name2Bool.put(bool.getName(), bool);
            }
    	}
        return name2Bool.get(name);
    }

    Bool(boolean value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public boolean getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

