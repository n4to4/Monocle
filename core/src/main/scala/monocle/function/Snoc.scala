package monocle.function

import monocle.{SimpleOptional, SimplePrism}
import monocle.std.tuple2._

import scala.annotation.implicitNotFound


@implicitNotFound("Could not find an instance of Snoc[${S},${A}], please check Monocle instance location policy to " +
  "find out which import is necessary")
trait Snoc[S, A] {

  def _snoc: SimplePrism[S, (S, A)]

  final def initOption: SimpleOptional[S, S] = _snoc composeOptional first
  final def lastOption: SimpleOptional[S, A] = _snoc composeOptional second

}

object Snoc extends SnocFunctions with SnocFunctionsAfterDeprecation

trait SnocFunctions {
  final def _snoc[S, A](implicit ev: Snoc[S, A]): SimplePrism[S, (S, A)] = ev._snoc

  /** append an element to the end */
  final def snoc[S, A](init: S, last: A)(implicit ev: Snoc[S, A]): S =
    ev._snoc.reverseGet((init, last))

  /** deconstruct an S between its init and last */
  final def unsnoc[S, A](s: S)(implicit ev: Snoc[S, A]): Option[(S, A)] =
    ev._snoc.getOption(s)
}

// To merge into ConsFunctions when HeadOption and LastOption are deprecated
sealed trait SnocFunctionsAfterDeprecation {
  final def initOption[S, A](implicit ev: Snoc[S, A]): SimpleOptional[S, S] = ev.initOption
  final def lastOption[S, A](implicit ev: Snoc[S, A]): SimpleOptional[S, A] = ev.lastOption
}