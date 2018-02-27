package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class donatorsHouseNumberFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "donatorsHouseNumber.error.required"
  val lengthKey = "donatorsHouseNumber.error.length"
  val maxLength = 10

  val form = new donatorsHouseNumberFormProvider()()

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
