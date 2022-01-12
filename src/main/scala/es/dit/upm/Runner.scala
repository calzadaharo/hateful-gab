package es.dit.upm

import es.dit.upm.graphBuilders.HGGraphBuilder
import es.dit.upm.algorithms.DepthFromOriginal
import com.raphtory.core.build.server.RaphtoryGraph
import es.dit.upm.spouts.HGSpoutHDFS
//import com.raphtory.algorithms.{ConnectedComponents}
import com.raphtory.spouts.FileSpout

object Runner extends App {
  val source    = new FileSpout(
    "/home/rodrigo/Examples/hateful-gab/src/main/scala/es/dit/upm/data")
//  val source    = new HGSpoutHDFS(
//    "hdfs://com31.dit.upm.es:9000/", "/data/rcalzada/datasets/definitive/hateful_gab.csv/")

  val builder   = new HGGraphBuilder()
  val rg        = RaphtoryGraph[String](source,builder)

//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),42977)
  rg.pointQuery(DepthFromOriginal(path="/home/rcalzada/output"),42977)

  // Partition 0
//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),timestamp = 3062658)

//   Partition 1
//  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),5505216)
}
