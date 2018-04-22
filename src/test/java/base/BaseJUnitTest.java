/*
 * @(#)BaseJUnitTest.java 2017年9月28日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author zhaomy07
 * @date 2017年9月28日 下午1:37:10
 * @version V1.0.0
 * description：
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:conf/spring-mybatis.xml"}) 
public class BaseJUnitTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
}
