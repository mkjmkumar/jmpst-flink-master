package com.mukesh.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * Flink Wordcount with Sockets
 */
public class App {

	public static void main(String[] args) throws Exception {
		/**
		 * Preparing the streaming enviornment
		 */
		// Create the execution environment 
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		/**
		 * Opening the socket with the batch size of 5 secs
		 */
		// DataStream<Tuple2<String, Integer>> dataStream =
		// env.socketTextStream("localhost", 9999).flatMap(new Splitter())
		// .keyBy(0).timeWindow(Time.seconds(5)).sum(1);

		/**
		 * Opening the socket without a time window, so we are real-time
		 */
		// Create the Datastream consuming data using a socketTextStream 
		DataStream<Tuple2<String, Integer>> dataStream = env.socketTextStream("victoria.com", 9099).flatMap(new Splitter())
				.keyBy(0).sum(1);

		dataStream.print();

		env.execute("Window WordCount");

	}

	/**
	 * Splits the lines into words
	 * 
	 * @author sascha.kerbler
	 *
	 */
	public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
		@Override
		public void flatMap(String sentence, Collector<Tuple2<String, Integer>> out) throws Exception {
			for (String word : sentence.split(" ")) {
				out.collect(new Tuple2<String, Integer>(word, 1));
			}
		}
	}
}
