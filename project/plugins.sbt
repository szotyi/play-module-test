// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// iMind repository
resolvers += "nexus" at "http://nexus.imind.hu:8082/nexus/content/groups/public/"

// iMind credentials
credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.imind.hu", "download", "duna780")

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.1.3")

// Play2War plugin
addSbtPlugin("com.github.play2war" % "play2-war-plugin" % "1.0.2-SNAPSHOT")
