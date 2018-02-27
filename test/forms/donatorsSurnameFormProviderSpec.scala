package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class donatorsSurnameFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "donatorsSurname.error.required"
  val lengthKey = "donatorsSurname.error.length"
  val maxLength = 50

  val form = new donatorsSurnameFormProvider()()

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
