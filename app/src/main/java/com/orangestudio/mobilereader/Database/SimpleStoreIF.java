package com.orangestudio.mobilereader.Database;

import java.util.List;
import java.util.Map;

public interface SimpleStoreIF {
	void put(String key, String value);

	void put(String key, String value, int expiresInSecs);

	String get(String key);

	Map<String, String> getMultiKeys(List<String> keys);

	Map<String, String> getMultiKeys(List<String> keys,
											List<String> keys_miss);

	void remove(String key);

	void removeAll();

	Map<String, String> getAllKey();

	void close();
}
