package com.shangliwei.firstly.service;

import java.util.List;
import java.util.Map;

public interface IService {

	public void add(Map<String, Object> form, String currentUserId);
	public void update(Map<String, Object> form, String currentUserId);
	public void delete(String id, String currentUserId);
	public Map<String, Object> queryDetail(String id);
	public List<Map<String, Object>> queryList(Map<String, Object> condition, Map<String, Integer> pagination);
	public List<Map<String, Object>> queryPop(Map<String, Object> condition, Map<String, Integer> pagination);
}
