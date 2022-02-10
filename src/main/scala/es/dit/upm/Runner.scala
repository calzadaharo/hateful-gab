package es.dit.upm

import es.dit.upm.graphBuilders.HGGraphBuilder
import es.dit.upm.graphBuilders.UserGraphBuilder
import es.dit.upm.algorithms.DepthFromOriginal
import es.dit.upm.algorithms.DepthTimestampFromOriginal
import com.raphtory.core.build.server.RaphtoryGraph
import es.dit.upm.spouts.HGSpoutHDFS
//import com.raphtory.algorithms.{ConnectedComponents}
import com.raphtory.spouts.FileSpout

object Runner extends App {
  val start = 0

  val end = 46417964

  val hour = 3600

  val day = 3600*24

  val week = 3600*24*7

  val month = 3600*24*30

  val source    = new FileSpout(
    "/home/rodrigo/Examples/hateful-gab/src/main/scala/es/dit/upm/data/",
    "part-00000-hateful_gab.csv")
//  val source    = new FileSpout(
//    "/home/rcalzada/data/hateful_gab.csv")
//  val source    = new HGSpoutHDFS(
//    "hdfs://com31.dit.upm.es:9000/", "/data/rcalzada/datasets/definitive/hateful_gab.csv/")

  val builder   = new HGGraphBuilder()
//  val builder   = new UserGraphBuilder()
  val rg        = RaphtoryGraph[String](source,builder)

  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------
  // STATIC
  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------

  // Testing algorithms
//  rg.pointQuery(DepthTimestampFromOriginal(path="/home/rodrigo/output"),timestamp = 42977)
//  rg.pointQuery(DepthFromOriginal(path="/home/rcalzada/output"),42977)

  // Partition 0
  rg.pointQuery(DepthTimestampFromOriginal(path="/home/rodrigo/output"),timestamp = 3062658)

  // Partition 1
//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),5505216)

  // Whole dataset
//  rg.pointQuery(DepthFromOriginal(path="/home/rcalzada/output/new"),timestamp = 46417964)

  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------
  // TIME - INTERVALS
  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------

//	for (i <- 0 to 3) {
//		if(i == 0) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/hours"),
//				start = start, end = end, increment = hour, windows = List(hour))
//			Thread.sleep(5000)
//		}
//		else if(i == 1) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/day"),
//				start = start, end = end, increment = day, windows = List(day))
//			Thread.sleep(5000)
//		}
//		else if(i == 2) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/week"),
//				start = start, end = end, increment = week, windows = List(week))
//			Thread.sleep(5000)
//		}
//		else if(i == 3) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/month"),
//				start = start, end = end, increment = month, windows = List(month))
//			Thread.sleep(5000)
//		}
//	}

//	for (i <- 0 to 1) {
//		if(i == 0) {
//		  rg.rangeQuery(DepthFromOriginal(path="/home/rodrigo/output/test1"),
//		    start=start, end = 3062658, increment=hour, windows=List(hour))
//		  Thread.sleep(5000)
//	    	}
//		if(i == 1) {
//		  rg.rangeQuery(DepthFromOriginal(path="/home/rodrigo/output/test2"),
//		    start=start, end = 3062658, increment=day, windows=List(day))
//		  Thread.sleep(5000)
//	    	}
//	  }

	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------
	// TIME - WINDOW
	//----------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------

//	for (i <- 0 to 3) {
//		if(i == 0) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/hours"),
//				start = start, end = end, increment = hour)
//			Thread.sleep(5000)
//		}
//		else if(i == 1) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/day"),
//				start = start, end = end, increment = day)
//			Thread.sleep(5000)
//		}
//		else if(i == 2) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/week"),
//				start = start, end = end, increment = week)
//			Thread.sleep(5000)
//		}
//		else if(i == 3) {
//			rg.rangeQuery(DepthFromOriginal(path = "/home/rcalzada/output/time/month"),
//				start = start, end = end, increment = month)
//			Thread.sleep(5000)
//		}
//	}

//	for (i <- 0 to 1) {
//		if(i == 0) {
//			rg.rangeQuery(DepthFromOriginal(path="/home/rodrigo/output/test1"),
//				start=start, end = 3062658, increment=hour)
//			Thread.sleep(5000)
//		}
//		if(i == 1) {
//			rg.rangeQuery(DepthFromOriginal(path="/home/rodrigo/output/test2"),
//				start=start, end = 3062658, increment=day)
//			Thread.sleep(5000)
//		}
//	}
}
