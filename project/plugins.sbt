// adds reStart and reStop
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")

// adds scalafmt
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

// sbt> scapegoat
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.2.4")

// Publish to sonatype
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.11.0")

// publishSigned
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.2.1")

addSbtPlugin("com.github.sbt" % "sbt-git" % "2.0.1")

// sbt> mimaReportBinaryIssues
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.1.3")
