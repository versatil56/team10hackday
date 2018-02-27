package views

import play.api.data.Form
import controllers.routes
import forms.donatorsSurnameFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.donatorsSurname

class donatorsSurnameViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "donatorsSurname"

  val form = new donatorsSurnameFormProvider()()

  def createView = () => donatorsSurname(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => donatorsSurname(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donatorsSurname view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.donatorsSurnameController.onSubmit(NormalMode).url)
  }
}
