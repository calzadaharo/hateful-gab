package es.dit.upm.graphBuilders

import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.implementations.generic.messaging._
import com.raphtory.core.model.graph.{ImmutableProperty, Properties, Type}

class HGGraphBuilder extends GraphBuilder[String] {
  override def parseTuple(tuple: String): Unit = {

    val dataLine = tuple.split(",").map(_.trim)

    val vertex = dataLine(0).toLong
//    val vertexID = assignID(vertex)
    val timeString = dataLine(1)
    val timestamp = dataLine(1).toLong
    val user = dataLine(2).toLong
    val hateful = dataLine(5)

    val parent = dataLine(3).toLong
//    val parentID = assignID(parent)

    val initial = dataLine(4).toLong
//    val initialID = assignID(parent)

    if (parent == 0 && initial == 0) {
      addVertex(timestamp, vertex,
        Properties(
//        ImmutableProperty("name",vertex.toString),
//        ImmutableProperty("author",user.toString),
          ImmutableProperty("timestamp",timeString),
          ImmutableProperty("hateful",hateful),
          ImmutableProperty("type","original")),
        Type("Post")
      )
    } else if (parent != 0) {
      addVertex(timestamp, vertex,
        Properties(
//        ImmutableProperty("name",vertex.toString),
//        ImmutableProperty("author",user.toString),
          ImmutableProperty("timestamp",timeString),
          ImmutableProperty("hateful",hateful),
         ImmutableProperty("type","answer")),
        Type("Post")
      )

      addVertex(timestamp, parent, Type("Post"))

      addEdge(timestamp,vertex,parent,Type("Answer"))

    } else {
      addVertex(timestamp, vertex,
        Properties(
//        ImmutableProperty("name",vertex.toString),
//        ImmutableProperty("author",user.toString),
          ImmutableProperty("timestamp",timeString),
          ImmutableProperty("hateful",hateful),
          ImmutableProperty("type","initial")),
        Type("Post")
      )
      addVertex(timestamp, initial, Type("Post"))
      addEdge(timestamp,vertex, initial, Type("Answer"))
    }
  }
}
