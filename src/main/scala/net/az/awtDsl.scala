package net.az

import java.awt._

import gov.nasa.worldwind.{Model, WorldWindow}
import gov.nasa.worldwind.awt.WorldWindowGLCanvas
import org.eclipse.swt.awt.SWT_AWT
import org.eclipse.swt.widgets.Composite

object awtDsl {
  def borderLayout:BorderLayout = new BorderLayout
  def flowLayout: FlowLayout = new FlowLayout

  def panel(parent:Container, lm: LayoutManager, setups: (Panel=>AnyRef)*):Panel = {
    val p = new Panel(lm)
    parent.add(p)
    setups.foreach(_(p))
    p
  }

  def worldWindow(parent:Container, model:Model):WorldWindow = {
    val world = new WorldWindowGLCanvas
    world.setModel(model)
    parent.add(world)
    world.setPreferredSize(new Dimension(300, 300))
    world
  }

  def frame(parent:Composite, setups: (Frame => AnyRef)*): Frame  = {
    val f = SWT_AWT.new_Frame(parent)
    setups.foreach(_(f))
    f.pack()
    f
  }
}
