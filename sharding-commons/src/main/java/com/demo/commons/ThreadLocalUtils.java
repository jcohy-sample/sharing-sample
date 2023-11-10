package com.demo.commons;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:21:33
 * @since 2023.0.1
 */
public class ThreadLocalUtils {

	private static final ThreadLocal<Map<String, Object>> threadLocal = ThreadLocal.withInitial(() -> new HashMap<>(4));

	public static Map<String, Object> getThreadLocal() {
		return threadLocal.get();
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		Map<String, Object> map = threadLocal.get();
		return (T) map.get(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String key, T defaultValue) {
		Map<String, Object> map = threadLocal.get();
		return map.get(key) == null ? defaultValue : (T) map.get(key);
	}

	public static void set(String key, Object value) {
		Map<String, Object> map = threadLocal.get();
		map.put(key, value);
	}

	public static void set(Map<String, Object> keyValueMap) {
		Map<String, Object> map = threadLocal.get();
		map.putAll(keyValueMap);
	}

	public static void remove() {
		threadLocal.remove();
	}

	@SuppressWarnings("unchecked")
	public static <T> T remove(String key) {
		Map<String, Object> map = threadLocal.get();
		return (T) map.remove(key);
	}
}
