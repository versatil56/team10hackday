package views

import play.api.data.Form
import controllers.routes
import forms.donationDateFormProvider
import models.NormalMode
import views.behaviours.StringViewBehaviours
import views.html.donationDate

class donationDateViewSpec extends StringViewBehaviours {

  val messageKeyPrefix = "donationDate"

  val form = new donationDateFormProvider()()

  def createView = () => donationDate(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[String]) => donationDate(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donationDate view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like stringPage(createViewUsingForm, messageKeyPrefix, routes.donationDateController.onSubmit(NormalMode).url)
  }
}
