import model._
import org.scalatestplus.play._

class ChessSpec extends PlaySpec {
/*
  "A Stack" must {
    "pop values in last-in-first-out order" in {
      val stack = new mutable.Stack[Int]
      stack.push(1)
      stack.push(2)
      stack.pop() mustBe 2
      stack.pop() mustBe 1
    }
    "throw NoSuchElementException if an empty stack is popped" in {
      val emptyStack = new mutable.Stack[Int]
      a [NoSuchElementException] must be thrownBy {
        emptyStack.pop()
      }
    }
  }*/

  val g = Chess.newGame()

  "An initial game" must {
    "have 32 pieces" in {
      g.pieces.length mustBe 32
    }
  }

  "An initial game" must {
    "have 4 bishops" in {
      g.pieces.count(p => p.isInstanceOf[Bishop]) mustBe 4
    }
  }

  "An initial game" must {
    "have 4 rooks" in {
      g.pieces.count(p => p.isInstanceOf[Rook]) mustBe 4
    }
  }

  "An initial game" must {
    "have 4 knights" in {
      g.pieces.count(p => p.isInstanceOf[Knight]) mustBe 4
    }
  }

  "An initial game" must {
    "have 16 pawns" in {
      g.pieces.count(p => p.isInstanceOf[Pawn]) mustBe 16
    }
  }
  "An initial game" must {
    "have 2 queens" in {
      g.pieces.count(p => p.isInstanceOf[Queen]) mustBe 2
    }
  }
  "An initial game" must {
    "have 2 kings" in {
      g.pieces.count(p => p.isInstanceOf[King]) mustBe 2
    }
  }

  "A rook should have no moves at the start of a game in" in {
      val r: ChessPiece = g.pieces.filter(p => p.isInstanceOf[Rook]).head
      r.validMove(g).isEmpty mustBe true
  }

  "A knight should have two valid moves at the start of a game in" in {
    val r: ChessPiece = g.pieces.filter(p => p.isInstanceOf[Knight]).head
    //Console.println(r.validMove(g).toString)
    r.validMove(g).length mustBe 2
  }

  "A bishop should have no valid moves at the start of a game in" in {
    val r: ChessPiece = g.pieces.filter(p => p.isInstanceOf[Bishop]).head
    //Console.println(r.validMove(g).toString)
    r.validMove(g).length mustBe 0
  }

  "A pawn should have two valid moves at the start of a game in" in {
    val r: ChessPiece = g.pieces.filter(p => p.isInstanceOf[Pawn]).head
    val m: List[Square] = r.validMove(g)
    r.validMove(g).length mustBe 2
  }



}