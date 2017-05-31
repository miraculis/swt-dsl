package net.az

import org.eclipse.swt.SWT
import org.eclipse.swt.events.{ModifyEvent, SelectionEvent, SelectionListener}
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.layout._
import org.eclipse.swt.widgets._
import rx.lang.scala.{Observable, Observer, Subscriber}

case class ShellDisplay(shell: Shell, display: Display) {
  def run = {
    while (!shell.isDisposed)
      if (!display.readAndDispatch()) display.sleep()
    display.dispose()
  }
}

object swtDsl extends Layouts with Styles {

  def shell(setups: (Shell => AnyRef)*): ShellDisplay = {
    val display = new Display()
    val shell = ShellDisplay(new Shell(display), display)
    shell.shell.setLayout(new FillLayout)
    setups.foreach(_(shell.shell))
    shell.shell.pack()
    shell.shell.open()
    shell
  }

  def shell(style: ShellStyle)(setups: (Shell => AnyRef)*): ShellDisplay = {
    val display = new Display()
    val shell = ShellDisplay(new Shell(display, style.value), display)
    shell.shell.setLayout(new FillLayout)
    setups.foreach(_(shell.shell))
    shell.shell.pack()
    shell.shell.open()
    shell
  }

  def title(t: String)(titled: {def setText(t: String)}) = {
    titled.setText(t)
    titled
  }

  def icon(image: Image)(target: {def setImage(image: Image)}) = target.setImage(image)

  def byClose(handler: Event => Unit)(s: Shell) = s.addListener(SWT.Close,
                                                                new Listener {
                                                                  def handleEvent(e: Event) = handler(e)

                                                                })

  def gridData(style:Style)(parent:Composite): GridData = {
    val gd = new GridData(style.value)
    parent.setLayoutData(gd)
    gd
  }

  def composite(setups: (Composite => AnyRef)*)(parent: Composite): Composite = {
    val c = new Composite(parent, None.value)
    c.setLayout(new FillLayout)
    setups.foreach(_(c))
    c
  }

  def composite(style: Style)(setups: (Composite => AnyRef)*)(parent: Composite): Composite = {
    val c = new Composite(parent, style.value)
    c.setLayout(new FillLayout)
    setups.foreach(_(c))
    c
  }


  def group(setups: (Group => AnyRef)*)(parent:Composite): Group = {
    val g = new Group(parent, SWT.NONE)
    g.setLayout(new FillLayout)
    setups.foreach(_(g))
    g
  }

  def label(t: String, setups: (Label => AnyRef)*)(parent:Composite): Label = {
		  val label = new Label(parent, SWT.NONE)
		  label.setText(t)
		  setups.foreach(_(label))
      label
  }

  case class DefaultEditValue(value: String)

  def edit(binding: Observer[String], setups: (Text => AnyRef)*)(parent:Composite)(implicit content: DefaultEditValue): Text = {
    val text = new Text(parent, SWT.BORDER)
    text.setText(content.value)
    setups.foreach(_(text))
    Observable((subscriber: Subscriber[String]) =>
      text.addModifyListener((e: ModifyEvent) =>
          e.widget match {
          case t:Text => subscriber.onNext(t.getText)
        })
      ).subscribe(binding)

    text
  }

  def radioButton(binding: Observer[Boolean], setups: (Button => AnyRef)*)(parent: Composite): Button = {
    val button = new Button(parent, SWT.RADIO)
    setups.foreach(_(button))
    Observable((subscriber: Subscriber[Boolean]) => button.addSelectionListener(new SelectionListener {
      override def widgetSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)

      override def widgetDefaultSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)
    })).subscribe(binding)
    button
  }

  def checkBox(binding: Observer[Boolean], setups: (Button => AnyRef)*)(parent:Composite): Button = {
    val button = new Button(parent, SWT.CHECK)
    setups.foreach(_(button))
    Observable((subscriber: Subscriber[Boolean]) => button.addSelectionListener(new SelectionListener {
      override def widgetSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)

      override def widgetDefaultSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)
    })).subscribe(binding)
    button
  }

  def button(stream: Observer[Boolean], setups: (Button => AnyRef)*)(parent:Composite): Button = {
    val button = new Button(parent, SWT.NONE)
    setups.foreach(_(button))
    Observable((subscriber: Subscriber[Boolean]) => button.addSelectionListener(new SelectionListener {
      override def widgetSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)

      override def widgetDefaultSelected(selectionEvent: SelectionEvent): Unit = subscriber.onNext(true)
    })).subscribe(stream)
    button
  }

  def spinner(binding: Observer[Int], setups: (Spinner => AnyRef)*)(parent:Composite) = {
    val spinner = new Spinner(parent, SWT.NONE)
    setups.foreach(_(spinner))
    Observable((subscriber: Subscriber[Int]) =>
      spinner.addModifyListener((e: ModifyEvent) =>
          e.widget match {
            case t:Spinner => subscriber.onNext(t.getDigits)
          })
    ).subscribe(binding)
    spinner
  }

  def selectedIndex(i: Int)(s: Spinner) = s.setSelection(i)

  def selected(widget: {def setSelection(b: Boolean)}) = widget.setSelection(true)
}