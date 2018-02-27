/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils

import uk.gov.hmrc.http.cache.client.CacheMap
import identifiers._
import models._

class UserAnswers(val cacheMap: CacheMap) extends Enumerable.Implicits {
  def doYouWantToGiftAid: Option[Boolean] = cacheMap.getEntry[Boolean](doYouWantToGiftAidId.toString)

  def whatIsYourDonatorsNumber: Option[String] = cacheMap.getEntry[String](whatIsYourDonatorsNumberId.toString)

  def doYouHaveADonatorsNumber: Option[Boolean] = cacheMap.getEntry[Boolean](doYouHaveADonatorsNumberId.toString)

  def donatorAgreement: Option[Boolean] = cacheMap.getEntry[Boolean](donatorAgreementId.toString)

  def donationDate: Option[String] = cacheMap.getEntry[String](donationDateId.toString)

  def amountToDonate: Option[Int] = cacheMap.getEntry[Int](amountToDonateId.toString)

  def donatorsPostcode: Option[String] = cacheMap.getEntry[String](donatorsPostcodeId.toString)

  def donatorsHouseNumber: Option[String] = cacheMap.getEntry[String](donatorsHouseNumberId.toString)

  def donatorsEmail: Option[String] = cacheMap.getEntry[String](donatorsEmailId.toString)

  def donatorsSurname: Option[String] = cacheMap.getEntry[String](donatorsSurnameId.toString)

  def donatorsName: Option[String] = cacheMap.getEntry[String](donatorsNameId.toString)

  def whatIsDonatorsName: Option[Int] = cacheMap.getEntry[Int](whatIsDonatorsNameId.toString)

}
