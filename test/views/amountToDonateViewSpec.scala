package views

import play.api.data.Form
import controllers.routes
import forms.amountToDonateFormProvider
import models.NormalMode
import views.behaviours.IntViewBehaviours
import views.html.amountToDonate

class amountToDonateViewSpec extends IntViewBehaviours {

  val messageKeyPrefix = "amountToDonate"

  val form = new amountToDonateFormProvider()()

  def createView = () => amountToDonate(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => amountToDonate(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "amountToDonate view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like intPage(createViewUsingForm, messageKeyPrefix, routes.amountToDonateController.onSubmit(NormalMode).url)
  }
}
