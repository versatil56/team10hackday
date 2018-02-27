package utils

import uk.gov.hmrc.http.cache.client.CacheMap
import identifiers._
import models._

class UserAnswers(val cacheMap: CacheMap) extends Enumerable.Implicits {
  def whatIsDonatorsName: Option[Int] = cacheMap.getEntry[Int](whatIsDonatorsNameId.toString)

}
