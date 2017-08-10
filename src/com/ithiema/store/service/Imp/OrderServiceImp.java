package com.ithiema.store.service.Imp;

import java.sql.SQLException;
import java.util.List;
import com.ithiema.store.dao.OrderDAO;
import com.ithiema.store.dao.Imp.OrderDAOImp;
import com.ithiema.store.domain.Order;
import com.ithiema.store.domain.OrderItem;
import com.ithiema.store.domain.PageBean;
import com.ithiema.store.domain.User;
import com.ithiema.store.service.OrderService;
import com.ithiema.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	@Override
	public void saveOrder(Order order) throws Exception {
		try {
			JDBCUtils.startTranscation();

			OrderDAO dao = new OrderDAOImp();
			List<OrderItem> list = order.getList();
			dao.saveOrder(order);
			dao.saveOrderItem(list);

			JDBCUtils.commitAndRelease();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JDBCUtils.rollAndRelease();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		}
	}

	@Override
	public void confirmOrderByOid(String oid, String name, String address, String telephone) throws Exception {
		OrderDAO dao = new OrderDAOImp();
		dao.confirmOrderByOid(oid, name, address, telephone);
	}

	@Override
	public PageBean<Order> showOrderByUid(User user, String pnum) throws Exception {
		PageBean<Order> pageBean = new PageBean<>();
		OrderDAO dao = new OrderDAOImp();
		
		int pagesize = 4;
		int currentPageNum = Integer.parseInt(pnum);
		long totalRows = dao.findTotalRows(user.getUid());
		int totalPageNum = (int) Math.ceil(totalRows*1.0f/pagesize);
		
		int offset = (currentPageNum-1)*pagesize;
		List<Order> datas = dao.showOrderByUid(user,offset,pagesize);
		
		pageBean.setCurrentPageNum(currentPageNum);
		pageBean.setTotalRows(totalRows);
		pageBean.setTotalPageNum(totalPageNum);
		pageBean.setDatas(datas);

		return pageBean;
	}

	@Override
	public Order payOrderByOid(User user, String oid) throws Exception {
		OrderDAO dao = new OrderDAOImp();
		return dao.payOrderByOid(user,oid);
	}

	@Override
	public List<Order> showOrder(String state) throws Exception {
		OrderDAO dao = new OrderDAOImp();
		return dao.showOrder(state);
	}

	@Override
	public List<Order> updateOrderState(String state, String oid) throws Exception {
		OrderDAO dao = new OrderDAOImp();
		dao.updateOrderState(state,oid);
		return dao.showOrder(Integer.parseInt(state)+1+"");
	}

	@Override
	public Order showDetailOrder(String oid) throws Exception {
		OrderDAO dao = new OrderDAOImp();
		return dao.showDetailOrder(oid);
	}

}
