package views

import play.api.data.Form
import controllers.routes
import forms.whatIsYourDonatorsNumberFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.whatIsYourDonatorsNumber

class whatIsYourDonatorsNumberViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "whatIsYourDonatorsNumber"

  val form = new whatIsYourDonatorsNumberFormProvider()()

  def createView = () => whatIsYourDonatorsNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => whatIsYourDonatorsNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "whatIsYourDonatorsNumber view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.whatIsYourDonatorsNumberController.onSubmit(NormalMode).url)
  }
}
