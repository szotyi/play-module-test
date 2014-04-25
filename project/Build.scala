import com.github.play2war.plugin.Play2WarKeys
import com.github.play2war.plugin.Play2WarPlugin
import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "kgh"
  val appVersion      = "1.0"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm
  )


  val _main = play.Project(appName, appVersion, appDependencies).
    settings(Play2WarPlugin.play2WarSettings: _*)
    .settings(
      credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.imind.hu", "download", "duna780"),
      resolvers += "nexus" at "http://nexus.imind.hu:8082/nexus/content/groups/public/",

      Play2WarKeys.servletVersion := "3.0",
      Play2WarKeys.targetName := Some(appName),
      Play2WarKeys.explodedJar := true,

//      scalacOptions ++= Seq("-Xmax-classfile-name","140"),
//      // http://stackoverflow.com/questions/14807724/how-can-i-set-the-javac-compile-version-for-play-framework-2-0-to-prevent-unsup
//      javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6"),
//      javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+UseCodeCacheFlushing"),
      // teszt alatt nem veszi figyelembe a parancssori konfigurációs paramétert
      // http://play.lighthouseapp.com/projects/82401/tickets/981-overriding-configuration-for-tests
      javaOptions in Test += "-Dconfig.file=conf/application-ci.conf",
      parallelExecution in Test := false,
      parallelExecution in IntegrationTest := false
    ).dependsOn(test).aggregate(test)

  lazy val test = RootProject(file("modules/test"))

}
