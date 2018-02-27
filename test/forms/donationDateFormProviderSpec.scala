package forms

import forms.behaviours.StringFieldBehaviours
import play.api.data.FormError

class donationDateFormProviderSpec extends StringFieldBehaviours {

  val requiredKey = "donationDate.error.required"
  val lengthKey = "donationDate.error.length"
  val maxLength = 20

  val form = new donationDateFormProvider()()

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
