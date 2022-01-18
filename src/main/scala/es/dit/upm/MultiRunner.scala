package es.dit.upm

import es.dit.upm.spouts.HGSpoutHDFS
import es.dit.upm.graphBuilders.HGGraphBuilder

import com.raphtory.core.build.server.RaphtoryService
import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.components.spout.Spout
import com.raphtory.spouts.FileSpout

object MultiRunner extends RaphtoryService[String]{

//  override def defineSpout: Spout[String] =
//    new FileSpout("/home/rodrigo/Examples/hateful-gab/src/main/scala/es/dit/upm/data")

  override def defineSpout: Spout[String] =
    new HGSpoutHDFS("hdfs://com31.dit.upm.es:9000/",
      "/data/rcalzada/datasets/definitive/hateful_gab.csv/")

  override def defineBuilder: GraphBuilder[String] =
    new HGGraphBuilder()
}
