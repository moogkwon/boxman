package com.spring.bm.purchase.model.service;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
	
	List<Map<String,String>> selectPurList(int cPage, int numPerPage);
	int selectPurCount();
	
	List<Map<String,String>> selectConnList();

}