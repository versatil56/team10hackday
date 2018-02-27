package views

import play.api.data.Form
import controllers.routes
import forms.donatorsHouseNumberFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.donatorsHouseNumber

class donatorsHouseNumberViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "donatorsHouseNumber"

  val form = new donatorsHouseNumberFormProvider()()

  def createView = () => donatorsHouseNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => donatorsHouseNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donatorsHouseNumber view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.donatorsHouseNumberController.onSubmit(NormalMode).url)
  }
}
