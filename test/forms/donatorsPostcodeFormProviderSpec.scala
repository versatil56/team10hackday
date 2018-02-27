package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class donatorsPostcodeFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "donatorsPostcode.error.required"
  val lengthKey = "donatorsPostcode.error.length"
  val maxLength = 100

  val form = new donatorsPostcodeFormProvider()()

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
