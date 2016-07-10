package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//获取时间---自定义时间格式
import java.util.Date;
import java.text.SimpleDateFormat;

import com.model.bean.BugBean;

/**
 * 未测试
 * @author xj
 * 7/10
 * 第一轮测试
 * by lk
 */
public class BugDao extends BaseDao{
	
	public ArrayList<BugBean> findAllBug() {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "SELECT * FROM bug ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				BugBean bug = new BugBean();
				bug.setId(rst.getString("id"));
				bug.setName(rst.getString("name"));
				bug.setStatus(rst.getString("status"));
				bug.setBug_type(rst.getString("bug_type"));
				bug.setOs(rst.getString("os"));
				bug.setBrowser(rst.getString("browser"));
				bug.setFoundBy(rst.getString("foundBy"));
				bug.setFoundDate(rst.getString("foundDate"));
				bug.setPriority(rst.getString("priority"));
				bug.setSteps(rst.getString("steps"));
				bug.setUsecaseId(rst.getString("usecaseId"));
				bug.setTask_testerId(rst.getString("task_testerId"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//增加 bug时  创建日期为当前日期
	public boolean addBug(BugBean bug) {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		
		String sql = "INSERT INTO bug(id,name,status,bug_type,os,browser,foundBy,foundDate,priority,steps,usecaseId,task_testerId)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bug.getId());
			pstmt.setString(2, bug.getName());
			pstmt.setString(3, bug.getStatus());
			pstmt.setString(4, bug.getBug_type());
			pstmt.setString(5, bug.getOs());
			pstmt.setString(6, bug.getBrowser());
			pstmt.setString(7, bug.getFoundBy());
			pstmt.setString(8, dateNowStr);
			pstmt.setString(9, bug.getPriority());
			pstmt.setString(10, bug.getSteps());
			pstmt.setString(11, bug.getUsecaseId());
			pstmt.setString(12, bug.getTask_testerId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteBug(String id) {
		String sql = "DELETE FROM bug where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	//update --- 有限的数据可以更新
	//所属用例类型 和 所属测试小组 不可更改
	public boolean updateBug(BugBean bug) {
		String sql = "update bug set name=?,status=?,bug_type=?,os=?,browser=?,priority=?,steps=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bug.getName());
			pstmt.setString(2, bug.getStatus());
			pstmt.setString(3, bug.getBug_type());
			pstmt.setString(4, bug.getOs());
			pstmt.setString(5, bug.getBrowser());
			pstmt.setString(6, bug.getPriority());
			pstmt.setString(7, bug.getSteps());
			pstmt.setString(8, bug.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
}
