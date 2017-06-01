package net.az.widgets

import org.eclipse.swt.events.ModifyEvent
import org.eclipse.swt.widgets.Composite

import net.az.react.dom._

class Spinner(parent: Composite, style: Style) {
  val swt: org.eclipse.swt.widgets.Spinner = new org.eclipse.swt.widgets.Spinner(parent, style.value)
  val values = EventSource[Int]
  val init = {
    swt.addModifyListener((e: ModifyEvent) =>
      e.widget match {
        case t: org.eclipse.swt.widgets.Spinner => values << t.getSelection
      })
  }
}

object Spinner {
  def apply(parent: Composite, style: Style): Spinner = new Spinner(parent, style)
}
