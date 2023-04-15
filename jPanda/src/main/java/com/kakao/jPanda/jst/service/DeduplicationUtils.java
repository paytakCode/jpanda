package com.kakao.jPanda.jst.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class DeduplicationUtils {
	
	public static <T> Predicate<T> distinctByKey( Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new HashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
}