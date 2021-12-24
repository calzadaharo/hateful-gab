package es.dit.upm

import com.raphtory.algorithms.ConnectedComponents
import com.raphtory.core.build.client.RaphtoryClient
import es.dit.upm.algorithms.DepthFromOriginal

object Client extends App {
  val client = new RaphtoryClient("localhost:1600",1700)
  client.pointQuery(DepthFromOriginal("/home/rodrigo/output/partitioned"),42977)
}
