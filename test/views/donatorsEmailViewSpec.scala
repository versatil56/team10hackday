package views

import play.api.data.Form
import controllers.routes
import forms.donatorsEmailFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.donatorsEmail

class donatorsEmailViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "donatorsEmail"

  val form = new donatorsEmailFormProvider()()

  def createView = () => donatorsEmail(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => donatorsEmail(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donatorsEmail view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.donatorsEmailController.onSubmit(NormalMode).url)
  }
}
