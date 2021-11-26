package es.dit.upm.algorithms

import com.raphtory.core.model.algorithm.{GraphAlgorithm, GraphPerspective, Row}

class DepthFromOriginal (path: String) extends GraphAlgorithm{
  override def algorithm(graph: GraphPerspective): Unit = {
    graph
      .filter (
        vertex => true
      )
      .step ({
        vertex =>
          if (vertex.getAllNeighbours().length == 0) {
            vertex.setState("delete", true)
            vertex voteToHalt
          } else if (vertex.getAllNeighbours().length > 0 &&
            vertex.getPropertyOrElse("type",null) == "original") {
            vertex.setState("level",0)
            vertex.setState("cascade",vertex.ID)
            vertex.messageAllNeighbours(vertex.ID,0)
            vertex voteToHalt
          }
      })
      .iterate ({
        vertex =>
          val messages = vertex.messageQueue[(Long,Int)]
          println("HEREEEEEEE")
          println(messages)
          println(messages(0)._1)

          //          val level = messages(0)(1) + 1
          //          vertex.setState("cascade", cascade)
          //          vertex.setState("level",level)
          vertex voteToHalt
      }, iterations = 1000, executeMessagedOnly = true)
      .select ({ vertex =>
        Row(vertex.ID, vertex.getStateOrElse("delete",false),
          vertex.getStateOrElse("cascade",null),
          vertex.getStateOrElse("level",null))
      })
      .writeTo(path)
  }
}

object DepthFromOriginal {
  def apply(path:String) = new DepthFromOriginal(path)
}
