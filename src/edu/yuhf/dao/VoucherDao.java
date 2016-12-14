package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Voucher;

@Repository
public interface VoucherDao {
	public List<Voucher> queryAll();
	public Voucher queryById(int id);
	public int countByOccurtime(String occurtime);
	public int buyMaterial(Voucher voucher);
	public int update(Voucher voucher);
	public int delete(int ids[]);
	public int insert(Voucher voucher);
	public List<Voucher> queryByCustomerId(int id);
	public List<Voucher> queryStoreUserNull();
	public List<Voucher> queryByCrIdAndBrNull(int id);
}
