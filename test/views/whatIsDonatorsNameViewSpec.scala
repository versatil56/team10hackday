package views

import play.api.data.Form
import controllers.routes
import forms.whatIsDonatorsNameFormProvider
import models.NormalMode
import views.behaviours.IntViewBehaviours
import views.html.whatIsDonatorsName

class whatIsDonatorsNameViewSpec extends IntViewBehaviours {

  val messageKeyPrefix = "whatIsDonatorsName"

  val form = new whatIsDonatorsNameFormProvider()()

  def createView = () => whatIsDonatorsName(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => whatIsDonatorsName(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "whatIsDonatorsName view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like intPage(createViewUsingForm, messageKeyPrefix, routes.whatIsDonatorsNameController.onSubmit(NormalMode).url)
  }
}
