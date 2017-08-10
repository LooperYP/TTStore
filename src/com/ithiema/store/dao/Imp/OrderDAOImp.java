package com.ithiema.store.dao.Imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;
import com.ithiema.store.dao.OrderDAO;
import com.ithiema.store.domain.Order;
import com.ithiema.store.domain.OrderItem;
import com.ithiema.store.domain.Product;
import com.ithiema.store.domain.User;
import com.ithiema.store.utils.JDBCUtils;

public class OrderDAOImp implements OrderDAO {

	@Override
	public void saveOrder(Order order) throws Exception {
		QueryRunner qRunner = new QueryRunner();
		Connection conn = JDBCUtils.getConnectionTL();

		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] params = { order.getOid(), //
				order.getOrdertime(), //
				order.getTotal(), //
				order.getState(), //
				order.getAddress(), //
				order.getName(), //
				order.getTelephone(), //
				order.getUser().getUid() //
		};
		qRunner.update(conn, sql, params);
	}

	@Override
	public void saveOrderItem(List<OrderItem> list) throws Exception {
		QueryRunner qRunner = new QueryRunner();
		Connection conn = JDBCUtils.getConnectionTL();

		for (OrderItem orderItem : list) {
			String sql = "insert into orderitem values(?,?,?,?,?)";
			Object[] params = { orderItem.getItemid(), //
					orderItem.getCount(), //
					orderItem.getSubtotal(), //
					orderItem.getProduct().getPid(), //
					orderItem.getOrder().getOid(), //
			};
			qRunner.update(conn, sql, params);
		}
	}

	@Override
	public void confirmOrderByOid(String oid, String name, String address, String telephone) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "update orders set state=1,address=?,name=?,telephone=? where oid=?";
		qRunner.update(sql, address, name, telephone, oid);
	}

	@Override
	public long findTotalRows(String uid) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select count(1) from orders where uid=?";
		return (long) qRunner.query(sql, new ScalarHandler(), uid);
	}

	@Override
	@Test
	public List<Order> showOrderByUid(User user, int offset, int pagesize) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from orders where uid=? limit ?,?";
		List<Order> listOrder = qRunner.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), offset,
				pagesize);

		for (Order order : listOrder) {
			List<OrderItem> listOrderItem = new ArrayList<OrderItem>();

			sql = "SELECT o.oid,i.count,i.subtotal,p.pname,p.pimage,p.shop_price FROM orders o,orderitem i,product p WHERE o.oid=i.oid AND i.pid=p.pid AND o.uid = ? and o.oid = ?";
			List<Map<String, Object>> list = qRunner.query(sql, new MapListHandler(), user.getUid(), order.getOid());
			for (Map<String, Object> map : list) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();

				// String pname = (String) map.get("pname");
				// product.setPname(pname );

				BeanUtils.populate(product, map);
				BeanUtils.populate(orderItem, map);
				orderItem.setProduct(product);
				orderItem.setOrder(order);

				listOrderItem.add(orderItem);
			}
			order.setUser(user);
			order.setList(listOrderItem);
		}

		return listOrder;
	}

	@Override
	public Order payOrderByOid(User user, String oid) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from orders where oid=?";
		Order order = qRunner.query(sql, new BeanHandler<Order>(Order.class),oid);
		
		List<OrderItem> listOrderItem = new ArrayList<OrderItem>();
		
		sql = "SELECT o.oid,i.count,i.subtotal,p.pname,p.pimage,p.shop_price FROM orders o,orderitem i,product p WHERE o.oid=i.oid AND i.pid=p.pid AND o.uid = ? and o.oid = ?";
		List<Map<String, Object>> list = qRunner.query(sql, new MapListHandler(),user.getUid(), oid);
		for (Map<String, Object> map : list) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();

			// String pname = (String) map.get("pname");
			// product.setPname(pname );

			BeanUtils.populate(product, map);
			BeanUtils.populate(orderItem, map);
			orderItem.setProduct(product);
			orderItem.setOrder(order);

			listOrderItem.add(orderItem);
		}
		order.setList(listOrderItem);
		order.setUser(user);
		return order;
	}

	@Override
	public List<Order> showOrder(String state) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		if ("-1".equals(state)) {
			String sql = "select * from orders";
			return qRunner.query(sql, new BeanListHandler<Order>(Order.class));
		} else {
			String sql = "select * from orders where state=?";
			return qRunner.query(sql, new BeanListHandler<Order>(Order.class),state);
		}
	}

	@Override
	public void updateOrderState(String state, String oid) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		int stateInt = Integer.parseInt(state) + 1;
		String sql = "update orders set state=? where oid=?";
		qRunner.update(sql,stateInt,oid);
	}

	@Override
	public Order showDetailOrder(String oid) throws Exception {
		QueryRunner qRunner = new QueryRunner(JDBCUtils.getDataSourse());
		String sql = "select * from orders where oid=?";
		Order order = qRunner.query(sql, new BeanHandler<Order>(Order.class),oid);
		
		List<OrderItem> listOrderItem = new ArrayList<OrderItem>();
		
		sql = "SELECT o.oid,i.count,i.subtotal,p.pname,p.pimage,p.shop_price FROM orders o,orderitem i,product p WHERE o.oid=i.oid AND i.pid=p.pid and o.oid = ?";
		List<Map<String, Object>> list = qRunner.query(sql, new MapListHandler(),oid);
		for (Map<String, Object> map : list) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();

			// String pname = (String) map.get("pname");
			// product.setPname(pname );

			BeanUtils.populate(product, map);
			BeanUtils.populate(orderItem, map);
			orderItem.setProduct(product);
			orderItem.setOrder(order);

			listOrderItem.add(orderItem);
		}
		order.setList(listOrderItem);
		return order;
	}

}
