lazy val site = (project in file("."))
  .settings(
    name := "site",
    scalaVersion := "2.13.13",
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies ++= List(
      "org.scala-js" %%% "scalajs-dom" % "1.2.0",
      "com.lihaoyi" %%% "utest" % "0.7.9" % "test",
      "in.nvilla" %%% "monadic-html" % "0.4.1"
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )
  .enablePlugins(ScalaJSPlugin)
