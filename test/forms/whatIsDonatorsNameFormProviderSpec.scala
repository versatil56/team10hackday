package forms

import forms.behaviours.IntFieldBehaviours
import play.api.data.FormError

class whatIsDonatorsNameFormProviderSpec extends IntFieldBehaviours {

  val form = new whatIsDonatorsNameFormProvider()()

  ".value" must {

    val fieldName = "value"

    val minimum = 0
    val maximum = 10000

    val validDataGenerator = intsInRangeWithCommas(minimum, maximum)

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      validDataGenerator
    )

    behave like intField(
      form,
      fieldName,
      nonNumericError  = FormError(fieldName, "whatIsDonatorsName.error.nonNumeric"),
      wholeNumberError = FormError(fieldName, "whatIsDonatorsName.error.wholeNumber")
    )

    behave like intFieldWithRange(
      form,
      fieldName,
      minimum       = minimum,
      maximum       = maximum,
      expectedError = FormError(fieldName, "whatIsDonatorsName.error.outOfRange", Seq(minimum, maximum))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, "whatIsDonatorsName.error.required")
    )
  }
}
