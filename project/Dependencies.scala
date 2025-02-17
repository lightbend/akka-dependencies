import sbt._

object Dependencies {
  object Versions {
    val Scala213 = "2.13.14"
    val CrossScalaVersions = Seq(Scala213)

    val Scala3 = "3.3"

    // To update Cinnamon version, change the plugin version
    // in project/plugins.sbt
    val AkkaDependenciesMinor = "24.10"
    val Akka = "2.10.2"
    val AkkaHttp = "10.7.0"
    val AkkaManagement = "1.6.0"
    val AkkaProjections = "1.6.9"
    val AkkaGrpc = "2.5.0"
    val AkkaPersistenceCassandra = "1.3.0"
    val AkkaPersistenceDynamoDb = "2.0.5"
    val AkkaPersistenceJdbc = "5.5.0"
    val AkkaPersistenceR2dbc = "1.3.3"
    val Alpakka = "9.0.0"
    val AlpakkaKafka = "7.0.0"
    val AkkaDiagnostics = "2.2.0"
    val AkkaEdgeRust = "0.8.0"
  }

  import Versions._

  /**
   * Based on list of supported components
   * https://developer.lightbend.com/docs/introduction/getting-help/build-dependencies.html
   */
  val akka = Seq(
    "com.typesafe.akka" %% "akka-actor" % Akka,
    "com.typesafe.akka" %% "akka-actor-testkit-typed" % Akka,
    "com.typesafe.akka" %% "akka-actor-typed" % Akka,
    "com.typesafe.akka" %% "akka-cluster" % Akka,
    "com.typesafe.akka" %% "akka-cluster-metrics" % Akka,
    "com.typesafe.akka" %% "akka-cluster-sharding" % Akka,
    "com.typesafe.akka" %% "akka-cluster-sharding-typed" % Akka,
    "com.typesafe.akka" %% "akka-cluster-tools" % Akka,
    "com.typesafe.akka" %% "akka-cluster-typed" % Akka,
    "com.typesafe.akka" %% "akka-coordination" % Akka,
    "com.typesafe.akka" %% "akka-discovery" % Akka,
    "com.typesafe.akka" %% "akka-distributed-data" % Akka,
    "com.typesafe.akka" %% "akka-serialization-jackson" % Akka,
    "com.typesafe.akka" %% "akka-multi-node-testkit" % Akka,
    "com.typesafe.akka" %% "akka-persistence" % Akka,
    "com.typesafe.akka" %% "akka-persistence-query" % Akka,
    "com.typesafe.akka" %% "akka-persistence-tck" % Akka,
    "com.typesafe.akka" %% "akka-persistence-typed" % Akka,
    "com.typesafe.akka" %% "akka-persistence-testkit" % Akka,
    "com.typesafe.akka" %% "akka-protobuf-v3" % Akka,
    "com.typesafe.akka" %% "akka-pki" % Akka,
    "com.typesafe.akka" %% "akka-remote" % Akka,
    "com.typesafe.akka" %% "akka-slf4j" % Akka,
    "com.typesafe.akka" %% "akka-stream" % Akka,
    "com.typesafe.akka" %% "akka-stream-testkit" % Akka,
    "com.typesafe.akka" %% "akka-stream-typed" % Akka,
    "com.typesafe.akka" %% "akka-testkit" % Akka)

  val akkaHttp = Seq(
    "com.typesafe.akka" %% "akka-http" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-caching" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-core" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-jackson" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttp,
    "com.typesafe.akka" %% "akka-http-xml" % AkkaHttp,
    "com.typesafe.akka" %% "akka-parsing" % AkkaHttp)

  val akkaManagement = Seq(
    "com.lightbend.akka.management" %% "akka-management" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-cluster-http" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-loglevels-logback" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-loglevels-log4j2" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-management-pki" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-lease-kubernetes" % AkkaManagement,
    "com.lightbend.akka.management" %% "akka-rolling-update-kubernetes" % AkkaManagement,
    "com.lightbend.akka.discovery" %% "akka-discovery-kubernetes-api" % AkkaManagement,
    "com.lightbend.akka.discovery" %% "akka-discovery-aws-api-async" % AkkaManagement,
    "com.lightbend.akka.discovery" %% "akka-discovery-aws-api" % AkkaManagement,
    "com.lightbend.akka.discovery" %% "akka-discovery-azure-api" % AkkaManagement)

  val akkaProjections = Seq(
    "com.lightbend.akka" %% "akka-projection-core" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-dynamodb" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-slick" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-eventsourced" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-durable-state" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-kafka" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-cassandra" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-jdbc" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-r2dbc" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-grpc" % AkkaProjections,
    "com.lightbend.akka" %% "akka-projection-testkit" % AkkaProjections)

  val akkaGrpc = Seq("com.lightbend.akka.grpc" %% "akka-grpc-runtime" % AkkaGrpc)

  val akkaPersistencePlugins = Seq(
    "com.typesafe.akka" %% "akka-persistence-cassandra" % AkkaPersistenceCassandra,
    "com.lightbend.akka" %% "akka-persistence-dynamodb" % AkkaPersistenceDynamoDb,
    "com.lightbend.akka" %% "akka-persistence-jdbc" % AkkaPersistenceJdbc,
    "com.lightbend.akka" %% "akka-persistence-r2dbc" % AkkaPersistenceR2dbc)

  val alpakka = Seq(
    "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % Alpakka,
    "com.lightbend.akka" %% "akka-stream-alpakka-csv" % Alpakka,
    "com.typesafe.akka" %% "akka-stream-kafka" % AlpakkaKafka,
    "com.typesafe.akka" %% "akka-stream-kafka-cluster-sharding" % AlpakkaKafka,
    "com.typesafe.akka" %% "akka-stream-kafka-testkit" % AlpakkaKafka)

  val akkaDiagnostics = Seq("com.lightbend.akka" %% "akka-diagnostics" % AkkaDiagnostics)

  /**
   * Commercial components
   */
  // sbt-cinnamon plugin does not have artifacts for Scala 2.13.x
  val telemetry = com.lightbend.cinnamon.sbt.Cinnamon.library.modules.filterNot(_.name.equals("sbt-cinnamon"))

  val allCommercialLibs = telemetry
}
