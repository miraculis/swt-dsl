package net.az

import org.eclipse.swt._
import org.eclipse.swt.layout._
import org.eclipse.swt.widgets._

trait Layouts {
  protected class GridCell(
    val span: Int => Unit, 
    val align: Int => Unit, 
    val grabExcessSpace: Boolean => Unit, 
    val sizeHint: Int => Unit
  )

  def fillLayout(setups: (FillLayout=>AnyRef)*) = {
    val layout = new FillLayout
    setups.foreach(_(layout))
    layout
  }

  def rowLayout(setups: (RowLayout => AnyRef)*): RowLayout = {
  	val layout = new RowLayout
    setups.foreach(_(layout))
  	layout
  }
  
  def gridLayout(setups: (GridLayout => AnyRef)*): GridLayout = {
    val layout = new GridLayout
    setups.foreach(_(layout))
    layout
  }

  def horizontal(settings: (GridCell => AnyRef)*)(target: Control) = {
    val data = target.getLayoutData() match {
      case x: GridData => x
      case _ => new GridData
    }
    val cell = new GridCell(data.horizontalSpan=_,
                            data.horizontalAlignment=_,
                            data.grabExcessHorizontalSpace=_,
                            data.widthHint=_)
    settings foreach(_(cell))
    target.setLayoutData(data)
    target
  }

  def vertical(settings: (GridCell => AnyRef)*)(target: Control) = {
    val data = target.getLayoutData() match {
      case x: GridData => x
      case _ => new GridData
    }
    val cell = new GridCell(data.verticalSpan=_,
                            data.verticalAlignment=_,
                            data.grabExcessVerticalSpace=_, 
                            data.heightHint=_)
    settings foreach(_(cell))
    target.setLayoutData(data)
    target
  }

  def grabExcessSpace = (target:GridCell) => {
    target.grabExcessSpace(true)
    target
  }

  def fill = (target:GridCell) => {
    target.align(SWT.FILL)
    target
  }
  
  def beginning(target: GridCell) = {
    target.align(SWT.BEGINNING)
    target
  }
  
  def end(target: GridCell) = {
    target.align(SWT.END)
    target
  }

  def columns(n: Int)(layout: GridLayout) = {
    layout.numColumns = n
    layout
  }

  def horizontal(layout: FillLayout) {
    layout.`type` = SWT.HORIZONTAL
  }

  def horizontal(layout: RowLayout) {
    layout.`type` = SWT.HORIZONTAL
  }

  def vertical(layout: FillLayout) {
    layout.`type` = SWT.VERTICAL
  }

  def vertical(layout: RowLayout) {
    layout.`type` = SWT.VERTICAL
  }

  def margins(size: Int)(layout: Layout) = layout match {
    case layout: FillLayout =>
      layout.marginWidth = size
	  layout.marginHeight = size
    case layout: GridLayout =>
      layout.marginWidth = size
	  layout.marginHeight = size
    case layout: RowLayout =>
      layout.marginWidth = size
      layout.marginHeight = size
  }
}