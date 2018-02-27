package views

import play.api.data.Form
import controllers.routes
import forms.donatorAgreementFormProvider
import views.behaviours.YesNoViewBehaviours
import models.NormalMode
import views.html.donatorAgreement

class donatorAgreementViewSpec extends YesNoViewBehaviours {

  val messageKeyPrefix = "donatorAgreement"

  val form = new donatorAgreementFormProvider()()

  def createView = () => donatorAgreement(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => donatorAgreement(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "donatorAgreement view" must {

    behave like normalPage(createView, messageKeyPrefix)

    behave like yesNoPage(createViewUsingForm, messageKeyPrefix, routes.donatorAgreementController.onSubmit(NormalMode).url)
  }
}
