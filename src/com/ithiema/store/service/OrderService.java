package com.ithiema.store.service;

import java.util.List;

import com.ithiema.store.domain.Order;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.User;

public interface OrderService {

	void saveOrder(Order order) throws Exception;

	void confirmOrderByOid(String oid, String name, String address, String telephone) throws Exception;

	PageBean<Order> showOrderByUid(User user, String pnum) throws Exception;

	Order payOrderByOid(User user, String oid) throws Exception;

	List<Order> showOrder(String state) throws Exception;

	List<Order> updateOrderState(String state, String oid) throws Exception;

	Order showDetailOrder(String oid) throws Exception;

}
