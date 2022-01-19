package es.dit.upm.graphBuilders

import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.implementations.generic.messaging._
import com.raphtory.core.model.graph.{ImmutableProperty, Properties, Type}

class HGGraphBuilder extends GraphBuilder[String] {
  override def parseTuple(tuple: String): Unit = {

    val dataLine = tuple.split(",").map(_.trim)

    val vertex = dataLine(0).toLong
//    val vertexID = assignID(vertex)
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
        ImmutableProperty("name",vertex.toString),
        ImmutableProperty("author",user.toString),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","original")),
        Type("Post")
      )
    } else if (parent != 0) {
      addVertex(timestamp, vertex,
        Properties(
        ImmutableProperty("name",vertex.toString),
        ImmutableProperty("author",user.toString),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","answer")),
        Type("Answer")
      )
      addVertex(timestamp, parent,
        Properties(ImmutableProperty("name",parent.toString)),
        Type("Post")
      )
      addEdge(timestamp,vertex,parent, Type("Answer"))
    } else {
      addVertex(timestamp, vertex,
        Properties(
        ImmutableProperty("name",vertex.toString),
        ImmutableProperty("author",user.toString),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","initial")),
        Type("Repost")
      )
      addVertex(timestamp, initial,
        Properties(ImmutableProperty("name",initial.toString)),
        Type("Post"))
      addEdge(timestamp,vertex, initial, Type("Answer"))
    }
  }
}
