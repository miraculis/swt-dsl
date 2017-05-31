package net.az

import java.awt._

import gov.nasa.worldwind.{Model, WorldWindow}
import gov.nasa.worldwind.awt.WorldWindowGLCanvas
import org.eclipse.swt.awt.SWT_AWT
import org.eclipse.swt.widgets.Composite

object awtDsl {
  def borderLayout:BorderLayout = new BorderLayout
  def flowLayout: FlowLayout = new FlowLayout

  def panel(lm: LayoutManager, setups: (Panel=>AnyRef)*)(parent: Container):Panel = {
    val p = new Panel(lm)
    parent.add(p)
    setups.foreach(_(p))
    p
  }

  def label(text:String)(parent:Container):Label = {
    val l = new Label(text)
    parent.add(l)
    l
  }

  def worldWindow(model:Model)(parent: Container):WorldWindow = {
    val world = new WorldWindowGLCanvas
    world.setModel(model)
    parent.add(world)
    world.setPreferredSize(new Dimension(300, 300))
    world
  }

  def frame(setups: (Frame => AnyRef)*)(parent:Composite): Frame  = {
    val f = SWT_AWT.new_Frame(parent)
    setups.foreach(_(f))
    f.pack()
    f
  }
}
