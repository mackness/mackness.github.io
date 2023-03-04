lazy val site = (project in file("."))
  .settings(
    name := "site",
    scalaVersion := "2.13.6",
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= List(
      "org.scala-js" %%% "scalajs-dom" % "1.2.0",
      "com.lihaoyi" %%% "utest" % "0.7.9" % "test",
      "com.github.japgolly.scalacss" %%% "core" % "0.8.0-RC1"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaJSPlugin)
