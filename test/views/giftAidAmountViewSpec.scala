package views

import views.behaviours.ViewBehaviours
import views.html.giftAidAmount

class giftAidAmountViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "giftAidAmount"

  def createView = () => giftAidAmount(frontendAppConfig)(fakeRequest, messages)

  "giftAidAmount view" must {
    behave like normalPage(createView, messageKeyPrefix)
  }
}
