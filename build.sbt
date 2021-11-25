name := "hateful-gab"

version := "0.1"

ThisBuild / organization := "es.dit.upm"
scalaVersion := "2.13.7"

lazy val root = (project in file(".")).
  settings(
    name := "hateful-gab"
  )

// SBT already finds jars jars present in the "lib" directory. However it is always best to express unmanaged dependencies explicitly.
// It eliminates scope of any assumptions and documents the dependencies right here in the "build.sbt" file.
Compile / unmanagedJars += baseDirectory.value / "lib/raphtory.jar"
