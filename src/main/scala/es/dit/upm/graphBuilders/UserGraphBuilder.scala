package es.dit.upm.graphBuilders

import com.raphtory.core.components.graphbuilder.GraphBuilder
import com.raphtory.core.implementations.generic.messaging._
import com.raphtory.core.model.graph.{ImmutableProperty, Properties, Type}

import java.text.SimpleDateFormat

class UserGraphBuilder extends GraphBuilder[String] {
  override def parseTuple(tuple: String) = {
    val fileLine = tuple.split(",").map(_.trim)
    //user wise
    val sourceNode = fileLine(2).toInt
    val targetNode = fileLine(3).toInt
    //comment wise
    // val sourceNode=fileLine(1).toInt
    //val targetNode=fileLine(4).toInt
    if (targetNode > 0 && targetNode != sourceNode) {
//      val creationDate = dateToUnixTime(timestamp = fileLine(0).slice(0, 19))
      val creationDate = fileLine(1).toInt
      addVertex(creationDate, sourceNode, Type("User"))
      addVertex(creationDate, targetNode, Type("User"))
      addEdge(creationDate, sourceNode, targetNode, Type("User to User"))
      //      sendGraphUpdate(VertexAddWithProperties(creationDate, sourceNode, Properties(StringProperty("test1","value1"),StringProperty("test2","Value2")),Type("User")))
      //      sendGraphUpdate(VertexAddWithProperties(creationDate, targetNode, Properties(StringProperty("test1","value1"),StringProperty("test2","Value2")),Type("User")))
      //      sendGraphUpdate(EdgeAddWithProperties(creationDate, sourceNode, targetNode, Properties(StringProperty("test1","value1"),StringProperty("test2","Value2")),Type("User To User")))
    }
  }

//  def dateToUnixTime(timestamp: => String): Long = {
//    val sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//    val dt = sdf.parse(timestamp)
//    val epoch = dt.getTime
//    epoch
//  }
}
