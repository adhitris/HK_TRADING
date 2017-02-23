package com.hk.common.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



public class CheckDuplicate {

	public static <T> boolean containDuplicate(T...elements) {
        final Set<T> set = new HashSet<T>();
        Collections.addAll(set, elements);
        return set.size() < elements.length;
    }
	
}
