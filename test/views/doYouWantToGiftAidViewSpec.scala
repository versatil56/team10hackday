package views

import play.api.data.Form
import controllers.routes
import forms.doYouWantToGiftAidFormProvider
import views.behaviours.YesNoViewBehaviours
import models.NormalMode
import views.html.doYouWantToGiftAid

class doYouWantToGiftAidViewSpec extends YesNoViewBehaviours {

  val messageKeyPrefix = "doYouWantToGiftAid"

  val form = new doYouWantToGiftAidFormProvider()()

  def createView = () => doYouWantToGiftAid(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => doYouWantToGiftAid(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "doYouWantToGiftAid view" must {

    behave like normalPage(createView, messageKeyPrefix)

    behave like yesNoPage(createViewUsingForm, messageKeyPrefix, routes.doYouWantToGiftAidController.onSubmit(NormalMode).url)
  }
}
