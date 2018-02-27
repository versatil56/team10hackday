package views

import play.api.data.Form
import controllers.routes
import forms.doYouHaveADonatorsNumberFormProvider
import views.behaviours.YesNoViewBehaviours
import models.NormalMode
import views.html.doYouHaveADonatorsNumber

class doYouHaveADonatorsNumberViewSpec extends YesNoViewBehaviours {

  val messageKeyPrefix = "doYouHaveADonatorsNumber"

  val form = new doYouHaveADonatorsNumberFormProvider()()

  def createView = () => doYouHaveADonatorsNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => doYouHaveADonatorsNumber(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "doYouHaveADonatorsNumber view" must {

    behave like normalPage(createView, messageKeyPrefix)

    behave like yesNoPage(createViewUsingForm, messageKeyPrefix, routes.doYouHaveADonatorsNumberController.onSubmit(NormalMode).url)
  }
}
