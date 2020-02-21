package com.sxt.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.sxt.util.JdbcUtil;

/**
 * @ClassName: BaseDao 
 * @Description: 通用数据库数据操作类
 * @author: Mr.T
 * @date: 2020年2月4日 下午4:48:52
 */
public  class BaseDao {
	
	
	/**
	 * @Title: insert
	 * @author: Mr.T   
	 * @date: 2020年2月4日 下午4:59:18 
	 * @Description: 添加数据库 返回自增长的主键
	 * @param sql
	 * @param param
	 * @return
	 * @return: int
	 */
	public  int insert(String sql,Object... param) {
		Connection conn = JdbcUtil.getConn();
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			//获取指令对象，且支持获取数据库生成主键值
			prep = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			//设置参数
			//设置参数 根据循环设置参数
			for (int i = 0; i < param.length; i++) {
				//PreparedStatement 设置参数是 第一个? 下标为  1 第二个是2
				prep.setObject(i+1, param[i]);
			}
			prep.executeUpdate();
			//获取生成主键值
			rs = prep.getGeneratedKeys();
			//偏移结果集的指针
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, prep, rs);
		}
		return 0;
	}
	
	
	/**
	 * @Title: update
	 * @author: Mr.T   
	 * @date: 2020年2月3日 下午6:18:56 
	 * @Description: 数据库表数据的更新操作
	 * @param sql
	 * @param param
	 * @return
	 * @return: boolean
	 */
	public  boolean update(String sql,Object... param) {
		int m = 0;
		Connection conn = JdbcUtil.getConn();
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			//设置参数 根据循环设置参数
			for (int i = 0; i < param.length; i++) {
				//PreparedStatement 设置参数是 第一个? 下标为  1 第二个是2
				prep.setObject(i+1, param[i]);
			}
			m = prep.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			JdbcUtil.close(conn, prep);
		}
		return m > 0?true:false;
	}
	
	
	
	public  <T> List<T>  selectList(String sql,Class<T> cls,Object... param) {
		Connection conn = JdbcUtil.getConn();
		List<T> list = new ArrayList<T>();
		PreparedStatement prep = null;
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			// set parameters
			for (int i = 0; i < param.length; i++) {
				prep.setObject(i+1, param[i]);
			}
			// query result set
			rs = prep.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			// get column number which will be used in following loop
			int columnCount = metaData.getColumnCount();
			while(rs.next()) {
				// Generate object
				T t = cls.getConstructor().newInstance();
				// set object fields using reflection
				for (int i = 1; i <= columnCount; i++) {
					// Get column name
					String columnLabel = metaData.getColumnLabel(i);
					// get column value
					Object object = rs.getObject(columnLabel);
					// set value  
					//find attribute by using column name
					Field field = cls.getDeclaredField(columnLabel);
					//2. set attribute value
					field.setAccessible(true);
					field.set(t, object);
					field.setAccessible(false);
				}
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, prep, rs);
		}
		return list;
	}

	public  <T> T selectOne(String sql,Class<T> cls,Object... param) {
		List<T> list = selectList(sql, cls, param);
		if(list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * @Title: selectPage
	 * @author: Mr.T   
	 * @date: 2020年2月4日 下午5:25:20 
	 * @Description: 通用的分页查询
	 *	select * from user  LIMIT 4,2;   --  第三页的sql   (页码-1) * 每页的行数
	 * @param <T>
	 * @param sql  查询的sql
	 * @param cls  对应的类
	 * @param pageNo  页码
	 * @param pageSize  每页行数
	 * @param param  sql中参数
	 * @return
	 * @return: List<T>
	 */
	public  <T> List<T> selectPage(String sql,Class<T> cls,Integer pageNo,Integer pageSize,Object... param){
		/*
		 	如果我们一共7条数据   且 每页2条    4 页    pageNo值为  5    传进来的pageNo  不能大于最大页码值
		 	如果16条数据  每页2条 一共多少页
		 	
		 	一共多少页 ?
		 	最大的页码值：
		 	
		 	计算最大页码值 ----> 获取符合条件的行数
		 */
		//获取总行数
		int total = selectCount(sql, param);
		//计算最大页码 总页数  若此时 total 的值为  0  则 totalPage值为0 
		int totalPage =  total % pageSize == 0?total/pageSize:total/pageSize+1;
		//pageNo   页码的范围:  1  -----  totalPage
		//如果页码比最大页码大，则默认为最大页码  若totalPage 为0   那么pageNo 一定大于  0   则最终 pageNo = 0 pageNo 最小应该为 1
		if (pageNo > totalPage) {
			pageNo = totalPage;
		}
		// 如果页码比1小  则 默认为  1
		if(pageNo < 1) {
			pageNo = 1;
		}
		//计算分页的偏移量
		int offset = (pageNo - 1) * pageSize;
		StringBuffer sb = new StringBuffer(sql);
		sb.append("  limit ");
		sb.append(offset);
		sb.append(",");
		sb.append(pageSize);
		sql = sb.toString();
		System.out.println("sql为:"+sql);
		
		List<T> list = selectList(sql,cls,param);
		
		return list;
	}
	
	
	/**
	 * @Title: selectCount
	 * @author: Mr.T   
	 * @date: 2020年2月4日 下午5:31:08 
	 * @Description: 获取符合条件的总行数
	 * @param sql
	 * @param param
	 * @return
	 * @return: int
	 */
	private  int selectCount(String sql,Object...param) {
		StringBuffer sb = new StringBuffer("select count(1) from (");
		sb.append(sql);
		sb.append(") rs");
		sql = sb.toString();
		PreparedStatement prep = null;
		Connection conn = JdbcUtil.getConn();
		ResultSet rs = null;
		try {
			prep = conn.prepareStatement(sql);
			//设置查询参数
			for (int i = 0; i < param.length; i++) {
				prep.setObject(i+1, param[i]);
			}
			rs = prep.executeQuery();
			rs.next();
			//获取符合条件总行数
			return rs.getInt(1);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, prep, rs);
		}
		return 0;
	}
	
	
	
}
