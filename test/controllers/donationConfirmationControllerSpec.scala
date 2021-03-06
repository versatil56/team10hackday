package controllers

import controllers.actions._
import play.api.test.Helpers._
import views.html.donationConfirmation

class donationConfirmationControllerSpec extends ControllerSpecBase {

  def controller(dataRetrievalAction: DataRetrievalAction = getEmptyCacheMap) =
    new donationConfirmationController(frontendAppConfig, messagesApi, FakeAuthAction,
      dataRetrievalAction, new DataRequiredActionImpl)

  def viewAsString() = donationConfirmation(frontendAppConfig)(fakeRequest, messages).toString

  "donationConfirmation Controller" must {

    "return OK and the correct view for a GET" in {
      val result = controller().onPageLoad(fakeRequest)

      status(result) mustBe OK
      contentAsString(result) mustBe viewAsString()
    }
  }
}




