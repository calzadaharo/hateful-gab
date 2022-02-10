package es.dit.upm.algorithms

import com.raphtory.core.model.algorithm.{GraphAlgorithm, GraphPerspective, Row}

class DepthTimestampFromOriginal(path: String) extends GraphAlgorithm{
  override def algorithm(graph: GraphPerspective): Unit = {
    graph
      .step ({
        vertex =>
          if (vertex.getAllNeighbours().length == 0) {
            vertex.voteToHalt
          } else if (vertex.getAllNeighbours().length > 0 &&
            vertex.getPropertyOrElse("type", null) == "original") {
            vertex.setState("cascade", vertex.ID)
            vertex.setState("depth",0)
            vertex.setState("index", 0)
            val timestamp = vertex.getProperty("timestamp")
            vertex.messageAllIngoingNeighbors(vertex.ID,timestamp,0,true)
            vertex.voteToHalt
          } else {
            val timestamp = vertex.getProperty("timestamp")
            vertex.setState("index", 0)
            vertex.messageAllOutgoingNeighbors(0,timestamp,0,false)
          }
      })

      .iterate ({
        vertex =>
          val messages = vertex.messageQueue[(Long,Long,Int,Boolean)]
          val cascade = messages(0)._1
          val timestamp = messages(0)._2
          val level = messages(0)._3 + 1
          val flag = messages(0)._4
          val index = vertex.getStateOrElse[Int]("index",0) + 1

          // Check if message is already descending
          if (flag) {
            // Check if the message was born in the original
            if (cascade != 0) {
              vertex.setState("cascade",cascade)
              vertex.setState("level",level)
              vertex.setState("index",index)
              vertex.messageAllIngoingNeighbors(cascade,timestamp,level,true)
            } else {
              // Check if vertex timestamp is higher than the message one
              if (timestamp < vertex.getPropertyOrElse[Long]("timestamp",0)) {
                vertex.setState("index",index)
              }
              // Common for both higher and lower than timestamp
              vertex.messageAllIngoingNeighbors(0,timestamp,0,true)
            }
          // Message ascending
          } else {
            // Check is message has reached the original
            if (vertex.getPropertyOrElse("type",null) == "original"){
              vertex.messageAllIngoingNeighbors(0,timestamp,0,true)
            } else {
              vertex.messageAllOutgoingNeighbors(0,timestamp,0,false)
            }
          }
      }, iterations = 100000, executeMessagedOnly = true)

      .select ({
        vertex =>
          Row(vertex.ID,
            vertex.getPropertyOrElse("timestamp",null),
            vertex.getStateOrElse("cascade",null),
            vertex.getStateOrElse("level",null),
            vertex.getStateOrElse("index",null),
            vertex.getPropertyOrElse("hateful","PROBLEM")
          )
      })

      .writeTo(path)
  }
}

object DepthTimestampFromOriginal {
  def apply(path:String) = new DepthTimestampFromOriginal(path)
}
