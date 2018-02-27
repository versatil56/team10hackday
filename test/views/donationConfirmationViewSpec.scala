package views

import views.behaviours.ViewBehaviours
import views.html.donationConfirmation

class donationConfirmationViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "donationConfirmation"

  def createView = () => donationConfirmation(frontendAppConfig)(fakeRequest, messages)

  "donationConfirmation view" must {
    behave like normalPage(createView, messageKeyPrefix)
  }
}
