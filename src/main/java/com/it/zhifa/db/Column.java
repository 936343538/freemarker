package com.it.zhifa.db;
/**
 * 列对象
 */
public class Column {

	/**
	 * 列名称
	 */
	private String columnName;
	/**
	 * 列名称(处理后的列名称)
	 */
	private String columnName2;
	/**
	 * 列类型
	 */
	private String columnType;
	/**
	 * 列数据库类型
	 */
	private String columnDbType;
	/**
	 * 列备注D
	 */
	private String columnComment;
	/**
	 * 是否是主键
	 */
	private String columnKey;
	/**
	 * 是否是主键
	 */
	private int columnSize;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName2() {
		return columnName2;
	}

	public void setColumnName2(String columnName2) {
		this.columnName2 = columnName2;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnDbType() {
		return columnDbType;
	}

	public void setColumnDbType(String columnDbType) {
		this.columnDbType = columnDbType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = Integer.parseInt(columnSize);
	}
}
