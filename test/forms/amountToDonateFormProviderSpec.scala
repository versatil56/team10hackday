package forms

import forms.behaviours.IntFieldBehaviours
import play.api.data.FormError

class amountToDonateFormProviderSpec extends IntFieldBehaviours {

  val form = new amountToDonateFormProvider()()

  ".value" must {

    val fieldName = "value"

    val minimum = 1
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
      nonNumericError  = FormError(fieldName, "amountToDonate.error.nonNumeric"),
      wholeNumberError = FormError(fieldName, "amountToDonate.error.wholeNumber")
    )

    behave like intFieldWithRange(
      form,
      fieldName,
      minimum       = minimum,
      maximum       = maximum,
      expectedError = FormError(fieldName, "amountToDonate.error.outOfRange", Seq(minimum, maximum))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, "amountToDonate.error.required")
    )
  }
}
