package views

import play.api.data.Form
import controllers.routes
import forms.donatorsPostcodeFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.donatorsPostcode

class donatorsPostcodeViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "donatorsPostcode"

  val form = new donatorsPostcodeFormProvider()()

  def createView = () => donatorsPostcode(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => donatorsPostcode(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donatorsPostcode view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.donatorsPostcodeController.onSubmit(NormalMode).url)
  }
}
