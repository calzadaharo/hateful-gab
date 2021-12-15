package es.dit.upm.spouts

import com.raphtory.core.components.spout.Spout

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}

import java.io.{BufferedReader, InputStreamReader}
import java.net.URI


class HGSpoutHDFS(headerUrlPort:String,route:String,dropHeader:Boolean=false) extends Spout[String] {

  val fs = FileSystem.get(new URI(headerUrlPort), new Configuration())
  private var status = fs.listStatus(new Path(route))
  private var hdfsManager = HDFSManager()

  override def setupDataSource(): Unit = {}

  override def closeDataSource(): Unit = {}

  override def generateData(): Option[String] = {
    val line = hdfsManager.bufferReader.readLine()
    if (hdfsManager.finished(line)) {
      dataSourceComplete()
      None
    } else {
      Some(line)
    }
  }

  final case class HDFSManager private () {
    val stream = fs.open(status(0).getPath)
    status = status.slice(1,status.length-1)
    val bufferReader = new BufferedReader(new InputStreamReader(stream))

    def finished(line: String): Boolean = {
      if (line == null) {
        if(status.length > 0) {
          hdfsManager = HDFSManager()
          false
        } else {
          true
        }
      } else {
        false
      }
    }
  }
}