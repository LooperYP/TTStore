package com.ithiema.store.dao;

import java.util.List;
import com.ithiema.store.domain.Order;
import com.ithiema.store.domain.OrderItem;
import com.ithiema.store.domain.User;

public interface OrderDAO {

	void saveOrder(Order order) throws Exception;

	void saveOrderItem(List<OrderItem> list) throws Exception;

	void confirmOrderByOid(String oid, String name, String address, String telephone) throws Exception;

	long findTotalRows(String uid) throws Exception;

	List<Order> showOrderByUid(User user, int offset, int pagesize) throws Exception;

	Order payOrderByOid(User user, String oid) throws Exception;

	List<Order> showOrder(String state) throws Exception;

	void updateOrderState(String state, String oid) throws Exception;

	Order showDetailOrder(String oid) throws Exception;
}
