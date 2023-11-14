import Dependencies._
import com.geirsson.CiReleasePlugin
import sbtdynver.DynVerPlugin.autoImport.dynverSonatypeSnapshots

ThisBuild / resolvers += "Akka library repository".at("https://repo.akka.io/maven")
ThisBuild / resolvers ++= sys.env
  .get("LIGHTBEND_COMMERCIAL_MVN")
  .map { repo =>
    Seq(
      "lightbend-commercial-mvn".at(repo),
      Resolver.url("lightbend-commercial-ivy", url(repo))(Resolver.ivyStylePatterns))
  }
  .getOrElse(Seq.empty)

lazy val `akka-dependencies` =
  Project(id = "akka-dependencies", base = file("."))
    .enablePlugins(BillOfMaterialsPlugin)
    .disablePlugins(CiReleasePlugin)
    .settings(
      crossScalaVersions := Versions.CrossScalaVersions,
      scalaVersion := Versions.Scala213,
      organization := "com.lightbend.akka",
      name := "akka-dependencies",
      description := s"${description.value} (depending on Scala ${CrossVersion.binaryScalaVersion(scalaVersion.value)})",
      homepage := Some(url("https://akka.io/")),
      licenses := Seq(("BUSL-1.1", url("https://raw.githubusercontent.com/akka/akka-dependencies/v23.10.0/LICENSE"))),
      developers := List(
          Developer(
            "akka-contributors",
            "Akka Contributors",
            "akka.official@gmail.com",
            url("https://github.com/akka/akka-dependencies/graphs/contributors"))),
      // append -SNAPSHOT to version when isSnapshot
      ThisBuild / dynverSonatypeSnapshots := true,
      bomIncludeModules := akka ++
        akkaHttp ++
        akkaManagement ++
        akkaProjections ++
        akkaGrpc ++
        akkaPersistencePlugins ++
        alpakka ++
        akkaDiagnostics ++
        telemetry,
      resolvers += "Akka library repository".at("https://repo.akka.io/maven"),
      // to check that all dependencies can be pulled and there are no conflicts
      libraryDependencies ++= {
        val bomDeps = bomIncludeModules.value
        if (sys.env.contains("LIGHTBEND_COMMERCIAL_MVN")) {
          bomDeps
        } else {
          // Run the validation for at least the non-commercial dependencies
          bomDeps.filterNot(allCommercialLibs.contains)
        }
      },
      publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true),
      publishM2Configuration := publishM2Configuration.value.withOverwrite(true))
    .aggregate(docs)

lazy val docs = project
  .enablePlugins(SitePreviewPlugin, AkkaParadoxPlugin, ParadoxSitePlugin, PublishRsyncPlugin)
  .settings(
    name := "Akka Dependencies",
    previewPath := (Paradox / siteSubdirName).value,
    Paradox / siteSubdirName := s"docs/akka-dependencies/${projectInfoVersion.value}",
    projectInfoVersion := (if (isSnapshot.value) "snapshot" else version.value),
    publish / skip := true,
    paradoxProperties ++= Map(
        "scala.binary.version" -> "2.13",
        "akka-scala-2.13.version" -> "2.13",
        "akka-scala-3.version" -> "3.3",
        "akka-dependencies.version" -> version.value,
        "akka-dependencies.major-version" -> "23.10",
        "akka.version" -> Dependencies.Versions.Akka,
        "akka-http.version" -> Dependencies.Versions.AkkaHttp,
        "akka-grpc.version" -> Dependencies.Versions.AkkaGrpc,
        "akka-management.version" -> Dependencies.Versions.AkkaManagement,
        "akka-diagnostics.version" -> Dependencies.Versions.AkkaDiagnostics,
        "akka-projections.version" -> Dependencies.Versions.AkkaProjections,
        "akka-persistence-cassandra.version" -> Dependencies.Versions.AkkaPersistenceCassandra,
        "akka-persistence-jdbc.version" -> Dependencies.Versions.AkkaPersistenceJdbc,
        "akka-persistence-r2dbc.version" -> Dependencies.Versions.AkkaPersistenceR2dbc,
        "alpakka.version" -> Dependencies.Versions.Alpakka,
        "alpakka-kafka.version" -> Dependencies.Versions.AlpakkaKafka),
    paradoxRoots := List("index.html"),
    resolvers += Resolver.jcenterRepo,
    publishRsyncArtifacts += makeSite.value -> "www/",
    publishRsyncHost := "akkarepo@gustav.akka.io")

addCommandAlias("checkBom", ";scalafmtSbtCheck;+akka-dependencies/billOfMaterials:publishM2")
addCommandAlias("checkPullBom", ";scalafmtSbtCheck;+update;+akka-dependencies/billOfMaterials:publishM2")
