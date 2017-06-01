package net.az.widgets

import net.az.swtDsl.DefaultEditValue
import org.eclipse.swt.events.ModifyEvent
import org.eclipse.swt.widgets.Composite

import net.az.react.dom._

class Text(parent: Composite, style: Style, value: DefaultEditValue) {
  val swt: org.eclipse.swt.widgets.Text = new org.eclipse.swt.widgets.Text(parent, style.value)

  val values = EventSource[String]
  val init = {
    swt.addModifyListener((e: ModifyEvent) => e.widget match {
      case t: org.eclipse.swt.widgets.Text => values << t.getText
    })
    swt.setText(value.value)
  }

}

object Text {
  def apply(parent: Composite, style: Style, value: DefaultEditValue): Text = new Text(parent, style, value)
}

