package forms

import javax.inject.Inject

import forms.mappings.Mappings
import play.api.data.Form

class whatIsDonatorsNameFormProvider @Inject() extends FormErrorHelper with Mappings {

  def apply(): Form[Int] =
    Form(
      "value" -> int(
        "whatIsDonatorsName.error.required",
        "whatIsDonatorsName.error.wholeNumber",
        "whatIsDonatorsName.error.nonNumeric")
          .verifying(inRange(0, 10000, "whatIsDonatorsName.error.outOfRange"))
    )
}
