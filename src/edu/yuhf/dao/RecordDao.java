package edu.yuhf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.yuhf.entity.Record;
import edu.yuhf.entity.RecordInfo;

@Repository
public interface RecordDao {
	public List<Record> queryAll();
	public Record queryById(int id);
	public int insert(Record record);
	public int update(Record record);
	public int delete(int ids[]);
	public List<RecordInfo> queryByFrName(String name);
	public List<RecordInfo> queryRecordInfo();
	public RecordInfo queryInfoById(int id);
}
