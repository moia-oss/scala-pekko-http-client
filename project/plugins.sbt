// adds reStart and reStop
addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")

// adds scalafmt
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

// sbt> scapegoat
addSbtPlugin("com.sksamuel.scapegoat" %% "sbt-scapegoat" % "1.1.1")

// Publish to sonatype
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.12")

// publishSigned
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")

addSbtPlugin("com.github.sbt" % "sbt-git" % "2.0.0")

// sbt> mimaReportBinaryIssues
addSbtPlugin("com.typesafe" % "sbt-mima-plugin" % "1.0.1")
