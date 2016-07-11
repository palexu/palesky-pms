package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.model.bean.DemandBean;

/**
 * 未测试
 * @author xj
 * demand 共有10项数据
 * 7/10 合理性修改
 *
 */
public class DemandDao extends BaseDao{
	
	public ArrayList<DemandBean> findAllDemand() {
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql = "SELECT * FROM demand ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setLastEditedDate(rst.getString("lastEditedDate"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
				demand.setProject_id(rst.getString("project_id"));
				list.add(demand);
				System.out.println(demand.toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    
	//通过id获取需求信息
	public DemandBean getDemand(String id) {
		String sql = "SELECT * FROM demand where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			DemandBean demand = new DemandBean();
			while (rst.next()) {
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setLastEditedDate(rst.getString("lastEditedDate"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
				demand.setProject_id(rst.getString("project_id"));
			}
			return demand;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}

	
	public boolean addDemand(DemandBean demand) {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		
		String sql = "INSERT INTO demand(id,name,status,createdBy,createdDate,endDate,explain,lastEditedDate,confirmedBy,project_id)VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, demand.getId());
			pstmt.setString(2, demand.getName());
			pstmt.setString(3, demand.getStatus());
			pstmt.setString(4, demand.getCreatedBy());
			pstmt.setString(5, dateNowStr);
			pstmt.setString(6, demand.getEndDate());
			pstmt.setString(7, demand.getExplain());
			pstmt.setString(8, dateNowStr);
			pstmt.setString(9, demand.getConfirmedBy());
			pstmt.setString(10, demand.getProject_id());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteDemand(String id) {
		String sql = "DELETE FROM demand where id=?";
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

	
	public boolean updateDemand(DemandBean demand) {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		String sql = "update demand set id=?,name=?,status=?,endDate=?,explain=?,lastEditedDate=?,confirmedBy=? where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, demand.getId());
			pstmt.setString(2, demand.getName());
			pstmt.setString(3, demand.getStatus());
			pstmt.setString(4, demand.getEndDate());
			pstmt.setString(5, demand.getExplain());
			pstmt.setString(6, dateNowStr);
			pstmt.setString(7, demand.getConfirmedBy());
			pstmt.setString(8, demand.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
}