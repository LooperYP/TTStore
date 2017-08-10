package com.itheima.test;

import org.junit.Test;

import com.ithiema.store.domain.TestBean;

public class TestDemo {

	@Test
	public void test1() {
		TestBean bean = new TestBean();
		bean.setBl(false);
		bean.setI(1);
		bean.setStr("str");
		bean.setTest("test");
	}
}
