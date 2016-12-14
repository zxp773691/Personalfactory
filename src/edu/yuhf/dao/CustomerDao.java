package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Customer;
@Repository
public interface CustomerDao {
	public List<Customer> queryAll();
	public Customer queryById(int id);
	public List<Customer> queryByName(String name);
	public int update(Customer customer);
	public int delete(int ids[]);
	public int insert(Customer customer);
	public Customer queryBySingleName(String name);
}
