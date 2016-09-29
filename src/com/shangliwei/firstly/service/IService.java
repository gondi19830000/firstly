package com.shangliwei.firstly.service;

import java.util.List;
import java.util.Map;

public interface IService {

	public void add(Map<String, Object> form, String currentUserId) throws Exception;
	public void update(Map<String, Object> form, String currentUserId) throws Exception;
	public void delete(String id, String currentUserId) throws Exception;
	public Map<String, Object> queryDetail(String id) throws Exception;
	public List<Map<String, Object>> queryList(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception;
	public List<Map<String, Object>> queryPop(Map<String, Object> condition, Map<String, Integer> pagination) throws Exception;
}
