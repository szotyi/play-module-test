import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "test"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm
  )


  val _test = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
    // Titkosított fájlrendszer alatt elszáll a fordítás, ez a megoldás, máshol nem okoz bajt:
//    scalacOptions ++= Seq("-Xmax-classfile-name","140"),
    // http://stackoverflow.com/questions/14807724/how-can-i-set-the-javac-compile-version-for-play-framework-2-0-to-prevent-unsup
//    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6"),
//    javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+UseCodeCacheFlushing")
    // Add your own project settings here
  )

}
