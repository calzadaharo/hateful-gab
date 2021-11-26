package es.dit.upm.graphBuilders

import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.implementations.generic.messaging._
import com.raphtory.core.model.graph.{ImmutableProperty, Properties, Type}

class HGGraphBuilder extends GraphBuilder[String] {
  override def parseTuple(tuple: String): Unit = {

    val dataLine = tuple.split(",").map(_.trim)

    val vertex = dataLine(0)
    val vertexID = assignID(vertex)
    val timestamp = dataLine(1).toLong
    val user = dataLine(2)
    val hateful = dataLine(5)

    val parent = dataLine(3)
    val parentID = assignID(parent)

    val repost = dataLine(4)
    val repostID = assignID(parent)

    if (parent == "0" && repost == "0") {
      addVertex(timestamp, vertexID, Properties(
        ImmutableProperty("name",vertex),
        ImmutableProperty("author",user),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","original")),
        Type("Post")
      )
    } else if (parent != "0") {
      addVertex(timestamp, vertexID, Properties(
        ImmutableProperty("name",vertex),
        ImmutableProperty("author",user),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","answer")),
        Type("Post")
      )
      addVertex(timestamp, parentID, Properties(ImmutableProperty("name",parent)), Type("Post"))
      addEdge(timestamp,vertexID,parentID, Type("Answer"))
    } else {
      addVertex(timestamp, vertexID, Properties(
        ImmutableProperty("name",vertex),
        ImmutableProperty("author",user),
        ImmutableProperty("hateful",hateful),
        ImmutableProperty("type","repost")),
        Type("Post")
      )
      addVertex(timestamp, repostID, Properties(ImmutableProperty("name",repost)), Type("Post"))
      addEdge(timestamp,vertexID,repostID, Type("Repost"))
    }
  }
}
