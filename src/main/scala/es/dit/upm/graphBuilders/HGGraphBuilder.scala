package es.dit.upm.graphBuilders

import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.implementations.generic.messaging._
import com.raphtory.core.model.graph.{ImmutableProperty, Properties, Type}

class HGGraphBuilder extends GraphBuilder[String] {
  override def parseTuple(tuple: String): Unit = {

    val dataLine = tuple.split(",").map(_.trim)

    val vertex = dataLine(0).toInt
//    val vertexID = assignID(vertex)
    val timestamp = dataLine(1).toLong
    val user = dataLine(2).toInt
    val hateful = dataLine(5)

    val parent = dataLine(3).toInt
//    val parentID = assignID(parent)

    val initial = dataLine(4).toInt
//    val initialID = assignID(parent)

    if (parent == 0 && initial == 0) {
      addVertex(timestamp, vertex,
//        Properties(
//        ImmutableProperty("name",vertex),
//        ImmutableProperty("author",user),
//        ImmutableProperty("hateful",hateful),
//        ImmutableProperty("type","original")),
        Type("Post")
      )
    } else if (parent != 0) {
      addVertex(timestamp, vertex,
//        Properties(
//        ImmutableProperty("name",vertex),
//        ImmutableProperty("author",user),
//        ImmutableProperty("hateful",hateful),
//        ImmutableProperty("type","answer")),
        Type("Post")
      )
      addVertex(timestamp, parent,
//        Properties(ImmutableProperty("name",parent)),
        Type("Post")
      )
      addEdge(timestamp,vertex,parent, Type("Answer"))
    } else {
      addVertex(timestamp, vertex,
//        Properties(
//        ImmutableProperty("name",vertex),
//        ImmutableProperty("author",user),
//        ImmutableProperty("hateful",hateful),
//        ImmutableProperty("type","initial")),
        Type("Post")
      )
      addVertex(timestamp, initial,
//        Properties(ImmutableProperty("name",initial)),
        Type("Post"))
      addEdge(timestamp,vertex, initial, Type("Answer"))
    }
  }
}
