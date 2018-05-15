package util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 此接口是所有DAO层接口的父接口
 * @author HSW
 * 包含如下功能：<br>
 * <li>增加一条记录</li>
 * <li>批量删除记录</li>
 * <li>修改一条记录</li>
 * <li>查询一条记录</li>
 * <li>查询所有数据</li>
 * <li>分页查询中查询记录条数</li>
 * <li>分页查询中查询符合条件的所有记录</li>
 * @param <K> 表的主键类型（ID）
 * @param <V> 表所对应的VO类类型
 */
public interface IDAO<K,V> {
	/**
	 * 向表中差入一条记录
	 * @param vo 插入记录的内容
	 * @return sql执行成功则返回true，失败返回false
	 * @throws SQLException SQL执行异常
	 */
	public boolean doCreate(V vo) throws SQLException ;
	
	/**
	 * 批量删除表中的数据
	 * @param ids 要删除的所有数据对应的id
	 * @return 所有数据全部删除成功返回true，否则返回false
	 * @throws SQLException SQL执行异常
	 */
	public boolean doRemoveBatch(Set<K> ids) throws SQLException ;
	
	/**
	 * 修改表中的一条记录
	 * @param vo 包含要修改数据的id，以及要修改到的内容
	 * @return sql执行成功返回true
	 * @throws SQLException SQL执行异常
	 */
	public boolean doUpdate(V vo) throws SQLException ;
	
	/**
	 * 格局ID查询表中的一条记录
	 * @param id 要查询数据的ID
	 * @return 查询到的数据以VO形式返回
	 * @throws SQLException SQL执行异常
	 */
	public V findById(K id) throws SQLException ;
	
	/**
	 * 查询全部记录
	 * @return 以List<V>的形式该表中所有的记录
	 * @throws SQLException SQL执行异常
	 */
	public List<V> findAll() throws SQLException ;
	
	/**
	 * 用于分页查询，根据列名和关键字查询记录条数
	 * @param column 列名
	 * @param keyWord 关键字
	 * @return 查询得到的记录条数
	 * @throws SQLException SQL执行异常
	 */
	public Integer getCount(String column, String keyWord) throws SQLException ;
	
	/**
	 * 用于分页查询，根据当前页码、每页显示记录行数、列名、关键字查询符合要求的记录
	 * @param currentPage 当前页码
	 * @param lineSize 每页显示的记录行数
	 * @param column 列名
	 * @param keyWord 关键字
	 * @return 以List<V>的形式返回全部查询结果
	 * @throws SQLException SQL执行异常
	 */
	public List<V> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException ;
}
