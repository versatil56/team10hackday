#!/bin/bash

echo "Applying migration donatorsHouseNumber"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /donatorsHouseNumber               controllers.donatorsHouseNumberController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /donatorsHouseNumber               controllers.donatorsHouseNumberController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changedonatorsHouseNumber                        controllers.donatorsHouseNumberController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changedonatorsHouseNumber                        controllers.donatorsHouseNumberController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "donatorsHouseNumber.title = donatorsHouseNumber" >> ../conf/messages.en
echo "donatorsHouseNumber.heading = donatorsHouseNumber" >> ../conf/messages.en
echo "donatorsHouseNumber.checkYourAnswersLabel = donatorsHouseNumber" >> ../conf/messages.en
echo "donatorsHouseNumber.error.required = Enter donatorsHouseNumber" >> ../conf/messages.en
echo "donatorsHouseNumber.error.length = donatorsHouseNumber must be 10 characters or less" >> ../conf/messages.en

echo "Adding helper line into UserAnswers"
awk '/class/ {\
     print;\
     print "  def donatorsHouseNumber: Option[String] = cacheMap.getEntry[String](donatorsHouseNumberId.toString)";\
     print "";\
     next }1' ../app/utils/UserAnswers.scala > tmp && mv tmp ../app/utils/UserAnswers.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def donatorsHouseNumber: Option[AnswerRow] = userAnswers.donatorsHouseNumber map {";\
     print "    x => AnswerRow(\"donatorsHouseNumber.checkYourAnswersLabel\", s\"$x\", false, routes.donatorsHouseNumberController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration donatorsHouseNumber completed"
