package es.dit.upm

import es.dit.upm.graphBuilders.HGGraphBuilder
import es.dit.upm.algorithms.DepthFromOriginal
import com.raphtory.core.build.server.RaphtoryGraph
import com.raphtory.algorithms.{ConnectedComponents}
import com.raphtory.spouts.FileSpout

object Runner extends App {
  val source    = new FileSpout("src/main/scala/es/dit/upm/data",
    "part-00000-hateful_gab.csv")
  val builder   = new HGGraphBuilder()
  val rg        = RaphtoryGraph[String](source,builder)

  rg.pointQuery(DepthFromOriginal(path="/home/rodrigo/output"),42977)
}
