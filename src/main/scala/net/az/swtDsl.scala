package net.az

import net.az.widgets.{Border, Button, Check, None, Radio, ShellStyle, Spinner, Style, Text}
import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.layout._
import org.eclipse.swt.widgets._


object swtDsl extends Layouts {

  def display = new Display

  def shell(display: Display, style: ShellStyle, settings:(Shell=>AnyRef)*): Shell = {
    val shell = new Shell(display, style.value)
    settings.foreach(_(shell))
    shell
  }

  def run(display:Display, shell:Shell) = {
    while (!shell.isDisposed)
      if (!display.readAndDispatch()) display.sleep()
    display.dispose()
  }


  def title(t: String)(titled: {def setText(t: String)}) = {
    titled.setText(t)
    titled
  }

  def layout(l: Layout)(wl: {def setLayout(l: Layout)}) = {
    wl.setLayout(l)
    wl
  }

  def layoutData(data:Any)(ld: {def setLayoutData(data:Any)}) = {
    ld.setLayoutData(data)
    ld
  }

  def icon(image: Image)(target: {def setImage(image: Image)}) = target.setImage(image)

  def byClose(handler: Event => Unit)(s: Shell) = s.addListener(SWT.Close,
                                                                new Listener {
                                                                  def handleEvent(e: Event) = handler(e)

                                                                })

  def gridData(style:Style): GridData = {
    new GridData(style.value)
  }

  def composite(parent:Composite, style: Style, setups: (Composite => AnyRef)*): Composite = {
    val c = new Composite(parent, style.value)
    c.setLayout(new FillLayout)
    setups.foreach(_(c))
    c
  }

  def group(parent:Composite, setups: (Group => AnyRef)*): Group = {
    val g = new Group(parent, SWT.NONE)
    setups.foreach(_(g))
    g
  }

  def label(parent:Composite, t: String, setups: (Label => AnyRef)*): Label = {
		  val label = new Label(parent, SWT.NONE)
		  label.setText(t)
		  setups.foreach(_(label))
      label
  }

  case class DefaultEditValue(value: String)

  def edit(parent:Composite, setups: (org.eclipse.swt.widgets.Text => AnyRef)*)(implicit content: DefaultEditValue): Text = {
    val text = Text(parent, Border, content)
    setups.foreach(_(text swt))
    text
  }

  def radioButton(parent:Composite, setups: (org.eclipse.swt.widgets.Button => AnyRef)*): Button = {
    val button = new Button(parent, Radio)
    setups.foreach(_(button swt))
    button
  }

  def checkBox(parent:Composite, setups: (org.eclipse.swt.widgets.Button => AnyRef)*): Button = {
    val button = new Button(parent, Check)
    setups.foreach(_(button swt))
    button
  }

  def button(parent:Composite, setups: (org.eclipse.swt.widgets.Button => AnyRef)*): Button = {
    val button = new Button(parent, widgets.None)
    setups.foreach(_(button swt))
    button
  }

  def spinner(parent:Composite, setups: (org.eclipse.swt.widgets.Spinner => AnyRef)*) = {
    val spinner = new Spinner(parent, None)
    setups.foreach(_(spinner swt))
    spinner
  }

  def selectedIndex(i: Int)(s: Spinner) = s.swt.setSelection(i)

  def selected(widget: {def setSelection(b: Boolean)}) = widget.setSelection(true)
}
