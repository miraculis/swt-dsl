package net.az.widgets

import org.eclipse.swt.events.{SelectionEvent, SelectionListener}
import org.eclipse.swt.widgets.Composite

import net.az.react.dom._

class Button(parent:Composite, style:Style) {
  val swt :org.eclipse.swt.widgets.Button = new org.eclipse.swt.widgets.Button(parent, style.value)
  val clicks = EventSource[Boolean]
  val init =  {
    swt.addSelectionListener(new SelectionListener {
      override def widgetSelected(selectionEvent: SelectionEvent): Unit = clicks << true

      override def widgetDefaultSelected(selectionEvent: SelectionEvent): Unit = clicks << true
    })
  }
}

object Button {
  def apply(parent:Composite, style:Style) = new Button(parent, style)
}
