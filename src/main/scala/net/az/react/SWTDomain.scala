package net.az.react

import org.eclipse.swt.widgets.Display

import scala.react.Domain

object dom extends SWTDomain;

class SWTDomain extends Domain {
  val engine = new Engine
  val scheduler = new ThreadSafeScheduler {
    override protected def schedule(r: Runnable): Unit = Display.getDefault.asyncExec(r)
  }
}
