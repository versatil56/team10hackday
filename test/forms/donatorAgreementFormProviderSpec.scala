package forms

import forms.behaviours.BooleanFieldBehaviours
import play.api.data.FormError

class donatorAgreementFormProviderSpec extends BooleanFieldBehaviours {

  val requiredKey = "donatorAgreement.error.required"
  val invalidKey = "error.boolean"

  val form = new donatorAgreementFormProvider()()

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
