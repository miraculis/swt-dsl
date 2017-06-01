package net.az

import net.az.react.dom
import net.az.swtDsl._
import net.az.awtDsl._
import wwDsl._
import net.az.react.dom._
import net.az.widgets.Embedded
/**
  */
object Main extends Observing {
  implicit val editValue = new DefaultEditValue("")

  class WWd {
    val d = display
    val sh = shell(d, net.az.widgets.None, title("Nasa"), layout(gridLayout()))
    val comp = composite(sh, Embedded)
    val awt = frame(comp)
    val top = panel(awt, flowLayout)
    val ww = worldWindow(top, model)

    def runForm = {
      sh.pack
      sh.open
      run(d, sh)
    }
  }

  class UserForm {
    val d = display
    val sh = shell(d, net.az.widgets.None, title("User Profile"), layout(gridLayout()))
    val c1 = composite(sh, net.az.widgets.None, layout(gridLayout(columns(2))))
    val g1 = group(c1, title("first name"), layout(gridLayout(columns(2))), horizontal(fill, grabExcessSpace),
      vertical(fill, grabExcessSpace))
    swtDsl.label(g1, "first name:")
    val fn = edit(g1)
    val g2 = group(c1, title("last name"), layout(gridLayout(columns(2))), horizontal(fill, grabExcessSpace),
      vertical(fill, grabExcessSpace))
    swtDsl.label(g2, "last name:")
    val ln = edit(g2)
    val g3 = group(sh, title("gender"), layout(rowLayout()), horizontal(fill, grabExcessSpace), vertical(fill, grabExcessSpace))
    val male = radioButton(g3, title("male"))
    val female = radioButton(g3, title("female"))
    val g4 = group(sh, title("role"), layout(rowLayout()), horizontal(fill, grabExcessSpace), vertical(fill, grabExcessSpace))
    val stud = checkBox(g4, title("student"))
    val emp = checkBox(g4, title("empoyee"))
    val g5 = group(sh, title("expirience"), layout(rowLayout()), horizontal(fill, grabExcessSpace), vertical(fill, grabExcessSpace))
    val spin = spinner(g5)
    swtDsl.label(g5, "years")
    val save = button(sh, title("save"))
    val exit = button(sh, title("exit"))

    def control(): Unit = {
      observe(fn.values)((m) => {
        println(m)
        true
      }
      )
      observe(exit.clicks)((m) => {
        System.exit(0)
        false
      })
      observe(save.clicks)((m) => {
        println("saving...")
        true
      })
      observe(spin.values)((m) => {
        println(s"$m years")
        true
      })
      observe(emp.clicks)((m) => {
        println("e selected")
        true
      })
      observe(stud.clicks)((m) => {
        println("s selected")
        true
      })
      observe(ln.values)((m) => {
        println(m)
        true
      }
      )
      observe(male.clicks)((m) => {
        println("m selected")
        true
      })
      observe(female.clicks)((m) => {
        println("f selected")
        true
      })
    }

    def run() = {
      sh.pack
      sh.open
      swtDsl.run(d, sh)
    }
  }

  def main(args: Array[String]): Unit = {
    val form = new WWd

    dom.start
    form.runForm
  }
}

