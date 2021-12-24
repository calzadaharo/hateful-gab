name := "hateful-gab"

version := "0.1"

ThisBuild / organization := "es.dit.upm"
ThisBuild / scalaVersion := "2.12.4"

lazy val root = (project in file(".")).
  settings(
    name := "hateful-gab"
  )

//assemblyOption in assembly:= (assemblyOption in assembly)
//  .value.copy(includeScala = false, includeDependency = false)

// SBT already finds jars jars present in the "lib" directory. However it is always best to express unmanaged dependencies explicitly.
// It eliminates scope of any assumptions and documents the dependencies right here in the "build.sbt" file.
Compile / unmanagedJars += baseDirectory.value / "lib/raphtory.jar"
