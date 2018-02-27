package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class doYouWantToGiftAidFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "doYouWantToGiftAid.error.required"
  val invalidKey = "error.boolean"

  val form = new doYouWantToGiftAidFormProvider()()

  ".value" must {

    val fieldName = "value"

    behave like booleanField(
      form,
      fieldName,
      invalidError = FormError(fieldName, invalidKey)
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
