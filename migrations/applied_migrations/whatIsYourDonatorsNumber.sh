#!/bin/bash

echo "Applying migration whatIsYourDonatorsNumber"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsYourDonatorsNumber               controllers.whatIsYourDonatorsNumberController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsYourDonatorsNumber               controllers.whatIsYourDonatorsNumberController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changewhatIsYourDonatorsNumber                        controllers.whatIsYourDonatorsNumberController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changewhatIsYourDonatorsNumber                        controllers.whatIsYourDonatorsNumberController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsYourDonatorsNumber.title = whatIsYourDonatorsNumber" >> ../conf/messages.en
echo "whatIsYourDonatorsNumber.heading = whatIsYourDonatorsNumber" >> ../conf/messages.en
echo "whatIsYourDonatorsNumber.checkYourAnswersLabel = whatIsYourDonatorsNumber" >> ../conf/messages.en
echo "whatIsYourDonatorsNumber.error.required = Enter whatIsYourDonatorsNumber" >> ../conf/messages.en
echo "whatIsYourDonatorsNumber.error.length = whatIsYourDonatorsNumber must be 100 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def whatIsYourDonatorsNumber: Option[String] = cacheMap.getEntry[String](whatIsYourDonatorsNumberId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def whatIsYourDonatorsNumber: Option[AnswerRow] = userAnswers.whatIsYourDonatorsNumber map {";\
     print "    x => AnswerRow(\"whatIsYourDonatorsNumber.checkYourAnswersLabel\", s\"$x\", false, routes.whatIsYourDonatorsNumberController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration whatIsYourDonatorsNumber completed"
