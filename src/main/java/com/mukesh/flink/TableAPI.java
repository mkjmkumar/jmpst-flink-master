package com.mukesh.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.table.TableEnvironment;
import org.apache.flink.api.table.Table;

/**
 * Creates a Flink in memory table and filters the data
 * 
 * @author mukesh.kumar
 *
 */
public class TableAPI {

	public static void main(String[] args) throws Exception {
		/**
		 * Creates the execution environment
		 */
		ExecutionEnvironment env = ExecutionEnvironment.createCollectionsEnvironment();
		TableEnvironment tableEnv = new TableEnvironment();

		/**
		 * Creates the input data and fills it into a table
		 */
		DataSet<WC> input = env.fromElements(new WC("Hello", 1), new WC("Ciao", 1), new WC("Hello", 1));
		Table table = tableEnv.fromDataSet(input);
		/**
		 * Filters the table data
		 */
		Table filtered = table.groupBy("word").select("word.count as count, word").filter("count = 2");

		/**
		 * Parses the data into a WC object and stores it into a dataset
		 */
		DataSet<WC> result = tableEnv.toDataSet(filtered, WC.class);
		result.print();
	}

	/**
	 * Dummy class for the word count
	 * 
	 * @author mukesh.kumar
	 */
	public static class WC {
		public String word;
		public int count;

		// Public constructor to make it a Flink POJO
		public WC() {

		}

		public WC(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return "WC " + word + " " + count;
		}
	}
}
