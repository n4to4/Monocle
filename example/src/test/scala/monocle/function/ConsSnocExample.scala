package monocle.function

import org.specs2.scalaz.Spec
import monocle.std._


class ConsSnocExample extends Spec {

  "cons add an element to the head" in {
    cons(1, List(2, 3))        shouldEqual List(1, 2, 3)
    cons(1, Vector.empty[Int]) shouldEqual Vector(1)
  }

  "uncons deconstructs an element between its head and tail" in {
    uncons(List(1, 2, 3))   shouldEqual Some(1, List(2, 3))
    uncons(Vector(1, 2, 3)) shouldEqual Some(1, Vector(2, 3))

    uncons(List.empty[Int]) shouldEqual None
  }

  "snoc add an element to the end" in {
    snoc(List(1, 2), 3)        shouldEqual List(1, 2, 3)
    snoc(Vector.empty[Int], 1) shouldEqual Vector(1)
  }

  "snoc deconstructs an element between its init and last" in {
    unsnoc(List(1, 2, 3))   shouldEqual Some(List(1, 2), 3)
    unsnoc(Vector(1, 2, 3)) shouldEqual Some(Vector(1, 2), 3)

    unsnoc(List.empty[Int]) shouldEqual None
  }

}
