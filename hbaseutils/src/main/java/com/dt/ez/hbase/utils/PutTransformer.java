package com.dt.ez.hbase.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Put;

/**
 * Interface that takes a map of jdbc field names to values
 * and converts them to a Put command for HBase.
 */
public abstract class PutTransformer {

  private String columnFamily;
  private String rowKeyColumn;

  /**
   * @return the default column family to insert into.
   */
  public String getColumnFamily() {
    return this.columnFamily;
  }

  /**
   * Set the default column family to insert into.
   */
  public void setColumnFamily(String colFamily) {
    this.columnFamily = colFamily;
  }

  /**
   * @return the field name identifying the value to use as the row id.
   */
  public String getRowKeyColumn() {
    return this.rowKeyColumn;
  }

  /**
   * Set the column of the input fields which should be used to calculate
   * the row id.
   */
  public void setRowKeyColumn(String rowKeyCol) {
    this.rowKeyColumn = rowKeyCol;
  }

  /**
   * Returns a list of Put commands that inserts the fields into a row in HBase.
   * @param fields a map of field names to values to insert.
   * @return A list of Put commands that inserts these into HBase.
   */
  public abstract List<Put> getPutCommand(Map<String, Object> fields)
      throws IOException;
}


