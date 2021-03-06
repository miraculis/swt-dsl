name := "ui"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

val os =
  (sys.props("os.name"), sys.props("os.arch")) match {
    case ("Linux", _) => "gtk.linux.x86"
    case ("Mac OS X", "amd64" | "x86_64") => "cocoa.macosx.x86_64"
    case ("Mac OS X", _) => "cocoa.macosx.x86"
    case (os, "amd64") if os.startsWith("Windows") => "win32.win32.x86_64"
    case (os, _) if os.startsWith("Windows") => "win32.win32.x86"
    case (os, arch) => sys.error("Cannot obtain lib for OS '" + os + "' and architecture '" + arch + "'")
  }

libraryDependencies += "org.eclipse.swt" % "org.eclipse.swt.win32.win32.x86_64" % "4.3"

libraryDependencies += "org.scala-lang.plugins" %% "scala-continuations-library" % "1.0.3"