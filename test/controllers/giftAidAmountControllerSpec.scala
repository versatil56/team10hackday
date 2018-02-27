package controllers

import controllers.actions._
import play.api.test.Helpers._
import views.html.giftAidAmount

class giftAidAmountControllerSpec extends ControllerSpecBase {

  def controller(dataRetrievalAction: DataRetrievalAction = getEmptyCacheMap) =
    new giftAidAmountController(frontendAppConfig, messagesApi, FakeAuthAction,
      dataRetrievalAction, new DataRequiredActionImpl)

  def viewAsString() = giftAidAmount(frontendAppConfig)(fakeRequest, messages).toString

  "giftAidAmount Controller" must {

    "return OK and the correct view for a GET" in {
      val result = controller().onPageLoad(fakeRequest)

      status(result) mustBe OK
      contentAsString(result) mustBe viewAsString()
    }
  }
}




