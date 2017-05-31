package net.az

import swtDsl._
import awtDsl._
import wwDsl._
import rx.lang.scala.Observer


/**
  */
object Main {
  implicit val editValue = new DefaultEditValue("")
  def runUserForm = {
    shell(
      title("User Profile"),
      gridLayout(),
      composite (
        gridLayout(columns(2)),
        group(
          title("Name"),
          gridLayout(columns(2)),
          horizontal(fill, grabExcessSpace),
          vertical(fill, grabExcessSpace),
          swtDsl.label("First:"),
          edit(Observer(println(_))),
          horizontal(fill, grabExcessSpace)
        ),
        group(
          title("Last"),
          gridLayout(columns(2)),
          horizontal(fill, grabExcessSpace),
          vertical (fill, grabExcessSpace),
          swtDsl.label("Last:"),
          edit(Observer(println(_))),
          horizontal(fill, grabExcessSpace)
        )
      ),
      group(
        title("Gender"),
        rowLayout(),
        horizontal(fill, grabExcessSpace),
        vertical(fill, grabExcessSpace),
        radioButton(Observer((b) => println("male selected")), title("Male")),
        radioButton(Observer((b) => println("female selected")), title("Female"))
      ),
      group(
        title("Role"),
        rowLayout(),
        horizontal(fill, grabExcessSpace),
        vertical(fill, grabExcessSpace),
        checkBox(Observer((b) => println("student")), title("Student")),
        checkBox(Observer((b) => println("employee")), title("Employee"))
      ),
      group(
        title("Experience"),
        rowLayout(),
        horizontal(fill, grabExcessSpace),
        vertical(fill, grabExcessSpace),
        spinner(Observer((x) => println(s"$x years expirience"))),
        swtDsl.label("years")
      ),
      button(
        Observer((b) => println("save user")),
        title("save"),
        horizontal(fill, grabExcessSpace),
        vertical(fill, grabExcessSpace)
      ),
      button(
        Observer((b) => System.exit(0)),
        title("close"),
        horizontal(fill, grabExcessSpace),
        vertical(fill, grabExcessSpace)
      )
    ).run
  }

  def runWWd = {
    shell(
      title("World window"),
      gridLayout(),
      composite(Embedded) (
        gridData(FillBoth),
        frame(panel(flowLayout, worldWindow(model)))
      )
    ).run
  }
  def main(args: Array[String]): Unit = {
    runWWd
  }
}

