package com.test1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.test1.model.AdReceivable;

@Repository
public class TestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public List<AdReceivable> getAdReceivableList() {
		final String sql = "select  aa.ad_owner_id,sum(aed.income) as receivable"
				+ "  from ad_effect_data aed join ad_app aa on aed.adappid = aa.id where 1=1 "
				+ "  GROUP BY aa.ad_owner_id";
		List<AdReceivable> list = this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<AdReceivable>(AdReceivable.class));
		return list;
	}
}
