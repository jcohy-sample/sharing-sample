package com.demo.commons;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.demo.commons.annotations.ShardingField;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * Copyright: Copyright (c) 2023 <a href="https://www.jcohy.com" target="_blank">jcohy.com</a>
 * <p> Description:
 *
 * @author jiac
 * @version 2023.0.1 2023/11/9:01:29
 * @since 2023.0.1
 */
public class Utils {


	public static Map<String, Object> toMap(String[] keys, Object[] values) {
		return IntStream.range(0, keys.length)
				.boxed()
				.collect(Collectors.toMap(
						i -> keys[i],
						i -> values[i],
						(v1, v2) -> v1,
						LinkedHashMap::new
				));
	}

	public static String toPhysicalTableName(String text) {
		StringBuilder builder = new StringBuilder(text.replace('.', '_'));
		for (int i = 1; i < builder.length() - 1; i++) {
			if (isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
				builder.insert(i++, '_');
			}
		}
		return builder.toString().toLowerCase(Locale.ROOT);
	}
	private static boolean isUnderscoreRequired(final char before, final char current, final char after) {
		return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
	}

	public static String getShardingField(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			// 判断字段上是否有指定的注解
			ShardingField annotation = AnnotationUtils.findAnnotation(field, ShardingField.class);
			if (annotation != null) {
				// todo 仅支持一个字段,匹配到第一个标有 ShardingField 注解的就返回
				return field.getName();
			}
		}
		return "";
	}

	public static boolean isNotNull(Object... objects) {
		for (Object object : objects) {
			if(Objects.isNull(object)) {
				return false;
			}
		}
		return true;
	}
}
