lazy val root = (project in file("."))
  .settings(
    name := "scala-pekko-http-client",
    organization := "io.moia",
    licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0")),
    scmInfo := Some(ScmInfo(url("https://github.com/moia-oss/scala-pekko-http-client"), "scm:git@github.com:moia-oss/scala-pekko-http-client.git")),
    homepage := Some(url("https://github.com/moia-oss/scala-pekko-http-client")),
    scalaVersion := "2.13.16",
    crossScalaVersions := List("2.12.20", "2.13.16"),
    versionScheme := Some("early-semver"),
    scalacOptions ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, 12)) => scalacOptions_2_12
        case Some((2, 13)) => scalacOptions_2_13
        case _             => Seq()
      }
    },
    libraryDependencies ++= pekkoDependencies ++ awsDependencies ++ testDependencies ++ loggingDependencies ++ scalaDependencies
  )
  .settings(sonatypeSettings: _*)
  .configs(IntegrationTest)
  .settings(
    scalafmtOnCompile := true,
    Defaults.itSettings,
    IntegrationTest / scalacOptions := (Compile / scalacOptions).value.filterNot(_ == "-Ywarn-dead-code")
  )
  .settings(sbtGitSettings)
  .enablePlugins(
    GitVersioning,
    GitBranchPrompt
  )
  .settings(mimaSettings)

val pekkoVersion     = "1.1.5"
val pekkoHttpVersion = "1.2.0"

lazy val pekkoDependencies = Seq(
  "org.apache.pekko" %% "pekko-stream"       % pekkoVersion,
  "org.apache.pekko" %% "pekko-stream-typed" % pekkoVersion,
  "org.apache.pekko" %% "pekko-http"         % pekkoHttpVersion,
  "org.apache.pekko" %% "pekko-testkit"      % pekkoVersion     % Test,
  "org.apache.pekko" %% "pekko-http-testkit" % pekkoHttpVersion % Test
)

lazy val awsJavaSdkVersion = "2.32.33"
lazy val awsDependencies = Seq(
  "software.amazon.awssdk" % "core" % awsJavaSdkVersion,
  "software.amazon.awssdk" % "sts"  % awsJavaSdkVersion
)

lazy val testDependencies = Seq(
  "org.scalatest"  %% "scalatest"        % "3.2.19" % Test,
  "org.mockito"    %% "mockito-scala"    % "2.0.0"  % Test,
  "org.mock-server" % "mockserver-netty" % "5.15.0" % Test
)

lazy val loggingDependencies = Seq(
  "com.typesafe.scala-logging" %% "scala-logging"   % "3.9.5",
  "ch.qos.logback"              % "logback-classic" % "1.5.18" % Test
)

lazy val scalaDependencies = Seq(
  "org.scala-lang.modules" %% "scala-collection-compat" % "2.13.0"
)

ThisBuild / scapegoatVersion := "3.1.9"

lazy val scalacOptions_2_12 = Seq(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-release",
  "8",
  "-encoding",
  "UTF-8",
  "-Xfatal-warnings",
  "-Ywarn-unused-import",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit"
)

lazy val scalacOptions_2_13 = Seq(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-release",
  "8",
  "-encoding",
  "UTF-8",
  "-Xfatal-warnings",
  "-Xlint:strict-unsealed-patmat",
  "-Ymacro-annotations",
  "-Ywarn-dead-code",
  "-Xsource:3"
)

lazy val sonatypeSettings = {
  import xerial.sbt.Sonatype._
  Seq(
    publishTo := sonatypePublishTo.value,
    sonatypeProfileName := organization.value,
    publishMavenStyle := true,
    sonatypeProjectHosting := Some(GitHubHosting("moia-oss", "scala-pekko-http-client", "oss-support@moia.io")),
    credentials += Credentials(Path.userHome / ".sbt" / "sonatype_credential")
  )
}

lazy val sbtVersionRegex = "v([0-9]+.[0-9]+.[0-9]+)-?(.*)?".r

lazy val sbtGitSettings = Seq(
  git.useGitDescribe := true,
  git.baseVersion := "0.0.0",
  git.uncommittedSignifier := None,
  git.gitTagToVersionNumber := {
    case sbtVersionRegex(v, "")         => Some(v)
    case sbtVersionRegex(v, "SNAPSHOT") => Some(s"$v-SNAPSHOT")
    case sbtVersionRegex(v, s)          => Some(s"$v-$s-SNAPSHOT")
    case _                              => None
  }
)

lazy val mimaSettings = Seq(
  mimaPreviousArtifacts := Set("io.moia" %% "scala-pekko-http-client" % "1.0.0")
)
