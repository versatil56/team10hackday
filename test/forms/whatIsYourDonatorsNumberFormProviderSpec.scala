package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class whatIsYourDonatorsNumberFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "whatIsYourDonatorsNumber.error.required"
  val lengthKey = "whatIsYourDonatorsNumber.error.length"
  val maxLength = 100

  val form = new whatIsYourDonatorsNumberFormProvider()()

  ".value" must {

    val fieldName = "value"

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      stringsWithMaxLength(maxLength)
    )

    behave like fieldWithMaxLength(
      form,
      fieldName,
      maxLength = maxLength,
      lengthError = FormError(fieldName, lengthKey, Seq(maxLength))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, requiredKey)
    )
  }
}
