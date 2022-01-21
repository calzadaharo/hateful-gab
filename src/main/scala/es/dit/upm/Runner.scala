package es.dit.upm

import es.dit.upm.graphBuilders.HGGraphBuilder
import es.dit.upm.graphBuilders.UserGraphBuilder
import es.dit.upm.algorithms.DepthFromOriginal
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

//  val source    = new FileSpout(
//    "/home/rodrigo/Examples/hateful-gab/src/main/scala/es/dit/upm/data")
//  val source    = new FileSpout(
//    "/home/rcalzada/data/hateful_gab.csv")
  val source    = new HGSpoutHDFS(
    "hdfs://com31.dit.upm.es:9000/", "/data/rcalzada/datasets/definitive/hateful_gab.csv/")

  val builder   = new HGGraphBuilder()
//  val builder   = new UserGraphBuilder()
  val rg        = RaphtoryGraph[String](source,builder)

//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),timestamp = 42977)
//  rg.pointQuery(DepthFromOriginal(path="/home/rcalzada/output"),42977)

  // Partition 0
//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),timestamp = 3062658)

  // Partition 1
//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),5505216)

  // Whole dataset
//  rg.pointQuery(DepthFromOriginal(path="/home/rcalzada/output"),timestamp = 46417964)

  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------
  // TIME
  //----------------------------------------------------------------------------------------------------
  //----------------------------------------------------------------------------------------------------

  rg.rangeQuery(DepthFromOriginal(path="/home/rcalzada/output/time/hour"),
    start = start, end = end, increment = hour, windows=List(hour))

  rg.rangeQuery(DepthFromOriginal(path="/home/rcalzada/output/time/day"),
    start = start, end = end, increment = day, windows=List(day))

  rg.rangeQuery(DepthFromOriginal(path="/home/rcalzada/output/time/week"),
    start = start, end = end, increment = week, windows=List(week))

  rg.rangeQuery(DepthFromOriginal(path="/home/rcalzada/output/time/month"),
    start = start, end = end, increment = month, windows=List(month))
}
